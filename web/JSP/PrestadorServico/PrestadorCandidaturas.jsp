<%-- 
    Document   : PrestadorConsultoriasPendentes
    Created on : 18/09/2015, 11:31:44
    Author     : Elvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
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
        <title>Candidaturas</title>
    </head>
    <body>
         <div >
            <table data-toggle="table" data-show-columns="true" data-pagination="true" data-search="true" 
                data-select-item-name="toolbar1" data-sort-order="desc" >
                <thead>
                <tr>
                    <th id="desc" data-sortable="true">Descrição</th> 
                    <th id="cliente" data-sortable="true">Cliente</th> 
                    <th id="data_consultoria"data-sortable="true" class="text-center">Data Canditatura</th>
                    <th id="status" data-sortable="true">Status</th>
                    <th>Acão  <span class="glyphicon glyphicon-question-sign"></span></th>
                </tr>
                </thead>
                <c:if test="${requestScope.ListaPedido.pedidos ne null}"><!--A lista de pedidos esta vazia?-->
                    <!--não está vazia -->
                    <!--ForEach para percorrer todos pedidos -->
                    <c:forEach var="list" items="${requestScope.ListaPedido.pedidos}">
                        <!--ForEach para percorrer os interessados pelo pedido -->
                        <c:forEach var="prestador" items="${list.prestadores}">
                            <tr>
                                <td style="text-align: center">${list.descricao}</td> 
                                <td style="text-align: center">${list.cliente.nome}&nbsp;${list.cliente.sobrenome}</td>
                                <td style="text-align: center"><f:formatDate pattern="dd/MM/yyyy"  value="${prestador.data.getTime()}"></f:formatDate></td>
                                <td style="text-align: center">${prestador.candidatura}</td>
                               
                                <td style="text-align: center">
                                  <a href="ConsultarPedido?id_pedido=${list.id}&local=candidaturas" value="Ver" id="btn_Ver" class="btn btn-info ver" >Ver</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:if>
            </table>
            
        </div>
        <script src="../../js/libs/bootstrap-table/bootstrap-table.min.js" type="text/javascript"></script>
        <script>
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
    </script>
    </body>
   
</html>
