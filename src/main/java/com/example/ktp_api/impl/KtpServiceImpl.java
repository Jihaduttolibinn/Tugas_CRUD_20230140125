package com.example.ktp_api.impl;

import com.example.ktp_api.dto.KtpDto;
import com.example.ktp_api.entity.KtpEntity;
import com.example.ktp_api.mapper.KtpMapper;
import com.example.ktp_api.repository.KtpRepository;
import com.example.ktp_api.service.KtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KtpServiceImpl implements KtpService {

    private final KtpRepository ktpRepository;
    private final KtpMapper ktpMapper;

    @Override
    public List<KtpDto> getAllKtp() {
        return ktpMapper.toDtoList(ktpRepository.findAll());
    }

    @Override
    public KtpDto getKtpById(Long id) {
        KtpEntity entity = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan dengan id: " + id));
        return ktpMapper.toDto(entity);
    }

    @Override
    @Transactional
    public KtpDto createKtp(KtpDto ktpDto) {
        if (ktpRepository.existsByNomorKtp(ktpDto.getNomorKtp())) {
            throw new RuntimeException("Nomor KTP sudah terdaftar");
        }
        KtpEntity entity = ktpMapper.toEntity(ktpDto);
        return ktpMapper.toDto(ktpRepository.save(entity));
    }

    @Override
    @Transactional
    public KtpDto updateKtp(Long id, KtpDto ktpDto) {
        KtpEntity entity = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan dengan id: " + id));
        
        // Cek jika nomor KTP diubah dan apakah nomor KTP baru sudah ada
        if (!entity.getNomorKtp().equals(ktpDto.getNomorKtp()) && ktpRepository.existsByNomorKtp(ktpDto.getNomorKtp())) {
            throw new RuntimeException("Nomor KTP sudah terdaftar");
        }

        entity.setNomorKtp(ktpDto.getNomorKtp());
        entity.setNamaLengkap(ktpDto.getNamaLengkap());
        entity.setAlamat(ktpDto.getAlamat());
        entity.setTanggalLahir(ktpDto.getTanggalLahir());
        entity.setJenisKelamin(ktpDto.getJenisKelamin());

        return ktpMapper.toDto(ktpRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteKtp(Long id) {
        if (!ktpRepository.existsById(id)) {
            throw new RuntimeException("Data KTP tidak ditemukan dengan id: " + id);
        }
        ktpRepository.deleteById(id);
    }
}
