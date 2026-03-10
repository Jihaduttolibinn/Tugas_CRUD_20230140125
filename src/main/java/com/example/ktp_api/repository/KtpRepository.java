package com.example.ktp_api.repository;

import com.example.ktp_api.entity.KtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KtpRepository extends JpaRepository<KtpEntity, Long> {
    Optional<KtpEntity> findByNomorKtp(String nomorKtp);
    boolean existsByNomorKtp(String nomorKtp);
}
