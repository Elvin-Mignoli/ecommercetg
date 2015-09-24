<%-- 
    Document   : AlterarCartao
    Created on : 06/08/2015, 14:23:40
    Author     : java
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configurações da Conta</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <title>Alterar Cartão</title>
    </head>
    <body>
        <form method="post" action="AtualizaCartao">
            <div class="container">
                <div class="row">
                    <div class="col-lg-20">
                        <div class="form-group col-lg-4" id="div_cartao">
                            <div class="input-group">
                                <span class="input-group-addon">Numero Cartão</span>
                                <input type="text" name="txtNumero" id="numero_cartao" class="form-control" required="required" onchange="validaBandeira()" placeholder="Número do Cartão"/>
                                <span class="" id="span_cartao"></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-1">
                        <div class="form-group">
                            <div class="input-group">
                                <img src="" id="ico_card"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon">Nome Titular</span>
                            <input type="text" name="txtTitular" class="form-control" placeholder="Nome igual ao cartão" required="required"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <div class="input-group">
                            <span class=" input-group-addon">Código Segurança</span>
                            <input type="text" id="secure_number" class="form-control" name="txtSecureNumber" placeholder="Três digitos" required="required"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <div class="input-group">
                            <span class="input-group-addon">Validade</span>
                            <input type="text" name="txtValidade" id="validade" class="form-control" required="required" placeholder="Validade do Cartão"/>
                        </div>
                    </div>
                </div>
                <input type="text" id="bandeira" name="txtBandeira" hidden="true"/>
                <input type="text" name="operacao" value="Atualizar" hidden="true"/>
                <input type="submit" name="txtNovoEmail" class="btn btn-success" value="Alterar" id="changeEmail"/>
            </div>
        </form>
        <script src="../../js/ajaxFuntions.js"></script>
        <script src="../../js/libs/jquery-1.11.1.min.js"></script>
        <script src="../../js/libs/jquery-ui.min.js"></script>
        <script src="../../js/libs/jquery.maskedinput.js"></script>
        <!-- implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/collapse.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
    </body>
</html>
