package controllers.follows;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FollowShowServlet
 */
@WebServlet("/follows/show")
public class FollowShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();


        Employee employee = em.find(Employee.class, Integer.parseInt(request.getParameter("employee_id")));

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }

        List<Report> reports = em.createNamedQuery("getMyAllReports", Report.class)
                                 .setParameter("employee", employee)
                                 .setFirstResult(15 * (page - 1))
                                 .setMaxResults(15)
                                 .getResultList();

        long reports_count = (long)em.createNamedQuery("getMyReportsCount",Long.class)
                                       .setParameter("employee", employee)
                                       .getSingleResult();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        long follows_count = (long)em.createNamedQuery("getFollowsCount", Long.class )
                                        .setParameter("employee", employee)
                                        .getSingleResult();

        long follower_count = (long)em.createNamedQuery("getFollowerCount", Long.class )
                                        .setParameter("employee", employee)
                                        .getSingleResult();

        try{
            Follow f = (Follow)em.createNamedQuery("checkFollow_id", Follow.class)
                                                        .setParameter("user_id", login_employee)
                                                        .setParameter("follow_id", employee)
                                                        .getSingleResult();
            request.setAttribute("follow", f);

        }catch(NoResultException e){

        }


        em.close();

        request.setAttribute("employee", employee);
        request.setAttribute("reports", reports);
        request.setAttribute("reports_count", reports_count);
        request.setAttribute("follows_count", follows_count);
        request.setAttribute("follower_count", follower_count);
        request.setAttribute("page", page);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/show.jsp");
        rd.forward(request, response);
    }

}
