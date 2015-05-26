<%-- 
    Document   : ClienteConsultar
    Created on : 26/05/2015, 15:05:44
    Author     : Elvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dados da conta</title>
    </head>
    <body>
        <div class="div_bloco_comum">
            <fieldset>
                <fieldset id="meusdados">
                    <legend>Informações Pessoais</legend>
                        <table>
                            <tr>
                                <th align="left">Nome: </th>
                                <td>${sessionScope.cliente.nome}</td> 
                            </tr>
                            <tr>
                                <th align="left">Sobrenome:</th>
                                <td>${sessionScope.cliente.sobrenome}</td>
                            </tr>
                            <tr>
                                <th align="left">Sexo:</th>
                                <td>${sessionScope.cliente.sexo}</td>
                            </tr>
                            <tr>
                                <th align="left">CPF:</th>
                                <td>${sessionScope.cliente.cpf}</td>
                            </tr>
                            <tr>
                                <th align="left">Data de nascimento:</th>
                                <td>${sessionScope.cliente.dataNascimento}</td>
                            </tr>
                            <tr>
                                <th align="left">Telefone:</th>
                                <td>${sessionScope.cliente.telefone}</td>
                            </tr>
                            <tr>
                                <th align="left">Celular:</th>
                                <td>${sessionScope.cliente.celular}</td>
                            </tr>
                        </table>
                </fieldset>
                <fieldset>
                    <legend>Endereço</legend>
                    <table>
                        <tr>
                            <th align="left">Logadouro:</th>
                            <td>${sessionScope.cliente.endereco.logradouro}</td>   
                        </tr>
                        <tr>
                            <th align="left">numero:</th>
                            <td>${sessionScope.cliente.endereco.numero}</td>   
                        </tr>
                        <tr>
                            <th align="left">Cep:</th>
                            <td>${sessionScope.cliente.endereco.cep}</td>   
                        </tr>
                        <tr>
                            <th align="left">Bairro:</th>
                            <td>${sessionScope.cliente.endereco.bairro}</td>   
                        </tr>
                        <tr>
                            <th align="left">Cidade:</th>
                            <td>${sessionScope.cliente.endereco.cidade}</td>   
                        </tr>
                        <tr>
                            <th align="left">Estado:</th>
                            <td>${sessionScope.cliente.endereco.estado}</td>   
                        </tr>
                        <tr>
                            <th align="left">Complemento:</th>
                            <td>${sessionScope.cliente.endereco.complemento}</td>   
                        </tr>
                    </table>
                </fieldset>     
                        <fieldset>
                    <legend>Informações de Login</legend>
                    <table>
                        <tr>
                            <th align="left">E-mail:</th>
                            <td>${sessionScope.cliente.endereco.email}</td>   
                        </tr>
                        <tr>
                            <th align="left">Senha:</th>
                            <td>${sessionScope.cliente.endereco.Senha}</td>   
                        </tr>
                       
                    </table>
                </fieldset>     
            </fieldset>
        </div>
    </body>
</html>
