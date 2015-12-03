<%-- 
    Document   : Pedidos
    Created on : 31/08/2015, 15:19:03
    Author     : Felipe Monteiro
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Efetuar um Pedido</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- Implementacao de CSS para Bootstrap tag input  -->
        <link href="../../bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.css">
    </head>
    <body>
        <!-- <div class="container"> -->
            <form method="post" action="SalvarPedido">
                <input type="text" name="operacao" value="Salvar" hidden="true" />
                <!-- PRIMEIRA LINHA DO FORM -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Descrição do Problema:</h4>
                        <textarea class="form-control" name="txtDescricao" required="required"></textarea>
                    </div>
                </div>
                <!-- TERCEIRA LINHA -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Habilidades Requeridas</h4>
                        <div class="input-group">
                            <input data-role="tagsinput" name="txtHabilidadeRequirida" class="form-control col-lg-6"/>
                        </div>
                    </div>
                </div>    

                <!-- QUARTA LINHA -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Suas Habilidades</h4>
                        <input id="my_habilities" data-role="tagsinput" name="txtMinhaHabilidade"/>
                    </div>
                </div>

                <!-- SEGUNDA LINHA DO FORM -->
                <div class="row">
                    <!-- ************* Campo data de Inicio **************-->
                    <div class="form-group col-lg-4">
                        <h4>Data de início</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span> Início
                            </span>
                            <input type="date" name="txtDataInicio" class="form-control" required="required"/>
                        </div>
                    </div>
                    <!-- **** Fim do Campo!  ***** -->

                    <!-- *************** Campo Data de Termino ****************** -->
                    <div class="form-group col-lg-4">
                        <h4>Data de término</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span> Término
                            </span>
                            <input type="date" name="txtDataTermino" class="form-control" required="required"/>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <h4><span class="glyphicon glyphicon-question-sign" id="ajudaData"></span></h4>
                    </div>
                    <!-- **** Fim do Campo!  ***** --> 
                </div>

                <!-- QUINTA LINHA -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h4>Hora da Consultoria&nbsp;<span class="glyphicon glyphicon-question-sign" id="ajudaHora"></span></h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-time"></span>
                            </span>
                            <input type="time" name="txtHora" class="form-control"/>
                        </div>
                    </div>
                </div>

                <!-- SEXTA LINHA // Campos de Submit -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <div class="input-group">
                            <button class="btn btn-success btn-lg" type="submit">
                                <span class="glyphicon glyphicon-bullhorn"></span>
                                Anunciar
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        <!-- </div> -->
        <!-- JavaScript Bootstrap tag-input -->
        <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.min.js"></script>
        <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput-angular.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#ajudaData').popover(
                        {
                            animation: true,
                            content: 'Essa é apenas uma data de ínicio e término inicial, pode ser alterada futuramente!',
                            placement: 'top',
                            title: 'Observações',
                            trigger: 'hover focus'
                        });

                $('#ajudaHora').popover(
                        {
                            animation: true,
                            content: 'Essa é apenas uma hora inicial, pode ser alterada futuramente!',
                            placement: 'right',
                            title: 'Observações',
                            trigger: 'hover focus'
                        });
            });
        </script>
    </body>
</html>
