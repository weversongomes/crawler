package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Crawler {
	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		try {
			crawler.getData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getData() throws IOException {
		String website = "https://www.sine.com.br/vagas-empregos-em-feira-de-santana-ba/";
		String function = "eletricista";
		URL sourceUrl = new URL(website + function);
		System.out.println("Iniciando download...");
		BufferedReader in = new BufferedReader(new InputStreamReader(sourceUrl.openStream()));
		//System.out.println("passou");
		String line;
		Job job;
		StringBuilder jobTitle = new StringBuilder();
		boolean buildingJobTitle = false;
		while ((line=in.readLine()) != null) {
			if ((line.contains("item-block col-md-12"))) {
				job = new Job();
				jobTitle = new StringBuilder();
			} else if (line.contains("titulo_vaga")) {
				buildingJobTitle = true;
			} else if (buildingJobTitle) {
				if (line.contains("<span>")) {
					buildingJobTitle = false;
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
					jobTitle.append(line);
				}
			}
		}
		in.close(); 
	}
	//System.out.println(line.substring(line.indexOf(">") + 1, line.indexOf("</h1>")));
}
