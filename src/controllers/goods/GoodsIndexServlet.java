package controllers.goods;

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

@WebServlet("/goods/index")
public class GoodsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GoodsIndexServlet() {
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
        List<Blog> blogs = em.createNamedQuery("getMyGoodBlogs", Blog.class)
                                  .setParameter("user", u)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long blogs_count = (long)em.createNamedQuery("getMyGoodBlogsCount", Long.class)
                                       .setParameter("user", u)
                                       .getSingleResult();

        em.close();

        request.setAttribute("user",u);
        request.setAttribute("blogs", blogs);
        request.setAttribute("blogs_count", blogs_count);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/goods/index.jsp");
        rd.forward(request, response);
    }


}
