package com.example.library_management_system_ex.controller;

import com.example.library_management_system_ex.model.Book;
import com.example.library_management_system_ex.repository.BookRepository;
import com.example.library_management_system_ex.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/books")
    public String books(ModelMap modelMap){
        List<Book> all = bookRepository.findAll();
        modelMap.addAttribute("books", all);
        return "book";
    }
    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam("id") int id){
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/books/add")
    public String addBook(ModelMap modelMap){
        modelMap.addAttribute("book", new Book());
        modelMap.addAttribute("categories", categoryRepository.findAll());

        return "addBook";
    }

    @PostMapping("books/add")
    public String addBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/books";
    }

}
