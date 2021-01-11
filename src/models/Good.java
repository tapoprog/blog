package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "goods")
@NamedQueries({
    @NamedQuery(
            name = "getMyGood",
            query = "SELECT g FROM Good AS g WHERE g.goodUser = :goodUser and g.goodBlogId = :goodBlogId"
        )
})

@Entity
public class Good {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "good_user")
    private User goodUser;

    @Column(name = "good_blog_id")
    private Integer goodBlogId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getGoodUser() {
        return goodUser;
    }

    public void setGoodUser(User goodUser) {
        this.goodUser = goodUser;
    }

    public Integer getGoodBlogId() {
        return goodBlogId;
    }

    public void setGoodBlogId(Integer goodBlogId) {
        this.goodBlogId = goodBlogId;
    }
}
