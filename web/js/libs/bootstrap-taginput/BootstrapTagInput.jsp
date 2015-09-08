<%-- 
    Document   : BootstrapTagInput
    Created on : 06/09/2015, 09:46:25
    Author     : Felipe Monteiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../../../bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../../bootstrap/dist/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="bootstrap-tagsinput.css">
    </head>
    <body>
        <select multiple data-role="tagsinput">
            <option value="Amsterdam">Amsterdam</option>
            <option value="Washington">Washington</option>
            <option value="Sydney">Sydney</option>
            <option value="Beijing">Beijing</option>
            <option value="Cairo">Cairo</option>
        </select>
        <script src="../jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="../../../bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../jQuery-Tags/js/libs/typeahead.bundle.min.js" type="text/javascript"></script>
        <script src="../angular/angular.min.js" type="text/javascript"></script>
        <script src="bootstrap-tagsinput.min.js"></script>
        <script src="bootstrap-tagsinput-angular.min.js"></script>
    </body>
</html>
