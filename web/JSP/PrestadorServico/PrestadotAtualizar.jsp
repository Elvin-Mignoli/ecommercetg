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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <!--Ajax Function e Loading menus -->
        <script src="../../js/ajaxFuntions.js"></script>
        <script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>
        <!--Jquery Skill Bar -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/typeahead.js/0.10.4/typeahead.bundle.min.js"></script>
        <script src="../../js/libs/jQuery-Tags/js/typeahead.tagging.js" type="text/javascript"></script>
        <script src="../../js/libs/jQuery-Tags/js/libs/typeahead.bundle.min.js" type="text/javascript"></script>
        <link href="../../js/libs/jQuery-Tags/css/typeahead.tagging.css" rel="stylesheet" type="text/css"/>
         <a href="../../js/libs/jQuery-Tags/css/typeahead.tagging.less"></a>
         
         
        <script>
            $(document).ready(function () {
                $("#input_cpf").mask("999.999.999-99");
                $("#input_cnpj").mask("99.999.999/9999-99");
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
    </head>
    <body>
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
                       <input type="text" name="txtNome" placeholder="João" required="required" value="${sessionScope.user.nome}" class="form-control"/>
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
                <c:choose>
                    <c:when test="${sessionScope.user.cpf != null}">         
                        <div class="form-group">
                            <div class="input-group col-lg-5">
                                <span class="input-group-addon">
                                    CPF
                                </span>
                                <input type="text" name="txtCpf" id="input_cpf"   value="${sessionScope.user.cpf}" readonly="true" class="form-control"/>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.cnpj != null}">        
                        <div class="form-group">
                            <div class="input-group col-lg-5">
                                <span class="input-group-addon">
                                    CNPJ
                                </span>
                                <input type="text" name="txtCnpj" id="input_cnpj"  value="${sessionScope.user.cnpj}" readonly="true" class="form-control"/>
                            </div>
                        </div>
                    </c:when>
                </c:choose>        
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
                   <h2>Competência</h2>
               </div>    
               <div class="form-group">
                   <div class="input-group col-lg-5">
                      <input id="skill_bar" class="tags-input" value="<c:forEach var="list" items="${sessionScope.user.habilidades}"><c:if test='${sessionScope.user.habilidades != null}'>${list.descricao}</c:if></c:forEach>" name="txtSkill"/>
                   </div>
               </div>
             <script>
                 
                 var tagsource = [
                'jquery-libs', 'jquery-multilingual-news',
                'jquert-typeahead-tagging', 'jquery-multilingual-tags',
                'jquery-forms-ajaxified', 'jquery-project-template',
                'jquery-development-fabfile', 'jquery-user-media',
                'jquery-feedback-form', 'jquery-review', 'jquery-hero-slider',
                'jquery-document-library', 'jquery-paypal-express-checkout'
                ];
                $('#skill_bar').tagging(tagsource);
             </script>    
           </div>
           <br>
           <div class="container">
               <div class="form-group form-inline">
                   <input type="submit" value="Atualizar" class="btn btn-success"/>
               </div>
           </div>
       </form>     
                 
</html>



 