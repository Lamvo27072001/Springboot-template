package com.ecommerce.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.website.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
