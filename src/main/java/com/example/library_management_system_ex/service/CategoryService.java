package com.example.library_management_system_ex.service;

import com.example.library_management_system_ex.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category save(Category category);

    void deleteById(Integer id);

    Category getOne(Integer id);

}
