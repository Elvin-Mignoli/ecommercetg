<%-- 
    Document   : HeadHunterAlterarEmail
    Created on : 26/11/2015, 12:20:10
    Author     : Elvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
       
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>

    </head>
    <body> 
        <div class="container-fluid">
                <!--E-mail atual-->
                <div class="row">
                    <div class="form-group ">
                        <h4>LOGIN/E-MAIL ATUAL</h4>
                        <div class="input-group col-lg-6">
                            <span class="input-group-addon" >
                                <span class="glyphicon glyphicon-envelope"></span>
                            </span>
                            <input type="text" name="txtEmailAtual" placeholder="exemplo@exemplo.com" required="required" id="email_atual"
                                   value="${sessionScope.user.email}" readonly class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <!-- Status E-mail-->
                    <div id="statusEmail"></div>
                    <!--Input digite um novo email -->
                    <div class="form-group " id="div_email">
                        <h4>NOVO E-MAIL PARA LOGIN</h4>
                        <div class="input-group col-lg-6">
                            <span class="input-group-addon">
                                 <span class="glyphicon glyphicon-lock"></span>
                            </span>
                            <input type="text" name="txtNovoEmail" placeholder="exemplo@exemplo.com" required="required" 
                                   id="input_email" class="form-control" onchange="validaEmail()"/>
                            <span id="span_email"></span>
                        </div>
                    </div>
                </div>
                <!-- Input confirmar o E-mail-->
                <!-- Status da confirmação do E-mail-->
                <div class="row">
                    <div id="status_confirm"></div>
                    <div class="form-group " id="div_confirm">
                        <h4>DIGITE NOVAMENTE O E-MAIL</h4>
                        <div class="input-group col-lg-6">
                            <span class="input-group-addon">
                                  <span class="glyphicon glyphicon-envelope"></span>
                            </span>
                            <input type="text" name="txtConfirmEmail" placeholder="exemplo@exemplo.com"
                                   id="input_confirm" required="required"  class="form-control"
                                   onchange="confirmEmail()" />
                            <span id="span_confirm"></span>
                        </div>
                    </div>
                </div>
            </div>
           
            <div class="container">
                <div class="form-group form-inline">
                    <input type="button" value="Alterar" class="btn btn-success" id="submit" onclick="atualizarEmailHeadHunter()">
                    <a href="HeadHunterDashboard.jsp" class="btn btn-primary">Voltar</a>
                </div>
            </div>                        
    </body>
</html>