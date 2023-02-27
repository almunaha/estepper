$(document).ready(function () {

    //Botones del PESO
    $(".pesoTabla").hide();
    $("#form-peso").hide();
    $("#grafica").show();

    $("#registroPeso").click(function () {
        $(".pesoTabla").show();
        $("#grafica").hide();
        $("#form-peso").hide();
    });

    $("#graficaPeso").click(function () {
        $("#grafica").show();
        $(".pesoTabla").hide();
        $("#form-peso").hide();
    });

    $(".btn-plus").click(function () {
        $("#grafica").hide();
        $(".pesoTabla").hide();
        $("#registroPeso").hide();
        $("#graficaPeso").hide();
        $(".btn-plus").hide();
        $("#form-peso").show();
    });

    

});