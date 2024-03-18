document.addEventListener("DOMContentLoaded" , ()=>{
    GetCategorias();
})


const GetCategorias = ()=>{
    fetch("/LAB02_JPA/Categoria")
    .then(response => response.json())
    .then((categorias)=>{
        RenderTableData(categorias);
    })
}

const RenderTableData = (categorias)=>{
    
    $("#table").DataTable().clear().draw();

    categorias.forEach( (categoria) =>{
        $("#table").DataTable()
        .row.add([
            `<input type="checkbox" name="item-${categoria.idCategoria}" value="${categoria.idCategoria}" />`,
            categoria.categoria,
            categoria.imagenCat,
             `
                <div class="btn-group text-center">
                    <button class="btn btn-primary" onclick="UpdateCliente(${categoria.idCategoria})">Actualizar</button>
                    <button class="btn btn-danger" onclick="DeleteCliente(${categoria.idCategoria})">Eliminar</button>
                <div>
            `
        ]).draw();
    });
    
}