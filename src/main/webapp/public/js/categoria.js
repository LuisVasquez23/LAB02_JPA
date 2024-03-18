const BASE_URL = "/LAB02_JPA/";

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
                    <button class="btn btn-danger" onclick="DeleteCategory(${categoria.idCategoria})">Eliminar</button>
                <div>
            `
        ]).draw();
    });
    
}

const DeleteCategory = (idCategory) => {
    
   Swal.fire({
      title: "¿Estás seguro?",
      text: "Una vez eliminado, no podrás recuperar este registro.",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminarlo!',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
       if (result.isConfirmed) {
            fetch(BASE_URL + `Categoria?id=${idCategory}` , {
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => response.json())
            .then( data =>{
        
                console.log(data.length);
        
                if(data.length != 0){
                 Swal.fire(
                    '¡Eliminado!',
                    'El registro ha sido eliminado.',
                    'success'
                  );
                    return;
                }
            
                Swal.fire({
                    title: '¡Error!',
                    text: '¡No se puede eliminar el cliente!',
                    icon: 'error',
                    confirmButtonText: 'Cerrar'
                });
            })
            .then(function(){
                GetCategorias();
            })
            .catch(error => {
                console.error('Error al realizar la solicitud:', error); // Manejar errores
            });;
       }
    });
    
}