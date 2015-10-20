<%-- 
    Document   : PestadorMensagensEnvidas
    Created on : 01/09/2015, 10:18:13
    Author     : Elvin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <link href="../../js/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
        <!-- CSS de botton-->
        
    </head>

    <body>
        <div>
            <table data-toggle="table" data-pagination="true" data-search="true">
                <thead>
                <tr>
                    <th id="rmt">Destinatario</th> <th id="asst">Assunto</th> <th id="data">Data</th><th id="acao">Ação</th>
                </tr>
                </thead>
                <c:if test="${!requestScope.user.entrada.mensagens.isEmpty()}">
                        <c:forEach var="list" items="${requestScope.user.entrada.mensagens}">
                                <tr>
                                    <td style="text-align: center">${list.destinatario}</td>
                                    <c:if test="${list.flg_resposta == true}">
                                         <td style="text-align: center">Re:${list.assunto}</td>
                                    </c:if>
                                    <c:if test="${list.flg_resposta == false}">
                                        <td style="text-align: center">${list.assunto}</td>                                
                                    </c:if>
                                    <td style="text-align: center"><f:formatDate pattern="dd/MM/yyyy" value="${list.data_msg}"></f:formatDate></td>
                                    <td style="text-align: center">
                                        <button type="button" value="Abrir" id="btn_abrir" class="btn btn-info" 
                                                onclick="abrirMsgCliente(${list.id},'enviadas')">Abrir</button>
                                        <button type="button" value="Excluir" id="btn_excluir"  class="btn btn-info" 
                                                onclick="excluirMsgEnviadasFrontCliente('Excluir',${requestScope.user.entrada.id},${list.id})">Excluir</button>
                                    </td>
                                </tr>
                        </c:forEach>
                   </c:if>
            </table>
           
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
                      
        
        <script src="../../js/libs/bootstrap-table/bootstrap-table.min.js" type="text/javascript"></script>
    </body>
</html>
