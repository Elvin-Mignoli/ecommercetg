/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class AvaliacaoDAO extends AbstractDAO
{

    public AvaliacaoDAO()
    {
    }
    
    public AvaliacaoDAO(Connection conexao)
    {
        super(conexao);
    }
    
    
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        Pedido pedido = (Pedido) entidade;
        try
        {
            if(conexao == null || conexao.isClosed())
            {
                openConnection();
                
                if(transaction)
                    conexao.setAutoCommit(false);
            }
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("INSERT INTO AVALIACOES ");
            sql.append("(id_cliente,id_prestador,id_pedido,pontuacao,descricao,data) ");
            sql.append("VALUES (?,?,?,?,?,?)");
            
            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1,pedido.getCliente().getId());
            pst.setInt(2, pedido.getPrestadorFinalista().getId());
            pst.setInt(3, pedido.getId());
            pst.setDouble(4, pedido.getAvaliacao().getPontos());
            pst.setString(5, pedido.getAvaliacao().getDescricao());
            pst.setTimestamp(6, new Timestamp(pedido.getAvaliacao().getData().getTimeInMillis()));
            
            pst.execute();
            
            ResultSet rs = pst.getGeneratedKeys();
            
            if(rs.next())
            {
                pedido.getAvaliacao().setId(rs.getInt("id"));
            }
            else
            {
                throw new SQLException("Desculpe, algum erro ocorreu!");
            }
            
            sql = new StringBuilder();
            sql.append("SELECT AVALIACAO(?)");
            
            pst = conexao.prepareStatement(sql.toString());
            pst.setInt(1, pedido.getPrestadorFinalista().getId());
            pst.execute();
            
            if(transaction)
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
            throw new SQLException("Desculpe, algum erro ocorreu!");
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
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        /*try
        {
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }*/
        return null;
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
