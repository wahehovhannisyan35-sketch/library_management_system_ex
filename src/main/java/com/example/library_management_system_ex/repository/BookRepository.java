package com.example.library_management_system_ex.repository;


import com.example.library_management_system_ex.model.Book;
import com.example.library_management_system_ex.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByCategoryId(int categoryId);

    List<Book> findAllByTitleContainingIgnoreCase(String title);
}
