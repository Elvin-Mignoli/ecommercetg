<%-- 
    Document   : Dash
    Created on : 21/09/2015, 18:49:39
    Author     : Elvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bem Vindo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
   
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
        
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Know-How</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="PrestadorDashboard.jsp">Home</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Page 1-1</a></li>
            <li><a href="#">Page 1-2</a></li>
            <li><a href="#">Page 1-3</a></li>
          </ul>
        </li>
        <li><a href="#">Page 2</a></li>
        <li><a href="#">Page 3</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
         <li><a href="logoff"><span class="glyphicon glyphicon-log-in"></span> Sair</a></li>
      </ul>
    </div>
  </div>
</nav>
<!-- Scripts da Pagina -->
   <!-- Arquivos bootstrap da página -->
      <script src="../../bootstrap/js/dropdown.js"></script>
      <script src="../../bootstrap/js/collapse.js"></script>
      <script src="../../bootstrap/js/tab.js"></script>
      <script src="../../bootstrap/js/modal.js" type="text/javascript"></script>
      <!-- Jquery.js-->
      <script src="../../js/libs/jquery-1.11.1.min.js" type="text/javascript"></script>
      <!-- Mask's -->
      <script src="../../js/libs/jquery.maskedinput.js"></script>
      <script src="../../js/libs/jquery.mask/jquery.mask.min.js" type="text/javascript"></script>
      <!--Bootstrap -->
      <script src="../../bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
       <!-- Arquivos JS para carregar tag inputs do bootstrap -->
      <script src="../../js/libs/angular/angular.min.js" type="text/javascript"></script>
      <script src="../../js/libs/jQuery-Tags/js/typeahead.tagging.js" type="text/javascript"></script>
      <script src="../../js/libs/jQuery-Tags/js/libs/typeahead.bundle.min.js" type="text/javascript"></script>
      <!-- Implementando script de load de paginas de funcoes -->
      <script src="../../js/ajaxFuntions.js"></script>
      <script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>

</body>
</html>
