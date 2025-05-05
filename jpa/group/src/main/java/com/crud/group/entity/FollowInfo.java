package com.crud.group.entity;

import com.crud.group.entity.base.CreateTimeEntity;
import jakarta.persistence.*;

@Entity
public class FollowInfo extends CreateTimeEntity {

    @EmbeddedId
    private FollowingKey key;


}
