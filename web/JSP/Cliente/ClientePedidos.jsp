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
    </head>
    <body>
        <div class="container">
            <table class="table table-hover">
                <!-- Colunas da tabela -->
                <tr>
                    <th>
                        Descrição
                    </th>
                    <th>
                        Data
                    </th>
                    <th>
                        Status
                    </th>
                    <th class="text-center">
                        Detalhes
                    </th>
                    <th class="text-center">
                        Atualizar
                    </th>
                    <th class="text-center">
                        Desativar
                    </th>
                    <th class="text-center">
                        Interessados
                    </th>
                </tr>
                <!-- Linhas da Tabela -->
                <c:forEach var="pedido" items="${requestScope.pedidos}">
                    <tr>
                        <td>${pedido.descricao}</td>
                        <td><f:formatDate pattern="dd/MM/yyyy" value="${pedido.data.getTime()}" /></td>
                        <td>${pedido.status}</td>
                        <td>
                            <form method="post" action="ConsultarPedido">
                                <input type="text" name="operacao" value="Consultar" hidden="true"/>
                                <input type="text" name="txtId" value="${pedido.id}" hidden="true"/>
                                <button class="btn btn-primary">
                                    Detalhes <span class="glyphicon glyphicon-info-sign"></span>
                                </button>
                            </form>
                        </td>
                        <td>
                            <!-- <form method="post" action="FiltroAtualizarPedido"> 
                                <input type="text" name="operacao" value="Atualizar" hidden="true"/>
                                <input type="text" name="txtId" value="${pedido.id}" hidden="true"/>
                                <input type="text" name="txtDescricao" value="${pedido.descricao}" hidden="true"/>
                                <input type="text" name="txtHabilidadesRequeridas" value="${pedido.habilidadePrestador.toString().replace("[","").replace("]","")}" hidden="true"/>
                                <input type="text" name="txtMinhaHabilidade" value="${pedido.habilidadeCliente.toString().replace("[","").replace("]","")}" hidden="true"/>
                                <input type="text" name="txtDataInicio" value="<f:formatDate pattern="dd/MM/yyy" value="${pedido.dataInicio}"/>" hidden="true"/>
                                <input type="text" name="txtDataFim" value="<f:formatDate pattern="dd/MM/yyy" value="${pedido.dataFim}"/>" hidden="true"/>
                                <input type="text" name="txtHora" value="<f:formatDate pattern="HH:mm:ss" value="${pedido.horaConsultoria.getTime()}"/>" hidden="true"/> -->
                                
                                <a href="/Ecommerce/JSP/Cliente/FiltroAtualizarPedido?txtId=${pedido.id}" class="btn btn-success filtroAtualiza" data-titulo="Atualizar Pedido">
                                    Atualizar
                                    <span class="glyphicon glyphicon-refresh"></span>
                                </a>
                            <!-- </form> -->
                        </td>
                        <td>
                            <form method="post" action="DesativarPedido">
                                <input type="text"  name="operacao" value="Excluir" hidden="true"/>
                                <input type="text"  name="txtId" value="${pedido.id}" hidden="true"/>
                                <button type="submit" class="btn btn-danger">
                                    Desativar
                                    <span class="glyphicon glyphicon-trash"></span>
                                </button>
                            </form>
                        </td>
                        <td>
                            <a class="btn btn-default">
                                Inscritos
                                <span class="glyphicon glyphicon-star"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>
    </body>
</html>
