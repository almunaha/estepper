$(document).ready(function () {
    //FORMULARIO ALIMENTACIONVAL.HTML
    document.getElementById('aceite').addEventListener('input', calcularaceite);
    document.getElementById('racaceite').addEventListener('input', calcularacraceite);
    document.getElementById('racfruta').addEventListener('input', calcularacfruta);
    document.getElementById('racverdura').addEventListener('input', calcularacverdura);
    document.getElementById('raccarne').addEventListener('input', calcularaccarne);
    document.getElementById('racmantequilla').addEventListener('input', calcularacmantequilla);
    document.getElementById('racbebidas').addEventListener('input', calcularacbebidas);
    document.getElementById('racvino').addEventListener('input', calcularacvino);
    document.getElementById('raclegumbres').addEventListener('input', calcularaclegumbres);
    document.getElementById('racpescado').addEventListener('input', calcularacpescado);
    document.getElementById('racreposteria').addEventListener('input', calcularacreposteria);
    document.getElementById('racfrutosecos').addEventListener('input', calcularacfrutosecos);
    document.getElementById('carneblanca').addEventListener('input', calcularcarneblanca);
    document.getElementById('racsofrito').addEventListener('input', calcularracsofrito);

    function calcularadherencia() {
        var ptos = Number(document.getElementById('puntuacion').value);
        if (ptos < 9) resultado = "baja";
        else resultado = "alta";
        document.getElementById('adherencia').value = resultado;
    }
    function calcularpuntuacion() {
        var ptosaceite = Number(document.getElementById('ptosaceite').value);
        var ptosracaceite = Number(document.getElementById('ptosracaceite').value);
        var ptosfruta = Number(document.getElementById('ptosracfruta').value);
        var ptosverdura = Number(document.getElementById('ptosracverdura').value);
        var ptoscarne = Number(document.getElementById('ptosraccarne').value);
        var ptosmantequilla = Number(document.getElementById('ptosracmantequilla').value);
        var ptosbebidas = Number(document.getElementById('ptosracbebidas').value);
        var ptosvino = Number(document.getElementById('ptosracvino').value);
        var ptoslegumbres = Number(document.getElementById('ptosraclegumbres').value);
        var ptospescado = Number(document.getElementById('ptosracpescado').value);
        var ptosreposteria = Number(document.getElementById('ptosracreposteria').value);
        var ptosfrutosecos = Number(document.getElementById('ptosracfrutosecos').value);
        var ptoscarneblanca = Number(document.getElementById('ptoscarneblanca').value);
        var ptossofrito = Number(document.getElementById('ptosracsofrito').value);

        var resultado = ptosaceite + ptosracaceite + ptosfruta + ptosverdura + ptoscarne + ptosmantequilla + ptosbebidas + ptosvino + ptoslegumbres + ptospescado + ptosreposteria + ptosfrutosecos + ptoscarneblanca + ptossofrito;

        document.getElementById('puntuacion').value = resultado;
        calcularadherencia();
    }

    function calcularaceite() {
        var aceite = document.getElementById('aceite').value;
        var resultado;
        if (aceite === "si") { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosaceite').value = resultado;
        calcularpuntuacion();
    }

    function calcularacraceite() {
        var aceite = document.getElementById('racaceite').value;
        var resultado;
        if (aceite > 1) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosracaceite').value = resultado;
        calcularpuntuacion();
    }

    function calcularacfruta() {
        var aceite = document.getElementById('racfruta').value;
        var resultado;
        if (aceite > 2) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosracfruta').value = resultado;
        calcularpuntuacion();
    }

    function calcularacverdura() {
        var aceite = document.getElementById('racverdura').value;
        var resultado;
        if (aceite > 1) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosracverdura').value = resultado;
        calcularpuntuacion();
    }

    function calcularaccarne() {
        var aceite = document.getElementById('raccarne').value;
        var resultado;
        if (aceite < 1) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosraccarne').value = resultado;
        calcularpuntuacion();
    }

    function calcularacmantequilla() {
        var aceite = document.getElementById('racmantequilla').value;
        var resultado;
        if (aceite < 1) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosracmantequilla').value = resultado;
        calcularpuntuacion();
    }

    function calcularacbebidas() {
        var aceite = document.getElementById('racbebidas').value;
        var resultado;
        if (aceite < 1) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosracbebidas').value = resultado;
        calcularpuntuacion();
    }

    function calcularacvino() {
        var aceite = document.getElementById('racvino').value;
        var resultado;
        if (aceite > 2) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosracvino').value = resultado;
        calcularpuntuacion();
    }

    function calcularaclegumbres() {
        var aceite = document.getElementById('raclegumbres').value;
        var resultado;
        if (aceite > 2) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosraclegumbres').value = resultado;
        calcularpuntuacion();
    }

    function calcularacpescado() {
        var aceite = document.getElementById('racpescado').value;
        var resultado;
        if (aceite > 2) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosracpescado').value = resultado;
        calcularpuntuacion();
    }

    function calcularacreposteria() {
        var aceite = document.getElementById('racreposteria').value;
        var resultado;
        if (aceite < 3) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosracreposteria').value = resultado;
        calcularpuntuacion();
    }

    function calcularacfrutosecos() {
        var aceite = document.getElementById('racfrutosecos').value;
        var resultado;
        if (aceite > 0) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosracfrutosecos').value = resultado;
        calcularpuntuacion();
    }

    function calcularcarneblanca() {
        var aceite = document.getElementById('carneblanca').value;
        var resultado;
        if (aceite === "si") { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptoscarneblanca').value = resultado;
        calcularpuntuacion();
    }

    function calcularracsofrito() {
        var aceite = document.getElementById('racsofrito').value;
        var resultado;
        if (aceite > 1) { resultado = 1; }
        else { resultado = 0; }
        document.getElementById('ptosracsofrito').value = resultado;
        calcularpuntuacion();
    }
    //FIN FORMULARIO ALIMENTACIONVAL.HTML

});


function myFunction() {
    if (confirm("Desea guardar los datos?")) {
        alert("Datos guardados exitosamente");
    } else {
        alert("Usted cancelo la acción para guardar");
    }
}