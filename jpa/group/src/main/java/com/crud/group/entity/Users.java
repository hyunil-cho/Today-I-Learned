package com.crud.group.entity;

import com.crud.group.entity.base.UpdateTimeEntity;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Users extends UpdateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_name", length = 30, nullable = false)
    private String userName;

    @Column(name = "user_password", length = 30, nullable = false)
    private String password;


}
