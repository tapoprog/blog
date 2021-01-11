package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "blogs")
@NamedQueries({
    @NamedQuery(
            name = "getAllBlogs",
            query = "SELECT b FROM Blog AS b ORDER BY b.id DESC"
            ),
    @NamedQuery(
            name = "getBlogsCount",
            query = "SELECT COUNT(b) FROM Blog AS b"
            ),
    @NamedQuery(
            name = "getMyAllBlogs",
            query = "SELECT b FROM Blog AS b WHERE b.user = :user ORDER BY b.id DESC"
            ),
    @NamedQuery(
            name = "getMyBlogsCount",
            query = "SELECT COUNT(b) FROM Blog AS b WHERE b.user = :user"
            ),
    @NamedQuery(
            name = "getMyFollowBlogs",
            query = "SELECT b FROM Blog AS b WHERE EXISTS (SELECT 'found' FROM Follow AS f WHERE b.user = f.followerUser AND f.followUser = :login_user)"
            ),
    @NamedQuery(
            name = "getMyFollowBlogsCount",
            query = "SELECT COUNT(b) FROM Blog AS b WHERE EXISTS (SELECT 'found' FROM Follow AS f WHERE b.user = f.followerUser AND f.followUser = :login_user)"
            ),
    @NamedQuery(
            name = "getMyGoodBlogs",
            query = "SELECT b FROM Blog AS b WHERE EXISTS (SELECT 'found' FROM Good AS g WHERE b.id = g.goodBlogId AND g.goodUser = :user)"
            ),
    @NamedQuery(
            name = "getMyGoodBlogsCount",
            query = "SELECT COUNT(b) FROM Blog AS b WHERE EXISTS (SELECT 'found' FROM Good AS g WHERE b.id = g.goodBlogId AND g.goodUser = :user)"
            )
})
@Entity
public class Blog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "blog_date", nullable = false)
    private Date blog_date;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBlog_date() {
        return blog_date;
    }

    public void setBlog_date(Date blog_date) {
        this.blog_date = blog_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}