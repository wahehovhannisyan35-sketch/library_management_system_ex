package com.example.library_management_system_ex.service.impl;

import com.example.library_management_system_ex.model.Book;
import com.example.library_management_system_ex.repository.BookRepository;
import com.example.library_management_system_ex.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BookServiceImpl implements BookService {


private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBooksByCategoryId(int categoryId) {
        return bookRepository.findBooksByCategoryId(categoryId);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAllByTitleContainingIgnoreCase(String search) {
        return bookRepository.findAllByTitleContainingIgnoreCase(search);
    }

    @Override
    public void deleteById(Integer id) {
    bookRepository.deleteById(id);
    }



}
