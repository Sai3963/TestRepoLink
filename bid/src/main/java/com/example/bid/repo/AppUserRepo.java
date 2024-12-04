package com.example.bid.repo;

import com.example.bid.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {

    @Override
    Optional<AppUser> findById(Long aLong);

    boolean existsByEmail(String email);

    Optional<AppUser> findByEmail(String email);
}

