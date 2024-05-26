package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAllSortedByName();
}
