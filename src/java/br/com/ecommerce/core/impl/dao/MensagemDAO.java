/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.domain.CaixaEntrada;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Mensagem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author java
 */
public class MensagemDAO extends AbstractDAO
{

    public MensagemDAO(Connection conexao)
    {
        super(conexao);
    }

    public MensagemDAO()
    {
        
    } 
    
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        CaixaEntrada entrada = (CaixaEntrada) entidade;
        
        try
        {
            if(conexao == null || conexao.isClosed())
            {
                throw new SQLDataException("Desculpe, ocorreu um erro inesperado!!");
            }
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("INSERT INTO MENSAGENS ");
            sql.append("(id_caixa_entrada,mensagem) VALUES(?,?)");
            
            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1,entrada.getId());
            pst.setString(2, entrada.getMensagem().getDescricao());
            
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            
            if(rs.next())
            {
                entrada.getMensagem().setId(rs.getInt("id"));
            }
            
            conexao.commit();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                conexao.close();
            }
            catch(SQLException ex1)
            {
                ex1.printStackTrace();
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
        CaixaEntrada entrada = (CaixaEntrada) entidade;
        
        try
        {
            openConnection();
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("DELETE FROM MENSAGENS ");
            sql.append("WHERE id_caixa_entrada = ? and id = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setInt(1, entrada.getId());
            pst.setInt(2, entrada.getMensagem().getId());
            
            pst.execute();
            
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
            
            throw new SQLException("Desculpe, algum erro inesperado aconteceu!");
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        CaixaEntrada entrada = (CaixaEntrada) entidade;
        List<EntidadeDominio> mensagens = new ArrayList<>();
        try
        {
            if(conexao == null || conexao.isClosed())
                openConnection();
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT M.* FROM MENSAGENS as M, CAIXA_ENTRADA as C");
            sql.append("WHERE M.id_caixa_entrada = ? ");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setInt(1, entrada.getId());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                Mensagem msg = new Mensagem();
                
                msg.setDescricao(rs.getString("Mensagem"));
                msg.setId(rs.getInt("id"));
                
                mensagens.add(msg);
            }
            
            return mensagens;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException("Erro ao consultar as mensagens!");
        }
        finally
        {
            try
            {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
