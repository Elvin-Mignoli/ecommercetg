/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class PedidoDAO extends AbstractDAO
{

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        Pedido pedido = (Pedido) entidade;

        try
        {
            openConnection();   //abrindo conexao com o banco de dados
            conexao.setAutoCommit(false);   //fazendo commit manualmente          

            StringBuilder sql = new StringBuilder();
            //preparando SQL para salvar o pedido!
            sql.append("INSERT INTO PEDIDOS ");
            sql.append("(id_cliente,descricao,habilidades,habilidade_cliente,data_pedido,status,data_inicio,data_fim,horapedido) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?)");

            pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            //setando os parametros
            pst.setInt(1, pedido.getCliente().getId());
            pst.setString(2, pedido.getDescricao());
            //pegando habilidade requerida
            String habilidadeRequerida = pedido.getHabilidadePrestador().toString().replace("[", "").replace("]", "");
            pst.setString(3, habilidadeRequerida);
            //pegando habilidade do cliente
            String habilidadeCliente = pedido.getHabilidadeCliente().toString().replace("[", "").replace("]", "");
            pst.setString(4, habilidadeCliente);
            //pegando a data do pedido
            pst.setDate(5, new java.sql.Date(pedido.getData().getTimeInMillis()));
            //pegando status do pedido
            pst.setString(6, pedido.getStatus().name());
            //pegando data de inico para a consultoria
            pst.setDate(7, new java.sql.Date(pedido.getDataInicio().getTime()));
            //pegando data de Fim da consultoria
            pst.setDate(8, new java.sql.Date(pedido.getDataFim().getTime()));
            //pegando hora da consultoria
            Timestamp time = new Timestamp(pedido.getHoraConsultoria().getTimeInMillis());
            pst.setTimestamp(9, time);

            pst.executeUpdate();  //executando query no banco de dados!

            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next())
            {
                pedido.setId(rs.getInt("id"));
            }

            //varrendo dados na lista!
            for (String h : pedido.getHabilidadePrestador())
            {
                sql = new StringBuilder();
                //salvando as habilidades requeridas para o pedido!
                sql.append("INSERT INTO pedido_habilidades ");
                sql.append("(habilidade,id_pedido) ");
                sql.append("VALUES(?,?)");

                pst = conexao.prepareStatement(sql.toString());

                pst.setString(1, h);
                pst.setInt(2, pedido.getId());

                pst.execute();
            }

            conexao.commit();   //commitando as alteracoes no banco de dados!
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
            throw new SQLException("Algum erro inesperado ocorreu");
        } finally
        {
            try
            {
                conexao.close();    //fechando a conexao com o banco!
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        Pedido pedido = (Pedido) entidade;
        StringBuilder sql = new StringBuilder();
        try
        {
            if (conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);
            }
            /*
             Deletando as habilidades do pedido
             */
            sql = new StringBuilder();
            //salvando as habilidades requeridas para o pedido!
            sql.append("DELETE FROM pedido_habilidades ");
            sql.append("WHERE id_pedido = ?");

            pst = conexao.prepareStatement(sql.toString());

            pst.setInt(1, pedido.getId());

            pst.execute();
            //fim da exclusao!

            sql = new StringBuilder();

            sql.append("UPDATE PEDIDOS ");
            sql.append("SET ");
            sql.append("DESCRICAO = ?, ");
            sql.append("HABILIDADES = ?, ");
            sql.append("HABILIDADE_CLIENTE = ?, ");
            sql.append("DATA_INICIO = ?, ");
            sql.append("DATA_FIM = ?, ");
            sql.append("HORAPEDIDO = ? ");
            sql.append("WHERE ID = ? and ID_CLIENTE = ?");

            pst = conexao.prepareStatement(sql.toString());

            pst.setString(1, pedido.getDescricao());
            pst.setString(2, pedido.getHabilidadePrestador().toString().replace("[", "").replace("]", ""));
            pst.setString(3, pedido.getHabilidadeCliente().toString().replace("[", "").replace("]", ""));
            pst.setDate(4, new java.sql.Date(pedido.getDataInicio().getTime()));
            pst.setDate(5, new java.sql.Date(pedido.getDataFim().getTime()));
            Timestamp time = new Timestamp(pedido.getHoraConsultoria().getTimeInMillis());
            pst.setTimestamp(6, time);
            pst.setInt(7, pedido.getId());
            pst.setInt(8, pedido.getCliente().getId());

            pst.executeUpdate();

            /*
             Inserindo as novas habilidades do cliente
             */
            //varrendo dados na lista!
            for (String h : pedido.getHabilidadePrestador())
            {
                sql = new StringBuilder();
                //salvando as habilidades requeridas para o pedido!
                sql.append("INSERT INTO pedido_habilidades ");
                sql.append("(habilidade,id_pedido) ");
                sql.append("VALUES(?,?)");

                pst = conexao.prepareStatement(sql.toString());

                pst.setString(1, h);
                pst.setInt(2, pedido.getId());

                pst.execute();
            }
            
            conexao.commit();   //commitando as alterações

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            try
            {
                conexao.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
            throw new SQLException("Tenta refazer essa operação mais tarde!");
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

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        Pedido pedido = (Pedido) entidade;
        try
        {
            openConnection();
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("UPDATE PEDIDOS ");
            sql.append("SET STATUS = ? ");
            sql.append("WHERE ID = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, Status.FECHADO.name());
            pst.setInt(2, pedido.getId());
            
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
                ex.printStackTrace();
            }
            ex.printStackTrace();
            throw new SQLException("Erro ao tentar fechar o pedido");
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

    /**
     * Busca todos os pedidos relacionados à um Cliente
     *
     * @param entidade Pedido com o atributo cliente populado
     * @return List<Pedidos> de um determinado cliente, ou null se não existe
     * nenhum pedido
     * @throws SQLException Lança SQLException se alguma coisa dar errado na
     * consulta
     */
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        List<EntidadeDominio> entidades = new ArrayList<>();
        try
        {
            if (conexao == null || conexao.isClosed())
            {
                openConnection();   //abrindo a conexao!
            }

            entidades = this.filtroConsultar(entidade, pst);

            return entidades;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException("Algum erro ocorreu ao fazer a pesquisa do pedido!");
        } finally
        {
            try
            {
                conexao.close();    //fechando a conexao
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                throw new SQLException("Algum erro ocorreu ao tentar fechar a conexao!");
            }
        }
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        Pedido pe = (Pedido) entidade;
        try
        {
            openConnection();

            String sql = "SELECT * FROM PEDIDOS WHERE ID = ?";

            pst = conexao.prepareStatement(sql);
            pst.setInt(1, pe.getId());
            ResultSet rs = pst.executeQuery();
            
            if (rs.next())
            {
                pe = new Pedido();
                pe.setId(rs.getInt("id"));
                //buscar dados do cliente
                pe.setCliente(new Cliente());
                pe.getCliente().setId(rs.getInt("id_cliente"));
                ClienteDAO clientedao = new ClienteDAO();
                pe.setCliente((Cliente) clientedao.consultarUm(pe.getCliente()));
                //buscar dado do prestador
                pe.setPrestadorFinalista(new PrestadorServico());
                pe.getPrestadorFinalista().setId(rs.getInt("id_prestador"));
                if (pe.getPrestadorFinalista().getId() != 0)
                {
                    PrestadorServicoDAO prestadordao = new PrestadorServicoDAO();
                    pe.setPrestadorFinalista((PrestadorServico) prestadordao.consultarUm(pe.getPrestadorFinalista()));

                }
                pe.setDescricao(rs.getString("descricao"));
                String habilidades[] = rs.getString("habilidades").split(",");
                pe.addHabilidadeRequerida(habilidades);
                String habilidadeCliente[] = rs.getString("habilidade_cliente").split(",");
                pe.addHabilidadeCliente(habilidadeCliente);
                pe.setDataInicio(rs.getDate("data_inicio"));
                pe.setDataFim(rs.getDate("data_fim"));
                pe.setData(Calendar.getInstance());
                pe.getData().setTime(rs.getDate("data_pedido"));
                pe.setStatus(Status.valueOf(rs.getString("status")));
                pe.setHoraConsultoria(Calendar.getInstance());
                pe.getHoraConsultoria().setTimeInMillis(rs.getTimestamp("horapedido").getTime());
                pe.setCanal(rs.getString("video_canal"));
                //procurar interessados
                InteressadoDAO dao = new InteressadoDAO();
                pe.setPrestadores(dao.consultar(pe));
                pe.setQtdeInteressados(pe.getPrestadores().size());

            }
            return pe;
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException("Erro ao consultar um pedido");
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

    @SuppressWarnings("ConvertToStringSwitch")
    private List<EntidadeDominio> filtroConsultar(EntidadeDominio entidade, PreparedStatement pst) throws SQLException
    {
        Pedido pedido = (Pedido) entidade;
        List<EntidadeDominio> entidades = new ArrayList<>();

        if (pedido.getConsulta().equals(Pedido.CLIENTE))
        {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM PEDIDOS AS p ");
            sql.append("WHERE p.id_cliente = ? ORDER BY p.data_pedido DESC");

            pst = conexao.prepareStatement(sql.toString());

            pst.setInt(1, pedido.getCliente().getId());

            ResultSet rs = pst.executeQuery();

            while (rs.next())    //pegando os dados da pesquisa
            {
                Pedido pe = new Pedido();
                pe.setId(rs.getInt("id"));
                pe.setCliente(new Cliente());
                pe.getCliente().setId(rs.getInt("id_cliente"));
                pe.setPrestadorFinalista(new PrestadorServico());
                pe.getPrestadorFinalista().setId(rs.getInt("id_prestador"));
                pe.setDescricao(rs.getString("descricao"));
                String habilidades[] = rs.getString("habilidades").split(",");
                pe.addHabilidadeRequerida(habilidades);
                String habilidadeCliente[] = rs.getString("habilidade_cliente").split(",");
                pe.addHabilidadeCliente(habilidadeCliente);
                pe.setDataInicio(rs.getDate("data_inicio"));
                pe.setDataFim(rs.getDate("data_fim"));
                pe.setData(Calendar.getInstance());
                pe.getData().setTime(rs.getDate("data_pedido"));
                pe.setStatus(Status.valueOf(rs.getString("status")));
                pe.setHoraConsultoria(Calendar.getInstance());
                pe.getHoraConsultoria().setTimeInMillis(rs.getTimestamp("horapedido").getTime());

                entidades.add(pe);
            }
        } 
        else if (pedido.getConsulta().equals(Pedido.PRESTADOR))
        {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM PEDIDOS AS p ");
            sql.append("WHERE p.id_prestador = ?");

            pst = conexao.prepareStatement(sql.toString());

            pst.setInt(1, pedido.getPrestadorFinalista().getId());

            ResultSet rs = pst.executeQuery();

            while (rs.next())    //pegando os dados da pesquisa
            {
                Pedido pe = new Pedido();
                pe.setId(rs.getInt("id"));
                pe.setCliente(new Cliente());
                pe.setId(rs.getInt("id_cliente"));
                pe.setPrestadorFinalista(new PrestadorServico());
                pe.getPrestadorFinalista().setId(rs.getInt("id_prestador"));
                pe.setDescricao(rs.getString("descricao"));
                String habilidades[] = rs.getString("habilidades").split(",");
                pe.addHabilidadeRequerida(habilidades);
                String habilidadeCliente[] = rs.getString("habilidade_cliente").split(",");
                pe.addHabilidadeCliente(habilidadeCliente);
                pe.setDataInicio(rs.getDate("data_inicio"));
                pe.setDataFim(rs.getDate("data_fim"));
                pe.setData(Calendar.getInstance());
                pe.getData().setTime(rs.getDate("data_pedido"));
                pe.setStatus(Status.valueOf(rs.getString("status")));
                pe.setHoraConsultoria(Calendar.getInstance());
                pe.getHoraConsultoria().setTimeInMillis(rs.getTimestamp("horapedido").getTime());

                entidades.add(pe);
            }
        } else if (pedido.getConsulta().equals(Pedido.MURAL))
        {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM PEDIDOS AS p ");
            sql.append("WHERE p.status = ?");

            pst = conexao.prepareStatement(sql.toString());

            pst.setString(1, pedido.getStatus().name());

            ResultSet rs = pst.executeQuery();

            while (rs.next())    //pegando os dados da pesquisa
            {
                Pedido pe = new Pedido();
                pe.setId(rs.getInt("id"));
                pe.setCliente(new Cliente());
                pe.setId(rs.getInt("id_cliente"));
                pe.setPrestadorFinalista(new PrestadorServico());
                pe.getPrestadorFinalista().setId(rs.getInt("id_prestador"));
                pe.setDescricao(rs.getString("descricao"));
                String habilidades[] = rs.getString("habilidades").split(",");
                pedido.addHabilidadeRequerida(habilidades);
                String habilidadeCliente[] = rs.getString("habilidade_cliente").split(",");
                pedido.addHabilidadeCliente(habilidadeCliente);
                pedido.setDataInicio(rs.getDate("data_inicio"));
                pedido.setDataFim(rs.getDate("data_fim"));
                pedido.setData(Calendar.getInstance());
                pedido.getData().setTime(rs.getDate("data_pedido"));
                pedido.setStatus(Status.valueOf(rs.getString("status")));
                pedido.setHoraConsultoria(Calendar.getInstance());
                pedido.getHoraConsultoria().setTimeInMillis(rs.getTimestamp("horapedido").getTime());

                entidades.add(pe);
            }

        }

        return entidades;
    }
}
