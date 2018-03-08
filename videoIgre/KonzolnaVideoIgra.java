package videoIgre;

public class KonzolnaVideoIgra extends VideoIgra {
	
	private String platforma;

	public KonzolnaVideoIgra(String ime, String zanr, int cena, String platforma) {
		super(ime, zanr, cena);
		this.platforma = platforma;
	}

	@Override
	public String getPlatforma() {
		return this.platforma;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
