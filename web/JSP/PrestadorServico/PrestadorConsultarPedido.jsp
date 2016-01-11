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
       
        
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
        <!--css bootstrap table -->
        <link href="../../js/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
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
                        <!-- Span descriÃ§Ã£o do pedido-->
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
                        <span><b>Status:&nbsp;</b>${requestScope.pedido.status}</span>
                        <br>
                        <br>
                        <c:if test="${param.local != 'candidaturas'}">
                            <form method="POST" action="Notify">
                                <input type="hidden" name='idCliente' value="${requestScope.pedido.cliente.id}"/>
                                <input type="hidden" name='idPrestador' value="${requestScope.pedido.prestadorFinalista.id}"/>
                                <input type="hidden" name='idPedido' value="${requestScope.pedido.id}"/>
                                <input type="hidden" name='Canal' value="${requestScope.pedido.canal}"/>
                            <button  type='submit' class="btn btn-success 
                               <c:if test="${requestScope.pedido.status != 'FECHADO'}">disabled</c:if>"
                               >Video Chat 
                            <span class="glyphicon glyphicon-film"></span></button>
                            </form>
                        <br> <br>
                        <button type="button" id="btn_enviar" class="btn btn-success
                               <c:if test="${requestScope.pedido.status != 'FECHADO'}"></c:if>" onclick="showButtonMsg()">Mensagem
                         <span class="glyphicon glyphicon-send"></span></button>
                        <br><br>
                        
                            <!--Form pra mandar a mensagem -->
                            <div id="div_mensagem" hidden="true">
                                <form action="EnviarMensagem" method="POST">
                                    <input type="hidden" name="operacao" value="Atualizar"/>
                                    <input type="hidden" name="txtDestinatario" value="${requestScope.pedido.cliente.email}"/>
                                    <input type="hidden" name="txtCaixaEntrada" value="${requestScope.pedido.cliente.entrada.id}"/>
                                    <input type="hidden" name="txtId" value="${requestScope.pedido.id}"/>
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
                        </c:if>
                        
                </div>
                </div>
            </div>
        </div>
        <c:if test="${param.local != 'candidaturas'}">                
        <div class="panel panel-info col-lg-12">
            <div class="panel panel-heading text-center">Histórico de mensagens</div>
            <div class="panel-body">
            <div class=" col-lg-12">
                <table data-toggle="table" data-show-columns="true" data-pagination="true" data-search="true" 
                       data-select-item-name="toolbar1" data-sort-order="desc" data-page-size="5">
                    <thead>
                        <tr>
                            <th id="rmt" data-sortable="true">Remetente</th> 
                            <th id="asst" data-sortable="true">Assunto</th> 
                            <th id="data" data-sortable="true" class="text-center">Data</th>
                          
                        </tr>
                    </thead>
                    <c:if test="${!requestScope.mensagens.isEmpty()}">
                    <c:forEach var="list" items="${requestScope.mensagens}">
                        <c:if test="${list.flg_excluida_recebido == false}">    
                            <tr>
                                <c:if test="${list.id_caixa_remetente == sessionScope.user.entrada.id}">
                                    <td style="text-align: center">Enviada: ${list.remetente}</td>
                                </c:if>
                                <c:if test="${list.id_caixa_remetente != sessionScope.user.entrada.id}">
                                    <td style="text-align: center">Recebida: ${list.remetente}</td>
                                </c:if>
                                <c:if test="${list.flg_resposta == true}">
                                    <td style="text-align: center">Re:${list.assunto}</td>
                                </c:if>
                                <c:if test="${list.flg_resposta == false}">
                                    <td style="text-align: center">${list.assunto}</td>                                
                                </c:if>
                                <td style="text-align: center"><f:formatDate pattern="dd/MM/yyyy" value="${list.data_msg}"></f:formatDate></td>
                                   
                            </tr>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </table>
                </div>
            </div>
        </div>
        </c:if>
         <script src="../../js/ajaxFuntions.js" type="text/javascript"></script>
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
       
         </script>
    </body>
</html>
