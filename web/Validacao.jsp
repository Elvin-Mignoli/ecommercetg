<%-- 
    Document   : Validacao
    Created on : 19/10/2015, 11:54:35
    Author     : java
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="Validate" id="myForm">
            <label for="txtNome">Digite seu nome</label>
            <input type="text" id="txtNome" name="txtNome"/>
            <button type="button" onclick="teste()" id="validator">
                Validate
            </button>
        </form>
        <script src="libs/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="libs/validate/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="libs/validate/dist/additional-methods.min.js" type="text/javascript"></script>
        <script type="text/javascript">
               $('#validator').on("click",function (){
                  $('#myForm').validate({
                            rules: {
                                txtNome: {
                                    required: true
                                }
                            },
                            messages: {
                                txtNome: "Esse campo não pode ser vazio"
                            }
                        });
               });
        </script>
    </body>
</html>
