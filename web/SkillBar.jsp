<%-- 
    Document   : SkillBar
    Created on : 17/08/2015, 15:22:37
    Author     : java
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <!--Para funcionar o collapsed-->
        <script src="js/libs/jquery-1.11.1.min.js"></script>
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="SkillBar.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="input-group col-lg-5">
                    <input type="text" id="txtHab" class="form-control"/>
                    <span class="input-group-addon btn btn-success" id="btnAdd">add</span>
                </div>
            </div>
            <div class="row" id="divSkill">

            </div>
            <div class="btn btn-success alert alert-success alert-dismissible col-xs-push-1" role="alert">
                <button type="button" class="close small" data-dismiss="alert" aria-label="close">
                    <span aria-hidden="true">&times;
                    </span>
                </button>
                Java Web
            </div>

            <div class="alert alert-success alert-dismissible col-sm-1">
                <button type="button" class="close small" data-dismiss="alert" aria-label="close">
                    <span aria-hidden="true">&times;
                    </span>
                </button>
                Felipe Melo
            </div>

            <!-- Large modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Large modal</button>

            <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        ...
                    </div>
                </div>
            </div>

            <!-- Small modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">Small modal</button>

            <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        ...
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
