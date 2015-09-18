<%-- 
    Document   : PrestadorAlterarSenha
    Created on : 14/08/2015, 13:36:52
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
         <form method="POST" action="AlterarSenha">
      <input type="text" name="operacao" value="AlterarSenha" hidden="true"/>
      <div class="container-fluid">
          <!--Input digite um nova senha -->
          <div class="form-group " id="div_senha">
              <div class="input-group col-lg-7">
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
              <div class="input-group col-lg-7">
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
