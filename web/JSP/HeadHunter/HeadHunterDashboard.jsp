<%-- 
    Document   : HeadHunterDashboard
    Created on : 26/11/2015, 10:13:26
    Author     : Elvin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <!-- CSS Bootstrap table -->
        <link href="../../js/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
        <!-- CSS Tagsinput -->
        <link href="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.css" rel="stylesheet" type="text/css"/>
    </head>
    <body id="dashBoard">
        <!-- Modal Perfil prestador -->
        <div class="modal fade" tabindex="-1" role="dialog" id="modalPerfil">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Perfil do prestador</h4>
                    </div>
                    <div class="modal-body">
                        <!-- Conteudo do Modal -->
                        <div class="container-fluid">
                            <div class="row-fluid">
                                <div class="col-md-10">
                                    <h3>Nome: <span class="btn-primary" id="nome"></span></h3>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="col-md-10">
                                    <!-- <h3>habilidades: <span class="" id="habilidades"></span></h3> -->
                                    <h3>Habilidades:</h3>
                                    <textarea id="habilidades" disabled="disabled"></textarea>
                                    <!-- <input type="text" data-role="tagsinput" id="habilidades" class="tags"/>-->
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="col-md-10">
                                    <!-- <h3>Contato: <span class="" id="email"></span></h3> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

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
                        <li class="active"><a href="HeadHunterDashboard.jsp">Home</a></li>
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
                                                    <img src="http://www.tvchoicemagazine.co.uk/sites/default/files/imagecache/interview_image/intex/michael_emerson.png" class="img-responsive">
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
                                            <a href="HeadHunterPerfil.jsp" id="meu_perfil_head" data-titulo="Perfil">
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
                                                <a href="HeadHunterAlterarEmail.jsp" id="editar_email_head">
                                                    <i class="glyphicon glyphicon-envelope"></i>
                                                    Editar Email
                                                </a>
                                                <br/>
                                                <a href="HeadHunterAlterarSenha.jsp" id="editar_senha_head">
                                                    <i class="glyphicon glyphicon-lock"></i>
                                                    Editar Senha
                                                </a>
                                            </ul>
                                        </div>
                                        <!-- <li class="active" id="listMensagens">
                                            <a href="#collapseMenuMensagens" data-toggle="collapse" aria-expanded="false">
                                                <i class="glyphicon glyphicon-envelope"></i>
                                                Mensagens
                                        <c:if test="${sessionScope.user.entrada.QtdeAbertas() gt 0}">
                                            <i class="badge" id="qtdeMsg" style="background-color: #2ECC40">${sessionScope.user.entrada.QtdeAbertas()}</i>
                                        </c:if>
                                    </a>
                                </li> -->
                                        <div class="collapse active" id="collapseMenuMensagens">
                                            <ul id="option_mensagens">
                                                <a href= "#" id="caixa_entrada_head" data-titulo="Caixa de Entrada" >
                                                    <i class="glyphicon glyphicon-envelope"></i>
                                                    Caixa de entrada 
                                                </a>
                                                <br/>
                                                <a href= "#" id="mensagens_enviadas_head" data-titulo="Mensagens enviadas" >
                                                    <i class="glyphicon glyphicon-cloud-download"></i>
                                                    Mensagens enviadas
                                                </a>
                                                <br/>

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
                                <div class="panel-heading" id="panel-heading">Ranking dos melhores prestadores</div>
                                <div class="panel-body">

                                    <!-- Aqui vai toda as informações que o usuário precisar! -->
                                    <input type="text" hidden="true" value="" id="lastURL" />
                                    <form method="POST" action="Ranking">
                                        <input type="text" name="operacao" value="Consultar" hidden="false"/>
                                        <input type="submit" id="ranking_submit" hidden="false"/>        
                                    </form>
                                    <main id="conteudo">
                                        <table data-toggle="table" data-pagination="true" data-search="true">
                                            <thead class="text-center">
                                            <th>Nome</th>
                                            <th>Ranking</th>
                                            <th>Perfil</th>
                                            </thead>
                                            <c:forEach var="prestador" items="${requestScope.prestadores}">
                                                <tr>
                                                    <td>
                                                        ${prestador.nome} ${prestador.sobrenome}
                                                    </td>
                                                    <td>
                                                        <input type="number" class="rating" disabled="disabled" value="${prestador.ranking}" data-size="xs"/>
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-primary perfil" id="btnPerfil" data-name="${prestador.id}" data-target="#modalPerfil" data-toggle="modal" onclick="modalPerfil(${prestador.id})">
                                                            <span class="glyphicon glyphicon-eye-open"></span>
                                                            Visualizar
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </main>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:set var="idPrestador" value="0"></c:set>
            <!-- Scripts da Pagina -->
            <!-- Jquery.js-->
            <script src="../../js/libs/jquery-1.11.1.min.js" type="text/javascript"></script>
            <!-- Mask's -->
            <script src="../../js/libs/jquery.maskedinput.js"></script>
            <script src="../../js/libs/jquery.mask/jquery.mask.min.js" type="text/javascript"></script>
            <!-- Arquivos bootstrap da página -->
            <script src="../../bootstrap/js/dropdown.js"></script>
            <script src="../../bootstrap/js/collapse.js"></script>
            <script src="../../bootstrap/js/tab.js"></script>
            <script src="../../bootstrap/js/modal.js" type="text/javascript"></script>
            <!-- Arquivos JS para carregar tag inputs do bootstrap -->
            <script src="../../js/libs/angular/angular.min.js" type="text/javascript"></script>
            <script src="../../js/libs/jQuery-Tags/js/typeahead.tagging.js" type="text/javascript"></script>
            <script src="../../js/libs/jQuery-Tags/js/libs/typeahead.bundle.min.js" type="text/javascript"></script>
            <!-- Plug-ins Jquery Notify -->
            <script src="../../js/libs/noty/packaged/jquery.noty.packaged.min.js" type="text/javascript"></script>
            <script src="../../js/libs/noty/layouts/topRight.js" type="text/javascript"></script>
            <script src="../../js/libs/noty/jquery.noty.js" type="text/javascript"></script>
            <!-- Sweet alert -->
            <script src="../../js/libs/sweet-notify/sweetalert.min.js" type="text/javascript"></script>
            <script src="../../js/libs/bootstrap-star-rating/js/star-rating.min.js" type="text/javascript"></script>
            <!--Notify js -->
            <script src="../../js/libs/Notify/pnotify.custom.min.js" type="text/javascript"></script>
            <!-- Bootstrap table -->
            <script src="../../js/libs/bootstrap-table/bootstrap-table.min.js"></script>
            <!-- tags input -->
            <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput-angular.min.js" type="text/javascript"></script>
            <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.min.js" type="text/javascript"></script>
            <!-- Implementando script de load de paginas de funcoes -->
            <script src="../../js/ajaxFuntions.js"></script>
            <script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>
            <script>
                                                            $(document).ready(function () {
                                                                if ($('#panel-heading').html() === 'Ranking dos melhores prestadores')
                                                                {
                                                                    if (${requestScope.prestadores == null})
                                                                        $('#ranking_submit').trigger("click");
                                                                }
                                                            });
        </script>
    </body>
</html>

