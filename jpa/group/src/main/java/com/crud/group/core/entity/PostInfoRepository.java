package com.crud.group.core.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostInfoRepository extends JpaRepository<PostInfo, Long> {
}
