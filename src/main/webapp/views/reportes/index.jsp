<%@ include file="../../shared/_header.jspf" %>

<div class="container">
    <div class="table-responsive p-2" id="juegos-container">
            <h1 class="mb-3">Juegos del más caro al más barato</h1>
            <table class="table table-striped table-bordered" id="table-games">
                <thead>
                  <tr>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Existencias</th>
                    <th>Imagen</th>
                    <th>Clasificación</th>
                    <th>Categoria</th>
                  </tr>
                </thead>
                <tbody>

                </tbody>
              </table>
        </div>

        <button type="button" class="btn btn-primary" id="generarReporte">Generar reporte</button>
</div>


<%@ include file="../../shared/_footer.jspf" %>

    <!-- JS -->
    <script src="<%= Constantes.URL %>public/js/reporte.js" type="text/javascript"></script>

<%@ include file="../../shared/_footer_end.jspf" %>
