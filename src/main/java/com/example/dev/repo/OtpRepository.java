package com.example.dev.repo;

import com.example.dev.entity.OtpCode;
import com.example.dev.entity.OtpStatus;
import com.example.dev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpCode, Long> {
    List<OtpCode> findByStatus(OtpStatus status);
    Optional<OtpCode> findTopByUserAndOperationIdAndCodeOrderByCreatedAtDesc(
            User user,
            Long operationId,
            String code
    );
}
