/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Status;
import br.com.ecommerce.domain.Transacao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author admin
 */
public class TransacaoDAO extends AbstractDAO
{

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        Transacao tr = (Transacao) entidade;
        try
        {
            if(conexao == null || conexao.isClosed())   //conexao eh nula ou fechada?
            {
                openConnection();
                conexao.setAutoCommit(false);
            }
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("INSERT INTO transacoes ");
            sql.append("(id_pedidos,tipo_pagamento,data_transacao,status,id_prestador,id_cliente) ");
            sql.append("VALUES (?,?,?,?,?,?)");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setInt(1, tr.getPedido().getId());
            pst.setString(2, tr.getTipoPagamento());
            Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
            pst.setTimestamp(3, time);
            pst.setString(4, tr.getStatus().name());
            pst.setInt(5, tr.getPrestador().getId());
            pst.setInt(6, tr.getCliente().getId());
            
            pst.execute();  //executa o codigo sql no banco!
            
            PedidoDAO dao = new PedidoDAO(conexao);
            
            dao.AtualizarStatusPedido(tr.getPedido());
            
            TransferirValor(tr);
            
            if(transaction) //eh a sua transacao?
                conexao.commit();
        }
        catch(SQLException ex)
        {
            try
            {
                conexao.rollback();
            }
            catch(SQLException ex1)
            {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
            throw new SQLException("Erro ao gravar a transacao");
        }
        finally
        {
            try
            {
                if(transaction) //foi esta classe que abriu a transacao?
                    conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        Transacao tr = (Transacao) entidade;
        try
        {
            if(conexao == null || conexao.isClosed()) //abrindo conexao
                openConnection();
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("UPDATE transacoes ");
            sql.append("SET status = ? ");
            sql.append("WHERE id_pedido = ? and ");
            sql.append("id_cliente = ? and ");
            sql.append("id_prestador = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, tr.getStatus().name());
            pst.setInt(2, tr.getPedido().getId());
            pst.setInt(3, tr.getCliente().getId());
            pst.setInt(2, tr.getPrestador().getId());
            
            pst.executeUpdate();
            
            if(transaction) //foi essa classe que abriu a transacao?
                conexao.commit();
        }
        catch(SQLException ex)
        {
            try
            {
                conexao.rollback();
            }
            catch(SQLException ex1)
            {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
            throw new SQLException("Erro ao atualizar status da transacao");
        }
        finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            if(conexao == null || conexao.isClosed())
                openConnection();
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT * FROM transacoes ");
            //sql.append("WHERE id = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> entidades = new ArrayList<>();
            
            /*
                Estou pegando apenas informacoes basicas da transacoes,
                caso seja necessario pegar mais informacoes do cliente ou do prestador
                altere o while.
            */
            while(rs.next())
            {
                Transacao tr = new Transacao();
                tr.setId(rs.getInt("id"));
                tr.setPedido(new Pedido());
                tr.getPedido().setId(rs.getInt("id_pedido"));
                tr.setCliente(new Cliente());
                tr.getCliente().setId(rs.getInt("id_cliente"));
                tr.setPrestador(new PrestadorServico());
                tr.getPrestador().setId(rs.getInt("id_prestador"));
                tr.setStatus(Status.valueOf(rs.getString("status")));
                tr.setTipoPagamento(rs.getString("tipo_pagamento"));
                tr.setData(Calendar.getInstance());
                tr.getData().setTimeInMillis(rs.getTimestamp("data_transacao").getTime());
                
                entidades.add(tr);
            }
            
            return entidades;
        }
        catch(SQLException ex)
        {
            try
            {
                conexao.rollback();
            }
            catch(SQLException ex1)
            {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
            throw new SQLException("Erro ao consultar lista de transacoes");
        }
        finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        Transacao tr = (Transacao) entidade;
        try
        {
            if(conexao == null || conexao.isClosed())
                openConnection();
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT * FROM transacoes ");
            sql.append("WHERE id_cliente = ? and ");
            sql.append("id_prestador= ? and ");
            sql.append("id_pedido = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                tr = new Transacao();
                tr.setId(rs.getInt("id"));
                tr.setPedido(new Pedido());
                tr.getPedido().setId(rs.getInt("id_pedido"));
                tr.setCliente(new Cliente());
                tr.getCliente().setId(rs.getInt("id_cliente"));
                tr.setPrestador(new PrestadorServico());
                tr.getPrestador().setId(rs.getInt("id_prestador"));
                tr.setStatus(Status.valueOf(rs.getString("status")));
                tr.setTipoPagamento(rs.getString("tipo_pagamento"));
                tr.setData(Calendar.getInstance());
                tr.getData().setTimeInMillis(rs.getTimestamp("data_transacao").getTime());
            }
            return tr;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException("Erro ao consultar uma transacao");
        }
        finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Verifica o saldo e transfere os dados de uma conta para outra caso tenha alguma saldo
     * @param entidade - Transação contendo os dados do Cliente
     */
    public void TransferirValor(EntidadeDominio entidade) throws SQLException
    {
        Transacao tr = (Transacao) entidade;
        try
        {
            if(conexao == null || conexao.isClosed())
            {
                openConnection();   //abrindo conexao
                conexao.setAutoCommit(false);
            }
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("UPDATE saldos ");
            sql.append("SET valor = valor - ? ");
            sql.append("WHERE id_cliente = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setDouble(1, tr.getValor());
            pst.setInt(2, tr.getCliente().getId());
            
            pst.executeUpdate();
            
            //if(transaction)
              //  conexao.commit();
        }
        catch(SQLException ex)
        {
            try
            {
                conexao.rollback();
            }
            catch(SQLException  ex1)
            {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
            throw new SQLException("Erro ao efetuar a Transação");
        }
        finally
        {
            /*try
            {
              //  if(transaction) //foi essa classe que abriu a transacao?
                   // conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            } */
        }
    }
    
    /**
     * Consulta o saldo de um cliente para uma determinada transacao
     * @param entidade
     * @return
     * @throws SQLException 
     */
    public Boolean consultaSaldo(EntidadeDominio entidade) throws SQLException
    {
        Transacao tr = (Transacao) entidade;
        try
        {
            if(conexao == null || conexao.isClosed())
            {
               openConnection();
               conexao.setAutoCommit(false);
            }
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT valor FROM saldos WHERE id_cliente = ? AND valor > ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setInt(1, tr.getCliente().getId());
            pst.setDouble(2, tr.getValor());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())//retorno alguma consulta?
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
}
