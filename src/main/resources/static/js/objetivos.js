document.addEventListener("DOMContentLoaded", function () {
    const diasContainer = document.querySelector(".dias");
    const fechaActual = new Date();

    let diaActual = fechaActual.getDate();
    let mesActual = fechaActual.getMonth() + 1;
    const anioActual = fechaActual.getFullYear();
    const numDiasMesActual = new Date(anioActual, mesActual, 0).getDate();
    const primerDiaMesActual = new Date(anioActual, mesActual - 1, 1).getDay();

    function actualizarCalendario() {
        diasContainer.innerHTML = '';
        const numDiasMes = new Date(fechaActual.getFullYear(), fechaActual.getMonth() + 1, 0).getDate();
        const primerDiaMes = new Date(fechaActual.getFullYear(), fechaActual.getMonth(), 1).getDay();

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
            const dia = celdas[i].innerText;

            if (dia == diaActual && fechaActual.getMonth() == mesActual - 1 && fechaActual.getFullYear() == anioActual) {
                celdas[i].classList.add('today');
            } else {
                celdas[i].classList.remove('today');
            }
        }   
        document.querySelector('.fecha').innerText = obtenerNombreMes(fechaActual.getMonth()) + ' ' + fechaActual.getFullYear();

          // Agregar eventos al calendario
          eventos.forEach(evento => agregarEvento(evento));
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

    document.querySelector('.prev').addEventListener('click', () => {
        fechaActual.setMonth(fechaActual.getMonth() - 1);
        actualizarCalendario();
    });

    document.querySelector('.next').addEventListener('click', () => {
        fechaActual.setMonth(fechaActual.getMonth() + 1);
        actualizarCalendario();
    });

    actualizarCalendario();



    /*PARA AÑADIR EL EVENTO AL CALENDARIO */

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
        const repeticion = form.repeticion.value;*/
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



});