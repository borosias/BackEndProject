package com.example.backendproject.data;

import com.example.backendproject.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User getByUsername(String username);

    boolean existsByUsername(String username);
}
