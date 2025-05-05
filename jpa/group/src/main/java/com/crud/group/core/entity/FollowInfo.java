package com.crud.group.core.entity;

import com.crud.group.core.entity.base.CreateTimeEntity;
import jakarta.persistence.*;

@Entity
public class FollowInfo extends CreateTimeEntity {

    @EmbeddedId
    private FollowingKey key;


}
