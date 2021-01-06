package com.kye.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kye.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
