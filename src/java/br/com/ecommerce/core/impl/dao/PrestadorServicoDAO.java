/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Competencia;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class PrestadorServicoDAO extends AbstractDAO
{

     @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
       openConnection(); //Abrir conexão com banco
        PrestadorServico prestador = (PrestadorServico) entidade;
        PreparedStatement preparador;
        //criando sql para insert no banco
        String sql = "INSERT INTO PRESTADOR_SERVICOS(NOME,SOBRENOME,DATA_NASCIMENTO,CPF,TELEFONE,CELULAR,SEXO,ID_ENDERECO) VALUES(?,?,?,?,?,?,?,?)";
        
        //pegando o id do endereço e salvando endereco
        EnderecoDAO endDAO = new EnderecoDAO();
        endDAO.salvar(prestador.getEndereco());
        
        try
        {
            conexao.setAutoCommit(false);//setando auto commit para false
            preparador = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//criando caminho para conexao no banco de dados
            //setando parametros do insert
            preparador.setString(1, prestador.getNome());
            preparador.setString(2, prestador.getSobrenome());
            java.sql.Date sqlData = new java.sql.Date(prestador.getDataNascimento().getTime());  //atribuir a data de nascimento para um objeto do tipo sql Date
            preparador.setDate(3, sqlData);
            preparador.setString(4, prestador.getCpf());
            preparador.setString(5, prestador.getContato().getTelefone());
            preparador.setString(6, prestador.getContato().getCelular());
            preparador.setString(7, prestador.getSexo());
            preparador.setInt(8, prestador.getEndereco().getId());
            preparador.executeUpdate();//executando a query no banco de dados
            ResultSet resultado = preparador.getGeneratedKeys(); //pegando id da ultima insercao no banco
            if (resultado.next())            //se conseguir interar pelo menos 1 vez
            {//conseguiu iterar
                entidade.setId(resultado.getInt(1));
            }
            conexao.commit();//confirmando alteracoes no banco
            //insercao dos dados de login
            AutenticarDAO autenticar = new AutenticarDAO();
            autenticar.salvar(prestador);
            //inserção das competencias
            CompetenciaDAO compDAO= new CompetenciaDAO();
            compDAO.salvar(prestador);
            
        } catch (SQLException ex)
        { 
            endDAO.excluir(prestador.getEndereco());
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
        openConnection();//Abrir conexão com banco
        PrestadorServico prestador = (PrestadorServico) entidade;
        PreparedStatement preparador;
        String sql = "UPDATE PRESTADOR_SERVICOS SET NOME = ?, SOBRENOME = ?, DATA_NASCIMENTO = ?, "
                + " TELEFONE = ?,  CELULAR = ? ,  SEXO = ? WHERE ID = ?";  //criando sql para insert no banco
        try
        {
            conexao.setAutoCommit(false);//setando auto commit para false
            preparador = conexao.prepareStatement(sql);//criando caminho para conexao no banco de dados
            //setando parametros do insert
            preparador.setString(1, prestador.getNome());
            preparador.setString(2, prestador.getSobrenome());
            java.sql.Date sqlData = new java.sql.Date(prestador.getDataNascimento().getTime());  //atribuir a data de nascimento para um objeto do tipo sql Date
            preparador.setDate(3, sqlData);
            preparador.setString(4, prestador.getContato().getTelefone());
            preparador.setString(5, prestador.getContato().getCelular());
            preparador.setString(6,prestador.getSexo());
            preparador.setInt(7, prestador.getId());
            preparador.executeUpdate();//executando a query no banco de dados
            conexao.commit();//confirmando alteracoes no banco
            //Atualizar o endereço
            EnderecoDAO endDAO = new EnderecoDAO();
            endDAO.atualizar(prestador.getEndereco());
            //Atualizar Login
            AutenticarDAO login = new AutenticarDAO();
            login.atualizar(prestador);
            //Atualizar competencias
            CompetenciaDAO compDAO = new CompetenciaDAO();
            compDAO.atualizar(prestador);
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
    public void excluir(EntidadeDominio entidade) throws SQLException {
         openConnection();//Abrir conexão com banco
        PrestadorServico prestador = (PrestadorServico)entidade;
        PreparedStatement preparador;
        //excluir as competencias vinculadas com prestador
        CompetenciaDAO compDAO = new CompetenciaDAO();
        compDAO.excluir(prestador);
        //excluir login
        AutenticarDAO autenticar = new AutenticarDAO();
        autenticar.excluir(prestador);
        //excluir prestador
        String sql = "DELETE FROM PRESTADOR_SERVICOS WHERE ID = ?";
        try{
        conexao.setAutoCommit(false);
        preparador = conexao.prepareStatement(sql);
        preparador.setInt(1,prestador.getId());
        preparador.executeUpdate();
        conexao.commit();
        //excluir endereço
        EnderecoDAO endDAO = new EnderecoDAO();
        endDAO.excluir(prestador.getEndereco());
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
        String sql = "SELECT PRESTADOR_SERVICOS.* FROM PRESTADOR_SERVICOS,ENDERECOS WHERE ID_ENDERECO = ENDERECOS.ID ";
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
                PrestadorServico prestador = new PrestadorServico();
                EnderecoDAO endDao = new EnderecoDAO();
                Endereco end = new Endereco();
                end.setId(resultado.getInt("id_endereco"));
                prestador.setEndereco((Endereco) endDao.consultarUm(end));
                //pegar o id do banco 
                prestador.setId(resultado.getInt("id"));
                //pegar os dados do login
                AutenticarDAO autenticar = new AutenticarDAO();
                autenticar.consultarUm(prestador);
                //pegar os dados do contato
                Contato contato = new Contato(resultado.getString("telefone"),resultado.getString("celular"));  
                prestador.setContato(contato);
                //
                prestador.setNome(resultado.getString("nome"));
                prestador.setCpf(resultado.getString("cpf"));
                prestador.setDataNascimento(resultado.getDate("data_nascimento"));
                prestador.setSexo(resultado.getString("sexo"));
                prestador.setSobrenome(resultado.getString("sobrenome"));
                //recuperar as competencias
                CompetenciaDAO compDAO = new CompetenciaDAO();
                compDAO.consultarUm(prestador);
                lista.add(prestador);
                
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
        PrestadorServico prestador = (PrestadorServico)entidade;
        PreparedStatement preparador;
        String sql = "SELECT PRESTADOR_SERVICOS.* FROM PRESTADOR_SERVICOS,ENDERECOS WHERE PRESTADOR_SERVICOS.ID = ? AND PRESTADOR_SERVICOS.ID_ENDERECO = ENDERECOS.ID ";
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
            //pegar os dados de  endereço
            EnderecoDAO endDao = new EnderecoDAO();
            Endereco end = new Endereco();
            end.setId(resultado.getInt("id_endereco"));
            prestador.setEndereco((Endereco) endDao.consultarUm(end));
            //pegar o id do banco 
            prestador.setId(resultado.getInt("id"));
            //pegar os dados do login
            AutenticarDAO autenticar = new AutenticarDAO();
            autenticar.consultarUm(prestador);
            //pegar os dados do contato
            Contato contato = new Contato(resultado.getString("telefone"),resultado.getString("celular"));  
            prestador.setContato(contato);
            //
            prestador.setNome(resultado.getString("nome"));
            prestador.setCpf(resultado.getString("cpf"));
            prestador.setDataNascimento(resultado.getDate("data_nascimento"));
            prestador.setSexo(resultado.getString("sexo"));
            prestador.setSobrenome(resultado.getString("sobrenome"));
            //recuperar as competencias
            CompetenciaDAO compDAO = new CompetenciaDAO();
            compDAO.consultarUm(prestador);
            return prestador;
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
