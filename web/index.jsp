<%-- 
    Document   : index
    Created on : 21/04/2015, 16:31:43
    Author     : Felipe Monteiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Bem Vindo</title>
        <!-- Bootstrap core CSS -->
        <link href="bootstrap/dist/css/bootstrap.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="bootstrap/dist/css/jumbotron.css" rel="stylesheet">
    </head>

    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Know-How</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" method="post" action="ValidarUsuario">
                        <c:if test="${requestScope.loginError ne null}">
                            <div class="form-group">
                                <span class="alert alert-info">
                                    ${requestScope.loginError}
                                    <% request.setAttribute("loginError", null);%>
                                </span>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <input type="text" placeholder="Email" name="txtEmail" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" name="txtPassword" class="form-control">
                        </div>
                        <button type="submit" class="btn btn-success">Entrar</button>
                        <a href="JSP/Cliente/ClienteSalvar.jsp" class="btn btn-link">Não possui cadastro?</a>
                        <!-- <input type="button" class="btn btn-link" value="Não possui cadastro?"/> -->
                        <a href="JSP/PrestadorServico/PrestadorSalvar.jsp" class="btn btn-link">Quero prestar Serviço</a>
                        <a href="JSP/HeadHunter/HeadHunterSalvar.jsp" class="btn btn-link">Você é um HeadHunter?</a>
                    </form>
                </div><!--/.navbar-collapse -->
            </div>
        </nav>

        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="jumbotron">
            <div class="container">
                <h1>Bem vindo!</h1>
                <p>Está página vai ser destinada para apresentar diversas informações sobre os serviços</p>
                <p><a class="btn btn-primary btn-lg" href="#" role="button">Saiba mais sobre nós!</a></p>
            </div>
        </div>

        <div class="container">
            <!-- Example row of columns -->
            <div class="row">
                <div class="col-md-4 text-center">
                    <h2>Habilidades mais procuradas</h2>
                    <!-- Div do Gráfico -->
                    <div class="box-chart">
                        <canvas id="graficoRosquinha" style="width: 100%"></canvas>
                    </div>
                    <!-- <p>Alguns informações aqui</p>
                    <p><a class="btn btn-default" href="#" role="button">Mais detalhes &raquo;</a></p> -->
                </div>
                <div class="col-md-4">
                    <h2>Nossos Pedidos</h2>
                    <div class="box-chart">
                        <canvas id="GraficoBarra" style="width: 100%"></canvas>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <span class="label label-success">Abertos</span>
                        </div>
                        <div class="col-sm-3">
                            <span class="label label-info">Fechados</span>
                        </div>
                        <div class="col-sm-3">
                            <span class="label label-danger">Cancelados</span>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <h2>Titulo 3</h2>
                    <p>E mais algumas por aqui!</p>
                    <p><a class="btn btn-default" href="#" role="button">Mais detalhes &raquo;</a></p>
                </div>
            </div>

            <hr>

            <footer class="form-inline">
                <p>&copy; Company 2014</p>
            </footer>
        </div> <!-- /container -->
        <!--Para funcionar o collapsed-->
        <script src="js/libs/jquery-1.11.1.min.js"></script>
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="js/libs/Charts/Chart.min.js" type="text/javascript"></script>
        <script src="js/ajaxFuntions.js" type="text/javascript"></script>
        <!-- Funcoes para o gráfico -->
        <script type="text/javascript">
                    window.onload = function ()
                    {
                    /*var ctx = document.getElementById("graficoRosquinha").getContext("2d");
                     window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive: true});*/
                    graficoRosquinha();
                            /*ctx = document.getElementById("GraficoBarra").getContext("2d");
                             var Bar = new Chart(ctx).Bar(dataBar, option);
                             alert("Criou os gráficos");*/
                            graficoBarras();
                    };
                    function graficoRosquinha()
                    {
                    var doughnutData = [
            <c:forEach var="hb" items="${requestScope.donnutHabilidade}">
                    {
                    value: '${hb.value}',
                    scaleShowLabels: true,
                            color: this.dar_cor_aleatoria(),
                            highlight: "#D3D3D3",
                            label: '${hb.label}'
                    },
            </c:forEach>
                    ];
                            var ctx = document.getElementById("graficoRosquinha").getContext("2d");
                            window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive: true});
                    }

            function graficoBarras()
            {
            var options = {
            responsive: true
            };
                    var data = {
                    labels: ["Setembro", "Outubro", "Novembro", "Dezembro"],
                            datasets: [

            <c:forEach var="pedido" items="${requestScope.pedidos}">
                <c:choose>
                    <c:when test="${pedido.status eq 'ABERTO'}">
                            {
                            label: "Abertos",
                                    fillColor: "rgba(124,252,0,0.5)",
                                    strokeColor: "rgba(124,252,0,1)",
                                    pointColor: "rgba(220,220,220,1)",
                                    pointStrokeColor: "#fff",
                                    pointHighlightFill: "#fff",
                                    pointHighlightStroke: "rgba(151,187,205,1)",
                                    data: ['${pedido.values.get("Setembro")}', '${pedido.values.get("Outubro")}', '${pedido.values.get("Novembro")}', '${pedido.values.get("Dezembro")}']
                            },
                    </c:when>
                    <c:when test="${pedido.status eq 'FECHADO'}">
                            {
                            label: "Fechados",
                                    fillColor: "rgba(0,191,255,0.5)",
                                    strokeColor: "rgba(0,191,255,1)",
                                    pointColor: "rgba(220,220,220,1)",
                                    pointStrokeColor: "#fff",
                                    pointHighlightFill: "#fff",
                                    pointHighlightStroke: "rgba(151,187,205,1)",
                                    data: ['${pedido.values.get("Setembro")}', '${pedido.values.get("Outubro")}', '${pedido.values.get("Novembro")}', '${pedido.values.get("Dezembro")}']
                            },
                    </c:when>
                    <c:otherwise>
                            {
                            label: "Cancelados",
                                    fillColor: "rgba(178,34,34,0.5)",
                                    strokeColor: "rgba(178,34,34,1)",
                                    pointColor: "rgba(220,220,220,1)",
                                    pointStrokeColor: "#fff",
                                    pointHighlightFill: "#fff",
                                    pointHighlightStroke: "rgba(151,187,205,1)",
                                    data: ['${pedido.values.get("Setembro")}', '${pedido.values.get("Outubro")}', '${pedido.values.get("Novembro")}', '${pedido.values.get("Dezembro")}']
                            },
                    </c:otherwise>
                </c:choose>
            </c:forEach>
                            ]
                    };
                    var ctx = document.getElementById("GraficoBarra").getContext("2d");
                    var BarChart = new Chart(ctx).Bar(data, options);
            }
        </script>
    </body>
</html>

