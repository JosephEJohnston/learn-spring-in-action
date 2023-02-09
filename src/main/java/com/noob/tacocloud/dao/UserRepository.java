package com.noob.tacocloud.dao;

import com.noob.tacocloud.model.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
