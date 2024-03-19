<%@ include file="../../shared/_header.jspf" %>
    <div class="card">
        <div class="card-header">
          Categorias
        </div>
        <div class="card-body">
            
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-success mb-3" id="addModalBtn">
              Agregar categoria
            </button>
   
            <div class="table-responsive">
                <table class="table table-striped table-bordered" id="table">
                    <thead>
                      <tr>
                        <th></th>
                        <th>Categoria</th>
                        <th>Imagen</th>
                        <th>Acción</th>
                      </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            
            <button class="btn btn-danger" onclick="DeleteCategories()">Eliminar seleccionados</button>
            
        </div>
      </div>



    <!-- Modal -->
    <div class="modal fade" id="addModal" >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalLabel">Agregar categoria</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form action="/LAB02_JPA/Categoria" method="POST" enctype="multipart/form-data" class="row" id="categoriaForm">
                  
                   <!-- Accion -->
                  <div class="col-md-12 mb-2">
                      <input type="hidden" name="action" id="actionInsert" value="create" class="form-control" >
                  </div>
                  
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
    <div class="modal fade" id="updateModal" >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="updateModallabel">Actualizar categoria</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form action="/LAB02_JPA/Categoria" method="POST" enctype="multipart/form-data" class="row" id="categoriaFormUpdate">
                  
                  <!-- Accion -->
                  <div class="col-md-12 mb-2">
                      <input type="hidden" name="action" id="actionUpdate" value="update" class="form-control" >
                  </div>
                  
                  <!-- ID CATEGORIA -->
                  <div class="col-md-12 mb-2">
                      <input type="hidden" name="idCategoriaUpdate" id="idCategoriaUpdate" class="form-control" >
                  </div>
                  
                  <!-- IMAGEN CATEGORIA -->
                  <div class="col-md-12 mb-2">
                      <input type="hidden" name="pic_categoria_old" id="imagenCategoriaUpdate" class="form-control" >
                  </div>
                  
                  <!-- NOMBRE CATEGORIA -->
                  <div class="col-md-12 mb-2">
                      <label for="categoriaUpdate">Categoria: </label>
                      <input type="text" name="categoria" id="categoriaUpdate" class="form-control" placeholder="categoria" required="true">
                  </div>
                  
                  <!-- FOTO CATEGORIA -->
                  <div class="col-md-12 mb-2">
                      <label for="pic_categoriaUpdate">Imagen: </label>
                      <input type="file" name="pic_categoria" id="pic_categoria" class="form-control" placeholder="pic_categoria">
                  </div>
                  
                  <!-- SUBMIT BTN -->
                  <div class="col-md-12 mb-2">
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
    <script src="<%= Constantes.URL %>public/js/categoria.js" type="text/javascript"></script>

<%@ include file="../../shared/_footer_end.jspf" %>
