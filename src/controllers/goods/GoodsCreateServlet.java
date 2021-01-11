package controllers.goods;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Blog;
import models.Good;
import models.User;
import utils.DBUtil;

@WebServlet("/goods/create")
public class GoodsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GoodsCreateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Good g = new Good();

            g.setGoodUser((User)request.getSession().getAttribute("login_user"));

            Blog a = em.find(Blog.class, Integer.parseInt(request.getParameter("blogId")));
            Integer b = Integer.parseInt(request.getParameter("blogId"));
            g.setGoodBlogId(b);

            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/blogs/show?id="+ a.getId());
        }
    }

}
