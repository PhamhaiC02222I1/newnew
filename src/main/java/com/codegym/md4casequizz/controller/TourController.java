package com.codegym.md4casequizz.controller;

import com.codegym.md4casequizz.dto.response.ResponMessage;
import com.codegym.md4casequizz.model.Category;
import com.codegym.md4casequizz.model.Product;
import com.codegym.md4casequizz.model.Tour;
import com.codegym.md4casequizz.service.tour.TourServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/tour")
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
    public ResponseEntity<?> listCategory() {
        Iterable<Tour> categories = tourServiceIMPL.findAll();
        if (categories != null) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
