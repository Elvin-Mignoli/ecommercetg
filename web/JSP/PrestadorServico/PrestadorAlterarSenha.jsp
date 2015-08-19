<%-- 
    Document   : PrestadorAlterarSenha
    Created on : 14/08/2015, 13:36:52
    Author     : Elvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="../../js/ajaxFuntions.js"></script>
        <script src="../../js/libs/jquery-1.11.1.min.js"></script>
        <script src="../../js/libs/jquery-ui.min.js"></script>
        <script src="../../js/libs/jquery.maskedinput.js"></script>
        <!--implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/collapse.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
        <!-- Custom styles for this template -->
        <link href="../../bootstrap/dist/css/jumbotron.css" rel="stylesheet" type="text/css"/>
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <script src="../../js/ajaxFuntions.js"></script>
        <script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>
    </head>
    <body>
         <form method="POST" action="AlterarSenha">
      <input type="text" name="operacao" value="AlterarSenha" hidden="true"/>
      <div class="container">
          <!--Input digite um nova senha -->
          <div class="form-group " id="div_senha">
              <div class="input-group col-lg-5">
                  <span class="input-group-addon">
                      NOVO SENHA PARA LOGIN
                  </span>
                  <input type="password" name="txtNovaSenha" placeholder="*************" required="required" 
                         id="input_senha" class="form-control" />
                   <span id="span_senha"></span>
              </div>
          </div>   
         
          <!-- Status da confirmação da no senha -->
          <div id="status_senha_confirm"></div>
          <!-- Input confirmar a nova senha-->
          <div class="form-group" id="div_senha_confirm">
              <div class="input-group col-lg-5">
                  <span class="input-group-addon">
                      DIGITE A SENHA NOVAMENTE
                  </span>
                  <input type="password" name="txtConfirmSenha" placeholder="**************" 
                         id="input_senha_confirm" required="required"  class="form-control"
                         onchange="confirmSenha()"/>
                  <span id="span_senha_confirm"></span>
              </div>
          </div>
      </div>
      
      <div class="container">
            <div class="form-group form-inline">
                <input type="submit" value="Alterar" class="btn btn-success" id="bt_alterar"/>
                <a href="PrestadorDashboard.jsp" class="btn btn-primary">Voltar</a>
            </div>
      </div>  
  </form>
    </body>
</html>
