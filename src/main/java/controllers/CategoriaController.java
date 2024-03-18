
package controllers;

import helpers.General;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Categoria;
import service.CategoriaService;


@WebServlet(name = "CategoriaController", urlPatterns = {"/Categoria"})
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
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
        

            _service.destroy(id);
            
            General.sendAsJson(response, "[{\"id\": " + id + "}]");
            return;

        }catch(Exception e){
            General.sendAsJson(response, "[]");
            return;
        }
    }
    
    


}
