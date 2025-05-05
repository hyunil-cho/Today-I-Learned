package com.crud.group.core.entity;

import com.crud.group.core.entity.base.UpdateTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "posts", indexes = {
        @Index(name="author_info",columnList = "author.id")
})
public class PostInfo extends UpdateTimeEntity {
    @Override
    public String toString() {
        return "PostInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author=" + author +
                ", status=" + status +
                "} " + super.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 30)
    private String title;
    private String body;

    @ManyToOne
    private Users author;

    @Enumerated(EnumType.STRING)
    private PostStatus status;


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }
}
