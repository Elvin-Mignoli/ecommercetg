/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.domain.CaixaEntrada;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import java.sql.Connection;
import java.sql.Date;
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

    PrestadorServicoDAO(Connection conexao)
    {
        super(conexao);
    }

    public PrestadorServicoDAO()
    {
        
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        
        try
        {
            PrestadorServico prestador= (PrestadorServico) entidade;
            
            if(conexao == null || conexao.isClosed())
                openConnection(); //Abrir conexão com banco
            
            conexao.setAutoCommit(false);
            
            //salvando dados do Endereco!
            IDAO dao = new EnderecoDAO(conexao);
            
            dao.salvar(prestador.getEndereco());
            
            //criando sql para insert no banco
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO PRESTADOR_SERVICOS ");
            sql.append("(NOME,SOBRENOME,CPF,CNPJ,ID_ENDERECO) ");
            sql.append("VALUES(?,?,?,?,?)");
            
            //criando caminho para conexao no banco de dados e retornando o ID do ultimo insert!
            pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            //setando parametros do insert
            pst.setString(1, prestador.getNome());
            pst.setString(2, prestador.getSobrenome());
            pst.setString(3, prestador.getCpf());
            pst.setString(4, prestador.getCnpj());
            pst.setInt(5, prestador.getEndereco().getId());
            
            pst.executeUpdate();
            
            ResultSet resultado = pst.getGeneratedKeys(); //pegando id da ultima insercao no banco

            if (resultado.next())            //se conseguir interar pelo menos 1 vez
            {//conseguiu iterar
                entidade.setId(resultado.getInt("id"));
            }
            
            //salvando dados de Login!
            dao = new AutenticarDAO(conexao);
            
            dao.salvar(entidade);         
            
            //salvando dados da caixa de caixa
            dao = new CaixaEntradaDAO(conexao);
            
            dao.salvar(new CaixaEntrada(null, prestador));

            conexao.commit();   //commitando as alteracoes feitas no banco!

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
            throw new SQLException(ex);
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
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        
        try
        {  
            PrestadorServico prestador = (PrestadorServico) entidade;
            
            StringBuilder sql = new StringBuilder();
                sql.append("UPDATE PRESTADOR_SERVICOS SET ");
                sql.append("NOME = ?, ");
                sql.append("SOBRENOME = ?, ");
                sql.append("DATA_NASCIMENTO = ?, ");
                sql.append("TELEFONE = ?, ");
                sql.append("CELULAR = ? , ");
                sql.append("SEXO = ? ");
                sql.append("WHERE ID = ?");  //criando sql para insert no banco
                
                if(conexao == null || conexao.isClosed())
                {
                    openConnection();
                }//Abrir conexão com banco
                
                conexao.setAutoCommit(false);//setando auto commit para false
                
                IDAO dao = new EnderecoDAO(conexao);

                dao.atualizar(prestador.getEndereco());   //atualizando dados do endereco!
                
                pst = conexao.prepareStatement(sql.toString());//criando caminho para conexao no banco de dados
                //setando parametros do insert
                pst.setString(1, prestador.getNome());
                pst.setString(2, prestador.getSobrenome());
                if(prestador.getDataNascimento()!= null)
                    pst.setDate(3, new Date(prestador.getDataNascimento().getTime()));
                else
                    pst.setDate(3, null);
                pst.setString(4, prestador.getContato().getTelefone());
                pst.setString(5, prestador.getContato().getCelular());
                pst.setString(6, prestador.getSexo());
                pst.setInt(7, prestador.getId());
                pst.executeUpdate();//executando a query no banco de dados
                
                if(prestador.getHabilidades()!=null) //a lista de skills está vazia?
                {//cheia
                    //Atualizar competencias
                    CompetenciaDAO competenciaDAO = new CompetenciaDAO(conexao);
                    PrestadorServico prestadorComp = new PrestadorServico();
                    prestadorComp.setId(prestador.getId());
                    //buscar as competencias do prestador
                    prestadorComp = (PrestadorServico)competenciaDAO.consultarUm(prestadorComp);
                    //excluir competencias somente do prestador e depois salvar as novas
                    if(prestadorComp!= null)
                        competenciaDAO.excluir(prestadorComp);
                    competenciaDAO.salvar(prestador);
                }
                conexao.commit();//confirmando alteracoes no banco
            } catch (SQLException ex)
            {
                conexao.rollback();
                ex.printStackTrace();
                throw new SQLException();
            } finally
            {
                try
                {
                    if(transaction)
                        conexao.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                    throw new SQLException();
                }
            }
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        openConnection();//Abrir conexão com banco
        PrestadorServico prestador = (PrestadorServico) entidade;
        PreparedStatement preparador;
        //excluir as competencias vinculadas com prestador
        CompetenciaDAO compDAO = new CompetenciaDAO();
        compDAO.excluir(prestador);
        //excluir login
        AutenticarDAO autenticar = new AutenticarDAO();
        autenticar.excluir(prestador);
        //excluir prestador
        String sql = "DELETE FROM PRESTADOR_SERVICOS WHERE ID = ?";
        try
        {
            conexao.setAutoCommit(false);
            preparador = conexao.prepareStatement(sql);
            preparador.setInt(1, prestador.getId());
            preparador.executeUpdate();
            conexao.commit();
            //excluir endereço
            EnderecoDAO endDAO = new EnderecoDAO();
            endDAO.excluir(prestador.getEndereco());
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
        if(conexao == null || conexao.isClosed())
            openConnection();//Abrir conexão com banco
        
        ArrayList<EntidadeDominio> lista = new ArrayList<>();
        PreparedStatement preparador;
        String sql = "SELECT PRESTADOR_SERVICOS.* FROM PRESTADOR_SERVICOS,ENDERECOS WHERE ID_ENDERECO = ENDERECOS.ID ";
        try
        {
            conexao.setAutoCommit(false);
            preparador = conexao.prepareStatement(sql);
            ResultSet resultado = preparador.executeQuery();
            resultado.next();
            conexao.commit();
            if (resultado.getRow() == 0)
            {
                return null;
            } else
            {
                do
                {
                    PrestadorServico prestador = new PrestadorServico();
                    EnderecoDAO endDao = new EnderecoDAO(conexao);
                    Endereco end = new Endereco();
                    end.setId(resultado.getInt("id_endereco"));
                    prestador.setEndereco((Endereco) endDao.consultarUm(end));
                    //pegar o id do banco 
                    prestador.setId(resultado.getInt("id"));
                    //pegar os dados do login
                    AutenticarDAO autenticar = new AutenticarDAO(conexao);
                    autenticar.consultarUm(prestador);
                    //pegar os dados do contato
                    Contato contato = new Contato(resultado.getString("telefone"), resultado.getString("celular"));
                    prestador.setContato(contato);
                    //
                    prestador.setNome(resultado.getString("nome"));
                    prestador.setCnpj(resultado.getString("cpf"));
                    prestador.setDataNascimento(resultado.getDate("data_nascimento"));
                    prestador.setSexo(resultado.getString("sexo"));
                    prestador.setSobrenome(resultado.getString("sobrenome"));
                    //recuperar as competencias
                    CompetenciaDAO compDAO = new CompetenciaDAO(conexao);
                    compDAO.consultarUm(prestador);
                    lista.add(prestador);

                } while (resultado.next());
                return lista;
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException();
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
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        if(conexao == null || conexao.isClosed())
            openConnection();//Abrir conexão com banco
        
        PrestadorServico prestador = (PrestadorServico) entidade;
        PreparedStatement preparador;
        String sql = "SELECT PRESTADOR_SERVICOS.* FROM PRESTADOR_SERVICOS WHERE PRESTADOR_SERVICOS.ID = ?";
        try
        {
            //conexao.setAutoCommit(false);
            preparador = conexao.prepareStatement(sql);
            preparador.setInt(1, prestador.getId());
            ResultSet resultado = preparador.executeQuery();
            resultado.next();
            //conexao.commit(); nao precisa de commit para fazer uma consulta
            if (resultado.getRow() == 0)
            {
                return null;
            }
            else
            {
                //pegar os dados de  endereço
                EnderecoDAO endDao = new EnderecoDAO(conexao);
                Endereco end = new Endereco();
                end.setId(resultado.getInt("id_endereco"));
                prestador.setEndereco((Endereco) endDao.consultarUm(end));
                //pegar o id do banco 
                prestador.setId(resultado.getInt("id"));
               
                //pegar os dados do contato
                Contato contato = new Contato(resultado.getString("telefone"),resultado.getString("celular"));
                prestador.setContato(contato);
                //
                prestador.setNome(resultado.getString("nome"));
                prestador.setCpf(resultado.getString("cpf"));
                prestador.setCnpj(resultado.getString("cnpj"));
                prestador.setDataNascimento(resultado.getDate("data_nascimento"));
                prestador.setSexo(resultado.getString("sexo"));
                prestador.setSobrenome(resultado.getString("sobrenome"));
                //recuperar as competencias
                CompetenciaDAO compDAO = new CompetenciaDAO(conexao);
                compDAO.consultarUm(prestador);
                
                //recuperar a caixa de caixa
                CaixaEntradaDAO entradaDAO = new CaixaEntradaDAO(conexao);
                CaixaEntrada caixa = new CaixaEntrada(null, prestador);
                caixa = (CaixaEntrada)entradaDAO.consultarUm(caixa);
                prestador.setEntrada(caixa);
                return prestador;
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException();
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
    //Método para identificar se já existe um prestador com um determinado CPF
    public EntidadeDominio consultaPrestadorCPF(EntidadeDominio entidade) throws SQLException
    {
        try
        {
           PrestadorServico prestador = (PrestadorServico) entidade;
            
           if(conexao == null || conexao.isClosed()) 
              openConnection();
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT NOME FROM PRESTADOR_SERVICOS ");
            sql.append("WHERE CPF = ? ");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, prestador.getCpf());
                        
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())   //retornou algum cliente?
            {
                return prestador;
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
                if(transaction)
                    conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    //Método para identificar se já existe um prestador com um determinado CNPJ
    public EntidadeDominio consultaPrestadorCNPJ(EntidadeDominio entidade) throws SQLException
    {
        try
        {
           PrestadorServico prestador = (PrestadorServico) entidade;
            
           if(conexao == null || conexao.isClosed()) 
              openConnection();
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT NOME FROM PRESTADOR_SERVICOS ");
            sql.append("WHERE CNPJ = ? ");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, prestador.getCnpj());
                        
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())   //retornou algum cliente?
            {
                return prestador;
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
