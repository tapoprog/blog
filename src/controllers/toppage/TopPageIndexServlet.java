package controllers.toppage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Blog;
import models.User;
import utils.DBUtil;


@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;


    public TopPageIndexServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User login_user = (User)request.getSession().getAttribute("login_user");

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception u) {
            page = 1;
        }
        List<Blog> blogs = em.createNamedQuery("getMyFollowBlogs", Blog.class)
                                  .setParameter("login_user", login_user)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long blogs_count = (long)em.createNamedQuery("getMyFollowBlogsCount", Long.class)
                                       .setParameter("login_user", login_user)
                                       .getSingleResult();

        em.close();

        request.setAttribute("blogs", blogs);
        request.setAttribute("blogs_count", blogs_count);
        request.setAttribute("page", page);
        request.setAttribute("login_user", login_user);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }

}