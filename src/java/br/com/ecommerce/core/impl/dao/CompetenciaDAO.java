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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
