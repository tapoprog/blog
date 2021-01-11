package controllers.blogs;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Blog;


@WebServlet("/blogs/new")
public class BlogsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public BlogsNewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Blog b = new Blog();
        b.setBlog_date(new Date(System.currentTimeMillis()));
        request.setAttribute("blog", b);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/blogs/new.jsp");
        rd.forward(request, response);
    }

}
