package com.example.library_management_system_ex.controller;


import com.example.library_management_system_ex.model.Category;
import com.example.library_management_system_ex.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class CategoryController {

    private  final CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String categories(ModelMap modelMap){
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "category";
    }
    @GetMapping("/categories/add")
    public String addCategory(ModelMap modelMap){
        modelMap.addAttribute("category", new Category());
        return "addCategory";
    }
    @PostMapping("categories/add")
    public String saveCategory(@ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/categories";
    }
}
