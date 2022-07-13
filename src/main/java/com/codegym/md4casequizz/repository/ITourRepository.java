package com.codegym.md4casequizz.repository;

import com.codegym.md4casequizz.model.Product;
import com.codegym.md4casequizz.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITourRepository extends JpaRepository<Tour,Long> {
}
