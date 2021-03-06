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
        <link href="../../js/libs/sweet-notify/sweetalert.css" rel="stylesheet" type="text/css"/> 
        <link href="../../js/libs/bootstrap-star-rating/css/star-rating.min.css" rel="stylesheet" type="text/css"/>
        <!--CSS notify -->
         <link href="../../js/libs/Notify/src/pnotify.brighttheme.css" rel="stylesheet" type="text/css"/>
         <link href="../../js/libs/Notify/src/pnotify.core.css" rel="stylesheet" type="text/css"/>
         <link href="../../js/libs/Notify/src/pnotify.buttons.css" rel="stylesheet" type="text/css"/>
        <link href="../../js/libs/Notify/src/pnotify.brighttheme.css" rel="stylesheet" type="text/css"/>
        <link href="../../js/libs/Notify/src/pnotify.mobile.css" rel="stylesheet" type="text/css"/>
        <link href="../../js/libs/Notify/src/pnotify.nonblock.css" rel="stylesheet" type="text/css"/>   
        <link href="../../js/libs/Notify/src/pnotify.history.css" rel="stylesheet" type="text/css"/>
        <link href="../../js/libs/Notify/src/pnotify.material.css" rel="stylesheet" type="text/css"/>
        
        
   </head>
    <body id="dashBoard">
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
            <div class="row-fluid profile" id="userProfile">
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
                                                    <img src="../../imagens/michael_emerson.png" class="img-responsive">
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
                                            <a href="ClientePerfil.jsp" id="meu_perfil_cliente" data-titulo="Perfil">
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
                                        <li class="active" id="listMensagens">
                                            <a href="#collapseMenuMensagens" data-toggle="collapse" aria-expanded="false">
                                                <i class="glyphicon glyphicon-envelope"></i>
                                                Mensagens
                                                <c:if test="${sessionScope.user.entrada.QtdeAbertas() gt 0}">
                                                    <i class="badge" id="qtdeMsg" style="background-color: #2ECC40">${sessionScope.user.entrada.QtdeAbertas()}</i>
                                                </c:if>
                                            </a>
                                        </li>
                                        <div class="collapse active" id="collapseMenuMensagens">
                                            <ul id="option_mensagens">
                                                <a href= "CaixaEntrada" id="caixa_entrada_cliente" data-titulo="Caixa de Entrada" >
                                                <i class="glyphicon glyphicon-envelope"></i>
                                                Caixa de entrada 
                                                </a>
                                                <br/>
                                                <a href= "MensagemEnviada" id="mensagens_enviadas_cliente" data-titulo="Mensagens enviadas" >
                                                <i class="glyphicon glyphicon-cloud-download"></i>
                                                Mensagens enviadas
                                                </a>
                                                <br/>
                                              
                                            </ul>
                                        </div>
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
                                                    Todos Pedidos
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
                                        <%request.setAttribute("MsgAtualiza", null);%>
                                    </c:if>
                                    <!-- Aqui vai toda as informações que o usuário precisar! -->
                                    <input type="text" hidden="true" value="" id="lastURL" />
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
        <!-- Plug-ins Jquery Notify -->
        <script src="../../js/libs/noty/packaged/jquery.noty.packaged.min.js" type="text/javascript"></script>
        <script src="../../js/libs/noty/layouts/topRight.js" type="text/javascript"></script>
        <script src="../../js/libs/noty/jquery.noty.js" type="text/javascript"></script>
        <!-- Implementando script de load de paginas de funcoes -->
        <script src="../../js/ajaxFuntions.js"></script>
        <script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>
        <script src="../../js/libs/sweet-notify/sweetalert.min.js" type="text/javascript"></script>
        <script src="../../js/libs/bootstrap-star-rating/js/star-rating.min.js" type="text/javascript"></script>
        <!--Notify js -->
        <script src="../../js/libs/Notify/pnotify.custom.min.js" type="text/javascript"></script>
        <script>
        $(document).ready
        (
        function() 
        {
            //AJAX para Caixa de entrada
           setInterval(function() 
           {
               
                if($('#panel-heading').html() === "Caixa de Entrada")//está napage da caixa de entrada?
                {//sim
                    //$(this).load('ClienteDashboard.jsp')
                    $("#caixa_entrada_cliente").trigger("click");
                }else if($('#panel-heading').html() === "Mensagens Enviadas")//está na page da mensagens enviadas?
                {//sim
                     $("#mensagens_enviadas_cliente").trigger("click");
                }
                //método para buscar notificações de video chat
                buscarNotificationCliente();
            },120000); //fim do ajax
           //método para buscar notificações de video chat
           buscarNotificationCliente();
            
        });
        
        </script>
    </body>
</html>
