<%-- 
    Document   : ClienteDetalhePedido
    Created on : 23/09/2015, 20:35:36
    Author     : java
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <title>Pedido</title>
    </head>
    <body>
        <!-- Modal Hora -->
        <div class="modal fade" id="setHoraData" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Alterar Data e Hora da Consultoria</h4>
                    </div>
                    <form method="post" action="AtualizarDataHora">
                        <input type="text" name="txtId" id="txtId" value="${requestScope.pedido.id}" hidden="true"/>
                        <!-- Conteudo do Modal -->
                        <div class="modal-body">                            
                            <div class="row">
                                <div class="form-group col-lg-6">
                                    <h4>Data de início</h4>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span> Início
                                        </span>
                                        <input type="date" name="txtDataInicio" id="txtDataInicio" class="form-control" required="required" value="<f:formatDate value="${requestScope.pedido.dataInicio}" pattern="yyyy-MM-dd"/>"/>
                                    </div>
                                </div>
                                <div class="form-group col-lg-5">
                                    <h4>Data de término</h4>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span> Término
                                        </span>
                                        <input type="date" name="txtDataTermino" id="txtDataTermino" class="form-control" required="required" value="<f:formatDate value="${requestScope.pedido.dataFim}" pattern="yyyy-MM-dd"/>"/>
                                    </div>
                                </div>
                            </div>
                            <!-- Hora da consultoria -->
                            <div class="row">
                                <div class="form-group col-lg-6">
                                    <h4>Hora da Consultoria</h4>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-time"></span>
                                        </span>
                                        <input type="time" name="txtHora" id="txtHora" class="form-control" value="<f:formatDate value="${requestScope.pedido.horaConsultoria.getTime()}" pattern="HH:mm"/>" required="required"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                Fechar
                            </button>
                            <button type="button" class="btn btn-success" id="atualizaHoraData" onclick="atualizarDataHora()">
                                Atualizar
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Fim modal Data e Hora-->

        <!-- Inicio modal Transferência -->
        <div class="modal fade" id="transfModal" tabindex="-1" role="dialog" aria-labelledby="transfModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Selecionar Consultor</h4>
                    </div>
                    <div class="modal-body">
                        <!-- informacao da transacao -->
                        <div class="container-fluid">
                            <div class="alert alert-info">
                                <b>Atenção</b>!
                                Para selecionar este consultor como finalista é necessário efetuar a transferência bancária com o valor acordado entre as partes.
                            </div>

                            <div class="row-fluid">
                                <h4>Número do Cartão</h4>
                            </div>
                            <div class="row-fluid">
                                <div class="form-group">
                                    <div class="input-group col-lg-6">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-credit-card"></span>
                                        </span>
                                        <input type="text" name="txtNumeroCartao" id="txtNumeroCartao" value="${sessionScope.user.cartao.numeroCartao}" class="form-control" placeholder="Ex. 000111666555" required="required"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <h4>Código de Segurança</h4>
                            </div>
                            <div class="row-fluid">
                                <div class="form-group">
                                    <div class="input-group col-lg-6">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-lock"></span>
                                        </span>
                                        <input type="text" name="txtCodSeg" id="txtCodSeg" value="${sessionScope.user.cartao.numeroSeguranca}" class="form-control" placeholder="Ex. 111" required="required"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <h4>Nome do Titular</h4>
                            </div>
                            <div class="row-fluid">
                                <div class="form-group">
                                    <div class="input-group col-lg-6">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input type="text" placeholder="Nome do Titular" name="txtTitular" id="txtTitular" class="form-control" required="required"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <h4>Data de Validade</h4>
                            </div>
                            <div class="row-fluid">
                                <div class="form-group">
                                    <div class="input-group col-lg-6">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                        <input type="text" placeholder="ex. 01/15" name="txtValidade" id="txtValidade" class="form-control" required="required"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <h4>Valor da Transação</h4>
                            </div>
                            <div class="row-fluid">
                                <div class="form-group">
                                    <div class="input-group col-lg-6">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-usd"></span>
                                        </span>
                                        <input type="text" name="txtValor" id="txtValor" class="form-control" placeholder="1.200,00" required="required"/>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                Eu aceito os termos de uso &nbsp;<input type="checkbox" id="txtTermo" name="txtTermo" required="required"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            Fechar
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                        <button type="button" class="btn btn-success">
                            Transferir
                            <span class="glyphicon glyphicon-transfer"></span>
                        </button>                        
                    </div>
                </div>
            </div>
        </div>
        <!-- Fim modal Transferência -->
        <div class="container">
            <div class="panel panel-success">
                <div class="panel panel-heading text-center">
                    <h4>Pedido</h4>
                </div>
                <div class="panel panel-body">
                    <div class="row">
                        <div class="col-sm-10">
                            <h3>
                                <span class="glyphicon glyphicon-list-alt"></span>
                                Descrição:
                                <span class="label label-primary">
                                    ${requestScope.pedido.descricao}
                                </span>
                            </h3>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-3">
                            <h3>
                                <span class="glyphicon glyphicon-calendar"></span>
                                Início
                                <span class="label label-primary" id="spanDataInicio">
                                    <f:formatDate value="${requestScope.pedido.dataInicio}" pattern="dd/MM/yyyy"/>
                                </span>
                            </h3>
                        </div>
                        <div class="col-sm-3">
                            <h3>
                                <span class="glyphicon glyphicon-calendar"></span>
                                Fim
                                <span class="label label-primary" id="spanDataFim">
                                    <f:formatDate value="${requestScope.pedido.dataFim}" pattern="dd/MM/yyyy"/>
                                </span>
                            </h3>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-5">
                            <h3>
                                <span class="glyphicon glyphicon-time"></span>
                                Hora Consultoria:
                                <span class="label label-primary" id="spanHora"> 
                                    <f:formatDate pattern="HH:mm" value="${requestScope.pedido.horaConsultoria.getTime()}"/>
                                </span>
                                <a class="btn btn-link" data-target="#setHoraData" data-toggle="modal">Alterar Data e Hora da consultoria</a>
                            </h3>
                        </div>
                    </div>  

                    <div class="row">
                        <div class="col-md-10">
                            <h3>
                                <span class="glyphicon glyphicon-flash"></span>
                                Habilidades Requiridas: 
                                <c:forEach var="hbr" items="${requestScope.pedido.habilidadePrestador}">
                                    <span class="label label-success">
                                        ${hbr}
                                    </span>
                                    &nbsp;
                                </c:forEach>
                            </h3>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-10">
                            <h3>
                                <span class="glyphicon glyphicon-tasks"></span>
                                Minhas Habilidades:
                                <c:forEach var="mhb" items="${requestScope.pedido.habilidadeCliente}">
                                    <span class="label label-info">
                                        ${mhb}
                                    </span>
                                    &nbsp;
                                </c:forEach>
                            </h3>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-10">
                            <h3>
                                <span class="glyphicon glyphicon-user"></span>
                                Consultor(a):
                                <c:choose>
                                    <c:when test="${requestScope.pedido.prestadorFinalista.id gt 0}">
                                        <span class="label label-success">
                                            ${requestScope.pedido.prestadorFinalista.nome} ${requestScope.pedido.prestadorFinalista.sobrenome}
                                        </span>
                                        &nbsp;
                                        <button class="btn btn-danger">
                                            Remover
                                            <span class="glyphicon glyphicon-ban-circle"></span>
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="label label-success">
                                            Nenhum prestador selecionado para esse Pedido!
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </h3>
                        </div>
                    </div> 
                </div>
                <div class="panel panel-footer">
                    <!--Form pra mandar a mensagem -->
                    <div id="div_mensagem" hidden="true">
                        <form action="EnviarMensagem" method="POST">
                            <input type="hidden" name="operacao" value="Atualizar"/>
                            <input type="hidden" name="txtDestinatario" value="${requestScope.pedido.prestadorFinalista.email}"/>
                            <input type="hidden" name="txtCaixaEntrada" value="${requestScope.pedido.prestadorFinalista.entrada.id}"/>
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
                    <br>
                    <div class="row">
                        <div class="col-md-2">
                            <a href="ClienteVideoConferencia.jsp?canal=${requestScope.pedido.canal}" class="btn btn-primary" 
                               <c:if test="${requestScope.pedido.prestadorFinalista.id eq 0}">
                                   disabled="true"
                               </c:if>
                               >
                                Video Chat
                                <span class="glyphicon glyphicon-film"></span>
                            </a>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-success" id="btnMensagem"  <c:if test="${requestScope.pedido.prestadorFinalista.id eq 0}">disabled="true"</c:if> >
                                Mensagem
                                <span class="glyphicon glyphicon-send"></span>
                            </button>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-default" id="selPrestador" data-toggle="modal" data-target="#transfModal">
                                Pagamento
                                <span class="glyphicon glyphicon-briefcase"></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function ()
            {
                $('#btnMensagem').on('click', function ()
                {
                    $('#div_mensagem').attr("hidden", false);
                    $('#btnMensagem').attr('disabled', true);
                });
            });
        </script>
    </body>
</html>
