/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecommerce.core.impl.ViewHelper;

import br.com.ecommerce.core.IViewHelper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Felipe Monteiro
 * Fábrica para retornar os objetos de um Helper
 */
public abstract class FactoryHelper
{
    //lista Hash para conter todos os helpers
    private final static Map<String,IViewHelper> helpers = new HashMap<>();
    
    //esse bloco de codigo carrega quando a classe é carregada!
    static
    {
       
        helpers.put("/Ecommerce/JSP/Cliente/SalvarCliente", new SalvarClienteVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/CPF", new ConsultaCPFVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/Email", new ConsultaEmailVHWeb());
        helpers.put("/Ecommerce/ValidarUsuario", new ValidaUsuarioVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/CEP", new ConsultaCEPVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/AtualizarCliente", new AtualizarClienteVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/AtualizaEmail", new AtualizarEmailVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/AtualizaSenha", new AtualizarSenhaVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/AtualizaCartao", new AtualizarCartaoVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/Imagem", new AlterarImageClienteVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/SalvarPedido", new SalvarPedidoVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/MeusPedidos", new ListaPedidosVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/FiltroAtualizarPedido", new FiltroAtualizarPedidoVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/AtualizarPedido", new AtualizarPedidoClienteVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/DesativarPedido", new ExcluirPedidoVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/ConsultaInscritos", new ConsultaInteressadosVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/ConsultaPedido", new ConsultaPedidoCliente());         
        helpers.put("/Ecommerce/JSP/Cliente/SelecionarPrestador", new FecharPedidoVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/SalvarPrestador", new SalvarPrestadorVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/CPF", new ConsultaCPFVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/Email", new ConsultaEmailVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/AtualizarPrestador", new AtualizarPrestadorVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/CEP", new ConsultaCEPVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/AlterarEmail", new AlterarEmailVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/AlterarSenha", new AlterarSenhaVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/CNPJ", new ConsultaCNPJVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/ExcluirMensagem", new ExcluirMsgVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/ExcluirMensagemFront", new ExcluirMsgFrontVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/ResponderMensagem", new ResponderMensagemVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/CaixaEntrada", new CaixaEntradaVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/Mural", new MuralPedidoVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/Candidatar", new CandidatarVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/Candidaturas", new CandidaturasVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/ConsultarPedido", new ConsultarPedidoVHWeb());  
        helpers.put("/Ecommerce/JSP/PrestadorServico/Consultorias", new PrestadorConsultoriasVHWeb());  
        helpers.put("/Ecommerce/JSP/PrestadorServico/EnviarMensagem", new EnviarMensagemVHWeb());  
        helpers.put("/Ecommerce/JSP/PrestadorServico/MensagemEnviada", new MensagensEnviadasVHWeb()); 
        helpers.put("/Ecommerce/index.jsp", new BuscaGraficosInicialVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/CaixaEntrada", new CaixaEntradaVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/MensagemEnviada", new MensagensEnviadasVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/ExcluirMensagem", new ExcluirMsgVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/ExcluirMensagemFront", new ExcluirMsgFrontVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/ResponderMensagem", new ResponderMensagemVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/EnviarMensagem", new EnviarMensagemVHWeb()); 
        helpers.put("/Ecommerce/JSP/Cliente/MensagemAberta", new AtualizarQtdeMensagemVHWeb()); 
        helpers.put("/Ecommerce/JSP/PrestadorServico/MensagemAberta", new AtualizarQtdeMensagemVHWeb()); 
        helpers.put("/Ecommerce/JSP/Cliente/AtualizarDataHora", new AtualizarDataHoraVHWeb()); 
        helpers.put("/Ecommerce/JSP/Cliente/SalvarTransacao", new SalvarTransacaoVHWeb()); 
        helpers.put("/Ecommerce/JSP/Cliente/RemoverConsultor", new RemoverConsultorVHWeb());
        helpers.put("/Ecommerce/JSP/Cliente/AvaliarConsultor", new SalvarAvaliacaoVHWeb());
        helpers.put("/Ecommerce/JSP/PrestadorServico/Notify", new VideoChatVHWeb()); 
        helpers.put("/Ecommerce/JSP/PrestadorServico/NotifySalvar", new NotifySalvarVHWeb()); 
        helpers.put("/Ecommerce/JSP/Cliente/Notify", new VideoChatVHWeb()); 
        helpers.put("/Ecommerce/JSP/Cliente/NotifySalvar", new NotifySalvarVHWeb()); 
        helpers.put("/Ecommerce/JSP/Cliente/NotifyAtualizar", new NotifyAtualizarVHWeb()); 
        helpers.put("/Ecommerce/JSP/PrestadorServico/NotifyAtualizar", new NotifyAtualizarVHWeb()); 
        helpers.put("/Ecommerce/JSP/PrestadorServico/NotifyConsultar", new NotifyConsultarVHWeb()); 
        helpers.put("/Ecommerce/JSP/Cliente/NotifyConsultar", new NotifyConsultarVHWeb()); 
        helpers.put("/Ecommerce/JSP/HeadHunter/SalvarHeadHunter", new SalvarHeadHunterVHWeb()); 
        helpers.put("/Ecommerce/JSP/HeadHunter/CPF", new ConsultaCPFVHWeb());
        helpers.put("/Ecommerce/JSP/HeadHunter/Email", new ConsultaEmailVHWeb());
        helpers.put("/Ecommerce/JSP/HeadHunter/CEP", new ConsultaCEPVHWeb());
        helpers.put("/Ecommerce/JSP/HeadHunter/CNPJ", new ConsultaCNPJVHWeb());
        helpers.put("/Ecommerce/JSP/HeadHunter/AtualizarHeadHunter", new AtualizarHeadHunterVHWeb());
        helpers.put("/Ecommerce/JSP/HeadHunter/AlterarEmail", new AlterarEmailHeadHunterVHWeb()); 
        helpers.put("/Ecommerce/JSP/HeadHunter/AlterarSenha", new AlterarSenhaHeadHunterVHWeb()); 
        helpers.put("/Ecommerce/JSP/HeadHunter/Ranking", new ConsultarRankingVHWeb());
        helpers.put("/Ecommerce/JSP/HeadHunter/ConsultarUmPrestador", new ConsultarUmPrestadorIVHWeb());
    }
    
    /**
     * Método para retornar uma implementação de IViewHelper
     * @param URL - URL da requisição
     * @return Retorna uma implementação de IViewHelper
     */
    public static IViewHelper getInstance(String URL)
    {
        return helpers.get(URL);
    }
}
