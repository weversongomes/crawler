/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.sql.ResultSet;

/**
 *
 * @author Murilo
 */

@WebServlet(name = "Api", urlPatterns = {"/Api"})
public class Api extends HttpServlet {
	Bd bd;
	private ResultSet resultSet;
	
    public void init(ServletConfig config) throws ServletException {
		bd = new Bd();
		resultSet = null;
      
       // <editor-fold defaultstate="collapsed" desc="Compiled Code">
       /* 0: aload_0
        * 1: aload_1
        * 2: putfield      javax/servlet/GenericServlet.config:Ljavax/servlet/ServletConfig;
        * 5: aload_0
        * 6: invokevirtual javax/servlet/GenericServlet.init:()V
        * 9: return
        *  */
       // </editor-fold
   }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String profissao =  request.getParameter("cargo");
        String estado =  request.getParameter("estado");
        String min = "";
        String max = "";
        String med = "";
        String Qtd = "";
        int todosEstados;
        if(estado.equalsIgnoreCase("BR")) {
        	todosEstados=1;
        }else {
        	todosEstados=0;
        }
        
        try {
			min = Integer.toString(bd.buscaPorSalario(profissao,estado, todosEstados,"MIN"));
			max = Integer.toString(bd.buscaPorSalario(profissao,estado, todosEstados,"MAX"));
			med = Integer.toString(bd.buscaPorSalario(profissao,estado, todosEstados,"AVG"));
			Qtd = Integer.toString(bd.buscaPorSalario(profissao,estado, todosEstados,"COUNT"));
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        //criar um json
        JsonObject obj = Json.createObjectBuilder()
        		.add("Cargo", profissao)
        		.add("Estado", estado)
        		.add("Min", min)
        		.add("Max", max)
        		.add("Med", med)
        		.add("Qtd", Qtd)
        		.build();
       
        //retornar um json
        response.setContentType("application/json");
	     // Get the printwriter object from response to write the required json object to the output stream      
	     PrintWriter out = response.getWriter();
	     // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
	     out.print(obj);
	     out.flush();
	     


       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
