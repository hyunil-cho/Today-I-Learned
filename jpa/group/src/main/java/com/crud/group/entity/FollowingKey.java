package com.crud.group.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FollowingKey implements Serializable {

    @Column(name = "user_id")
    private int personId;

    @Column(name = "followed_user_id")
    private int targetId;


    public FollowingKey(int targetId, int personId) {
        this.targetId = targetId;
        this.personId = personId;
    }


    public int getTargetId() {
        return targetId;
    }

    public int getPersonId() {
        return personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowingKey that = (FollowingKey) o;
        return targetId == that.targetId && personId == that.personId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetId, personId);
    }
}
