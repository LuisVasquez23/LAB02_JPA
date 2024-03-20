/* global Swal */

/**
 * ===========================================================
 *  VARIABLES AND CONST DECLARATION 
 * ===========================================================
 * */
const BASE_URL = "/LAB02_JPA/";
const DELETE_URL = BASE_URL+"Juego";

/**
 * ===========================================================
 *  ELEMENTS DECLARATION 
 * ===========================================================
 * */
const btnAdd = document.getElementById("btnAdd");
const deleteSelectedBtn = document.getElementById("deleteSelectedBtn");


/**
 * ===========================================================
 *  FUNTIONS DECLARATIONS 
 * ===========================================================
 * */

const GetJuegos = ()=>{
    fetch("/LAB02_JPA/Juego")
    .then(response => response.json())
    .then((categorias)=>{
        RenderTableData(categorias);
    });
};

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
            juego.clasificacion,
            juego.idCategoria.categoria,
             `
                <div class="btn-group text-center">
                    <button class="btn btn-primary" onclick="showUpdateModal()">Actualizar</button>
                    <button class="btn btn-danger" onclick="deleteGame(${juego.idJuego})">Eliminar</button>
                <div>
            `
        ]).draw();
    });
    
};

const showUpdateModal = () =>{
    showModal("#updateModal");
}

const GetSelectedCheckboxes = () => {
    const selectedCheckboxes = [];
    $('#table input[type="checkbox"]:checked').each(function() {
        selectedCheckboxes.push($(this).val());
    });
    return selectedCheckboxes;
}

const deleteSelectedGames = () =>{
    
    // Validar que existan registros seleccionados
    const selectedIds = GetSelectedCheckboxes();
    if (selectedIds.length === 0) {
        showAlert('Cuidado!' , 'Tienes que seleccionar un registro' , 'warning'); 
        return;
    }
    
    
    
};

const deleteGame = (gameId)=>{
    
    Swal.fire({
      title: "Estas seguro?",
      text: "Una vez eliminado, no podras recuperar este registro.",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminarlo!',
      cancelButtonText: 'Cancelar'
    })
    .then((result)=>{
         if (result.isConfirmed) {
            fetch(DELETE_URL + `?gameId=${gameId}` , {
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => response.json())
            .then( data =>{

                if(data.length !== 0){
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
                GetJuegos();
            })
            .catch(error => {
                console.error('Error al realizar la solicitud:', error); // Manejar errores
            });
        }
    });
    
};

/**
 * ===========================================================
 *  EVENTS HANDLING 
 * ===========================================================
 * */
document.addEventListener("DOMContentLoaded" , ()=>{
    GetJuegos();
});


btnAdd.addEventListener("click" , ()=>{
    showModal("#addModal");
});

deleteSelectedBtn.addEventListener("click" , ()=>{
    deleteSelectedGames();
});