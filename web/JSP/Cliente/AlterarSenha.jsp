<%-- 
    Document   : AlterarSenha
    Created on : 04/08/2015, 18:09:52
    Author     : java
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <title>Alterar Senha</title>
    </head>
    <body>
        <form method="post" action="AtualizaSenha">
            <div class="container">
                <div class="row">
                    <div class="alert alert-info col-lg-5 alert-dismissable" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;
                            </span>
                        </button>
                        <strong>Atenção!</strong> Coloque sua senha antiga e logo após a nova senha.
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-3">
                        <div class="input-group">
                            <span class="input-group-addon">Senha</span>
                            <input type="password" name="txtSenha" class="form-control" required="required" placeholder="Senha atual"/>
                        </div> 
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-3">
                        <div class="input-group">
                            <span class="input-group-addon">Nova Senha</span>
                            <input type="password" name="txtNovaSenha" class="form-control" placeholder="Nova Senha" required="required"/>
                        </div>
                    </div>
                </div>
                <input type="submit" class="btn btn-success" value="Alterar"/>  
            </div>
        </form>
    </body>
</html>
