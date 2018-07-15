/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import java.sql.ResultSet;

/**
 *
 * @author Murilo
 */

@WebServlet(name = "Api", urlPatterns = {"/Api"})
public class Api extends HttpServlet {
	Bd bd;
	
	
    public void init(ServletConfig config) throws ServletException {
		bd = new Bd();

      
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
    	
    	String op =  request.getParameter("md");
    	
    	//retornar as estatisticas
    	if(op.equals("estatistica")) {
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

    		
    	}else if(op.equals("dado")) { //retorna json com os dados de vagas do bd
    		String profissao =  request.getParameter("cargo");
            String estado =  request.getParameter("estado");
            Map<String, Job> hash = new HashMap<String, Job>();
            Job job = new Job();
            
            JsonArrayBuilder builder = Json.createArrayBuilder();
            //JsonObjectBuilder builder = Json.createObjectBuilder();
            
    		try {
				hash = bd.procurarVagas(profissao, estado, 500);
				
	            for (String key : hash.keySet()) {
	            	
	                job = hash.get(key);              
	                JsonObjectBuilder object = Json.createObjectBuilder();
	                
	                object.add("city",job.city)
            		.add("est", job.state)
            		.add("prof", job.title)
            		.add("desc", job.description)
            		.add("sl", job.salary)
            		.add("id", job.id);
	                builder.add(object);
	            }
	            
	            //builder.build().toString();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Erro exceção durante a procura de vagas!");
			}
    		
    		//enviar
    		response.setContentType("application/json");
	   	        
	   	     PrintWriter out = response.getWriter();
	   	    
	   	     out.print(builder.build().toString());
	   	     out.flush();
    	}
    		     


       
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
