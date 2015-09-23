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
        <link href="../../bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.css">
    </head>

    <body>
        <div class="container">
            <div class="table-responsive">
                <table class="table">
                    <!-- cabecalho -->
                    <tr>
                        <th>Prestador</th>
                        <th>Data</th>
                        <th>Recomendacoes</th>
                        <th>Mensagem</th>
                        <th>Ranking</th>
                        <th>Selecionar</th>
                        <th>Descartar</th>
                    </tr>
                    <!-- Fim do cabecalho -->

                    <!-- Linhas da tabela -->
                    <c:forEach var="prestador" items="${requestScope.prestadores}">
                        <tr>
                            <td>${requestScope.prestador.nome}</td>
                            <td>26/08/1992</td>
                            <td>1</td>
                            <td>
                                <form method="post" action="SalvarMensagem">
                                    <input type="text" name="operacao" value="Salvar" hidden="true"/>
                                    <button type="submit" class="btn btn-info">
                                        <span class="glyphicon glyphicon-envelope"></span>
                                        Enviar
                                    </button>
                                </form>
                            </td>
                            <td>4</td>
                            <td>
                                <form method="post" action="SelecionarPrestador">
                                    <input type="text" name="operacao" value="Atualizar" hidden="true" />
                                    <button class="btn btn-success">
                                        Selecionar
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
