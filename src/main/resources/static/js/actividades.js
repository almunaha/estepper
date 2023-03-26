$(document).ready(function () {

    //Botones actividad
    $(".descripcion").show();
    $(".detalles").hide();
    $(".inscripcion").hide();

    $("#descripcion").css("background-color", "rgb(98, 207, 234)");

    $("#descripcion").click(function () {
        $(this).css("background-color", "rgb(98, 207, 234)");
        $("#detalles").css("background-color", "rgb(175, 198, 204)");
        $("#inscripcion").css("background-color", "rgb(175, 198, 204)");

        $(".descripcion").show();
        $(".detalles").hide();
        $(".inscripcion").hide();
    });

    $("#detalles").click(function () {
        $(this).css("background-color", "rgb(98, 207, 234)");
        $("#inscripcion").css("background-color", "rgb(175, 198, 204)");
        $("#descripcion").css("background-color", "rgb(175, 198, 204)");

        $(".descripcion").hide();
        $(".detalles").show();
        $(".inscripcion").hide();
    });

    $("#inscripcion").click(function () {
        $(this).css("background-color", "rgb(98, 207, 234)");
        $("#detalles").css("background-color", "rgb(175, 198, 204)");
        $("#descripcion").css("background-color", "rgb(175, 198, 204)");

        $(".descripcion").hide();
        $(".detalles").hide();
        $(".inscripcion").show();
    });


});