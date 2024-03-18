/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import models.Juego;
import service.JuegoService;


@WebServlet(name = "JuegoController", urlPatterns = {"/Juego"})
public class JuegoController extends HttpServlet {
    
    
    private final JuegoService _service = new JuegoService();


   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Juego> juegos = _service.findJuegoEntities();
        
        General.sendAsJson(response, General.ObjectToJson(juegos));
        return;
        
   
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }


}
