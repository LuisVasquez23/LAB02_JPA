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
    
    console.log(juegos);
   
    
}

