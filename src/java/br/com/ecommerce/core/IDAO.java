/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core;

import br.com.ecommerce.domain.EntidadeDominio;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Elvin
 * Alterado por Felipe Monteiro
 */
public interface IDAO 
{
    //para salvar qualquer entidade no banco!
    public void salvar(EntidadeDominio entidade) throws SQLException;
    
    //para alterar qualquer entidade no banco!
    public void atualizar(EntidadeDominio entidade) throws SQLException;
    
    //para excluir qualquer entidade no banco!
    public void excluir(EntidadeDominio entidade) throws SQLException;
    
    //para capturar mais de um resultado no banco
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException;
    
    //para capturar apenas um único resultado no banco!
    public EntidadeDominio consultarUm(EntidadeDominio entidade) throws SQLException;
}
