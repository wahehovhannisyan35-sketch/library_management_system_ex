package com.example.library_management_system_ex.controller;


import com.example.library_management_system_ex.model.Category;
import com.example.library_management_system_ex.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "category";
    }

    @GetMapping("/categories/add")
    public String addCategory(ModelMap modelMap) {
        modelMap.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("categories/add")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("category/delete")
    public String deleteCategory(@RequestParam("id") int id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
