package org.project.backend.com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.project.backend.com.example.model.Inquiry;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}