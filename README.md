# 🎵 Musify — Backend API

> **GitHub short description (About section):**
> Spring Boot REST API for Musify — JWT auth, role-based access control, MongoDB, and Cloudinary media storage powering a full-stack music streaming platform.

Secure, production-ready REST API powering **Musify**, a Spotify-inspired music streaming platform. Handles authentication, song/album management, and media storage for both the user-facing app and the admin dashboard.

**Live demos:**
- 🎧 User App: https://musify-front-end.vercel.app/
- 🛠️ Admin Panel: https://musify-admin-front-end.vercel.app/

---

## ✨ Features

- 🔐 JWT-based authentication with role-based access control (Admin / User)
- 🎶 Full CRUD APIs for songs and albums
- 📀 Album-to-song relationship management (browse songs within an album)
- 🔍 Search endpoints for songs and albums
- ☁️ Cloudinary integration for audio file and cover image uploads
- 🗄️ MongoDB schemas designed for songs, albums, and users with proper indexing
- 🛡️ Production-ready Spring Security configuration
- 🌐 Clean, RESTful API architecture consumed by two separate frontends (user + admin)

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Framework | Spring Boot |
| Security | Spring Security, JWT |
| Database | MongoDB |
| File Storage | Cloudinary |
| Architecture | RESTful API |

## 📂 Project Structure

```
src/main/java/com/musify/
├── config/          # Security & Cloudinary configuration
├── controller/       # REST controllers (auth, songs, albums, users)
├── model/             # MongoDB document schemas
├── repository/       # Spring Data MongoDB repositories
├── security/          # JWT filters & providers
└── service/            # Business logic layer
```

## 🔑 API Overview

| Endpoint | Method | Description | Access |
|---|---|---|---|
| `/api/auth/register` | POST | Register a new user | Public |
| `/api/auth/login` | POST | Authenticate & receive JWT | Public |
| `/api/songs` | GET | List / search songs | Public |
| `/api/songs` | POST | Add a new song | Admin |
| `/api/albums` | GET | List / search albums | Public |
| `/api/albums/{id}` | GET | Get album with its songs | Public |
| `/api/albums` | POST | Add a new album | Admin |

*(Adjust to match your actual route names before publishing.)*

## ⚙️ Getting Started

### Prerequisites
- Java 17+
- Maven
- MongoDB instance (local or Atlas)
- Cloudinary account

### Environment Variables

```env
MONGODB_URI=your_mongodb_connection_string
JWT_SECRET=your_jwt_secret
CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret
```

### Run Locally

```bash
git clone https://github.com/momen-tarek111/Musify_Backend.git
cd musify-backend
mvn clean install
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

## 🔗 Related Repositories

- 🎧 [Musify — User Frontend](https://github.com/momen-tarek111/Musify_FrontEnd)
- 🛠️ [Musify — Admin Panel](https://github.com/momen-tarek111/Musify_Admin_FrontEnd)

## 👤 Author

**Momen Tarek Nagaty** — Full Stack Developer
[LinkedIn](http://www.linkedin.com/in/momen-tarek-nagaty) · [GitHub](https://github.com/momen-tarek111)