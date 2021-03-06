<%-- 
    Document   : ClienteSalvar
    Created on : 26/05/2015, 12:26:40
    Author     : Felipe Monteiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<script src="../../js/libs/jquery-1.11.1.min.js"></script>
<script src="../../js/libs/jquery-ui.min.js"></script>
<script src="../../js/libs/jquery.maskedinput.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Cadastrar Cliente</title>
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        
        <!-- implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>

        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <script src="../../js/ajaxFuntions.js"></script>
        <script>
            $(document).ready(function () {
                //$("#data").mask("99/99/9999");
                $("#telefone").mask("(99)9999-9999");
                $("#celular").mask("(99)99999-9999");
                $("#cep").mask("99999-999");
                $("#input_cpf").mask("999.999.999-99");

                if ($("#estado").val().length > 1)
                {
                    $("#numero").focus();
                }
                $("#numero").blur(function () {
                    $("#input_nome").focus();
                });
            });
        </script>
    </head>
    <body>
        <div class="page-header text-center">
            <h1>Cadastro de Clientes</h1>
        </div>
        <!-- DIV CONTAINER DA APLICAÇÂO -->
        <div class="container">
            <form id="form_cadastro" method="POST" action="SalvarCliente">
                <div class="row">
                    <input type="text" hidden="true" name="operacao" value="Salvar"/>
                    <div class="panel panel-success col-lg-5">
                        <div class="panel panel-heading text-center">Informações Pessoais</div>
                        <div class="panel-body">
                            <!-- Conteudo dos Panels! -->
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Nome</span>
                                    <input type="text" required="required" name="txtNome" class="form-control" placeholder="Digite seu Nome" value="${requestScope.cliente.nome}"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Sobrenome</span>
                                    <input type="text" name="txtSobrenome" class="form-control" placeholder="Digite seu Sobrenome" required="required" value="${requestScope.cliente.sobrenome}"/>
                                </div>
                            </div>

                            <div class="form-group" id="div_cpf">
                                <div class="input-group">
                                    <span class="input-group-addon">CPF</span>
                                    <input type="text" name="txtCpf" class="form-control" id="input_cpf" required="required" value="${requestScope.cliente.cpf}" onchange="validaCPF()"/>
                                </div>
                                <span id="span_cpf">

                                </span>
                            </div>
                            <div id="statusCPF">

                            </div>
                        </div>
                    </div>

                    <div class="col-sm-1"></div> <!-- Um pequeno SPAN -->

                    <!-- -------------------------------------------- -->
                    <div class="panel panel-primary col-lg-5">
                        <div class="panel panel-heading text-center">Informações de Login</div>
                        <div class="panel-body">
                            
                            <div id="statusEmail">

                            </div>
                            <div class="form-group" id="div_email">
                                <div class="input-group">
                                    <span class="input-group-addon">Email</span>
                                    <input type="email" required="required" name="txtLogin" class="form-control" value="${requestScope.cliente.email}" id="input_email" onchange="validaEmail()"/>
                                </div>
                                <span id="span_email"></span>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Senha</span>
                                    <input type="password" required="required" name="txtSenha" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Confirmar Senha</span>
                                    <input type="password" required="required" name="txtSenha2" class="form-control" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- Fim do ROW!-->

                <div class="row center-block">
                    <div class="form-group">
                        <div class="form-inline">
                            <input type="submit" value="Cadastrar!" class="btn btn-success" />
                            <a href="../../index.jsp" class="btn btn-primary">Voltar</a>
                        </div>
                    </div>
                </div>
            </form>

            <!-- Mensagens das operacoes -->
            <c:if test="${requestScope.MsgSalvarCliente ne null}">
                <c:forEach var="alert" items="${requestScope.MsgSalvarCliente}">
                    <div class="alert alert-danger">
                        ${alert}
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${requestScope.MsgSucess ne null}">
                <c:forEach var="alert" items="${requestScope.MsgSucess}">
                    <div class="alert alert-success">
                        ${alert}
                    </div>
                </c:forEach>
            </c:if>
        </div>

        <!-- Teste do Codigo -->

    </body> 
</html>
