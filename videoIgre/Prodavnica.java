package videoIgre;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Prodavnica {
	
	private List<VideoIgra> igre;
	
	public Prodavnica(List<VideoIgra> igre) {

		this.igre = igre;	
	}

	public List<VideoIgra> getIgre() {
		return igre;
	}
	
	public static List<VideoIgra> ucitajIgre(String putanja) {
		
		List<VideoIgra> listaVideoIgara = new ArrayList<VideoIgra>();
		Path input = Paths.get(putanja);
		
		try {
			List<String> listaIgara = Files.readAllLines(input, StandardCharsets.UTF_8);
			for (String l : listaIgara) {
				String[] podaci = l.split(", ");
				String platforma = podaci[0].trim();
				String ime = podaci[1].trim();
				String zanr = podaci[2].trim();
				int cena = Integer.parseInt(podaci[3].trim());
				if (platforma.equalsIgnoreCase("pc")) {
					int zahtevnost = Integer.parseInt(podaci[4].trim());
					listaVideoIgara.add(new PCVideoIgra(ime, zanr, cena, zahtevnost));
				}
				else {
					listaVideoIgara.add(new KonzolnaVideoIgra(ime, zanr, cena, platforma));				
				}
			}
		} catch (IOException e) {
			System.err.print("Greska prilikom ucitavanja datoteke");
			System.exit(1);
		}
		
		return listaVideoIgara;	
	}

	@Override
	public String toString() {
		
		StringBuilder igreLista = new StringBuilder();
		for (VideoIgra igra : igre) {
			igreLista.append("* " + igra + "\n");
		}
		return igreLista.toString();
	}
	
	
	
	

}
