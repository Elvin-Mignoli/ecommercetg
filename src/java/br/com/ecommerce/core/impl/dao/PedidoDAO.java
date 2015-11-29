/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.domain.Avaliacao;
import br.com.ecommerce.domain.BuscaPedidoMes;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.HabilidadeProcurada;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Status;
import java.sql.Connection;
import java.sql.Date;
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

    public PedidoDAO(Connection conexao)
    {
        super(conexao);
    }

    public PedidoDAO()
    {
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        Pedido pedido = (Pedido) entidade;
        
        try
        {
            openConnection();   //abrindo conexao com o banco de dados
            conexao.setAutoCommit(false);   //fazendo commit manualmente          

            StringBuilder sql = new StringBuilder();
            //preparando SQL para salvar o pe!
            sql.append("INSERT INTO PEDIDOS ");
            sql.append("(id_cliente,descricao,habilidades,habilidade_cliente,data_pedido,status,data_inicio,data_fim,horapedido,video_canal) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?,NEXTVAL('seq_video_canal'))");

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
            //pegando a data do pe
            pst.setDate(5, new java.sql.Date(pedido.getData().getTimeInMillis()));
            //pegando status do pe
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

                pst.setString(1, h.toLowerCase().trim());
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
            throw new SQLException("Algum erro inesperado ocorreu ao tentar salvar o pedido");
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

            pst.setString(1, Status.CANCELADO.name());
            pst.setInt(2, pedido.getId());

            pst.executeUpdate();

            conexao.commit();
        } catch (SQLException ex)
        {
            try
            {
                conexao.rollback();
            } catch (SQLException ex1)
            {
                ex.printStackTrace();
            }
            ex.printStackTrace();
            throw new SQLException("Erro ao tentar cancelar o pedido");
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
                if(transaction)
                    conexao.close();    //fechando a conexao
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                throw new SQLException("Algum erro ocorreu ao consultar um pedido");
            }
        }
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            Pedido pe = (Pedido) entidade;
            if(conexao == null || conexao.isClosed())
            { 
                openConnection();
                conexao.setAutoCommit(false);
            }

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
                ClienteDAO clientedao = new ClienteDAO(conexao);
                pe.setCliente((Cliente) clientedao.consultarUm(pe.getCliente()));
                AutenticarDAO logindao = new AutenticarDAO(conexao);
                pe.setCliente((Cliente) logindao.consultarLogin(pe.getCliente()));
                //buscar dado do prestador
                pe.setPrestadorFinalista(new PrestadorServico());
                pe.getPrestadorFinalista().setId(rs.getInt("id_prestador"));
                if (pe.getPrestadorFinalista().getId() != 0)
                {
                    PrestadorServicoDAO prestadordao = new PrestadorServicoDAO(conexao);
                    pe.setPrestadorFinalista((PrestadorServico) prestadordao.consultarUm(pe.getPrestadorFinalista()));
                    logindao = new AutenticarDAO(conexao);
                    pe.setPrestadorFinalista((PrestadorServico)logindao.consultarLogin(pe.getPrestadorFinalista()));

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
                pe.setAvaliacao(new Avaliacao());
                pe.getAvaliacao().setId(rs.getInt("id_avaliacao"));
                pe.setValor(rs.getDouble("valor"));
                //procurar interessados
                InteressadoDAO dao = new InteressadoDAO(conexao);
                pe.setPrestadores(dao.consultar(pe));
                pe.setQtdeInteressados(pe.getPrestadores().size());

            }
            return pe;
        } 
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException("Erro ao consultar o pedido");
        }
        finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            }catch (SQLException ex)
            {
                ex.printStackTrace();
                throw new SQLException("Algum problema ao consultar um notify!");
            }
        }

    }

    public void AtualizarStatusPedido(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            Pedido pedido = (Pedido) entidade;

            if(conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);   //setando autocommit para false
            }   //abrindo conexao

            StringBuilder sql = new StringBuilder();

            sql.append("UPDATE PEDIDOS ");
            sql.append("SET STATUS = ? ");
            sql.append("WHERE ID = ?");

            pst = conexao.prepareStatement(sql.toString());

            pst.setString(1,pedido.getStatus().getValue());
            pst.setInt(2, pedido.getId());

            pst.executeUpdate();

            //atualizando o status dos prestadores
            pedido.setStatus(Status.NAO_SELECIONADO);

            IDAO interDAO = new InteressadoDAO(conexao);

            interDAO.atualizar(pedido);   //atualizando status dos campos!
            
            if(transaction)
                conexao.commit();

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
            throw new SQLException("Erro ao tentar atualizar o Pedido!");
        } finally
        {
            try
            {
                if(transaction)
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
            sql.append("SELECT p.*, ");
            sql.append("(SELECT count(i.id) as qtde ");
            sql.append("FROM INTERESSADOS as i ");
            sql.append("WHERE i.id_pedidos = p.id) ");
            sql.append("FROM PEDIDOS AS p, INTERESSADOS as i ");
            sql.append("WHERE p.id_cliente = ? ");
            sql.append("GROUP BY p.id");
            /*sql.append("SELECT p.*,count(i.id) as qtde FROM PEDIDOS AS p, INTERESSADOS as i ");
            sql.append("WHERE p.id_cliente = ? ");
            sql.append("GROUP BY p.id ");
            sql.append("ORDER BY p.data_pedido DESC");*/

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
                pe.setQtdeInteressados(rs.getInt("qtde"));
                pe.setAvaliacao(new Avaliacao());
                pe.getAvaliacao().setId(rs.getInt("id_avaliacao"));
                pe.setValor(rs.getDouble("valor"));
                
                entidades.add(pe);
            }
        } else if (pedido.getConsulta().equals(Pedido.PRESTADOR))
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
                //buscar dados do cliente
                pe.setCliente(new Cliente());
                pe.getCliente().setId(rs.getInt("id_cliente"));
                ClienteDAO clientedao = new ClienteDAO(conexao);
                pe.setCliente((Cliente) clientedao.consultarUm(pe.getCliente()));
                //buscar dado do prestador
                pe.setPrestadorFinalista(new PrestadorServico());
                pe.getPrestadorFinalista().setId(rs.getInt("id_prestador"));
                if (pe.getPrestadorFinalista().getId() != 0)
                {
                    PrestadorServicoDAO prestadordao = new PrestadorServicoDAO(conexao);
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
                pe.setValor(rs.getDouble("valor"));
                //procurar interessados
                InteressadoDAO dao = new InteressadoDAO(conexao);
                pe.setPrestadores(dao.consultar(pe));
                pe.setQtdeInteressados(pe.getPrestadores().size());
                entidades.add(pe);
            }
        } else if (pedido.getConsulta().equals(Pedido.MURAL))
        {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM PEDIDOS AS p ");
            sql.append("WHERE p.status = ? ORDER BY DATA_PEDIDO");

            pst = conexao.prepareStatement(sql.toString());

            pst.setString(1, pedido.getStatus().ABERTO.toString());

            ResultSet rs = pst.executeQuery();

            while (rs.next())    //pegando os dados da pesquisa
            {
                Pedido pe = new Pedido();
                pe.setId(rs.getInt("id"));
                //buscar dados do cliente
                pe.setCliente(new Cliente());
                pe.getCliente().setId(rs.getInt("id_cliente"));
                ClienteDAO clientedao = new ClienteDAO(conexao);
                pe.setCliente((Cliente) clientedao.consultarUm(pe.getCliente()));
                //buscar dado do prestador
                pe.setPrestadorFinalista(new PrestadorServico());
                pe.getPrestadorFinalista().setId(rs.getInt("id_prestador"));
                if (pe.getPrestadorFinalista().getId() != 0)
                {
                    PrestadorServicoDAO prestadordao = new PrestadorServicoDAO(conexao);
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
                pe.setValor(rs.getDouble("valor"));
                //procurar interessados
                InteressadoDAO dao = new InteressadoDAO(conexao);
                pe.setPrestadores(dao.consultar(pe));
                pe.setQtdeInteressados(pe.getPrestadores().size());
                entidades.add(pe);
            }
        }else if(pedido.getConsulta().equals(Pedido.CANDIDATURAS)){
           StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM PEDIDOS AS p ");
            sql.append("ORDER BY DATA_PEDIDO");

            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();

            while (rs.next())    //pegando os dados da pesquisa
            {
                Pedido pe = new Pedido();
                pe.setId(rs.getInt("id"));
                //buscar dados do cliente
                pe.setCliente(new Cliente());
                pe.getCliente().setId(rs.getInt("id_cliente"));
                ClienteDAO clientedao = new ClienteDAO(conexao);
                pe.setCliente((Cliente) clientedao.consultarUm(pe.getCliente()));
                //buscar dado do prestador
                pe.setPrestadorFinalista(new PrestadorServico());
                pe.getPrestadorFinalista().setId(rs.getInt("id_prestador"));
                if (pe.getPrestadorFinalista().getId() != 0)
                {
                    PrestadorServicoDAO prestadordao = new PrestadorServicoDAO(conexao);
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
                pe.setValor(rs.getDouble("valor"));
                //procurar interessados
                InteressadoDAO dao = new InteressadoDAO(conexao);
                pe.setPrestadores(dao.consultar(pe));
                pe.setQtdeInteressados(pe.getPrestadores().size());
                entidades.add(pe); 
            }
        }
        return entidades;
    }
    
    public Resultado habilidadesMaisProcuradas() throws SQLException
    {
        try
        {
            if(conexao == null || conexao.isClosed())   //conexao esta fechada??
            {
                openConnection();   //abrindo conexao!
            }
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT lower(ph.habilidade) AS habilidade, count(ph.habilidade) AS quantidade ");
            sql.append("FROM pedido_habilidades AS ph, pedidos AS p ");
            sql.append("WHERE ph.id_pedido = p.id ");
            sql.append("GROUP BY ph.habilidade ");
            sql.append("ORDER BY quantidade DESC ");
            sql.append("LIMIT 5");
            
            pst = conexao.prepareStatement(sql.toString());
            
            ResultSet rs = pst.executeQuery();
            
            Resultado result = new Resultado();
            
            while(rs.next())    //existem registros no resultset?
            {
                HabilidadeProcurada hb = new HabilidadeProcurada();
                hb.setLabel(rs.getString("habilidade"));
                hb.setValue(rs.getInt("quantidade"));
                
                result.addEntidades(hb);
            }
            
            return result;
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
                conexao.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    public Resultado TotalPedidos() throws SQLException
    {
            Resultado rs = new Resultado();
        try
        {
            if(conexao == null || conexao.isClosed())
                openConnection();   //abrindo conexao!
            
            StringBuilder sql = new StringBuilder();
            sql.append("select count(p.id) as qtde, p.data_pedido ");
            sql.append("FROM pedidos as p ");
            sql.append("WHERE p.data_pedido between '2015-09-01' and '2015-09-30' ");
            sql.append("GROUP BY  p.data_pedido");
            
            pst = conexao.prepareStatement(sql.toString());
            
            ResultSet result = pst.executeQuery();
            
            while(result.next())    //iterando pelos dados do retorno!
            {
                //String label = rs.get
                BuscaPedidoMes bpm = new BuscaPedidoMes();
            }
            
            return rs;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return rs;
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
    
    //metodo para retornar a quantidade de pedidos em Range de Meses
    public Resultado totalPedidosMes() throws SQLException
    {
        Resultado rs = new Resultado();
        try
        {
            if(conexao == null || conexao.isClosed())
            {
                openConnection();   //abrindo conexao!
            }
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT count(p.id) as quantidade,date_part('month',p.data_pedido) as mes ");
            sql.append("FROM pedidos as p ");
            sql.append("WHERE status = 'ABERTO' and date_part('month',p.data_pedido) between 08 and 12 ");
            sql.append("GROUP BY mes");
            
            pst = conexao.prepareStatement(sql.toString());
            
            ResultSet result = pst.executeQuery();
            
            String mes[] = {"Janeiro","Fevereiro","Maio","Abril","Março","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
            
            while(result.next())
            {
                Integer x = result.getInt("mes");
                Integer quantidade = result.getInt("quantidade");
                
                BuscaPedidoMes bpm = new BuscaPedidoMes();
                
                bpm.getValues().put(mes[x-1], quantidade);
                
                bpm.setStatus("ABERTO");
                
                rs.addEntidades(bpm);
            }
            
            sql = new StringBuilder();
            
            sql.append("SELECT count(p.id) as quantidade,date_part('month',p.data_pedido) as mes ");
            sql.append("FROM pedidos as p ");
            sql.append("WHERE status = 'FECHADO' and date_part('month',p.data_pedido) between 08 and 12 ");
            sql.append("GROUP BY mes");
            
            pst = conexao.prepareStatement(sql.toString());
            
            result = pst.executeQuery();
            
            while(result.next())
            {
                Integer x = result.getInt("mes");
                Integer quantidade = result.getInt("quantidade");
                
                BuscaPedidoMes bpm = new BuscaPedidoMes();
               
                bpm.getValues().put(mes[x-1], quantidade);
                
                bpm.setStatus("FECHADO");
                
                rs.addEntidades(bpm);
            }
            
            sql = new StringBuilder();
            
            sql.append("SELECT count(p.id) as quantidade,date_part('month',p.data_pedido) as mes ");
            sql.append("FROM pedidos as p ");
            sql.append("WHERE status = 'CANCELADO' and date_part('month',p.data_pedido) between 08 and 12 ");
            sql.append("GROUP BY mes");
            
            pst = conexao.prepareStatement(sql.toString());
            
            result = pst.executeQuery();
            
            while(result.next())
            {
                Integer x = result.getInt("mes");
                Integer quantidade = result.getInt("quantidade");
                
                BuscaPedidoMes bpm = new BuscaPedidoMes();
               
                bpm.getValues().put(mes[x-1], quantidade);
                
                bpm.setStatus("CANCELADO");
                
                rs.addEntidades(bpm);
            }
            
            return rs;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return rs;
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
    
    public Resultado consultarPedidosStatus()
    {
        try
        {
            if(conexao == null || conexao.isClosed())
                openConnection();
            
            StringBuilder sql = new StringBuilder();
            
            return null;
        }
        catch(SQLException ex)
        {
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
    
    public void AtualizarDataHora(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            Pedido pedido = (Pedido) entidade;
            if(conexao == null || conexao.isClosed())
                openConnection();
            
            conexao.setAutoCommit(false);
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("UPDATE pedidos ");
            sql.append("SET data_inicio = ?,");
            sql.append("data_fim = ?,");
            sql.append("horapedido = ? ");
            sql.append("WHERE id = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            
            java.sql.Date dt_inicio = new Date(pedido.getDataInicio().getTime());
            pst.setDate(1, dt_inicio);
            java.sql.Date dt_fim = new Date(pedido.getDataFim().getTime());
            pst.setDate(2,dt_fim);
            Timestamp time = new Timestamp(pedido.getHoraConsultoria().getTimeInMillis());
            pst.setTimestamp(3, time);
            pst.setInt(4, pedido.getId());
            
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
            throw new SQLException("Erro ao atualizar Data e Hora da consultoria");
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
    
    public void removerConsultor(EntidadeDominio entidade) throws SQLException
    {
        Pedido pedido = (Pedido) entidade;
        try
        {
            if(conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);
            }
            
            InteressadoDAO dao = new InteressadoDAO(conexao);
            
            dao.excluir(pedido);  //atualizando status de todos os inscritos
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("UPDATE PEDIDOS ");
            sql.append("SET STATUS = ?, ");
            sql.append("ID_PRESTADOR = ? ");
            sql.append("WHERE ID = ? ");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, Status.ABERTO.getValue());
            pst.setInt(2, 0);
            pst.setInt(3, pedido.getId());
            
            pst.executeUpdate();
            
            if(transaction)
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
        }
    }
    
    public void SelecionarPrestador(EntidadeDominio entidade) throws SQLException
    {
        Pedido pedido = (Pedido) entidade;
        try
        {
            if(conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);
            }
            
            InteressadoDAO dao = new InteressadoDAO(conexao);
            
            pedido.setStatus(Status.NAO_SELECIONADO);
            
            dao.AtualizarStatus(entidade);
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("UPDATE PEDIDOS ");
            sql.append("SET STATUS = ?, ");
            sql.append("ID_PRESTADOR = ?, ");
            sql.append("VALOR = ? ");
            sql.append("WHERE ID = ? ");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pedido.setStatus(Status.EM_PROCESSO);
            
            pst.setString(1, pedido.getStatus().getValue());
            pst.setInt(2, pedido.getPrestadorFinalista().getId());
            pst.setDouble(3, pedido.getPrestadorFinalista().getValorConsultoria());
            pst.setInt(4, pedido.getId());
            
            pst.executeUpdate();
            
            if(transaction)
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
            throw new SQLException();
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
    
    public void AvaliarPedido(EntidadeDominio entidade)throws SQLException
    {
        Pedido pedido = (Pedido) entidade;
        try
        {
            if(conexao == null || conexao.isClosed())
            {
                openConnection();
                if(transaction)
                    conexao.setAutoCommit(false);
            }
            
            IDAO dao = new AvaliacaoDAO(conexao);
            
            dao.salvar(pedido); //salvando a avaliacao
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("UPDATE PEDIDOS ");
            sql.append("SET ID_AVALIACAO = ? ");
            sql.append("WHERE ID = ? ");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setInt(1, pedido.getAvaliacao().getId());
            pst.setInt(2, pedido.getId());
            
            pst.executeUpdate();
            
            if(transaction)
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
            throw new SQLException(ex.getMessage());
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
