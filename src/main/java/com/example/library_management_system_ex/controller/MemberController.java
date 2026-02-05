package com.example.library_management_system_ex.controller;


import com.example.library_management_system_ex.model.Book;
import com.example.library_management_system_ex.model.Member;
import com.example.library_management_system_ex.repository.BookRepository;
import com.example.library_management_system_ex.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @GetMapping("/members")
    public String members(ModelMap modelMap){
        modelMap.addAttribute("members", memberRepository.findAll());
        modelMap.addAttribute("books", bookRepository.findAll());
        return "member";
    }
    @GetMapping("/members/delete")
    public String deleteMember(@RequestParam("id") int id){
        memberRepository.deleteById(id);
        return "redirect:/members";
    }

    @GetMapping("/members/add")
    public String addMember(ModelMap modelMap){
        modelMap.addAttribute("member", new Member());
        return "addMember";
    }

    @PostMapping("members/add")
    public String addMember(@ModelAttribute Member member){
       member.setRegistrationDate(LocalDateTime.now());
       memberRepository.save(member);
        return "redirect:/members";
    }

    @PostMapping("/members/borrow")
    public String borrowBook(@RequestParam("memberId") int memberId, @RequestParam("bookId") int bookId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();
        member.getBorrowedBooks().add(book);
        memberRepository.save(member);
        return "redirect:/members";
    }
@GetMapping("/members/details")
    public String memberDetails(@RequestParam("id") int id, ModelMap modelMap) {
    Member member=memberRepository.findById(id).orElseThrow(()->new RuntimeException("Member not found"));
    modelMap.addAttribute("member", member);
    modelMap.addAttribute("books", member.getBorrowedBooks());
return "memberDetails";
    }
}
