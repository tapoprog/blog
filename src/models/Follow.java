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

@Table(name = "follows")
@NamedQueries({
    @NamedQuery(
            name = "getMyFollow",
            query = "SELECT f FROM Follow AS f WHERE f.followUser = :followUser and f.followerUser = :followerUser"
        ),
    @NamedQuery(
            name = "getMyAllFollows",
            query = "SELECT f FROM Follow AS f WHERE f.followUser = :followUser ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "getMyFollowsCount",
            query = "SELECT COUNT(f) FROM Follow AS f WHERE f.followUser = :followUser"
            ),
    @NamedQuery(
            name = "getMyAllFollowers",
            query = "SELECT f FROM Follow AS f WHERE f.followerUser = :followerUser ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "getMyFollowersCount",
            query = "SELECT COUNT(f) FROM Follow AS f WHERE f.followerUser = :followerUser"
            ),

})

@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follow_user_id")
    private User followUser;

    @ManyToOne
    @JoinColumn(name = "follower_user_id")
    private User followerUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getFollowUser() {
        return followUser;
    }

    public void setFollowUser(User followUser) {
        this.followUser = followUser;
    }

    public User getFollowerUser() {
        return followerUser;
    }

    public void setFollowerUser(User followerUser) {
        this.followerUser = followerUser;
    }



}
