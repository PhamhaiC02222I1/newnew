package com.codegym.md4casequizz.repository;

import com.codegym.md4casequizz.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends JpaRepository<Company,Long> {
    Boolean existsByCompanyName(String companyName);
    Page<Company> findAllByCompanyNameContaining(String companyName, Pageable pageable);
    Boolean existsByWebsite(String website);
}
