package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import javax.json.JsonObject;

import com.mysql.cj.xdevapi.JsonParser;


public class Crawler {
	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		try {
			//String url = crawler.getAddressFor("", "");
			//crawler.getData(url);
			String jsonContent = crawler.getContent("https://www.sine.com.br/api/v1.0/Job/List?idFuncao=8741&idCidade=0&pagina=2&pesquisa=&ordenacao=1&idUsuario=NaN");
			crawler.getAndParseJson(jsonContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	public String getAddressFor(String city, String function) {
		//System.setProperty("https.proxyHost", "10.65.16.2"); 
		//System.setProperty("http.proxyPort", "3128");

		StringBuilder address = new StringBuilder();
		address.append("https://www.sine.com.br/");
		if (city.equals("feira de santana")) {
			address.append("vagas-empregos-em-feira-de-santana-ba");
		}
		
		if (function.equals("eletricista")) {
			address.append("/eletricista");
		}
		return address.toString();
	}*/
	
	public String getContent(String url) throws IOException {
		URL sourceUrl = new URL(url);
		System.out.println("Iniciando download do json...");
		BufferedReader in = new BufferedReader(new InputStreamReader(sourceUrl.openStream()));
		String line;
		StringBuilder jsonContent = new StringBuilder();
		while ((line=in.readLine()) != null) {
			jsonContent.append(line);
		}
		return jsonContent.toString();
	}
	
	public void getAndParseJson(String jsonContent) {
		/*JsonParser parser = new JsonParser();
		JsonObject jsonObj;
		jsonObj = (JsonObject) parser.parse(content);
		String name = jsonObj.getString("name");
		int level = jsonObj.getInt("level");*/
	}
	
	public void getData(String url) throws IOException {
		Bd bd = new Bd();
		URL sourceUrl = new URL(url);
		System.out.println("Iniciando download...");
		BufferedReader in = new BufferedReader(new InputStreamReader(sourceUrl.openStream()));

		String line;
		Job job = new Job();
		boolean buildingID = false;
		boolean buildingJobTitle = false;
		boolean buildingCity = false;
		boolean buildingSalary = false;
		while ((line=in.readLine()) != null) {
			if ((line.contains("item-block col-md-12"))) {
				job = new Job();
				buildingID = true;
			} else if (buildingID) {
				if (line.contains("href")) {
					line = line.substring(line.indexOf("\"") + 1);
					line = line.substring(0, line.indexOf("\""));
					job.id =line;
					buildingID = false;
					System.out.println(job.id);
				}
			} else if (line.contains("titulo_vaga")) {
				buildingJobTitle = true;
			} else if (buildingJobTitle) {
				if (line.contains("<span>")) {
					buildingJobTitle = false;
					buildingCity = true;
				} else if (line.trim().length() > 1) {
					if (line.contains("fa-wheelchair")) {
						line = line.substring(line.indexOf("</i>") + 4);
						if (line.charAt(0) == 32) {
							line = line.substring(1);
						}
					}
					if (line.trim().length() > 1) {
						line = line.substring(line.indexOf(line.trim()));
						job.title = line;
						System.out.println(job.title);
					}
				}
			} else if (buildingCity) {
				if (line.trim().length() > 1) {
					line = line.substring(line.indexOf(line.trim()));
					buildingCity = false;
					buildingSalary = true;
					job.city = line.split("/")[0];
					job.state = line.split("/")[1];
					System.out.println(job.city);
					System.out.println(job.state);
				}
			} else if (buildingSalary) {
				if (line.contains("R$ ")) {
						buildingSalary = false;
						line = line.substring(line.indexOf("R$ ") + 3);
						line = line.replace(".", "");
						line = line.replace(",", ".");
						line = line.substring(0, line.indexOf(".") + 3);
						job.salary = Float.parseFloat(line);
						System.out.println(job.salary);
						if (job.isValid()) { 
							//teste inserir
							job.description= "Sem descricao";
							
							
							
							try {
								bd.insert(job);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
				            } 
				        } else if(line.contains("fa-map-marker")) { 
				          if (job.isValid()) { 
				        	//teste inserir
								job.description= "Sem descricao";
								
								
								
								try {
									bd.insert(job);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				         
				          }
						
						
						
				}
			}
		}
		
		in.close();
		//System.out.println(jobs.toString());
		//exibir tudo que tem na base
		try {
			System.out.println("\n******************Empregos na base de dados:**********************");
			LinkedList<Job> list = new LinkedList<Job>();
			list = bd.buscarTudo();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
