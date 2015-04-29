/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class Autenticar extends AbstractDAO
{

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        openConnection(); //Abrir conexão com banco
        Cliente cliente = (Cliente) entidade;
        PreparedStatement preparador;
        //criando sql para insert no banco
        String sql = "INSERT INTO LOGIN (EMAIL,SENHA,STATUS,ID_CLIENTE,TIPO_CONTA) VALUES(?,?,?,?,?);";

        try
        {
            conexao.setAutoCommit(false);//setando auto commit para false
            preparador = conexao.prepareStatement(sql);
            //setando parametros do insert
            preparador.setString(1, cliente.getEmail());
            preparador.setString(2, cliente.getSenha());
            preparador.setInt(3, cliente.getStatus());
            preparador.setInt(4, cliente.getId());
            preparador.setString(5, cliente.getTipoConta());
            preparador.executeUpdate();//executando a query no banco de dados
            conexao.commit();//confirmando alteracoes no banco
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
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        openConnection();//Abrir conexão com banco 
        PreparedStatement preparador;
        String sql = null;
        if(entidade instanceof Cliente)
        {
            Cliente cliente = (Cliente) entidade;
            sql = "DELETE FROM LOGIN WHERE ID_CLIENTE = ?";
             try{
            conexao.setAutoCommit(false);
            preparador = conexao.prepareStatement(sql);
            preparador.setInt(1,cliente.getId());
            preparador.executeUpdate();
            conexao.commit();
            }catch (SQLException ex)
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
                }//catch
            }//finally
        }//if
        else if(entidade instanceof PrestadorServico)
        {
            PrestadorServico prestador = (PrestadorServico) entidade;
            sql = "DELETE FROM LOGIN WHERE ID_PRESTADOR = ?";
            try{
            conexao.setAutoCommit(false);
            preparador = conexao.prepareStatement(sql);
            preparador.setInt(1,prestador.getId());
            preparador.executeUpdate();
            conexao.commit();
            }catch (SQLException ex)
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
        }//else if
        
        
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        openConnection();//Abrir conexão com banco
        PreparedStatement preparador;
        String sql = null;
        if(entidade instanceof Cliente)
        {
            Cliente cliente = (Cliente) entidade;
            sql = "SELECT * FROM LOGIN WHERE ID_CLIENTE = ?";
            try{
            conexao.setAutoCommit(false);
            preparador = conexao.prepareStatement(sql);
            preparador.setInt(1,cliente.getId());
            ResultSet  resultado = preparador.executeQuery();
            resultado.next();
            conexao.commit();
            if(resultado.getRow()== 0)
            {
                return null;
            }else
            {
                cliente.setEmail(resultado.getString("email"));
                cliente.setSenha(resultado.getString("senha"));
                cliente.setStatus(resultado.getInt("status"));
                cliente.setTipoConta(resultado.getString("tipo_conta"));
                cliente.setId(resultado.getInt("id_cliente"));
               
                return cliente;
            }//else
            }catch (SQLException ex)
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
                }//catch
            }//finally
        }//if
        else if(entidade instanceof PrestadorServico)
        {
            sql = "SELECT * FROM LOGIN WHERE ID_PRESTADOR = ?";
            PrestadorServico prestador = (PrestadorServico) entidade;
            try{
            conexao.setAutoCommit(false);
            preparador = conexao.prepareStatement(sql);
            preparador.setInt(1,prestador.getId());
            ResultSet  resultado = preparador.executeQuery();
            resultado.next();
            conexao.commit();
            if(resultado.getRow()== 0)
            {
                return null;
            }else
            {
                prestador.setEmail(resultado.getString("email"));
                prestador.setSenha(resultado.getString("senha"));
                prestador.setStatus(resultado.getInt("status"));
                prestador.setTipoConta(resultado.getString("tipo_conta"));
                prestador.setId(resultado.getInt("id_cliente"));
               
                return prestador;
            }//else
            }catch (SQLException ex)
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
                }//catch
            }//finnally
        }//else if
        throw new SQLException();
    }
}
