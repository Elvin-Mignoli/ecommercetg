/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.core.impl.dao.EnderecoDAO;
import br.com.ecommerce.domain.Endereco;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Elvin
 */
public class TesteEnderecoDAO
{

    public static void main(String[] args) throws SQLException, ParseException
    {
        Endereco end = new Endereco();
        end.setBairro("Jardim Leblon");
        end.setCep("08655151");
        end.setCidade("Suzano");
        end.setComplemento("Casa dos fundos");
        end.setEstado("São Paulo");
        end.setLogradouro("Rua: 2");
        end.setNumero("50");
        end.setId(1);

        IDAO dao = new EnderecoDAO();

        // dao.salvar(end);   
        dao.excluir(end);

        Endereco end2 = new Endereco();
        end2.setId(2);
        end2 = (Endereco) dao.consultarUm(end);
        System.out.println("\nEndereço: " + end2.getLogradouro()
                + "\nNúmero: " + end2.getNumero()
                + "\nBairro: " + end2.getBairro()
                + "\nCidade: " + end2.getCidade()
                + "\nEstado: " + end2.getEstado()
                + "\nCEP: " + end2.getCep());

        Endereco end3 = new Endereco();
        end3.setBairro("Colorado");
        end3.setCep("0864151");
        end3.setCidade("Suzano");
        end3.setComplemento("Bloco B");
        end3.setEstado("São Paulo");
        end3.setLogradouro("Rua: São João");
        end3.setNumero("220");
        end3.setId(2);

            //dao.atualizar(end3);
        DateFormat f = DateFormat.getDateInstance();
        Date data2 = f.parse("12/01/1995");

        System.out.println(data2);
    }
}
