package model;

import dao.DAOeqlink;
import dao.DAOquestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Exam {

	private int idexam;
	private int nbq;
	private float duree;

	private ObservableList<Integer> myquestions = FXCollections.observableArrayList();

	public Exam(int id, int nq, float duree) {
		DAOeqlink daoeqlink = new DAOeqlink();
		this.idexam = id;
		this.nbq = nq;
		this.duree = duree;
		this.myquestions = daoeqlink.getAllQuestionsForAnExam(idexam);
	}

	public String getMyquestions() {
		DAOquestion daoquestion = new DAOquestion();
		String s = new String("");
		for (int temp : this.myquestions) {
			if (s.length() == 0) {
				s =daoquestion.read(temp).getEnoncee();
			} else {
				s = s +"\n"+ daoquestion.read(temp).getEnoncee();
			}
		}

		return s;

	}
	

	public void setMyquestions(ObservableList<Integer> myquestions) {
		this.myquestions = myquestions;
	}

	public int getIdexam() {
		return idexam;
	}

	public void setIdexam(int idexam) {
		this.idexam = idexam;
	}

	public int getNbq() {
		return nbq;
	}

	public void setNbq(int nbq) {
		this.nbq = nbq;
	}

	public float getDuree() {
		return duree;
	}

	public void setDuree(float duree) {
		this.duree = duree;
	}

}
