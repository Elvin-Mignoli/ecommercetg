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
            int idCliente = 0;
            int idPrestador = 0;
            
            if(entidade instanceof Cliente)
                idCliente = entidade.getId();
            else
                idPrestador = entidade.getId();
            
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
            sql.append("(EMAIL,SENHA,STATUS,TIPO_CONTA");
            
            if(usuario.getTipoConta().equalsIgnoreCase("Cliente"))
                sql.append(",ID_CLIENTE) ");
            else
                sql.append(",ID_PRESTADOR) ");
            
            sql.append("VALUES(?,?,?,?,?)");

            //conexao.setAutoCommit(false);//setando auto commit para false
            pst = conexao.prepareStatement(sql.toString());

            //setando parametros do insert
            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getSenha());
            pst.setInt(3, usuario.getStatus());
            pst.setString(4, usuario.getTipoConta());
            pst.setInt(5, entidade.getId());

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
            sql.append("WHERE ID = ?");  //criando sql para update no banco

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
                    prestador.setUsuarioID(rs.getInt("id"));
                    prestador.setImagem(rs.getString("imagem"));
                    
                    dao = new PrestadorServicoDAO(conexao);
                    
                    dao.consultarUm(prestador);

                    return prestador;
                } else if (rs.getInt("id_cliente") != 0)
                {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id_cliente"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setStatus(rs.getInt("status"));
                    cliente.setTipoConta(rs.getString("tipo_conta"));
                    cliente.setUsuarioID(rs.getInt("id"));
                    cliente.setImagem(rs.getString("imagem"));
                    
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
    /**
     * Método para atualizar um email de um usuário! (Cliente ou Prestador)
     * @param entidade EntidadeDominio contendo ID do usuário e Email
     */
    public void atualizaEmail(EntidadeDominio entidade) throws SQLException
    {
        pst = null;
        StringBuilder sql = new StringBuilder();
        Usuario usuario = (Usuario) entidade;
        try
        {
            openConnection();   //abrindo conexao com o  banco de dados
            
            conexao.setAutoCommit(false);   //setando commit para false
            
            sql.append("UPDATE LOGIN ");
            sql.append("SET EMAIL = ? ");
            sql.append("WHERE ID = ?");
            
            pst = conexao.prepareStatement(sql.toString()); //passando SQL para o PST!!!
            
            pst.setString(1, usuario.getEmail());   //parametro de email
            pst.setInt(2, usuario.getUsuarioID());     // parametro de senha
            
            pst.executeUpdate();    //executando a query no banco!
            
            conexao.commit();  //commitando as alteraçoes no banco de dados!
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
            throw new SQLException(ex);
        }
        finally
        {
            try
            {
                conexao.close();
                pst.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Método para atualizar apenas a senha de um usuário
     * @param entidade Usuario contendo a nova senha
     * @throws java.sql.SQLException lança um erro caso de alguma coisa errada na atualização
     */
    public void atualizaSenha(EntidadeDominio entidade) throws SQLException
    {
        Usuario usuario = (Usuario) entidade;
        StringBuilder sql = new StringBuilder();
        
        try
        {
            openConnection();   //abrindo conexao com o banco de dados!
            conexao.setAutoCommit(false);
            
            sql.append("UPDATE LOGIN ");
            sql.append("SET SENHA = ? ");
            sql.append("WHERE ID = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, usuario.getSenha());
            pst.setInt(2, usuario.getUsuarioID());
            
            pst.executeUpdate();
            
            conexao.commit();   //commitando todas as alterações no banco
        }
        catch(SQLException ex)
        {
            try
            {
                conexao.rollback(); //desfez todas as alterações caso de algum problema
            }
            catch(SQLException ex1)
            {
              ex1.printStackTrace();
            }
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                pst.close();
                conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    public void AtualizaImagem(EntidadeDominio entidade) throws SQLException
    {
        Usuario user = (Usuario) entidade;
        StringBuilder sql = new StringBuilder();
        
        try
        {
            if(conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);
            }
            
            sql.append("UPDATE LOGIN ");
            sql.append("SET imagem = ?");
            sql.append("WHERE ID = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, user.getImagem());
            pst.setInt(2, user.getUsuarioID());
            
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
            throw new SQLException(ex);
        }
        finally
        {
            try
            {
                pst.close();
                conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
