package com.example.library_management_system_ex.service;

import com.example.library_management_system_ex.model.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    List<Member> findAll();

    Member save (Member member, MultipartFile multipartFile);

    Member save(Member member);

    void deleteById (Integer id);

    Optional<Member> findById(int id);

}
