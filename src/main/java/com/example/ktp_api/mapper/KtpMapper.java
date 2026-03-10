package com.example.ktp_api.mapper;

import com.example.ktp_api.dto.KtpDto;
import com.example.ktp_api.entity.KtpEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KtpMapper {


    public KtpDto toDto(KtpEntity entity) {
        if (entity == null) return null;
        return KtpDto.builder()
                .id(entity.getId())
                .nomorKtp(entity.getNomorKtp())
                .namaLengkap(entity.getNamaLengkap())
                .alamat(entity.getAlamat())
                .tanggalLahir(entity.getTanggalLahir())
                .jenisKelamin(entity.getJenisKelamin())
                .build();
    }

    public KtpEntity toEntity(KtpDto dto) {
        if (dto == null) return null;
        return KtpEntity.builder()
                .id(dto.getId())
                .nomorKtp(dto.getNomorKtp())
                .namaLengkap(dto.getNamaLengkap())
                .alamat(dto.getAlamat())
                .tanggalLahir(dto.getTanggalLahir())
                .jenisKelamin(dto.getJenisKelamin())
                .build();
    }

    public List<KtpDto> toDtoList(List<KtpEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
