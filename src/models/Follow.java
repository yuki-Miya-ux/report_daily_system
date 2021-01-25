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
            name = "getAllMyFollow_id",
            query = "SELECT f FROM Follow AS f WHERE f.user_id = :user_id ORDER BY f.id DESC"
            ),
    @NamedQuery(
            name = "getFollowsCount",
            query = "SELECT COUNT(f) FROM Follow AS f WHERE f.user_id = :employee"
            ),
    @NamedQuery(
            name = "getFollowerCount",
            query = "SELECT COUNT(f) FROM Follow AS f WHERE f.follow_id = :employee"
            ),
    @NamedQuery(
            name = "getFollow_id",
            query = "SELECT f FROM Follow AS f WHERE f.user_id = :user_id AND f.follow_id = :follow_id"
            )

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

    public Employee getUser_id() {
        return user_id;
    }

    public void setUser_id(Employee user_id) {
        this.user_id = user_id;
    }

    public Employee getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(Employee follow_id) {
        this.follow_id = follow_id;
    }





}
