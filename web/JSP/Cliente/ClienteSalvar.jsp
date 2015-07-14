<%-- 
    Document   : ClienteSalvar
    Created on : 26/05/2015, 12:26:40
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
        <title>Cadastrar Cliente</title>

        <!-- implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>

        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css"/>

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
            <div class="row">
                <form id="form_cadastro" method="POST" action="SalvarCliente">
                    <div class="panel panel-default col-lg-5">
                        <div class="panel panel-heading text-center">Informações Pessoais</div>
                        <div class="panel-body">
                            <!-- Conteudo dos Panels! -->
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Nome</span>
                                    <input type="text" required="required" name="txtNome" class="form-control" placeholder="Digite seu Nome"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Sobrenome</span>
                                    <input type="text" name="txtSobrenome" class="form-control" placeholder="Digite seu Sobrenome" required="required"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Sexo</span>
                                    <select name="txtSexo" class="form-control">
                                        <option>Masculino</option>
                                        <option>Feminino</option>
                                        <option>Não especificado</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">CPF</span>
                                    <input type="text" name="txtCpf" class="form-control" id="input_cpf" required="required"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Data de Nascimento</span>
                                    <input type="date" required="required" name="txtDataNascimento" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Telefone</span>
                                    <input type="text" name="txtTelefone" id="telefone" class="form-control" required="required"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Celular</span>
                                    <input type="text" name="txtCelular" required="required" class="form-control" id="celular"/>
                                </div>
                            </div>                          
                        </div>
                    </div>

                    <!-- <div class="col-sm-1"></div> <!-- Um pequeno SPAN -->
                    <div class="panel panel-default col-lg-5">
                        <div class="panel panel-heading text-center">Dados do Endereço</div>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">CEP</span>
                                    <input type="text" name="txtCEP" id="cep" class="form-control" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Endereço</span>
                                    <input type="text" name="txtEndereco" class="form-control"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- -------------------------------------------- -->
                    <div class="panel panel-default col-lg-5">
                        <div class="panel panel-heading text-center">Informações de Login</div>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Email</span>
                                    <input type="email" required="required" name="txtLogin" class="form-control" />
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Senha</span>
                                    <input type="password" required="required" name="txtSenha" class="form-control" />
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">Confirmar Email</span>
                                    <input type="email" required="required" name="txtEmail2" class="form-control" />
                                </div>
                            </div>
                        </div>
                    </div>
            </div> <!-- Fim do ROW!-->
        </form>
    </div>
</div>
</body> 
</html>
