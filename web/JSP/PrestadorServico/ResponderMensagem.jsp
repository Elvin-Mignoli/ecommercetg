<%-- 
    Document   : EnviarMensagem
    Created on : 03/09/2015, 16:57:29
    Author     : Elvin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Custom styles for this template -->
        <link href="../../bootstrap/dist/css/jumbotron.css" rel="stylesheet" type="text/css"/>
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
         <div class="table-responsive">
            <Form method="POST" action="ResponderMensagem">
            <table class="table table-responsive">
                
                <c:forEach var="list" items="${sessionScope.user.entrada.mensagens}">
                    <c:if test="${list.id == param.id}">
                        <!--Assunto da mensagem -->
                        <tr>
                            <th class="segoe-ui-light-font" id="assunto">Re:&nbsp;${list.assunto}</th>
                        </tr>  
                        <!--Remetente e a data da mensagem -->
                        <tr>
                            <td class="segoe-ui-font" id="remetente">&nbsp;De:&nbsp;${sessionScope.user.email} - <f:formatDate pattern="dd/MM/yyyy" value="${list.data_msg}"></f:formatDate></td>
                        </tr>
                        <!--para: -->
                        <tr>
                            <td class="segoe-ui-font" id="destinatario">
                                &nbsp;Para:&nbsp;${list.remetente}
                            </td>
                        </tr>
                        <!--body da mensagem -->
                        <tr>
                            <td  id="descricao">
                                <input type="text" name="txtResposta" id="input_resposta" class="form-control"  style="width:830px; height:100px">
                            </td>
                        </tr>
                    
                        <td>
                            
                                <input type="hidden" name="operacao" value="Atualizar"/>
                                <input type="hidden" name="txtDestinatario" value="${list.remetente}"/>
                                <input type="hidden" name="txtRemetente_id" value="${list.id_caixa_remetente}"/>
                                <input type="hidden" name="txtAssunto" value="${list.assunto}"/>
                                <button type="submit" class="btn btn-success"   value="Responder">Responder</button>
                        </td
                    </c:if>
                </c:forEach>
            </table>
            </Form>
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
        <!--<script src="../../js/ajaxFuntions.js"></script>-->
        
    </body>
</html>
