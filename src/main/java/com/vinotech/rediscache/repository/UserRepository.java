package com.vinotech.rediscache.repository;

import com.vinotech.rediscache.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}