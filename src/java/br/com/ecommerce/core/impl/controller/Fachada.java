package br.com.ecommerce.core.impl.controller;

import br.com.ecommerce.core.IFachada;
import br.com.ecommerce.domain.EntidadeDominio;
import br.com.ecommerce.application.Resultado;
import br.com.ecommerce.core.IDAO;
import br.com.ecommerce.core.IStrategy;
import br.com.ecommerce.core.impl.IStrategy.ConsultaSaldo;
import br.com.ecommerce.core.impl.IStrategy.ExisteCliente;
import br.com.ecommerce.core.impl.IStrategy.ExistePrestador;
import br.com.ecommerce.core.impl.IStrategy.IdentificarDocumento;
import br.com.ecommerce.core.impl.IStrategy.ValidaCPF;
import br.com.ecommerce.core.impl.IStrategy.ValidaCartaoCredito;
import br.com.ecommerce.core.impl.IStrategy.ValidaHabilidadePedido;
import br.com.ecommerce.core.impl.dao.CaixaEntradaDAO;
import br.com.ecommerce.core.impl.dao.CartaoCreditoDAO;
import br.com.ecommerce.core.impl.dao.ClienteDAO;
import br.com.ecommerce.core.impl.dao.InteressadoDAO;
import br.com.ecommerce.core.impl.dao.PedidoDAO;
import br.com.ecommerce.core.impl.dao.PrestadorServicoDAO;
import br.com.ecommerce.core.impl.dao.TransacaoDAO;
import br.com.ecommerce.domain.CaixaEntrada;
import br.com.ecommerce.domain.CartaoCredito;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.Interessado;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.PrestadorServico;
import br.com.ecommerce.domain.Transacao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Fachada Generica para encapsular todos os métodos do sistema!
 * @author Felipe Monteiro
 */
public class Fachada implements IFachada
{

    //atributos da fachada!
    private Map<String, Map<String, List<IStrategy>>> rns;
    private Map<String, IDAO> daos;
    private Resultado resultado;
    /*
     Metodo Construtor da Fachada!
     */

    public Fachada()
    {
        rns = new HashMap<>();
        daos = new HashMap<>(); //notacao diamante!
        //populando o hash de DAO!
        daos.put(Cliente.class.getName(), new ClienteDAO());
        daos.put(PrestadorServico.class.getName(), new PrestadorServicoDAO());
        daos.put(CartaoCredito.class.getName(), new CartaoCreditoDAO());
        daos.put(CaixaEntrada.class.getName(),new CaixaEntradaDAO());
        daos.put(Pedido.class.getName(), new PedidoDAO()); 
        daos.put(Interessado.class.getName(), new InteressadoDAO());
        daos.put(Transacao.class.getName(), new TransacaoDAO());
        /* 
         Lista de Regras de negocio! 
         */
        // --> Regras de Negocio SalvarCliente!
        List<IStrategy> rnsSalvarCliente = new ArrayList<>();
        rnsSalvarCliente.add(new ValidaCPF());
        rnsSalvarCliente.add(new ExisteCliente());
        
        Map<String, List<IStrategy>> rnsCliente = new HashMap<>();   //Mapa de regras!
        rnsCliente.put("Salvar", rnsSalvarCliente);
        //Fim das regras do Cliente
        
        // --> Regras de Negócio AtualizarCartao!
        List<IStrategy> rnsAtualizarCartao = new ArrayList<>();
        //rnsAtualizarCartao.add(new ValidaCartaoCredito());
        
        Map<String,List<IStrategy>> rnsCartao = new HashMap<>();
        rnsCartao.put("Atualizar", rnsAtualizarCartao);
        //Fim das regras do Cartão!
        
        //--> Regras de Negocio SalvarPrestador!
        List<IStrategy> rnsSalvarPrestador = new ArrayList<>();
        rnsSalvarPrestador.add(new IdentificarDocumento());  // verificar depois
        rnsSalvarPrestador.add(new ExistePrestador());
        
        Map<String, List<IStrategy>> rnsPrestador = new HashMap<>();   //Mapa de regras!
        rnsPrestador.put("Salvar", rnsSalvarPrestador);
        //Fim das regras do Prestador de serviço
        
        // --> Regras de Negócio SalvarPedido
        List<IStrategy> rnsSalvarPedido = new ArrayList<>();
        rnsSalvarPedido.add(new ValidaHabilidadePedido());
        
        //Map para regras do Pedido!
        Map<String,List<IStrategy>> rnsPedido = new HashMap<>();
        rnsPedido.put("Salvar", rnsSalvarPedido);
        
        //Lista com Regras para salvar uma transacao
        List<IStrategy> rnsSalvarTransacao = new ArrayList<>();
        rnsSalvarTransacao.add(new ConsultaSaldo());
        
        //Map para regras da Transacao
        Map<String,List<IStrategy>> rnsTransacao = new HashMap<>();
        rnsTransacao.put("Salvar", rnsSalvarTransacao);
        
        //Map final com todas as regras separados por operacao!
        rns.put(Cliente.class.getName(), rnsCliente);
        rns.put(PrestadorServico.class.getName(), rnsPrestador);
        rns.put(CartaoCredito.class.getName(), rnsCartao);
        rns.put(Pedido.class.getName(), rnsPedido);
        rns.put(Transacao.class.getName(), rnsTransacao);
    }

