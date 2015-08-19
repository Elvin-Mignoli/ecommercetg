<%-- 
    Document   : PrestadotAtualizar
    Created on : 07/08/2015, 10:12:52
    Author     : Elvin
--%>

<%@page import="br.com.ecommerce.domain.PrestadorServico"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<script src="../../js/libs/jquery-1.11.1.min.js"></script>
<script src="../../js/libs/jquery-ui.min.js"></script>
<script src="../../js/libs/jquery.maskedinput.js"></script>
<!-- <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualizar Cliente</title> -->
<script>
    $(document).ready(function () {
        $("#data").mask("99/99/9999");
        $("#telefone").mask("(99)9999-9999");
        $("#celular").mask("(99)99999-9999");
        $("#cep").mask("99999-999");
        $("#input_cpf").mask("999.999.999-99");

        if ($("#estado").val().length > 1)
        {
            $("#numero").focus();
        }
        $("#numero").blur(function () {
            $("#input_nome").focus();
        });


    });
</script>
<script src="../../js/ajaxFuntions.js"></script>
 <script src="../../js/libs/jquery-1.11.1.min.js"></script>
 <script src="../../js/libs/jquery-ui.min.js"></script>
 <script src="../../js/libs/jquery.maskedinput.js"></script>
<!--implementando bootstrap na página -->
<script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../bootstrap/js/collapse.js"></script>
<script src="../../bootstrap/js/tab.js"></script>
<!-- Custom styles for this template -->
<link href="../../bootstrap/dist/css/jumbotron.css" rel="stylesheet" type="text/css"/>
<!-- implementando CSS do bootstrap -->
<link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
<script src="../../js/ajaxFuntions.js"></script>
<script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>
<!--</head>
<body>  -->
<!--Jquery do campo Sexo -->
 <script>
    var valSexo = $('#valueSexo').val();

    if (valSexo === "M")
    {
        document.getElementById("op2").setAttribute("selected", "true");
    }
    else if (valSexo === "F")
    {
        document.getElementById("op3").setAttribute("selected", "true");
    }

</script>
<form method="POST" action="AtualizarPrestador">
    <input type="text" name="operacao" value="Atualizar" hidden="true"/>
    <div class="container">
        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Nome
                </span>
                <input type="text" name="txtNome" placeholder="José" required="required" value="${sessionScope.user.nome}" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Sobrenome
                </span>
                <input type="text" name="txtSobrenome" placeholder="Silva" required="required" value="${sessionScope.user.sobrenome}" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Sexo
                </span>
                <input type="text" hidden="true" value="${sessionScope.user.sexo}" id="valueSexo" />
                <select name="txtSexo" class="form-control col-lg-3">
                    <option value="Não especificado" id="op1">Prefiro não especificar</option>
                    <option value="Masculino" id="op2">Masculino</option>
                    <option value="Feminino" id="op3">Feminino</option>                                
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    CPF
                </span>
                <input type="text" name="txtCpf" id="input_cpf" placeholder="CPF" required="required" min="3" value="${sessionScope.user.cpf}" readonly="true" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Data
                </span>
                <input type="date" name="txtDatanascimento" value="<f:formatDate pattern="yyyy-MM-dd" value="${sessionScope.user.dataNascimento}"></f:formatDate>" class="form-control"/>        
                </div>
        </div>

            <div class="form-group">
                <div class="input-group col-lg-5">
                    <span class="input-group-addon">
                        Telefone
                    </span>
                    <input type="text" id="telefone" name="txtTelefone" placeholder="Telefone" value="${sessionScope.user.contato.telefone}" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Celular
                </span>
                <input type="text" id="celular" name="txtCelular" placeholder="Celular" value="${sessionScope.user.contato.celular}" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <h2>Endereço</h2>
        </div>

        <div class="form-group" id="div_cep">
            <div class="input-group col-lg-5">
                <span class="input-group-addon" id="span_cep">
                    CEP
                </span>
                <input type="text" id="cep" name="txtCep" placeholder="0800-000" value="${sessionScope.user.endereco.cep}" class="form-control" onchange="loadEndereco()"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Logradouro
                </span>
                <input type="text" id="rua" name="txtLogradouro" placeholder="Rua, Logradouro, Avenida" value="${sessionScope.user.endereco.logradouro}" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Número
                </span>
                <input type="text" max="10000" name="txtNumero" id="numero" value="${sessionScope.user.endereco.numero}" class="form-control"/>
            </div>
        </div> 

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Bairro
                </span>
                <input type="text" id="bairro" name="txtBairro"  placeholder="Bairro" value="${sessionScope.user.endereco.bairro}" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Cidade
                </span>
                <input type="text" id="cidade" name="txtCidade" placeholder="Cidade" value="${sessionScope.user.endereco.cidade}" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Estado
                </span>
                <input type="text" id="estado" name="txtEstado"  placeholder="Estado" value="${sessionScope.user.endereco.estado}" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group col-lg-5">
                <span class="input-group-addon">
                    Complemento
                </span>
                <input type="text" id="complemento" placeholder="Complemento" class="form-control" name="txtComplemento" value="${sessionScope.user.endereco.complemento}"/>
            </div>
        </div>
         <div class="form-group">
            <h2>Competências</h2>
        </div>    
        <div class="form-group">
            <div class="input-group col-lg-5">
                <input type="text" id="write_skill" placeholder="escreva sua habilidade" class="form-control" />
                <span class="input-group-addon ">
                    <input type="button" id="input_skill" value="Adicionar" />
                </span>
            </div>
        </div>
       
        <div class="form-group">
            <div class="input-group col-lg-5">
                <textarea  rows="3" id="show_skill" style="width:475px; height:100px" readonly name="txtSkill"><c:forEach var="list" items="${sessionScope.user.habilidades}"><c:choose><c:when test='${sessionScope.user.habilidades != null}'>${list.descricao}${" "}</c:when><c:otherwise></c:otherwise></c:choose></c:forEach></textarea>
            </div>
        </div>
     <!--Jquery adicionar skill na textarea -->        
        <script>
            $("#input_skill").on("click", function (e) {
                e.preventDefault();
               // alert($('#show_skill').val())
                var input = $('#write_skill').val();// var input receve o conteudo digitado no campo 
                var show = $('#show_skill').text();//var show recebe a conteudo da textarea
                input = input.toUpperCase(); // var input fica com caixa alta
                if(show === "" ||show=== " " ) // textarea esta vazio?
                 show = show.concat(input);
                else//não está
                    
                 show = show.concat(" ",input);// var show concatena seu conteudo com o novo digitado, separdo com " - "
                 show = show.toUpperCase(); //var show fica com caixa alta
                $('#show_skill').text(show); //textarea recebe o conteudo da var show
                $('#write_skill').val("");//o limpar campo pra digitação 
               // alert($('#show_skill').text());
            });
        </script>
       
    </div>
    <br>
    <div class="container">
        <div class="form-group form-inline">
            <input type="submit" value="Atualizar" class="btn btn-success"/>
        </div>
    </div>
</form>

<!-- </body>
</html> -->


