
package controllers;

import helpers.General;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            String action = request.getParameter("action");
            
            if(action.equals("create")){
                createCategory(request, response);
            }
            
            if(action.equals("update")){
                updateCategory(request, response);
            }
          
        } catch (Exception e) {
            General.sendAsJson(response, "[{\"status\":\""+e.getMessage()+"\"}]");
        }
        
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));

            Categoria categoria = _service.findCategoria(id);
            
            String uploadedDirectory = getServletContext().getRealPath("/"); // Obtiene la ruta del contexto del proyecto
            uploadedDirectory = uploadedDirectory + "imagenes\\";
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

    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            
            String uploadedDirectory = getServletContext().getRealPath("/");
            
            // Obtener los datos del formulario
            String categoria = request.getParameter("categoria");
            
            
             // Categoria agregar
            Categoria categoriaAdd = new Categoria();
             
            Part filePart = request.getPart("pic_categoria");
            InputStream fileContent = filePart.getInputStream();
        
            // Obtener el nombre original del archivo
            String originalFileName = filePart.getSubmittedFileName();
            
            // Obtener la extensión del archivo
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
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
            categoriaAdd.setImagenCat(fileName);
            
            categoriaAdd.setCategoria(categoria);
            
            _service.create(categoriaAdd);
            
            General.sendAsJson(response, General.ObjectToJson(categoriaAdd));
            
        } catch (Exception e) {
            General.sendAsJson(response, "[]");
        }
    }
    
    private void updateCategory(HttpServletRequest request, HttpServletResponse response)  throws IOException{
        try {
            
            String uploadedDirectory = getServletContext().getRealPath("/");
            
            // Obtener los datos del formulario
            String categoria = request.getParameter("categoria");
            
            // Categoria agregar
            Categoria categoriaAdd = new Categoria();
            Part filePart = request.getPart("pic_categoria");
            InputStream fileContent = filePart.getInputStream();
        
            // Obtener el nombre original del archivo
            String originalFileName = filePart.getSubmittedFileName();
            
            if(!originalFileName.equals("")){
                 // Obtener la extensión del archivo
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
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
                categoriaAdd.setImagenCat(fileName);
            }else{
                String picCategoria = request.getParameter("pic_categoria_old");
                categoriaAdd.setImagenCat(picCategoria);

                String uploadedFilePath = uploadedDirectory + "imagenes\\"+categoriaAdd.getImagenCat();
                File uploadedFile = new File(uploadedFilePath);

                // Verificar si el archivo existe
                if (uploadedFile.exists()) {

                    // Obtener la extensión del archivo
                    String fileExtension = picCategoria.substring(picCategoria.lastIndexOf("."));

                    // Nuevo nombre del archivo
                    String nuevoNombreArchivo = uploadedDirectory +  "imagenes\\" + categoria.replace(" ", "_") + fileExtension ; 

                    // Crear un nuevo objeto File con el nuevo nombre
                    File nuevoArchivo = new File(nuevoNombreArchivo);

                    uploadedFile.renameTo(nuevoArchivo);

                    categoriaAdd.setImagenCat(categoria.replace(" ", "_") + fileExtension);
                }
            }
            
            int id = Integer.parseInt(request.getParameter("idCategoriaUpdate"));
                
            Categoria categoriaEdit = _service.findCategoria(id);

            categoriaEdit.setCategoria(categoria);
            categoriaEdit.setImagenCat(categoriaAdd.getImagenCat());

            _service.edit(categoriaEdit);
            
            General.sendAsJson(response, General.ObjectToJson(categoriaEdit));
            
            
        } catch (Exception e) {
            General.sendAsJson(response, "[]");
        }
    }
}
