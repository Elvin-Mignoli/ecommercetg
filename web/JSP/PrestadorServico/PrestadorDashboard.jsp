<%-- 
    Document   : PrestadorDashboard
    Created on : 06/08/2015, 15:39:17
    Author     : Elvin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--Navbar -->
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
        </nav><!--fim Navbar -->


        <div class="container-fluid">
            <div class="row-fluid profile">
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
                                <div class="profile-usertitle ">
                                    <div class="profile-usertitle-name text-center">
                                        ${sessionScope.user.nome} ${sessionScope.user.sobrenome}
                                    </div>
                                    <div class="profile-usertitle-job text-center">
                                        Developer
                                    </div>
                                </div>
                                <!-- END SIDEBAR USER TITLE -->
                                
                                <!-- SIDEBAR MENU -->
                                <div class="profile-usermenu">
                                    <ul class="nav">
                                        <li class="active">          
                                            <a href="PrestadorPerfil.jsp" id="meu_perfil" data-titulo="Perfil" >
                                                <i class="glyphicon glyphicon-home" data-titulo="Perfil"></i>
                                                Meu Perfil </a>
                                        </li>
                                       
                                        <li class="active">
                                             <a  href="#collapseMenu" data-toggle="collapse"  aria-expanded="false"  >
                                                <i class="glyphicon glyphicon-cog"></i>
                                                Config.Conta </a>
                                        </li>
                                        <div class="collase active" id="collapseMenu">
                                            <ul id="option-config">
                                                <a href="PrestadorAlterarEmail.jsp" id="alterar_email" data-titulo="Alterar Login/E-mail">
                                                    <i class="glyphicon glyphicon-pencil" data-titulo="Alterar Login/E-mail"></i>
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
                                                <a href= "CaixaEntrada" id="caixa_entrada" data-titulo="Caixa de Entrada" >
                                                <i class="glyphicon glyphicon-envelope"></i>
                                                Caixa de entrada 
                                                </a>
                                                <br/>
                                                <a href= "MensagemEnviada" id="mensagens_enviadas" data-titulo="Mensagens enviadas" >
                                                <i class="glyphicon glyphicon-cloud"></i>
                                                Mensagens enviadas
                                                </a>
                                                <br/>
                                              
                                            </ul>
                                        </div>
                                        
                                        
                                        <li class="active" id="listConsult">
                                            <a href="#collapseMenuConsult" data-toggle="collapse" aria-expanded="false">
                                                <i class="glyphicon glyphicon-chevron-down"></i>
                                                Consultorias
                                            </a>
                                        </li>
                                        <div class="collapse active" id="collapseMenuConsult">
                                            <ul id="option_consultoria">
                                                <a href="Consultorias?operacao=Consultar" id="minhas_consultorias" data-titulo="Minhas Consultorias">
                                                    <i class="glyphicon glyphicon-ok"></i>
                                                    Minhas Consultorias
                                                </a>
                                                <br/>
                                                <a href="Candidaturas?operacao=Consultar" id="candidaturas" data-titulo="Candidaturas">
                                                    <i class="glyphicon glyphicon-question-sign"></i>
                                                    Candidaturas
                                                </a>
                                                <br/>
                                                
                                            </ul>
                                        </div>
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
                        <div class="row-fluid-fluid">
                            <div class="panel panel-default">
                                <div class="panel-heading" id="panel-heading"></div>
                                <div class="panel-body">
                                    <c:if test="${requestScope.MsgAtualiza ne null}">
                                        <div class="alert alert-info alert-dismissible" role="alert">
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close" id="alert_msg">
                                                <span aria-hidden="true">&times;
                                                </span>
                                            </button>
                                            ${requestScope.MsgAtualiza}
                                        </div>
                                    </c:if>
                                    <!-- Aqui vai toda as informações que o usuário precisar! -->
                                    <main id="conteudo">
                                        <div class="container-fluid">
                                            <div class="row-fluid ">
                                                <c:forEach var="list" items="${requestScope.ListaPedido.pedidos}">
                                                    <div class="panel panel-success col-lg-12">
                                                        <div class="panel panel-heading text-center">Pedido</div>
                                                        <div class="panel-body">
                                                            <!-- Span descrição do pedido-->
                                                            <span><b>Descrição:</b> &nbsp;${list.descricao}</span><br>
                                                            <div class="text-center">
                                                                <span class="text-center"><b>Datas da consultoria:</b></span><br><br>
                                                            </div>
                                                            <!--span da datas do pedidos -->
                                                            <span><b>Data de início: &nbsp;</b><f:formatDate pattern="dd/MM/yyyy" value="${list.dataInicio}"></f:formatDate>
                                                                &nbsp;&nbsp;&nbsp;&nbsp;<b>Data de fim:</b><f:formatDate pattern="dd/MM/yyyy" value="${list.dataFim}"></f:formatDate></span>
                                                            <br>
                                                            <!-- hora da consultoria-->
                                                            <br>
                                                            <span><b>Hora:&nbsp;</b><f:formatDate type="time"  value="${list.horaConsultoria.getTime()}"></f:formatDate></span>
                                                            <br>
                                                            <!--span das habilidades requeridas pelo cliente -->
                                                            <span><b>Habilidades requeridas: &nbsp;</b>${list.habilidadePrestador.toString().replace("[","").replace("]","")}</span>
                                                            <br>
                                                            <span><b>Habilidades do cliente:&nbsp;</b>${list.habilidadeCliente.toString().replace("[","").replace("]","")}</span>
                                                            <br>
                                                            <c:if test="${!list.prestadores.contains(sessionScope.user)}">
                                                                <form action="Candidatar" method="POST">
                                                                    <input type="hidden" name="id_prestador" id="id_prestador" value="${sessionScope.user.id}"/>
                                                                    <input type="hidden" name="id_pedido" id="id_prestador" value="${list.id}"/>
                                                                    <button type="submit" class="btn btn-success" name="bt_candidatar" id="bt_candidatar">Candidatar-se</button>
                                                                </form>
                                                            </c:if>
                                                            <c:if test="${list.prestadores.contains(sessionScope.user)}">
                                                                <button type="button" class="btn btn-success"disabled="true">Já candidatou-se</button>
                                                            </c:if>
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

        <script>
        $(document).ready(    
        function() {
            //definindo o nome do pabel-heading
            $('#panel-heading').html("Mural de Pedidos");
            
            //AJAX para Caixa de entrada
           setInterval(function() {
                if($('#panel-heading').html() === "Caixa de Entrada")//está napage da caixa de entrada?
                {//sim
                      $("#caixa_entrada").trigger("click");
                      
                }else if($('#panel-heading').html() === "Mural de Pedidos")//est na page do mural de pedidos?
                {//dim
                    $("#sub").trigger("click");
                }
                    
            }, 120000); //fim do ajax
            
            if(${requestScope.MsgAtualiza == null})//existe mensagem de alerta?
            {//não
               if(${requestScope.ListaPedido == null})//já realizou alguma requeisição para o mural?
               {              //não  
                $("#sub").trigger("click");
               } 
            }
           //evento: após clicar pra fechar a mensagem de 
           //alerta se dispara uma requisição do mural de pedidos 
           $("#alert_msg").on("click",function(){
               $("#sub").trigger("click");
           });
        });  
        
        </script>
    </body>
</html>

