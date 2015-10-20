<%-- 
    Document   : ClientePedidos
    Created on : 16/09/2015, 16:21:57
    Author     : java
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Meus Pedidos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- Implementacao de CSS para Bootstrap tag input  -->
        <link href="../../bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.css">
        <link href="../../js/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- <div class="container"> -->
        <!-- <div class=""> -->
        <table data-toggle="table" 
               data-show-columns="true" data-pagination="true" data-search="true" data-select-item-name="toolbar1" data-sort-order="desc">
            <!-- Colunas da tabela -->
            <thead>
                <tr>
                    <th>
                        <h4>Descrição</h4>
                    </th>
                    <th data-field="Data" data-sortable="true" class="text-center">
                        <h4>Data</h4>
                    </th>
                    <th data-field="Status" data-sortable="true" class="text-center">
                        <h4>Status</h4>
                    </th>
                    <th class="text-center">
                        <h4>Ação 
                        <span class="glyphicon glyphicon-question-sign"></span>
                        </h4>
                    </th>
                    <th class="text-center" data-field="Inte" data-sortable="true">
                        <h4>Interessados</h4>
                    </th>
                </tr>
            </thead>
            <!-- Linhas da Tabela -->
            <c:forEach var="pedido" items="${requestScope.pedidos}">
                <tr>
                    <td>${pedido.descricao}</td>
                    <td><f:formatDate pattern="dd/MM/yyyy" value="${pedido.data.getTime()}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${pedido.status eq 'EM_PROCESSO'}">
                                <label class="label label-primary">${pedido.status}</label>
                            </c:when>
                            <c:when test="${pedido.status eq 'ABERTO'}">
                                <label class="label label-success">${pedido.status}</label>
                            </c:when>
                            <c:when test="${pedido.status eq 'FECHADO'}">
                                <label class="label label-default">${pedido.status}</label>
                            </c:when>
                            <c:otherwise>
                                <label class="label label-danger">${pedido.status}</label>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <!-- dropdown com as acoes -->
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownAcao" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="true">
                                <span class="glyphicon glyphicon-pushpin"></span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownAcao">
                                <c:choose>
                                    <c:when test="${pedido.status eq 'CANCELADO'}">
                                        <li class="text-center">
                                            <a href="/Ecommerce/JSP/Cliente/ConsultaPedido?txtId=${pedido.id}" class="consultaPedido" id="btnConsultaPedido">
                                                Detalhes <span class="glyphicon glyphicon-info-sign"></span>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:when test="${pedido.status eq 'FECHADO'}">
                                        <li class="text-center">
                                            <a href="/Ecommerce/JSP/Cliente/ConsultaPedido?txtId=${pedido.id}" class="consultaPedido" id="btnConsultaPedido">
                                                Detalhes <span class="glyphicon glyphicon-info-sign"></span>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="text-center">
                                            <a href="/Ecommerce/JSP/Cliente/ConsultaPedido?txtId=${pedido.id}" class="consultaPedido" id="btnConsultaPedido">
                                                Detalhes <span class="glyphicon glyphicon-info-sign"></span>
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <!-- Atualizar -->
                                <c:choose>
                                    <c:when test="${pedido.status eq 'CANCELADO'}">
                                        
                                    </c:when>
                                    <c:when test="${pedido.status eq 'FECHADO'}">
                                        
                                    </c:when>
                                    <c:otherwise>
                                        <li class="text-center">
                                            <a href="/Ecommerce/JSP/Cliente/FiltroAtualizarPedido?txtId=${pedido.id}" class="filtroAtualiza" data-titulo="Atualizar Pedido">
                                                Atualizar
                                                <span class="glyphicon glyphicon-refresh"></span>
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <!-- Excluir -->
                                <li class="text-center">
                                    <form method="post" action="DesativarPedido">
                                        <input type="text"  name="operacao" value="Excluir" hidden="true"/>
                                        <input type="text"  name="txtId" value="${pedido.id}" hidden="true"/>
                                        <c:choose>
                                            <c:when test="${pedido.status eq 'CANCELADO'}">
                                                
                                            </c:when>
                                            <c:when test="${pedido.status eq 'FECHADO'}">
                                                <button type="" class="btn btn-default btn-link disabled">
                                                    Cancelar
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-default btn-link">
                                                    Cancelar
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </td>

                    <td>
                        <c:if test="${pedido.status.toString() eq 'ABERTO'}">
                            <label hidden="true">
                                ${pedido.qtdeInteressados}
                            </label>
                        </c:if>

                        <c:choose>
                            <c:when test="${pedido.status.toString() eq 'EM_PROCESSO' or pedido.status.toString() eq 'CANCELADO' or pedido.status.toString() eq 'FECHADO'}">
                                <a href="/Ecommerce/JSP/Cliente/ConsultaInscritos?txtId=${pedido.id}" class="btn btn-default disabled" id="buttonInscritos" data-titulo="Inscritos">
                                    Inscritos
                                    <span class="glyphicon glyphicon-star"></span>
                                </a>
                            </c:when>
                            <c:when test="${pedido.qtdeInteressados eq 0}">
                                <button class="btn btn-default" disabled="true">
                                    <span class="badge">${pedido.qtdeInteressados} </span>
                                    Inscritos
                                    <span class="glyphicon glyphicon-star-empty"></span>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <a href="/Ecommerce/JSP/Cliente/ConsultaInscritos?txtId=${pedido.id}" class="btn btn-default consultaInscritos" data-titulo="Inscritos">
                                    <span class="badge">${pedido.qtdeInteressados} </span>
                                    Inscritos
                                    <span class="glyphicon glyphicon-star"></span>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <!-- </div> -->
        <!-- </div> -->
        <script src="../../js/libs/bootstrap-table/bootstrap-table.min.js" type="text/javascript"></script>       
        <script src="../../bootstrap/js/popover.js" type="text/javascript"></script>
        <script src="../../bootstrap/js/tooltip.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {

                $('#pesquisa').popover(
                        {
                            animation: true,
                            content: 'Digite qualquer valor da tabela',
                            placement: 'top',
                            title: 'Pesquisa',
                            trigger: 'hover focus'
                        });

                $('#colunas').popover(
                        {
                            animation: true,
                            content: 'Selecionador de Colunas',
                            placement: 'top',
                            title: 'Colunas',
                            trigger: 'hover focus'
                        });

                $('.glyphicon-question-sign').popover(
                        {
                            animation: true,
                            content:'Selecione uma ação para esse pedido',
                            placement: 'top',
                            title: 'Olá',
                            trigger: 'hover focus'
                        });
            });
        </script>
    </body>
</html>
