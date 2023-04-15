$(document).ready(function () {
    function calcularpuntuacion() {
        var edad = parseInt(document.getElementById('edad').value);
        var cmcintura = parseInt(document.getElementById('cmcintura').value);
        var imc = parseInt(document.getElementById('imc').value);
        var actfis = parseInt(document.getElementById('actfis').value);
        var fruta = parseInt(document.getElementById('fruta').value);
        var medicacion = parseInt(document.getElementById('medicacion').value);
        var glucosa = parseInt(document.getElementById('glucosa').value);
        var diabetes = parseInt(document.getElementById('diabetes').value);
        var resultado = edad + cmcintura + imc + actfis + fruta + medicacion + glucosa + diabetes;
        document.getElementById('puntuacion').value = resultado;
        calcularescala();
    }

    document.getElementById('edad').addEventListener('change', calcularpuntuacion);
    document.getElementById('cmcintura').addEventListener('change', calcularpuntuacion);
    document.getElementById('imc').addEventListener('change', calcularpuntuacion);
    document.getElementById('actfis').addEventListener('change', calcularpuntuacion);
    document.getElementById('fruta').addEventListener('change', calcularpuntuacion);
    document.getElementById('medicacion').addEventListener('change', calcularpuntuacion);
    document.getElementById('glucosa').addEventListener('change', calcularpuntuacion);
    document.getElementById('diabetes').addEventListener('change', calcularpuntuacion);

    function calcularescala() {
        var resultado;
        var puntuacion = parseInt(document.getElementById('puntuacion').value);
        if (puntuacion < 7) resultado = "Bajo";
        else if (puntuacion >= 7 && puntuacion < 12) resultado = "Ligeramente elevado";
        else if (puntuacion >= 12 && puntuacion < 15) resultado = "Moderado";
        else if (puntuacion >= 15 && puntuacion < 21) resultado = "Alto";
        else if (puntuacion > 21) resultado = "Muy alto";
        document.getElementById('escala').value = resultado;
    }
});

function myFunction() {
    if (confirm("Desea guardar los datos?")) {
        alert("Datos guardados exitosamente");
    } else {
        alert("Usted cancelo la acción para guardar");
    }
}