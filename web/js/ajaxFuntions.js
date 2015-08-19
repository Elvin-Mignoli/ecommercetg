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
//função ajax para identificar se o campo confirma email esta igual ao digitado anteriomente 
function confirmEmail()
{
    if($("#input_email").val()=== "")
    {
         $("#div_confirm").removeClass();
         $("#div_confirm").addClass("form-group has-warning has-feedback");
         $("#span_confirm").removeClass();
         $("#span_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
         $("#status_confirm").addClass("text-warning");
         $("#status_confirm").text("Digite antes um novo E-mail!");
    }else if($("#input_email").val()!== $("#input_confirm").val())
    {
         $("#div_confirm").removeClass();
         $("#div_confirm").addClass("form-group has-warning has-feedback");
         $("#span_confirm").removeClass();
         $("#span_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
         $("#status_confirm").addClass("text-warning");
         $("#status_confirm").text("Os dois novos E-mails não conferem, tente novamente!");
    }else
    {
        $("#div_confirm").removeClass();
        $("#div_confirm").addClass("form-group has-success has-feedback");
        $("#span_confirm").removeClass();
        $("#span_confirm").addClass("glyphicon glyphicon-ok form-control-feedback");
        $("#status_confirm").text("");
    }
    //Modificou o email novamente
        $("#input_email").on("click",function (e)
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
     
        if($("#input_senha").val()=== "")
        {
             $("#div_senha_confirm").removeClass();
             $("#div_senha_confirm").addClass("form-group has-warning has-feedback");
             $("#span_senha_confirm").removeClass();
             $("#span_senha_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
             $("#status_senha_confirm").addClass("text-warning");
             $("#status_senha_confirm").text("Digite antes um nova senha!");
        }else if($("#input_senha").val()!== $("#input_senha_confirm").val())
        {
             $("#div_senha_confirm").removeClass();
             $("#div_senha_confirm").addClass("form-group has-warning has-feedback");
             $("#span_senha_confirm").removeClass();
             $("#span_senha_confirm").addClass("glyphicon glyphicon-warning-sign form-control-feedback");
             $("#status_senha_confirm").addClass("text-warning");
             $("#status_senha_confirm").text("Os dois novas  não conferem, tente novamente!");
        }else
        {
            $("#div_senha_confirm").removeClass();
            $("#div_senha_confirm").addClass("form-group has-success has-feedback");
            $("#span_senha_confirm").removeClass();
            $("#span_senha_confirm").addClass("glyphicon glyphicon-ok form-control-feedback");
            $("#status_senha_confirm").text("");
        }
        //Modificou a senha novamente
        $("#input_senha").on("click",function (e)
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
    
    $.post("CEP", {cep: cep}, function(responseJson)
    {
        var resposta = responseJson;
        
        var endereco = jQuery.parseJSON(resposta);
        
        if(endereco.resultado === '0')
        {
            $('#div_cep').removeClass();
            $('#div_cep').addClass("form-group has-error has-feedback");
        }
        else if(endereco.resultado === '1')
        {
            $('#div_cep').removeClass();
            $("#div_cep").addClass("form-group has-success has-feedback");
        }
        
        $('#rua').val(endereco.tipo_logradouro +" "+ endereco.logradouro);
        $('#bairro').val(endereco.bairro);
        $('#cidade').val(endereco.cidade);
        $('#estado').val(endereco.uf);
    });   
}



