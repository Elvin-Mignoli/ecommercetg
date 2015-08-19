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
    
    $('#photoModal').on('show.bs.modal',function ()
    {
        $('#myInput').focus();
    });
        

    $('#validade').mask('99/99');

    //Codigo para carregar os campos de atualização
    $("#editar_dados").on("click", function (e) {
        $("#panel-heading").html("Dados Pessoais");
        e.preventDefault(); //eliminamos o evento
        var path = $(this).attr("href"); //Pegamos o caminho
        var titulo = $(this).attr('data-titulo'); //pegamos o titulo da página
        document.title = titulo; // Alterar o titulo da página
        window.history.pushState("", titulo, path);
        $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
        $("#conteudo").load(path); //Faz uma requisição http para o servidor.
        window.history.pushState('Object', 'Dashboard', './ClienteDashboard.jsp');
        return false;
    });

    //Codigo para chamar o formulário de Alteracao de Email
    $("#editar_email").on("click", function (e) {
        $("#panel-heading").html("Dados Pessoais");
        e.preventDefault(); //eliminamos o evento
        var path = $(this).attr("href"); //Pegamos o caminho
        var titulo = $(this).attr('data-titulo'); //pegamos o titulo da página
        document.title = titulo; // Alterar o titulo da página
        window.history.pushState("", titulo, path);
        $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
        $("#conteudo").load(path); //Faz uma requisição http para o servidor.
        window.history.pushState('Object', 'Dashboard', './ClienteDashboard.jsp');
        return false;
    });

    //Codigo para chamar o formulário de Alteracao da senha
    $("#editar_senha").on("click", function (e) {
        $("#panel-heading").html("Dados Pessoais");
        e.preventDefault(); //eliminamos o evento
        var path = $(this).attr("href"); //Pegamos o caminho
        var titulo = $(this).attr('data-titulo'); //pegamos o titulo da página
        document.title = titulo; // Alterar o titulo da página
        window.history.pushState("", titulo, path);
        $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
        $("#conteudo").load(path); //Faz uma requisição http para o servidor.
        window.history.pushState('Object', 'Dashboard', './ClienteDashboard.jsp');
        return false;
    });

    //Codigo para chamar o formulário de alteração do cartão de crédito
    $("#editar_cartao").on("click", function (e) {
        $("#panel-heading").html("Dados do Cartão");
        e.preventDefault(); //eliminamos o evento
        var path = $(this).attr("href"); //Pegamos o caminho
        var titulo = $(this).attr('data-titulo'); //pegamos o titulo da página
        document.title = titulo; // Alterar o titulo da página
        window.history.pushState("", titulo, path);
        $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
        $("#conteudo").load(path); //Faz uma requisição http para o servidor.
        window.history.pushState('Object', 'Dashboard', './ClienteDashboard.jsp');
        return false;
    });
    
    //funcao para carregar a imagem do cliente com ajax
    $('#imagePerfil').on("click",function (e)
    {    
        var file = document.getElementById("buttonGroups");
        
        if(file.getAttribute("hidden") === "true")
        {
            $('#buttonGroups').attr("hidden",false);
        }
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