    @Override
    public Resultado salvar(EntidadeDominio entidade)
    {
        resultado = executaRegras(entidade,"Salvar");

        if (!resultado.getMensagens().isEmpty())
            return resultado;
        
        IDAO dao = daos.get(entidade.getClass().getName());
        
        try
        {
            dao.salvar(entidade);   //conseguiu salvar a entidade
            
            return resultado;       //retorna o resultado sem uma lista de Mensagens!
        } 
        catch (SQLException ex) //--> Algum erro grave ocorreu!
        {
            ex.printStackTrace();
            resultado.addMensagens("Erro ao processar a requisição");
            return resultado;
        }
    }

    @Override
    public Resultado alterar(EntidadeDominio entidade)
    {
        resultado = executaRegras(entidade, "Atualizar");
        
        if(!resultado.getMensagens().isEmpty())
            return resultado;
        
        try
        {
          IDAO dao = daos.get(entidade.getClass().getName());
          
          dao.atualizar(entidade);
          
          return resultado; 
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            resultado.addMensagens("Erro ao processar a requisição");
            return resultado;
        }
    }

    @Override
    public Resultado excluir(EntidadeDominio entidade)
    {
        resultado = executaRegras(entidade, "Excluir");
        
        if(!resultado.getMensagens().isEmpty())
            return resultado;
        
        try
        {
            IDAO dao = daos.get(entidade.getClass().getName());
            
            dao.excluir(entidade);
            
            return resultado;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            resultado.addMensagens("Erro ao processar a requisição");
            return resultado;
        }
    }

    @Override
    public Resultado consultar(EntidadeDominio entidade)
    {
        resultado = executaRegras(entidade, "Consultar");
        
        if(!resultado.getMensagens().isEmpty())
            return resultado;
        
        try
        {
            IDAO dao = daos.get(entidade.getClass().getName());
            
            List<EntidadeDominio> entidades = dao.consultar(entidade);
            resultado.setEntidades(entidades);
            return resultado;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            resultado.addMensagens("Erro ao processar a requisição");
            return resultado;
        }
    }

    /**
     * Executa cada regra de negocio especifica de cada entidadeDominio
     *
     * @param entidade SubClasse de EntidadeDomino
     * @param operacao Operacao para Validacao
     * (Salvar,Alterar,Consultar,Deletar)
     * @return Null se tudo deu certo ou uma String preenchida!
     */
    public Resultado executaRegras(EntidadeDominio entidade, String operacao)
    {
        Resultado resultado = new Resultado();
        String nameClass = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();

        Map<String, List<IStrategy>> regrasOperacao = rns.get(nameClass);

        if (regrasOperacao == null)  //nao existem regras para essa entidade?
        {
            return resultado;
        }

        if (!regrasOperacao.isEmpty())   //lista nao eh vazia?
        {//Sim!
            List<IStrategy> regras = regrasOperacao.get(operacao);
            
            if(regras == null || regras.isEmpty())
                return resultado;
            
            for (IStrategy s : regras)
            {
                resultado = s.processar(entidade);

                if (!resultado.getMensagens().isEmpty())
                {
                    return resultado;
                }
            }
        }
        return resultado;
    }
}
