package controllers.blogs;

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
import utils.DBUtil;


@WebServlet("/blogs/index")
public class BlogsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public BlogsIndexServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Blog> blogs = em.createNamedQuery("getAllBlogs", Blog.class)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long blogs_count = (long)em.createNamedQuery("getBlogsCount", Long.class)
                                     .getSingleResult();

        em.close();

        request.setAttribute("blogs", blogs);
        request.setAttribute("blogs_count", blogs_count);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/blogs/index.jsp");
        rd.forward(request, response);
    }

}