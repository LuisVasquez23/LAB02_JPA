<%@ include file="../../shared/_header.jspf" %>
    <div class="card">
        <div class="card-header">
          Categorias
        </div>
        <div class="card-body">
            
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#exampleModal">
              Agregar categoria
            </button>
   
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
