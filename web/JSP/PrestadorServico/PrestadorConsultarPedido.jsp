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
                    </div>     
                </div>
            </div>
        </div>
        <!-- Arquivos bootstrap da página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
        <script src="../../bootstrap/js/modal.js" type="text/javascript"></script>
        <!-- Implementando script de load de paginas de funcoes -->
        <!--<script src="../../js/ajaxFuntions.js"></script>-->
    </body>
</html>
