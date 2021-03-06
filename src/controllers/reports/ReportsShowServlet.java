package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Favorite;
import models.Follow;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        Employee employee = r.getEmployee();

        try{
            Follow f = em.createNamedQuery("checkFollow_id", Follow.class)
                                                        .setParameter("user_id", login_employee)
                                                        .setParameter("follow_id", employee)
                                                        .getSingleResult();
            request.setAttribute("follow", f);

        }catch(NoResultException e){

        }


        try{
            Favorite fav = em.createNamedQuery("checkFavorites", Favorite.class)
                                                        .setParameter("user_id", login_employee)
                                                        .setParameter("report_id", r)
                                                        .getSingleResult();
            request.setAttribute("favorite", fav);

        }catch(NoResultException e){

        }

        long favorites_count = (long)em.createNamedQuery("getFavoritesCount", Long.class)
                                            .setParameter("report_id", r)
                                            .getSingleResult();



        em.close();

        request.setAttribute("report", r);
        request.setAttribute("employee", employee);
        request.setAttribute("favorites_count", favorites_count);
        request.setAttribute("_token", request.getSession().getId());


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);

    }

}
