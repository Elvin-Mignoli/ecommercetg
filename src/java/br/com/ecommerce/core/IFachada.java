/*
-----------    ATENCAO!!!!!   ---------------
Esse Código é quardado pelo CAPIROTO (sim, esse mesmo)! Qualquer cópia indevida
e você vai ser cobrado pelo CAPA! Então veja bem vai fazer u.u!
*/
package br.com.ecommerce.core;

import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.application.Resultado;

/**
 *
 * @author Felipe Monteiro
 * Interface para a fachada do sistema
 * OBS: Qualquer insercao de um novo método, justificar com comentário acima !
 */
public interface IFachada
{
    //Para Salvar qualquer subclasse de EntidadeDominio pela Fachada!
    public Resultado salvar(EntidadeDominio entidade); 
    
    //Para Alterar qualquer EntidadeDominio pela Fachada!
    public Resultado alterar(EntidadeDominio entidade);
    
    //Para Excluir qualquer EntidadeDominio pela Fachada
    public Resultado excluir(EntidadeDominio entidade);
    
    //Para Consultar qualquer EntidadeDominio pela Fachada
    public Resultado consultar(EntidadeDominio entidade);
}
