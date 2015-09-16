<%-- 
    Document   : PrestadorDashboard
    Created on : 06/08/2015, 15:39:17
    Author     : Elvin
--%>

<%@page import="br.com.ecommerce.domain.PrestadorServico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Bem Vindo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
       
        <!-- Custom styles for this template -->
        <link href="../../bootstrap/dist/css/jumbotron.css" rel="stylesheet" type="text/css"/>
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                     <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" >
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
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
                                    <button type="button" class="btn btn-success">Seguir</button>
                                    <button type="button" class="btn btn-danger">Mensagem</button>
                                    
                                </div>
                                <!-- END SIDEBAR BUTTONS -->
                                <!-- SIDEBAR MENU -->
                                <div class="profile-usermenu">
                                    <ul class="nav">
                                        <li class="active">          
                                            <a href="PrestadorPerfil.jsp" id="meu_perfil" data-titulo="Perfil" >
                                                <i class="glyphicon glyphicon-home" data-titulo="Perfil"></i>
                                                Meu Perfil </a>
                                        </li>
                                       
                                        <li class="active">
                                             <a  data-toggle="collapse" href="#collapseMenu" aria-expanded="false"  >
                                                <i class="glyphicon glyphicon-cog"></i>
                                                Config.Conta </a>
                                        </li>
                                        <div class="collase active" id="collapseMenu">
                                            <ul id="option-config">
                                                <a href="PrestadorAlterarEmail.jsp" id="alterar_email" data-titulo="Alterar Login/E-mail">
                                                    <i class="glyphicon glyphicon-envelope" data-titulo="Alterar Login/E-mail"></i>
                                                    Alterar Login/E-mail
                                                </a>
                                                <br/>
                                                <a href="PrestadorAlterarSenha.jsp" id="alterar_senha" data-titulo="Alterar senha">
                                                    <i class="glyphicon glyphicon-lock"></i>
                                                    Alterar Senha
                                                </a>
                                                <br/>
                                            </ul>
                                        </div>
                                        <li>
                                                <a href="PrestadotAtualizar.jsp" id="edit_dados_prest" data-titulo="Editar Dados">
                                                <i class="glyphicon glyphicon-pencil"></i>
                                               
                                                Editar Dados </a>
                                        </li>
                                        <li class="active">
                                            <a href= "/Ecommerce/JSP/PrestadorServico/CaixaEntrada" id="caixa_entrada" data-titulo="Caixa de Entrada" >
                                                <i class="glyphicon glyphicon-envelope"></i>
                                                Mensagens </a>
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
                                        <div class="container-fluid">
                                            <div class="row-fluid ">
                                                <c:forEach var="list" items="${requestScope.pedidos.listaPedidos}">
                                                    <div class="panel panel-success col-lg-12">
                                                        <div class="panel panel-heading text-center">Pedido</div>
                                                        <div class="panel-body">
                                                            <span>Descrição:${list.descricao}</span>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </main>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--form para resgatar os pedidos para o mural -->
        <form action="Mural" method="POST">
            <input type="hidden" value="Consultar" name="operacao"/>
            <input type="submit"  hidden="false"id="sub"/>
        </form>
        <!-- Scripts da Pagina -->
       <!-- Arquivos JS da pagina -->
        <script src="../../js/libs/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="../../js/libs/jquery.maskedinput.js"></script>
        <script src="../../js/libs/jquery.mask/jquery.mask.min.js" type="text/javascript"></script>
        <!-- Arquivos bootstrap da página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/dropdown.js"></script>
        <script src="../../bootstrap/js/collapse.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
        <script src="../../bootstrap/js/modal.js" type="text/javascript"></script>
        <!-- Arquivos JS para carregar tag inputs do bootstrap -->
        <script src="../../js/libs/angular/angular.min.js" type="text/javascript"></script>
        <script src="../../js/libs/jQuery-Tags/js/typeahead.tagging.js" type="text/javascript"></script>
        <script src="../../js/libs/jQuery-Tags/js/libs/typeahead.bundle.min.js" type="text/javascript"></script>
        <!-- Implementando script de load de paginas de funcoes -->
        <script src="../../js/ajaxFuntions.js"></script>
        <script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>
        <script>
        $(document).ready(    
        function() {
            //definindo o nome do pabel-heading
                $('#panel-heading').html("Mural de Pedidos");
            //AJAX para Caixa de entrada
            setInterval(function() {
                if($('#panel-heading').html() === "Caixa de Entrada")
                {
                      $("#caixa_entrada").trigger("click");
                }else if($('#panel-heading').html() === "Mural de Pedidos")
                    $("#sub").trigger("click");
            }, 60000);
            if(${requestScope.pedidos == null})
            {                
                $("#sub").trigger("click");
            }
          
             //$("#sub").prop("type","submit");
            //script para o mural de pedidos
            /*var operacao = "Consultar";
            $.post("Mural", {operacao: operacao}, function(responseJson){
                alert(responseJson);
                var path ="MuralPedidos.jsp"; //Pegamos o caminho
                var titulo = "Mural Pedidos"; //pegamos o titulo da página
                document.title = titulo; // Alterar o titulo da página
                window.history.pushState("", titulo, path);
                $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
                $("#conteudo").load(path); //Faz uma requisição http para o servidor.
                window.history.pushState('Object', 'Dashboard', './PrestadorDashboard.jsp');
            });*/
        });  
        </script>
    </body>
</html>

