package org.project.backend.service;

import lombok.RequiredArgsConstructor;
import org.project.backend.dto.MemberDTO;
import org.project.backend.exception.InvalidMemberDataException;
import org.project.backend.exception.MemberNotFoundException;
import org.project.backend.model.Member;
import org.project.backend.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberDTO> memberDTOs = new ArrayList<>();
        for (Member member : members) {
            memberDTOs.add(convertToDTO(member));
        }
        return memberDTOs;
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found"));
        return convertToDTO(member);
    }

    @Override
    public Member createMember(MemberDTO memberDTO) {
        if (memberDTO == null || memberDTO.getName() == null) {
            throw new InvalidMemberDataException("Invalid Member Data");
        }
        Member member = convertToEntity(memberDTO);
        return memberRepository.save(member);
    }

    @Override
    public MemberDTO updateMember(Long id, MemberDTO memberDetails) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found"));
        member = member.toBuilder()
                .name(memberDetails.getName())
                .build();
        return convertToDTO(memberRepository.save(member));
    }

    @Override
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found"));
        memberRepository.deleteById(id);
    }

    // 엔티티를 DTO로 변환
    private MemberDTO convertToDTO(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setName(member.getName());
        return dto;
    }

    // DTO를 엔티티로 변환
    private Member convertToEntity(MemberDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
    }
}
