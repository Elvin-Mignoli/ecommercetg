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
        <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/font-awesome.css" rel="stylesheet" type="text/css"/>
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
                                <span class="alert alert-warning">
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
                <div id="indexCarousel" class="carousel slide col-md-10 text-center" data-ride="carousel">
                    <!-- Indicadores -->
                    <ol class="carousel-indicators">
                        <li data-target="#image-carousel" data-slide="0" class="active"></li>
                        <li data-target="#image-carousel" data-slide="1"></li>
                        <li data-target="#image-carousel" data-slide="2"></li>
                    </ol>
                    <!-- Wrapper  for slides -->
                    <div class="carousel-inner" role="listbox">
                        <!-- primeiro item -->
                        <div class="item active">
                            <img src="imagens/freelance.jpg" alt="">
                            <div class="carousel-caption">
                                <h3>Temos ótimos planos para você!</h3>
                            </div>
                        </div>
                        <!-- Segundo item -->
                        <div class="item">
                            <img src="imagens/happy.jpg" alt="">
                            <div class="carousel-caption">
                                <h3>Seja um grande consultor</h3>
                            </div>
                        </div>
                        <!-- Terceiro Item -->
                        <div class="item">
                            <img src="imagens/ranking.jpg" alt="">
                            <div class="carousel-caption">
                                <h3>Cresça em nosso Ranking</h3>
                            </div>
                        </div>
                    </div>
                    <!-- Controls -->
                    <a class="left carousel-control" href="#indexCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Anterior</span>
                    </a>
                    <a class="right carousel-control" href="#indexCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Próximo</span>
                    </a>
                </div>
                <!-- <h1>Bem vindo!</h1>
                <p>Está página vai ser destinada para apresentar diversas informações sobre os serviços</p>
                <p><a class="btn btn-primary btn-lg" href="#" role="button">Saiba mais sobre nós!</a></p> --> 
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
                <!-- Comentários --> 
                <div class="col-md-4 text-center">
                    <h2>Depoimentos</h2>
                    <div id="carousel-example" class="carousel slide-custom" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example" data-slide-to="1"></li>
                            <li data-target="#carousel-example" data-slide-to="2"></li>
                        </ol>

                        <div class="carousel-inner">
                            <div class="item active">
                                <div class="item">
                                    <div class="col-md-10 slide-custom">
                                        <h4><i class="fa fa-quote-left"></i> Um serviço muito prático para quem quer fazer freelancer.<i class="fa fa-quote-right"></i></h4>
                                        <div class="user-img pull-right">
                                            <img src="assets/img/user.gif" alt="" class="img-u image-responsive"  />
                                        </div>
                                        <h5 class="pull-right"><strong class="c-black">Lorem Dolor</strong></h5>
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <div class="">
                                    <div class="col-md-10 slide-custom">
                                        <h4> <i class="fa fa-quote-left"></i>O "Know-how" é ótimo, com ele consegui muita visibilidade!<i class="fa fa-quote-right"></i></h4>
                                        <div class="user-img pull-right">
                                            <img src="assets/img/user2.png" alt="" class="img-u image-responsive"  />
                                        </div>
                                        <h5 class="pull-right"><strong class="c-black">Keep Code</strong></h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Fim dos comentários -->
                <div class="col-md-4 text-left">
                    <h2>Empresas Envolvidas</h2>
                    <div id="carousel-example" class="carousel slide-custom" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example" data-slide-to="1"></li>
                            <li data-target="#carousel-example" data-slide-to="2"></li>
                        </ol>

                        <div class="carousel-inner">
                            <div class="item active">
                                <div class="item">
                                    <div class="col-md-10 slide-custom">
                                        <h4><i class="fa fa-quote-left"></i>Desenvolvimento de soluções em segurança<i class="fa fa-quote-right"></i></h4>
                                        <div class="user-img pull-right">
                                            <img src="assets/img/metro.png" alt="" class="img-u image-responsive"  />
                                        </div>
                                        <h5 class="pull-right"><strong class="c-black">Defender SW</strong></h5>
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <div class="col-md-10 slide-custom">
                                    <h4> <i class="fa fa-quote-left"></i>O "Know-how" é ótimo, com ele consegui muita visibilidade!<i class="fa fa-quote-right"></i></h4>
                                    <div class="user-img pull-right">
                                        <img src="assets/img/code.png" alt="" class="img-u image-responsive"  />
                                    </div>
                                    <h5 class="pull-right"><strong class="c-black">Faucibus luctus</strong></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <hr>

            <footer class="form-inline">
                <p>&copy; <b>Know-how</b> Company 2015</p>
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

