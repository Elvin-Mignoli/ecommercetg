/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//funcao ajax para o CPF
function validaCPF()
{
    var cpf = $('#input_cpf').val();
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
    }
    //Modificou a senha novamente
    $("#input_senha").on("click", function (e)
    {
        $("#div_senha_confirm").removeClass();
        $("#div_senha_confirm").addClass("form-group");
        $("#span_senha_confirm").removeClass();
        $("#status_senha_confirm").val("");
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
        $("#span_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
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
