/*
 * Esse Código foi escrito por mim, enquanto estava bebendo cerveja!
 * Altere qualquer linha de código sóbrio! Please! ;)
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Elvin
 */
public class AutenticarDAO extends AbstractDAO
{

    public AutenticarDAO()
    {
    }

    public AutenticarDAO(Connection conexao)
    {
        super(conexao);
    }

    /**
     * Salva uma entidade Usuario no banco de dados! OBS: É preciso ter os dados
     * de uma entidade Cliente para persistir um Usuário!
     *
     * @param entidade - EntidadeDominio (Usuário) - Contendo os dados de um
     * usuário e um cliente
     * @throws SQLException Lança uma exception caso algum erro ocorra ao
     * persistir a entidade!
     */
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            Usuario usuario = (Usuario) entidade;

            if (conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);
            } //Abrir conexão com banco

            //criando sql para insert no banco
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO LOGIN ");
            sql.append("(EMAIL,SENHA,STATUS,ID_CLIENTE,TIPO_CONTA) ");
            sql.append("VALUES(?,?,?,?,?)");

            //conexao.setAutoCommit(false);//setando auto commit para false
            pst = conexao.prepareStatement(sql.toString());

            //setando parametros do insert
            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getSenha());
            pst.setInt(3, usuario.getStatus());
            pst.setInt(4, entidade.getId());
            pst.setString(5, usuario.getTipoConta());

            pst.execute();//executando a query no banco de dados

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    /**
     * Atualiza um Usuário no banco de dados!
     *
     * @param entidade Entidade Usuário contendo os dados de um usuário
     * @throws SQLException Lança uma SQLException caso não consiga atualizar um
     * usuário!
     */
    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        Usuario usuario = (Usuario) entidade;
        try
        {
            openConnection();// --> Abrir conexão com banco

            conexao.setAutoCommit(false);//setando auto commit para false

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE LOGIN SET");
            sql.append("EMAIL = ?, ");
            sql.append("SENHA = ?,");
            sql.append("STATUS = ? ");
            sql.append("WHERE ID_CLIENTE = ?");  //criando sql para update no banco

            pst = conexao.prepareStatement(sql.toString());//criando caminho para conexao no banco de dados

            //setando parametros do insert
            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getSenha());
            pst.setInt(3, usuario.getStatus());
            pst.setInt(4, usuario.getId());

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

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        Usuario usuario = (Usuario) entidade;
        try
        {
            openConnection();//Abrir conexão com banco 

            conexao.setAutoCommit(false);   //evitando o autocommit!

            StringBuilder sql = new StringBuilder();   //--> String para conter a query!
            sql.append("UPDATE LOGIN ");
            sql.append("SET STATUS = 0 ");
            sql.append("WHERE ID_CLIENTE = ?");

            pst = conexao.prepareStatement(sql.toString()); //criando caminho para o sql

            pst.setInt(1, usuario.getId());

            pst.executeUpdate();

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
            }//catch
        }//finally
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        return null;
    }

    /**
     * Método responsável por efetuar uma autenticação de um usuário na
     * aplicação
     *
     * @param entidade PrestadorServico ou Cliente com os dados Email e Senha da
     * Classe Pai!
     * @return entidade Dominio com todos os dados do usuário!
     * @throws SQLException Caso Algo der errado retorna um SQLException!
     */
    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            Usuario usuario = (Usuario) entidade;

            IDAO dao;   //--> conexao dao para cliente ou prestador!

            openConnection();   //abrindo conexao com o banco de dados!

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT * FROM LOGIN ");
            sql.append("WHERE email = ? and senha = ? ");

            pst = conexao.prepareStatement(sql.toString());

            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getSenha());

            ResultSet rs = pst.executeQuery();

            if (rs.next())  //entra no While enquanto tiver registro para leitura
            {
                if (rs.getInt("id_prestador") != 0)    //Decidindo qual campo pegar o ID! Prestador ou Cliente!
                {
                    PrestadorServico prestador = new PrestadorServico();
                    prestador.setId(rs.getInt("id_prestador"));
                    prestador.setEmail(rs.getString("email"));
                    prestador.setTipoConta(rs.getString("tipo_conta"));
                    prestador.setStatus(rs.getInt("status"));

                    dao = new PrestadorServicoDAO(conexao);

                    dao.consultarUm(prestador);

                    return prestador;
                } else if (rs.getInt("id_cliente") != 0)
                {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id_cliente"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setSenha(rs.getString("senha"));
                    cliente.setStatus(rs.getInt("status"));
                    cliente.setTipoConta(rs.getString("tipo_conta"));

                    dao = new ClienteDAO(conexao);

                    dao.consultarUm(cliente);

                    return cliente;
                }
            } else    //não existe ninguem com esse email e senha =/
            {
                return null;
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
            return null;
        } finally
        {
            try
            {
                conexao.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public EntidadeDominio existeEmail(EntidadeDominio entidade) throws SQLException
    {
        Usuario usuario = (Usuario) entidade;
        openConnection();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT EMAIL FROM LOGIN ");
        sql.append("WHERE EMAIL = ?");

        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, usuario.getEmail());

        ResultSet rs = pst.executeQuery();

        if (rs.next())   //Retornou um Usuario?
        {
            return usuario;
        } else
        {
            return null;
        }
    }
}
