package com.crud.group.entity;

import com.crud.group.entity.base.UpdateTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "posts", indexes = {
        @Index(name="author_info",columnList = "author.id")
})
public class PostInfo extends UpdateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 30)
    private String title;
    private String body;

    @OneToOne
    private Users author;

    @Enumerated(EnumType.STRING)
    private PostStatus status;


}
