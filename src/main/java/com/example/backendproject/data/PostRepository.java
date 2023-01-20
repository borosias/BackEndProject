package com.example.backendproject.data;

import com.example.backendproject.domain.Post;
import com.example.backendproject.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    void deleteByCreator(User creator);
    List<Post> findAllByCreator(User creator);
}

