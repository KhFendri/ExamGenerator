package model;

import java.util.ArrayList;

public class EQLink {
		
	private int idlink;
	private int ide;
	private int idq;

	
	public EQLink(int idlink, int ide, int idq) {
		super();
		this.idlink = idlink;
		this.ide = ide;
		this.idq = idq;
	}
	public int getIdlink() {
		return idlink;
	}
	public void setIdlink(int idlink) {
		this.idlink = idlink;
	}
	public int getIde() {
		return ide;
	}
	public void setIde(int ide) {
		this.ide = ide;
	}
	public int getIdq() {
		return idq;
	}
	public void setIdq(int idq) {
		this.idq = idq;
	}
	
	
	
}
