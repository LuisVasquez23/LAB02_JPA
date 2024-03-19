
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
import service.CategoriaService;


@WebServlet(name = "CategoriaController", urlPatterns = {"/Categoria"})
@MultipartConfig
public class CategoriaController extends HttpServlet {

    private final CategoriaService _service = new CategoriaService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Categoria> categorias = _service.findCategoriaEntities();
        
        General.sendAsJson(response, General.ObjectToJson(categorias));
        return;
        
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        try {
            // Obtener los datos del formulario
            String categoria = request.getParameter("categoria");
            Part filePart = request.getPart("pic_categoria");
            InputStream fileContent = filePart.getInputStream();
        
            // Obtener el nombre original del archivo
            String originalFileName = filePart.getSubmittedFileName();

            // Obtener la extensión del archivo
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        
            // Guardar la imagen en el servidor
            String uploadedDirectory = getServletContext().getRealPath("/"); // Obtiene la ruta del contexto del proyecto
            uploadedDirectory = uploadedDirectory + "imagenes";

            // Verificar si el directorio existe, si no, crearlo
            File directory = new File(uploadedDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
        
            String fileName = categoria.replace(" ", "_") + fileExtension;
            String uploadedFilePath = uploadedDirectory + File.separator + fileName;
            File uploadedFile = new File(uploadedFilePath);

            // Si el archivo ya existe, eliminarlo
            if (uploadedFile.exists()) {
                uploadedFile.delete();
            }

            // Guardar la nueva imagen en el servidor
            Files.copy(fileContent, Paths.get(uploadedFilePath));

            // Agregar en la db 
            Categoria categoriaAdd = new Categoria();
            categoriaAdd.setCategoria(categoria);
            categoriaAdd.setImagenCat(fileName);

            _service.create(categoriaAdd);
            
            General.sendAsJson(response, General.ObjectToJson(categoriaAdd));
            return;

        } catch (Exception e) {
            General.sendAsJson(response, "[]");
            return;
        }
        
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));

            Categoria categoria = _service.findCategoria(id);
            
            String uploadedDirectory = getServletContext().getRealPath("/"); // Obtiene la ruta del contexto del proyecto
            uploadedDirectory = uploadedDirectory + "imagenes";
            String uploadedFilePath = uploadedDirectory + categoria.getImagenCat();
            File uploadedFile = new File(uploadedFilePath);

            // Si el archivo ya existe, eliminarlo
            if (uploadedFile.exists()) {
                uploadedFile.delete();
            }

            _service.destroy(id);
            
            General.sendAsJson(response, "[{\"id\": " + id + "}]");
            return;

        }catch(Exception e){
            General.sendAsJson(response, "[]");
            return;
        }
    }
    
    


}
