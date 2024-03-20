/**
 * ===========================================================
 *  VARIABLES AND CONST  
 * ===========================================================
 * */
const BASE_URL = "/LAB02_JPA/";
const GAME_URL = BASE_URL+"Reporte";

/**
 * ===========================================================
 *  ELEMENTS  
 * ===========================================================
 * */

const btnReporte = document.getElementById("generarReporte");

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
    
    initDataTable("#table-games" , {paging: true,pageLength: 50,columnDefs: [ { width: 'auto' , target: 0 } ] });
    
    $("#table-games").DataTable().clear().draw();

    juegos.forEach( (juego) =>{
        $("#table-games").DataTable()
        .row.add([
            juego.nomJuego,
            `$ ${juego.precio}`,
            juego.existencias,
            `<img src="${BASE_URL}/imagenes/${juego.imagen}" title="${juego.nomJuego}" style="width:30px" />`,
            juego.clasificacion,
            juego.idCategoria.categoria,
        ]).draw();
    });
    
};

const generarReporte = () =>{
    const tableElement = document.getElementById('juegos-container');
     const nombreArchivo = 'reporte_juegos.pdf'; 
    html2pdf().from(tableElement).save(nombreArchivo);
};

/**
 * ===========================================================
 *  EVENTS HANDLING 
 * ===========================================================
 * */
document.addEventListener("DOMContentLoaded" , ()=>{
    GetJuegos();
});

btnReporte.addEventListener("click" , generarReporte);