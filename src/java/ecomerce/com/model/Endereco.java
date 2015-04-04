/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.com.model;

/**
 *
 * @author Grimmjhow (Felipe Monteiro)
 */
//imports do programa!

public class Endereco extends EntidadeDominio
{
    private String logradouro;
    private String numero;
    private String cep;
    private String bairro;
    private Cidade cidade;
    private Estado estado;
}
