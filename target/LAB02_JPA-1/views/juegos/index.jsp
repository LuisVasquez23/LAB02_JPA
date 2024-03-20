<%@ include file="../../shared/_header.jspf" %>
    <div class="card">
        <div class="card-header">
          Juegos
        </div>
        <div class="card-body">
            
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-success mb-3" id="btnAdd">
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
