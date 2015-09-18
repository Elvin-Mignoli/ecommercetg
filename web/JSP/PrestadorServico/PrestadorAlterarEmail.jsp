<%-- 
    Document   : PrestadorEditarEmail
    Created on : 14/08/2015, 09:35:16
    Author     : Elvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Custom styles for this template -->
        <link href="../../bootstrap/dist/css/jumbotron.css" rel="stylesheet" type="text/css"/>
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <form method="POST" action="AlterarEmail" name="form_alterarEmail">
            <input type="text" name="operacao" value="AlterarEmail" hidden="true"/>
            <div class="container-fluid">
                <!--E-mail atual-->
                <div class="form-group">
                    <div class="input-group col-lg-8">
                        <span class="input-group-addon" >
                            LOGIN/E-MAIL ATUAL
                        </span>
                        <input type="text" name="txtEmailAtual" placeholder="exemplo@exemplo.com" required="required" id="email_atual"
                               value="${sessionScope.user.email}" readonly class="form-control"/>
                    </div>
                </div>
                <!-- Status E-mail-->
                <div id="statusEmail"></div>
                <!--Input digite um novo email -->
                <div class="form-group " id="div_email">
                    <div class="input-group col-lg-8">
                        <span class="input-group-addon">
                            NOVO E-MAIL PARA LOGIN
                        </span>
                        <input type="text" name="txtNovoEmail" placeholder="exemplo@exemplo.com" required="required" 
                               id="input_email" class="form-control" onchange="validaEmail()"/>
                        <span id="span_email"></span>
                    </div>
                </div>

                <!-- Input confirmar o E-mail-->
                <!-- Status da confirmação do E-mail-->
                <div id="status_confirm"></div>
                <div class="form-group" id="div_confirm">
                    <div class="input-group col-lg-8">
                        <span class="input-group-addon">
                            DIGITE NOVAMENTE O E-MAIL
                        </span>
                        <input type="text" name="txtConfirmEmail" placeholder="exemplo@exemplo.com"
                               id="input_confirm" required="required"  class="form-control"
                               onchange="confirmEmail()" />
                        <span id="span_confirm"></span>
                    </div>
                </div>
            </div>
           
            <div class="container">
                <div class="form-group form-inline">
                    <input type="button" value="Alterar" class="btn btn-success" id="submit"/>
                    <a href="PrestadorDashboard.jsp" class="btn btn-primary">Voltar</a>
                </div>
            </div>  
        </form>
                     
        <!-- Scripts da Pagina -->
        <!-- Importando jquery-->
          <!--<script src="../../js/libs/jquery-1.11.1.min.js"></script>
        <script src="../../js/libs/jquery-ui.min.js"></script>-->
         <!--implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/collapse.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
         <!-- Scripts personalizados -->
        <!--<script src="../../js/ajaxFuntions.js"></script>-->
        
    </body>
</html>
