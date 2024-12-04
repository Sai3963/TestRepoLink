package com.example.bid.repository;

import com.example.bid.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    Optional<Object> findByEmail(String username);
}
