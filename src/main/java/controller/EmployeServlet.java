package controller;

        import model.Employe;
        import model.Service;
        import model.User;
        import service.EmployeDao;
        import service.UserDao;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;
        import java.time.LocalDate;
        import java.time.format.DateTimeFormatter;
        import java.util.List;

@WebServlet(name="employe", urlPatterns="/employe")
public class EmployeServlet extends HttpServlet {

    EmployeDao employeDao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employeDao=new EmployeDao();
        String action=req.getParameter("action");

        switch (action){
            case "add":
                req.setAttribute("employes",employeDao.findEmploye());
                List<Employe> employes=employeDao.findEmploye();
                req.setAttribute("services",employeDao.findServices());
                getServletContext().getRequestDispatcher("/WEB-INF/employe.jsp").forward(req,resp); break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action=req.getParameter("action");
        switch (action){
            case "add":
                String matricule=req.getParameter("matricule");
                String nom=req.getParameter("nom");
                DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate naissance=LocalDate.parse(req.getParameter("naissance"),df);
                String telephone=req.getParameter("tel");
                int salaire=Integer.parseInt(req.getParameter("salaire"));
                String var=req.getParameter("service");
                int id_service=Integer.parseInt(req.getParameter("service"));
                Service service=new Service();
                service.setId(id_service);
                Employe employe=new Employe(matricule,nom,telephone,naissance,salaire,service);
                try {
                    employeDao.add(employe);
                    req.setAttribute("employes",employeDao.findEmploye());
                } catch (Exception e) {
                    req.setAttribute("error","impossible d ajouter l employe");
                }

                getServletContext().getRequestDispatcher("/WEB-INF/employe.jsp").forward(req,resp); break;
        }
    }
}
