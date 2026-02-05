package com.example.library_management_system_ex.repository;


import com.example.library_management_system_ex.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
