<%-- 
    Document   : PrestadorPerfil
    Created on : 14/08/2015, 16:06:49
    Author     : Elvin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="../../css/changeColor.css" rel="stylesheet" type="text/css"/>
         <!-- Custom styles for this template -->
        <link href="../../bootstrap/dist/css/jumbotron.css" rel="stylesheet" type="text/css"/>
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/> 
        <!--style para mudar as cores dos inputs e span-->
        <style>
            #input_nome,#input_sexo,#input_data,
            #input_cpf,#input_telefone,#input_celular,
            #input_cep,#input_rua,#input_numero,#input_bairro,
            #input_cidade,#input_estado,#input_complemento,#input_cnpj{
                background-color: white;
            }
            #span_nome,#span_sexo,#span_cpf,#span_cnpj,
            #span_data,#span_telefone,#span_celular{
                background-color: #dff0d8;
                border: 1px solid #3c763d;
            }
            #span_cep,#span_rua,#span_numero,
            #span_bairro,#span_cidade,#span_estado,
            #span_complemento{
                background-color: #d9edf7;
                border:1px solid #31708f;
            }
        </style>
    </head>
    
    
    <body>
       <!--Verificar se há algum dado pendente-->
       <c:if test="${sessionScope.user.dataNascimento == null || 
                    sessionScope.user.contato.telefone == ''||
                    sessionScope.user.sexo == '' ||
                    sessionScope.user.contato.celular == ''||
                    sessionScope.user.endereco.cep == ''||
                    sessionScope.user.endereco.logradouro == ''||
                    sessionScope.user.endereco.numero == ''||
                    sessionScope.user.endereco.bairro == ''||
                    sessionScope.user.endereco.cidade == ''||
                    sessionScope.user.endereco.estado == ''||
                    sessionScope.user.habilidades== null}">
             
              <div class="alert alert-info alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;
                     </span>
                    </button>
                    Você possui alguns dados cadastrais pendentes!
               </div>
       </c:if> 
      
       <!--Panel informações pessoais -->
       <div class="panel  panel-success col-lg-10" >   
                        <div class="panel panel-heading text-center" id="panel_info">Informações Pessoais</div>
                        <div class="panel-body">
                            <!-- Conteudo dos Panels! -->
                            <!--Nome completo  -->
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon" id="span_nome">Nome completo</span>
                                    <input type="text"   class="form-control"  value="${sessionScope.user.nome} ${sessionScope.user.sobrenome}" 
                                           id="input_nome" readonly="readonly"/>
                                </div>
                            </div>
                               <!--Sexo -->
                            <div class="form-group">
                                    <div class="input-group col-lg-5">
                                        <span class="input-group-addon" id="span_sexo">
                                            Sexo
                                        </span>
                                        <c:choose> 
                                            <c:when test="${sessionScope.user.sexo == 'F'}">
                                                <input type="text" class="form-control" value="FEMININO"  readonly="readonly" id="input_sexo" />
                                            </c:when>
                                            <c:when test="${sessionScope.user.sexo == 'M'}">
                                                 <input type="text" class="form-control" value="MASCULINO"  readonly="readonly" id="input_sexo"/>
                                            </c:when>
                                            <c:otherwise>
                                                 <input type="text" class="form-control" value="NÃO ESPECIFICADO"  readonly="readonly" id="input_sexo"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                            </div>
                            <c:choose>
                                <c:when test="${sessionScope.user.cpf != null}">
                                    <!--CPF-->
                                    <div class="form-group">
                                        <div class="input-group col-lg-5">
                                            <span class="input-group-addon"  id="span_cpf">
                                                CPF
                                            </span>
                                            <input type="text"  id="input_cpf" value="${sessionScope.user.cpf}" readonly="readonly"  class="form-control"/>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${sessionScope.user.cnpj != null}">
                                    <!--CNPJ-->
                                    <div class="form-group">
                                        <div class="input-group col-lg-5">
                                            <span class="input-group-addon"  id="span_cnpj">
                                                CNPJ
                                            </span>
                                            <input type="text"  id="input_cnpj" value="${sessionScope.user.cnpj}" readonly="readonly"  class="form-control"/>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                             <!--Data de nascimento-->
                            <div class="form-group">
                                <div class="input-group col-lg-5">
                                    <span class="input-group-addon"  id="span_data">
                                        Data nascimento
                                    </span>
                                    <input type="date" value="<f:formatDate pattern="yyyy-MM-dd" value="${sessionScope.user.dataNascimento}"></f:formatDate>" 
                                           id="input_data" class="form-control" readonly="readonly"/>           
                                </div>
                            </div> 
                            <!--Contato-->
                            <div class="form-group">
                                <div class="input-group col-lg-5">
                                    <span class="input-group-addon"  id="span_telefone">
                                        Telefone
                                    </span>
                                    <input type="text" id="input_telefone"  value="${sessionScope.user.contato.telefone}" readonly="readonly" class="form-control" readlony />
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group col-lg-5" >
                                    <span class="input-group-addon" id="span_celular">
                                        Celular
                                    </span>
                                    <input  type="text" id="input_celular"  value="${sessionScope.user.contato.celular}" readonly="readonly" class="form-control" />
                                </div>
                            </div>
                        </div> <!-- panel body-->     
       </div><!-- panel -->
       <!--Panel informações pessoais -->
       <div class="panel panel-info col-lg-10">
                        <div class="panel panel-heading text-center">Endereço</div>
                        <div class="panel-body">
                            <!-- Conteudo dos Panels! -->
                            <!-- CEP-->  
                        <div class="form-group" id="div_cep">
                            <div class="input-group col-lg-5">
                                <span class="input-group-addon" id="span_cep">
                                    CEP
                                </span>
                                <input type="text" id="input_cep"  value="${sessionScope.user.endereco.cep}" class="form-control" readonly="readonly"/>
                            </div>
                        </div>
                         <!-- LOGRADOURO-->             
                        <div class="form-group">
                            <div class="input-group col-lg-10">
                                <span class="input-group-addon"  id="span_rua">
                                    Logradouro
                                </span>
                                <input type="text" id="input_rua"  value="${sessionScope.user.endereco.logradouro}" class="form-control"readonly="readonly"/>
                            </div>
                        </div>
                            <!-- número-->  
                        <div class="form-group">
                            <div class="input-group col-lg-5">
                                <span class="input-group-addon" id="span_numero">
                                    Número
                                </span>
                                <input type="text"  id="input_numero" value="${sessionScope.user.endereco.numero}" class="form-control"readonly="readonly"/>
                            </div>
                        </div> 
                            <!-- bairro-->  
                        <div class="form-group">
                            <div class="input-group col-lg-5">
                                <span class="input-group-addon" id="span_bairro">
                                    Bairro
                                </span>
                                <input type="text" id="input_bairro"  value="${sessionScope.user.endereco.bairro}" class="form-control"readonly="readonly"/>
                            </div>
                        </div>
                            <!-- cidade-->  
                        <div class="form-group">
                            <div class="input-group col-lg-5">
                                <span class="input-group-addon" id="span_cidade">
                                    Cidade
                                </span>
                                <input type="text" id="input_cidade"  value="${sessionScope.user.endereco.cidade}" class="form-control"readonly="readonly"/>
                            </div>
                        </div>
                            <!-- estado-->  
                        <div class="form-group">
                            <div class="input-group col-lg-5">
                                <span class="input-group-addon" id="span_estado">
                                    Estado
                                </span>
                                <input type="text" id="input_estado"  value="${sessionScope.user.endereco.estado}" class="form-control"readonly="readonly"/>
                            </div>
                        </div>
                            <!--complemento-->  
                        <div class="form-group">
                            <div class="input-group col-lg-10">
                                <span class="input-group-addon" id="span_complemento">
                                    Complemento
                                </span>
                                <input type="text" id="input_complemento" class="form-control"  value="${sessionScope.user.endereco.complemento}"readonly="readonly"/>
                            </div>
                        </div>
                        </div><!--panel body-->
       </div><!--panel-->
        <!--Panel informações Competências -->
       <div class="panel  panel-primary col-lg-10" >
            <div class="panel panel-heading text-center" id="panel_info">Competências</div>
            <div class="panel-body">
                <!-- Conteudo dos Panels! -->
                 <div class="form-group">
                    <div class="input-group col-lg-3">
                        <textarea  rows="3" id="show_skill" style="width:642px; height:100px "

                                   readonly name="txtSkill"><c:forEach var="list" items="${sessionScope.user.habilidades}"><c:choose><c:when test='${sessionScope.user.habilidades != null}'>${list.descricao}${" "}</c:when><c:otherwise></c:otherwise></c:choose></c:forEach></textarea>
                    </div>
                </div>
            </div><!--panel body-->
       </div><!--panel-->
         <!-- Scripts da Pagina -->
       <!-- Mask -->
       <script>
        $(document).ready(function(){
                $("#input_cpf").mask("999.999.999-99");
                $("#input_cnpj").mask("99.999.999/9999-99");
                $("#input_telefone").mask("(99)9999-9999");
                $("#input_celular").mask("(99)99999-9999");
                $("#input_cep").mask("99999-999");
            });
        </script> 
        <!-- Importando jquery-->
         <!--<script src="../../js/libs/jquery-1.11.1.min.js"></script>
        <script src="../../js/libs/jquery-ui.min.js"></script>-->
        <script src="../../js/libs/jquery.maskedinput.js"></script>
         <!--implementando bootstrap na página -->
        <script src="../../bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../../bootstrap/js/collapse.js"></script>
        <script src="../../bootstrap/js/tab.js"></script>
         <!-- Scripts personalizados -->
        <script src="../../js/ajaxFuntions.js"></script>
        <script src="../../js/ajaxLoadingMenu.js" type="text/javascript"></script>
    </body>
</html>
