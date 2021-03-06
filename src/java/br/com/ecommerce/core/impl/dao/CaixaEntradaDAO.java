/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.domain.CaixaEntrada;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author java
 */
public class CaixaEntradaDAO extends AbstractDAO
{

    public CaixaEntradaDAO(Connection conexao)
    {
        super(conexao);
    }

    public CaixaEntradaDAO()
    {

    }
    
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        CaixaEntrada entrada = (CaixaEntrada) entidade;
        int id = 0;
        try
        {
            if(conexao == null || conexao.isClosed())
            {
                openConnection();   //abrindo conexao com o banco de dados
                conexao.setAutoCommit(false);
            }
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("INSERT INTO CAIXA_ENTRADA ");
            
            //decidindo se é cliente ou prestador
            if(entrada.getCliente() != null)
            {
                sql.append("(ID_CLIENTE) ");
                id = entrada.getCliente().getId();
            }
            else if(entrada.getPrestador() != null)
            {
                sql.append("(ID_PRESTADOR) ");
                id = entrada.getPrestador().getId();
            }else
            {
                sql.append("(ID_HEADHUNTER) ");
                id = entrada.getHeadHunter().getId();
            }
            
            sql.append("VALUES (?)");
            
            pst = conexao.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1,id);
            
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();  //executando a query no banco de dados!
            if (rs.next())            //se conseguir interar pelo menos 1 vez
            {//conseguiu iterar
                entrada.setId(rs.getInt("id"));
            }
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
            throw new SQLException("Desculpe, algum erro inesperado aconteceu!");
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
           
            IDAO dao = new MensagemDAO();
            
            dao.salvar(entidade);
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        IDAO dao = new MensagemDAO();
        
        dao.excluir(entidade);
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        CaixaEntrada entrada = (CaixaEntrada) entidade;
        int id = 0;
        
        try
        {
             if(conexao == null || conexao.isClosed())
                openConnection();
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT ID ");
            sql.append("FROM caixa_entrada ");
            
            //decidindo se é cliente ou prestador
            if(entrada.getCliente() != null)
            {
                sql.append("WHERE ID_CLIENTE = ?");
                id = entrada.getCliente().getId();
            }
            else if(entrada.getPrestador() != null)
            {
                sql.append("WHERE ID_PRESTADOR = ?");
                id = entrada.getPrestador().getId();
            }else
            {
                sql.append("WHERE ID_HEADHUNTER= ?");
                id = entrada.getHeadHunter().getId();
            }
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                entrada.setId(rs.getInt("id"));
            }
            
            IDAO dao = new MensagemDAO(conexao);
            
            entrada.setMensagens(dao.consultar(entrada));
            
            return entrada;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
            return null;
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
