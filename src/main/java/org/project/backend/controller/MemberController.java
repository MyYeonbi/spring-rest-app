package org.project.backend.controller;

import lombok.RequiredArgsConstructor;
import org.project.backend.dto.MemberDTO;
import org.project.backend.model.Member;
import org.project.backend.service.MemberServiceImpl;
import org.project.backend.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    // GET: 모든 회원 조회
    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<MemberDTO> members = memberServiceImpl.getAllMembers();
        return ResponseEntity.ok(members);
    }

    // GET: ID로 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        try {
            MemberDTO member = memberServiceImpl.getMemberById(id);
            return ResponseEntity.ok(member);
        } catch (MemberNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // 에러 시 null로 응답
        }
    }

    // POST: 회원 생성
    @PostMapping
    public ResponseEntity<Member> createMember(@Valid @RequestBody MemberDTO memberDTO) {
        try {
            Member createdMember = memberServiceImpl.createMember(memberDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // 내부 서버 오류 발생 시
        }
    }

    // PUT: 회원 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long id, @Valid @RequestBody MemberDTO memberDetails) {
        try {
            MemberDTO updatedMember = memberServiceImpl.updateMember(id, memberDetails);
            return ResponseEntity.ok(updatedMember);
        } catch (MemberNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    // DELETE: 회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        try {
            memberServiceImpl.deleteMember(id);
            return ResponseEntity.noContent().build();
        } catch (MemberNotFoundException e) {
            // MemberNotFoundException 처리
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
        }
    }

}
