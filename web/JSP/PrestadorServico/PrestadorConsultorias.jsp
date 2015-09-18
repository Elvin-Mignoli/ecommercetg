<%-- 
    Document   : PrestadorConsultorias
    Created on : 18/09/2015, 11:31:27
    Author     : Elvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <title>Minhas consultorias</title>
    </head>
    <body>
         <div class="table-responsive" id="div_candidaturas">
            <table class="table-bordered table-striped col-lg-12 table-responsive">
                <tr>
                    <th id="desc">Descrição</th> <th id="cliente">Cliente</th> <th id="status">Status</th><th>Acão</th>
                </tr>
                <c:if test="${requestScope.ListaPedido.pedidos ne null}"><!--A lista de pedidos esta vazia?-->
                    <!--não está vazia -->
                    <!--ForEach para percorrer todos pedidos -->
               
                    <c:forEach var="list" items="${requestScope.ListaPedido.pedidos}">
                        <tr>
                            <td style="text-align: center">${list.descricao}</td> 
                            <td style="text-align: center">${list.cliente.nome}&nbsp;${list.cliente.sobrenome}</td>
                            <td style="text-align: center">${list.status}</td>
                            <td style="text-align: center">
                                <a href="ConsultarPedido?id_pedido=${list.id}" value="Ver" name="btn_ver"  class="btn btn-info ver"  >Ver</a>
                            </td>
                        </tr>
            
                    </c:forEach>
                </c:if>
            </table>       
            <c:if test="${requestScope.ListaPedido.pedidos == null}">
                <span class="col-lg-12" style="text-align: center">Nenhuma candidatura até esse momento.</span> 
            </c:if>
        </div>
       
       <!-- Arquivos bootstrap da página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
        <script src="../../bootstrap/js/modal.js" type="text/javascript"></script>
        <!-- Implementando script de load de paginas de funcoes -->
        <script src="../../js/ajaxFuntions.js"></script>
        <!--<script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>-->
    </body>
</html>
