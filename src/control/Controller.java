package control;

import java.util.ArrayList;
import java.util.List;

import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import view.View;

public class Controller {

	// View theView = new View();

	public void printAllChapters() {
		DAOchapitre daochapitre = new DAOchapitre();

		ObservableList<Chapitre> oList = daochapitre.getListChap();
		View.ChaptersTab(oList);

	}

	public void printAllExams() {
		DAOexam daoexam = new DAOexam();
		ObservableList<Exam> eList = daoexam.getListExam();
		View.examTab(eList);

	}

	public void addchapter(String chaptername) {

		DAOchapitre daochapitre = new DAOchapitre();

		if (daochapitre.getallnames().contains(chaptername)) {
			View.error("Chapter already Exists");
		} else {

			int newID = RandID.randID(); // making sure there is no duplicate IDs
			while (daochapitre.getallIDs().contains(newID)) {
				newID = RandID.randID();
			}

			Chapitre newChapitre = new Chapitre(newID, chaptername);

			daochapitre.add(newChapitre);
			printAllChapters();
		}

	}

	public int generateIDforQestion() {
		DAOquestion daoquestion = new DAOquestion();
		int r = RandID.randID();
		do {// making sure there is no duplicate IDs
			r = RandID.randID();
		} while (daoquestion.getallIDs().contains(r));
		return r;
	}

	public List<String> allchapternames() {
		DAOchapitre daochapitre = new DAOchapitre();

		return daochapitre.getallnames();
	}

	public void addQestion(int id, String chapname, String enon) {
		DAOquestion daoquestion = new DAOquestion();
		DAOchapitre daochapitre = new DAOchapitre();
		int idChap = daochapitre.readChapID(chapname);
		daoquestion.add(new Question(id, idChap, enon));

	}

	public void addExam(Exam e, ObservableList<Integer> qs) {
		DAOexam daoexam = new DAOexam();
		DAOeqlink daoeqlink = new DAOeqlink();

		if (e.getNbq() < qs.size()) {
			View.error("number of questions chosen exceeds the total number set for this exam");
		}else if (e.getNbq() > qs.size()) {
			View.error("couldn't reach the total number of questions set for this exam");
		}

		daoexam.add(e); // 1 add exam to db

		for (Integer temp : qs) {
			int eqlinkid = RandID.randID(); // no control over duplicates TO DO !!!
			daoeqlink.add(new EQLink(eqlinkid, e.getIdexam(), temp)); // 2 add its questions
		}

		e.setMyquestions(qs);// 3 alter exam object
	}

}
