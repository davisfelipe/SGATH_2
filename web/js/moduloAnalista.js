$(document).ready(function(){
    ponerHora();
    $('#hora').html(darHora());
    $.ajax({
       type:'POST',
       data:{opcion:1},
       url:"AjaxAnalista",
       success:function(result){
           $('#identificacion').html("<h4>Bienvenido "+result+"</h4>");
       }
   });
   $('#cerrarsesion').click(function(){
       $.ajax({
           type:"POST",
           data:{opcion:2},
           url:"AjaxAnalista",
           success:function(result){
               ventanaInicio();
           }
       });
   });
   $('#enviarPerfil').click(function(){
       $('#principal').load("http://localhost:8084/BD1/gestionPerfil.html");
   });
   $('#enviarProgramacion').click(function(){
       $('#principal').load("http://localhost:8084/BD1/gestionProgramacion.html");
       
   });
   $('#enviarProceso').click(function(){
       $('#principal').load("http://localhost:8084/BD1/gestionProceso.html");
       
   });
});
function ventanaInicio(){
    window.location.assign("http://localhost:8084/BD1/index.html");
}
