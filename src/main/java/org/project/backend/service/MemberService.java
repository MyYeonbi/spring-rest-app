package org.project.backend.service;

import org.project.backend.dto.MemberDTO;
import org.project.backend.model.Member;

import java.util.List;

public interface MemberService {
    List<MemberDTO> getAllMembers();
    MemberDTO getMemberById(Long id);
    Member createMember(MemberDTO memberDTO);
    MemberDTO updateMember(Long id, MemberDTO memberDetails);
    void deleteMember(Long id);
}
