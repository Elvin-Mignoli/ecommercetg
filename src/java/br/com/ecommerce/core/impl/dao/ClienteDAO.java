/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Elvin
 */
public class ClienteDAO extends AbstractDAO
{

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
       openConnection(); //Abrir conexão com banco
        Cliente cliente = (Cliente) entidade;
        PreparedStatement preparador;
        //criando sql para insert no banco
        String sql = "INSERT INTO CLIENTES(NOME,SOBRENOME,DATA_NASCIMENTO,CPF,TELEFONE,CELULAR,SEXO,ID_ENDERECO) VALUES(?,?,?,?,?,?,?,?)";
        
        //pegando o id do endereço
        EnderecoDAO endDAO = new EnderecoDAO();
        endDAO.salvar(cliente.getEndereco());
        
        try
        {
            conexao.setAutoCommit(false);//setando auto commit para false
            preparador = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//criando caminho para conexao no banco de dados
            //setando parametros do insert
            preparador.setString(1, cliente.getNome());
            preparador.setString(2, cliente.getSobrenome());
            java.sql.Date sqlData = new java.sql.Date(cliente.getDataNascimento().getTime());  //atribuir a data de nascimento para um objeto do tipo sql Date
            preparador.setDate(3, sqlData);
            preparador.setString(4, cliente.getCpf());
            preparador.setString(5, cliente.getContato().getTelefone());
            preparador.setString(6, cliente.getContato().getCelular());
            preparador.setString(7, cliente.getSexo());
            preparador.setInt(8, cliente.getEndereco().getId());
            preparador.executeUpdate();//executando a query no banco de dados
            ResultSet resultado = preparador.getGeneratedKeys(); //pegando id da ultima insercao no banco
            if (resultado.next())            //se conseguir interar pelo menos 1 vez
            {//conseguiu iterar
                entidade.setId(resultado.getInt(1));
            }
            conexao.commit();//confirmando alteracoes no banco
            Autenticar autenticar = new Autenticar();
            autenticar.salvar(cliente);
        } catch (SQLException ex)
        { 
            endDAO.excluir(cliente.getEndereco());
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
    public void atualizar(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {
         openConnection();//Abrir conexão com banco
        Cliente cliente = (Cliente)entidade;
        PreparedStatement preparador;
        String sql = "DELETE FROM CLIENTES WHERE ID = ?";
        try{
        conexao.setAutoCommit(false);
        preparador = conexao.prepareStatement(sql);
        preparador.setInt(1,cliente.getId());
        preparador.executeUpdate();
        conexao.commit();
        EnderecoDAO endDAO = new EnderecoDAO();
        endDAO.excluir(cliente.getEndereco());
        Autenticar autenticar = new Autenticar();
        autenticar.excluir(cliente);
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
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        openConnection();//Abrir conexão com banco
        ArrayList<EntidadeDominio> lista = new ArrayList<>();
        PreparedStatement preparador;
        String sql = "SELECT * FROM CLIENTES ";
        try{
        conexao.setAutoCommit(false);
        preparador = conexao.prepareStatement(sql);
        ResultSet  resultado = preparador.executeQuery();
        resultado.next();
        conexao.commit();
        if(resultado.getRow()== 0)
        {
            return null;
        }else
        {
           do
            {
                Cliente cliente = new Cliente();
                EnderecoDAO endDao = new EnderecoDAO();
                Endereco end = new Endereco();
                end.setId(resultado.getInt("id_endereco"));
                cliente.setEndereco((Endereco) endDao.consultarUm(end));
                //pegar o id do banco 
                cliente.setId(resultado.getInt("id"));
                //pegar os dados do login
                Autenticar autenticar = new Autenticar();
                autenticar.consultarUm(cliente);
                //pegar os dados do contato
                Contato contato = new Contato(resultado.getString("telefone"),resultado.getString("celular"));  
                cliente.setContato(contato);
                //
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setDataNascimento(resultado.getDate("data_nascimento"));
                cliente.setSexo(resultado.getString("sexo"));
                cliente.setSobrenome(resultado.getString("sobrenome"));
                //falta dao de autenticação
                lista.add(cliente);
                
            }while(resultado.next());
            return lista;
        }
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
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException {
        openConnection();//Abrir conexão com banco
        Cliente cliente = (Cliente)entidade;
        PreparedStatement preparador;
        String sql = "SELECT * FROM CLIENTES WHERE ID = ?";
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
            //pegar os dados de  endereço
            EnderecoDAO endDao = new EnderecoDAO();
            Endereco end = new Endereco();
            end.setId(resultado.getInt("id_endereco"));
            cliente.setEndereco((Endereco) endDao.consultarUm(end));
            //pegar o id do banco 
            cliente.setId(resultado.getInt("id"));
            //pegar os dados do login
            Autenticar autenticar = new Autenticar();
            autenticar.consultarUm(cliente);
            //pegar os dados do contato
            Contato contato = new Contato(resultado.getString("telefone"),resultado.getString("celular"));  
            cliente.setContato(contato);
            //
            cliente.setNome(resultado.getString("nome"));
            cliente.setCpf(resultado.getString("cpf"));
            cliente.setDataNascimento(resultado.getDate("data_nascimento"));
            cliente.setSexo(resultado.getString("sexo"));
            cliente.setSobrenome(resultado.getString("sobrenome"));
            return cliente;
        }
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
    }

   
}
