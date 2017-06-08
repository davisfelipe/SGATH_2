function darHora(){
    setInterval(function(){
    var f=new Date();
    hora=f.getHours()+":"+f.getMinutes()+":"+f.getSeconds();
    fecha=f.getDate()+"/"+f.getMonth()+"/"+f.getFullYear();
    $('#hora').html(fecha+"      "+hora);
},1000);
};
function ponerHora(){
    var f=new Date();
    hora=f.getHours()+":"+f.getMinutes()+":"+f.getSeconds();
    fecha=f.getDate()+"/"+f.getMonth()+"/"+f.getFullYear();
    $('#hora').html(fecha+"      "+hora);
}