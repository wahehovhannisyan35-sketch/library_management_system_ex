package com.example.library_management_system_ex.service;

import com.example.library_management_system_ex.model.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {

    List<Book> findAll();

    List<Book> findBooksByCategoryId(int categoryId);

    Book save(Book book);

    Optional<Book> findById(Integer id);

    List<Book> findAllByTitleContainingIgnoreCase(String search);

    void deleteById(Integer id);



}
