//js de login, findrisc

function activar(obj) {
    frm = document.forms[0];
    for (i = 0; ele = frm.getElementsByTagName('select')[i]; i++)
        ele.style.display = (ele == frm[obj]) ? 'block' : 'none';
}

function calcularValoresRespuesta() {
    var total = 0;

    $(".form-select").each(function () {
        if (isNaN(parseFloat($(this).val()))) {
            total += 0;
        } else {
            total += parseFloat($(this).val());
        }
    });

    var perimetro = 0;
    if (document.getElementById('flexRadioDefault1').checked) perimetro = parseFloat(document.getElementById('select4').value);
    else perimetro = parseFloat(document.getElementById('select3').value);

    total -= perimetro;

    document.getElementById('resultado').value = total;
    document.getElementById('resultado').innerHTML = 'La puntuación obtenida es: ' + total;

    document.getElementById('alerta1').style.display = 'none';
    document.getElementById('alerta2').style.display = 'none';
    document.getElementById('alerta3').style.display = 'none';

    if (total < 7) {
        document.getElementById('explicaciones').innerHTML = 'El riesgo de desarrollar diabetes tipo 2 durante los próximos 10 años es bajo ';
        document.getElementById('alerta1').style.display = '';
    }
    else if ((total >= 7) && (total <= 11)) {
        document.getElementById('explicaciones').innerHTML = 'El riesgo de desarrollar diabetes tipo 2 durante los próximos 10 años es ligeramente elevado ';
        document.getElementById('alerta2').style.display = '';
    }
    else if ((total >= 12) && (total <= 14)) {
        document.getElementById('explicaciones').innerHTML = 'El riesgo de desarrollar diabetes tipo 2 durante los próximos 10 años es moderado ';
        document.getElementById('alerta3').style.display = '';
    }
    else if ((total >= 15) && (total <= 20)) {
        document.getElementById('explicaciones').innerHTML = 'El riesgo de desarrollar diabetes tipo 2 durante los próximos 10 años es alto ';
        document.getElementById('alerta3').style.display = '';
    }
    else if (total > 20) {
        document.getElementById('explicaciones').innerHTML = 'El riesgo de desarrollar diabetes tipo 2 durante los próximos 10 años es muy alto ';
        document.getElementById('alerta3').style.display = '';
    }

}


//Calcular IMC 
function imc() {
    //convertir lo que teclea el usuario a un double
    var num1 = parseFloat(document.getElementById('peso').value);
    var num2 = parseFloat(document.getElementById('altura').value);
    num2 = num2 / 100;

    if (num1 == '') num1 = 0.0;
    if (num2 == '') num2 = 0.0;

    var resultado = (num1 / (num2 * num2));

    document.getElementById('imc').value = resultado.toFixed(2);
}

$(document).ready(function () {

    //ojo del login para mostrar u ocultar la contraseña
    const ojo = document.querySelector("#ojo");
    const iconoOjo = document.getElementById('iconoOjo');

    ojo.addEventListener('click', function () {

        if (this.previousElementSibling.type === 'password') {
            this.previousElementSibling.type = "text";
            iconoOjo.classList.remove('fa-eye');
            iconoOjo.classList.add('fa-eye-slash');
        }

        else if (this.previousElementSibling.type === 'text') {
            this.previousElementSibling.type = "password";
            iconoOjo.classList.remove('fa-eye-slash');
            iconoOjo.classList.add('fa-eye');
        }
    })
});
