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
import model.MySingleton;

public class DAOchapitre extends DAO<Chapitre> {
	MySingleton singleton = MySingleton.getInstance();
	Connection conn = singleton.getConn(); // establishing connection

	@Override
	public void add(Chapitre a) {
		int idchap = a.getIdchap();
		String titlechap = a.getTitlechap();
		try {
			Statement ps;
			// ResultSet rs;
			String requete = "INSERT INTO chapitre (idchapitre, titre) VALUES (" + idchap + ",'" + titlechap + "')";
			ps = conn.createStatement();
			ps.executeUpdate(requete);

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}
	}

	@Override
	public void delete(Chapitre obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Chapitre o) {
		// TODO Auto-generated method stub

	}

	@Override
	public Chapitre read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chapitre> findAll() {
		Statement ps;
		ResultSet rs;
		List<Chapitre> listChap = new ArrayList<>();
		try {
			String requete = "select * from chapitre ";
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				int id = rs.getInt("idchapitre");
				String title = rs.getString("titre");
				Chapitre c = new Chapitre(id, title);

				listChap.add(c);
			}
			rs.close();
			ps.close();

			return listChap;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}
		return null;
	}

	public ObservableList<Chapitre> getListChap() {
		ObservableList<Chapitre> liste = FXCollections.observableArrayList(findAll());
		return liste;
	}

	public List<String> getallIDs() {
		List<String> idlist = new ArrayList<>();

		try {
			Statement ps;
			ResultSet rs;
			String requete = "SELECT `idchapitre` FROM `chapitre` WHERE 1";
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				String id = rs.getString("idchapitre");
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
	
	public List<String> getallnames() {
		List<String> namelist = new ArrayList<>();

		try {
			Statement ps;
			ResultSet rs;
			String requete = "SELECT `titre` FROM `chapitre` WHERE 1";
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				String name = rs.getString("titre");
				namelist.add(name);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}

		return namelist;
	}
	
	public int readChapID(String name) {  //return chap id from a given name
		int res = 0;
		try {
			Statement ps;
			ResultSet rs;
			String requete = "SELECT `idchapitre` FROM `chapitre` WHERE `titre`='"+name+"'";
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
			res = rs.getInt("idchapitre");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}

	
		return res;
	}

}
