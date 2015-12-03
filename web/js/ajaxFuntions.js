/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//funcao ajax para o CPF
function validaCPF()
{
    var cpf = $('#input_cpf').val();
    if ($('#input_cpf').val() !== "")
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
    } else
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
        $("#status_senha_confirm").text("Os dois novas senhas não conferem, tente novamente!");
    } else
    {
        $("#div_senha_confirm").removeClass();
        $("#div_senha_confirm").addClass("form-group has-success has-feedback");
        $("#span_senha_confirm").removeClass();
        $("#span_senha_confirm").addClass("glyphicon glyphicon-ok form-control-feedback");
        $("#status_senha_confirm").text("");
        $("#submit").prop("type", "submit");
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
    $("#statusCEP").text("");
    $.ajax({
        type: 'POST',
        url: "CEP",
        data: {cep: cep},
        success: function (data, textStatus, jqXHR) {

            var endereco = jQuery.parseJSON(data);
           
            if (endereco.resultado === '1') //cep encontrado com sucesso!
            {
                $('#div_cep').removeClass();
                $("#div_cep").addClass("form-group has-success has-feedback");

                $('#rua').val(endereco.tipo_logradouro + " " + endereco.logradouro);
                $('#bairro').val(endereco.bairro);
                $('#cidade').val(endereco.cidade);
                $('#estado').val(endereco.uf);

            } else {
                $('#div_cep').removeClass();
                $("#div_cep").addClass("form-group has-warning has-feedback");
                $('#statusCEP').addClass("text-warning");
                $('#statusCEP').text("CEP incorreto!");
                $('#rua').val('');
                $('#bairro').val('');
                $('#cidade').val('');
                $('#estado').val('');
            }
             //Modificou a senha novamente
            $("#cep").on("click", function (e)
            {
                $("#statusCEP").text("");
                $("#div_cep").removeClass();
                $("#div_cep").addClass("form-group");
                $("#statusCEP").removeClass();
                $("#statusCEP").text("");
                $("#cep").val("");
            });
        } 
    }); 
}

function validaBandeira()
{
    var content = $('#numero_cartao').val();

    $('#formCartao').validate({
        rules: {
            txtNumero: {
                creditcard: true
            }
        },
        messages: {
            txtNumero: {
                creditcard: "Cartão inválido"
            }
        }
    });

    $('#formCartao').valid();

    /* if (content.substring(0, 1) === '4')//&& content.length >= 13 && content.length < 17)
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
     } */
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
        $("#submit").prop("type", "submit");
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
    if (select === "CPF")//o usuário selecionou o documento CPF?
    {//sim
        if (cpf === "")
        {
            if (email === "")
            {
                if (senha === "")
                {
                    $("#bt_cadastro").prop("type", "submit");
                }
            }
        }
    } else if (select === "CNPJ")//o usuário selecionou o documento CNPJ?
    {//sim
        if (cnpj === "")
        {
            if (email === "")
            {
                if (senha === "")
                {
                    $("#bt_cadastro").prop("type", "submit");
                }
            }
        }
    } else if (select === "")//o usuário selecionounenhum documento?
    {//não
        $("#select_doc").focus();
    }

}//fim 

//função para identificar o documentação CPF ou CNPJ
function indentificarSelect()
{
    if ($("#select_doc").val() === "CPF") {
        $("#div_cnpj").toggle(false);
        $("#div_cpf").toggle(true);
    } else if ($("#select_doc").val() === "CNPJ")
    {
        $("#div_cpf").toggle(false);
        $("#div_cnpj").toggle(true);
    } else if ($("#select_doc").val() === "")
    {
        $("#div_cpf").toggle(false);
        $("#div_cnpj").toggle(false);
    }
}
;

