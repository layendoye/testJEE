package model;

import java.time.LocalDate;
import java.util.Date;

public class Employe {
	public int id;
	public String matricule;
	public String nom;
	public String telephone;
	public LocalDate naissance;
	public int salaire;
	public Service service;

	public Employe() {}
	public Employe(String matricule,String nom,String telephone,LocalDate naissance,int salaire,Service service) {
		this.matricule=matricule;
		this.nom=nom;
		this.telephone=telephone;
		this.naissance=naissance;
		this.salaire=salaire;
		this.service=service;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public LocalDate getNaissance() {
		return naissance;
	}
	public void setNaissance(LocalDate naissance) {
		this.naissance = naissance;
	}
	public int getSalaire() {
		return salaire;
	}
	public void setSalaire(int salaire) {
		this.salaire = salaire;
	}
	public Service getService() {return service;}
	public void setService(Service service) {this.service = service;}

}
