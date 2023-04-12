$('#eliminar').click(function () {
    var id = $(this).data('id');

    Swal.fire({
        position: 'center',
        title: '<h4>¿Estás seguro de eliminar el alimento consumido?</h4>',
        showConfirmButton: true,
        showCancelButton: true,
        cancelButtonText: 'Cancelar',
        confirmButtonColor: "rgb(218, 77, 73)",
        confirmButtonText: '<a href="/alimento/eliminar/' + id + '" id ="conf">Eliminar</a>',

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

});

$(document).ready(function () {
    // Obtener la lista de opciones del select
    var opciones = $("#lista-alimentos option");

    // Crear un input de búsqueda
    var inputBusqueda = $('<input type="text" class="form-control mt-2" placeholder="Buscar...">');

    // Agregar el input de búsqueda antes del select
    $("#lista-alimentos").before(inputBusqueda);

    // Escuchar el evento keyup del input de búsqueda
    inputBusqueda.keyup(function () {
        // Obtener el término de búsqueda
        var term = $(this).val().toLowerCase();

        // Filtrar las opciones que contengan el término de búsqueda
        var opcionesFiltradas = opciones.filter(function () {
            return $(this).text().toLowerCase().indexOf(term) > -1;
        });

        // Actualizar las opciones del select con las opciones filtradas
        $("#lista-alimentos").empty().append(opcionesFiltradas);
    });

    // Restaurar todas las opciones del select cuando se cierre
    $("#lista-alimentos").on("hidden.bs.select", function () {
        $("#lista-alimentos").empty().append(opciones);
    });

    $("#lista-alimentos").keydown("hidden.bs.select", function () {
        $("#lista-alimentos").empty().append(opciones);
    });
});