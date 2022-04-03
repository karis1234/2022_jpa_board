package com.karis.exam2.user.dao;

import com.karis.exam2.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    // 가져올게 1가지면 Optional로 감싼다.
}