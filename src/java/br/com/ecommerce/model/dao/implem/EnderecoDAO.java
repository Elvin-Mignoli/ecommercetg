/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.model.dao.implem;

import br.com.ecommerce.model.dominio.Endereco;
import br.com.ecommerce.model.dominio.EntidadeDominio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elvin
 */
public class EnderecoDAO extends AbstractDAO{

    @Override
    public boolean salvar(EntidadeDominio entidade) {
        openConnection(); //Abrir conexão com banco
        Endereco end = (Endereco) entidade; 
        PreparedStatement preparador;
        //criando sql para insert no banco
        String sql = "INSERT INTO ENDERECOS(logradouro,numero,bairro,cidade,estado,complemento, cep) VALUES(?,?,?,?,?,?,?);";
        
        try {
            conexao.setAutoCommit(false);//setando auto commit para false
            preparador = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//criando caminho para conexao no banco de dados
            //setando parametros do insert
            preparador.setString(1, end.getLogradouro());
            preparador.setString(2, end.getNumero());
            preparador.setString(3, end.getBairro());
            preparador.setString(4, end.getCidade());
            preparador.setString(5, end.getEstado());
            preparador.setString(6, end.getComplemento());
            preparador.setString(7, end.getCep());
            preparador.executeUpdate();//executando a query no banco de dados
            ResultSet resultado = preparador.getGeneratedKeys(); //pegando id da ultima insercao no banco
            if(resultado.next())            //se conseguir interar pelo menos 1 vez
            {//conseguiu iterar
                end.setId(resultado.getInt(1));
            }
            conexao.commit();//confirmando alteracoes no banco
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
            try 
            {
                conexao.close();
            } catch (SQLException e) {
                return false;
            }
        }
        
    }

    @Override
    public boolean alterar(EntidadeDominio entidade) {
        openConnection();//Abrir conexão com banco
        Endereco end = (Endereco)entidade;
       PreparedStatement preparador;
       String sql = "UPDATE ENDERECOS SET LOGRADOURO = ?, NUMERO = ?, BAIRRO = ?, CIDADE = ?,"
               + " ESTADO = ?,  CEP = ? ,  COMPLEMENTO = ? WHERE ID = ?";  //criando sql para insert no banco
        try {
            conexao.setAutoCommit(false);//setando auto commit para false
            preparador = conexao.prepareStatement(sql);//criando caminho para conexao no banco de dados
            //setando parametros do insert
            preparador.setString(1,end.getLogradouro());
            preparador.setString(2,end.getNumero());
            preparador.setString(3,end.getBairro());
            preparador.setString(4,end.getCidade());
            preparador.setString(5,end.getEstado());
            preparador.setString(6,end.getCep());
            preparador.setString(7,end.getComplemento());
            preparador.setInt(8,end.getId());
            preparador.executeUpdate();//executando a query no banco de dados
            conexao.commit();//confirmando alteracoes no banco
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
            try 
            {
                conexao.close();
            } catch (SQLException e) {
                return false;
            }
        }
    }

    @Override
    public boolean excluir(EntidadeDominio entidade) {
       openConnection();//Abrir conexão com banco
       PreparedStatement preparador;
       String sql = "DELETE FROM ENDERECOS WHERE ID = ?";  //criando sql para insert no banco
        try {
            conexao.setAutoCommit(false);//setando auto commit para false
            preparador = conexao.prepareStatement(sql);//criando caminho para conexao no banco de dados
            //setando parametros do insert
            preparador.setInt(1,entidade.getId());
            preparador.execute();//executando a query no banco de dados
            conexao.commit();//confirmando alteracoes no banco
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
            try 
            {
                conexao.close();
            } catch (SQLException e) {
                return false;
            }
        }
      
    }

    @Override
    public List<EntidadeDominio> consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntidadeDominio autenticar(EntidadeDominio entidadeDominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidadeDominio) {
        openConnection();//Abrir conexão com banco
        Endereco end = (Endereco) entidadeDominio;
        PreparedStatement preparador;
        String sql = "SELECT * FROM ENDERECOS WHERE ID = ?";//criando sql para insert no banco
        try {
            conexao.setAutoCommit(false);//setando auto commit para false
            preparador = conexao.prepareStatement(sql);//criando caminho para conexao no banco de dados
            //setando parametros do insert
            preparador.setInt(1,end.getId());
            ResultSet resultado = preparador.executeQuery();//executando a query no banco de dados
            resultado.next();//posicionar para primeira linha
            if(resultado.getRow()==0) //não encontrou nenhuma linha/row?
            {//não encontrou
                return null;
            }else
            {//encontrou
                end.setBairro(resultado.getString("bairro"));
                end.setCep(resultado.getString("cep"));
                end.setCidade(resultado.getString("cidade"));
                end.setComplemento(resultado.getString("complemento"));
                end.setEstado(resultado.getString("estado"));
                end.setId(resultado.getInt("id"));
                end.setLogradouro(resultado.getString("logradouro"));
                end.setNumero(resultado.getString("numero"));
                return end;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            try 
            {
                conexao.close();
            } catch (SQLException e) {
                return null;
            }
        }
    }

   
}
