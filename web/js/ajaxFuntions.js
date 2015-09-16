/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//funcao ajax para o CPF
function validaCPF()
{
    var cpf = $('#input_cpf').val();
    if($('#input_cpf').val()!== "")
    {
        $.post("CPF", {cpf: cpf}, function (responseJson)
        {

            var resposta = responseJson;
            if (resposta === "CPF Inválido!")
            {
                $("#div_cpf").addClass("form-group has-error has-feedback");
                $("#span_cpf").addClass("glyphicon glyphicon-remove form-control-feedback");
                $("#statusCPF").addClass("text-danger");
            }
            else if (resposta === "Já existe um usuário com esse CPF!")
            {
                $("#div_cpf").removeClass();
                $("#div_cpf").addClass("form-group has-warning has-feedback");
                $("#span_cpf").removeClass();
                $("#span_cpf").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
                $("#statusCPF").addClass("text-warning");
            }
            else
            {
                $("#div_cpf").removeClass();
                $("#div_cpf").addClass("form-group has-success has-feedback");
                $("#span_cpf").removeClass();
                $("#span_cpf").addClass("glyphicon glyphicon-ok form-control-feedback");
            }

            $("#statusCPF").text(responseJson);
        });
    }else
    {
        $("#div_cpf").removeClass();
        $("#span_cpf").removeClass();
        $("#statusCPF").text("");
    }
}

//funcao ajax para email
function validaEmail()
{
    var email = $('#input_email').val();
    $.post("Email", {email: email}, function (responseJson)
    {
        var resposta = responseJson;
        if (resposta === "Já existe alguém com esse Email!")
        {
            $("#div_email").removeClass();
            $("#div_email").addClass("form-group has-warning has-feedback");
            $("#span_email").removeClass();
            $("#span_email").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
            $("#statusEmail").addClass("text-warning");
        }
        else if (resposta === "Email Inválido!")
        {
            $("#div_email").removeClass();
            $("#div_email").addClass("form-group has-error has-feedback");
            $("#span_email").removeClass();
            $("#span_email").addClass("glyphicon glyphicon-remove form-control-feedback");
            $("#statusEmail").addClass("text-danger");
        }
        else
        {
            $("#div_email").removeClass();
            $("#div_email").addClass("form-group has-success has-feedback");
            $("#span_email").removeClass();
            $("#span_email").addClass("glyphicon glyphicon-ok form-control-feedback");
        }

        $("#statusEmail").text(responseJson);
    });
}


