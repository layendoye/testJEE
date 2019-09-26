package model;


import java.lang.reflect.Array;
import java.util.*;

public class Service {
    private int id;
    private String lib;
    private List<Employe> employes;

    public List<Employe> getEmployers() {
        return employes;
    }

    public void setEmployers(Employe e) {
        List<Employe> employes =new ArrayList<Employe>();
        this.employes.add(e);
        this.employes = employes;
    }

    private List<Employe> Employers=new ArrayList<Employe>() ;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }
 public String getLib() {
  return lib;
 }

 public void setLib(String lib) {
  this.lib = lib;
 }

}
