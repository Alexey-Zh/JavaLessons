package com.alexeyzh.models.repos;

import com.alexeyzh.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface DbUser extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    List<User> findByIsAdmin(boolean v);
}