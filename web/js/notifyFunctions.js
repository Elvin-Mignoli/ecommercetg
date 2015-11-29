/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function homechat(user){

    //gravar notify
    var  idCliente = $('#idCliente').val();
    var  idPrestador = $('#idPrestador').val();
    var idPedido = $('#idPedido').val();
    var Canal = $('#canal').val();

    $.ajax({
         type: 'POST',
         url: "NotifyAtualizar",
         data: {
         operacao: 'Atualizar',
         idCliente:idCliente,
         idPedido:idPedido,
         idPrestador: idPrestador,
         Canal: Canal},
     success: function (json)
     {
         if (json !== null && json === "")
         {   
            //alert("Tudo OK!");
         }
         else
         {
            // alert("Fail!"); 
         }
     },
     beforeSend: function (xhr) {

     },
     error: function (jqXHR, textStatus, errorThrown) {
         swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
     },
     complete: function (jqXHR, textStatus) { 
         if(user === 'cliente')
            window.location.replace("ClienteDashboard.jsp");
         if(user === 'prestador')
            window.location.replace("PrestadorDashboard.jsp");
     }
    });
   
};

function logoffchat(){
//gravar notify
    var  idCliente = $('#idCliente').val();
    var  idPrestador = $('#idPrestador').val();
    var idPedido = $('#idPedido').val();
    var Canal = $('#canal').val();

    $.ajax({
         type: 'POST',
         url: "NotifyAtualizar",
         data: {
         operacao: 'Atualizar',
         idCliente:idCliente,
         idPedido:idPedido,
         idPrestador: idPrestador,
         Canal: Canal},
     success: function (json)
     {
         if (json !== null && json === "")
         {   
            //alert("Tudo OK!");
         }
         else
         {
             //alert("Fail!"); 
         }
     },
     beforeSend: function (xhr) {

     },
     error: function (jqXHR, textStatus, errorThrown) {
         swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
     },
     complete: function (jqXHR, textStatus) {        
        /*$("#logoff_chat").attr("href", "logoff");
        $("#logoff_chat");*/
         window.location.replace("logoff");
     }
    });
    
};
/*window.onbeforeunload = function(){
  //return 'Você tem certeza que deseja sair?';
    var opcao = window.confirm("Deseja realmente sair?");
    if(opcao === true)
        alert("oi");
    //gravar notify
    
};


window.onunload = Sair;
function Sair()
{
    //return "A página foi fechada";
    
}
*/