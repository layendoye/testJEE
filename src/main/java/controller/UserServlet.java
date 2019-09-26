package controller;

import model.User;
import service.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="user", urlPatterns="/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().println("Bonjour Abdoulaye");
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login=req.getParameter("login");
        String password=req.getParameter("password");
        UserDao userDao=new UserDao();
        User user=userDao.logon(login,password);
        if(user!=null){
            HttpSession session=req.getSession(true);//si la session n existe pas il la cree
            session.setAttribute("connectedUser",user);
            if(user.getProfil().equalsIgnoreCase("user")){
                getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req,resp);
            }
            else {
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(req,resp);
            }
        }
        else{
            req.setAttribute("error","Login ou password");
            getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req,resp);
        }
    }
}
