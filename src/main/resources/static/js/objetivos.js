$(document).ready(function () {

    var baseUrl = window.location.origin;

    const diasContainer = document.querySelector(".dias");
    const fechaActual = new Date();

    const diaActual = fechaActual.getDate();
    const mesActual = fechaActual.getMonth() + 1;
    const anioActual = fechaActual.getFullYear();
    let fechaUsada = new Date(anioActual + '-' + mesActual + '-01');
    let mesUsado = fechaUsada.getMonth() + 1;
    let anioUsado = fechaUsada.getFullYear();
    const numDiasMesActual = new Date(anioActual, mesActual, 0).getDate();
    const primerDiaMesActual = new Date(anioActual, mesActual - 1, 1).getDay();
    let primeraVez = true;

    let objetivos = [];

    function actualizarCalendario(modo = undefined) { // modo = undefined, sin cambios; modo = 1, atrás; modo = 2, adelante

        diasContainer.innerHTML = '';

        const numDiasMes = new Date(fechaUsada.getFullYear(), fechaUsada.getMonth() + 1, 0).getDate();
        const primerDiaMes = new Date(fechaUsada.getFullYear(), fechaUsada.getMonth(), 1).getDay();

        if (!primeraVez) {
            const fechaSeleccionada = new Date(fechaUsada).toISOString().slice(0, 10);
            let fechaArray = fechaSeleccionada.split('-');
            if (modo && modo === 1) {
                mesUsado = obtenerNumeroMes(fechaArray[1]) - 1;
            }
            if (modo && modo === 2) {
                mesUsado = obtenerNumeroMes(fechaArray[1]) + 1;
            }
            anioUsado = Number(fechaArray[0]);
        } else {
            mesUsado = mesUsado;
            anioUsado = anioUsado;
        }

        const url = baseUrl + '/objetivos/listaObjetivosPorMes/' + mesUsado + '/' + anioUsado;
        fetch(url)
            .then(response => response.json())
            .then(data => {
                objetivos = data;

                // calcular el número de días que debemos mover hacia atrás para que el primer día del mes sea el lunes
                let offset = primerDiaMes - 1;
                if (offset < 0) {
                    offset = 6;
                }

                // añadir los días vacíos al principio del mes
                for (let i = 0; i < offset; i++) {
                    const diaVacio = document.createElement("div");
                    diaVacio.classList.add("dia", "vacio");
                    diasContainer.appendChild(diaVacio);
                }

                // añadir los días del mes
                for (let i = 1; i <= numDiasMes; i++) {
                    const dia = document.createElement("div");
                    dia.classList.add("dia");
                    dia.innerText = i;
                    diasContainer.appendChild(dia);
                }

                const celdas = document.querySelectorAll('.calendario .dias .dia');

                for (let i = 0; i < celdas.length; i++) {
                    let clickable = false;
                    const dia = celdas[i].innerText;

                    // Agregaremos el color .objetivos si el día en cuestión tiene objetivos            
                    if (diaTieneObjetivos(dia, mesUsado, anioUsado)) {
                        celdas[i].classList.add('objetivo');
                    }

                    if (dia == diaActual && mesActual == mesUsado && anioActual == anioUsado) {
                        celdas[i].classList.add('today');
                        seleccionarObjetivosDelDia(dia, mesUsado, anioUsado);
                    } else {
                        celdas[i].classList.remove('today');
                    }

                    celdas[i].addEventListener('click', function () {
                        seleccionarObjetivosDelDia(dia, mesUsado, anioUsado);
                    });

                }

                document.querySelector('.fecha').innerText = obtenerNombreMes(fechaUsada.getMonth()) + ' ' + fechaUsada.getFullYear();

                renderDatos();
            });

        primeraVez = false;

    }

    function seleccionarObjetivosDelDia(dia, mes, anio) {
        let objetivosAux = [];
        let fecha = dateToString(dia, mes.toString(), anio.toString());
        fechaDate = new Date(fecha);
        objetivos.forEach(objetivo => {
            const fechaInicioDate = new Date(objetivo.fechaInicio);
            const fechaVencimientoDate = new Date(objetivo.fechaVencimiento);
            if (objetivo.repeticion === 'DIARIAMENTE') {
                if (fechaInicioDate <= fechaDate && fechaVencimientoDate >= fechaDate) {
                    objetivosAux.push(objetivo);
                }
            }
            if (objetivo.repeticion === 'SEMANALMENTE') {
                if (fechaDate.getDay() === fechaInicioDate.getDay() && fechaDate <= fechaVencimientoDate) {
                    objetivosAux.push(objetivo);
                }
            } 
            if (objetivo.repeticion === 'MENSUALMENTE') {
                if (fechaDate.getDate() === fechaInicioDate.getDate()) {
                    objetivosAux.push(objetivo);
                }
            }
            if (objetivo.repeticion === 'ANUALMENTE') {
                console.log(fechaDate.getDate(), fechaInicioDate.getDate());
                console.log(fechaDate.getMonth(), fechaInicioDate.getMonth());
                if ((fechaDate.getDate() === fechaInicioDate.getDate()) && (fechaDate.getMonth() === fechaInicioDate.getMonth())) {
                    objetivosAux.push(objetivo);
                }
            }
            if (objetivo.repeticion === 'NINGUNA') {
                if (fechaDate.getDate() === fechaVencimientoDate.getDate() && fechaDate.getMonth() === fechaVencimientoDate.getMonth() && fechaDate.getFullYear() === fechaVencimientoDate.getFullYear()) {
                    objetivosAux.push(objetivo);
                }
            }
        });

        // Renderizaremos los objetivos a la derecha
        const tablaResultado = document.getElementById('objetivos');
        let contenido = '<h3 class="text-center">' + dia + '/' + mes + '/' + anio + '</h3>';
        console.log(objetivosAux);
        if (objetivosAux.length === 0) {
            contenido += '<div class="d-flex justify-content-center align-items-center py-4"><div class="sin-datos"><h5>No tienes ningún objetivo marcado para este día</h5></div></div>';
        } else {
            contenido += '<ol>';
            for (i = 0; i < objetivosAux.length; i++) {
                contenido += '<li class="listaObj">' + objetivosAux[i].titulo + '</li>';
            };
            contenido += '</ol>';
        }
        tablaResultado.innerHTML = contenido;
    }

    function diaTieneObjetivos(dia, mes, anio) {

        let resultado = false;
        let fecha = dateToString(dia, mes.toString(), anio.toString());

        fechaDate = new Date(fecha);

        objetivos.forEach(objetivo => {
            const fechaInicioDate = new Date(objetivo.fechaInicio);
            const fechaVencimientoDate = new Date(objetivo.fechaVencimiento);
            if (objetivo.repeticion === 'DIARIAMENTE') {
                if (fechaInicioDate <= fechaDate && fechaVencimientoDate >= fechaDate) {
                    resultado = true;
                }
            }
            if (objetivo.repeticion === 'SEMANALMENTE') {
                if (fechaDate.getDay() === fechaInicioDate.getDay() && fechaDate <= fechaVencimientoDate) {
                    resultado = true;
                }
            }
            if (objetivo.repeticion === 'MENSUALMENTE') {
                if (fechaDate.getDate() === fechaInicioDate.getDate()) {
                    resultado = true;
                }
            }
            if (objetivo.repeticion === 'ANUALMENTE') {
                if (fechaDate.getDate() === fechaInicioDate.getDate() && fechaDate.getMonth() === fechaInicioDate.getMonth()) {
                    resultado = true;
                }
            }
            if (objetivo.repeticion === 'NINGUNA') {
                if (fechaDate.getDate() === fechaVencimientoDate.getDate() && fechaDate.getMonth() === fechaVencimientoDate.getMonth() && fechaDate.getFullYear() === fechaVencimientoDate.getFullYear()) {
                    resultado = true;
                }
            }
        });

        return resultado;
    }

    function dateToString(dia, mes, anio) {
        let anioFormateado = anio;
        let mesFormateado = mes.length < 2 ? '0' + mes : mes;
        let diaFormateado = dia.length < 2 ? '0' + dia : dia;
        resultado = anioFormateado + '-' + mesFormateado + '-' + diaFormateado;
        return resultado;
    }

    function obtenerNombreMes(mes) {
        const meses = [
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"
        ];
        return meses[mes];
    }

    function obtenerNumeroMes(mes) {
        const meses = [
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre"
        ];
        return meses.indexOf(mes) + 1;
    }

    function renderDatos() {

        const tablaObjetivos = document.getElementById('filtro');
        tablaObjetivos.innerHTML = '';

        if (objetivos.length > 0) {
            // Si hay datos, mostraremos el filtro
            let filtro;
            filtro = '<div class="mt-4"><div><form id="buscadorObj">';
            filtro += '<div class="d-flex justify-content-center align-items-center">';
            filtro += '<div class="me-4" for="estado">Estado:</div>';
            filtro += '<div><select class="form-control" name="estado" id="estadoObjetivo">';
            filtro += '<option value="TODOS">Todos</option><option value="PENDIENTE">Pendientes</option><option value="COMPLETADO">Completados</option>';
            filtro += '</select></div>';
            filtro += '</div></form></div></div>';
            tablaObjetivos.innerHTML = filtro;

            document.getElementById('estadoObjetivo').addEventListener('change', function () {
                const url = baseUrl + '/objetivos/listaObjetivosPorMes/' + mesUsado + '/' + anioUsado;
                fetch(url)
                    .then(response => response.json())
                    .then(data => {
                        objetivos = data.filter(objetivo => {
                            if (this.value === 'TODOS') {
                                return objetivo;
                            } else if (this.value === objetivo.estadoObjetivo) {
                                return objetivo;
                            }
                        });

                        console.log(objetivos);

                        renderTabla();

                    });
            });

            renderTabla();

        } else {
            document.getElementById('datos').innerHTML = '<div class="sin-datos"><h5>Aún no tienes objetivos para este mes</h5></div>';
        }

    }

    document.querySelector('.prev').addEventListener('click', () => {
        const timestamp = fechaActual.setMonth(fechaUsada.getMonth() - 1);
        changeMonth(timestamp);
    });

    document.querySelector('.next').addEventListener('click', () => {
        const timestamp = fechaActual.setMonth(fechaUsada.getMonth() + 1);
        changeMonth(timestamp);
    });

    function renderTabla() {

        if(objetivos.length > 0){
            let tabla = '';
            tabla += '<table class="table tableObj mt-4"><thead class="thead-dark"><tr>';
            tabla += '<th scope="col">Título</th>';
            tabla += '<th scope="col">Fecha inicio</th>';
            tabla += '<th scope="col">Fecha finalización</th>';
            tabla += '<th scope="col">Estado</th>';
            tabla += '<th scope="col">Repeticion</th>';
            tabla += '<th scope="col">Acciones</th>';
            tabla += '</tr></thead>';
            tabla += '<tbody class="buscarObjetivos">';

            for (let i = 0; i < objetivos.length; i++) {
                tabla += '<tr>';
                tabla += '<td>' + objetivos[i].titulo + '</td>';
                tabla += '<td>' + objetivos[i].fechaInicio + '</td>';
                tabla += '<td>' + objetivos[i].fechaVencimiento + '</td>';
                tabla += '<td>' + objetivos[i].estadoObjetivo + '</td>';
                tabla += '<td>' + objetivos[i].repeticion + '</td>';
                tabla += '<td>';
                tabla += '<a class="fa-solid fa-pen-to-square fa-1x ps-4 pe-2" style="color:rgba(127, 179, 120, 0.903)" id="btn-icono" data-bs-toggle="tooltip" title="Editar objetivo" href="/objetivos/editar/' + objetivos[i].id + '"></a>';
                tabla += '<a class="eliminarObjetivo" style="color:rgb(201, 101, 101)" id="btn-icono" data-id="' + objetivos[i].id + '" data-bs-toggle="tooltip" title="Eliminar objetivo"><i class="fa-solid fa-trash-can fa-1x  pe-2"></i></a>'
                tabla += '</td></tr>';
            }
            

            tabla += '</tbody></table>';
            document.getElementById('datos').innerHTML = tabla;    
        } else {
            document.getElementById('datos').innerHTML = '<div class="sin-datos"><h5>Aún no tienes objetivos para este mes</h5></div>';
        }
        
    }


    actualizarCalendario();

    $(document).on('click', '.eliminarObjetivo', function() {
        var id = $(this).data('id');
        console.log("hola");
        Swal.fire({
          position: 'center',
          title: '<h4>¿Estás seguro de eliminar el objetivo?</h4>',
          showConfirmButton: true,
          showCancelButton: true,
          cancelButtonText: 'Cancelar',
          confirmButtonColor: "rgb(218, 77, 73)",
          confirmButtonText: '<a href="/objetivos/eliminar/' + id + '" id ="conf">Eliminar</a>',
    
          didRender: function () {
            const confirm = document.querySelector('#conf');
    
            if (confirm) {
              confirm.style.color = 'white';
            }
          },
    
          showClass: {
            popup: 'animate__animated animate__fadeInDown'
          },
          hideClass: {
            popup: 'animate__animated animate__fadeOutUp'
          }
    
        })
    
    })



    /*PARA AÑADIR EL EVENTO AL CALENDARIO */
    /*
        // Lista de eventos
        let eventos = [];
    
        // Función para agregar un evento al calendario
        function agregarEvento(evento) {
            const fecha = evento.fecha.toISOString().slice(0, 10); // Convertir fecha a formato ISO
            const dia = document.querySelector(`[data-fecha="${fecha}"]`);
            if (dia) {
                const eventoHTML = `<div class="evento" data-id="${evento.id}">
                              <span class="titulo">${evento.titulo}</span>
                              <span class="fechaVencimiento">${evento.fechaVencimiento}</span>
                              <span class="descripcion">${evento.descripcion}</span>
                            </div>`;
                dia.innerHTML += eventoHTML;
            }
        }
    
    
        // Función para agregar un evento
        function agregarNuevoEvento(objetivo) {
            objetivo.preventDefault();
           /* const form = document.getElementById('form-evento');
            const titulo = form.titulo.value;
            const fecha = new Date(form.fechaVencimiento.value);
            const estado = form.hora.estado;
            const repeticion = form.repeticion.value;
            const titulo = objetivo.titulo;
            const fecha = objetivo.fechaVencimiento;
            const descripcion = objetivo.descripcion;
            const id = eventos.length + 1; // Generar un ID único para el evento
    
            const evento = { id, titulo, fecha, descripcion };
            eventos.push(evento);
            agregarEvento(evento);
        }
    
        // Asignar eventos a botones
        document.getElementById('agregar-evento').addEventListener('submit', agregarNuevoEvento);
    
    */


    /*NUEVO INTENTO */


    var calendar = document.getElementById('calendario');

    function agregarObjetivo() {

        //console.log("Agregar objetivo ejecutado");
        //System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // obtener los valores del formulario
        //var titulo = document.getElementById("titulo").value;
        //var descripcion = document.getElementById("descripcion").value;
        //var fechaVencimiento = document.getElementById("fechaVencimiento").value;


        // crear el objeto de evento
        /*var event = {
            title: titulo,
            descripcion: descripcion,
            start: fechaVencimiento
        };*/

        // agregar el evento al calendario
        //calendar.addEvent(event);
        //calendar.render();

        /*// agregar el evento al calendario (usando FullCalendar)
        $('#calendario').fullCalendar('renderEvent', {
          title: titulo,
          description: descripcion,
          start: fechaVencimiento
        });*/

        //document.getElementById("formNuevoObjetivo").submit();
    }

    function changeMonth(timestamp) {
        const fechaTemporal = new Date(timestamp);
        anioUsado = fechaTemporal.getFullYear();
        mesUsado = fechaTemporal.getMonth() + 1;
        fechaUsada = new Date(anioUsado + '-' + mesUsado + '-01');
        actualizarCalendario();
        console.log(1, mesUsado, anioUsado);
        seleccionarObjetivosDelDia(1, mesUsado, anioUsado);
    }


    agregarObjetivo();



    //Botones objetivos recomendados
    $(".agua").show();
    $(".ejercicio").hide();
    $(".descanso").hide();
    $(".testAnimico").hide();

    $("#agua").css("background-color", "rgba(105, 208, 236, 0.93)");

    $("#agua").click(function () {
        $(this).css("background-color", "rgba(105, 208, 236, 0.93)");
        $("#ejercicio").css("background-color", "rgb(175, 204, 185)");
        $("#descanso").css("background-color", "rgb(200, 175, 204)");
        $("#testAnimico").css("background-color", "rgb(198, 192, 131)");

        $(".agua").show();
        $(".ejercicio").hide();
        $(".descanso").hide();
        $(".testAnimico").hide();

    });

    $("#ejercicio").click(function () {
        $(this).css("background-color", "rgba(99, 188, 86, 0.807)");
        $("#agua").css("background-color", "rgb(145, 203, 210)");
        $("#descanso").css("background-color", "rgb(200, 175, 204)");
        $("#testAnimico").css("background-color", "rgb(198, 192, 131)");

        $(".agua").hide();
        $(".ejercicio").show();
        $(".descanso").hide();
        $(".testAnimico").hide();

    });

    $("#descanso").click(function () {
        $(this).css("background-color", "rgba(221, 125, 210, 0.996)");
        $("#agua").css("background-color", "rgb(145, 203, 210)");
        $("#ejercicio").css("background-color", "rgb(175, 204, 185)");
        $("#testAnimico").css("background-color", "rgb(198, 192, 131)");

        $(".agua").hide();
        $(".ejercicio").hide();
        $(".descanso").show();
        $(".testAnimico").hide();

    });

    $("#testAnimico").click(function () {
        $(this).css("background-color", "rgba(231, 208, 114, 0.915)");
        $("#agua").css("background-color", "rgb(145, 203, 210)");
        $("#ejercicio").css("background-color", "rgb(175, 204, 185)");
        $("#descanso").css("background-color", "rgb(200, 175, 204)");

        $(".agua").hide();
        $(".ejercicio").hide();
        $(".descanso").hide();
        $(".testAnimico").show();

    });


    /*function mostrarFormularioDescanso() {
        var formulario = document.getElementById("formularioDescanso");
        if (formulario.style.display === "none") {
            formulario.style.display = "block";
        } else {
            formulario.style.display = "none";
        }
    }
    
    document.getElementById("btnDescanso").addEventListener("click", function(){
        mostrarFormularioDescanso();
    });*/


});