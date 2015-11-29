/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.dao;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.domain.CaixaEntrada;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.HeadHunter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class HeadHunterDAO extends AbstractDAO{

    public HeadHunterDAO() {
    }

    public HeadHunterDAO(Connection conexao) {
        super(conexao);
    }
    

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
       HeadHunter head = (HeadHunter) entidade;
       
       try{
           if(conexao == null || conexao.isClosed())
           {
               openConnection();
               conexao.setAutoCommit(false);
           }
           
            //salvando dados do Endereco!
            IDAO dao = new EnderecoDAO(conexao);
            
            dao.salvar(head.getEndereco());

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO HEADHUNTERS");
            sql.append("(NOME,SOBRENOME,NOME_EMPRESA,CPF,CNPJ,ID_ENDERECO)");
            sql.append(" VALUES(?,?,?,?,?,?)");

            pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, head.getNome());
            pst.setString(2, head.getSobrenome());
            pst.setString(3, head.getNome_empresa());
            pst.setString(4, head.getCpf());
            pst.setString(5, head.getCnpj());
            pst.setInt(6, head.getEndereco().getId());

            pst.executeUpdate();
            ResultSet resultado = pst.getGeneratedKeys(); //pegando id da ultima insercao no banco

            if (resultado.next())            //se conseguir interar pelo menos 1 vez
            {//conseguiu iterar
                entidade.setId(resultado.getInt("id"));
            }
           //salvando dados de Login!
            dao = new AutenticarDAO(conexao);
            
            dao.salvar(head);         
            
            //salvando dados da caixa de caixa
            dao = new CaixaEntradaDAO(conexao);
            
            dao.salvar(new CaixaEntrada(null, null,head));
           conexao.commit();
           
       }catch(Exception e){
           
            try
            {
                conexao.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
            e.printStackTrace();
            throw new SQLException(e);
        } finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            } catch (SQLException e)
            {
                throw new SQLException();
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException {
       HeadHunter head = (HeadHunter) entidade;
       
       try{
            if(conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);
            }
           
            IDAO dao = new EnderecoDAO(conexao);

            dao.atualizar(head.getEndereco());   //atualizando dados do endereco!
           
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE HEADHUNTERS SET ");
            sql.append("NOME = ?, ");
            sql.append("SOBRENOME = ?, ");
            sql.append("NOME_EMPRESA = ?, ");
            sql.append("TELEFONE = ?, ");
            sql.append("CELULAR = ? , ");
            sql.append("DATA_NASCIMENTO = ?, ");
            sql.append("SEXO = ? ");
            sql.append("WHERE ID = ?");
            
            pst = conexao.prepareStatement(sql.toString());
            pst.setString(1,head.getNome() );
            pst.setString(2,head.getSobrenome() );
            pst.setString(3, head.getNome_empresa());
            pst.setString(4,head.getContato().getTelefone() );
            pst.setString(5, head.getContato().getCelular());
             if(head.getDataNascimento()!= null)
                    pst.setDate(6, new Date(head.getDataNascimento().getTime()));
                else
                    pst.setDate(6, null);
            pst.setString(7, head.getSexo());
            pst.setInt(8,head.getId() );
            
            pst.executeUpdate();
            
            conexao.commit();
       }catch(Exception e){
           
            try
            {
                conexao.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
            e.printStackTrace();
            throw new SQLException(e);
        } finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            } catch (SQLException e)
            {
                throw new SQLException();
            }
        }
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        
        ArrayList<EntidadeDominio> lista = new ArrayList<>();
        try{
            if(conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);
            }
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT HEADHUNTERS.* FROM HEADHUNTERS, ENDERECOS ");
            sql.append(" WHERE HEADHUNTERS.ID_ENDERECO = ENDERECOS.ID ");
            
            pst = conexao.prepareStatement(sql.toString());
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                HeadHunter head = new HeadHunter();
                head.setCnpj(rs.getString("cnpj"));
                head.setContato(new Contato());
                head.getContato().setTelefone(rs.getString("telefone"));
                head.getContato().setCelular(rs.getString("celular"));
                head.setCpf(rs.getString("cpf"));
                //resgatando o endereço
                EnderecoDAO endDao = new EnderecoDAO(conexao);
                Endereco end = new Endereco();
                end.setId(rs.getInt("id_endereco"));
                head.setEndereco((Endereco) endDao.consultarUm(end));
                
                head.setNome(rs.getString("nome"));
                head.setSobrenome(rs.getString("sobrenome"));
                head.setNome_empresa(rs.getString("nome_empresa"));
                head.setSexo(rs.getString("sexo"));
                head.setId(rs.getInt("id"));
                head.setDataNascimento(rs.getDate("data_nascimento"));
                
                
                lista.add(head);
            }
            return lista;
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException(e);
        } finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            } catch (SQLException e)
            {
                throw new SQLException();
            }
        }
    }

    @Override
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException {
   
        HeadHunter head = (HeadHunter) entidade;
        try{
            if(conexao == null || conexao.isClosed())
            {
                openConnection();
                conexao.setAutoCommit(false);
            }
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM HEADHUNTERS ");
            sql.append(" WHERE ID = ? ");
            
            pst = conexao.prepareStatement(sql.toString());
            pst.setInt(1, head.getId());
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.getRow() == 0)
            {
                return null;
            }else
            {
                
                head.setCnpj(rs.getString("cnpj"));
                head.setContato(new Contato());
                head.getContato().setTelefone(rs.getString("telefone"));
                head.getContato().setCelular(rs.getString("celular"));
                head.setCpf(rs.getString("cpf"));
                //resgatando o endereço
                EnderecoDAO endDao = new EnderecoDAO(conexao);
                Endereco end = new Endereco();
                end.setId(rs.getInt("id_endereco"));
                head.setEndereco((Endereco) endDao.consultarUm(end));
                
                head.setNome(rs.getString("nome"));
                head.setSobrenome(rs.getString("sobrenome"));
                head.setNome_empresa(rs.getString("nome_empresa"));
                head.setSexo(rs.getString("sexo"));
                head.setId(rs.getInt("id"));
                head.setDataNascimento(rs.getDate("data_nascimento"));
            
                //recuperar caixa d entrada
                 //recuperar a caixa de caixa
                CaixaEntradaDAO entradaDAO = new CaixaEntradaDAO(conexao);
                CaixaEntrada caixa = new CaixaEntrada(null,null,head);
                caixa = (CaixaEntrada)entradaDAO.consultarUm(caixa);
                head.setEntrada(caixa);
                
               return head;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException(e);
        } finally
        {
            try
            {
                if(transaction)
                    conexao.close();
            } catch (SQLException e)
            {
                throw new SQLException();
            }
        }
    }
    
     //Método para identificar se já existe um prestador com um determinado CNPJ
    public EntidadeDominio consultaHeadHunterCNPJ(EntidadeDominio entidade) throws SQLException
    {
        try
        {
           HeadHunter prestador = (HeadHunter) entidade;
            
           if(conexao == null || conexao.isClosed()) 
              openConnection();
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT NOME FROM HEADHUNTERS  ");
            sql.append("WHERE CNPJ = ? ");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, prestador.getCnpj());
                        
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())   //retornou algum cliente?
            {
                return prestador;
            }
            else
            {
                return null;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex);
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
    
     //Método para identificar se já existe um prestador com um determinado CNPJ
    public EntidadeDominio consultaHeadHunterCPF(EntidadeDominio entidade) throws SQLException
    {
        try
        {
           HeadHunter prestador = (HeadHunter) entidade;
            
           if(conexao == null || conexao.isClosed()) 
              openConnection();
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT NOME FROM HEADHUNTERS ");
            sql.append("WHERE CPF = ? ");
            
            pst = conexao.prepareStatement(sql.toString());
            
            pst.setString(1, prestador.getCpf());
                        
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())   //retornou algum cliente?
            {
                return prestador;
            }
            else
            {
                return null;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex);
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
