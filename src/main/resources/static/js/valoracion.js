$(document).ready(function () {

    //FORMULARIO ACTFISICA.HTML
    document.getElementById('minafv').addEventListener('input', calcularmetsafv);
    document.getElementById('horaafv').addEventListener('input', calcularminafv);
    document.getElementById('vecesafv').addEventListener('input', calcularmetsafv);


    document.getElementById('minafm').addEventListener('input', calcularmetsafm);
    document.getElementById('horaafm').addEventListener('input', calcularminafm);
    document.getElementById('vecesafm').addEventListener('input', calcularmetsafm);

    document.getElementById('mincaminar').addEventListener('input', calcularmetscaminar);
    document.getElementById('horacaminar').addEventListener('input', calcularmincaminar);
    document.getElementById('vecescaminar').addEventListener('input', calcularmetscaminar);

    document.getElementById('horasentado').addEventListener('input', calcularminsentado);
    document.getElementById('metstotales').addEventListener('input', calcularclasificacion);
    document.getElementById('metstotales').addEventListener('change', calcularclasificacion);


    function calcularclasificacion() {
        var metstotales = parseInt(document.getElementById('metstotales').value);
        var resultado;
        if (metstotales > 1500) { resultado = "vigorosa"; }
        else { resultado = "moderada"; }
        document.getElementById('clasificacion').value = resultado;

    }

    function calcularmetstotales() {
        var metscaminar = parseInt(document.getElementById('metscaminar').value);
        var metsafv = parseInt(document.getElementById('metsafv').value);
        var metsafm = parseInt(document.getElementById('metsafm').value);
        var resultado = metscaminar + metsafm + metsafv;
        document.getElementById('metstotales').value = resultado.toFixed(0);
        calcularclasificacion();
    }

    function calcularminsentado() {
        var horasentado = parseInt(document.getElementById('horasentado').value);
        document.getElementById('minsentado').value = (horasentado * 60);
    }

    function calcularmincaminar() {
        var horacaminar = parseInt(document.getElementById('horacaminar').value);
        document.getElementById('mincaminar').value = (horacaminar * 60);
        calcularmetscaminar();
    }


    function calcularmetscaminar() {
        var mincaminar = parseInt(document.getElementById('mincaminar').value);
        var vecescaminar = parseInt(document.getElementById('vecescaminar').value);
        var resultado = 3.3 * vecescaminar * mincaminar;
        document.getElementById('metscaminar').value = resultado.toFixed(0);
        calcularmetstotales();

    }


    function calcularminafm() {
        var horaafm = parseInt(document.getElementById('horaafm').value);
        document.getElementById('minafm').value = (horaafm * 60);
        calcularmetsafm();
    }


    function calcularmetsafm() {
        var minafm = parseInt(document.getElementById('minafm').value);
        var vecesafm = parseInt(document.getElementById('vecesafm').value);
        var resultado1 = 4 * vecesafm * minafm;
        document.getElementById('metsafm').value = resultado1.toFixed(0);
        calcularmetstotales();

    }


    function calcularmetsafv() {
        var minafv = parseInt(document.getElementById('minafv').value);
        var vecesafv = parseInt(document.getElementById('vecesafv').value);
        var resultado = 8 * vecesafv * minafv;
        document.getElementById('metsafv').value = resultado.toFixed(0);
        calcularmetstotales();

    }


    function calcularminafv() {
        var horaafv = parseInt(document.getElementById('horaafv').value);
        document.getElementById('minafv').value = (horaafv * 60);
        calcularmetsafv();
    }

    //FIN FORMULARIO ACTFISICA.HTML

});



function myFunction() {
    if (confirm("Desea guardar los datos?")) {
        alert("Datos guardados exitosamente");
    } else {
        alert("Usted cancelo la acción para guardar");
    }
}