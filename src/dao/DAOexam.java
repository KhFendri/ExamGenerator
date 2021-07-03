package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Chapitre;
import model.Exam;
import model.MySingleton;

public class DAOexam extends DAO<Exam>
{
	MySingleton singleton = MySingleton.getInstance();
	Connection conn = singleton.getConn(); // establishing connection


	@Override
	public void add(Exam t)
	{
		int idexam = t.getIdexam();
		int nbq = t.getNbq();
		Float duree =  t.getDuree();
		try {
			Statement ps;
			// ResultSet rs;
			String requete = "INSERT INTO exam (idexam, nbq, duree) VALUES (" + idexam + "," + nbq + ","+ duree +")";
			ps = conn.createStatement();
			ps.executeUpdate(requete);

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}
		
	}

	@Override
	public void delete(Exam obj)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Exam o)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Exam read(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> findAll()
	{
			Statement ps;
			ResultSet rs;
			List<Exam> listexam = new ArrayList<>();
			try {
				String requete = "select * from exam";
				ps = conn.createStatement();
				rs = ps.executeQuery(requete);
				while (rs.next()) {
					int id = rs.getInt("idexam");
					int nbq =rs.getInt("nbq");
					Float duree=rs.getFloat("duree");
					
					
					Exam q = new Exam(id,nbq,duree);

					listexam.add(q);
				}
				rs.close();
				ps.close();

				return listexam;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Execption");
			}
			return null;
		}
	
	public ObservableList<Exam> getListExam() {
		ObservableList<Exam> liste = FXCollections.observableArrayList(findAll());
		return liste;
	}
	}


