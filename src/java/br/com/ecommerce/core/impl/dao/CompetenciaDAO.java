/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.core.impl.dao.AbstractDAO;
import br.com.ecommerce.domain.Competencia;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.PrestadorServico;
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
public class CompetenciaDAO extends AbstractDAO{

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        openConnection(); //Abrir conexão com banco
        PrestadorServico prestador = (PrestadorServico) entidade;
        PreparedStatement preparador;
        for(Competencia comp: prestador.getHabilidades())
        {
            String sql = "INSERT INTO COMPETENCIAS(DESCRICAO) VALUES(?)";
             try
        {
            conexao.setAutoCommit(false);//setando auto commit para false
            preparador = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//criando caminho para conexao no banco de dados
            //setando parametros do insert
            preparador.setString(1, comp.getDescricao());
            preparador.executeUpdate();//executando a query no banco de dados
            ResultSet resultado = preparador.getGeneratedKeys(); //pegando id da ultima insercao no banco
            if (resultado.next())            //se conseguir interar pelo menos 1 vez
            {//conseguiu iterar
                //end.setId(resultado.getInt(1));
                comp.setId(resultado.getInt(1));
            }
            conexao.commit();//confirmando alteracoes no banco
            String sql2 = "INSERT INTO PRESTADOR_COMPETENCIAS(ID_PRESTADOR,ID_COMPETENCIAS) VALUES(?,?)";
            conexao.setAutoCommit(false);//setando auto commit para false
            preparador = conexao.prepareStatement(sql2);//criando caminho para conexao no banco de dados
            preparador.setInt(1, prestador.getId());
            preparador.setInt(2, comp.getId());
            preparador.execute();
            conexao.commit();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                throw new SQLException();
            } 
        }//for
        conexao.close();
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException {
        openConnection();//Abrir conexão com banco
        PrestadorServico prestador = (PrestadorServico) entidade;
        PreparedStatement preparador;
        for(Competencia comp: prestador.getHabilidades())
        {
            String sql = "UPDATE COMPETENCIAS SET DESCRICAO = ? WHERE ID = ?";  //criando sql para insert no banco
            try
            {
                conexao.setAutoCommit(false);//setando auto commit para false
                preparador = conexao.prepareStatement(sql);//criando caminho para conexao no banco de dados
                //setando parametros do insert
                preparador.setString(1,comp.getDescricao());
                preparador.setInt(2,comp.getId());

                preparador.executeUpdate();//executando a query no banco de dados
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
        
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {
       openConnection();//Abrir conexão com banco
        PrestadorServico prestador = (PrestadorServico) entidade;
        PreparedStatement preparador;
        for(Competencia comp : prestador.getHabilidades())
        {
             try{
                //deletar as competencias ligadas ao prestador 
                String sql2 = "DELETE FROM PRESTADOR_COMPETENCIAS WHERE ID_PRESTADOR = ? AND ID_COMPETENCIAS = ?";
                conexao.setAutoCommit(false);
                preparador = conexao.prepareStatement(sql2);
                preparador.setInt(1, prestador.getId());
                preparador.setInt(2,comp.getId());
                preparador.executeUpdate();
                conexao.commit();
                //deletar competencias
                String sql = "DELETE FROM COMPETENCIAS WHERE ID = ?";
                conexao.setAutoCommit(false);
                preparador = conexao.prepareStatement(sql);
                preparador.setInt(1,comp.getId());
                preparador.executeUpdate();
                conexao.commit();
                }catch (SQLException ex)
                {
                    ex.printStackTrace();
                    throw new SQLException();
                } 
        }
       
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException {
        openConnection();//Abrir conexão com banco
        PrestadorServico prestador = (PrestadorServico) entidade;
        PreparedStatement preparador;
        String sql = "SELECT COMPETENCIAS.ID, COMPETENCIAS.DESCRICAO FROM COMPETENCIAS,PRESTADOR_COMPETENCIAS "
                + "WHERE COMPETENCIAS.ID = PRESTADOR_COMPETENCIAS.ID_COMPETENCIAS\n" +
                 " AND PRESTADOR_COMPETENCIAS.ID_PRESTADOR = ?";
        try{
        conexao.setAutoCommit(false);
        preparador = conexao.prepareStatement(sql);
        preparador.setInt(1,prestador.getId());
        ResultSet  resultado = preparador.executeQuery();
        resultado.next();
        conexao.commit();
        if(resultado.getRow()== 0)
        {
            return null;
        }else
        {
            ArrayList<Competencia> listaComp = new ArrayList<>();
            do{
                Competencia comp = new Competencia();
                comp.setId(resultado.getInt("id"));
                comp.setDescricao(resultado.getString("descricao"));
                listaComp.add(comp);
            }while(resultado.next());
            prestador.setHabilidades(listaComp);
            return prestador;
        }
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
    
    
}