//funcao ajax para o CNPJ
function validaCNPJ()
{
    var cnpj = $('#input_cnpj').val();
    if ($('#input_cnpj').val() !== "")
    {
        $.post("CNPJ", {cnpj: cnpj}, function (responseJson)
        {

            var resposta = responseJson;
           
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
    } else
    {
        $("#div_cnpj").removeClass();
        $("#span_cnpj").removeClass();
        $("#statusCNPJ").text("");
    }
}


//function para abrir uma mensagem do prestador de servico
function abrirMsg(id_msg, acao) {
    var btn_acao = acao;
    var url = 'OpenMensagem.jsp?id=';
    var id = id_msg;
    url = url.concat(id);
    url = url.concat('&acao=' + btn_acao);
    //window.open(url,'','height=1024,width=800');
    $("#panel-heading").html("Mensagem");
    var path = url; //Pegamos o caminh"o
    var titulo = "Mensagem"; //pegamos o titulo da página
    document.title = titulo; // Alterar o titulo da página
    window.history.pushState("", titulo, path);
    $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
    $("#conteudo").load(path); //Faz uma requisição http para o servidor.
    window.history.pushState('Object', 'Dashboard', './PrestadorDashboard.jsp');

    $.post("MensagemAberta", {idMensagem: id}, function (responseJson)
    {

    });

    var badge = $('#qtdeMsg').html();

    if (badge !== 0)
    {
        if ((badge - 1) === 0)
        {
            $('#qtdeMsg').html(badge - 1);
            $('#qtdeMsg').html('');
        }
        else
        {
            $('#qtdeMsg').html(badge - 1);
        }
    }

    return false;
}


//função para excluir uma mensagem
function excluirMensagem()
{
    var opcao = confirm("Você deseja realmente excluir essa mensagem?");
    if (opcao) {
        $("#bt_excluir").prop("type", "submit");
    }
}
//function para excluir a mensagem na front da caixa de mensagem do prestadro de serviço
function excluirMensagemFront(op, id_entrada, id_msg)
{
    var opcao = confirm("Você deseja realmente excluir essa mensagem?");
    if (opcao) {
        var operacao = op;
        var entrada = id_entrada;
        var txtId = id_msg;
        var local = 'entrada';
        $.post("ExcluirMensagemFront", {operacao: operacao, entrada: entrada, txtId: txtId, local: local}, function (responseJson) {
            var resposta = responseJson;
            if (resposta === "Mensagem excluida com sucesso!")
            {
                alert("Mensagem excluída com sucesso!");
                $("#caixa_entrada").trigger("click");
            } else {
                $("#div_fail").toggle(true);
            }

        });
    }//if
}//function


//function para responder as mensagens (prestador de servico)
function responderMsg(id_msg) {
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

//function para ativar o botão de enviar mensagem
function showButtonMsg() {
    $("#div_mensagem").toggle(true);
    $("#btn_enviar").toggle(false);
}


//function para excluir a mensagem na front de mensagens enviadas
function excluirMsgEnviadasFront(op, id_entrada, id_msg)
{
    var opcao = confirm("Você deseja realmente excluir essa mensagem?");
    if (opcao) {
        var operacao = op;
        var entrada = id_entrada;
        var txtId = id_msg;
        var local = 'enviadas';
        $.post("ExcluirMensagemFront", {operacao: operacao, entrada: entrada, txtId: txtId, local: local}, function (responseJson) {
            var resposta = responseJson;
            if (resposta === "Mensagem excluida com sucesso!")
            {
                alert("Mensagem excluída com sucesso!");
                $("#mensagens_enviadas").trigger("click");
            } else {
                $("#div_fail").toggle(true);
            }

        });
    }//if
}//function

//function para abrir uma mensagem do cleinte
function abrirMsgCliente(id_msg, acao)
{
    var btn_acao = acao;
    var url = 'OpenMensagemCliente.jsp?id=';
    var id = id_msg;
    url = url.concat(id);
    url = url.concat('&acao=' + btn_acao);
    //window.open(url,'','height=1024,width=800');
    $("#panel-heading").html("Mensagem");
    var path = url; //Pegamos o caminh"o
    var titulo = "Mensagem"; //pegamos o titulo da página
    document.title = titulo; // Alterar o titulo da página
    window.history.pushState("", titulo, path);
    $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
    $("#conteudo").load(path); //Faz uma requisição http para o servidor.
    window.history.pushState('Object', 'Dashboard', './ClienteDashboard.jsp');

    $.post("MensagemAberta", {idMensagem: id}, function (responseJson) {

    });

    var badge = $('#qtdeMsg').html();

    if (badge !== 0)
    {
        if ((badge - 1) === 0)
        {
            $('#qtdeMsg').html(badge - 1);
            $('#qtdeMsg').html('');
        }
        else
        {
            $('#qtdeMsg').html(badge - 1);
        }
    }
    return false;
}

//function para excluir a mensagem na front da caixa de mensagem
function excluirMsgFrontCliente(op, id_entrada, id_msg)
{
    var opcao = confirm("Você deseja realmente excluir essa mensagem?");
    if (opcao) {
        var operacao = op;
        var entrada = id_entrada;
        var txtId = id_msg;
        var local = 'entrada';
        $.post("ExcluirMensagemFront", {operacao: operacao, entrada: entrada, txtId: txtId, local: local}, function (responseJson) {
            var resposta = responseJson;
            if (resposta === "Mensagem excluida com sucesso!")
            {
                alert("Mensagem excluída com sucesso!");
                $("#caixa_entrada_cliente").trigger("click");
            } else {
                $("#div_fail").toggle(true);
            }

        });
    }//if
}//function

//function para excluir a mensagem na front de mensagens enviadas
function excluirMsgEnviadasFrontCliente(op, id_entrada, id_msg)
{
    var opcao = confirm("Você deseja realmente excluir essa mensagem?");
    if (opcao) {
        var operacao = op;
        var entrada = id_entrada;
        var txtId = id_msg;
        var local = 'enviadas';
        $.post("ExcluirMensagemFront", {operacao: operacao, entrada: entrada, txtId: txtId, local: local}, function (responseJson) {
            var resposta = responseJson;
            if (resposta === "Mensagem excluida com sucesso!")
            {
                alert("Mensagem excluída com sucesso!");
                $("#mensagens_enviadas_cliente").trigger("click");
            } else {
                $("#div_fail").toggle(true);
            }

        });
    }//if
}//function
//
//function para responder a mensagend (Cliente)
function responderMsgCliente(id_msg) {
    var url = 'ResponderMendagemCliente.jsp?id=';
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
    window.history.pushState('Object', 'Dashboard', './ClienteDashboard.jsp');
    return false;
}
//funcao para mudar a legenda de um gráfico em JS
function legend(parent, data)
{
    legend(parent, data, null);
}

function legend(parent, data, chart) {
    parent.className = 'legend';
    var datas = data.hasOwnProperty('datasets') ? data.datasets : data;

    // remove possible children of the parent
    while (parent.hasChildNodes()) {
        parent.removeChild(parent.lastChild);
    }

    var show = chart ? showTooltip : noop;
    datas.forEach(function (d, i) {
        //span to div: legend appears to all element (color-sample and text-node)
        var title = document.createElement('div');
        title.className = 'title';
        parent.appendChild(title);

        var colorSample = document.createElement('div');
        colorSample.className = 'color-sample';
        colorSample.style.backgroundColor = d.hasOwnProperty('strokeColor') ? d.strokeColor : d.color;
        colorSample.style.borderColor = d.hasOwnProperty('fillColor') ? d.fillColor : d.color;
        title.appendChild(colorSample);

        var text = document.createTextNode(d.label);
        text.className = 'text-node';
        title.appendChild(text);

        show(chart, title, i);
    });

    alert('Executou Legende');
}

//add events to legend that show tool tips on chart
function showTooltip(chart, elem, indexChartSegment)
{
    var helpers = Chart.helpers;

    var segments = chart.segments;
    //Only chart with segments
    if (typeof segments !== 'undefined')
    {
        helpers.addEvent(elem, 'mouseover', function () {
            var segment = segments[indexChartSegment];
            segment.save();
            segment.fillColor = segment.highlightColor;
            chart.showTooltip([segment]);
            segment.restore();
        });

        helpers.addEvent(elem, 'mouseout', function () {
            chart.draw();
        });
    }
}

function noop() {
}


//definindo cores aleatórios
function aleatorio(inferior, superior)
{
    numPossibilidades = superior - inferior;
    aleat = Math.random() * numPossibilidades;
    aleat = Math.floor(aleat);
    return parseInt(inferior) + aleat;
}

function dar_cor_aleatoria()
{
    hexadecimal = new Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F");
    cor_aleatoria = "#";
    for (i = 0; i < 6; i++) {
        posarray = aleatorio(0, hexadecimal.length);
        cor_aleatoria += hexadecimal[posarray];
    }
    return cor_aleatoria;
}
function refreshMenu(url)
{
    $("#panel-heading").html("Pedido");
    var path = url;
    var titulo = "Teste"; //pegamos o titulo da página
    document.title = titulo; // Alterar o titulo da página
    window.history.pushState("", titulo, path);
    $("#conteudo").empty(''); //Limpa para poder colocar o conteúdo.
    $("#conteudo").load(path); //Faz uma requisição http para o servidor.
    window.history.pushState('Object', 'Dashboard', './ClienteDashBoard.jsp');
    alert(url);
    return false;
}

function atualizarDataHora()
{
    var dataInicio = $('#txtDataInicio').val();
    var dataFim = $('#txtDataTermino').val();
    var hora = $('#txtHora').val();
    var id = $('#txtId').val();

    $.post("AtualizarDataHora", {txtDataInicio: dataInicio, txtDataTermino: dataFim, txtHora: hora, txtId: id}, function (responseJson)
    {
        if (responseJson === null || responseJson === "")
        {
            swal({
                title: "Sinto Muito!",
                text: "Ocorreu algum problema no processo. =/",
                type: "error",
                showConfirmButton: true,
                confirmButtonText: "OK"
            });
        }
        else
        {
            var pedido = jQuery.parseJSON(responseJson);

            $('#spanDataInicio').html(pedido.dataInicio);
            $('#spanDataFim').html(pedido.dataFim);
            $('#spanHora').html(pedido.hora);

            swal({
                title: "Sucesso!",
                text: "Seus dados foram atualizados",
                type: "success",
                showConfirmButton: true,
                confirmButtonText: "OK"
            });
        }
    });

    return false;
}

function salvarTransacao()
{
    var numero_cartao = $('#txtNumeroCartao').val();
    var numero_seg = $('#txtCodSeg').val();
    var titular = $('#txtTitular').val();
    var validade = $('#txtValidade').val();
    var valor = $('#txtValor').val();
    var termo = $('#txtTermo').val();
    var idCliente = $('#txtIdCliente').val();
    var idPrestador = $('#txtIdPrestador').val();
    var idPedido = $('#txtIdPedido').val();

    //Regras de validação para o formulário de transferência
    $('#formTransferencia').validate({
        rules: {
            txtNumeroCartao: {
                required: true,
                creditcard: true
            },
            txtCodSeguranca: {
                required: true,
                maxlength: 3
            },
            txtTitular: {
                required: true
            }
        },
        messages: {
            txtNumeroCartao: {
                required: "Campo obrigatório",
                creditcard: "Cartão inválido"
            },
            txtCodSeguranca: {
                required: "Campo obrigatório",
                maxlength: "Máximo 3 digitos"
            },
            txtTitular: {
                required: "Campo obrigatório"
            },
            txtValidade: {
                required: "Campo obrigatório"
            },
            txtValor: {
                required: "Campo obrigatório"
            },
            txtTermo: {
                required: "Por favor, aceito nosso termo de compromisso."
            }
        }
    });

    if ($('#formTransferencia').valid())//validando formulário antes de enviar a requisição
    {
        swal({
            title: 'Confirmar pagamento?',
            text: '',
            type: 'info',
            showCancelButton: true,
            CloseOnConfirm: false,
            showLoaderOnConfirm: true
        }, function ()
        {
            $.ajax({
                type: 'POST',
                url: "SalvarTransacao",
                data: {txtNumeroCartao: numero_cartao, txtCodSeguranca: numero_seg,
                    txtTitular: titular, txtValidade: validade, txtValor: valor, txtTermo: termo,
                    txtIdCliente: idCliente, txtIdPrestador: idPrestador, operacao: 'Salvar', txtIdPedido: idPedido},
                success: function (json) {
                    //alert('Resultado: '+json);
                    if (json !== null && json !== "")
                    {
                        swal({
                            title: "Desculpe!",
                            text: json,
                            type: "error",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        });
                    }
                    else
                    {
                        swal({
                            title: "Sucesso!",
                            text: "Transacao realizada!",
                            type: "success",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        });

                        //destravando opções
                        $('#btnChat').removeClass("disabled");
                        $('#btnMensagem').removeClass("disabled");
                        $('#btnAvaliacao').removeClass("disabled");
                        $('#btnPagamento').addClass("disabled");
                        $('#btnPagamento').html('Pagamento Efetuado');
                        //fechando modal
                        $('#fecharTransfe').click();
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    swal("Desculpe!", "Algum erro ocorreu. Tente novamente mais tarde.", "warning");
                },
                beforeSend: function ()
                {
                    /**/
                },
                complete: function (data) {
                }
            });
        });
    }
}

//funcao para remover um consultor
function removerConsultor()
{
    swal({
        title: "Remover Consultor?",
        text: "Remover esse consultor, irá quebrar o atual vínculo de Prestação de Serviços. " +
                "Você quer realmente fazer isso?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Sim, quero remove-lo!",
        cancelButtonText: "Cancelar",
        closeOnConfirm: false
    },
    function () {
        //requisão ajax para remoção do consultor
        var idCliente = $('#txtIdCliente').val();
        var idPrestador = $('#txtIdPrestador').val();
        var idPedido = $('#txtIdPedido').val();

        $.ajax({
            type: 'POST',
            url: "RemoverConsultor",
            data: {txtIdCliente: idCliente, txtIdPrestador: idPrestador, txtIdPedido: idPedido},
            success: function (data, textStatus, jqXHR)
            {
                $('#lb_consultor').html("Nenhum prestador selecionado para esse Pedido!");
                $('#btnRemover').addClass("disabled");
                swal("Removido!", "Consultor removido", "success");
            },
            beforeSend: function (xhr) {

            },
            error: function (jqXHR, textStatus, errorThrown) {
                swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
            },
            complete: function (jqXHR, textStatus) {
                //alert("requisição completa");
            }
        });
    });
}

//envia requisão ajax (Selecionar Prestador)
function selecionarPrestador(prestador,valor)
{
    var txtIdPedido = $('#txtIdPedido').val();
    swal({
        title: "Selecionar Prestador?",
        text: "",
        type: "info",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true
    },
    function () {
        $.ajax({
            type: 'POST',
            url: "SelecionarPrestador",
            data: {txtIdPrestador: prestador, txtIdPedido: txtIdPedido, txtValor:valor},
            success: function (data, textStatus, jqXHR) {

                if (data !== null && data !== "")
                {
                    swal("Desculpe!", data, "warning");
                }
                else
                {
                    swal("Parabéns!", "Prestador selecionado com sucesso!", "success");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                swal("Desculpe!", "Algum erro ocorreu no sistema. Tente novamente mais tarde", "warning");
            },
            beforeSend: function (xhr) {

            },
            complete: function (jqXHR, textStatus) {

            }

        });
    });
}

//avaliar prestador
function avaliarPrestador() {
    var point = $('#inputAvalicao').val();
    var coment = $('#comentarioAvaliacao').val();
    var idCliente = $('#txtIdCliente').val();
    var idPrestador = $('#txtIdPrestador').val();
    var idPedido = $('#txtIdPedido').val();

    if (point === '0' || coment.length === 0)
    {
        swal("Atenção", "De uma nota válida ao Consultor", "warning");
    }
    else {
        swal({
            title: "Avaliar Consultoria?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            $.ajax({
                type: 'POST',
                url: 'AvaliarConsultor',
                data: {txtIdPrestador: idPrestador, txtIdPedido: idPedido, txtIdCliente: idCliente, txtNota: point, txtComentario: coment},
                success: function (data, textStatus, jqXHR) {

                    var rs = jQuery.parseJSON(data);

                    if (rs.status === 'success')
                    {
                        swal("Sucesso!", rs.message, "success");
                        $('#btnAvaliacao').addClass("disabled");
                        $('#btnAvaliacao').html("Consultor Avaliado");
                        $('#fecharAvaliacao').click();  //fechando modal
                    }
                    else
                    {
                        swal("Desculpe!", rs.message, "warning");
                    }

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    swal("Desculpe!", data, "warning");
                }
            });
        });
    }
}

//método para liberar a edição de dados no meu perfil
function editarDados()
{
    $(".form-control").removeAttr('disabled');

    $("#input_cpf").prop("readonly", "readonly");
    $("#input_cnpj").prop("readonly");
    $("#skill_bar").prop("disabled", false);

    $("#div_button").toggle(true);
    $("#span_ajuda_disabled").toggle(false);
    $("#div_ajuda").toggle(true);
    $("#sexo").toggle(false);
    $("#div_sexo").toggle(true);

}


//função para atualizar dados do prestador
function atualizarDadosPresatador()
{
    swal({
        title: "Atualização",
        text: "Você quer realmente atualizar seus dados?",
        type: "info",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true
    },
    function () {
        //requisão ajax para remoção do consultor
        var nome = $('#input_nome').val();
        var sobrenome = $('#input_sobrenome').val();
        var sexo = $('#valueSexo').val();
        var cpf = $('#input_cpf').val();
        var cnpj = $('#input_cnpj').val();
        var data = $('#input_data').val();
        var telefone = $('#input_telefone').val();
        var celular = $('#input_celular').val();
        var cep = $('#cep').val();
        var rua = $('#rua').val();
        var numero = $('#numero').val();
        var bairro = $('#bairro').val();
        var cidade = $('#cidade').val();
        var estado = $('#estado').val();
        var complemento = $('#complemento').val();
        var skills = $('#skill_bar').val();

        $.ajax({
            type: 'POST',
            url: "AtualizarPrestador",
            data: {
                operacao: 'Atualizar',
                txtSkill: skills,
                txtNome: nome,
                txtSobrenome: sobrenome,
                txtCpf: cpf,
                txtCnpj: cnpj,
                txtSexo: sexo,
                txtDatanascimento: data,
                txtLogradouro: rua,
                txtNumero: numero,
                txtCep: cep,
                txtBairro: bairro,
                txtCidade: cidade,
                txtEstado: estado,
                txtComplemento: complemento,
                txtTelefone: telefone,
                txtCelular: celular},
            success: function (json)
            {

                if (json !== null && json !== "")
                {
                    swal({
                        title: "Desculpe!",
                        text: json,
                        type: "error",
                        showConfirmButton: true,
                        confirmButtonText: "OK"
                    });
                }
                else
                {
                    swal({
                        title: "Sucesso!",
                        text: "Atualização realizada!",
                        type: "success",
                        showConfirmButton: true,
                        confirmButtonText: "OK"
                    });


                }
            },
            beforeSend: function (xhr) {

            },
            error: function (jqXHR, textStatus, errorThrown) {
                swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
            },
            complete: function (jqXHR, textStatus) {
                $('#meu_perfil').click();
            }
        });
    });

}

//função para atualizar dados do prestador
function atualizarDadosCliente()
{
    swal({
        title: "Atualização",
        text: "Você quer realmente atualizar seus dados?",
        type: "info",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true
    },
    function () {
        //requisão ajax para remoção do consultor
        var nome = $('#input_nome').val();
        var sobrenome = $('#input_sobrenome').val();
        var sexo = $('#valueSexo').val();
        var cpf = $('#input_cpf').val();
        var data = $('#input_data').val();
        var telefone = $('#input_telefone').val();
        var celular = $('#input_celular').val();
        var cep = $('#cep').val();
        var rua = $('#rua').val();
        var numero = $('#numero').val();
        var bairro = $('#bairro').val();
        var cidade = $('#cidade').val();
        var estado = $('#estado').val();
        var complemento = $('#complemento').val();

        $.ajax({
            type: 'POST',
            url: "AtualizarCliente",
            data: {
                operacao: 'Atualizar',
                txtNome: nome,
                txtSobrenome: sobrenome,
                txtCpf: cpf,
                txtSexo: sexo,
                txtDatanascimento: data,
                txtLogradouro: rua,
                txtNumero: numero,
                txtCep: cep,
                txtBairro: bairro,
                txtCidade: cidade,
                txtEstado: estado,
                txtComplemento: complemento,
                txtTelefone: telefone,
                txtCelular: celular},
            success: function (json)
            {

                if (json !== null && json !== "")
                {
                    swal({
                        title: "Desculpe!",
                        text: json,
                        type: "error",
                        showConfirmButton: true,
                        confirmButtonText: "OK"
                    });
                }
                else
                {
                    swal({
                        title: "Sucesso!",
                        text: "Atualização realizada!",
                        type: "success",
                        showConfirmButton: true,
                        confirmButtonText: "OK"
                    });


                }
            },
            beforeSend: function (xhr) {

            },
            error: function (jqXHR, textStatus, errorThrown) {
                swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
            },
            complete: function (jqXHR, textStatus) {
                $('#meu_perfil_cliente').click();
            }
        });
    });

}

//função para o prestador se candidatar a um pedido
function candidatar(id)
{
    swal({
        title: "Valor da consultoria!",
        text: "Digite o valor da consultoria. Somente Números!",
        type: "input",
        inputType: "number",
        showCancelButton: true,
        closeOnConfirm: false, animation: "slide-from-top",
        inputPlaceholder: "Exemplo: 1050"},
    function (inputValue) {
        if (inputValue === false)
            return false;
        if (inputValue === "") {
            swal.showInputError("Você precisa preencher esse campo!");
            return false;
        }
        var testeCampo = "";
        testeCampo = inputValue.toString();

        if (testeCampo.indexOf('.') !== -1) {
            swal.showInputError("Digite somente números!");
            return false;
        }
        if (testeCampo.indexOf('-') !== -1) {
            swal.showInputError("Não digite número negativo!");
            return false;
        }
        var valor = inputValue;

        swal({
            title: "Candidatar",
            text: "Você  realmente deseja candidatar-se?",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            //requisão ajax para remoção do consultor
            var id_prestador = $('#id_prestador').val();
            var id_pedido = id;

            $.ajax({
                type: 'POST',
                url: "Candidatar",
                data: {
                    id_prestador: id_prestador,
                    id_pedido: id_pedido,
                    valor: valor},
                success: function (json)
                {
                    if (json !== null && json !== "")
                    {
                        $("#sub").trigger("click");
                        swal({
                            title: "Desculpe!",
                            text: json,
                            type: "error",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        });
                    }
                    else
                    {
                        $("#sub").trigger("click");
                        swal({
                            title: "Sucesso!",
                            text: "Candidatura realizada!",
                            type: "success",
                            showConfirmButton: true,
                            confirmButtonText: "OK",
                            confirmButtonClass: 'confirm-class',
                            closeOnConfirm: false
                        },
                        function (isConfirm) {

                            if (isConfirm) {
                                swal({
                                    title: "Mural",
                                    text: "Carregando os pedidos para o mural",
                                    timer: 10000,
                                    imageUrl: "http://oi61.tinypic.com/65r2wp.jpg",
                                    showConfirmButton: false
                                });

                            }
                        });

                    }
                },
                beforeSend: function (xhr) {

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
                },
                complete: function (jqXHR, textStatus) {
                    $("#sub").trigger("click");
                }
            });
        });
    });
}

function buscarNotificationCliente(){
    //buscar notificações
   
    $.ajax({
        type: 'POST',
        url: "NotifyConsultar",
        data: {
        operacao: 'Consultar'
        },
    success: function (json)
    {
        if (json !== null && json !=="Vazio")
        { 
           // canal.replace(",", " ").trim().toUpperCase();
            var notify = jQuery.parseJSON(json);
            PNotify.prototype.options.styling = "bootstrap3";
            var animate_in = $('#animate_in').val(),
            animate_out = $('#animate_out').val();
            new PNotify({
                title: 'Noticifação de Vídeo Chat!',
                text: 'Foi iniciado uma sala de Vídeo Chat' + '\n' +
                'Pedido:' + '\n'+
                '" ' +notify.pedido + '"\n' +
                " Consultor: "+ notify.prestador + '\n' +
                "Aguardando a sua entrada!",
                icon: 'glyphicon glyphicon-pushpin',
                animate: {
                    animate: true,
                    in_class: animate_in,
                    out_class: animate_out
                },
                confirm: {  
                confirm: true,
                buttons: [{
                    text: 'Ir para o vídeo chat',
                    addClass: 'btn-success',
                    click: function() {
                      window.location.replace("Notify?idCliente="+notify.idCliente +
                            "&idPrestador="+ notify.idPrestador +
                            "&idPedido="+ notify.idPedido +
                            "&Canal="+ notify.canal);
                    }
                }]
                }
            });
            
        }
        
    },
    beforeSend: function (xhr) {

    },
    error: function (jqXHR, textStatus, errorThrown) {
        
    },
    complete: function (jqXHR, textStatus) { 
        
    }
   });

}

function buscarNotificationPrestador(){
    //buscar notificações
   
    $.ajax({
        type: 'POST',
        url: "NotifyConsultar",
        data: {
        operacao: 'Consultar'
        },
    success: function (json)
    {
        if (json !== null && json !=="Vazio")
        {   
            
           // canal.replace(",", " ").trim().toUpperCase();
            var notify = jQuery.parseJSON(json);
            PNotify.prototype.options.styling = "bootstrap3";
            var animate_in = $('#animate_in').val(),
            animate_out = $('#animate_out').val();
            new PNotify({
                title: 'Noticifação de Vídeo Chat!',
                text: 'Foi iniciado uma sala de Vídeo Chat! \n\
                Pedido:' +'\n'+ 
                '"' + notify.pedido +'"\n' +
                " Cliente: "+ notify.cliente+ '\n' +
                "Aguardando a sua entrada!",
                icon: 'glyphicon glyphicon-pushpin',
                animate: {
                    animate: true,
                    in_class: animate_in,
                    out_class: animate_out
                } ,
                confirm: {  
                confirm: true,
                buttons: [{
                    text: 'Ir para o vídeo chat',
                    addClass: 'btn-success',
                    click: function() {
                    // window.location.replace("PrestadorVideoConferencia.jsp?canal="+notify.canal); 
                    window.location.replace("Notify?idCliente="+notify.idCliente +
                            "&idPrestador="+ notify.idPrestador +
                            "&idPedido="+ notify.idPedido +
                            "&Canal="+ notify.canal);
                    }
                }]
                }
            });
            
        }
        
    },
    beforeSend: function (xhr) {

    },
    error: function (jqXHR, textStatus, errorThrown) {
        
    },
    complete: function (jqXHR, textStatus) { 
        
    }
   });

}

//função para atualizar dados do headhunter
function atualizarDadosHeadHunter()
{
    swal({
        title: "Atualização",
        text: "Você quer realmente atualizar seus dados?",
        type: "info",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true
    },
    function () {
        //requisão ajax para remoção do consultor
        var nome = $('#input_nome').val();
        var sobrenome = $('#input_sobrenome').val();
        var sexo = $('#valueSexo').val();
        var cpf = $('#input_cpf').val();
        var cnpj = $('#input_cnpj').val();
        var data = $('#input_data').val();
        var telefone = $('#input_telefone').val();
        var celular = $('#input_celular').val();
        var cep = $('#cep').val();
        var rua = $('#rua').val();
        var numero = $('#numero').val();
        var bairro = $('#bairro').val();
        var cidade = $('#cidade').val();
        var estado = $('#estado').val();
        var complemento = $('#complemento').val();
        var txtEmpresa = $('#input_empresa').val();
     
        $.ajax({
            type: 'POST',
            url: "AtualizarHeadHunter",
            data: {
                operacao: 'Atualizar',
                txtNome: nome,
                txtSobrenome: sobrenome,
                txtCpf: cpf,
                txtCnpj: cnpj,
                txtSexo: sexo,
                txtDatanascimento: data,
                txtLogradouro: rua,
                txtNumero: numero,
                txtCep: cep,
                txtBairro: bairro,
                txtCidade: cidade,
                txtEstado: estado,
                txtComplemento: complemento,
                txtTelefone: telefone,
                txtCelular: celular,
                txtEmpresa:txtEmpresa},
            success: function (json)
            {

                if (json !== null && json !== "")
                {
                    swal({
                        title: "Desculpe!",
                        text: json,
                        type: "error",
                        showConfirmButton: true,
                        confirmButtonText: "OK"
                    });
                }
                else
                {
                    swal({
                        title: "Sucesso!",
                        text: "Atualização realizada!",
                        type: "success",
                        showConfirmButton: true,
                        confirmButtonText: "OK"
                    });


                }
            },
            beforeSend: function (xhr) {

            },
            error: function (jqXHR, textStatus, errorThrown) {
                swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
            },
            complete: function (jqXHR, textStatus) {
                $('#meu_perfil_head').click();
            }
        });
    });

}
//função para atualizar o email do prestador de servico
function atualizarEmailPrestador()
{
    var email = $('#statusEmail').text();
    var confirm = $('#status_confirm').text();
   if(email === "" && confirm === "")
   {
        swal({
            title: "Atualização",
            text: "Você quer realmente atualizar seu E-mail?",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            //requisão ajax para remoção do consultor
            var txtNovoEmail = $('#input_email').val();


            $.ajax({
                type: 'POST',
                url: "AlterarEmail",
                data: {
                    txtNovoEmail:txtNovoEmail},
                success: function (json)
                {

                    if (json !== null && json !== "")
                    {
                        swal({
                            title: "Desculpe!",
                            text: json,
                            type: "error",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        });
                    }
                    else
                    {
                        swal({
                            title: "Sucesso!",
                            text: "Atualização realizada!\n\
                            Será realizado o logoff, autentique-se novamente!",
                            type: "success",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        }, 
                        function(isConfirm){   
                            if (isConfirm) {    
                                window.location.replace("logoff");
                        }}
                        );



                    }
                },
                beforeSend: function (xhr) {

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
                },
                complete: function (jqXHR, textStatus) {
                  
                }
            });
        });
    }//if
    else
    {
        swal({
            title: "Erro!",
            text: "E-mail digitado já existe ou os E-mails não correspondem !",
            type: "warning",
            showCancelButton: false,
            closeOnConfirm: true
        });
    }
}

//função para atualizar a senha do prestador de servico
function atualizarSenhaPrestador()
{
    var senha= $('#statusEmail').text();
    var confirm = $('#status_senha_confirm').text();
   if(senha === "" && confirm === "")
   {
        swal({
            title: "Atualização",
            text: "Você quer realmente atualizar sua senha?",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            //requisão ajax para remoção do consultor
            var txtNovaSenha = $('#input_senha').val();


            $.ajax({
                type: 'POST',
                url: "AlterarSenha",
                data: {
                   txtNovaSenha:txtNovaSenha},
                success: function (json)
                {

                    if (json !== null && json !== "")
                    {
                        swal({
                            title: "Desculpe!",
                            text: json,
                            type: "error",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        });
                    }
                    else
                    {
                        swal({
                            title: "Sucesso!",
                            text: "Atualização realizada!\n\
                            Será realizado o logoff, autentique-se novamente!",
                            type: "success",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        }, 
                        function(isConfirm){   
                            if (isConfirm) {    
                                window.location.replace("logoff");
                        }}
                        );



                    }
                },
                beforeSend: function (xhr) {

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
                },
                complete: function (jqXHR, textStatus) {
                   
                }
            });
        });
    }//if
    else
    {
        swal({
            title: "Erro!",
            text: "Senha digitado já existe ou as senhas não correspondem !",
            type: "warning",
            showCancelButton: false,
            closeOnConfirm: true
        });
    }
}

//função para atualizar o email do prestador de servico
function atualizarEmailHeadHunter()
{
    var email = $('#statusEmail').text();
    var confirm = $('#status_confirm').text();
   if(email === "" && confirm === "")
   {
        swal({
            title: "Atualização",
            text: "Você quer realmente atualizar seu E-mail?",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            //requisão ajax para remoção do consultor
            var txtNovoEmail = $('#input_email').val();


            $.ajax({
                type: 'POST',
                url: "AlterarEmail",
                data: {
                    txtNovoEmail:txtNovoEmail},
                success: function (json)
                {

                    if (json !== null && json !== "")
                    {
                        swal({
                            title: "Desculpe!",
                            text: json,
                            type: "error",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        });
                    }
                    else
                    {
                        swal({
                            title: "Sucesso!",
                            text: "Atualização realizada!\n\
                            Será realizado o logoff, autentique-se novamente!",
                            type: "success",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        }, 
                        function(isConfirm){   
                            if (isConfirm) {    
                                window.location.replace("logoff");
                        }}
                        );



                    }
                },
                beforeSend: function (xhr) {

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
                },
                complete: function (jqXHR, textStatus) {
                   
                }
            });
        });
    }//if
    else
    {
        swal({
            title: "Erro!",
            text: "E-mail digitado já existe ou os E-mails não correspondem !",
            type: "warning",
            showCancelButton: false,
            closeOnConfirm: true
        });
    }
}

//função para atualizar a senha do prestador de servico
function atualizarSenhaHeadHunter()
{
    var senha= $('#statusEmail').text();
    var confirm = $('#status_senha_confirm').text();
   if(senha === "" && confirm === "")
   {
        swal({
            title: "Atualização",
            text: "Você quer realmente atualizar sua senha?",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            //requisão ajax para remoção do consultor
            var txtNovaSenha = $('#input_senha').val();


            $.ajax({
                type: 'POST',
                url: "AlterarSenha",
                data: {
                   txtNovaSenha:txtNovaSenha},
                success: function (json)
                {

                    if (json !== null && json !== "")
                    {
                        swal({
                            title: "Desculpe!",
                            text: json,
                            type: "error",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        });
                    }
                    else
                    {
                        swal({
                            title: "Sucesso!",
                            text: "Atualização realizada!\n\
                            Será realizado o logoff, autentique-se novamente!",
                            type: "success",
                            showConfirmButton: true,
                            confirmButtonText: "OK"
                        }, 
                        function(isConfirm){   
                            if (isConfirm) {    
                                window.location.replace("logoff");
                        }}
                        );



                    }
                },
                beforeSend: function (xhr) {

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
                },
                complete: function (jqXHR, textStatus) {
                   
                }
            });
        });
    }//if
    else
    {
        swal({
            title: "Erro!",
            text: "Senha digitado já existe ou as senhas não correspondem !",
            type: "warning",
            showCancelButton: false,
            closeOnConfirm: true
        });
    }
}

function modalPerfil(id){
    var idPrestador = id;
    
    $.ajax({
        type: 'POST' ,
        url: 'ConsultarUmPrestador',
        data:{txtIdPrestador:idPrestador},
        success: function (data, textStatus, jqXHR) {
            //alert(data);
            var prestador = jQuery.parseJSON(data);
            
            $('#nome').html(prestador.nome+' '+prestador.sobrenome);
            var hb = '';
            /*for(i = 0; i < prestador.habilidades.length;i++)
            {
                hb += prestador.habilidades[i].descricao; 
            }*/
            //alert(prestador.habilidades);
            //$('#habilidades').text("Java");
            var input = document.getElementById('habilidades');
            input.value = prestador.habilidades;
            $('#email').html(prestador.celular);
            //alert("Habilidades: "+prestador.habilidades[2].descricao);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Erro '+errorThrown+' texto: '+textStatus);
        }
    });
}