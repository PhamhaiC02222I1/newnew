package com.codegym.md4casequizz.controller;

import com.codegym.md4casequizz.dto.response.ResponMessage;
import com.codegym.md4casequizz.model.Category;
import com.codegym.md4casequizz.model.Product;
import com.codegym.md4casequizz.model.Tour;
import com.codegym.md4casequizz.service.tour.TourServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("tour")
public class TourController {
    @Autowired
    TourServiceIMPL tourServiceIMPL;

    @GetMapping("/{id}")
    public ResponseEntity<?> detailProduct(@PathVariable Long id){
        Optional<Tour> tour=tourServiceIMPL.findById(id);
        if (!tour.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tour,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        Optional<Tour> productOptional = tourServiceIMPL.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tourServiceIMPL.remove(productOptional.get().getId());
        return new ResponseEntity<>(new ResponMessage("delete success"), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody Tour tour) {

        tourServiceIMPL.save(tour);
        return new ResponseEntity<>(new ResponMessage("create success"), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> pageProduct(@PageableDefault(sort = "name",direction = Sort.Direction.ASC) Pageable pageable){
        Page<Tour> productPage=tourServiceIMPL.findAll(pageable);
        if (productPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,@RequestBody Tour tour){
        Optional<Tour> category1=tourServiceIMPL.findById(id);
        if (!category1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (tourServiceIMPL.existsByName(tour.getName())){
            return new ResponseEntity<>(new ResponMessage("tour name is existed"),HttpStatus.OK);
        }

        category1.get().setName(tour.getName());
        category1.get().setPrice(tour.getPrice());
        category1.get().setDesciption(tour.getDesciption());
        tourServiceIMPL.save(category1.get());
        return new ResponseEntity<>(new ResponMessage("update success"),HttpStatus.OK);
    }
}
