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
import java.sql.Connection;
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

    public CompetenciaDAO() {
    }

    public CompetenciaDAO(Connection conexao) {
        super(conexao);
    }

    
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        try
        {
            if (conexao == null || conexao.isClosed())//Abrir conexão com banco
                {
                    openConnection();
                    conexao.setAutoCommit(false);//setando auto commit para false
                }
            PrestadorServico prestador = (PrestadorServico) entidade;
            for(Competencia comp: prestador.getHabilidades())
            {
                String sql = "INSERT INTO COMPETENCIAS(DESCRICAO) VALUES(?)";


                pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//criando caminho para conexao no banco de dados
                //setando parametros do insert
                pst.setString(1, comp.getDescricao());
                pst.executeUpdate();//executando a query no banco de dados
                ResultSet resultado =  pst.getGeneratedKeys(); //pegando id da ultima insercao no banco
                if (resultado.next())            //se conseguir interar pelo menos 1 vez
                {//conseguiu iterar
                    //end.setId(resultado.getInt(1));
                    comp.setId(resultado.getInt(1));
                }

                String sql2 = "INSERT INTO PRESTADOR_COMPETENCIAS(ID_PRESTADOR,ID_COMPETENCIAS) VALUES(?,?)";

                pst = conexao.prepareStatement(sql2);//criando caminho para conexao no banco de dados
                pst.setInt(1, prestador.getId());
                pst.setInt(2, comp.getId());
                pst.executeUpdate();
            }//for
         } catch (SQLException ex)
         {
            ex.printStackTrace();
            throw new SQLException();
         } 
        
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException {
         try
        {
            if (conexao == null || conexao.isClosed())
            {
                openConnection();//Abrir conexão com banco
            }
            PrestadorServico prestador = (PrestadorServico) entidade;
            for(Competencia comp: prestador.getHabilidades())
            {
                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE COMPETENCIAS SET ");
                sql.append("DESCRICAO = ? ");
                sql.append("WHERE ID = ?");  //criando sql para insert no banco

                pst = conexao.prepareStatement(sql.toString());//criando caminho para conexao no banco de dados
                //setando parametros do insert
                pst.setString(1, comp.getDescricao());
                pst.setInt(2, comp.getId());

                pst.executeUpdate();//executando a query no banco de dados
                
            }///for
            // O Commite é feito por outra Classe DAO (Cliente ou PrestadorServico)
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
            throw new SQLException();
        }
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {
        if(conexao == null || conexao.isClosed())
        {
            openConnection();//Abrir conexão com banco
            conexao.setAutoCommit(false);
        }
        PrestadorServico prestador = (PrestadorServico) entidade;
        for(Competencia comp : prestador.getHabilidades())
        {
             try{
                //deletar as competencias ligadas ao prestador 
                String sql2 = "DELETE FROM PRESTADOR_COMPETENCIAS WHERE ID_PRESTADOR = ? AND ID_COMPETENCIAS = ?";
               
                pst = conexao.prepareStatement(sql2);
                pst.setInt(1, prestador.getId());
                pst.setInt(2,comp.getId());
                pst.executeUpdate();
                
                //deletar competencias
                String sql = "DELETE FROM COMPETENCIAS WHERE ID = ?";
                conexao.setAutoCommit(false);
                pst = conexao.prepareStatement(sql);
                pst.setInt(1,comp.getId());
                pst.executeUpdate();
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
       try{
            if(conexao == null || conexao.isClosed())
             {
                    openConnection();//Abrir conexão com banco
                    conexao.setAutoCommit(false);
             }
            PrestadorServico prestador = (PrestadorServico) entidade;

            String sql = "SELECT COMPETENCIAS.ID, COMPETENCIAS.DESCRICAO FROM COMPETENCIAS,PRESTADOR_COMPETENCIAS "
                    + "WHERE COMPETENCIAS.ID = PRESTADOR_COMPETENCIAS.ID_COMPETENCIAS\n" +
                     " AND PRESTADOR_COMPETENCIAS.ID_PRESTADOR = ?";


            pst = conexao.prepareStatement(sql);
            pst.setInt(1,prestador.getId());
            ResultSet  resultado = pst.executeQuery();
            resultado.next();

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
            } 
    }
    
    public EntidadeDominio consultarCometencia(){return null;}
    public EntidadeDominio salvarCompetencia_prestador(){return null;}
    
}