//carrega pagina de edição de dados do cliente - Método inutilizado
function loadEditarDados(id)
{
    $("#" + id).on("click", function (e) {
        alert(id);
        $("#panel-heading").html("Dados Pessoais");
        e.preventDefault(); //eliminamos o evento
        var path = "ClienteAtualizar.jsp";//$(this).attr("href"); //Pegamos o caminho
        var titulo = $(this).attr('data-titulo'); //pegamos o titulo da página
        document.title = titulo; // Alterar o titulo da página
        window.history.pushState("", titulo, path);
        $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
        $("#conteudo").load(path); //Faz uma requisição http para o servidor.
        window.history.pushState('Object', 'Dashboard', './ClienteDashboard.jsp');
        return false;
    });
    if ($("#input_email").val() === "")
    {
        $("#div_confirm").removeClass();
        $("#div_confirm").addClass("form-group has-warning has-feedback");
        $("#span_confirm").removeClass();
        $("#span_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
        $("#status_confirm").addClass("text-warning");
        $("#status_confirm").text("Digite antes um novo E-mail!");
    } else if ($("#input_email").val() !== $("#input_confirm").val())
    {
        $("#div_confirm").removeClass();
        $("#div_confirm").addClass("form-group has-warning has-feedback");
        $("#span_confirm").removeClass();
        $("#span_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
        $("#status_confirm").addClass("text-warning");
        $("#status_confirm").text("Os dois novos E-mails não conferem, tente novamente!");
    } else
    {
        $("#div_confirm").removeClass();
        $("#div_confirm").addClass("form-group has-success has-feedback");
        $("#span_confirm").removeClass();
        $("#span_confirm").addClass("glyphicon glyphicon-ok form-control-feedback");
        $("#status_confirm").text("");
    }
    //Modificou o email novamente
    $("#input_email").on("click", function (e)
    {
        $("#div_confirm").removeClass();
        $("#div_confirm").addClass("form-group");
        $("#span_confirm").removeClass();
        $("#status_confirm").text("");
        $("#input_confirm").val("");
    });
}
//função ajax para identificar se o campo confirma senha esta igual ao digitado anteriomente 
function confirmSenha()
{

    if ($("#input_senha").val() === "")
    {
        $("#div_senha_confirm").removeClass();
        $("#div_senha_confirm").addClass("form-group has-warning has-feedback");
        $("#span_senha_confirm").removeClass();
        $("#span_senha_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
        $("#status_senha_confirm").addClass("text-warning");
        $("#status_senha_confirm").text("Digite antes um nova senha!");
    } else if ($("#input_senha").val() !== $("#input_senha_confirm").val())
    {
        $("#div_senha_confirm").removeClass();
        $("#div_senha_confirm").addClass("form-group has-warning has-feedback");
        $("#span_senha_confirm").removeClass();
        $("#span_senha_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
        $("#status_senha_confirm").addClass("text-warning");
        $("#status_senha_confirm").text("Os dois novas  não conferem, tente novamente!");
    } else
    {
        $("#div_senha_confirm").removeClass();
        $("#div_senha_confirm").addClass("form-group has-success has-feedback");
        $("#span_senha_confirm").removeClass();
        $("#span_senha_confirm").addClass("glyphicon glyphicon-ok form-control-feedback");
        $("#status_senha_confirm").text("");
        $("#submit").prop("type","submit");
    }
    //Modificou a senha novamente
    $("#input_senha").on("click", function (e)
    {
        $("#div_senha_confirm").removeClass();
        $("#div_senha_confirm").addClass("form-group");
        $("#span_senha_confirm").removeClass();
        $("#status_senha_confirm").removeClass();
        $("#status_senha_confirm").text("");
        $("#input_senha_confirm").val("");
    });

}
//função ajax para buscar um CEP
function loadEndereco()
{
    var cep = $('#cep').val();

    $.post("CEP", {cep: cep}, function (responseJson)
    {
        var resposta = responseJson;

        var endereco = jQuery.parseJSON(resposta);

        if (endereco.resultado === '0')
        {
            $('#div_cep').removeClass();
            $('#div_cep').addClass("form-group has-error has-feedback");
        }
        else if (endereco.resultado === '1')
        {
            $('#div_cep').removeClass();
            $("#div_cep").addClass("form-group has-success has-feedback");
        }

        $('#rua').val(endereco.tipo_logradouro + " " + endereco.logradouro);
        $('#bairro').val(endereco.bairro);
        $('#cidade').val(endereco.cidade);
        $('#estado').val(endereco.uf);
    });
}

function validaBandeira()
{
    var content = $('#numero_cartao').val();

    if (content.substring(0, 1) === '4')//&& content.length >= 13 && content.length < 17)
    {
        $('#ico_card').attr("src", "../../images/visa_ico.png");
        $('#secure_number').mask('999');

        if (content.length === 13)
            $('#numero_cartao').mask('9999999999999');

        $("#div_cartao").removeClass();
        $("#div_cartao").addClass("form-group col-lg-4 has-success has-feedback");

    }
    else if (content.substring(0, 1) === '5')
    {
        $('#ico_card').attr("src", "../../images/master_ico.png");
        $('#secure_number').mask('999');

        if (content.length === 16)
            $('#numero_cartao').mask('9999999999999');

        $("#div_cartao").removeClass();
        $("#div_cartao").addClass("form-group col-lg-4 has-success has-feedback");
    }
    else if (content.substring(0, 3) === '301' || content.substring(0, 3) === '305' || content.substring(0, 2) === '36' || content.substring(0, 2) === '38')
    {
        $('#ico_card').attr("src", "../../images/diners_ico.png");
        $('#secure_number').mask('999');

        if (content.length === 16)
            $('#numero_cartao').mask('9999999999999');

        $("#div_cartao").removeClass();
        $("#div_cartao").addClass("form-group col-lg-4 has-success has-feedback");
    }
    else
    {
        $("#div_cartao").removeClass();
        $("#div_cartao").addClass("form-group col-lg-4 has-error has-feedback");
        $("#span_cartao").removeClass();
        $("#span_cartao").addClass("glyphicon glyphicon-remove form-control-feedback ");

        $('#ico_card').attr("src", "");
        $('#secure_number').mask('9999999');
    }
}

//funcao para carregar a foto do cliente
function loadPhoto(file)
{

}

//função ajax para identificar se o campo confirma email esta igual ao digitado anteriomente 
function confirmEmail()
{
    if ($("#input_email").val() === "")
    {
        $("#div_confirm").removeClass();
        $("#div_confirm").addClass("form-group has-warning has-feedback");
        $("#span_confirm").removeClass();
        $("#span_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback ");
        $("#status_confirm").addClass("text-warning");
        $("#status_confirm").text("Digite antes um novo E-mail!");
    } 
    else if ($("#input_email").val() !== $("#input_confirm").val())
    {
        $("#div_confirm").removeClass();
        $("#div_confirm").addClass("form-group has-warning has-feedback");
        $("#span_confirm").removeClass();
        $("#span_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
        $("#status_confirm").addClass("text-warning");
        $("#status_confirm").text("Os dois novos E-mails não conferem, tente novamente!");
    } 
    else    //deu tudo certo?
    {
        $("#div_confirm").removeClass();
        $("#div_confirm").addClass("form-group has-success has-feedback");
        $("#span_confirm").removeClass();
        $("#span_confirm").addClass("glyphicon glyphicon-ok form-control-feedback");
        $("#status_confirm").text("");
        $("#submit").prop("type","submit");
    }
    //Modificou o email novamente
    $("#input_email").on("click", function (e)
    {
        $("#div_confirm").removeClass();
        $("#div_confirm").addClass("form-group");
        $("#span_confirm").removeClass();
        $("#status_confirm").text("");
        $("#input_confirm").val("");
    });
}
    //função que verifica se há algum erro nos campos do cadastro
    function confirmCadastro()
    {
        var select = $("#select_doc").val();
        var cpf = $("#statusCPF").text();
        var cnpj = $("#statusCNPJ").text();
        var email = $("#statusEmail").text();
        var senha = $("#status_senha_confirm").text();
        //alert(cpf); alert(email);alert(senha);alert($("#status_senha_confirm").text());alert(select);alert(cnpj);
        if(select === "CPF")//o usuário selecionou o documento CPF?
        {//sim
            if(cpf === "")
            {
                if(email === "")
                {
                    if(senha === "")
                    {
                        $("#bt_cadastro").prop("type","submit");
                    }
                }
            }
        }else if(select === "CNPJ")//o usuário selecionou o documento CNPJ?
        {//sim
           if(cnpj === "")
            {
                if(email === "")
                {
                    if(senha === "")
                    {
                        $("#bt_cadastro").prop("type","submit");
                    }
                }
            } 
        }else if(select === "")//o usuário selecionounenhum documento?
        {//não
           $("#select_doc").focus() ;
        }

    }//fim 
    
    //função para identificar o documentação CPF ou CNPJ
    function indentificarSelect()
    {
        if( $("#select_doc").val()=== "CPF"){
            $("#div_cnpj").toggle(false);
            $("#div_cpf").toggle(true);
        }else if($("#select_doc").val() === "CNPJ")
        {
             $("#div_cpf").toggle(false);
             $("#div_cnpj").toggle(true);
        }else if($("#select_doc").val() === "")
        {
            $("#div_cpf").toggle(false);
            $("#div_cnpj").toggle(false);
        }
     };   

//funcao ajax para o CNPJ
function validaCNPJ()
{
    var cnpj = $('#input_cnpj').val();
    if($('#input_cnpj').val()!== "")
    {
        $.post("CNPJ", {cnpj: cnpj}, function (responseJson)
        {

            var resposta = responseJson;
            alert(resposta);
            if (resposta === "CNPJ Inválido!")
            {
                $("#div_cnpj").addClass("form-group has-error has-feedback");
                $("#span_cnpj").addClass("glyphicon glyphicon-remove form-control-feedback");
                $("#statusCNPJ").addClass("text-danger");
            }
            else if (resposta === "Já existe um usuário com esse CNPJ!")
            {
                $("#div_cnpj").removeClass();
                $("#div_cnpj").addClass("form-group has-warning has-feedback");
                $("#span_cnpj").removeClass();
                $("#span_cnpj").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
                $("#statusCNPJ").addClass("text-warning");
            }
            else
            {
                $("#div_cnpj").removeClass();
                $("#div_cnpj").addClass("form-group has-success has-feedback");
                $("#span_cnpj").removeClass();
                $("#span_cnpj").addClass("glyphicon glyphicon-ok form-control-feedback");
            }

            $("#statusCNPJ").text(responseJson);
        });
    }else
    {
        $("#div_cnpj").removeClass();
        $("#span_cnpj").removeClass();
        $("#statusCNPJ").text("");
    }
}


//function para abrir uma mensagem
function abrirMsg(id_msg){
    var url = 'OpenMensagem.jsp?id=';
    var id = id_msg;
    url = url.concat(id);
    //window.open(url,'','height=1024,width=800');
    $("#panel-heading").html("Mensagem");
    var path = url; //Pegamos o caminh"o
    var titulo = "Mensagem"; //pegamos o titulo da página
    document.title = titulo; // Alterar o titulo da página
    window.history.pushState("", titulo, path);
    $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
    $("#conteudo").load(path); //Faz uma requisição http para o servidor.
    window.history.pushState('Object', 'Dashboard', './PrestadorDashboard.jsp');
    return false;
}


//função para excluir uma mensagem
function excluirMensagem()
{
    var opcao = confirm("Você deseja realmente excluir essa mensagem?");
    if(opcao){
        $("#bt_excluir").prop("type","submit");
    }
}
//function para excluir a mensagem na front da caixa de mensagem
function excluirMensagemFront(op,id_entrada,id_msg)
{
        var opcao = confirm("Você deseja realmente excluir essa mensagem?");
         if(opcao){
            var operacao = op;
            var entrada = id_entrada;
            var txtId = id_msg;
            
              $.post("ExcluirMensagemFront", {operacao: operacao,entrada:entrada,txtId:txtId}, function(responseJson){
                var resposta= responseJson;
                 if (resposta === "Mensagem excluida com sucesso!")
                {
                    alert("Mensagem excluída com sucesso!");
                    $("#caixa_entrada").trigger("click");
                }else{
                    $("#div_fail").toggle(true);
                }
	
            });
        }//if
}//function


//function para responder
function responderMsg(id_msg){
    var url = 'ResponderMensagem.jsp?id=';
    var id = id_msg;
    url = url.concat(id);
    //window.open(url,'','height=1024,width=800');
    $("#panel-heading").html(" Responder Mensagem");
    var path = url; //Pegamos o caminh"o
    var titulo = " Responder Mensagem"; //pegamos o titulo da página
    document.title = titulo; // Alterar o titulo da página
    window.history.pushState("", titulo, path);
    $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
    $("#conteudo").load(path); //Faz uma requisição http para o servidor.
    window.history.pushState('Object', 'Dashboard', './PrestadorDashboard.jsp');
    return false;
}
