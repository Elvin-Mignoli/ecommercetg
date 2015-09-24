<%-- 
    Document   : ClienteDetalhePedido
    Created on : 23/09/2015, 20:35:36
    Author     : java
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <title>Pedido</title>
    </head>
    <body>
        <div class="container">
            <div class="panel panel-success">
                <div class="panel panel-heading text-center">
                    <h4>Pedido</h4>
                </div>
                <div class="panel panel-body">

                    <div class="row">
                        <div class="col-sm-10">
                            <h3>
                                <span class="glyphicon glyphicon-list-alt"></span>
                                Descrição:
                                <span class="label label-primary">
                                    ${requestScope.pedido.descricao}
                                </span>
                            </h3>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-3">
                            <h3>
                                <span class="glyphicon glyphicon-calendar"></span>
                                Início
                                <span class="label label-primary">
                                    <f:formatDate value="${requestScope.pedido.dataInicio}" pattern="dd/MM/yyyy"/>
                                </span>
                            </h3>
                        </div>
                        <div class="col-sm-3">
                            <h3>
                                <span class="glyphicon glyphicon-calendar"></span>
                                Fim
                                <span class="label label-primary">
                                    <f:formatDate value="${requestScope.pedido.dataFim}" pattern="dd/MM/yyyy"/>
                                </span>
                            </h3>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-5">
                            <h3>
                                <span class="glyphicon glyphicon-time"></span>
                                Hora Consultoria:
                                <span class="label label-primary">
                                    <f:formatDate pattern="HH:mm" value="${requestScope.pedido.horaConsultoria.getTime()}"/>
                                </span>
                            </h3>
                        </div>
                    </div>  

                    <div class="row">
                        <div class="col-md-10">
                            <h3>
                                <span class="glyphicon glyphicon-flash"></span>
                                Habilidades Requiridas: 
                                <c:forEach var="hbr" items="${requestScope.pedido.habilidadePrestador}">
                                    <span class="label label-success">
                                        ${hbr}
                                    </span>
                                </c:forEach>
                            </h3>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-10">
                            <h3>
                                <span class="glyphicon glyphicon-tasks"></span>
                                Minhas Habilidades:
                                <c:forEach var="mhb" items="${requestScope.pedido.habilidadeCliente}">
                                    <span class="label label-info">
                                        ${mhb}
                                    </span>
                                </c:forEach>
                            </h3>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-10">
                            <h3>
                                <span class="glyphicon glyphicon-user"></span>
                                Consultor(a):
                                <c:choose>
                                    <c:when test="${requestScope.pedido.prestadorFinalista.id gt 0}">
                                        <span class="label label-success">
                                            ${requestScope.pedido.prestadorFinalista.nome} ${requestScope.pedido.prestadorFinalista.sobrenome}
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="label label-success">
                                            Nenhum prestador selecionado para esse Pedido!
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </h3>
                        </div>
                    </div> 
                </div>
                <div class="panel panel-footer">
                    <!--Form pra mandar a mensagem -->
                    <div id="div_mensagem" hidden="true">
                        <form action="EnviarMensagem" method="POST">
                            <input type="hidden" name="operacao" value="Atualizar"/>
                            <input type="hidden" name="txtDestinatario" value="${requestScope.pedido.cliente.email}"/>
                            <input type="hidden" name="txtCaixaEntrada" value="${requestScope.pedido.cliente.entrada.id}"/>
                            <!-- Assunto-->
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon " id="span_msg">Assunto: </span>
                                    <input type="text"  id="input_msg" name="txtAssunto" class="form-control">
                                </div>
                            </div>
                            <!-- Mensagem-->
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon " id="span_msg">Mensagem: </span>
                                    <input type="text"  id="input_msg" name="txtMensagem" class="form-control">
                                </div>
                            </div>
                            <button class="btn btn-success" type="submit">Enviar</button>
                        </form>            
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-2">
                            <button class="btn btn-primary">
                                Video Chat
                                <span class="glyphicon glyphicon-film"></span>
                            </button>
                        </div>
                        <div class="col-md-2">
                            <!-- <c:if test="${requestScope.pedido.prestadorFinalista.id eq 0}">disabled="true"</c:if> -->
                            <button class="btn btn-success" id="btnMensagem">
                                Mensagem
                                <span class="glyphicon glyphicon-send"></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function ()
            {
                $('#btnMensagem').on('click', function ()
                {
                    $('#div_mensagem').attr("hidden", false);
                    $('#btnMensagem').attr('disabled',true);
                });                
            });
        </script>
    </body>
</html>
