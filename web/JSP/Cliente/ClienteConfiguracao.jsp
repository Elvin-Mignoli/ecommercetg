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
        <script src="../../js/libs/jquery-1.11.1.min.js"></script>
        <script src="../../js/libs/jquery-ui.min.js"></script>
        <script src="../../js/libs/jquery.maskedinput.js"></script>
        <!-- implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/collapse.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <script src="../../js/ajaxFuntions.js"></script>
    </head>
    <body>
        <form action="ConfigurarConta" method="post">
            <div class="container">
                <h2 class="text-left">Mudar Email ou Senha</h2>
                <div class="row">
                    <div class="form-group col-sm-3">
                        <div class="input-group">
                            <span class="input-group-addon">Email</span>
                            <input type="text" name="txtEmail" id="email" value="${sessionScope.user.email}" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group col-sm-3">
                        <div class="input-group">
                            <span class="input-group-addon">Confirmar</span>
                            <input type="text"  name="txtEmail2" id="email2" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-sm-3">
                        <div class="input-group">
                            <span class="input-group-addon">Confirmar</span>
                            <input type="text" name="txtEmail" id="email" value="${sessionScope.user.email}" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group col-sm-3">
                        <div class="input-group">
                            <span class="input-group-addon">Confirmar</span>
                            <input type="password"  name="txtSenha" id="senha" value="${sessionScope.user.senha}" class="form-control"/>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
