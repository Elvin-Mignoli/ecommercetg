<%-- 
    Document   : teste
    Created on : 16/09/2015, 16:41:40
    Author     : Elvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <button type="button" class="btn btn-success" name="bt_candidatar" id="bt_candidatar" onclick="candidatar(2)" >Candidatar-se</button>
        <script>
            function candidatar(id){
            alert("oi");
            alert(id);
            var id_pedido = id;
           // $.post("CEP", {cep: cep}, function (responseJson){
            alert(id_pedido);
          
            //});
}
        </script>
    </body>
</html>
