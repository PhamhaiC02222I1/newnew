package com.codegym.md4casequizz.service.company;

import com.codegym.md4casequizz.model.Company;
import com.codegym.md4casequizz.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICompanyService extends IGeneralService<Company> {
    Page<Company> findAll(Pageable pageable);
    Boolean existsByCompanyName(String companyName);
    Page<Company> findAllByCompanyNameContaining(String companyName, Pageable pageable);
    Boolean existsByWebsite(String website);

}
