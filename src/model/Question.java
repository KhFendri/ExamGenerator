package model;

public class Question {

    private int idQuestion;
    private int idChapitre;
    private String enoncee;
	
    public Question(int idQuestion, int idChapitre, String enoncee) {
		super();
		this.idQuestion = idQuestion;
		this.idChapitre = idChapitre;
		this.enoncee = enoncee;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public int getIdChapitre() {
		return idChapitre;
	}

	public void setIdChapitre(int idChapitre) {
		this.idChapitre = idChapitre;
	}

	public String getEnoncee() {
		return enoncee;
	}

	public void setEnoncee(String enoncee) {
		this.enoncee = enoncee;
	}

    

}
