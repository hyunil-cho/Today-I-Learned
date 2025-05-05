package com.crud.group.core.entity;

import com.crud.group.core.entity.base.UpdateTimeEntity;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class)
public class Users extends UpdateTimeEntity {


    public Users() {
    }

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_name", length = 30, nullable = false)
    private String userName;

    @Column(name = "user_password", length = 30, nullable = false)
    private String password;

    public long getId() {
        return id;
    }
}
