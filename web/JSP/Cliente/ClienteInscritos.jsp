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
        <link href="../../js/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <div class="container">
            <div class="table-responsive">
                <table data-toggle="table">
                    <!-- cabecalho -->
                    <thead>
                        <tr>
                            <th>Prestador</th>
                            <th>Ranking</th>
                            <th>Selecionar</th>
                        </tr>
                    </thead>
                    <!-- Fim do cabecalho -->

                    <!-- Linhas da tabela -->
                    <c:forEach var="prestador" items="${requestScope.pedido.prestadores}">
                        <tr>
                            <td>${prestador.nome} ${prestador.sobrenome}</td>
                            <td>4</td>
                            <td>
                                <form method="post" action="SelecionarPrestador">
                                    <input type="text" name="txtIdPrestador" value="${prestador.id}" hidden="true"/>
                                    <input type="text" name="txtId" value="${pedido.id}" hidden="true"/>
                                    <button class="btn btn-success">
                                        <span class="glyphicon glyphicon-ok"></span>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <script src="../../bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../../js/libs/bootstrap-table/bootstrap-table.min.js" type="text/javascript"></script>
    </body>
</html>
