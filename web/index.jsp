<%-- 
    Document   : index
    Created on : 21/04/2015, 16:31:43
    Author     : java
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Bem Vindo</title>
        
        <!-- Bootstrap core CSS -->
        <link href="bootstrap/dist/css/bootstrap.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="bootstrap/dist/css/jumbotron.css" rel="stylesheet">
        
        <!--Para funcionar o collapsed-->
        <script src="js/libs/jquery-1.11.1.min.js"></script>
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
        
    </head>

    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Ecommerce</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" method="post" action="ValidarUsuario">
                        <c:if test="${requestScope.loginError ne null}">
                            <div class="form-group">
                                <span class="alert alert-info">
                                    ${requestScope.loginError}
                                    <% request.setAttribute("loginError", null); %>
                                </span>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <input type="text" placeholder="Email" name="txtEmail" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" name="txtPassword" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-success">Entrar</button>
                        <a href="JSP/Cliente/ClienteSalvar.jsp" class="btn btn-link">Não possui cadastro?</a>
                        <!-- <input type="button" class="btn btn-link" value="Não possui cadastro?"/> -->
                        <a href="JSP/PrestadorServico/PrestadorSalvar.jsp" class="btn btn-link">Quero prestar Serviço</a>
                    </form>
                </div><!--/.navbar-collapse -->
            </div>
        </nav>

        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="jumbotron">
            <div class="container">
                <h1>Bem vindo!</h1>
                <p>Está página vai ser destinada para apresentar diversas informações sobre os serviços</p>
                <p><a class="btn btn-primary btn-lg" href="#" role="button">Saiba mais sobre nós!</a></p>
            </div>
        </div>

        <div class="container">
            <!-- Example row of columns -->
            <div class="row">
                <div class="col-md-4">
                    <h2>Titulo 1</h2>
                    <p>Alguns informações aqui</p>
                    <p><a class="btn btn-default" href="#" role="button">Mais detalhes &raquo;</a></p>
                </div>
                <div class="col-md-4">
                    <h2>Titulo 2</h2>
                    <p>Algumas informações aqui também </p>
                    <p><a class="btn btn-default" href="#" role="button">Mais detalhes &raquo;</a></p>
                </div>
                <div class="col-md-4">
                    <h2>Titulo 3</h2>
                    <p>E mais algumas por aqui!</p>
                    <p><a class="btn btn-default" href="#" role="button">Mais detalhes &raquo;</a></p>
                </div>
            </div>

            <hr>

            <footer class="form-inline">
                <p>&copy; Company 2014</p>
            </footer>
        </div> <!-- /container -->
        
        
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
        
        
    </body>
</html>

