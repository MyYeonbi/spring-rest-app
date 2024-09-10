package org.project.backend.com.example.controller;
import org.project.backend.com.example.model.Inquiry;
import org.project.backend.com.example.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {
    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    // 문의사항 생성 (Create)
    @PostMapping
    public ResponseEntity<Inquiry> createInquiry(@RequestBody Inquiry inquiry) {
        Inquiry createdInquiry = inquiryService.createInquiry(inquiry);
        return ResponseEntity.ok(createdInquiry);
    }

    // 모든 문의사항 조회 (Read)
    @GetMapping
    public ResponseEntity<List<Inquiry>> getAllInquiries() {
        List<Inquiry> inquiries = inquiryService.getAllInquiries();
        return ResponseEntity.ok(inquiries);
    }

    // ID로 문의사항 조회 (Read)
    @GetMapping("/{id}")
    public ResponseEntity<Inquiry> getInquiryById(@PathVariable Long id) {
        Inquiry inquiry = inquiryService.getInquiryById(id);
        return inquiry != null ? ResponseEntity.ok(inquiry) : ResponseEntity.notFound().build();
    }

    // 문의사항 수정 (Update)
    @PutMapping("/{id}")
    public ResponseEntity<Inquiry> updateInquiry(@PathVariable Long id, @RequestBody Inquiry inquiryDetails) {
        Inquiry updatedInquiry = inquiryService.updateInquiry(id, inquiryDetails);
        return updatedInquiry != null ? ResponseEntity.ok(updatedInquiry) : ResponseEntity.notFound().build();
    }

    // 문의사항 삭제 (Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable Long id) {
        inquiryService.deleteInquiry(id);
        return ResponseEntity.noContent().build();
    }
}
