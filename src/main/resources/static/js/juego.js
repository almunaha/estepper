function abrirIframe(modalInst) {
    const modal = new bootstrap.Modal(document.getElementById(modalInst));
    modal.show();
}

//cantidad de ud intercambio para cada momento día y grupo (36 posiciones en la tabla) según las calorías
var totalUnidades = {
    '1200': 
        [[1, 0, 0, 1, 0, 0],
        [0, 0, 0, 0, 2, 0],
        [0, 2, 1, 2, 2, 1],
        [0.5, 0, 0, 1, 0, 0],
        [0, 2, 1, 2, 2, 1],
        [0.5, 0, 0, 0, 0, 0]],
    '1500': 
        [[1, 0, 0, 2, 2, 1],
        [0, 1, 0, 2, 0, 0],
        [0, 2, 1, 4, 2, 1],
        [1, 0, 0, 1, 0, 0],
        [0, 2, 1, 4, 2, 1],
        [0, 0, 0, 0, 0, 0]],
    '1800':
        [[1, 0, 0, 2, 2, 1],
        [0, 1, 0, 2, 0, 0],
        [0, 2, 1, 4, 2, 1],
        [1, 0, 0, 1, 0, 0],
        [0, 2, 1, 4, 2, 1],
        [1, 0, 0, 0, 0, 0]],
    '2000':
        [[1, 0, 0, 2, 2, 1],
        [0, 1, 0, 2, 0, 0],
        [0, 2, 1, 6, 2, 2],
        [1, 0, 0, 1, 0, 0],
        [0, 2, 1, 6, 2, 1],
        [1, 0, 0, 0, 0, 0]]
};

//listado para saber los divs ocupados
let ocupados = [[0, 0, 0, 0, 0, 0],
               [0, 0, 0, 0, 0, 0],
               [0, 0, 0, 0, 0, 0],
               [0, 0, 0, 0, 0, 0],
               [0, 0, 0, 0, 0, 0],
               [0, 0, 0, 0, 0, 0]];

let unidadesIniciales;

let listado = [[[], [], [], [], [], []],
                [[], [], [], [], [], []],
                [[], [], [], [], [], []],
                [[], [], [], [], [], []],
                [[], [], [], [], [], []],
                [[], [], [], [], [], []]];

let unidades; //total de unidades

function establecerUdsIntercambio() {
    var calorias = document.getElementById('calorias').value;
    ocupados = totalUnidades[calorias]; //hasta que no llegue a cero todo se pueden meter alimentos

    unidadesIniciales = JSON.parse(JSON.stringify(ocupados));
}

function comprobarResultado() {
    //listado[0].length: número columnas matriz
    unidades = 0;
    for (let fil = 0; fil < ocupados.length; fil++) {
        for (let col = 0; col < ocupados[0].length; col++) {
            unidades+= unidadesIniciales[fil][col]; //uds intercambio para cada celda
        }
    }

    var errores = 0;
    for (let col = 0; col < listado[0].length; col++) {
        for (let fil = 0; fil < listado.length; fil++) {
            if (listado[fil][col] != '') {
                pos = fil.toString() + col.toString();
                let celda = document.getElementById(pos);

                if (ocupados[fil][col] < 0 || unidadesIniciales[fil][col] > listado[fil][col].length ) { //si se han añadido más elementos de los necesarios o menos de los necesarios -> color amarillo
                    celda.style.backgroundColor = "#F7E4B5";
                    celda.style.color = "#FFFFFF";
                    celda.style.borderRadius = "0";
                    celda.style.border = "2px solid #F9CE65";
                    errores++;
                }

                else { //la celda tiene el número de alimentos correcto y comprobar si tienen bien el grupo
                    let bien = false;
                    for (let i = 0; i < listado[fil][col].length; i++) { //recorrer si hay más de un alimento en la celda
                        let elemento = listado[fil][col][i];

                        //grupo mal --> contabilizar errores e indicarlo con el style
                        if ((col == 0 && elemento.dataset.categoria != "LÁCTEO") ||
                            (col == 1 && elemento.dataset.categoria != "PROTEÍNA") ||
                            (col == 2 && elemento.dataset.categoria != "VERDURA") ||
                            (col == 3 && elemento.dataset.categoria != "FÉCULA") ||
                            (col == 4 && elemento.dataset.categoria != "FRUTA") ||
                            (col == 5 && elemento.dataset.categoria != "GRASA") ) {

                            errores++;
                            celda.style.backgroundColor = "#F7B5B5";
                            celda.style.color = "#FFFFFF";
                            celda.style.borderRadius = "0";
                            celda.style.border = "2px solid #F58F8F";

                            bien = false;
                        }

                        else {  // grupo bien y restar uds de intercambio OK
                            if ((col == 0 && elemento.dataset.categoria == "LÁCTEO") ||
                                (col == 1 && elemento.dataset.categoria == "PROTEÍNA") ||
                                (col == 2 && elemento.dataset.categoria == "VERDURA") ||
                                (col == 3 && elemento.dataset.categoria == "FÉCULA") ||
                                (col == 4 && elemento.dataset.categoria == "FRUTA") ||
                                (col == 5 && elemento.dataset.categoria == "GRASA")) {

                                bien = true; 
                            }
                        }

                        //si el elemento está bien
                        if (bien) {

                            if (unidadesIniciales[fil][col] === 0.5) {
                                unidades = unidades - 0.5;
                            }

                            else {
                                unidades -= 1;
                            }
                        }  
                    }  
                }
            }
        }
    }

    $('#falta').hide();
    $('#correcto').hide();
    $('#incorrecto').hide();

    if(unidades > 0){
        $('#falta').show();
        if(errores > 0){
            $('#incorrecto').show();
        } 
        $('#correcto').hide();
    }
    
    else{
        $('#falta').hide();
        if(errores > 0){
            $('#incorrecto').show();
            $('#correcto').hide();
        }
        else{
            $('#correcto').show();
            $('#incorrecto').hide();
        }
    }
}

