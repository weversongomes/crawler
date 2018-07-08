import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
   		 	abrirConexao();
           
            if (connection != null) {
            	 
               preparedStatement = connection.prepareStatement("insert into jobs(title,id_site,city,state,salary,description,date) values ( ?, ?, ?, ? , ?, ?, ?)");
               preparedStatement.setString(1, job.title);
               preparedStatement.setString(2, job.id);
               preparedStatement.setString(3, job.city);
               preparedStatement.setString(4, job.state);
               preparedStatement.setInt(5,job.salary);
               preparedStatement.setString(6,job.description);
               preparedStatement.setDate(7,job.date);
               preparedStatement.execute();
               
               preparedStatement.close();
               connection.close();
           
 
            } else {
            	 System.out.println("Errou ao conectar ao banco!");
            }   
            connection.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            connection.close();
        } finally {
        	
          
        }
    }
    
    /**
     * Retorna todos os dados do banco em uma lista 
     * @return Lista com os dados do banco
     * @throws ClassNotFoundException
     */
    public LinkedList<Job> buscarTudo() throws ClassNotFoundException {
    	LinkedList<Job> list= new LinkedList<Job>();
        try {
        	abrirConexao();
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
	        	job.date = resultSet.getDate("date");
	        	
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
     * 
     * @param profissao cargo procurado. (all) para todas as profissoes
     * @param estado o estado procurado (BR) vai para o brasil todo
     * @param limite numero maximo de dados retornados
     * @return map com um job, a chave o id_site
     * @throws ClassNotFoundException
     */
    public Map<String, Job> procurarVagas(String profissao, String estado, int limite) throws ClassNotFoundException {
    	
    	Map<String, Job> hash = new HashMap<String, Job>();
    	String query = "";
    	String prof ="";
    	String est = "";
    	
    	//SELECT * FROM `jobs` WHERE `title` LIKE 'Vendedor%' AND `state` LIKE 'BA' LIMIT 10
    	if(!profissao.equalsIgnoreCase("ALL")) {
    		prof = "`title` LIKE '"+profissao+"%' ";	
    	}
    	if(!estado.equalsIgnoreCase("BR")) {
    		est = "AND `state` LIKE '"+estado+"' ";
    	}
    	
    	if(prof.equalsIgnoreCase("") &&est.equalsIgnoreCase("")) {
    		query = "SELECT * FROM `jobs` LIMIT "+Integer.toString(limite);
    	}else {
        	query = "SELECT * FROM `jobs` "+
        			"WHERE "+prof+est+"LIMIT "+Integer.toString(limite);
    	}

        try {
        	abrirConexao();
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
	        	job.date = resultSet.getDate("date");
	        	
	        	/*
	            System.out.println("Titulo: " + job.title);
	            System.out.println("Id site: " +job.id);
	            System.out.println("Cidade: " + job.city );
	            System.out.println("estadot: " + job.state );
	            System.out.println("salario: " + job.salary);
	            System.out.println("descriacao: " + job.description);
	            System.out.println("\n");
	            */
	            hash.put(job.id, job);  
	        }
	        preparedStatement.close();
	        connection.close();
	        return hash;
	        
		} catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return hash;
        } finally {
        	
        	
        } 
  		 
  	 }
    
    /**
     * Procura no banco por uma query
     * @param query String com a query
     * @return lista de job encontrados com a query
     * @throws ClassNotFoundException
     */
    public int buscaPorSalario(String profissao, String estado, int todosEstados, String funcao) throws ClassNotFoundException {
    	int valor = 0;
    	String f ="";
    	String query = "";
    	
    	if(funcao.equalsIgnoreCase("MIN")) {
    		f="MIN";
    	}
    	if(funcao.equalsIgnoreCase("MAX")) {
    		f="MAX";
    	}
    	if(funcao.equalsIgnoreCase("COUNT")) {
    		f="COUNT";
    	}
    	if(funcao.equalsIgnoreCase("AVG")) {
    		f="AVG";
    	}
    	 //Montar a consulta
        //SELECT MIN(`salary`) FROM `jobs` WHERE `title` LIKE 'Vendedor' AND `state` LIKE 'BA' AND `salary` > 0 ORDER BY `salary` ASC
        
    	if(todosEstados ==1) { //consulta para todo o brasil
    		query = "SELECT "+f+"(`salary`) FROM `jobs` "+
    				"WHERE `title` LIKE '"+profissao+"%' AND `salary` > 0";
        }else { //consulta para o estado especifico
        	query = "SELECT "+f+"(`salary`) FROM `jobs` "+
    				"WHERE `title` LIKE '"+profissao+"%' AND `state` LIKE '"+estado+"' AND `salary` > 0";
	
        }
    	
    	try {
        	abrirConexao();
        	
	    	preparedStatement = connection.prepareStatement(query);
	        resultSet = preparedStatement.executeQuery();
	       
	        if(resultSet.next()) {
	        	valor = resultSet.getInt(1) ;				
			}
	        preparedStatement.close();
	        connection.close();
	        return valor;
	        
		} catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            //resultSet = null;
            return valor;
        } finally {
        	
        	
        } 
  		 
  	 }
    
    /**
     * Deleta uma vaga base de dados, executa uma query de delete.
     * @param id Id da vaga no banco de dados
     * @throws Exception
     */
    public void deletById(int id) throws Exception {
    	
      	 try {
      		 abrirConexao();
              
               if (connection != null) { 
                  System.out.println("Deletando...!!");
                  preparedStatement = connection.prepareStatement("DELETE FROM `job`.`jobs` WHERE `jobs`.`id_site` = ?");
                  preparedStatement.setInt(1, id);
           
                  preparedStatement.executeUpdate();
                  preparedStatement.close();

               } else {
               	 	System.out.println("Erro ao conectar ao banco!");
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
     * Procura a vaga pelo id e retorna true se a vaga ja existe, false se n existe
     * @param id Id da vaga
     * @return True se a vaga existe, false se ainda nao tiver no banco
     * @throws ClassNotFoundException 
     */
    public boolean verificarVaga(String id) throws ClassNotFoundException {
    	 try {
    		abrirConexao();           
            if (connection != null) {
               preparedStatement = connection.prepareStatement("SELECT * FROM `jobs` WHERE `id_site` = ? ORDER BY `Data` DESC");
               preparedStatement.setString(1,id);
   
               resultSet = preparedStatement.executeQuery();
	        
		        if (resultSet.next()) {
		        	
		            connection.close();
					return true;
				}else {
					preparedStatement.close();
		            connection.close();
					return false;	
				}
	       
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
    	return false;
    }
    
    /**
     * Abre conexao com o banco
     */
    private void abrirConexao() {
        try {
        	// Carregando o JDBC Driver padrao
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    	
    }

 
}
