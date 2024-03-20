<%@ include file="../../shared/_header.jspf" %>
    <div class="card">
        <div class="card-header">
          Juegos
        </div>
        <div class="card-body">
            
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-success mb-3" id="btnAddModal">
              Agregar juego
            </button>
   
            <div class="table-responsive">
                <table class="table table-striped table-bordered" id="table">
                    <thead>
                      <tr>
                        <th></th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Existencias</th>
                        <th>Imagen</th>
                        <th>Calificación</th>
                        <th>Categoria</th>
                        <th>Acción</th>
                      </tr>
                    </thead>
                    <tbody>

                    </tbody>
                  </table>
            </div>
            
            <button class="btn btn-danger" id="deleteSelectedBtn">Eliminar seleccionados</button>
            
        </div>
      </div>



    <!-- Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="addModalLabel">Agregar juego</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              
              <form action="/LAB02_JPA/Juego" method="POST" enctype="multipart/form-data" class="row" id="juegoForm">
                    
                    <!-- ACTION -->
                    <div class="col-md-12 mb-2">
                        <input type="hidden" name="action" id="action" value="create" class="form-control" >
                    </div>

                    <!-- NOMBRE INPUT -->
                    <div class="col-md-12 mb-2">
                        <label for="gameName">Nombre del juego: </label>
                        <input type="text" name="gameName" id="gameName" class="form-control" placeholder="Nombre del juego" required="true">
                    </div>
                    
                    <!-- PRECIO INPUT -->
                    <div class="col-md-6 mb-2">
                        <label for="gamePrice">Precio: </label>
                        <input type="number" min="0" step="0.01" name="gamePrice" id="gamePrice" class="form-control" placeholder="Precio" required="true">
                    </div>
                    
                    <!-- EXISTENCIAS -->
                    <div class="col-md-6 mb-2">
                        <label for="existencia">Existencia: </label>
                        <input type="number" min="0" step="1" name="existencia" id="existencia" class="form-control" placeholder="Existencia" required="true">
                    </div>
                    
                    <!-- GAME PICTURE -->
                    <div class="col-md-12 mb-2">
                        <label for="gamePicture">Imagen: </label>
                        <input type="file" name="gamePicture" id="gamePicture" class="form-control" placeholder="Imagen" required="true">
                    </div>
                    
                    <!-- CATEGORIES -->
                    <div class="col-md-12 mb-2">
                        <label for="gameCategory">Categoria </label>
                        <select name="gameCategory" id="gameCategory" class="form-control" style="width: 100%;height: 38px !important;">
                        </select>
                    </div>
                    
                    <!-- CLASIFICACION -->
                    <div class="col-md-12 mb-2">
                        <label for="clasificacion">Clasificacion </label>
                        <select id="clasificacion" name="clasificacion" class="form-control" style="width: 100%;height: 38px !important;">
                        </select>
                    </div>
                    
                     <!-- SUBMIT BTN -->
                    <div class="col-md-12 mb-2 mt-2">
                        <input type="button" name="btnAdd" id="btnAdd" class="btn btn-primary w-100" value="Agregar" >
                    </div>
                    
                    
              </form>
              
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Update modal -->
    <div class="modal fade" id="updateModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="updateMoldaLabel">Actualizar juego</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form action="/LAB02_JPA/Juego" method="POST" enctype="multipart/form-data" class="row" id="juegoUpdateForm">
                    
                    <!-- ACTION -->
                    <div class="col-md-12 mb-2">
                        <input type="hidden" name="action" id="action" value="update" class="form-control" >
                    </div>
                    
                    <!-- ID JUEGO -->
                    <div class="col-md-12 mb-2">
                        <input type="hidden" name="gameId" id="gameId" class="form-control" >
                    </div>
                    
                    <!-- OLD_IMG -->
                    <div class="col-md-12 mb-2">
                        <input type="hidden" name="imgOld" id="imgOld" class="form-control" >
                    </div>

                    <!-- NOMBRE INPUT -->
                    <div class="col-md-12 mb-2">
                        <label for="gameName">Nombre del juego: </label>
                        <input type="text" name="gameNameUpdate" id="gameNameUpdate" class="form-control" placeholder="Nombre del juego" required="true">
                    </div>
                    
                    <!-- PRECIO INPUT -->
                    <div class="col-md-6 mb-2">
                        <label for="gamePrice">Precio: </label>
                        <input type="number" min="0" step="0.01" name="gamePriceUpdate" id="gamePriceUpdate" class="form-control" placeholder="Precio" required="true">
                    </div>
                    
                    <!-- EXISTENCIAS -->
                    <div class="col-md-6 mb-2">
                        <label for="existencia">Existencia: </label>
                        <input type="number" min="0" step="1" name="existenciaUpdate" id="existenciaUpdate" class="form-control" placeholder="Existencia" required="true">
                    </div>
                    
                    <!-- GAME PICTURE -->
                    <div class="col-md-12 mb-2">
                        <label for="gamePicture">Imagen: </label>
                        <input type="file" name="gamePictureUpdate" id="gamePictureUpdate" class="form-control" placeholder="Imagen" required="true">
                    </div>
                    
                    <!-- CATEGORIES -->
                    <div class="col-md-12 mb-2">
                        <label for="gameCategory">Categoria </label>
                        <select name="gameCategoryUpdate" id="gameCategoryUpdate" class="form-control" style="width: 100%;height: 38px !important;">
                        </select>
                    </div>
                    
                    <!-- CLASIFICACION -->
                    <div class="col-md-12 mb-2">
                        <label for="clasificacion">Clasificacion </label>
                        <select id="clasificacionUpdate" name="clasificacionUpdate" class="form-control" style="width: 100%;height: 38px !important;">
                        </select>
                    </div>
                    
                     <!-- SUBMIT BTN -->
                    <div class="col-md-12 mb-2 mt-2">
                        <input type="button" name="btnUpdate" id="btnUpdate" class="btn btn-primary w-100" value="Actualizar" >
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

    <!-- JS -->
    <script src="<%= Constantes.URL %>public/js/juego.js" type="text/javascript"></script>

<%@ include file="../../shared/_footer_end.jspf" %>
