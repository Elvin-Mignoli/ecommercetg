package br.com.ecommerce.core.impl.command;

import br.com.ecommerce.core.ICommand;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Felipe Monteiro
 * Fábrica para retornar a instância de um Command
 */
public abstract class FactoryICommand
{
    private static Map<String,ICommand> cmd = new HashMap<>(); //lista para conter os commdes específicos
    
    //esse bloco de codigo é executado quando a classe é carregada!
    static
    {
        cmd.put("Salvar", new CommandSalvar());
        cmd.put("Atualizar", new CommandAtualizar());
        cmd.put("Excluir", new CommandExcluir());
        cmd.put("Consultar", new CommandConsultar());
    }
    
    //retorna um dos commands da lista dependendo do tipo da operacao
    public static ICommand getCommand(String operacao)
    {
        return cmd.get(operacao);
    }
}
