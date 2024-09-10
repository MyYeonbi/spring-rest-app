package org.project.backend.com.example.service;
import org.project.backend.com.example.model.Inquiry;


import org.project.backend.com.example.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    @Autowired
    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    public Inquiry createInquiry(Inquiry inquiry) {
        return inquiryRepository.save(inquiry);
    }


    public List<Inquiry> getAllInquiries() {
        return inquiryRepository.findAll();
    }

    public Inquiry getInquiryById(Long id) {
        return inquiryRepository.findById(id).orElse(null);
    }

    public Inquiry updateInquiry(Long id, Inquiry inquiryDetails) {
        Inquiry inquiry = getInquiryById(id);
        if (inquiry != null) {
            inquiry.setTitle(inquiryDetails.getTitle());
            inquiry.setContent(inquiryDetails.getContent());
            return inquiryRepository.save(inquiry);
        }
        return null;
    }

    public void deleteInquiry(Long id) {
        inquiryRepository.deleteById(id);
    }
}
