-- 1. Buat Database Schema
CREATE DATABASE IF NOT EXISTS spring;
USE spring;

-- 2. Buat Tabel KTP
CREATE TABLE IF NOT EXISTS KTP (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomor_ktp VARCHAR(16) UNIQUE NOT NULL,
    nama_lengkap VARCHAR(255) NOT NULL,
    alamat VARCHAR(255) NOT NULL,
    tanggal_lahir DATE NOT NULL,
    jenis_kelamin VARCHAR(20) NOT NULL
);

-- 3. Masukkan Data Sample
INSERT INTO KTP (nomor_ktp, nama_lengkap, alamat, tanggal_lahir, jenis_kelamin) VALUES 
('1234567890123456', 'Jihaduttolibin', 'Jl. Yogyakarta No. 123', '2000-01-01', 'LAKI-LAKI'),
('9876543210987654', 'Siti Aminah', 'Jl. Jakarta No. 45', '1995-12-15', 'PEREMPUAN')
ON DUPLICATE KEY UPDATE nomor_ktp=nomor_ktp;
