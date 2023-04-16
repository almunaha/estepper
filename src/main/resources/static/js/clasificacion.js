$(document).ready(function () {
    function calcularclasificacion() {
        var glucemia = parseInt(document.getElementById('glucemia').value);
        var sog = parseInt(document.getElementById('sog').value);
        var hbA1c = parseInt(document.getElementById('hb').value);
        var edad = /*[[${exploracion.edad}]]*/ null;
        var imc = /*[[${exploracion.imc}]]*/ null;
        var cmcintura = /*[[${exploracion.cmcintura}]]*/ null;
        var sexo = /*[[${exploracion.sexo}]]*/ null;
        var findrisc = /*[[${findrisc.puntuacion}]]*/ null;

        var clasglucemia = 0;
        if (glucemia < 100 || sog < 140 || hbA1c < 5, 7) clasglucemia = 1;
        else if ((glucemia >= 100 && glucemia <= 125) || (sog >= 140 && sog <= 199) || (hbA1c >= 5, 7 && hbA1c <= 6, 4)) clasglucemia = 2;
        else if (glucemia >= 126 || sog >= 200 || hbA1c >= 6, 5) clasglucemia = 3;

        var resultado = 3;
        if (edad >= 35 && findrisc > 14 && clasglucemia == 1 && imc >= 27) resultado = 3;
        else if (edad >= 35 && findrisc > 14 && clasglucemia == 1 && imc < 27) resultado = 6;
        else if (edad >= 35 && findrisc < 15 && imc >= 30) resultado = 4;
        else if (edad >= 35 && findrisc < 15 && imc >= 27 && imc < 30 && ((cmcintura > 88 && sexo == 'FEMENINO') || (cmcintura > 102 && sexo == 'MASCULINO'))) resultado = 5;
        else if (edad >= 35 && findrisc < 15 && imc >= 27 && imc < 30 && ((cmcintura <= 88 && sexo == 'FEMENINO') || (cmcintura <= 102 && sexo == 'MASCULINO'))) resultado = 6;
        else if (edad >= 35 && findrisc < 15 && imc < 27) resultado = 6;
        else if (edad < 35 && imc >= 30) resultado = 4;
        else if (edad < 35 && imc >= 27 && imc < 30 && ((cmcintura > 88 && sexo == 'FEMENINO') || (cmcintura > 102 && sexo == 'MASCULINO'))) resultado = 5;
        else if (edad < 35 && imc >= 27 && imc < 30 && ((cmcintura <= 88 && sexo == 'FEMENINO') || (cmcintura <= 102 && sexo == 'MASCULINO'))) resultado = 6;
        else if (edad < 35 && imc < 27) resultado = 6;
        else if (clasglucemia == 3) resultado = 1;
        else if (clasglucemia == 2) resultado = 2;

        document.getElementById('clasificacion').value = resultado;
    }

    document.getElementById('glucemia').addEventListener('input', calcularclasificacion);
    document.getElementById('sog').addEventListener('input', calcularclasificacion);
    document.getElementById('hb').addEventListener('input', calcularclasificacion);
});

function myFunction() {
    if (confirm("Desea guardar los datos?")) {
        alert("Datos guardados exitosamente");
    } else {
        alert("Usted cancelo la acción para guardar");
    }
}