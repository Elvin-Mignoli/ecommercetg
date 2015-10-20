/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elvin
 */
public class EnderecoDAO extends AbstractDAO
{

    /**
     * Construtor para receber uma conexao já aberta por um DAO!
     *
     * @param conexao
     */
    public EnderecoDAO(Connection conexao)
    {
        super(conexao);
    }

    public EnderecoDAO()
    {

    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            if (conexao == null || conexao.isClosed())//Abrir conexão com banco
            {
                openConnection();
                conexao.setAutoCommit(false);//setando auto commit para false
            }
            Endereco end = (Endereco) entidade;
            //criando sql para insert no banco
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ENDERECOS ");
            sql.append("(logradouro,numero,bairro,cidade,estado,complemento,cep) ");
            sql.append("VALUES(?,?,?,?,?,?,?)");

            pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);//criando caminho para conexao no banco de dados
            //setando parametros do insert
            pst.setString(1, end.getLogradouro());
            pst.setString(2, end.getNumero());
            pst.setString(3, end.getBairro());
            pst.setString(4, end.getCidade());
            pst.setString(5, end.getEstado());
            pst.setString(6, end.getComplemento());
            pst.setString(7, end.getCep());

            pst.executeUpdate();
            
            ResultSet resultado = pst.getGeneratedKeys(); //pegando id da ultima insercao no banco

            if (resultado.next()) //se conseguir interar pelo menos 1 vez
            {
                //end.setId(resultado.getInt(1));
                entidade.setId(resultado.getInt(1));
            }
        } catch (SQLException ex)
        {
            try
            {
                conexao.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
            throw new SQLException();
        } 
    }

    /**
     *
     * @param entidade
     * @throws SQLException
     */
    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            if (conexao == null || conexao.isClosed())
            {
                openConnection();//Abrir conexão com banco
            }
            Endereco end = (Endereco) entidade;
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ENDERECOS SET ");
            sql.append("LOGRADOURO = ?, ");
            sql.append("NUMERO = ?, ");
            sql.append("BAIRRO = ?, ");
            sql.append("CIDADE = ?, ");
            sql.append(" ESTADO = ?, ");
            sql.append("CEP = ? , ");
            sql.append("COMPLEMENTO = ? ");
            sql.append("WHERE ID = ?");  //criando sql para insert no banco

            pst = conexao.prepareStatement(sql.toString());//criando caminho para conexao no banco de dados
            //setando parametros do insert
            pst.setString(1, end.getLogradouro());
            pst.setString(2, end.getNumero());
            pst.setString(3, end.getBairro());
            pst.setString(4, end.getCidade());
            pst.setString(5, end.getEstado());
            pst.setString(6, end.getCep());
            pst.setString(7, end.getComplemento());
            pst.setInt(8, end.getId());

            pst.executeUpdate();//executando a query no banco de dados

            // O Commite é feito por outra Classe DAO (Cliente ou PrestadorServico)
        } catch (SQLException ex)
        {
            try
            {
                conexao.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
            throw new SQLException();
        }
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        openConnection();//Abrir conexão com banco
        Endereco end = (Endereco) entidade;
        PreparedStatement preparador;
        String sql = "DELETE FROM ENDERECOS WHERE ID = ?";
        try
        {
            conexao.setAutoCommit(false);
            preparador = conexao.prepareStatement(sql);
            preparador.setInt(1, end.getId());
            preparador.executeUpdate();
            conexao.commit();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException();
        } finally
        {
            try
            {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            Endereco end = (Endereco) entidade;
            if(conexao == null || conexao.isClosed())
                openConnection();//Abrir conexão com banco
            
            String sql = "SELECT * FROM ENDERECOS WHERE ID = ?";
            
            pst = conexao.prepareStatement(sql);
            
            pst.setInt(1, end.getId());
            
            ResultSet resultado = pst.executeQuery();

            if (resultado.next())
            {
                end.setBairro(resultado.getString("bairro"));
                end.setCep(resultado.getString("cep"));
                end.setCidade(resultado.getString("cidade"));
                end.setComplemento(resultado.getString("complemento"));
                end.setEstado(resultado.getString("estado"));
                end.setLogradouro(resultado.getString("logradouro"));
                end.setNumero(resultado.getString("numero"));
                end.setId(resultado.getInt("id"));
                return end;
            }
            
            return null;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex);
        } 
    }
}
