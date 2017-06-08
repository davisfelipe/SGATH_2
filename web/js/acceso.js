    $(document).ready(function(){
   $('#enviarAcceso').click(function(){
       var user=$('#user').val();
       var pass=$('#pass').val();
       var cargo;
       $.ajax({
           type:'POST',
           data:{opcion:1,user:user,pass:pass},
           url:"AjaxAcceso",
           success: function(result1){
               if(result1=="true"){
                   $.ajax({
                       type:'POST',
                       data:{opcion:2,user:user},
                       url:"AjaxAcceso"
                   });
                   $.ajax({
                       type:'POST',
                       data:{opcion:4,user:user},
                       url:"AjaxAcceso"
                   });
                   $.ajax({
                       type:'POST',
                       data:{opcion:3,user:user},
                       url:"AjaxAcceso",
                       success:function(result3){
                           cargo=result3;
                           console.log(cargo);
                           switch(cargo){
                               case "Analista":
                                   ventanaAnalista();
                               break;
                           }
                       }
                   });
               }else{
                   $('#mensajeAcceso').html("<h3><center>Acceso Denegado</center></h3>");
               }         
           }
       });
   });
});
function ventanaAnalista(){
    window.location.assign("http://localhost:8084/BD1/moduloAnalista.html");
}
function ventanaAspirante(){
    window.location.assign("http://localhost:8084/BD1/moduloAspirante.html");
}