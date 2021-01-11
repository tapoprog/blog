package controllers.blogs;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Blog;
import models.Good;
import models.User;
import utils.DBUtil;


@WebServlet("/blogs/show")
public class BlogsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public BlogsShowServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        request.setAttribute("_token", request.getSession().getId());

        Blog b = em.find(Blog.class, Integer.parseInt(request.getParameter("id")));
        Integer i = Integer.parseInt(request.getParameter("id"));

        User my = (User)request.getSession().getAttribute("login_user");

        try{
            Good goods = (Good)em.createNamedQuery("getMyGood", Good.class)
                                       .setParameter("goodUser", my)
                                       .setParameter("goodBlogId", i)
                                       .getSingleResult();

             request.setAttribute("goods",goods);
        }
        catch (NoResultException e){
            System.out.println("0件です");
        }

        em.close();

        request.setAttribute("blog", b);
        request.setAttribute("login_user", my);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/blogs/show.jsp");
        rd.forward(request, response);
    }


}
