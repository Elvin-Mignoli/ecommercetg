
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1" name="viewport">

        <title>Chart.js - criando gráficos com a biblioteca Chart.js</title>

        <script src="js/libs/Charts/Chart.min.js"></script>

        <style type="text/css">

            *{
                font-family: calibri;        
            }

            .box {
                margin: 0px auto;
                width: 70%;
            }

            .box-chart {
                width: 100%;
                margin: 0 auto;
                padding: 10px;
            }

        </style>  

    </head>

    <body>    

        <div class="box">

            <h1>Chart.js - criando gráficos com a biblioteca Chart.js - DEMO</h1>
            <small>Arquivo com testes e demo de funcionalidades da biblioteca Chart.js criado para o tutorial do blog Web Social Dev.</small>        

            <h2>Gráfico de Linha!</h2>
            <small>Dados gerados com função javascript para números randômicos até 300.</small>

            <div class="box-chart">

                <canvas id="GraficoLine" style="width:100%;"></canvas>
                <div id="lineLegend"></div>
                
                <script type="text/javascript">

                    var options = {
                        responsive: true
                    };

                    var data = {
                        labels: ["Setembro", "Outubro","Novembro","Dezembro"],
                        datasets: [
                            
                    <c:forEach var="pedido" items="${requestScope.pedidos}">
                        <c:choose>
                            <c:when test="${pedido.status eq 'ABERTO'}">
                                {
                                label: "Abertos",
                                fillColor: "rgba(50,205,50,0.2)",
                                strokeColor: "rgba(220,220,220,1)",
                                pointColor: "rgba(220,220,220,1)",
                                pointStrokeColor: "#fff",
                                pointHighlightFill: "#fff",
                                pointHighlightStroke: "rgba(151,187,205,1)",
                                data: ['${pedido.values.get("Setembro")}','${pedido.values.get("Outubro")}','${pedido.values.get("Novembro")}','${pedido.values.get("Dezembro")}']
                                },
                            </c:when>
                            <c:when test="${pedido.status eq 'FECHADO'}">
                                {
                                label: "Fechados",
                                fillColor: "rgba(151,187,205,0.2)",
                                strokeColor: "rgba(220,220,220,1)",
                                pointColor: "rgba(220,220,220,1)",
                                pointStrokeColor: "#fff",
                                pointHighlightFill: "#fff",
                                pointHighlightStroke: "rgba(151,187,205,1)",
                                data: ['${pedido.values.get("Setembro")}','${pedido.values.get("Outubro")}','${pedido.values.get("Novembro")}','${pedido.values.get("Dezembro")}']
                                },
                            </c:when>
                            <c:otherwise>
                                {
                                label: "Cancelados",
                                fillColor: "rgba(178,34,34,0.2)",
                                strokeColor: "rgba(178,34,34,1)",
                                pointColor: "rgba(220,220,220,1)",
                                pointStrokeColor: "#fff",
                                pointHighlightFill: "#fff",
                                pointHighlightStroke: "rgba(151,187,205,1)",
                                data: ['${pedido.values.get("Setembro")}','${pedido.values.get("Outubro")}','${pedido.values.get("Novembro")}','${pedido.values.get("Dezembro")}']
                                },
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                        ]
                    };

                    window.onload = function () 
                    {
                        var ctx = document.getElementById("GraficoLine").getContext("2d");
                        var LineChart = new Chart(ctx).Bar(data, options);
                        legend(document.getElementById("lineLegend"),data,null);
                    };
                </script>

                <p><a href="http://websocialdev.com/graficos-chart-js-introducao/">Voltar para o página do tutorial</p>
            </div>
        </div>
        <script src="js/ajaxFuntions.js" type="text/javascript"></script>
    </body>
</html>
