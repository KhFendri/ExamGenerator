package model;

public class Chapitre {
	private int idchap;
	private String titlechap;

	public Chapitre(int newID, String title) {
		this.idchap = newID;
		this.titlechap = title;
	}

	public int getIdchap() {
		return idchap;
	}

	public void setIdchap(int idchap) {
		this.idchap = idchap;
	}

	public String getTitlechap() {
		return titlechap;
	}

	public void setTitlechap(String titlechap) {
		this.titlechap = titlechap;
	}

}
