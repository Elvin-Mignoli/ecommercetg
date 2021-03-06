/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.domain.CaixaEntrada;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Mensagem;
import br.com.ecommerce.domain.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
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
                openConnection();
            
            conexao.setAutoCommit(false);
            
            //procurar a caixa de mensagem do destinatario
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("INSERT INTO MENSAGENS ");
            sql.append("(id_caixa_destinatario,mensagem,data_msg, assunto,remetente,destinatario,id_caixa_remetente,"
                    + "flg_resposta,flg_excluida_enviada,flg_excluida_recebido,idPedido) VALUES(?,?,NOW(),?,?,?,?,?,false,false,?)");
            
            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1,entrada.getMensagem().getId_caixa_destinatario());
            pst.setString(2, entrada.getMensagem().getDescricao());
            pst.setString(3, entrada.getMensagem().getAssunto());
            pst.setString(4, entrada.getMensagem().getRemetente());
            pst.setString(5, entrada.getMensagem().getDestinatario());
            pst.setInt(6, entrada.getId());
            pst.setBoolean(7, entrada.getMensagem().isFlg_resposta());
            pst.setInt(8, entrada.getMensagem().getIdPedido());
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
                if(transaction)
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
            if(conexao == null || conexao.isClosed())
                openConnection();
            
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            
            if(entrada.getMensagem().isFlg_excluida_enviada())
            {
                sql.append("UPDATE MENSAGENS SET flg_excluida_enviada = ?, flg_excluida_recebido = ? ");
                sql.append("WHERE  id = ?");
                
                pst = conexao.prepareStatement(sql.toString());
                
                pst.setBoolean(1, entrada.getMensagem().isFlg_excluida_enviada());
                pst.setBoolean(2, entrada.getMensagem().isFlg_excluida_recebido());
                pst.setInt(3, entrada.getMensagem().getId());
            }else
            {
                sql.append("UPDATE MENSAGENS SET flg_excluida_enviada = ?, flg_excluida_recebido = ? ");
                sql.append("WHERE id_caixa_destinatario = ? and id = ?");
                
                pst = conexao.prepareStatement(sql.toString());
                
                pst.setBoolean(1, entrada.getMensagem().isFlg_excluida_enviada());
                pst.setBoolean(2, entrada.getMensagem().isFlg_excluida_recebido());
                pst.setInt(3, entrada.getId());
                pst.setInt(4, entrada.getMensagem().getId());
            }
           
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
        } finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            } catch (SQLException e)
            {
                throw new SQLException();
            }
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
            
            sql.append("SELECT M.* FROM MENSAGENS as M, CAIXA_ENTRADA as C  "
                    + "WHERE M.ID_CAIXA_DESTINATARIO= C.ID and M.id_caixa_destinatario = ? AND flg_excluida_recebido = false ORDER BY  M.DATA_MSG ");
        
            
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
                msg.setFlg_resposta(rs.getBoolean("flg_resposta"));
                msg.setFlg_excluida_enviada(rs.getBoolean("flg_excluida_enviada"));
                msg.setFlg_excluida_recebido(rs.getBoolean("flg_excluida_recebido"));
                msg.setFlgAberto(rs.getBoolean("flg_msg_aberta"));
                msg.setIdPedido(rs.getInt("idPedido"));
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
       try{
        CaixaEntrada entrada = (CaixaEntrada) entidade;
        if(conexao == null || conexao.isClosed())
            openConnection();

        String sql = "SELECT * FROM MENSAGENS WHERE ID=?";

        pst = conexao.prepareStatement(sql);
        pst.setInt(1, entrada.getMensagem().getId());
        ResultSet rs = pst.executeQuery();
        if(rs.next())
        {
            Mensagem msg  = new Mensagem();
            msg.setId(rs.getInt("id"));
            msg.setAssunto(rs.getString("assunto"));
            msg.setData_msg(rs.getDate("data_msg"));
            msg.setDescricao(rs.getString("mensagem"));
            msg.setDestinatario(rs.getString("destinatario"));
            msg.setRemetente(rs.getString("remetente"));
            msg.setId_caixa_remetente(rs.getInt("id_caixa_remetente"));
            msg.setFlg_resposta(rs.getBoolean("flg_resposta"));
            msg.setFlg_excluida_enviada(rs.getBoolean("flg_excluida_enviada"));
            msg.setFlg_excluida_recebido(rs.getBoolean("flg_excluida_recebido"));
            msg.setFlgAberto(rs.getBoolean("flg_msg_aberta"));
            msg.setIdPedido(rs.getInt("idPedido"));
            entrada.setMensagem(msg);
        }
         return entrada;
       }
       finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    } 
    //Métodoas para buscar as mensagens envioadas por um usupario
     public List<EntidadeDominio> consultarMensagensEnvidas(EntidadeDominio entidade) throws SQLException
     {
         CaixaEntrada entrada = (CaixaEntrada) entidade;
        
        List<EntidadeDominio> mensagens = new ArrayList<>();
        try
        {
            if(conexao == null || conexao.isClosed())
                openConnection();
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT M.* FROM MENSAGENS as M, CAIXA_ENTRADA as C  "
                    + "WHERE M.ID_CAIXA_DESTINATARIO= C.ID and M.id_caixa_remetente = ?  and flg_excluida_enviada = false ORDER BY  M.DATA_MSG ");
        
            
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
                msg.setFlg_resposta(rs.getBoolean("flg_resposta"));
                msg.setFlg_excluida_enviada(rs.getBoolean("flg_excluida_enviada"));
                msg.setFlg_excluida_recebido(rs.getBoolean("flg_excluida_recebido"));
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
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
     }
     
     public void AtualizaMensagemAberta(EntidadeDominio entidade) throws SQLException
     {
         Mensagem msg = (Mensagem) entidade;
         try
         {
             if(conexao == null || conexao.isClosed())
             {
                 openConnection();  //abrindo conexao com o banco
                 conexao.setAutoCommit(false);
             }
             
             StringBuilder sql = new StringBuilder();
             
             sql.append("UPDATE MENSAGENS ");
             sql.append("SET flg_msg_aberta = true ");
             sql.append("WHERE id = ?");
             
             pst = conexao.prepareStatement(sql.toString());
             
             pst.setInt(1, msg.getId());
             
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
             throw new SQLException("Erro ao altera mensagem");
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
     
     public List<EntidadeDominio> historico(EntidadeDominio entidade) throws SQLException{
         Mensagem msg = (Mensagem) entidade;
         List<EntidadeDominio> mensagens = new ArrayList<>();
         try
         {
             if(conexao == null || conexao.isClosed())
             {
                 openConnection();  //abrindo conexao com o banco
                 conexao.setAutoCommit(false);
             }
             
             StringBuilder sql = new StringBuilder();
             
             sql.append("SELECT * FROM MENSAGENS ");
             sql.append("WHERE id_caixa_destinatario = ? and id_caixa_remetente = ? and idPedido = ?\n" +
                "or id_caixa_destinatario = ? and id_caixa_remetente = ? and idPedido = ?");
             
             pst = conexao.prepareStatement(sql.toString());
             
            pst.setInt(1, msg.getId_caixa_destinatario());
            pst.setInt(2, msg.getId_caixa_remetente());
             pst.setInt(3, msg.getIdPedido());
            pst.setInt(4, msg.getId_caixa_remetente()); 
            pst.setInt(5, msg.getId_caixa_destinatario());
            pst.setInt(6, msg.getIdPedido());
               
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                Mensagem mensagem = new Mensagem();
                
                mensagem.setDescricao(rs.getString("Mensagem"));
                mensagem.setId(rs.getInt("id"));
                mensagem.setData_msg(rs.getDate("data_msg"));
                mensagem.setAssunto(rs.getString("assunto"));
                mensagem.setRemetente(rs.getString("remetente"));
                mensagem.setDestinatario(rs.getString("destinatario"));
                mensagem.setId_caixa_remetente(rs.getInt("id_caixa_remetente"));
                mensagem.setFlg_resposta(rs.getBoolean("flg_resposta"));
                mensagem.setFlg_excluida_enviada(rs.getBoolean("flg_excluida_enviada"));
                mensagem.setFlg_excluida_recebido(rs.getBoolean("flg_excluida_recebido"));
                mensagens.add(mensagem);
            } 
            return mensagens;
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
             throw new SQLException("Erro ao no servidor, tente novamente mais tarde?");
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
