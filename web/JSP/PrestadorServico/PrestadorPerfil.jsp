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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link href="../../css/changeColor.css" rel="stylesheet" type="text/css"/>
         <!-- Custom styles for this template -->
        
        <!-- implementando CSS do bootstrap -->
        <link rel="stylesheet" href="../../bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.css">
        <!-- CSS das fontes -->
        <link href="../../css/fonts/font.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/openMensagem.css" rel="stylesheet" type="text/css"/> 
        <!-- CSS icons-->
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
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
       <form method="POST" action="AtualizarPrestador">
       <input type="text" name="operacao" value="Atualizar" hidden="true" id="operacao"/>
       <!--Panel informações pessoais -->
       <div class="panel  panel-success col-lg-10" >   
                        <div class="panel panel-heading text-center" id="panel_info">Informações Pessoais</div>
                        <div class="panel-body">
                            <!-- Conteudo dos Panels! -->
                            <div class="row text-right">
                                <button type="button" id="btn_editar" class="btn btn-success" onclick="editarDados()">Editar Dados</button>
                            </div>                     
                            <!--Nome   -->
                            <div class="row">
                            <div class="form-group  ">
                                <h4>Nome </h4>
                                <div class="input-group col-lg-5">
                                    <span class="input-group-addon" id="span_nome"><span class="glyphicon glyphicon-user"></span></span>
                                    <input type="text"  name="txtNome" class="form-control"  value="${sessionScope.user.nome}" id="input_nome"/>
                                </div>
                            </div>
                            </div>
                             <!--Nome   -->
                            <div class="row">
                            <div class="form-group  ">
                                <h4>Sobrenome </h4>
                                <div class="input-group col-lg-5">
                                    <span class="input-group-addon" id="span_nome"><span class="glyphicon glyphicon-user"></span></span>
                                    <input type="text"  name="txtSobrenome" class="form-control"  value="${sessionScope.user.sobrenome}" 
                                           id="input_sobrenome" />
                                </div>
                            </div>
                            </div>
                               <!--Sexo -->
                            <div class="row" id="sexo">
                                <div class="form-group ">
                                    <h4>Sexo</h4>
                                        <div class="input-group col-lg-5">
                                            <span class="input-group-addon" id="span_sexo"><b><i class="fa fa-venus-mars fa-lg"></i></b></span></span>
                                            <c:choose> 
                                                <c:when test="${sessionScope.user.sexo == 'F'}">
                                                    <input type="text" class="form-control" value="FEMININO"  id="input_sexo" />
                                                </c:when>
                                                <c:when test="${sessionScope.user.sexo == 'M'}">
                                                     <input type="text" class="form-control" value="MASCULINO"   id="input_sexo"/>
                                                </c:when>
                                                <c:otherwise>
                                                     <input type="text" class="form-control" value="NÃO ESPECIFICADO"   id="input_sexo"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                </div>
                            </div>
                               <div class="row" hidden="true" id="div_sexo">
                                 <div class="form-group ">
                                    <h4>Sexo</h4>
                                    <div class="input-group col-lg-5 ">
                                        <span class="input-group-addon"><b><i class="fa fa-venus-mars fa-lg"></i></b></span>
                                        <select name="txtSexo" class="form-control col-lg-3" id="valueSexo" value="${sessionScope.user.sexo}">
                                            <option value="Não especificado" id="op1">Prefiro não especificar</option>
                                            <option value="Masculino" id="op2">Masculino</option>
                                            <option value="Feminino" id="op3">Feminino</option>                                
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="${sessionScope.user.cpf != null}">
                                    <!--CPF-->
                                    <div class="row">
                                        <div class="form-group ">
                                            <h4>CPF</h4>
                                            <div class="input-group col-lg-5">
                                                <span class="input-group-addon"  id="span_cpf"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                <input type="text" name="txtCpf" id="input_cpf" value="${sessionScope.user.cpf}"   class="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${sessionScope.user.cnpj != null}">
                                    <!--CNPJ-->
                                    <div class="row">
                                        <div class="form-group ">
                                            <h4>CNPJ</h4>
                                            <div class="input-group col-lg-5">
                                                <span class="input-group-addon"  id="span_cnpj"><span class="glyphicon glyphicon-asterisk"></span></span>
                                                <input type="text"   name="txtCnpj"  id="input_cnpj" value="${sessionScope.user.cnpj}"   class="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                             <!--Data de nascimento-->
                            <div class="row">
                                <div class="form-group ">
                                    <h4>Data nascimento</h4>
                                    <div class="input-group col-lg-5">
                                        <span class="input-group-addon"  id="span_data"><b><i class="fa fa-birthday-cake fa-lg"></i></b></span></span>
                                        <input class="form-control " type="date" name="txtDatanascimento" value="<f:formatDate pattern="yyyy-MM-dd" value="${sessionScope.user.dataNascimento}"></f:formatDate>" 
                                               id="input_data"  />           
                                    </div>
                                </div> 
                            </div>
                            <!--Contato-->
                            <div class="row">
                                <div class="form-group ">
                                    <h4>Telefone</h4>
                                    <div class="input-group col-lg-5">
                                        <span class="input-group-addon"  id="span_telefone"><span class="glyphicon glyphicon-phone-alt"></span></span>
                                        <input type="text" name="txtTelefone" id="input_telefone"  value="${sessionScope.user.contato.telefone}"  class="form-control" readlony />
                                    </div>
                                </div>
                            </div>
                            <div class="row">        
                                <div class="form-group ">
                                     <h4>Celular</h4>
                                    <div class="input-group col-lg-5" >
                                        <span class="input-group-addon" id="span_celular"><span class="glyphicon glyphicon-phone"></span></span>
                                        <input  type="text" name="txtCelular" id="input_celular"  value="${sessionScope.user.contato.celular}"  class="form-control" />
                                    </div>
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
                        <div class="row">
                            <div class="form-group  " id="div_cep">
                                <h4>CEP</h4>
                                <div class="input-group col-lg-5">
                                    <span class="input-group-addon" id="span_cep"><b><i class="fa fa-street-view fa-lg"></i></b></span> </span>
                                    <input type="text" id="cep" name="txtCep" placeholder="0800-000" value="${sessionScope.user.endereco.cep}" class="form-control" onchange="loadEndereco()"/>
                                </div>
                            </div>
                        </div>
                         <!-- LOGRADOURO--> 
                        <div class="row">
                            <div class="form-group ">
                                <h4>Logradouro</h4>
                                <div class="input-group col-lg-5">
                                    <span class="input-group-addon"  id="span_rua"><span class="glyphicon glyphicon-road"></span> </span>
                                    <input type="text" name="txtLogradouro" id="rua" value="${sessionScope.user.endereco.logradouro}" class="form-control"/>
                                </div>
                            </div>
                        </div>
                            <!-- número-->  
                        <div class="row">
                            <div class="form-group ">
                                <h4>Número</h4>
                                <div class="input-group  col-lg-5">
                                    <span class="input-group-addon" id="span_numero"><b><i class="fa fa-home fa-lg"></i></b></span>
                                    <input type="text"  name="txtNumero" id="numero" value="${sessionScope.user.endereco.numero}" class="form-control"/>
                                </div>
                            </div> 
                        </div>
                            <!-- bairro--> 
                        <div class="row">
                            <div class="form-group ">
                                <h4>Bairro</h4>
                                <div class="input-group col-lg-5">
                                    <span class="input-group-addon" id="span_bairro"><b><i class="fa fa-building-o fa-lg"></i></b></span>
                                    <input type="text" name="txtBairro" id="bairro"  value="${sessionScope.user.endereco.bairro}" class="form-control"/>
                                </div>
                            </div>
                        </div>
                            <!-- cidade-->  
                        <div class="row">
                            <div class="form-group ">
                                <h4>Cidade</h4>
                                <div class="input-group  col-lg-5">
                                    <span class="input-group-addon" id="span_cidade"><b><i class="fa fa-building-o fa-lg"></i></b></span>
                                    <input type="text" name="txtCidade"  id="cidade"  value="${sessionScope.user.endereco.cidade}" class="form-control"/>
                                </div>
                            </div>
                        </div>
                            <!-- estado--> 
                         <div class="row">
                            <div class="form-group ">
                                <h4>Estado</h4>
                                <div class="input-group col-lg-5">
                                    <span class="input-group-addon" id="span_estado"><b><i class="fa fa-building-o fa-lg"></i></b></span>
                                    <input type="text" name="txtEstado" id="estado"  value="${sessionScope.user.endereco.estado}" class="form-control"/>
                                </div>
                            </div>
                         </div>
                            <!--complemento--> 
                         <div class="row">
                            <div class="form-group ">
                                <h4>Complemento</h4>
                                <div class="input-group col-lg-5">
                                    <span class="input-group-addon" id="span_complemento"><b><i class="fa fa-puzzle-piece fa-lg"></i></b></span>
                                    <input type="text" name="txtComplemento" id="complemento" class="form-control"  value="${sessionScope.user.endereco.complemento}" />
                                </div>
                            </div>
                         </div>
                        </div><!--panel body-->
       </div><!--panel-->
        <!--Panel informações Competências -->
       <div class="panel  panel-primary col-lg-10" >
            <div class="panel panel-heading text-center" id="panel_info">Competências</div>
            <div class="panel-body">
                <!-- Conteudo dos Panels! -->
                <div class="row">
                    <div class="form-group ">
                         <span class="glyphicon glyphicon-question-sign" id="span_ajuda_disabled"></span>
                         <div hidden="true" id="div_ajuda">
                            <span class="glyphicon glyphicon-question-sign" id="span_ajuda"></span>
                         </div>
                        <div class="input-group col-lg-5 " id="div_skill">
                            <input id="skill_bar" data-role="tagsinput" disabled="true" value="${sessionScope.user.habilidades.toString().replace("]","").replace("[","").trim()}" name="txtSkill"/>
                        </div>
                    </div>
                </div> 
                 
                <div class="row text-right" hidden="true" id="div_button">
                    <div class="form-group form-inline">
                        <button type="button" id="btn_atualizar" value="Atualizar" class="btn btn-primary " onclick="atualizarDadosPresatador()">Atualizar</button>
                    </div>
                </div>
            </div><!--panel body-->
       </div><!--panel--><br>
  
       </form>  
                       
         <!-- Scripts da Pagina -->
          <!-- JavaScript Bootstrap tag-input -->
         
        <script src="../../bootstrap/js/tooltip.js" type="text/javascript"></script>
        <script src="../../bootstrap/js/popover.js" type="text/javascript"></script>
        <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput.min.js"></script>
        <script src="../../js/libs/bootstrap-taginput/bootstrap-tagsinput-angular.min.js"></script>
   
       <!-- Mask -->
       <script>
        $(document).ready(function(){
                $("#input_cpf").mask("999.999.999-99");
                $("#input_cnpj").mask("99.999.999/9999-99");
                $("#input_telefone").mask("(99)9999-9999");
                $("#input_celular").mask("(99)99999-9999");
                $("#cep").mask("99999-999");
            
            $(".form-control").prop('readonly','readonly');
            //Aumentar o tamanho do skillbar
            $('#skill_bar').tagsinput({
                tagClass: 'big'
              });
            //caption do botão
            $('#btn_editar').tooltip(
            {
                animation: true,
                placement: 'top',
                title: 'Clique no botão para editar seus dados',
                trigger: 'hover focus'
            });
            //caption do input de competencia esta desativado
            $('#span_ajuda_disabled').popover(
            {
                animation: true,
                content: 'Essa opção está indisponível, para utilizar-la apertado o botão Editar Dados no topo da página.',
                placement: 'top',
                title: 'Habilidades',
                trigger: 'hover focus'
            });
            //caption do input de competencia
            $('#span_ajuda').popover(
            {
                animation: true,
                content: 'Após escrever uma habilidade, pressione o ENTER.',
                placement: 'top',
                title: 'Habilidades',
                trigger: 'hover focus'
            });
            
            //TypeaHead das competencias
          
          });
        </script> 

    </body>
</html>
