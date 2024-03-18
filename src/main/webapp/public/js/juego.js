document.addEventListener("DOMContentLoaded" , ()=>{
    GetJuegos();
})


const GetJuegos = ()=>{
    fetch("/LAB02_JPA/Juego")
    .then(response => response.json())
    .then((categorias)=>{
        RenderTableData(categorias);
    })
}


const RenderTableData = (juegos)=>{
    
    $("#table").DataTable().clear().draw();

    juegos.forEach( (juego) =>{
        $("#table").DataTable()
        .row.add([
            `<input type="checkbox" name="item-${juego.idJuego}" value="${juego.idJuego}" />`,
            juego.nomJuego,
            `$ ${juego.precio}`,
            juego.existencias,
            juego.imagen,
            juego.claficacion,
            juego.idCategoria.categoria,
             `
                <div class="btn-group text-center">
                    <button class="btn btn-primary" onclick="UpdateCliente(${juego.nomJuego})">Actualizar</button>
                    <button class="btn btn-danger" onclick="DeleteCliente(${juego.nomJuego})">Eliminar</button>
                <div>
            `
        ]).draw();
    });
    
}

