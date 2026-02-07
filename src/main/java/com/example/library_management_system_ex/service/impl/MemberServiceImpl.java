package com.example.library_management_system_ex.service.impl;

import com.example.library_management_system_ex.model.Member;
import com.example.library_management_system_ex.repository.MemberRepository;
import com.example.library_management_system_ex.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Value("${library.management.image.directory.path}")
    private String imageDirectoryPath;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member save(Member member, MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageDirectoryPath + fileName);
            try {
                multipartFile.transferTo(file);
                member.setPictureName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return memberRepository.save(member);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteById(Integer id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Optional<Member> findById(int id) {
        return memberRepository.findById(id);
    }
}
