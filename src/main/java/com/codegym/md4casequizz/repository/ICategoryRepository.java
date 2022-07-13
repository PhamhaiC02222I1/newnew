package com.codegym.md4casequizz.repository;

import com.codegym.md4casequizz.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
Boolean existsByName(String name);
Page<Category> findAllByNameContaining(String name, Pageable pageable);

}
