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
		//String website = "https://community.oracle.com/";
		URL sourceUrl = new URL(website + function);
		System.out.println("Iniciando download...");
		BufferedReader in = new BufferedReader(new InputStreamReader(sourceUrl.openStream()));
		System.out.println("passou");
		String line;
		while ((line=in.readLine()) != null) {
			if ((line.contains("<h1"))) {
				System.out.println(line.substring(line.indexOf(">") + 1, line.indexOf("</h1>")));
			}
		}
		in.close(); 
	}
}
