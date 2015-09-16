<%-- 
    Document   : MuralPedidos
    Created on : 11/09/2015, 10:29:13
    Author     : Elvin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../../css/changeColor.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this template -->
        <link href="../../bootstrap/dist/css/jumbotron.css" rel="stylesheet" type="text/css"/>
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/> 
        
    </head>
    <body>
        <span>OI${requestScope.user}</span>
        <div class="container">
            <div class="row ">
                <c:forEach var="list" items="${requestScope.user.pedidos}">
                    <div class="panel panel-success col-lg-4">
                        <div class="panel panel-heading text-center">Pedido</div>
                        <div class="panel-body">
                            <span>${requestScope.pedido.descricao}</span>
                            <span>OI</span>
                        </div>
                    </div>
                    <div class="panel panel-success col-lg-4">
                        <div class="panel panel-heading text-center">Pedido</div>
                        <div class="panel-body">
                            <span>${requestScope.pedido.descricao}</span>
                            <span>OI</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- Scripts da Pagina -->
        <!-- Importando jquery-->
         <!--<script src="../../js/libs/jquery-1.11.1.min.js"></script>
        <script src="../../js/libs/jquery-ui.min.js"></script>-->
        <script src="../../js/libs/jquery.maskedinput.js"></script>
         <!--implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/collapse.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
        <!-- JavaScript Bootstrap tag-input -->
        <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.min.js"></script>
        <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput-angular.min.js"></script>
         <!-- Scripts personalizados -->
        <script src="../../js/ajaxFuntions.js"></script>
        <script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>
    </body>
</html>
