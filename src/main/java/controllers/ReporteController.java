package controllers;

import helpers.General;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Juego;
import service.JuegoService;


@WebServlet(name = "ReporteController", urlPatterns = {"/Reporte"})
public class ReporteController extends HttpServlet {

    private final JuegoService _service = new JuegoService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Juego> games = this.listGamesByPriceDescending();
        
        General.sendAsJson(response, General.ObjectToJson(games));
    }
    
    private List<Juego> listGamesByPriceDescending(){
        List<Juego> games = _service.findJuegoEntities();
        
        int n = games.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (games.get(j).getPrecio() < games.get(j+1).getPrecio()) {
                    Juego temp = games.get(j);
                    games.set(j, games.get(j+1));
                    games.set(j+1, temp);
                }
            }
        }
        
        
        return games;
    }

}
