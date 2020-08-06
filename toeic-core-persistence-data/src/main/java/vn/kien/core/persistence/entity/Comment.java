package vn.kien.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(final Integer commentId) {
        this.commentId = commentId;
    }

    @Column(name = "content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @Column(name = "created_date")
    private Timestamp createdDate;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @ManyToOne
    @JoinColumn(name = "listen_id")
    private Listen listen;

    public Listen getListen() {
        return listen;
    }

    public void setListen(Listen listen) {
        this.listen = listen;
    }
}
