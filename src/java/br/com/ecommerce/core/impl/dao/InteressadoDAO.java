/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Status;
import br.com.ecommerce.domain.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author java
 */
public class InteressadoDAO extends AbstractDAO
{

    public InteressadoDAO()
    {
    }

    public InteressadoDAO(Connection conexao)
    {
        super(conexao);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        Usuario usuario = (Usuario) entidade;
        openConnection();
        conexao.setAutoCommit(false);

        String sql = "INSERT INTO INTERESSADOS(ID_PRESTADOR,ID_PEDIDOS,STATUS) VALUES(?,?,?)";
        try
        {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, usuario.getId());
            pst.setInt(2, usuario.getPedido().getId());
            pst.setString(3, usuario.getPedido().getStatus().EM_ANDAMENTO.toString());
            pst.executeUpdate();
            conexao.commit();
        } catch (SQLException e)
        {

            try
            {
                conexao.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
            e.printStackTrace();
            throw new SQLException();
        } finally
        {
            try
            {
                conexao.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                throw new SQLException();
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        Pedido pedido = (Pedido) entidade;

        if (conexao == null || conexao.isClosed())
        {
            openConnection();
            conexao.setAutoCommit(false);
        }

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE INTERESSADOS ");
        sql.append("SET STATUS = ? ");
        sql.append("WHERE ID_PEDIDOS = ? AND ID_PRESTADOR != ?");

        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, pedido.getStatus().name());
        pst.setInt(2, pedido.getId());
        pst.setInt(3,pedido.getPrestadorFinalista().getId());

        pst.executeUpdate();
        
        //Por favor, não repare nessa parte do código! Ainda somos iniciantes! Bjoos Abraços!
        sql = new StringBuilder();
        
        sql.append("UPDATE INTERESSADOS ");
        sql.append("SET STATUS = ? ");
        sql.append("WHERE ID_PEDIDOS = ? AND ID_PRESTADOR = ?");
        
        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, Status.SELECIONADO.name());
        pst.setInt(2, pedido.getId());
        pst.setInt(3,pedido.getPrestadorFinalista().getId());
        
        pst.executeUpdate();
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            if (conexao == null)
            {
                openConnection();
            }
            Pedido pedido = (Pedido) entidade;  //nao espera, por onde voce esta chamando esse metodo??
            ArrayList<EntidadeDominio> lista = new ArrayList<>();
            String sql = "SELECT * FROM INTERESSADOS WHERE ID_PEDIDOS =?";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, pedido.getId());
            ResultSet resultado = pst.executeQuery();

            IDAO dao = new PrestadorServicoDAO(conexao);

            while (resultado.next())
            {
                PrestadorServico prestador = new PrestadorServico();

                prestador.setId(resultado.getInt("id_prestador"));

                prestador = (PrestadorServico) dao.consultarUm(prestador);

                /*if (resultado.getString("status").equals(Status.EM_ANDAMENTO))
                 {
                 prestador.setCandidatura(Status.EM_ANDAMENTO);
                 } else if (resultado.getString("status").equals("NAO_SELECIONADO"))
                 {
                 prestador.setCandidatura(Status.NAO_SELECIONADO);
                 } else
                 {
                 prestador.setCandidatura(Status.SELECIONADO);
                 }*/
                prestador.setCandidatura(Status.valueOf(resultado.getString("status")));

                //pedido.setId(resultado.getInt("id_pedidos")); nao faz sentido essa linha!
                lista.add(prestador);
            }
            return lista;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException();
        }
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
