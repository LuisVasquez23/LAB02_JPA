/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import helpers.General;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Juego;
import service.JuegoService;
import service.exceptions.NonexistentEntityException;


@WebServlet(name = "JuegoController", urlPatterns = {"/Juego"})
public class JuegoController extends HttpServlet {
    
    
    private final JuegoService _service = new JuegoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Juego> juegos = _service.findJuegoEntities();
        
        General.sendAsJson(response, General.ObjectToJson(juegos));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            // Get the game id 
            int gameId = Integer.parseInt(request.getParameter("gameId"));
            
            // Delete game
            _service.destroy(gameId);
            
            General.sendAsJson(response, "[{\"status\":\"Done!\"}]");
            
        } catch (IOException | NonexistentEntityException e) {
            General.sendAsJson(response, "[{\"status\":\""+e.getMessage()+"\"}]");
        }
    }
    
    

}
