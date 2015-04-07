/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ecommerce.model.dao;

import com.br.ecommerce.model.dominio.EntidadeDominio;
import java.util.List;

/**
 *
 * @author Elvin
 */
public interface IDAO {
    public boolean salvar(EntidadeDominio entidade);

    public boolean alterar(EntidadeDominio entidade);

    public boolean excluir(EntidadeDominio entidade);

    public List<EntidadeDominio> consultar();
    
    public EntidadeDominio autenticar(EntidadeDominio entidadeDominio);
    
    public EntidadeDominio consultarUm(EntidadeDominio entidadeDominio);
}
