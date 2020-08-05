package vn.kien.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "listen")
public class Listen {
    @Id
    @Column(name = "listen_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listenId;

    public Integer getListenId() {
        return listenId;
    }

    public void setListenId(final Integer listenId) {
        this.listenId = listenId;
    }

    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Column(name = "image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
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

    @Column(name = "modified_date")
    private Timestamp modifiedDate;

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(final Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @OneToMany(mappedBy = "listen", fetch = FetchType.LAZY)
    private List<Comment> commentList;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
