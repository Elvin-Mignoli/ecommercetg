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
        <!--sweet alert-->
        <link href="../../js/libs/sweet-notify/sweetalert.css" rel="stylesheet" type="text/css"/>
   
        <!-- css table-->
        <link href="../../js/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
        <!-- CSS icons-->
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        
        <style>
            .afinidade{
                color: black;
            }
        </style>
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
                  <li class="active"><a href="PrestadorDashboard.jsp" id="home">Home</a></li>
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
                <!-- Modal loading-->
                <div id="myModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                      <div class="modal-content">
                        Carregando os pedidos no mural...
                      </div>
                    </div>
                    <button type="button" hidden="true" id="fechar_modal" data-dismiss="modal">Fechar</button>
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
                                        <table data-toggle="table" 
                                         data-show-columns="true" data-pagination="true" data-search="true" 
                                         data-select-item-name="toolbar1" data-sort-order="desc" data-page-size="5">
                                            <thead>
                                                <tr>
                                                    <th data-field="Pedido" data-sortable="true">Pedido</th>
                                                    <th data-field="Data do Pedido" data-sortable="true" class=" text-center col-lg-1">Data do Pedido</th>
                                                    <th data-field="Afinidade" data-sortable="true" class="text-center col-lg-2">Afinidade <span class="glyphicon glyphicon-question-sign"></span></th>
                                                </tr>
                                            </thead>
                                                <c:forEach var="list" items="${requestScope.ListaPedido.pedidos}">
                                                    <c:if test="${!list.prestadores.contains(sessionScope.user)}">
                                                    <tr>
                                                        <td>
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
                                                                            <input type="hidden"  id="id_prestador" value="${sessionScope.user.id}"/>
                                                                            <br><button type="button" class="btn btn-success" name="bt_candidatar" id="bt_candidatar" onclick="candidatar(${list.id})">Candidatar-se</button>
                                                                        </form>
                                                                    </c:if>
                                                                  <!--  <c:if test="${list.prestadores.contains(sessionScope.user)}">
                                                                        <button type="button" class="btn btn-success"disabled="true">Já candidatou-se</button>
                                                                    </c:if>-->
                                                                </div>     
                                                            </div>
                                                        </td>
                                                        <td class="text-center"><f:formatDate pattern="dd/MM/yyyy" value="${list.data.getTime()}"></f:formatDate></td>
                                                        <td class="text-center">
                                                        <c:if test="${list.afinidade.equals('Nenhuma afinidade')}">
                                                            <h4 class="btn-danger"> <i class="glyphicon glyphicon-star-empty btn-danger  afinidade" id="star_perc">${list.afinidadePercent}%</i></h4>
                                                            <h4 class="btn-danger afinidade">${list.afinidade}</h4>
                                                        </c:if>
                                                        <c:if test="${list.afinidade.equals('Pouco afinidade')}">
                                                            <h4 class="btn-warning"> <i class="glyphicon glyphicon-star-empty btn-warning  afinidade" id="star_perc">${list.afinidadePercent}%</i></h4>
                                                            <h4 class="btn-warning afinidade">${list.afinidade}</h4>
                                                        </c:if>
                                                        <c:if test="${list.afinidade.equals('Afinidade regular')}">
                                                            <h4 class="btn-info"> <i class="glyphicon glyphicon-star-empty btn-info afinidade" id="star_perc">${list.afinidadePercent}%</i></h4>
                                                            <h4 class="btn-info afinidade">${list.afinidade}</h4>
                                                        </c:if>
                                                        <c:if test="${list.afinidade.equals('Boa afinidade')}">
                                                            <h4 class="btn-primary"> <i class="glyphicon glyphicon-star-empty btn-primary  afinidade" id="star_perc">${list.afinidadePercent}%</i></h4>
                                                            <h4 class="btn-primary afinidade">${list.afinidade}</h4>
                                                        </c:if>
                                                        <c:if test="${list.afinidade.equals('Ótima afinidade')}">
                                                            <h4 class="btn-success"> <i class="glyphicon glyphicon-star-empty btn-success  afinidade" id="star_perc">${list.afinidadePercent}%</i></h4>
                                                            <h4 class="btn-success afinidade">${list.afinidade}</h4>
                                                        </c:if>
                                                        </td>
                                                    </tr>
                                                    </c:if>
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
        <!--form para resgatar os pedidos para o mural -->
        <form action="Mural" method="POST">
            <input type="hidden" value="Consultar" name="operacao"/>
            <input type="submit"  hidden="false" id="sub"/>
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
      <!-- js table-->
       <script src="../../js/libs/bootstrap-table/bootstrap-table.min.js" type="text/javascript"></script>  
       <!--sweet alert-->
       <script src="../../js/libs/sweet-notify/sweetalert.min.js" type="text/javascript"></script>
      
   
        <script>
        $(document).ready(
        function() {
            
            //Mask money
          //  $('.vlConsult').mask('000.000.000.000.000,00', {reverse: true});
            //definindo o nome do pabel-heading
            $('#panel-heading').html("Mural de Pedidos");
            
            //AJAX para Caixa de entrada
           setInterval(function() {
                if($('#panel-heading').html() === "Caixa de Entrada")//está na page da caixa de entrada?
                {//sim
                      $("#caixa_entrada").trigger("click");
                      
                }else if($('#panel-heading').html() === "Mensagens Enviadas")//está na page da mensagens enviadas?
                {//sim
                     $("#mensagens_enviadas").trigger("click");
                }
                else if($('#panel-heading').html() === "Mural de Pedidos")//está na page do mural de pedidos?
                {//dim
                    $("#sub").trigger("click");
                    //sweet alert do mural carregando
                     swal({
                        title:"Mural",
                        text:"Carregando os pedidos para o mural",
                        timer:10000,
                        imageUrl: "http://oi61.tinypic.com/65r2wp.jpg",
                        showConfirmButton:false
                });
                }
                    
            }, 120000); //fim do ajax
            
            if(${requestScope.MsgAtualiza == null})//existe mensagem de alerta?
            {//não
               if(${requestScope.ListaPedido == null})//já realizou alguma requeisição para o mural?
               {              //não  
                $("#sub").trigger("click");
                //sweet alert do mural carregando
                 swal({
                        title:"Mural",
                        text:"Carregando os pedidos para o mural",
                        timer:10000,
                        imageUrl: "http://oi61.tinypic.com/65r2wp.jpg",
                        showConfirmButton:false
                });
               } 
            }
           //evento: após clicar pra fechar a mensagem de 
           //alerta se dispara uma requisição do mural de pedidos 
           $("#alert_msg").on("click",function(){
               $("#sub").trigger("click");
               //sweet alert do mural carregando
                swal({
                        title:"Mural",
                        text:"Carregando os pedidos para o mural",
                        timer:10000,
                        imageUrl: "http://oi61.tinypic.com/65r2wp.jpg",
                        showConfirmButton:false
                });
           });
           $('.glyphicon-question-sign').popover(
            {
                animation: true,
                placement: 'right',
                title: 'Afinidade',
                trigger: 'hover focus',
                content:'Porcentagem de compatibilidade com o pedido, baseados em suas habilidades'
            });
        });  
        </script>
        
            
        
    </body>
</html>

