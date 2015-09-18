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
         <!-- Custom styles for this template -->
        <link href="../../bootstrap/dist/css/jumbotron.css" rel="stylesheet" type="text/css"/>
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.css">
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/>
         
      
    </head>
    <body>
       
       <form method="POST" action="AtualizarPrestador">
           <input type="text" name="operacao" value="Atualizar" hidden="true"/>
           <div class="container-fluid">
               <div class="form-group">
                   <div class="input-group col-lg-7">
                       <span class="input-group-addon">
                           Nome
                       </span>
                       <input type="text" name="txtNome" placeholder="João" required="required" value="${sessionScope.user.nome}" class="form-control"/>
                   </div>
               </div>

               <div class="form-group">
                   <div class="input-group col-lg-7">
                       <span class="input-group-addon">
                           Sobrenome
                       </span>
                       <input type="text" name="txtSobrenome" placeholder="Silva" required="required" value="${sessionScope.user.sobrenome}" class="form-control"/>
                   </div>
               </div>

               <div class="form-group">
                   <div class="input-group col-lg-7">
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
                            <div class="input-group col-lg-7">
                                <span class="input-group-addon">
                                    CPF
                                </span>
                                <input type="text" name="txtCpf" id="input_cpf"   value="${sessionScope.user.cpf}" readonly="true" class="form-control"/>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${sessionScope.user.cnpj != null}">        
                        <div class="form-group">
                            <div class="input-group col-lg-7">
                                <span class="input-group-addon">
                                    CNPJ
                                </span>
                                <input type="text" name="txtCnpj" id="input_cnpj"  value="${sessionScope.user.cnpj}" readonly="true" class="form-control"/>
                            </div>
                        </div>
                    </c:when>
                </c:choose>        
               <div class="form-group">
                   <div class="input-group col-lg-7">
                       <span class="input-group-addon">
                           Data
                       </span>
                       <input type="date" name="txtDatanascimento" value="<f:formatDate pattern="yyyy-MM-dd" value="${sessionScope.user.dataNascimento}"></f:formatDate>" class="form-control"/>        
                       </div>
               </div>

                   <div class="form-group">
                       <div class="input-group col-lg-7">
                           <span class="input-group-addon">
                               Telefone
                           </span>
                           <input type="text" id="telefone" name="txtTelefone" placeholder="Telefone" value="${sessionScope.user.contato.telefone}" class="form-control"/>
                   </div>
               </div>

               <div class="form-group">
                   <div class="input-group col-lg-7">
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
                   <div class="input-group col-lg-7">
                       <span class="input-group-addon" id="span_cep">
                           CEP
                       </span>
                       <input type="text" id="cep" name="txtCep" placeholder="0800-000" value="${sessionScope.user.endereco.cep}" class="form-control" onchange="loadEndereco()"/>
                   </div>
               </div>

               <div class="form-group">
                   <div class="input-group col-lg-7">
                       <span class="input-group-addon">
                           Logradouro
                       </span>
                       <input type="text" id="rua" name="txtLogradouro" placeholder="Rua, Logradouro, Avenida" value="${sessionScope.user.endereco.logradouro}" class="form-control"/>
                   </div>
               </div>

               <div class="form-group">
                   <div class="input-group col-lg-7">
                       <span class="input-group-addon">
                           Número
                       </span>
                       <input type="text" max="10000" name="txtNumero" id="numero" value="${sessionScope.user.endereco.numero}" class="form-control"/>
                   </div>
               </div> 

               <div class="form-group">
                   <div class="input-group col-lg-7">
                       <span class="input-group-addon">
                           Bairro
                       </span>
                       <input type="text" id="bairro" name="txtBairro"  placeholder="Bairro" value="${sessionScope.user.endereco.bairro}" class="form-control"/>
                   </div>
               </div>

               <div class="form-group">
                   <div class="input-group col-lg-7">
                       <span class="input-group-addon">
                           Cidade
                       </span>
                       <input type="text" id="cidade" name="txtCidade" placeholder="Cidade" value="${sessionScope.user.endereco.cidade}" class="form-control"/>
                   </div>
               </div>

               <div class="form-group">
                   <div class="input-group col-lg-7">
                       <span class="input-group-addon">
                           Estado
                       </span>
                       <input type="text" id="estado" name="txtEstado"  placeholder="Estado" value="${sessionScope.user.endereco.estado}" class="form-control"/>
                   </div>
               </div>

               <div class="form-group">
                   <div class="input-group col-lg-7">
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
                   <div class="input-group col-lg-7">
                      <input id="skill_bar" data-role="tagsinput" value="${sessionScope.user.habilidades.toString().replace("]","").replace("[","")}" name="txtSkill"/>
                   </div>
               </div>
               
           </div>
           <br>
           <div class="container">
               <div class="form-group form-inline">
                   <input type="submit" value="Atualizar" class="btn btn-success"/>
               </div>
           </div>
       </form>   
      <!-- Scripts da Pagina -->
        <!-- Importando jquery-->
         <!--<script src="../../js/libs/jquery-1.11.1.min.js"></script>
        <script src="../../js/libs/jquery-ui.min.js"></script>-->
        <script src="../../js/libs/jquery.maskedinput.js"></script>
         <!--implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/collapse.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
        <!-- JavaScript Bootstrap tag-input -->
        <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.min.js"></script>
        <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput-angular.min.js"></script>
         <!-- Scripts personalizados -->
        <script src="../../js/ajaxFuntions.js"></script>
        
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
    </body>            
</html>



 