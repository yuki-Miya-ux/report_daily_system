package controllers.favorites;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Favorite;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FavoriteCreateServlet
 */
@WebServlet("/favorites/create")
public class FavoriteCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Report r = em.find(Report.class, Integer.parseInt(request.getParameter("report_id")));

            Favorite fav = new Favorite();


            fav.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
            fav.setReport(r);

            em.getTransaction().begin();
            em.persist(fav);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/reports/index");
        }
    }

}
