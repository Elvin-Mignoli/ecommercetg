/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function ()
{
    $('#btnAdd').on("click",function (){
       $('#divSkill').append("<button class='btn btn-primary' type='button'> Messages <span class='badge'>4</span></button>");
    });
});