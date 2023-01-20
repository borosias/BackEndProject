package com.example.backendproject.data;

import com.example.backendproject.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface RoleRepository
        extends PagingAndSortingRepository<Role, Long> {
    Optional<Role> findByName(String name);

    void deleteByName(String name);

    Collection<Role> findByRankGreaterThan(int rank);

    boolean existsByName(String name);

    Role save(Role admin);
}
