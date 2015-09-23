<%-- 
    Document   : PrestadorConsultarPedido
    Created on : 18/09/2015, 15:29:02
    Author     : Elvin
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
       
        <!-- Custom styles for this template -->
        <link href="../../bootstrap/dist/css/jumbotron.css" rel="stylesheet" type="text/css"/>
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
        <title>Pedido</title>
    </head>
    <body>
         <div class="container-fluid">
            <div class="row-fluid ">
                <div class="panel panel-success col-lg-12">
                    <div class="panel panel-heading text-center">Pedido</div>
                    <div class="panel-body">
                        <!-- Cliente-->
                        <br>
                        <span><b>Cliente:&nbsp;</b>${requestScope.pedido.cliente.nome}&nbsp;${requestScope.pedido.cliente.sobrenome}</span>
                        <br>
                        <!-- Span descrição do pedido-->
                        <span><b>Descrição:</b> &nbsp;${requestScope.pedido.descricao}</span><br>
                        <div class="text-center">
                            <span class="text-center"><b>Datas da consultoria:</b></span><br><br>
                        </div>
                        <!--span da datas do pedidos -->
                        <span><b>Data de início: &nbsp;</b><f:formatDate pattern="dd/MM/yyyy" value="${requestScope.pedido.dataInicio}"></f:formatDate>
                            &nbsp;&nbsp;&nbsp;&nbsp;<b>Data de fim:&nbsp;</b><f:formatDate pattern="dd/MM/yyyy" value="${requestScope.pedido.dataFim}"></f:formatDate></span>
                        <br>
                        <!-- hora da consultoria-->
                        <br>
                        <span><b>Hora:&nbsp;</b><f:formatDate type="time"  value="${requestScope.pedido.horaConsultoria.getTime()}"></f:formatDate></span>
                        <br>
                        <!--span das habilidades requeridas pelo cliente -->
                        <span><b>Habilidades requeridas: &nbsp;</b>${requestScope.pedido.habilidadePrestador.toString().replace("[","").replace("]","")}</span>
                        <br>
                        <span><b>Habilidades do cliente:&nbsp;</b>${requestScope.pedido.habilidadeCliente.toString().replace("[","").replace("]","")}</span>
                        <br>
                        <br>
                        <span><b>Consultor/A:&nbsp;</b>${requestScope.pedido.prestadorFinalista.nome} &nbsp;${requestScope.pedido.prestadorFinalista.sobrenome}</span>
                        <br>
                        <br>
                        <a href="PrestadorVideoConferencia.jsp?canal=${requestScope.pedido.canal}" class="btn btn-success">Vídeo Conferência</a>
                        <br> <br>
                        <button type="button" id="btn_enviar" class="btn btn-success" onclick="showButtonMsg()">Enviar Mensagem para o cliente</button>
                        <br><br>
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
                    </div>
                </div>
            </div>
        </div>
                        <script src="../../js/ajaxFuntions.js" type="text/javascript"></script>
    </body>
</html>
