function fxJSeliminarProducto(id) {
    swal({
        title: "¿Está seguro de que desea eliminar los datos seleccionados?",
        text: "Una vez eliminados, no podrán ser restaurados",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((OK) => {
        if (OK) {
            $.ajax({
                url: "/producto/eliminar/REST/"+id,
                success:function (res) {
                    console.log(res);
                },
            });
            swal("Datos eliminados exitosamente", {
                icon: "success",
            }).then((ok) => {
                if (ok){
                    location.href = "/producto/listar/REST";
                }
            });
        } else {
            swal("Los datos no han sufrido cambios");
        }
    });
}