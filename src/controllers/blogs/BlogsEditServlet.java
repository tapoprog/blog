package controllers.blogs;

import java.io.IOException;

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


@WebServlet("/blogs/edit")
public class BlogsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public BlogsEditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Blog b = em.find(Blog.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        User login_user = (User)request.getSession().getAttribute("login_user");
        if(b != null && login_user.getId() == b.getUser().getId()) {
            request.setAttribute("blog", b);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("blog_id", b.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/blogs/edit.jsp");
        rd.forward(request, response);
    }

}
