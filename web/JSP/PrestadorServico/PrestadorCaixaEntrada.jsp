<%-- 
    Document   : PestadorCaixaEntrada
    Created on : 01/09/2015, 10:18:13
    Author     : Elvin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Custom styles for this template -->
        <link href="../../bootstrap/dist/css/jumbotron.css" rel="stylesheet" type="text/css"/>
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
        <!-- CSS de botton-->
        <style>
            #btn_abrir,#btn_excluir{
                height: 30px;
                margin-top: 2px;
                margin-bottom: 2px;
            }
        </style>
    </head>

    <body>
        <div class="table-responsive" id="div_caixa_entrada">
            <table class="table-bordered table-striped col-lg-12 table-responsive">
                <tr>
                    <th id="rmt">Remetente</th> <th id="asst">Assunto</th> <th id="data">Data</th><th id="acao">Ação</th>
                </tr>
               
                <c:if test="${requestScope.user.entrada != null}">
                        <c:forEach var="list" items="${requestScope.user.entrada.mensagens}">
                            <tr>
                                <td style="text-align: center">${list.remetente}</td> 
                                <td style="text-align: center">${list.assunto}</td>
                                <td style="text-align: center">${list.data_msg}</td>
                                <td style="text-align: center">
                                    <button type="button" value="Abrir" id="btn_abrir" class="btn btn-info" 
                                            onclick="abrirMsg(${list.id})">Abrir</button>
                                    <button type="button" value="Excluir" id="btn_excluir"  class="btn btn-info" 
                                            onclick="excluirMensagemFront('Excluir',${requestScope.user.entrada.id},${list.id})">Excluir</button>
                                </td>
                            </tr>
                        </c:forEach>
                   </c:if>
            </table>
            <c:if test="${requestScope.user.entrada == null}">
                <span class="col-lg-12" style="text-align: center">Nenhuma mensagem encontrada na Caixa de Entrada.</span> 
            </c:if>
        </div>
        <div id="mensagem" title="Status da sua ação" style="display:none"> 
            <p>Mensagem excluída com sucesso!</p>
        </div>


         <div class="alert alert-info alert-dismissible" role="alert" id='div_fail' hidden="false">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;
                 </span>
                   Ocorreu algum erro na exclusão da mensagem tente mais tarde!
                </button>
        </div>
                      
        <!-- Scripts da Pagina -->
        <!-- Importando jquery-->
          <!--<script src="../../js/libs/jquery-1.11.1.min.js"></script>
        <script src="../../js/libs/jquery-ui.min.js"></script>-->
         <!--implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/collapse.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
         <!-- Scripts personalizados -->
       <!-- <script src="../../js/ajaxFuntions.js"></script>-->
        
    </body>
</html>
