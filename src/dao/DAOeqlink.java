package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.EQLink;
import model.MySingleton;

public class DAOeqlink extends DAO<EQLink> {

	MySingleton singleton = MySingleton.getInstance();
	Connection conn = singleton.getConn(); // establishing connection

	@Override
	public void add(EQLink a) {
		int idlink = a.getIdlink();
		int idexam = a.getIde();
		int idquestion =  a.getIdq();
		try {
			Statement ps;
			// ResultSet rs;
			String requete = "INSERT INTO eqlink (idlink, idexam, idquestion) VALUES (" + idlink + "," + idexam + ","+ idquestion +")";
			ps = conn.createStatement();
			ps.executeUpdate(requete);

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}

	}

	@Override
	public void delete(EQLink d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(EQLink u) {
		// TODO Auto-generated method stub

	}

	@Override
	public EQLink read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EQLink> findAll() {
		Statement ps;
		ResultSet rs;
		List<EQLink> list = new ArrayList<>();
		try {
			String requete = "select * from eqlink";
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				int idlink = rs.getInt("idlink");
				int idexam = rs.getInt("idexam");
				int idquestion = rs.getInt("idquestion");

				EQLink newobject = new EQLink(idlink, idexam, idquestion);

				list.add(newobject);
			}
			rs.close();
			ps.close();

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}
		return null;
	}

	public ObservableList<Integer> getAllQuestionsForAnExam(int ide) {

		Statement ps;
		ResultSet rs;
		ObservableList<Integer> data = FXCollections.observableArrayList();
		try {
			String requete = "SELECT * FROM `eqlink` WHERE `idexam`=" + ide;
			ps = conn.createStatement();
			rs = ps.executeQuery(requete);
			while (rs.next()) {
				int idqestion = rs.getInt("idquestion");

				data.add(idqestion);

			}
			rs.close();
			ps.close();

			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Execption");
		}
		return null;

	}

}
