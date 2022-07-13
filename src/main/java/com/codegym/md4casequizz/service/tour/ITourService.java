package com.codegym.md4casequizz.service.tour;

import com.codegym.md4casequizz.model.Category;
import com.codegym.md4casequizz.model.Company;
import com.codegym.md4casequizz.model.Tour;
import com.codegym.md4casequizz.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITourService extends IGeneralService<Tour> {
    Page<Tour> findAll(Pageable pageable);
    Boolean existsByName(String name);

}
