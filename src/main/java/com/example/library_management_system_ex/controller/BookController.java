package com.example.library_management_system_ex.controller;

import com.example.library_management_system_ex.model.Book;
import com.example.library_management_system_ex.service.BookService;
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
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping("/books")
    public String books(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("books", bookService.findAll());
        return "book";
    }

    @GetMapping("/books/filter")
    public String filterBooks(@RequestParam("categoryId") int categoryId, ModelMap modelMap) {
        modelMap.addAttribute("books", bookService.findBooksByCategoryId(categoryId));
        modelMap.addAttribute("categories", categoryService.findAll());
        return "book";
     }

    @GetMapping("books/search")
    public String searchBooks(@RequestParam("search") String search, ModelMap modelMap){
        modelMap.addAttribute("books",bookService.findAllByTitleContainingIgnoreCase(search));
        return "book";
    }

    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/books/add")
    public String addBook(ModelMap modelMap) {
        modelMap.addAttribute("book", new Book());
        modelMap.addAttribute("categories", categoryService.findAll());

        return "addBook";
    }

    @PostMapping("books/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

}
