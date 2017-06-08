var idreq;
$(document).ready(function(){
    $.ajax({
        type:'POST',
        data:{opcion:1},
        url:"AjaxGestionProgramacion",
        success:function(response){
            $('#requerimientos').html(response);
        }
    });
    $('#verRequerimientos').click(function(){
        idreq=$('#idreq').val();
        $.ajax({
            type:'POST',
            data:{opcion:2,dato:idreq},
            url:"AjaxGestionProgramacion",
            success:function(response){
                $('#requerimientoespecifico').html(response);
            }
        });
    });
    $('#agregarFases').click(function(){
        alert("bien");
    });
});

