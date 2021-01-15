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
            name = "getAllFollows",
            query = "SELECT f FROM Follow AS f ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "getFollowsCount",
            query = "SELECT COUNT(f) FROM Follow AS f"
            ),
})


@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Employee user_id;

    @ManyToOne
    @JoinColumn(name = "follow_id", nullable = false)
    private Employee follow_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getUser() {
        return user_id;
    }

    public void setUser(Employee user_id) {
        this.user_id = user_id;
    }

    public Employee getFollow() {
        return follow_id;
    }

    public void setFollow(Employee follow_id) {
        this.follow_id = follow_id;
    }



}
