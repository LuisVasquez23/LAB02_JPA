const BASE_URL = "/LAB02_JPA/";
const POST_URL = BASE_URL+"Categoria";
const UPDATE_URL = BASE_URL+"Categoria";
const btnAdd = document.getElementById("btnAdd");
const btnUpdate = document.getElementById("btnUpdate");
const addModalBtn = document.getElementById("addModalBtn");

document.addEventListener("DOMContentLoaded" , ()=>{
    GetCategorias();
})

btnAdd.addEventListener("click" , ()=>{
    
    let formData = new FormData(document.getElementById("categoriaForm"));
    
    // Enviar los datos del formulario al servidor usando Fetch
    fetch(POST_URL, {
        method: "POST",
        body: formData
    })
    .then(response => {

        
        if (!response.ok) {
            throw new Error("Error al enviar la solicitud.");
             hideModal('#addModal');
        }
        
        showAlert("Agregado correctamente" , "Agregado" , "success");
        
        GetCategorias();
        
        hideModal('#addModal');
        
        cleanInputs();
        
    })
    .catch(error => {
        console.error(error);
         hideModal('#addModal');
    });
    
});

btnUpdate.addEventListener("click" , ()=>{
   
    let formData = new FormData(document.getElementById("categoriaFormUpdate"));
    
   
    fetch(UPDATE_URL, {
        method: "POST",
        body: formData
    })
    .then(response => {

        GetCategorias();

        if (!response.ok) {
            throw new Error("Error al enviar la solicitud.");
            hideModal('#updateModal');
        }
        
        
        return response.json();
    })
    .then(data => {
        
        showAlert("Actualizado correctamente" , "Actualizado " , "success");
        
        GetCategorias();
        
        hideModal('#updateModal');
        
        cleanInputs();
        

    })
    .catch(error => {
        console.error(error);
        hideModal('#updateModal');

    });
   
});

addModalBtn.addEventListener("click" , ()=>{
    $("#addModal").modal('show');
});

const GetCategorias = ()=>{
    fetch("/LAB02_JPA/Categoria")
    .then(response => response.json())
    .then((categorias)=>{
        RenderTableData(categorias);
    });
};

const RenderTableData = (categorias)=>{
    
    $("#table").DataTable().clear().draw();

    categorias.forEach( (categoria) =>{
        $("#table").DataTable()
        .row.add([
            `<input type="checkbox" name="item-${categoria.idCategoria}" value="${categoria.idCategoria}" />`,
            categoria.categoria,
            `<img src="${BASE_URL}/imagenes/${categoria.imagenCat}" title="${categoria.categoria}" style="width:30px" />`,
             `
                <div class="btn-group text-center">
                    <button class="btn btn-primary" onclick="UpdateCategory(${categoria.idCategoria} , '${categoria.categoria}' , '${categoria.imagenCat}')">Actualizar</button>
                    <button class="btn btn-danger" onclick="DeleteCategory(${categoria.idCategoria})">Eliminar</button>
                <div>
            `
        ]).draw();
    });
    
};

const DeleteCategory = (idCategory) => {
    
   Swal.fire({
      title: "Estas seguro?",
      text: "Una vez eliminado, no podra recuperar este registro.",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminarlo!',
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
        
                if(data.length != 0){
                 Swal.fire(
                    'Eliminado!',
                    'El registro ha sido eliminado.',
                    'success'
                  );
                    return;
                }
            
                Swal.fire({
                    title: 'Error!',
                    text: 'No se puede eliminar el registro!',
                    icon: 'error',
                    confirmButtonText: 'Cerrar'
                });
            })
            .then(function(){
                GetCategorias();
            })
            .catch(error => {
                console.error('Error al realizar la solicitud:', error); // Manejar errores
            });
            
            GetCategorias();
       }
    });
    
}

const DeleteCategories = () => {
    
    const selectedIds = GetSelectedCheckboxes();
    if (selectedIds.length === 0) {
            Swal.fire(
                'Cuidado!',
                'Tienes que seleccionar un registro',
                'warning'
            );
        return;
    }
    
    Swal.fire({
        title: "Estas seguro?",
        text: "Una vez eliminados, no podras recuperar estos registros.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, eliminarlos!',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            Promise.all(selectedIds.map(id => {
                return fetch(BASE_URL + `Categoria?id=${id}`, {
                    method: "DELETE",
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then(response => response.json());
            }))
            .then(results => {
                const success = results.every(data => data.length !== 0);
                if (success) {
                    Swal.fire(
                        'Eliminados!',
                        'Los registros han sido eliminados.',
                        'success'
                    );
                } else {
                    Swal.fire({
                        title: 'Error!',
                        text: 'Algunos registros no pudieron ser eliminados!',
                        icon: 'error',
                        confirmButtonText: 'Cerrar'
                    });
                }
            })
            .then(function(){
                GetCategorias();
            })
            .catch(error => {
                console.error('Error al realizar la solicitud:', error); // Manejar errores
            });
        }
    });
    
}

const GetSelectedCheckboxes = () => {
    const selectedCheckboxes = [];
    $('#table input[type="checkbox"]:checked').each(function() {
        selectedCheckboxes.push($(this).val());
    });
    return selectedCheckboxes;
}

const cleanInputs = () => {
    // Limpiar el campo de texto de categoría
    $("input[name='categoria']").val("");
    // Limpiar el campo de archivo de imagen
    $("input[name='pic_categoria']").val("");
}

const UpdateCategory = (idCategory , nameCategory , imagenName) =>{
    
    // Asignar valores
    $("#idCategoriaUpdate").val(idCategory);
    $("#categoriaUpdate").val(nameCategory);
    $("#imagenCategoriaUpdate").val(imagenName);
    
    $("#updateModal").modal('show');
};