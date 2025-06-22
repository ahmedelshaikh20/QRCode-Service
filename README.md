# QR Code Generation REST API

A simple and customizable QR Code generator API built with **Spring Boot** and **Kotlin** using the **ZXing** library.

## 🚀 Features

- Generate QR codes via a RESTful API.
- Supports output formats: `PNG`, `JPEG`, and `GIF`.
- Supports error correction levels: `L`, `M`, `Q`, `H`.
- Size customization (between 150x150 and 350x350).
- Input validation with clear JSON error messages.

---

## 🛠️ Tech Stack

- **Kotlin**
- **Spring Boot**
- **ZXing (Zebra Crossing)** for QR code generation
- **Gradle**

---

## 🔗 API Endpoints

### 1. Health Check

**GET** `/`  
Response: `"Hello World"`

**GET** `/api/health`  
Response: `"Ok"`

---

### 2. Generate QR Code

**GET** `/api/qrcode`

#### Query Parameters

| Parameter     | Required | Default | Description |
|---------------|----------|---------|-------------|
| `contents`    | ✅ Yes   | –       | The text/data to encode in the QR code |
| `size`        | ❌ No    | `250`   | Image size (must be between 150 and 350) |
| `correction`  | ❌ No    | `L`     | Error correction level (`L`, `M`, `Q`, `H`) |
| `type`        | ❌ No    | `png`   | Output image format (`png`, `jpeg`, `gif`) |

