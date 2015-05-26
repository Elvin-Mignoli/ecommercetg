<%-- 
    Document   : ClienteExcluir
    Created on : 26/05/2015, 13:53:44
    Author     : Elvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Excluir Cliente</title>
    </head>
    <body>
         <div class="div_bloco_comum">
            <fieldset>
                <legend>Excluir minha conta</legend>
                <form action="ExcluirClienteVHWeb" method="post">
                    <input type="text" hidden="true" value="Excluir" name="operacao"
                    <table>
                        <center>
                            <span align="center"> Atenção !!!</span>
                        </center>
                        <fieldset>
                            <center>
                                <span>Prezado usuário, ao excluir sua conta todos os seus dados serão arquivados no sistema e podem ser recuperados quando desejar reativar suas atividades no site. 
                                    <br>Caso queira excluir sua conta definitivamente, por favor entre em contato com nosso helpdesk.
                                </span>
                            </center>      
                        </fieldset>
                    </table>
                </form>
            </fieldset>
             <center>
                <form id="form_excluir" method="POST" action="ExcluirCliente">
                    <p><input type="submit" value="Excluir" id="button_cadastrar" name="bExcluir"/></p>
                </form>
                <button onclick="window.location='/EcomerceTG/index.jsp';">Cancelar</button>
            </center> 
        </div>
    </body>
</html>
