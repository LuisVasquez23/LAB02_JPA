<%@ include file="../../shared/_header.jspf" %>

    <div class="card">
        <div class="card-header">
          Juegos
        </div>
        <div class="card-body">
            
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#exampleModal">
              Agregar juego
            </button>
   
            <table class="table table-striped table-bordered" id="table">
               <thead>
                 <tr>
                   <th></th>
                   <th>Categoria</th>
                   <th>Imagen</th>
                   <th>Acci�n</th>
                 </tr>
               </thead>
               <tbody>
                 <tr>
                   <td>Columna 1</td>
                   <td>Columna 2</td>
                   <td>Columna 3</td>
                   <td>Columna 3</td>
                 </tr>
                 <tr>
                   <td>Columna 5</td>
                   <td>Columna 4</td>
                   <td>Columna 6</td>
                   <td>Columna 3</td>
                 </tr>
               </tbody>
             </table>
            
            
            
        </div>
      </div>



    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalLabel">Agregar juego</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form method="post" action="" enctype="multipart/form-data" class="row">
                  
                  <!-- NOMBRE CATEGORIA -->
                  <div class="col-md-12 mb-2">
                      <label for="categoria">Categoria: </label>
                      <input type="text" name="categoria" class="form-control" placeholder="categoria" required="true">
                  </div>
                  
                  <!-- FOTO CATEGORIA -->
                  <div class="col-md-12 mb-2">
                      <label for="pic_categoria">Imagen: </label>
                      <input type="file" name="pic_categoria" class="form-control" placeholder="pic_categoria" required="true">
                  </div>
                  
                  <!-- SUBMIT BTN -->
                  <div class="col-md-12 mb-2">
                      <input type="submit" name="btnAdd" class="btn btn-primary w-100" value="Agregar" >
                  </div>
              </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
          </div>
        </div>
      </div>
    </div>
    
   
 
<%@ include file="../../shared/_footer.jspf" %>
