$(document).ready(function () {
    $('.analitica').click(function () {
        var id = $(this).data('id');

        Swal.fire({
            position: 'center',
            title: '<h4>¿Estás seguro de que desea solicitarle una analítica?</h4>',
            showConfirmButton: true,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonColor: "rgb(218, 77, 73)",
            confirmButtonText: '<a href="/analitica/' + id + '" id ="conf">Mandar</a>',

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
    
});
function myFunction() {
    if (confirm("Desea guardar los datos?")) {
        alert("Datos guardados exitosamente");
    } else {
        alert("Usted cancelo la acción para guardar");
    }
}

function abrirIframe(modalId) {
    const modal = new bootstrap.Modal(document.getElementById(modalId));
    modal.show();
}