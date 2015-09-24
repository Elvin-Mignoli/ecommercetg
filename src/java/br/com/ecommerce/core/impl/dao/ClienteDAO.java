/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.domain.CaixaEntrada;
import br.com.ecommerce.domain.CartaoCredito;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class ClienteDAO extends AbstractDAO
{

    //atributo para conter os parametros de uma query!
    private List<String> parameters = new ArrayList<>();    //parametros para busca dos clientes

    /**
     * Cria um objeto de ClienteDAO e anexa uma conexao já aberta!
     *
     * @param conexao Connection já aberta por outro DAO
     */
    public ClienteDAO(Connection conexao)
    {
        super(conexao);
    }

    //construtor default!
    public ClienteDAO()
    {
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            Cliente cliente = (Cliente) entidade;
            
            openConnection(); //Abrir conexão com banco
            
            conexao.setAutoCommit(false);
            
            //salvando dados do Endereco!
            IDAO dao = new EnderecoDAO(conexao);
            
            dao.salvar(cliente.getEndereco());
            
            //salvando dados do Cartão
            dao = new CartaoCreditoDAO(conexao);
            
            dao.salvar(cliente.getCartao());
            
            //criando sql para insert no banco
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO CLIENTES ");
            sql.append("(NOME,SOBRENOME,CPF,ID_ENDERECO,ID_CARTAO) ");
            sql.append("VALUES(?,?,?,?,?)");
            
            //criando caminho para conexao no banco de dados e retornando o ID do ultimo insert!
            pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            //setando parametros do insert
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getSobrenome());
            pst.setString(3, cliente.getCpf());
            pst.setInt(4, cliente.getEndereco().getId());
            pst.setInt(5, cliente.getCartao().getId());
            
            pst.executeUpdate();
            
            ResultSet resultado = pst.getGeneratedKeys(); //pegando id da ultima insercao no banco

            if (resultado.next())            //se conseguir interar pelo menos 1 vez
            {//conseguiu iterar
                entidade.setId(resultado.getInt("id"));
            }
            
            //salvando dados de Login!
            dao = new AutenticarDAO(conexao);
            
            dao.salvar(entidade);
            
            //criando caixa de entrada para o cliente!
            dao = new CaixaEntradaDAO(conexao);
            
            dao.salvar(new CaixaEntrada(cliente, null));
            
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
                conexao.close();
            } catch (SQLException e)
            {
                throw new SQLException();
            }
        }
    }

    /**
     * Atualiza todas as informações de uma entidade Cliente!
     *
     * @param entidade Cliente com seus dados preenchidos!
     * @throws SQLException Caso ocorra algum problema ao atualizar
     */
    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            Cliente cliente = (Cliente) entidade;

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CLIENTES SET ");
            sql.append("NOME = ?, ");
            sql.append("SOBRENOME = ?, ");
            sql.append("DATA_NASCIMENTO = ?, ");
            sql.append("TELEFONE = ?, ");
            sql.append("CELULAR = ? , ");
            sql.append("SEXO = ? ");
            sql.append("WHERE ID = ?");  //criando sql para insert no banco

            openConnection();//Abrir conexão com banco

            conexao.setAutoCommit(false);//setando auto commit para false

            IDAO dao = new EnderecoDAO(conexao);

            dao.atualizar(cliente.getEndereco());   //atualizando dados do endereco!

            pst = conexao.prepareStatement(sql.toString());//criando caminho para conexao no banco de dados

            //setando parametros do insert
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getSobrenome());
            if(cliente.getDataNascimento() != null)
                pst.setDate(3, new Date(cliente.getDataNascimento().getTime()));
            else
                pst.setDate(3, null);
            
            pst.setString(4, cliente.getContato().getTelefone());
            pst.setString(5, cliente.getContato().getCelular());
            pst.setString(6, cliente.getSexo());
            pst.setInt(7, cliente.getId());

            pst.executeUpdate();//executando a query no banco de dados

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

    /**
     * Método não foi implementado, pois a exclusão é feita de forma lógica!
     *
     * @param entidade
     * @throws SQLException
     */
    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        /*
         A exclusão de um cliente é feita de forma lógica!
         Não é necessário efetuar a exclusão por aqui!
         */
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            openConnection();//Abrir conexão com banco
            List<EntidadeDominio> lista = new ArrayList<>();

            Cliente cliente = (Cliente) entidade;

            StringBuilder sql = createQuery(entidade);  //criando a query para consulta!

            pst = conexao.prepareStatement(sql.toString());

            int x = 0;

            if (parameters.isEmpty())
            {
                sql.append("SELECT * FROM CLIENTES");
            } else
            {
                for (String p : parameters)
                {
                    pst.setString(x++, p);
                }
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                //pegar o id do banco 
                cliente.setId(rs.getInt("id"));

                //pegar os dados do contato
                Contato contato = new Contato(rs.getString("telefone"), rs.getString("celular"));
                cliente.setContato(contato);

                //pegando dados pessoais do cliente!
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNascimento(rs.getDate("data_nascimento"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                
                //pegando dados do cartao
                cliente.setCartao(new CartaoCredito());
                cliente.getCartao().setId(rs.getInt("id_cartao"));
                
                
                //pegando dados do endereco!
                cliente.setEndereco(new Endereco());
                IDAO dao = new EnderecoDAO(); // --> Criando instancia do DAO de Endereco!
                cliente.getEndereco().setId(rs.getInt("id_endereco"));
                dao.consultarUm(cliente.getEndereco());

                lista.add(cliente);
            }
            return lista;
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
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            openConnection();//Abrir conexão com banco
            Cliente cliente = (Cliente) entidade;

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT * FROM CLIENTES WHERE ID = ?");

            pst = conexao.prepareStatement(sql.toString());

            pst.setInt(1, cliente.getId());

            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                //pegar os dados de  endereço
                EnderecoDAO endDao = new EnderecoDAO();
                Endereco end = new Endereco();
                end.setId(rs.getInt("id_endereco"));
                cliente.setEndereco((Endereco) endDao.consultarUm(end));
                //pegar o id do banco 
                cliente.setId(rs.getInt("id"));

                //pegar os dados do contato
                Contato contato = new Contato(rs.getString("telefone"), rs.getString("celular"));
                cliente.setContato(contato);
                //
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNascimento(rs.getDate("data_nascimento"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                
                //pegando dados do cartao
                cliente.setCartao(new CartaoCredito());
                cliente.getCartao().setId(rs.getInt("id_cartao"));
                CartaoCreditoDAO dao = new CartaoCreditoDAO(conexao);
                
                dao.consultarUm(cliente.getCartao());
                
                //recuperar a caixa de caixa
                CaixaEntradaDAO entradaDAO = new CaixaEntradaDAO(conexao);
                CaixaEntrada caixa = new CaixaEntrada(cliente, null);
                caixa = (CaixaEntrada)entradaDAO.consultarUm(caixa);
                cliente.setEntrada(caixa);
                
                return cliente;
            }
            return null;
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

    /**
     * Método para criar uma query específica de uma consulta!
     *
     * @param entidade
     * @return Uma string contendo a query completa
     */
    public StringBuilder createQuery(EntidadeDominio entidade)
    {
        Cliente cliente = (Cliente) entidade;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM CLIENTES WHERE ");

        /*
         Bloco de IFs para definir os parâmetros de pesquisa!
         */
        if (cliente.getNome() != null || !cliente.getNome().equals(""))
        {
            sql.append("NOME ILIKE ? AND ");
            parameters.add(cliente.getNome());
        } else if (cliente.getSobrenome() != null || !cliente.getSobrenome().equals(""))
        {
            sql.append("SOBRENOME ILIKE ? AND ");
            parameters.add(cliente.getSobrenome());
        } else if (cliente.getCpf() != null || !cliente.getCpf().equals(""))
        {
            sql.append("CPF ILIKE ? AND ");
            parameters.add(cliente.getCpf());
        }

        sql.delete(sql.length() - 5, sql.length());

        return sql;
    }
    
    /**
     * Busca um Cliente no banco de dados pelo CPF!
     * @param entidade
     * @return Se o cliente existe retorna um objeto de Cliente, caso contrário retorna null
     * @throws SQLException lança uma exceção caso de alguma coisa errada na pesquisa!
     */
    public EntidadeDominio consultaClienteCPF(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            Cliente cliente = (Cliente) entidade;
            
            openConnection();
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT NOME FROM CLIENTES ");
            sql.append("WHERE CPF = ? ");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, cliente.getCpf());
                        
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())   //retornou algum cliente?
            {
                return cliente;
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
                conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
