package com.mag.usermanagement.data.repository;

import com.mag.usermanagement.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
