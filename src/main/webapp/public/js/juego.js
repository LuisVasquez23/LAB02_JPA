/* global Swal */

/**
 * ===========================================================
 *  VARIABLES AND CONST  
 * ===========================================================
 * */
const BASE_URL = "/LAB02_JPA/";
const GAME_URL = BASE_URL+"Juego";
const CATEGORIES_URL = BASE_URL+"Categoria";

/**
 * ===========================================================
 *  ELEMENTS  
 * ===========================================================
 * */
const btnAddModal = document.getElementById("btnAddModal");
const btnAdd = document.getElementById("btnAdd"); 
const deleteSelectedBtn = document.getElementById("deleteSelectedBtn");
const btnUpdate = document.getElementById("btnUpdate");

/**
 * ===========================================================
 *  FUNTIONS  
 * ===========================================================
 * */

const GetJuegos = ()=>{
    fetch(GAME_URL)
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
            `<img src="${BASE_URL}/imagenes/${juego.imagen}" title="${juego.nomJuego}" style="width:30px" />`,
            juego.clasificacion,
            juego.idCategoria.categoria,
             `
                <div class="btn-group text-center">
                    <button class="btn btn-primary" onclick="showUpdateModal(${juego.idJuego}, '${juego.imagen}' , '${juego.nomJuego}' , ${juego.precio} , ${juego.existencias} , ${juego.idCategoria.idCategoria} , '${juego.clasificacion}')">Actualizar</button>
                    <button class="btn btn-danger" onclick="deleteGame(${juego.idJuego})">Eliminar</button>
                <div>
            `
        ]).draw();
    });
    
};

const showUpdateModal = (gameId , imgOld , gameName , gamePrice , gameExistencias , gameCategory , clasificacion) =>{
    
 
    $("#gameId").val(gameId);
    $("#imgOld").val(imgOld);
    $("#gameNameUpdate").val(gameName);
    $("#gamePriceUpdate").val(gamePrice);
    $("#existenciaUpdate").val(gameExistencias);
    
    getCategories(gameCategory ,"#gameCategoryUpdate","#updateModal");
    getClasificacion(clasificacion,"#clasificacionUpdate","#updateModal");
    
    showModal("#updateModal");
};

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
                return fetch(GAME_URL + `?gameId=${id}`, {
                    method: "DELETE",
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then(response => response.json());
            }))
            .then(results => {
                const success = results.every(data => data.length !== 0);
                if (success) {
                    showAlert(
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
                GetJuegos()();
            })
            .catch(error => {
                console.error('Error al realizar la solicitud:', error); // Manejar errores
            });
        }
    });
    
    
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
            fetch(GAME_URL + `?gameId=${gameId}` , {
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => response.json())
            .then( data =>{

                if(data.length !== 0){
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
                GetJuegos();
            })
            .catch(error => {
                console.error('Error al realizar la solicitud:', error); // Manejar errores
            });
        }
    });
    
};

const getCategories = (id ,selectNode = "#gameCategory", parentModal = "#addModal") =>{

    id = id ?? "";
    
     $.ajax({
        url: CATEGORIES_URL,
        dataType: 'json',
        success: function(data) {
            
            // Formatea los datos en el formato requerido por select2
            let formattedData = data.map(function(item) {
                return {
                    id: item.idCategoria,
                    text: item.categoria
                };
            });
            
            formattedData.push({id: "" , text: "Seleccionar una categoria"});
            
            // Inicializa select2 y pasa los datos formateados
            $(selectNode).select2({
                dropdownParent: $(parentModal),
                data: formattedData // Pasa los datos ya formateados
            });

            // Establece el valor predeterminado seleccionado
            $(selectNode).val(id).trigger('change');

        }
    });
};

const getClasificacion = (value ,selectNode = "#clasificacion", parentModal = "#addModal") =>{
    
    value = value ?? "";
    
    let data = [
        {id: "EC" , text: "EARLY CHILDHOOD"},
        {id: "E"  , text: "EVERYONE"},
        {id: "E10" , text: "EVERYONE 10+"},
        {id: "T" , text: "TEEN"},
        {id: "M" , text: "MATURE 17+"},
        {id: "AO" , text: "ADULTS ONLY 18+"},
        {id: "RP" , text: "RATING PENDING"},
        {id: "" , text: "Seleccionar clasificacion"}
    ];  
    
    // Inicializa select2 y pasa los datos formateados
    $(selectNode).select2({
        dropdownParent: $(parentModal),
        data: data // Pasa los datos ya formateados
    });

    // Establece el valor predeterminado seleccionado
    $(selectNode).val(value).trigger('change');
    
};

const updateGame = ()=>{
    
    let formData = new FormData(document.getElementById("juegoUpdateForm"));
    
    fetch(GAME_URL, {
        method: "POST",
        body: formData
    })
    .then(response => {


        if (!response.ok) {
            throw new Error("Error al enviar la solicitud.");
            hideModal('#updateModal');
        }
        
        return response.json();
    })
    .then(data => {
        
        console.log(data);
        
        showAlert("Actualizado correctamente" , "Actualizado" , "success");
        
        GetJuegos();
        
        hideModal('#updateModal');
        
        cleanUpdateInputs();

    })
    .catch(error => {
        console.error(error);
        hideModal('#updateModal');

    });
   
};

const cleanUpdateInputs = () => {
   
    $("#gameId").val("");
    $("#imgOld").val("");
    $("#gameNameUpdate").val("");
    $("#gamePriceUpdate").val("");
    $("#existenciaUpdate").val("");
    
    getCategories("" ,"#gameCategoryUpdate","#updateModal");
    getClasificacion("","#clasificacionUpdate","#updateModal");
    
};

const cleanCreateInputs = () => {
   
    $("#gameName").val("");
    $("#gamePrice").val("");
    $("#existencia").val("");
    $("#gamePicture").val("");
    getCategories("" ,"#gameCategory","#addModal");
    getClasificacion("","#clasificacion","#addModal");
    
};

/**
 * ===========================================================
 *  EVENTS HANDLING 
 * ===========================================================
 * */
document.addEventListener("DOMContentLoaded" , ()=>{
    GetJuegos();
});

btnAddModal.addEventListener("click" , ()=>{
    getCategories();
    getClasificacion();
    showModal("#addModal");
});

deleteSelectedBtn.addEventListener("click" , ()=>{
    deleteSelectedGames();
});

btnAdd.addEventListener("click" , ()=>{
    let formData = new FormData(document.getElementById("juegoForm"));
    
    fetch(GAME_URL, {
        method: "POST",
        body: formData
    })
    .then(response => {
        
        if (!response.ok) {
            throw new Error("Error al enviar la solicitud.");
             hideModal('#addModal');
        }
        
        return response.json();
    })
    .then((data)=>{
        console.log(data);
        showAlert("Agregado" , "Agregado correctamente" , "success");
        GetJuegos();
        hideModal('#addModal');
        cleanCreateInputs();
    })
    .catch(error => {
        console.error(error);
         hideModal('#addModal');
    });
});

btnUpdate.addEventListener("click" , updateGame);