package controllers;

import helpers.General;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.Categoria;
import models.Juego;
import service.CategoriaService;
import service.JuegoService;
import service.exceptions.NonexistentEntityException;


@WebServlet(name = "JuegoController", urlPatterns = {"/Juego"})
@MultipartConfig
public class JuegoController extends HttpServlet {
    
    
    private final JuegoService _service = new JuegoService();
    private final CategoriaService _categoriaService = new CategoriaService();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Juego> juegos = _service.findJuegoEntities();
        
        General.sendAsJson(response, General.ObjectToJson(juegos));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            // OBTENER LOS PARAMETROS 
            String action = request.getParameter("action");
            
            if(action.equals("create")){
                createGame(request , response);
            }
            
           
        } catch (Exception e) {
            General.sendAsJson(response, "[{\"status\":\""+e.getMessage()+"\"}]");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            // Get the game id 
            int gameId = Integer.parseInt(request.getParameter("gameId"));
            
            // Obtener el juego para eliminar la imagen
            Juego juego = _service.findJuego(gameId);
            String uploadedDirectory = getServletContext().getRealPath("/"); // Obtiene la ruta del contexto del proyecto
            uploadedDirectory = uploadedDirectory + "imagenes\\";
            String uploadedFilePath = uploadedDirectory + juego.getImagen();
            File uploadedFile = new File(uploadedFilePath);

            // Si el archivo ya existe, eliminarlo
            if (uploadedFile.exists()) {
                uploadedFile.delete();
            }
            // Delete game
            _service.destroy(gameId);
            
            General.sendAsJson(response, "[{\"status\":\"Done!\"}]");
            
        } catch (IOException | NonexistentEntityException e) {
            General.sendAsJson(response, "[{\"status\":\""+e.getMessage()+"\"}]");
        }
    }
    
    private void createGame(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            String mainPath = getServletContext().getRealPath("/");
            
            // OBTENER VALORES
            String nombre = request.getParameter("gameName");
            Float precio = Float.valueOf(request.getParameter("gamePrice"));
            int existencia = Integer.parseInt(request.getParameter("existencia"));
            int idCategoria = Integer.parseInt(request.getParameter("gameCategory"));
            String clasificacion = request.getParameter("clasificacion");
            
            // OBTENER LA CATEGORIA ASIGNADA
            Categoria categoria = _categoriaService.findCategoria(idCategoria);
            
            // CONFUGURACION DE LA IMAGEN 
            Part filePart = request.getPart("gamePicture");
            InputStream fileContent = filePart.getInputStream();
            
            // Obtener el nombre original del archivo
            String originalFileName = filePart.getSubmittedFileName();
            
            // Obtener la extensión del archivo
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            
            
            String uploadedDirectory = mainPath + "imagenes";

            // Verificar si el directorio existe, si no, crearlo
            File directory = new File(uploadedDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
        
            String fileName = nombre.replace(" ", "_") + fileExtension;
            String uploadedFilePath = uploadedDirectory + File.separator + fileName;
            
         
            // CREACION DEL OBJETO A INSERTAR 
            Juego juegoAdd = new Juego();
            juegoAdd.setNomJuego(nombre);
            juegoAdd.setPrecio(precio);
            juegoAdd.setExistencias(existencia);
            juegoAdd.setIdCategoria(categoria);
            juegoAdd.setClasificacion(clasificacion);
            juegoAdd.setImagen(fileName);
            
            _service.create(juegoAdd);
            
            deleteImg(uploadedFilePath);
            
            // Guardar la nueva imagen en el servidor
            Files.copy(fileContent, Paths.get(uploadedFilePath));
           
            
            General.sendAsJson(response, General.ObjectToJson(juegoAdd));
            
        } catch (Exception e) {
            General.sendAsJson(response, "[{\"status\":\""+e.getMessage()+"\"}]");
        }
    }
    
    private void deleteImg(String uploadedFilePath){
        File uploadedFile = new File(uploadedFilePath);

        // Si el archivo ya existe, eliminarlo
        if (uploadedFile.exists()) {
            uploadedFile.delete();
        }
    }
}
