/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.domain.CartaoCredito;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class CartaoCreditoDAO extends AbstractDAO
{

    public CartaoCreditoDAO()
    {
    }

    public CartaoCreditoDAO(Connection conexao)
    {
        super(conexao);
    }
    
    /**
     * Método para salvar dados de um cartão de crédito!
     * @param entidade
     * @throws SQLException 
     */
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        CartaoCredito cartao = (CartaoCredito) entidade;
        StringBuilder sql = new StringBuilder();
        try
        {
            if(conexao == null || conexao.isClosed())   //conexao fechada? 
                openConnection();   //abrindo a conexao
            
            sql.append("INSERT INTO CARTOES ");
            sql.append("(numero_cartao,numero_seguranca,bandeira,nome_titular,validade)");
            sql.append("VALUES (?,?,?,?,?) ");
            
            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, cartao.getNumeroCartao());
            pst.setString(2, cartao.getNumeroSeguranca());
            pst.setString(3, cartao.getBandeira());
            pst.setString(4, cartao.getNomeTitular());
            if(cartao.getValidade() == null)
                pst.setDate(5, null);
            else
                pst.setDate(5, new Date(cartao.getValidade().getTimeInMillis()));
            
            pst.executeUpdate();  //salvando os dados do cartão !
            
            ResultSet rs = pst.getGeneratedKeys();
            
            if(rs.next())
            {
                entidade.setId(rs.getInt("id"));
            }
            else
            {
                throw new SQLException("Erro ao retornar ID do cartão");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        StringBuilder sql = new StringBuilder();
        CartaoCredito cartao = (CartaoCredito) entidade;
        
        try
        {
            openConnection();   //abrindo conexao com o banco de dados
            conexao.setAutoCommit(false);   //setando autocommit
            
            sql.append("UPDATE CARTOES ");
            sql.append("SET ");
            sql.append("numero_cartao = ?, ");
            sql.append("numero_seguranca = ?, ");
            sql.append("bandeira = ?, ");
            sql.append("nome_titular = ?, ");
            sql.append("validade = ? ");
            sql.append("WHERE ID = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, cartao.getNumeroCartao());
            pst.setString(2, cartao.getNumeroSeguranca());
            pst.setString(3, cartao.getBandeira());
            pst.setString(4, cartao.getNomeTitular());
            
            if(cartao.getValidade() == null)
                pst.setDate(5,null);
            else
                pst.setDate(5, new Date(cartao.getValidade().getTimeInMillis()));
            
            pst.setInt(6, cartao.getId());
            
            pst.executeUpdate();
            
            conexao.commit();   //commitando as alteracoes
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
            throw new SQLException(ex);
        }
        finally
        {
           try
           {
               pst.close();
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
        // sem implementação por enquanto
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        CartaoCredito cartao = (CartaoCredito) entidade;
        StringBuilder sql = new StringBuilder();
        
        try
        {
            openConnection();   //abrindo conexao com o banco
            sql.append("SELECT * FROM CARTOES ");
            sql.append("WHERE ID = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setInt(1, cartao.getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                cartao.setNomeTitular(rs.getString("nome_titular"));
                cartao.setNumeroCartao(rs.getString("numero_cartao"));
                cartao.setNumeroSeguranca(rs.getString("numero_seguranca"));
                Calendar dt = Calendar.getInstance();
                Date validade = rs.getDate("validade");
                
                if(validade == null)
                    dt.setTimeInMillis(0);
                else
                    dt.setTimeInMillis(validade.getTime());
                
                cartao.setValidade(dt);
                cartao.setId(rs.getInt("id"));
                return cartao;
            }
            else
            {
                return null;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
        finally
        {
            try
            {
                pst.close();
                conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
}