//función que evita que se abra como enlace al soltar un alimento
function allowDrop(ev) {
    ev.preventDefault();
}

//que sucede cuando se arrastra un alimento
function drag(ev) {
    //metodo que establece el tipo de datos y el valor de dato arrastrado
    //en este caso el dato es texto y el valor es el id del elemento arrastrado
    ev.dataTransfer.setData("text", ev.target.id);
}

//al meter el alimento en la celda
function drop(ev) {
    //mediante ev.target.id tomo el nombre del id del elemento arrastrado
    //ev.target.id: id de la celda donde se arrastra el alimento

    const fila = parseInt(ev.target.id.substring(0, 1));
    const columna = parseInt(ev.target.id.substring(1));

    var data = ev.dataTransfer.getData("text"); // data es el id
    var alimento = document.getElementById(data); //alimento saca el div de ese id

    //arrastra al listado alimentos el nombre del alimento
    listado[fila][columna].push(alimento);

    //agrega el elemento arrastrado al elemento soltado
    // ev.target.appendChild(document.getElementById(data));
    let celda = listado[fila][columna];
    let contenido = '';
    for (let i = 0; i < celda.length; i++) { //recorrer si hay más de un alimento en la celda
        let a = celda[i];
        if (a.dataset.nombre == 'huevo') {
            contenido += '1 huevo';
        } else if (ocupados[fila][columna] === 0.5) {
            contenido += a.dataset.gramos * 0.5 + 'g ' + a.dataset.nombre;
        }
        else {
            contenido += a.dataset.gramos + 'g ' + a.dataset.nombre;
        }

        if (i < celda.length - 1) {
            contenido += ', ';
        }

    }  

    if(celda.length > 0) {
        if (ocupados[fila][columna] == 0.5) {
            ocupados[fila][columna] = ocupados[fila][columna] - 0.5; //resto número de alimentos posibles a arrastrar
        }

        else {
            ocupados[fila][columna] -= 1;
        }
    }

    ev.target.textContent = contenido; //target hace referencia el elemento html 
    //se establece el contenido textual del elemento HTML en el que se ha desencadenado el evento en el valor de contenido
}

//Al hacer click en un alimento se borra
function eliminarAlimento(ev, id) {
    const fila = parseInt(id.substring(0, 1));
    const columna = parseInt(id.substring(1));

    ev.target.textContent = ''; //vaciar texto de la celda html

    listado[fila][columna] = []; //eliminar alimentos del listado
    ocupados[fila][columna] = unidadesIniciales[fila][columna]; //inicializar el nº de uds ocupadas

    celda = document.getElementById(id);

    //si elimina volver a poner el color en blanco si estaba en rojo por errores
    if(celda.style.backgroundColor != "rgb(255, 255, 255)"){
        celda.style.backgroundColor = "#FFFFFF";
        celda.style.color = "#88c0e5";
        celda.style.border = "";
    }
}

$(document).ready(function () {

    $('#correcto').hide();
    $('#incorrecto').hide();
    $('#falta').hide();

    // Filtros
    $('#filtrarAlimentos').on('keyup', function () {
        var buscado = $('#filtrarAlimentos').val().toLowerCase();
        var grupoSeleccionado = $('#grupo').val().toLowerCase();
        const alimentos = $('.elemento-intercambio');

        for (var i = 0; i < alimentos.length; i++) {
            var nombre = alimentos.eq(i).find('.nombre-alimento').text().toLowerCase();
            var grupo = alimentos.eq(i).find('.grupo').data('grupo').toLowerCase();

            if (buscado === '' && grupoSeleccionado === 'todos' || buscado === '' && grupoSeleccionado === grupo) {
                alimentos.eq(i).show();
            }

            else if (nombre.includes(buscado) && grupoSeleccionado === 'todos') {
                alimentos.eq(i).show();
            }

            else if (nombre.includes(buscado) && grupo === grupoSeleccionado) {
                alimentos.eq(i).show();
            }
            else {
                alimentos.eq(i).hide();
            }
        }
    });

    $('#grupo').on('change', function () { //cuando se cambia el grupo vuelve a hacer el keyup
        $('#filtrarAlimentos').trigger('keyup');
    });

    //Cada vez que se cambia el select de calorías se establecen el nº de uds para cada celda
    establecerUdsIntercambio();

    $('#calorias').on('change', function () { //cuando se cambia el grupo vuelve a hacer el keyup
        establecerUdsIntercambio();
    });

    //comprobar resultado
    $(".comprobar").click(function () {
        comprobarResultado();
    });

});