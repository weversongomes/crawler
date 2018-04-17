package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;

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
		Hashtable<String, Job> jobs = new Hashtable<String, Job>();
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
					}
				}
			} else if (buildingCity) {
				if (line.trim().length() > 1) {
					line = line.substring(line.indexOf(line.trim()));
					buildingCity = false;
					buildingSalary = true;
					job.city = line.split("/")[0];
					job.state = line.split("/")[1];
				}
			} else if (buildingSalary) {
				if (line.contains("R$ ")) {
						buildingSalary = false;
						line = line.substring(line.indexOf("R$ ") + 3);
						line = line.replace(".", "");
						line = line.replace(",", ".");
						line = line.substring(0, line.indexOf(".") + 3);
						job.salary = Float.parseFloat(line);
						if (!jobs.containsKey(job.id) && job.isValid()) {
							jobs.put(job.id, job);
						}
				} else if(line.contains("fa-map-marker")) {
					if (!jobs.containsKey(job.id) && job.isValid()) {
						jobs.put(job.id, job);
					}
				}
			}
		}
		in.close();
		System.out.println(jobs.toString());
	}
}
