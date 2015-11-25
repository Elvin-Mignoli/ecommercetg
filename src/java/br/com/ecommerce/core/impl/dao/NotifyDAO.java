package br.com.ecommerce.core.impl.dao;



import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Notify;
import br.com.ecommerce.domain.Pedido;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Elvin
 */
public class NotifyDAO extends AbstractDAO{

    public NotifyDAO() {
    }

    public NotifyDAO(Connection conexao) {
        super(conexao);
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        
        Notify notify = (Notify)entidade;
        StringBuilder sql = new StringBuilder();
        try
        {
            if (conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);
            }
            Notify notifyFound = buscarNotify(notify);
            if(notifyFound == null)
            {
                sql.append("INSERT INTO NOTIFICATION");
                sql.append("(IDCLIENTE,IDPRESTADOR,IDPEDIDO,CHANNEL,FLGON_CLIENTE, FLGON_PRESTADOR)");
                sql.append("VALUES(?,?,?,?,?,?)");

                pst = conexao.prepareStatement(sql.toString());
                pst.setInt(1, notify.getIdCliente());
                pst.setInt(2, notify.getIdPrestador());
                pst.setInt(3, notify.getPedido().getId());
                pst.setInt(4, notify.getChannelChat());
                pst.setBoolean(5,notify.isFlgOn_cliente());
                pst.setBoolean(6, notify.isFlgOn_prestador());

                pst.execute();

                conexao.commit();
            }else{
                notifyFound.setAcao("EXISTE"); // existe um notify para o pedido
                if(notify.isFlgOn_cliente())//cliente já esta no ambiente de videoconferencia?
                    notifyFound.setFlgOn_cliente(true);//sim
                if(notify.isFlgOn_prestador())//prestador já esta no ambiente de videoconferencia?
                    notifyFound.setFlgOn_prestador(true);//sim
                atualizar(notifyFound);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException("Algum erro ocorreu ao salvar um notify!");
        }finally
        {
            try
            {
                if(!conexao.isClosed())
                    if(transaction)
                        conexao.close();    //fechando a conexao
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                throw new SQLException("Algum erro ocorreu ao salvar um notify!");
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException {
        Notify notify = (Notify)entidade;
        StringBuilder sql = new StringBuilder();
        try
        {
            if (conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);
            }
            if(notify.getAcao().equals("EXISTE"))
            {
                sql.append("UPDATE NOTIFICATION SET FLGON_CLIENTE = ?, FLGON_PRESTADOR = ? WHERE channel = ?");
           
                pst = conexao.prepareStatement(sql.toString());
                pst.setBoolean(1, notify.isFlgOn_cliente());
                pst.setBoolean(2, notify.isFlgOn_prestador());
                pst.setInt(3, notify.getChannelChat());
                pst.executeUpdate();

                conexao.commit();
            }else if(notify.getAcao().equals("SAINDO"))
            {
                Notify notifyFound = (Notify) buscarNotify(notify);
                if(!notify.isFlgOn_cliente())
                    notifyFound.setFlgOn_cliente(false);
                if(!notify.isFlgOn_prestador())
                    notifyFound.setFlgOn_prestador(false);
                sql.append("UPDATE NOTIFICATION SET FLGON_CLIENTE = ?, FLGON_PRESTADOR = ? WHERE channel = ?");
           
                pst = conexao.prepareStatement(sql.toString());
                pst.setBoolean(1, notifyFound.isFlgOn_cliente());
                 pst.setBoolean(2, notifyFound.isFlgOn_prestador());
                pst.setInt(3, notifyFound.getChannelChat());
                pst.executeUpdate();

                conexao.commit();
            }
            
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException("Algum erro ocorreu ao atualizar um notify!");
        }finally
        {
            try
            {
                if(transaction)
                    conexao.close();    //fechando a conexao
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                throw new SQLException("Algum erro ocorreu ao atualizar um notify!");
            }
        }
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        Notify notify = (Notify) entidade;
        StringBuilder sql = new StringBuilder();
        ArrayList<EntidadeDominio> lista = new ArrayList<>();
        try
        {
           if(conexao == null || conexao.isClosed())
           { 
               openConnection();
               conexao.setAutoCommit(false);
           }
           sql.append("SELECT * ");
           sql.append("FROM NOTIFICATION ");
           if(notify.isFlgOn_cliente()) {
                sql.append("WHERE flgOn_cliente = TRUE ");//buscar um cliente no video chat para prestador
                sql.append("and idprestador = ?");
           }
           if(notify.isFlgOn_prestador()){
                sql.append("WHERE flgOn_prestador = TRUE ");//buscar um prestador no video chat para cliente
                sql.append("and idcliente = ?");
           }
           pst = conexao.prepareStatement(sql.toString());
           if(notify.getIdCliente() != 0)
             pst.setInt(1, notify.getIdCliente());
           if(notify.getIdPrestador() !=0)
               pst.setInt(1, notify.getIdPrestador());
           ResultSet rs = pst.executeQuery();
           
           /*if(rs.getRow() == 0)
               return null;*/
           
           while(rs.next()){
               Notify nt = new Notify();
               nt.setChannelChat(rs.getInt("channel"));
               nt.setFlgOn_cliente(rs.getBoolean("flgOn_cliente"));
               nt.setFlgOn_prestador(rs.getBoolean("flgOn_prestador"));
               nt.setIdCliente(rs.getInt("idCliente"));
               nt.setIdPrestador(rs.getInt("idPrestador"));
               nt.setId(rs.getInt("id"));
               //buscar dados do pedido
               Pedido pedido = new Pedido();
               pedido.setId(rs.getInt("idPedido"));
               PedidoDAO dao = new PedidoDAO(conexao);
               nt.setPedido((Pedido)dao.consultarUm(pedido));
               
               lista.add(nt);
           }
           return lista;
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException("Algum problema ao consultar um notify!");
        }finally{
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

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException {
        Notify notify = (Notify) entidade;
        StringBuilder sql = new StringBuilder();
        ArrayList<EntidadeDominio> lista = new ArrayList<>();
        try
        {
           if(conexao == null || conexao.isClosed())
           { 
               openConnection();
               conexao.setAutoCommit(false);
           }
           sql.append("SELECT * ");
           sql.append("FROM NOTIFICATION ");
           sql.append("WHERE channel = ?");
         
           pst = conexao.prepareStatement(sql.toString());
           pst.setInt(1, notify.getChannelChat());
           ResultSet rs = pst.executeQuery();
           
           /*if(rs.getRow() == 0)
               return null;*/
           
          if(rs.next()){
               Notify nt = new Notify();
               nt.setChannelChat(rs.getInt("channel"));
               nt.setFlgOn_cliente(rs.getBoolean("flgOn_cliente"));
               nt.setFlgOn_prestador(rs.getBoolean("flgOn_prestador"));
               nt.setIdCliente(rs.getInt("idCliente"));
               nt.setIdPrestador(rs.getInt("idPrestador"));
               nt.setId(rs.getInt("id"));
               //buscar dados do pedido
               Pedido pedido = new Pedido();
               pedido.setId(rs.getInt("idPedido"));
               PedidoDAO dao = new PedidoDAO(conexao);
               nt.setPedido((Pedido)dao.consultarUm(pedido));
               return nt;
           }
           return null;
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException("Algum problema ao consultar um notify!");
        }/*finally{
            try
            {
                if(transaction)
                    conexao.close();
            }catch (SQLException ex)
            {
                ex.printStackTrace();
                throw new SQLException("Algum problema ao consultar um notify!");
            }
        }*/
    }
    
    
    public Notify buscarNotify(Notify notify) throws SQLException{
         Notify nt = (Notify)consultarUm(notify);
         return nt;
    }
}
