/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;


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
 * @author Elvin
 */
public class InteressadoDAO extends AbstractDAO{

    public InteressadoDAO() {
    }

    public InteressadoDAO(Connection conexao) {
        super(conexao);
    }

    
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        Usuario usuario = (Usuario)entidade;
        openConnection();
        conexao.setAutoCommit(false);
        
        String sql= "INSERT INTO INTERESSADOS(ID_PRESTADOR,ID_PEDIDOS,STATUS) VALUES(?,?,?)";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1,usuario.getId());
            pst.setInt(2,usuario.getPedido().getId());
            pst.setString(3, usuario.getPedido().getStatus().EM_ANDAMENTO.toString());
            pst.executeUpdate();
            conexao.commit();
        }catch(SQLException e){
            
            try{
                conexao.rollback();
            }catch(SQLException ex1){
                ex1.printStackTrace();
            }
            e.printStackTrace();
            throw new SQLException();
        }finally{
            try{
                conexao.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                throw new SQLException();
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException {
  
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        try{   
            if(conexao == null)
                openConnection();
            Pedido pedido = (Pedido)entidade;
            ArrayList<EntidadeDominio> lista = new ArrayList<>();
            String sql = "SELECT * FROM INTERESSADOS WHERE ID_PEDIDOS =?";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, pedido.getId());
            ResultSet resultado = pst.executeQuery();
            while(resultado.next()){
                PrestadorServico prestador = new PrestadorServico();
                prestador.setId(resultado.getInt("id_prestador"));
                if(resultado.getString("status").equals("EM_ANDAMENTO"))
                    prestador.setCandidatura(Status.EM_ANDAMENTO); 
                else if(resultado.getString("status").equals("NAO_SELECIONADO"))
                    prestador.setCandidatura(Status.NAO_SELECIONADO); 
                else
                    prestador.setCandidatura(Status.SELECIONADO); 
                pedido.setId(resultado.getInt("id_pedidos"));
                lista.add(prestador);
            }
            return lista;
        }catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException();
        }
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
