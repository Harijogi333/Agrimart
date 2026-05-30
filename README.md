# Agrimart

A modern Spring Boot-based e-commerce platform designed for agricultural products and farming supplies marketplace.

## 🌾 Project Overview

Agrimart is a full-featured e-commerce application tailored for the agricultural sector. It provides a complete marketplace solution connecting farmers, suppliers, and buyers for trading agricultural products and farming equipment.

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 3.5.3
- **Language**: Java 21
- **Build Tool**: Maven

### Database
- **Database**: MySQL
- **ORM**: Spring Data JPA (Hibernate)
- **Auditing**: JPA Auditing enabled for tracking entity changes

### Core Features
- **Web Framework**: Spring Web MVC
- **Security**: Spring Security
- **Template Engine**: Thymeleaf (for server-side rendering)
- **Validation**: Spring Validation
- **Email Service**: Spring Mail (SMTP via Gmail)

### Additional Libraries
- **Lombok**: For reducing boilerplate code (getters, setters, constructors)
- **Dev Tools**: Spring Boot DevTools for enhanced development experience

## 📋 Key Features

- **User Management**: User registration, authentication, and profile management
- **Product Catalog**: Browse and manage agricultural products
- **Security**: Integrated Spring Security for authentication and authorization
- **Email Notifications**: Send emails for order confirmations and notifications
- **Data Persistence**: MySQL database with JPA ORM
- **Responsive UI**: Thymeleaf templates for dynamic web pages
- **Form Validation**: Built-in validation for user inputs

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- MySQL Server
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Harijogi333/Agrimart.git
   cd Agrimart
   ```

2. **Configure Database**
   
   Update `src/main/resources/application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/agrimart
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Configure Email Service**
   
   Update email settings in `application.properties`:
   ```properties
   spring.mail.username=your_email@gmail.com
   spring.mail.password=your_app_password
   ```

4. **Build the project**
   ```bash
   ./mvnw clean build
   ```

5. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

   The application will be available at `http://localhost:8080`

## 📁 Project Structure

```
Agrimart/
├── src/
│   ├── main/
│   │   ├── java/com/mart/Agrimart/
│   │   │   ├── model/          # Entity classes (User, Product, etc.)
│   │   │   ├── controller/     # REST/Web controllers
│   │   │   ├── service/        # Business logic
│   │   │   ├── repository/     # Data access layer
│   │   │   └── ...
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── templates/      # Thymeleaf templates
│   │       └── static/         # CSS, JS, images
│   └── test/                   # Unit and integration tests
├── pom.xml                     # Maven configuration
├── mvnw                        # Maven wrapper (Unix/Mac)
└── mvnw.cmd                    # Maven wrapper (Windows)
```

## 🔧 Configuration

### Application Properties

Key configuration in `application.properties`:
- **Database Connection**: MySQL connection with Hibernate auto DDL updates
- **JPA Properties**: SQL formatting and debugging enabled
- **Security**: Debug logging enabled for Spring Security
- **Session Timeout**: 10 seconds
- **Mail Service**: SMTP configuration for email notifications

## 📦 Dependencies

Main dependencies included:
- `spring-boot-starter-data-jpa` - Database access
- `spring-boot-starter-web` - Web framework
- `spring-boot-starter-security` - Security
- `spring-boot-starter-thymeleaf` - Template engine
- `spring-boot-starter-validation` - Form validation
- `spring-boot-starter-mail` - Email service
- `mysql-connector-j` - MySQL driver
- `lombok` - Code generation library

## 🔐 Security

The application includes Spring Security for:
- User authentication and authorization
- Session management (10-second timeout)
- Secure password handling
- Debug logging for security troubleshooting

## 📧 Email Integration

Gmail SMTP is configured for:
- Order confirmation emails
- User notifications
- Password reset emails
- Account verification

*Note*: Use Gmail App Passwords for enhanced security instead of your main password.

## 🚦 Running Tests

```bash
./mvnw test
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is open source and available under the MIT License.

## 👤 Author

**Harijogi333**

## 📞 Support

For support, please open an issue on the GitHub repository.

---

**Note**: This is a Spring Boot application running on Java 21. Ensure all prerequisites are installed before attempting to run the project.
