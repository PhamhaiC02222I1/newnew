package com.codegym.md4casequizz.service.company;

import com.codegym.md4casequizz.model.Company;
import com.codegym.md4casequizz.model.User;
import com.codegym.md4casequizz.repository.ICompanyRepository;
import com.codegym.md4casequizz.security.userprincal.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceIMPL implements ICompanyService {
    @Autowired
    private ICompanyRepository companyRepository;
    @Autowired
    UserDetailService userDetailService;

    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company save(Company company) {
        User user = userDetailService.getCurrentUser();
        company.setUser(user);
        return companyRepository.save(company);
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public void remove(Long id) {
        companyRepository.deleteById(id);
    }

    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Boolean existsByCompanyName(String companyName) {
        return companyRepository.existsByCompanyName(companyName);
    }

    public Page<Company> findAllByCompanyNameContaining(String companyName, Pageable pageable) {
        return companyRepository.findAllByCompanyNameContaining(companyName, pageable);
    }

    public Boolean existsByWebsite(String website) {
        return companyRepository.existsByWebsite(website);
    }
}
