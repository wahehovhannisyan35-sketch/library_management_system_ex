package com.example.library_management_system_ex.controller;


import com.example.library_management_system_ex.model.Member;
import com.example.library_management_system_ex.service.BookService;
import com.example.library_management_system_ex.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BookService bookService;

    @GetMapping("/members")
    public String members(ModelMap modelMap){
        modelMap.addAttribute("members", memberService.findAll());
        modelMap.addAttribute("books", bookService.findAll());
        return "member";
    }
    @GetMapping("/members/delete")
    public String deleteMember(@RequestParam("id") int id){
        memberService.deleteById(id);
        return "redirect:/members";
    }

    @GetMapping("/members/add")
    public String addMember(ModelMap modelMap){
        modelMap.addAttribute("member", new Member());
        return "addMember";
    }

    @PostMapping("members/add")
    public String addMember(@ModelAttribute Member member,@RequestParam ("pic") MultipartFile multipartFile){
       member.setRegistrationDate(LocalDateTime.now());
       memberService.save(member, multipartFile);
        return "redirect:/members";
    }

    @PostMapping("/members/borrow")
    public String borrowBook(@RequestParam("memberId") int memberId, @RequestParam("bookId") int bookId) {
        memberService.findById(memberId).ifPresent(member -> {
          bookService.findById(bookId).ifPresent(book -> {
              member.getBorrowedBooks().add(book);
              memberService.save(member);
          });
        });
        return "redirect:/members";
    }
@GetMapping("/members/details")
    public String memberDetails(@RequestParam("id") int id, ModelMap modelMap) {
    Member member=memberService.findById(id).orElseThrow(()->new RuntimeException("Member not found"));
    modelMap.addAttribute("member", member);
    modelMap.addAttribute("books", member.getBorrowedBooks());
return "memberDetails";
    }
}
