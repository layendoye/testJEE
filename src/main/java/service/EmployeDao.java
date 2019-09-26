package service;

import model.Employe;
import model.Service;
import model.User;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeDao {
    public List<Service> findServices(){
        List<Service> services=new ArrayList<Service>();
        try{
            String sql="SELECT * FROM service";
            DatabaseHelper db=DatabaseHelper.getInstance();
            ResultSet rs=db.My_ExecuteQuery(sql);
            while(rs.next()){
                Service service=new Service();
                service.setId(rs.getInt(1));
                service.setLib(rs.getString(2));
                services.add(service);
            }
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return services;
    }
    public void add(Employe employe) throws Exception {
        try{
            String sql= "INSERT INTO Employe VALUES (NULL,?,?,?,?,?,?)";
            DatabaseHelper bdd=DatabaseHelper.getInstance();
            bdd.iniPreparedCmd(sql);
            bdd.getPstmt().setString(1,employe.getMatricule());
            bdd.getPstmt().setString(2,employe.getNom());
            bdd.getPstmt().setString(3,employe.getTelephone());
            bdd.getPstmt().setString(4,employe.getNaissance().toString());
            bdd.getPstmt().setInt(5,employe.getSalaire());
            bdd.getPstmt().setInt(6,employe.getService().getId());
            bdd.My_ExecutePrepareUpdate();

        }catch (Exception ex){
            throw ex;
        }
    }
    public List<Employe> findEmploye(){
        List<Employe> employes=new ArrayList<Employe>();
        try{
            String sql="SELECT * FROM Employe e,service s WHERE e.id_service=s.id";
            DatabaseHelper db=DatabaseHelper.getInstance();
            ResultSet rs=db.My_ExecuteQuery(sql);
            boolean r=rs.next();
            while(rs.next()){
                Employe employe=new Employe();
                employe.setId(rs.getInt(1));
                employe.setMatricule(rs.getString(2));
                employe.setNom(rs.getString(3));
                employe.setTelephone(rs.getString(4));

                DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate naissance=LocalDate.parse(rs.getString(5),df);

                employe.setNaissance(naissance);
                employe.setSalaire(rs.getInt(6));
                Service service=new Service();
                service.setId(rs.getInt(8));
                service.setLib(rs.getString(9));
                employe.setService(service);
                employes.add(employe);
            }
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return employes;
    }
}
