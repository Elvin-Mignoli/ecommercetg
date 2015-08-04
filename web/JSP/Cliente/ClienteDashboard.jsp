<%-- 
    Document   : ClienteDashboard
    Created on : 21/07/2015, 22:24:22
    Author     : java
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bem Vindo</title>
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
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" aria-expanded="true">
                        Home
                    </button>
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="row profile">
                <div class="col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="profile-sidebar">
                                <!-- SIDEBAR USERPIC -->
                                <div class="profile-userpic">
                                    <img src="http://keenthemes.com/preview/metronic/theme/assets/admin/pages/media/profile/profile_user.jpg" class="img-responsive" alt="">
                                </div>
                                <!-- END SIDEBAR USERPIC -->
                                <!-- SIDEBAR USER TITLE -->
                                <div class="profile-usertitle">
                                    <div class="profile-usertitle-name">
                                        ${sessionScope.user.nome} ${sessionScope.user.sobrenome}
                                    </div>
                                    <div class="profile-usertitle-job">
                                        Developer
                                    </div>
                                </div>
                                <!-- END SIDEBAR USER TITLE -->
                                <!-- SIDEBAR BUTTONS -->
                                <div class="profile-userbuttons">
                                    <button type="button" class="btn btn-success">Follow</button>
                                    <button type="button" class="btn btn-danger">Message</button>
                                    <button type="button" class="btn btn-primary" id="danilo" onclick="danilo()">Danilo!</button>
                                </div>
                                <!-- END SIDEBAR BUTTONS -->
                                <!-- SIDEBAR MENU -->
                                <div class="profile-usermenu">
                                    <ul class="nav">
                                        <li class="active">
                                            <a href="ClienteDashboard.jsp">
                                                <i class="glyphicon glyphicon-home"></i>
                                                Meu Perfil </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i class="glyphicon glyphicon-user"></i>
                                                Config.Conta </a>
                                            <ul class="nav">

                                            </ul>
                                        </li>
                                        <li>
                                            <a href="ClienteAtualizar.jsp" id="ajax" data-titulo="Editar Dados">
                                                <i class="glyphicon glyphicon-pencil"></i>
                                                Editar Dados </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i class="glyphicon glyphicon-stats"></i>
                                                Meus Dados
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#">
                                                <i class="glyphicon glyphicon-flag"></i>
                                                Ajuda </a>
                                        </li>
                                        <li>
                                            <a href="logoff">
                                                <i class="glyphicon glyphicon-off"></i>
                                                Sair</a>
                                        </li>
                                    </ul>
                                </div>
                                <!-- END MENU -->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="profile-content">
                        <div class="row">
                            <div class="panel panel-default">
                                <div class="panel-heading" id="panel-heading"></div>
                                <div class="panel-body">

                                    <c:if test="${requestScope.MsgAtualiza ne null}">
                                        <div class="alert alert-info alert-dismissible" role="alert">
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                <span aria-hidden="true">&times;
                                                </span>
                                            </button>
                                            ${requestScope.MsgAtualiza}
                                        </div>
                                        <% request.setAttribute("MsgAtualiza", null);%>
                                    </c:if>
                                    <!-- Aqui vai toda as informações que o usuário precisar! -->
                                    <main id="conteudo">

                                    </main>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
</html>
