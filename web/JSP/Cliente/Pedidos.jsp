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
        <script src="../../js/libs/jquery-1.11.1.min.js"></script>
        <script src="../../js/libs/jquery-ui.min.js"></script>
        <script src="../../js/libs/jquery.maskedinput.js"></script>
        <!-- implementando bootstrap na página -->
        <!-- <a href="../../js/libs/jQuery-Tags/css/typeahead.tagging.less"></a> -->
        <link href="../../js/libs/jQuery-Tags/css/typeahead.tagging.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/typeahead.js/0.10.4/typeahead.bundle.min.js"></script>
        <script src="../../js/libs/jQuery-Tags/js/typeahead.tagging.js" type="text/javascript"></script>
        <script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- Carregando mascara! -->
        <script>
            $(document).ready(function ()
            {
               $('#valor_pedido').mask('99/99/9999'); 
            });
        </script>
    </head>
    <body>
        <div class="container">
            <form method="post" action="SalvarPedido">
                <input type="text" name="operacao" value="Salvar" hidden="true" />
                <!-- PRIMEIRA LINHA DO FORM -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h3>Descrição do Problema:</h3>
                        <textarea class="form-control" name="txtDescricacao"></textarea>
                    </div>
                </div>
                <!-- TERCEIRA LINHA -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h3>Habilidades Requeridas</h3>
                        <input id="require_habilities" class="tags-input" name="txtHabilidadeRequirida"/>
                    </div>
                </div>    
                
                <!-- QUARTA LINHA -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h3>Suas Habilidades</h3>
                        <input id="my_habilities" class="tags-input" name="txtMinhaHabilidade"/>
                    </div>
                </div>
                
                <!-- SEGUNDA LINHA DO FORM -->
                <div class="row">
                    <!-- ************* Campo data de Inicio **************-->
                    <div class="form-group col-lg-3">
                        <H3>Data de início</H3>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span> Início
                            </span>
                            <input type="date" name="txtDataInicio" class="form-control" required="required"/>
                        </div>
                    </div>
                    <!-- **** Fim do Campo!  ***** -->

                    <!-- *************** Campo Data de Termino ****************** -->
                    <div class="form-group col-lg-3">
                        <h3>Data de término</h3>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span> Término
                            </span>
                            <input type="date" name="txtDataTermino" class="form-control" required="required"/>
                        </div>
                    </div>
                    <!-- **** Fim do Campo!  ***** --> 
                </div>
                
                <!-- QUINTA LINHA -->
                <div class="row">
                    <div class="form-group col-lg-6">
                        <h3>Hora da Consultoria</h3>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-time"></span>
                            </span>
                            <input type="time" name="txtHora" class="form-control"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
