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
       
        
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <link href="../../js/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
        <title>Minhas consultorias</title>
    </head>
    <body>
         <div>
            <table data-toggle="table">
                <thead>
                <tr>
                    <th id="desc">Descrição</th> <th id="cliente">Cliente</th> <th id="status">Status</th><th>Acão</th>
                </tr>
                </thead>
                <c:if test="${requestScope.ListaPedido.pedidos ne null}"><!--A lista de pedidos esta vazia?-->
                    <!--não está vazia -->
                    <!--ForEach para percorrer todos pedidos -->
               
                    <c:forEach var="list" items="${requestScope.ListaPedido.pedidos}">
                        <tr>
                            <td style="text-align: center">${list.descricao}</td> 
                            <td style="text-align: center">${list.cliente.nome}&nbsp;${list.cliente.sobrenome}</td>
                            <td style="text-align: center">${list.status}</td>
                            <td style="text-align: center">
                                <a href="ConsultarPedido?id_pedido=${list.id}&local=vazio" value="Ver" name="btn_ver"  class="btn btn-info ver" data-titulo="Pedido" >Ver</a>
                            </td>
                        </tr>
            
                    </c:forEach>
                </c:if>
            </table>       
            
        </div>
       
        <script src="../../js/libs/bootstrap-table/bootstrap-table.min.js" type="text/javascript"></script>
    </body>
</html>
