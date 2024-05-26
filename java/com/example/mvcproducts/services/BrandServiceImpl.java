package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Brand;
import com.example.mvcproducts.repositories.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAllSortedByName() {
        return brandRepository.findAllByOrderByName();
    }
}
