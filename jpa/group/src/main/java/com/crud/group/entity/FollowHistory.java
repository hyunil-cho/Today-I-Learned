package com.crud.group.entity;

import com.crud.group.entity.base.CreateTimeEntity;
import jakarta.persistence.*;

@Entity
public class FollowHistory extends CreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Users userId;

    @ManyToOne
    private Users followedId;

    @Enumerated(EnumType.STRING)
    private FollowingCmd cmd;


}
