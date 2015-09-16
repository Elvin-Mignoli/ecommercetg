/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class PedidoDAO extends AbstractDAO
{

    public PedidoDAO() {
    }

    public PedidoDAO(Connection conexao) {
        super(conexao);
    }

    
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        Pedido pedido = (Pedido) entidade;
        
        try
        {
            openConnection();   //abrindo conexao com o banco de dados
            conexao.setAutoCommit(false);   //fazendo commit manualmente
            
            //preparando SQL para salvar o pedido!
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO PEDIDOS ");
            sql.append("(id_cliente,descricao,habilidades,habilidade_cliente,data_pedido,status,data_inicio,data_fim,horapedido) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?)");
            
            pst = conexao.prepareStatement(sql.toString());
            
            //setando os parametros
            pst.setInt(1, pedido.getCliente().getId());
            pst.setString(2, pedido.getDescricao());
            //pegando habilidade requerida
            String habilidadeRequerida = pedido.getHabilidadePrestador().toString().replace("[", "").replace("]", "");
            pst.setString(3,habilidadeRequerida);
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
            
            pst.execute();  //executando query no banco de dados!
            
            conexao.commit();   //commitando as alteracoes no banco de dados!
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
            throw new SQLException("Algum erro inesperado ocorreu");
        }
        finally
        {
            try
            {
                conexao.close();    //fechando a conexao com o banco!
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
    }
    
    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        try{    
            openConnection();
            Pedido pedido = new Pedido();
            pedido = (Pedido)entidade;
            ArrayList<EntidadeDominio> lista = new ArrayList<>();
            String sql = "SELECT * FROM PEDIDOS";
            pst = conexao.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            while(resultado.next()){
                
                pedido.setDescricao(resultado.getString("descricao"));
                pedido.setDataFim(resultado.getDate("data_fim"));
                pedido.setDataInicio(resultado.getDate("data_inicio"));
                pedido.setId(resultado.getInt("id"));
                InteressadorDAO dao = new InteressadorDAO(conexao);
                pedido.setPrestadores(dao.consultar(pedido));
                lista.add(pedido);
            }
            pedido.setListaPedidos(lista);
            return lista;
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
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
