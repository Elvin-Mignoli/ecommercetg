<%-- 
    Document   : OpenMensagem
    Created on : 01/09/2015, 12:26:22
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
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
    
    </head>
    
    <body>
        <div class="table-responsive">
            <table class="table table-responsive">
                <c:forEach var="list" items="${sessionScope.user.entrada.mensagens}">
                    <c:if test="${list.id == param.id}">
                        <!--Assunto da mensagem -->
                        <tr>
                            <th class="segoe-ui-light-font" id="assunto">&nbsp;${list.assunto}</th>
                        </tr>  
                        <!--Remetente e a data da mensagem -->
                        <tr>
                            <td class="segoe-ui-font" id="remetente">&nbsp;De:&nbsp;${list.remetente} - <f:formatDate pattern="dd/MM/yyyy" value="${list.data_msg}"></f:formatDate></td>
                        </tr>
                        <!--para: -->
                        <tr>
                            <td class="segoe-ui-font" id="destinatario">
                                &nbsp;Para:&nbsp;${list.destinatario}
                            </td>
                        </tr>
                        <!--body da mensagem -->
                        <tr>
                            <td class="calibri-font" id="descricao">
                                &nbsp;${list.descricao}
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                    <c:if test="${param.acao == 'entrada'}">
                <tr>
                    <td>
                        <button type="button" class="btn btn-success"  value="Responder" id="bt_responder" onclick="responderMsgCliente(${param.id})">Responder</button>
                    </td>
                </tr>
                    </c:if>
                <tr>
                    <td>
                        <Form method="POST" action="ExcluirMensagem">
                            <c:if test="${param.acao == 'entrada'}">
                                <input type="hidden" name="local" value="entrada"/>
                            </c:if>
                            <c:if test="${param.acao == 'enviadas'}">
                                <input type="hidden" name="local" value="enviadas"/>
                            </c:if>
                            <input type="hidden" name="operacao" value="Excluir"/>
                            <input type="hidden" name="txtId" value="${param.id}"/>
                            <input type="hidden" name="txtId_caixa_entrada" value="${sessionScope.user.entrada.id}"/>
                            <button type="button" class="btn btn-success"    value="Excluir" id="bt_excluir" onclick="excluirMensagem()">Excluir</button>
                        </Form>
                    </td>
                </tr>
                
            </table>
        </div>
                            
       
        
    </body>
</html>
