package control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.sql.ResultSet;

public class Bd {

    private String serverName = "localhost";    //caminho do servidor do BD
    private String mydatabase = "job";        //nome do seu banco de dados
  
    private String url = "jdbc:mysql://" + serverName + "/" + mydatabase+"?useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC";
    private String username = "user";        //nome de um usuaririo de seu BD      
    private String password = "12345";      //sua senha de acesso
   
    private Connection connection = null;
    //private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public String status = "Desconectado...";

    /**
     * Insere os dados da classe job no bd
     * @param job objeto com os atributos de job
     * @throws Exception gera erros sql
     */
    public void insert(Job job) throws Exception {
    	
   	 try {
   		 	// Carregando o JDBC Driver padrao
   		 	Class.forName("com.mysql.cj.jdbc.Driver");
            
            //abre a conexao
            connection = DriverManager.getConnection(url, username, password);
            //statement = connection.createStatement();
           
            if (connection != null) {
            	 
               preparedStatement = connection.prepareStatement("insert into jobs(title,id_site,city,state,salary,description,Data) values ( ?, ?, ?, ? , ?, ?, ?)");
               preparedStatement.setString(1, job.title);
               preparedStatement.setString(2, job.id);
               preparedStatement.setString(3, job.city);
               preparedStatement.setString(4, job.state);
               preparedStatement.setInt(5,job.salary);
               preparedStatement.setString(6,job.description);
               preparedStatement.setDate(7,job.date);
               preparedStatement.execute();
               
               preparedStatement.close();
           
 
            } else {
 
            	 System.out.println("Errou ao conectar ao banco!");
            	 
 
            }
            
            connection.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
        	
          
        }
    }
    /**
     * Retorna todos os dados do banco em uma lista 
     * @return Lista com os dados do banco
     * @throws ClassNotFoundException
     */
    public LinkedList<Job> buscarTudo() throws ClassNotFoundException {
    	// Carregando o JDBC Driver padrao
    	LinkedList<Job> list= new LinkedList<Job>();
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			 //abre a conexao
			connection = DriverManager.getConnection(url, username, password);
	    	preparedStatement = connection.prepareStatement("SELECT * FROM `jobs`");
	        resultSet = preparedStatement.executeQuery();
	        
	        Job job = new Job();
	        while (resultSet.next()) {
	            
	        	job = new Job();
	        			
	        	job.title =  resultSet.getString("title");
	        	job.id =  resultSet.getString("id_site");
	        	job.city = resultSet.getString("city");
	        	job.state = resultSet.getString("state");
	        	job.salary =  resultSet.getInt("salary");
	        	job.description = resultSet.getString("description");
	        	job.date = resultSet.getDate("Data");
	        	
	            System.out.println("Titulo: " + job.title);
	            System.out.println("Id site: " +job.id);
	            System.out.println("Cidade: " + job.city );
	            System.out.println("estadot: " + job.state );
	            System.out.println("salario: " + job.salary);
	            System.out.println("descriacao: " + job.description);
	            System.out.println("\n");
	            
	            list.add(job);
	            
	           
	        }
	        preparedStatement.close();
	        connection.close();
	        return list;
	        
		} catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return list;
        } finally {
        	
        	
        } 
  		 
  	 }
    
    /**
     * Procura no banco por uma query
     * @param query String com a query
     * @return lista de job encontrados com a query
     * @throws ClassNotFoundException
     */
    public LinkedList<Job> buscarQuery(String query) throws ClassNotFoundException {
    	// Carregando o JDBC Driver padr�o
    	LinkedList<Job> list= new LinkedList<Job>();
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			 //abre a conexao
			connection = DriverManager.getConnection(url, username, password);
	    	preparedStatement = connection.prepareStatement(query);
	        resultSet = preparedStatement.executeQuery();
	        
	        Job job = new Job();
	        while (resultSet.next()) {
	            
	        	job = new Job();

	        	job.title =  resultSet.getString("title");
	        	job.id =  resultSet.getString("id_site");
	        	job.city = resultSet.getString("city");
	        	job.state = resultSet.getString("state");
	        	job.salary =  resultSet.getInt("salary");
	        	job.description = resultSet.getString("description");
	        	job.date =  resultSet.getDate("Data");
	        	
	            System.out.println("Titulo: " + job.title);
	            System.out.println("Id site: " +job.id);
	            System.out.println("Cidade: " + job.city );
	            System.out.println("estadot: " + job.state );
	            System.out.println("salario: " + job.salary);
	            System.out.println("descricao: " + job.description);
	            System.out.println("data: " + job.date);
	            System.out.println("\n");
	            
	            list.add(job);
	            
	           
	        }
	        preparedStatement.close();
	        connection.close();
	        return list;
	        
		} catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return list;
        } finally {
        	
        	
        } 
  		 
  	 }
    /**
     * Deleta uma profi��o na base de dados, executa uma query de delete.
     * @param id Id da profiss�o no banco de dados
     * @throws Exception
     */
    public void deletById(int id) throws Exception {
    	
      	 try {
      		 	// Carregando o JDBC Driver padr�o
               Class.forName("com.mysql.cj.jdbc.Driver");
               
               //abre a conexao
               connection = DriverManager.getConnection(url, username, password);
               //statement = connection.createStatement();
              
               if (connection != null) {
               	 
                  System.out.println("Deletando...!!");
                  preparedStatement = connection.prepareStatement("DELETE FROM `job`.`jobs` WHERE `jobs`.`id_site` = ?");
                  preparedStatement.setInt(1, id);
           
                  preparedStatement.executeUpdate();
                  preparedStatement.close();

    
               } else {
    
               	 System.out.println("Errou ao conectar ao banco!");
               	 
    
               }
               
               connection.close();

           } catch (SQLException ex) {
               // handle any errors
               System.out.println("SQLException: " + ex.getMessage());
               System.out.println("SQLState: " + ex.getSQLState());
               System.out.println("VendorError: " + ex.getErrorCode());
           } finally {
           	
             
           }
       } 

 
}
