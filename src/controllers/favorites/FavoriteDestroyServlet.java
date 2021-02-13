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
 * Servlet implementation class FavoriteDestroyServlet
 */
@WebServlet("/favorites/destroy")
public class FavoriteDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteDestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();
            Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
            Report r_id = em.find(Report.class, Integer.parseInt(request.getParameter("report_id")));
            Favorite fav = em.createNamedQuery("checkFavorites", Favorite.class)
                    .setParameter("user_id", login_employee)
                    .setParameter("report_id", r_id)
                    .getSingleResult();

            em.getTransaction().begin();
            em.remove(fav);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/reports/index");
        }
    }

}
