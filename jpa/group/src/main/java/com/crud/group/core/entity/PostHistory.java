package com.crud.group.core.entity;

import com.crud.group.core.entity.base.CreateTimeEntity;
import jakarta.persistence.*;

@Entity
public class PostHistory extends CreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Users user;
    @ManyToOne
    private PostInfo post;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;
}
