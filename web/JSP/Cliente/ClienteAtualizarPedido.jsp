<%-- 
    Document   : Pedidos
    Created on : 31/08/2015, 15:19:03
    Author     : Felipe Monteiro
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Efetuar um Pedido</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- Implementacao de CSS para Bootstrap tag input  -->
        <link href="../../bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.css">
    </head>
    <body>
        <div class="container">
            <form method="post" action="AtualizarPedido">
                <input type="text" name="operacao" value="Atualizar" hidden="true" />
                <input type="text" name="txtId" value="${requestScope.pedido.id}" hidden="true"/>
                <!-- PRIMEIRA LINHA DO FORM -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Descrição do Problema:</h4>
                        <textarea class="form-control" name="txtDescricao" required="required">${requestScope.pedido.descricao}</textarea>
                    </div>
                </div>
                <!-- TERCEIRA LINHA -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Habilidades Requeridas</h4>
                        <div class="input-group">
                            <input data-role="tagsinput" name="txtHabilidadeRequirida" class="form-control col-lg-6" value="${requestScope.pedido.habilidadePrestador.toString().replace("[","").replace("]","").trim()}"/>
                        </div>
                    </div>
                </div>    

                <!-- QUARTA LINHA -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Suas Habilidades</h4>
                        <input id="my_habilities" data-role="tagsinput" name="txtMinhaHabilidade" value="${requestScope.pedido.habilidadeCliente.toString().replace("[","").replace("]","").trim()}"/>
                    </div>
                </div>

                <!-- SEGUNDA LINHA DO FORM -->
                <div class="row">
                    <!-- ************* Campo data de Inicio **************-->
                    <div class="form-group col-lg-3">
                        <h4>Data de início</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span> Início
                            </span>
                            <input type="date" name="txtDataInicio" class="form-control" required="required" value="<f:formatDate pattern="yyyy-MM-dd" value="${requestScope.pedido.dataInicio}"/>"/>
                        </div>
                    </div>
                    <!-- **** Fim do Campo!  ***** -->

                    <!-- *************** Campo Data de Termino ****************** -->
                    <div class="form-group col-lg-3">
                        <h4>Data de término</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span> Término
                            </span>
                            <input type="date" name="txtDataTermino" class="form-control" required="required" value="<f:formatDate pattern="yyyy-MM-dd" value="${requestScope.pedido.dataFim}"/>" />
                        </div>
                    </div>
                    <!-- **** Fim do Campo!  ***** --> 
                </div>

                <!-- QUINTA LINHA -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Hora da Consultoria</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-time"></span>
                            </span>
                            <input type="time" name="txtHora" class="form-control" value="<f:formatDate pattern="HH:mm:ss" value="${requestScope.pedido.horaConsultoria.getTime()}"/>"/>
                        </div>
                    </div>
                </div>

                <!-- SEXTA LINHA // Campos de Submit -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <div class="input-group">
                            <button class="btn btn-success btn-lg" type="submit">
                                Atualizar
                                <span class="glyphicon glyphicon-refresh"></span>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- JavaScript Bootstrap tag-input -->
        <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.min.js"></script>
        <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput-angular.min.js"></script>
    </body>
</html>
