/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.testes;

import br.com.ecommerce.core.impl.dao.PrestadorServicoDAO;
import br.com.ecommerce.domain.Competencia;
import br.com.ecommerce.domain.Contato;
import br.com.ecommerce.domain.Endereco;
import br.com.ecommerce.domain.PrestadorServico;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Elvin
 */
public class TestePrestadorDAO {
    public static void main(String[]args) throws ParseException, SQLException
    {
        //slavar
        Endereco end = new Endereco("RUA:PEDRO SEGUNDO", "50", "08655152","COLORADO" , "SUZANO", "SP", "CASA DOS FUNDOS");
        Contato contato = new Contato("47474747", "996147855");
        ArrayList<Competencia> lista = new ArrayList<>();
        Competencia comp = new Competencia("Java Web");
        lista.add(comp);
        comp= new Competencia("ITL");
        lista.add(comp);
        PrestadorServico prestador = new PrestadorServico("Joao", "Silva", "39698654845", end, contato, "M", "1992/05/11",
                "Prestador", "joao@gmail.com", "123", lista);
        PrestadorServicoDAO prestDAO = new PrestadorServicoDAO();
        prestDAO.salvar(prestador);
    }
}
