package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Crawler {
	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		try {
			String url = crawler.getAddressFor("feira de santana", "eletricista");
			crawler.getData(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getAddressFor(String city, String function) {
		StringBuilder address = new StringBuilder();
		address.append("https://www.sine.com.br/");
		if (city.equals("feira de santana")) {
			address.append("vagas-empregos-em-feira-de-santana-ba");
		}
		
		if (function.equals("eletricista")) {
			address.append("/eletricista");
		}
		return address.toString();
	}
	
	public void getData(String url) throws IOException {
		URL sourceUrl = new URL(url);
		System.out.println("Iniciando download...");
		BufferedReader in = new BufferedReader(new InputStreamReader(sourceUrl.openStream()));

		String line;
		Job job = new Job();
		StringBuilder jobTitle = new StringBuilder();
		boolean buildingJobTitle = false;
		boolean buildingCity = false;
		while ((line=in.readLine()) != null) {
			if ((line.contains("item-block col-md-12"))) {
				job = new Job();
				jobTitle = new StringBuilder();
			} else if (line.contains("titulo_vaga")) {
				buildingJobTitle = true;
			} else if (buildingJobTitle) {
				if (line.contains("<span>")) {
					buildingJobTitle = false;
					buildingCity = true;
					System.out.println(jobTitle.toString());
				}
				if (line.trim().length() > 1) {
					if (line.contains("fa-wheelchair")) {
						line = line.substring(line.indexOf("</i>") + 4);
						if (line.charAt(0) == 32) {
							line = line.substring(1);
						}
					}
					line = line.substring(line.indexOf(line.trim()));
					job.title = line;
				}
			} else if (buildingCity) {
				if (line.trim().length() > 1) {
					line = line.substring(line.indexOf(line.trim()));
					buildingCity = false;
					job.city = line;
				}
			}
		}
		in.close(); 
	}
}
