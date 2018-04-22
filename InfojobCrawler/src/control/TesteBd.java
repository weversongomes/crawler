package control;

import java.util.LinkedList;

public class TesteBd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bd bd= new Bd();
		
		/*
		// inserir no banco
		try {
			Job job = new Job();
			job.title = "Emprego de computero";
			job.city = "Salvador";
			job.id = "asdas"; //esse id é o id e do site, 
			job.state = "BA";
			job.salary = (float) 3500.00;
			job.description = "Descrição da profisão aqui \n Descrição da profisão aqui\n Descrição da profisão aqui";
			
					bd.insert(job);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erro asdasdasd");
			//e.printStackTrace();
		}
		
		*/
		
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
		try {
			LinkedList<Job> listaRetornada =  new LinkedList<Job>();
			listaRetornada =  bd.buscarTudo();
			System.out.println("Tamanho da lista: "+listaRetornada.size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//SELECT * FROM `jobs` WHERE `city` LIKE 'Salvador'
		
		//buscar vagas em salvador por exemplo
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
		

	}

}
