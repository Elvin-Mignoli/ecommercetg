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
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
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
                        <li class="active"><a href="ClienteDashboard.jsp">Home</a></li>
                        <!--<li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Page 1-1</a></li>
                                <li><a href="#">Page 1-2</a></li>
                                <li><a href="#">Page 1-3</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Page 2</a></li>
                        <li><a href="#">Page 3</a></li> -->
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="logoff"><span class="glyphicon glyphicon-log-in"></span> Sair</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <!-- Modal -->
            <div class="modal fade" id="photoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Modal Title</h4>
                        </div>
                        <div class="modal-body">
                            <input class="form-control" placeholder="Seu Nome?" id="myInput" />
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                Close
                            </button>
                            <button type="button" class="btn btn-success">Save Changes</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid profile">
                <div class="col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="profile-sidebar">
                                <!-- SIDEBAR USERPIC -->
                                <form method="post" action="Imagem" enctype="multipart/form-data">
                                    <div class="profile-userpic">
                                        <div class="media">
                                            <div class="media-left media-middle">
                                                <a href="#" id="file">
                                                    <img src="http://keenthemes.com/preview/metronic/theme/assets/admin/pages/media/profile/profile_user.jpg" class="img-responsive">
                                                    <!-- <img src="${sessionScope.user.imagem}" class="img-responsive" id="imagePerfil"> -->
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- SIDEBAR BUTTONS -->
                                    <div class="profile-userbuttons">
                                        <div id="buttonGroups" hidden="true">
                                            <input type="file" name="uploadImage" class="form-control" accept="image/*" id="file"/>
                                            <input type="submit" class="btn btn-success"/>
                                        </div>
                                    </div>
                                    <!-- END SIDEBAR BUTTONS -->
                                </form>
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

                                <!-- SIDEBAR MENU -->
                                <div class="profile-usermenu">
                                    <ul class="nav">
                                        <li class="active">
                                            <a href="ClienteDashboard.jsp">
                                                <i class="glyphicon glyphicon-home"></i>
                                                Meu Perfil </a>
                                        </li>
                                        <li class="active" id="listConfig">
                                            <a href="#collapseMenu" data-toggle="collapse" aria-expanded="false">
                                                <i class="glyphicon glyphicon-cog"></i>
                                                Configurações da Conta
                                            </a>
                                        </li>
                                        <div class="collapse active" id="collapseMenu">
                                            <ul id="option_config">
                                                <a href="AlterarEmail.jsp" id="editar_email">
                                                    <i class="glyphicon glyphicon-envelope"></i>
                                                    Editar Email
                                                </a>
                                                <br/>
                                                <a href="AlterarSenha.jsp" id="editar_senha">
                                                    <i class="glyphicon glyphicon-lock"></i>
                                                    Editar Senha
                                                </a>
                                                <br/>
                                                <a href="AlterarCartao.jsp" id="editar_cartao">
                                                    <i class="glyphicon glyphicon-credit-card"></i>
                                                    Editar Cartão
                                                </a>
                                            </ul>
                                        </div>
                                        <li>
                                            <a href="ClienteAtualizar.jsp" id="editar_dados" data-titulo="Editar Dados">
                                                <i class="glyphicon glyphicon-pencil"></i>
                                                Editar meus dados 
                                            </a>
                                        </li>
                                        <li class="active" id="listPedidos">
                                            <a href="#collapsePedidos" data-toggle="collapse" aria-expanded="false">
                                                <i class="glyphicon glyphicon-stats"></i>
                                                Meus Pedidos
                                            </a>
                                        </li>
                                        <div class="collapse active" id="collapsePedidos">
                                            <ul id="collapse_pedidos">
                                                <a href="Pedidos.jsp" id="criar_pedido">
                                                    <i class="glyphicon glyphicon-pencil"></i>
                                                    Abrir Pedido
                                                </a>
                                            </ul>
                                            <ul id="collapse_pedidos">
                                                <a href="/Ecommerce/JSP/Cliente/MeusPedidos?txtId=${sessionScope.user.id}&operacao=Consultar" id="consulta_pedido" data-titulo="Meus Pedidos">
                                                    <i class="glyphicon glyphicon-th-list"></i>
                                                    Consultar Pedidos
                                                </a>
                                            </ul>
                                        </div>
                                        <ul class="nav nav-divider"></ul>
                                        <li>
                                            <a href="#" data-toggle="modal" data-target="#photoModal">
                                                <i class="glyphicon glyphicon-flag"></i>
                                                Ajuda 
                                            </a>
                                        </li>
                                        <li>
                                            <a href="logoff">
                                                <i class="glyphicon glyphicon-off"></i>
                                                Sair
                                            </a>
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
                        <div class="row-fluid-fluid">
                            <div class="panel panel-default">
                                <div class="panel-heading" id="panel-heading"></div>
                                <div class="panel-body">
                                    <c:if test="${requestScope.MsgAtualiza ne null}">
                                        <div class="alert alert-info alert-dismissible" role="alert">
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                <span aria-hidden="true">&times;
                                                </span>
                                            </button>
                                            ${requestScope.MsgAtualiza} <!-- Mostra a mensagem --> 
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
