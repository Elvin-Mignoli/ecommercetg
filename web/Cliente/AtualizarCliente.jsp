<%-- 
    Document   : ClienteAtualizar
    Created on : 26/05/2015, 13:34:32
    Author     : Elvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="/EcomerceTG/js/libs/jquery-1.11.1.min.js"></script>
<script src="/EcomerceTG/js/libs/jquery-ui.min.js"></script>
<script src="/EcomerceTG/js/libs/jquery.maskedinput.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualizar Cliente</title>
        <script>
            $(document).ready(function() {
                $("#data").mask("99/99/9999");
                $("#telefone").mask("(99)9999-9999");
                $("#celular").mask("(99)99999-9999");
                $("#cep").mask("99999-999");
                $("#input_cpf").mask("99999999999");
                
                if ($("#estado").val().length > 1)
                {
                    $("#numero").focus();
                }
                $("#numero").blur(function() {
                    $("#input_nome").focus();
                });

                
            });
        </script>
    </head>
    <body>
       <div class="div_bloco_comum">
        <fieldset id="total">
          <legend><span>Altualizar dados do cliente</span></legend>
            <form id="form_atualizar" method="POST" action="AtualizarCliente">
                <input type="text" hidden="true" value="Atualizar" name="operacao"
                <fieldset class="pessoais">
                        <legend class="legend_comum">Informações pessoais</legend>
                        <table>
                            <tr>
                                <td><label for="input_nome">Nome</label></td>
                                <td><input type="text" name="txtNome" id="input_nome" placeholder="Nome" 
                                           required="true" min="3" value="${sessionScope.cliente.nome}"/></td>
                            </tr>
                            <tr>
                                <td><label for="input_sobrenome">Sobrenome</label></td>
                                <td><input type="text" name="txtSobrenome" id="input_sobrenome" placeholder="Sobrenome" 
                                           required="true" min="3" value="${sessionScope.cliente.sobrenome}" /></td>
                            </tr>
                            <tr>
                                <td><label for="sexo">Sexo</label></td>
                                <td><select id="sexo" name="txtSexo" required="true">
                                        <option value="Não especificado">Prefiro não especificar</option>
                                        <option value="Masculino">Masculino</option>
                                        <option value="Feminino">Feminino</option>                                
                                    </select>
                                </td>
                            </tr>
                            
                            <tr>
                                <td><label for="input_cpf">Cpf</label></td>
                                <td><input type="text" name="txtCpf" id="input_cpf" placeholder="CPF"
                                           required="true" min="3" value="${sessionScope.cliente.cpf}"/></td>
                            </tr>
                            <tr>
                                <td><label for="data">Data de nascimento</label></td>
                                <td><input type="text" id="data" name="txtDatanascimento" placeholder="Data de nascimento" 
                                           required="true" maxlength="10" value="${sessionScope.cliente.dataNascimento}"/></td>
                            </tr>

                            <tr>
                                <td><label for="telefone">Telefone</label></td>
                                <td><input type="tel" id="telefone" name="txtTelefone" placeholder="Telefone"
                                           required="true" value="${sessionScope.cliente.contato.telefone}"/></td>
                            </tr>
                              <tr>
                            <td><label for="celular">Celular</label></td>
                            <td><input type="cel" id="celular" name="txtCelular" placeholder="Celular" value="${sessionScope.cliente.contato.celular}"></td>
                            </tr>
                        </table>
                      
            </fieldset>
                    <fieldset id="endereco_layout" class="pessoais">
                        <legend class="legend_comum">Endereco</legend>
                        <table>
                            <tr>
                                <td><label for="cep">CEP</label></td>
                                <td><input type="text" id="cep" name="txtCep" placeholder="Insira CEP" 
                                           value="${sessionScope.cliente.endereco.cep}"required="true"/></td>
                                </tr>
                                <tr>
                                    <td><label for="rua">Logradouro</label></td>
                                    <td><input type="text" id="rua" name="txtRua" placeholder="Rua, Logradouro, Avenida"  
                                               required="true"   value="${sessionScope.cliente.endereco.logradouro}"/></td>
                                <td><label for="numero">N°</label><input type="number" max="10000" name="textNumero" id="numero" 
                                                                         required="true"value="${sessionScope.cliente.endereco.numero}"/></td>
                            </tr>
                            <tr>
                                <td><label for="bairro">Bairro</label></td>
                                <td><input type="text" id="bairro" name="txtBairro"  placeholder="Bairro" 
                                           required="true" value="${sessionScope.cliente.endereco.bairro}"/></td>
                            </tr>
                            <tr>
                                <td><label for="cidade">Cidade</label></td>
                                <td><input type="text" id="cidade" name="txtCidade" placeholder="Cidade" 
                                           required="true"  value="${sessionScope.cliente.endereco.cidade}"/></td>
                            </tr>
                            <tr>
                                <td><label for="estado">Estado</label></td>
                                <td><input type="text" id="estado" name="txtEstado"  placeholder="Estado"
                                           required="true"   value="${sessionScope.cliente.endereco.estado}"/></td>
                            </tr>
                            <tr>
                                <td><label for="completmento">Complemento</label></td>
                                <td><imput type="text" id="complemento" name="txtComplemento" placeholder="Complemento"
                                           required="true"  value="${sessionScope.cliente.endereco.complemento}"/></td>
                            </tr>
                        </table>
                    </fieldset>
                    
                    <fieldset class="pessoais">
                        <legend class="legend_comum">Informações de login</legend>
                        <table>                         
                            <tr>
                                <td><label for="input_email">Email</label></td>
                                <td><input type="email" name="txtEmail" id="input_email" placeholder="E-mail válido" 
                                           required="true"  value="${sessionScope.cliente.email}"/></td>
                            </tr>
                            <tr>
                                <td><label for="input_senha">Senha</label></td>
                                <td><input type="password" name="txtSenha" id="input_senha" placeholder="Senha" 
                                           required="true"   value="${sessionScope.cliente.senha}"/></td>
                            </tr>
                        </table>
                    </fieldset>
                    <p><input type="submit" value="Atualizar" id="button_cadastrar" name="bAtualizar"/></p>
                </form>
            </fieldset>
            <c:if test="${requestScope.MsgAtualizarCliente != null}">
              <table>
                <c:forEach items="${requestScope.MsgAtualizarCliente}" var="lista">
                    <tr>
                        <span>${lista.livro_id}</span>
                        <span>${lista.data_emp.time}</span>
                        <span>${requestScope.mensal.mes}</span>
                        <span>${requestScope.mensal.ano}</span>
                    </tr>
                </c:forEach>
              </table>
            </c:if>
        </div>
    </body>
</html>

