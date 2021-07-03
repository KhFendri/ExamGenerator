package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MySingleton;
import model.Question;

public class DAOquestion extends DAO<Question>
{
	MySingleton singleton = MySingleton.getInstance();
	Connection conn = singleton.getConn(); // establishing connection

	@Override
	public void add(Question a)
	{
		int idQestion = a.getIdQuestion();
		int idChap = a.getIdChapitre();
		String enoncee = a.getEnoncee();
		try {
			Statement ps;
			// ResultSet rs;
			String requete = "INSERT INTO question (idquestion, idchapitre, enonce) VALUES (" + idQestion + "," + idChap + ",'" + enoncee + "')";
			ps = conn.createStatement();
			ps.executeUpdate(requete);

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}
		
	}

	@Override
	public void delete(Question obj)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Question o)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Question read(int id)
	{
		try {
			Statement ps;
			ResultSet rs;
			String requete = "SELECT * FROM `question` WHERE `idquestion`="+id;
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				int idquestion= rs.getInt("idquestion");
				int idchap= rs.getInt("idchapitre");
				String enonce = rs.getString("enonce");
				
				
				
				return new Question(idquestion,idchap,enonce);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}
		return null;
	}

	@Override
	public List<Question> findAll()
	{
		List<Question> qlist = new ArrayList<>();

		try {
			Statement ps;
			ResultSet rs;
			String requete = "SELECT * FROM `question` WHERE 1";
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				int idq = rs.getInt("idquestion");
				int idch =rs.getInt ("idchapitre");
				String enonce = rs.getString ("enonce");
				qlist.add(new Question(idq,idch,enonce));
			}
			rs.close();
			ps.close();
			
			return qlist;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}

	
		return null;
	}
	
	public List<String> getallIDs() {
		List<String> idlist = new ArrayList<>();

		try {
			Statement ps;
			ResultSet rs;
			String requete = "SELECT `idquestion` FROM `question` WHERE 1";
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				String id = rs.getString("idquestion");
				idlist.add(id);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}

		return idlist;
	}

}
