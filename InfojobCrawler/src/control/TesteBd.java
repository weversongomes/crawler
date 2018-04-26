package control;

import java.util.LinkedList;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class TesteBd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bd bd= new Bd();
		
		String s ="Emprego testes àáâçdas";
		String e ="Cidade São Paulo ádãs";
		String r ="Descrção do emprego, gaçom testéééééá´´s´dasdasdas´dç~~as.da.sçdaçdçaç";
		
		// inserir no banco
		try {
			Job job = new Job();
			job.title = s;
			job.city = e;
			job.id = "4326654"; //esse id é o id e do site, 
			job.state = "BA";
			job.salary = 123123;
			job.description = r;
			Date data = new Date(System.currentTimeMillis());  
			SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 	
			job.date = data;
			
			bd.insert(job);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao tentar inserir");
			//e.printStackTrace();
		}
		
		
		
		/*
		//deletando por id
		try {
			bd.deletById(3);//deletando por id
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
		//buscar tudo do banco
        
        /*
		try {
			LinkedList<Job> listaRetornada =  new LinkedList<Job>();
			listaRetornada =  bd.buscarTudo();
			System.out.println("Tamanho da lista: "+listaRetornada.size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
        
		//SELECT * FROM `jobs` WHERE `city` LIKE 'Salvador'
		
		//buscar vagas em salvador por exemplo
		/*
		try {
			System.out.println("\nBuscando vagas em ssa:");
			String query = "SELECT * FROM `jobs` WHERE `city` LIKE 'Salvador'";
			LinkedList<Job> listaRetornada =  new LinkedList<Job>();
			listaRetornada =  bd.buscarQuery(query);
			System.out.println("Tamanho da lista: "+listaRetornada.size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

	}

}
