<%-- 
    Document   : ClienteInscritos
    Created on : 21/09/2015, 22:29:23
    Author     : Felipe Monteiro
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Inscritos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- Implementacao de CSS para Bootstrap tag input  -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <link href="../../js/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../js/libs/bootstrap-star-rating/css/star-rating.min.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        
            <div class="table-responsive">
                <table data-toggle="table" data-pagination="true">
                    <!-- cabecalho -->
                    <thead>
                        <tr>
                            <th>Prestador</th>
                            <th>Ranking</th>
                            <th>Valor</th>
                            <th>Selecionar</th>
                        </tr>
                    </thead>
                    <!-- Fim do cabecalho -->

                    <!-- Linhas da tabela -->
                    <c:forEach var="prestador" items="${requestScope.pedido.prestadores}">
                        <tr>
                            <td class="" onclick="alert('ola');">${prestador.nome} ${prestador.sobrenome}</td>
                            <td><input type="number" class="rating" min="0" max="5" value="${prestador.ranking}" data-size="xs" disabled="true"/></td>
                            <td><h1 class="label label-success">R$ <f:formatNumber pattern="#,###.##" value="${prestador.valorConsultoria}"></f:formatNumber></h1></td>
                            <td>
                                <form method="post" action="SelecionarPrestador" class="form-prestador">
                                <input type="text" name="txtIdPrestador" id="txtIdPrestador" value="${prestador.id}" hidden="true" class="id-prestador"/>
                                <input type="text" name="txtValorConsultoria" id="txtValorConsultoria" value="${prestador.valorConsultoria}" hidden="true"/>
                                    <input type="text" name="txtIdPedido" id="txtIdPedido" value="${pedido.id}" hidden="true"/>
                                    <button type="button" class="btn btn-success btn-prestador" onclick="selecionarPrestador(${prestador.id},${prestador.valorConsultoria})">
                                        <span class="glyphicon glyphicon-ok"></span>
                                        Selecionar
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        
        
        <script src="../../js/libs/bootstrap-table/bootstrap-table.min.js" type="text/javascript"></script>
        <script src="../../js/libs/bootstrap-star-rating/js/star-rating.min.js" type="text/javascript"></script>
    </body>
</html>
