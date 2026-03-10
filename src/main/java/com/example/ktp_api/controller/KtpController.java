package com.example.ktp_api.controller;

import com.example.ktp_api.dto.KtpDto;
import com.example.ktp_api.service.KtpService;
import com.example.ktp_api.util.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ktp")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class KtpController {

    private final KtpService ktpService;

    @GetMapping
    public ResponseEntity<Object> getAllKtp() {
        List<KtpDto> list = ktpService.getAllKtp();
        return ResponseHandler.generateResponse("Data KTP berhasil diambil", HttpStatus.OK, list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getKtpById(@PathVariable Long id) {
        KtpDto dto = ktpService.getKtpById(id);
        return ResponseHandler.generateResponse("Data KTP berhasil ditemukan", HttpStatus.OK, dto);
    }

    @PostMapping
    public ResponseEntity<Object> createKtp(@Valid @RequestBody KtpDto ktpDto) {
        KtpDto created = ktpService.createKtp(ktpDto);
        return ResponseHandler.generateResponse("Data KTP berhasil ditambahkan", HttpStatus.CREATED, created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateKtp(@PathVariable Long id, @Valid @RequestBody KtpDto ktpDto) {
        KtpDto updated = ktpService.updateKtp(id, ktpDto);
        return ResponseHandler.generateResponse("Data KTP berhasil diperbarui", HttpStatus.OK, updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteKtp(@PathVariable Long id) {
        ktpService.deleteKtp(id);
        return ResponseHandler.generateResponse("Data KTP berhasil dihapus", HttpStatus.OK, null);
    }
}
