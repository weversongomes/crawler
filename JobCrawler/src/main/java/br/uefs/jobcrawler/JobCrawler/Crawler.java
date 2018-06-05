package br.uefs.jobcrawler.JobCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class Crawler {
	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		//System.setProperty("https.proxyHost", "10.65.16.2"); 
		//System.setProperty("https.proxyPort", "3128");
		try {
			//String url = crawler.getAddressFor("", "");
			//crawler.getData(url);
			for (int i = 2; i < 3; i++) {  //carregar n paginas
				System.out.println("\n************************  Pagina: "+i+"  *************************\n");
				String jsonContent = crawler.getContent("https://www.sine.com.br/api/v1.0/Job/List?idFuncao=0&idCidade=0&pagina="+i+"&pesquisa=&ordenacao=1&idUsuario=NaN");
				crawler.getAndParseJson(jsonContent);
			}
			System.out.println("\nFim");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao baixar a pagina");
		}
	}
	
	public String getContent(String url) throws IOException {
		URL sourceUrl = new URL(url);
		System.out.println("Iniciando download do json...");
		BufferedReader in = new BufferedReader(new InputStreamReader(sourceUrl.openStream(), "UTF-8"));
		String line;
		StringBuilder jsonContent = new StringBuilder();
		while ((line=in.readLine()) != null) {
			jsonContent.append(line);
		}
		return jsonContent.toString();
	}
	
	public void getAndParseJson(String jsonContent) {
		Bd bd = new Bd();
		Job job = new Job();
		JsonReader jsonReader = Json.createReader(new StringReader(jsonContent));
		JsonArray array = jsonReader.readArray();
		
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 		
		System.out.println("Data: "+data);
		
		int count = 0;
		while(count<array.size()) {
			JsonObject jsonObject = array.getJsonObject(count);
	
	
			job.id = "" + jsonObject.getInt("id");
			job.title = jsonObject.getString("df");
			job.city = jsonObject.getString("dc");
			job.state = jsonObject.getString("uf");
			
			String s = jsonObject.get("sl").toString();
			s = s.replace(".", "");

			//formatar a string pra converter para int
			if (s.contains("R$")) {

				job.salary = Integer.parseInt(s.substring(s.indexOf(" ")+1, s.indexOf(",")));
			}else {
				job.salary = 0; //string nulla
			}
			
			job.description = jsonObject.getString("d");
			job.date = data;
			
			System.out.println("\nId: "+job.id);
			System.out.println("Titulo: "+job.title);
			System.out.println("Cidade: "+job.city);
			System.out.println("Estado: "+job.state);
			System.out.println("Salário: "+job.salary);
			System.out.println("Descrição: "+job.description);
			
			
			try {
				bd.insert(job);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
		jsonReader.close();
	}
}
