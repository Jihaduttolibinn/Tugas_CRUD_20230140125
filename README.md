# KTP CRUD System - Spring Boot & JQuery

A simple digital population system for managing KTP data using Spring Boot (Backend) and JQuery Ajax (Frontend).

## Features
- Create, Read, Update, and Delete KTP data.
- Built-in validation (e.g., 16-digit NIK, non-empty fields).
- Modern responsive UI with micro-animations.
- Global Error Handling for better API feedback.

## Tech Stack
- **Backend**: Java 25, Spring Boot 4.x, Spring Data JPA, Hibernate, MySQL Connector, Lombok, Validation.
- **Frontend**: HTML5, Vanilla CSS, JQuery 3.6.
- **Database**: MySQL.

## Database Schema
Table name: `KTP`
- `id` (INT, PK, Auto Increment)
- `nomor_ktp` (VARCHAR, Unique, 16 chars)
- `nama_lengkap` (VARCHAR)
- `alamat` (VARCHAR)
- `tanggal_lahir` (DATE)
- `jenis_kelamin` (VARCHAR)

## API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/ktp` | Get all KTP data |
| GET | `/ktp/{id}` | Get KTP by ID |
| POST | `/ktp` | Create new KTP |
| PUT | `/ktp/{id}` | Update existing KTP |
| DELETE | `/ktp/{id}` | Delete KTP by ID |

### Example Request Body (POST/PUT)
```json
{
  "nomorKtp": "1234567890123456",
  "namaLengkap": "Budi Santoso",
  "alamat": "Jl. Merdeka No. 10, Jakarta",
  "tanggalLahir": "1990-05-20",
  "jenisKelamin": "LAKI-LAKI"
}
```

## How to Run
1. Create a MySQL database named `spring`.
2. Update `src/main/resources/application.properties` with your MySQL credentials.
3. Run the application: `./mvnw spring-boot:run`.
4. Access the UI at `http://localhost:8080`.
