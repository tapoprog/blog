package controllers.users;

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

import models.Blog;
import models.Follow;
import models.User;
import utils.DBUtil;


@WebServlet("/users/show")
public class UsersShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UsersShowServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        request.setAttribute("_token", request.getSession().getId());

        User u = em.find(User.class, Integer.parseInt(request.getParameter("id")));

        User my = (User)request.getSession().getAttribute("login_user");

        try{
            Follow follows = (Follow)em.createNamedQuery("getMyFollow", Follow.class)
                                       .setParameter("followUser", my)
                                       .setParameter("followerUser", u)
                                       .getSingleResult();

             request.setAttribute("follows",follows);
        }
        catch (NoResultException e){
            System.out.println("0件です");
        }

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e2) {
            page = 1;
        }

        List<Blog> blogs = em.createNamedQuery("getMyAllBlogs", Blog.class)
                                  .setParameter("user", u)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long blogs_count = (long)em.createNamedQuery("getMyBlogsCount", Long.class)
                                     .setParameter("user", u)
                                     .getSingleResult();

        em.close();

        request.setAttribute("blogs", blogs);
        request.setAttribute("blogs_count", blogs_count);
        request.setAttribute("page", page);
        request.setAttribute("user", u);
        request.setAttribute("loginUser", my);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/show.jsp");
        rd.forward(request, response);
    }

}
