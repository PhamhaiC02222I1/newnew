package com.codegym.md4casequizz.service.tour;

import com.codegym.md4casequizz.model.Tour;
import com.codegym.md4casequizz.repository.ICompanyRepository;
import com.codegym.md4casequizz.repository.ITourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourServiceIMPL implements ITourService {

    @Autowired
    private ITourRepository tourRepository;

    public Iterable<Tour> findAll() {
        return tourRepository.findAll();
    }

    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    public void remove(Long id) {
tourRepository.deleteById(id);
    }
}
