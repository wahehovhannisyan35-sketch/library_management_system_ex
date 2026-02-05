package com.example.library_management_system_ex.repository;

import com.example.library_management_system_ex.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
