/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function ()
{
    //ajax para carregar dados do cliente
    $('#submit').click(function (event) //evento botão de click
    {
        var username = $('#user').val();
        $.post('ActionServlet', {user: username}, function (responseJson) {
            $('#welcometext').text(responseJson);
        });
    });

    $('#photoModal').on('show.bs.modal', function ()
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
    $('#imagePerfil').on("click", function (e)
    {
        var file = document.getElementById("buttonGroups");

        if (file.getAttribute("hidden") === "true")
        {
            $('#buttonGroups').attr("hidden", false);
        }
    });

    //ajax Editar dados prestador
    $("#edit_dados_prest").on("click", function (e) {
        $("#panel-heading").html("Dados Pessoais");
        e.preventDefault(); //eliminamos o evento
        var path = $(this).attr("href"); //Pegamos o caminho
        var titulo = $(this).attr('data-titulo'); //pegamos o titulo da página
        document.title = titulo; // Alterar o titulo da página
        window.history.pushState("", titulo, path);
        $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
        $("#conteudo").load(path); //Faz uma requisição http para o servidor.
        window.history.pushState('Object', 'Dashboard', './PrestadorDashboard.jsp');
        return false;
    });
    //ajax Alterar E-mail do prestador de serviço
    $("#alterar_email").on("click", function (e) {
        $("#panel-heading").html("Alterar Login/E-mail");
        e.preventDefault(); //eliminamos o evento
        var path = $(this).attr("href"); //Pegamos o caminho
        var titulo = $(this).attr('data-titulo'); //pegamos o titulo da página
        document.title = titulo; // Alterar o titulo da página
        window.history.pushState("", titulo, path);
        $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
        $("#conteudo").load(path); //Faz uma requisição http para o servidor.
        window.history.pushState('Object', 'Dashboard', './PrestadorDashboard.jsp');
        return false;
    });
    //ajax Alterar senha do  prestador de serviço
    $("#alterar_senha").on("click", function (e) {
        $("#panel-heading").html("Alterar Senha");
        e.preventDefault(); //eliminamos o evento
        var path = $(this).attr("href"); //Pegamos o caminho
        var titulo = $(this).attr('data-titulo'); //pegamos o titulo da página
        document.title = titulo; // Alterar o titulo da página
        window.history.pushState("", titulo, path);
        $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
        $("#conteudo").load(path); //Faz uma requisição http para o servidor.
        window.history.pushState('Object', 'Dashboard', './PrestadorDashboard.jsp');
        return false;
    });

    //ajax mostrar o perfil do  prestador de serviço
    $("#meu_perfil").on("click", function (e) {
        $("#panel-heading").html("Perfil");
        e.preventDefault(); //eliminamos o evento
        var path = $(this).attr("href"); //Pegamos o caminho
        var titulo = $(this).attr('data-titulo'); //pegamos o titulo da página
        document.title = titulo; // Alterar o titulo da página
        window.history.pushState("", titulo, path);
        $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
        $("#conteudo").load(path); //Faz uma requisição http para o servidor.
        window.history.pushState('Object', 'Dashboard', './PrestadorDashboard.jsp');
        return false;
    });
    
    
});

