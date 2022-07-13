package com.codegym.md4casequizz.controller;

import com.codegym.md4casequizz.dto.response.ResponMessage;
import com.codegym.md4casequizz.model.Company;
import com.codegym.md4casequizz.service.company.CompanyServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("company")
public class CompanyController {
    @Autowired
    CompanyServiceIMPL companyServiceIMPL;

    @GetMapping
    public ResponseEntity<?> pageCompany(@PageableDefault(sort = "companyName",direction = Sort.Direction.ASC) Pageable pageable){
        Page<Company> companyPage=companyServiceIMPL.findAll(pageable);
        if (companyPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companyPage,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody Company company){
        company.setEstablishmentDate(new Date());
        if (company.getCompanyName()==null){
            return new ResponseEntity<>(new ResponMessage("no name company"),HttpStatus.OK);
        }
//        else if (companyServiceIMPL.existsByCompanyName(company.getCompanyName())){
//            return new ResponseEntity<>(new ResponMessage("name company is existed!Please try again"),HttpStatus.OK);
//
//        }
        if (company.getWebsite()==null){
            return new ResponseEntity<>(new ResponMessage("no website company"),HttpStatus.OK);
        }
        else if (companyServiceIMPL.existsByWebsite(company.getWebsite())){
            return new ResponseEntity<>(new ResponMessage("website company is existed!Please try again"),HttpStatus.OK);

        }
        if (company.getImage()==null){
            return new ResponseEntity<>(new ResponMessage("no image company"),HttpStatus.OK);
        }
        if (company.getEstablishmentDate()==null){
            return new ResponseEntity<>(new ResponMessage("no Establishment_date company"),HttpStatus.OK);
        }
        companyServiceIMPL.save(company);
        return new ResponseEntity<>(new ResponMessage("create company success!!!"),HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchByNameCompanyContaining(@RequestParam ("companyName") String search,@PageableDefault(sort = "companyName",direction = Sort.Direction.ASC)Pageable pageable){
        Page<Company> companyPage=companyServiceIMPL.findAllByCompanyNameContaining(search,pageable);
        if (companyPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companyPage,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        Optional<Company> companyPage = companyServiceIMPL.findById(id);
        if (!companyPage.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyServiceIMPL.remove(companyPage.get().getId());
        return new ResponseEntity<>(new ResponMessage("delete success"), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailCompany(@PathVariable Long id){
        Optional<Company> companyPage=companyServiceIMPL.findById(id);
        if (!companyPage.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companyPage,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id,@RequestBody Company company){
        Optional<Company> companyPage=companyServiceIMPL.findById(id);
        if (!companyPage.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (companyServiceIMPL.existsByCompanyName(company.getCompanyName())){
            return new ResponseEntity<>(new ResponMessage("product name is existed"),HttpStatus.OK);
        }
        if (companyServiceIMPL.existsByWebsite(company.getWebsite())) {
            return new ResponseEntity<>(new ResponMessage("website company is existed!Please try again"), HttpStatus.OK);
        }

        companyPage.get().setCompanyName(company.getCompanyName());
        companyPage.get().setDescription(company.getDescription());
        companyPage.get().setEstablishmentDate(company.getEstablishmentDate());
        companyPage.get().setWebsite(company.getWebsite());
        companyPage.get().setImage(company.getImage());
        companyServiceIMPL.save(companyPage.get());
        return new ResponseEntity<>(new ResponMessage("update success"),HttpStatus.OK);
    }
}
