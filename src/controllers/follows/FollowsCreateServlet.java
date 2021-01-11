package controllers.follows;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follow;
import models.User;
import utils.DBUtil;

@WebServlet("/follows/create")
public class FollowsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FollowsCreateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Follow f = new Follow();

            f.setFollowUser((User)request.getSession().getAttribute("login_user"));

            User u = em.find(User.class, Integer.parseInt(request.getParameter("userId")));
            f.setFollowerUser(u);

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/users/show?id="+ u.getId());
        }
    }
}
