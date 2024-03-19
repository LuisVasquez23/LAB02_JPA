

document.addEventListener("DOMContentLoaded" , ()=>{
    setTimeout(()=>{
        ShowHideLoader();
    } , 1500);
})

initDataTable("table" , { columnDefs: [ { width: 'auto' , target: 0 } ] });

function showAlert(message,title, type) {
    Swal.fire({
        title:title,
        text: message,
        icon: type,
        showCancelButton: false,
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'OK'
    });
}

