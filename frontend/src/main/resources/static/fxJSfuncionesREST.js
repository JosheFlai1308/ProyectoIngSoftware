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

function fxJSeliminarProd_Prov(id) {
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
                url: "/prod_prov/eliminar/REST/"+id,
                success:function (res) {
                    console.log(res);
                },
            });
            swal("Datos eliminados exitosamente", {
                icon: "success",
            }).then((ok) => {
                if (ok){
                    location.href = "/prod_prov/listar/REST";
                }
            });
        } else {
            swal("Los datos no han sufrido cambios");
        }
    });
}

function fxJSeliminarProveedor(id) {
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
                url: "/proveedor/eliminar/REST/"+id,
                success:function (res) {
                    console.log(res);
                },
            });
            swal("Datos eliminados exitosamente", {
                icon: "success",
            }).then((ok) => {
                if (ok){
                    location.href = "/proveedor/listar/REST";
                }
            });
        } else {
            swal("Los datos no han sufrido cambios");
        }
    });
}

function fxJSEliminarEncargado(id) {
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
                url: "/encargado/eliminar/REST/" + id,
                success: function (res) {
                    console.log(res);
                },
            });
            swal("Datos eliminados exitosamente", {
                icon: "success",
            }).then((ok) => {
                if (ok) {
                    location.href = "/encargado/listar/REST";
                }
            });
        } else {
            swal("Los datos no han sufrido cambios");
        }
    });
}

function fxJSEliminarGuia_Despacho(id) {
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
                url: "/guia_despacho/eliminar/REST/" + id,
                success: function (res) {
                    console.log(res);
                },
            });
            swal("Datos eliminados exitosamente", {
                icon: "success",
            }).then((ok) => {
                if (ok) {
                    location.href = "/guia_despacho/listar/REST";
                }
            });
        } else {
            swal("Los datos no han sufrido cambios");
        }
    });
}

function fxJSEliminarPedido(id) {
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
                url: "/pedido/eliminar/REST/" + id,
                success: function (res) {
                    console.log(res);
                },
            });
            swal("Datos eliminados exitosamente", {
                icon: "success",
            }).then((ok) => {
                if (ok) {
                    location.href = "/pedido/listar/REST";
                }
            });
        } else {
            swal("Los datos no han sufrido cambios");
        }
    });
}
