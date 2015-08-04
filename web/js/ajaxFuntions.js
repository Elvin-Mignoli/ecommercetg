/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function ()
{
    $('#submit').click(function (event) //evento botão de click
    {
        var username = $('#user').val();
        $.post('ActionServlet', {user: username}, function (responseJson) {
            $('#welcometext').text(responseJson);
        });
    });

    $("#ajax").on("click", function (e) {
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
});

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
        else if (resposta === "Já existe um cliente com esse CPF!")
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

//carrega pagina de edição de dados do cliente
function loadEditarDados()
{
    $(document).ready(function () {
        $("#ajax").on("click", function (e) {
            e.preventDefault(); //eliminamos o evento
            var path = "ClienteAtualizar.jsp";//$(this).attr("href"); //Pegamos o caminho
            var titulo = $(this).attr('data-titulo'); //pegamos o titulo da página
            document.title = titulo; // Alterar o titulo da página
            window.history.pushState("", titulo, path);
            $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
            $("#conteudo").load(path); //Faz uma requisição http para o servidor.
            return false;
        });
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

