<%-- 
    Document   : ClienteConfiguracao
    Created on : 03/08/2015, 20:01:23
    Author     : java
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configurações da Conta</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
    </head>
    <body>
        <form action="AtualizaEmail" method="post">
            <div class="container">
                <div class="row">
                    <div class="alert alert-info col-lg-8 alert-dismissable" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;
                            </span>
                        </button>
                        <strong>Atenção!</strong> Por motivos de segurança, é obrigatório o preenchimento dos dados de Email e Senha.
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-3">
                        <h4>Email antigo</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-envelope"></span>
                            </span>
                            <input type="email" name="txtEmail" class="form-control" required="required" placeholder="Email atual"/>
                        </div> 
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-3">
                        <h4>Senha</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-lock"></span>
                            </span>
                            <input type="password" name="txtSenha" class="form-control" placeholder="Senha atual"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-3">
                        <h4>Novo email</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-ok"></span>
                            </span>
                            <input type="email" name="txtNovoEmail" required="required" class="form-control" placeholder="Novo Email"/>
                        </div>
                    </div>
                </div>
                <input type="submit" name="txtNovoEmail" class="btn btn-success" value="Alterar" id="changeEmail"/>  
            </div>
        </form>
        <script src="../../js/libs/jquery-1.11.1.min.js"></script>
        <script src="../../js/libs/jquery-ui.min.js"></script>
        <script src="../../js/libs/jquery.maskedinput.js"></script>
        <!-- implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/collapse.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
        <script src="../../js/ajaxFuntions.js"></script>
    </body>
</html>
