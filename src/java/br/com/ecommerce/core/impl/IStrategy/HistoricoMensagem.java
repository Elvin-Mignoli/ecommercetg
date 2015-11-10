/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.IStrategy;

import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.dao.MensagemDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.domain.Mensagem;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Usuario;
import java.sql.SQLException;

/**
 *
 * @author Elvin
 */
public class HistoricoMensagem implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
         Resultado resultado = new Resultado();
        try{
            Usuario usuario = (Usuario ) entidade;
            Mensagem msg = new Mensagem();
            msg.setId_caixa_remetente(usuario.getEntrada().getId());
            if(usuario instanceof PrestadorServico)
             msg.setId_caixa_destinatario(usuario.getPedido().getCliente().getEntrada().getId());
            else if(usuario instanceof Cliente)
                 msg.setId_caixa_destinatario(usuario.getPedido().getPrestadorFinalista().getEntrada().getId());
            msg.setIdPedido(usuario.getPedido().getId());
            MensagemDAO dao = new MensagemDAO();
           
            resultado.setEntidades(dao.historico(msg));
            return resultado;
        }catch (SQLException ex) {
            resultado.setMensagemSimples("Houve um erro no servidor, tente novamente mais tarde");
            return resultado;
        }
        
    }
    
}
