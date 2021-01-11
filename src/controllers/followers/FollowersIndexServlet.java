package controllers.followers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follow;
import models.User;
import utils.DBUtil;


@WebServlet("/followers/index")
public class FollowersIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public FollowersIndexServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User u = em.find(User.class, Integer.parseInt(request.getParameter("id")));

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Follow> followers = em.createNamedQuery("getMyAllFollowers", Follow.class)
                    .setParameter("followerUser",u)
                    .setFirstResult(15 * (page - 1))
                    .setMaxResults(15)
                    .getResultList();

        long followers_count = (long)em.createNamedQuery("getMyFollowersCount", Long.class)
                                    .setParameter("followerUser", u)
                                    .getSingleResult();

        em.close();

        request.setAttribute("user",u);
        request.setAttribute("followers", followers);
        request.setAttribute("followers_count", followers_count);
        request.setAttribute("page", page);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/followers/index.jsp");
        rd.forward(request, response);
    }

}
