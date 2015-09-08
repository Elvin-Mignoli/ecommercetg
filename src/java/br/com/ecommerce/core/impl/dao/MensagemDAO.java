/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.domain.CaixaEntrada;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Mensagem;
import br.com.ecommerce.domain.Usuario;
import java.sql.Connection;
import java.sql.Date;
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
            openConnection();
            conexao.setAutoCommit(false);
            
            //procurar a caixa de mensagem do destinatario
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("INSERT INTO MENSAGENS ");
            sql.append("(id_caixa_entrada,mensagem,data_msg, assunto,remetente,destinatario,id_caixa_remetente) VALUES(?,?,NOW(),?,?,?,?)");
            
            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1,entrada.getMensagem().getId_caixa_remetente());
            pst.setString(2, entrada.getMensagem().getDescricao());
            pst.setString(3, entrada.getMensagem().getAssunto());
            pst.setString(4, entrada.getMensagem().getRemetente());
            pst.setString(5, entrada.getMensagem().getDestinatario());
            pst.setInt(6, entrada.getId());
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
            throw new SQLException(ex);
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
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("DELETE FROM MENSAGENS ");
            sql.append("WHERE id_caixa_entrada = ? and id = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setInt(1, entrada.getId());
            pst.setInt(2, entrada.getMensagem().getId());
            
            pst.executeUpdate();
            
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
            
            sql.append("SELECT M.* FROM MENSAGENS as M, CAIXA_ENTRADA as C  WHERE M.ID_CAIXA_ENTRADA = C.ID and M.ID_CAIXA_ENTRADA = ? ORDER BY  M.DATA_MSG ");
        
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setInt(1, entrada.getId());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                Mensagem msg = new Mensagem();
                
                msg.setDescricao(rs.getString("Mensagem"));
                msg.setId(rs.getInt("id"));
                msg.setData_msg(rs.getDate("data_msg"));
                msg.setAssunto(rs.getString("assunto"));
                msg.setRemetente(rs.getString("remetente"));
                msg.setDestinatario(rs.getString("destinatario"));
                msg.setId_caixa_remetente(rs.getInt("id_caixa_remetente"));
                
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
