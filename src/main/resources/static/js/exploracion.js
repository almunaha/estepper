$(document).ready(function () {
    function calcularpuntuacion() {
        var peso = parseInt(document.getElementById('peso').value);
        var altura = parseInt(document.getElementById('altura').value);
        altura = altura / 100;
        var resultado = peso / (altura * altura);
        document.getElementById('imc').value = resultado.toFixed(0);
    }

    document.getElementById('peso').addEventListener('input', calcularpuntuacion);
    document.getElementById('altura').addEventListener('input', calcularpuntuacion);
});

function myFunction() {
    if (confirm("Desea guardar los datos?")) {
        alert("Datos guardados exitosamente");
    } else {
        alert("Usted cancelo la acción para guardar");
    }
}