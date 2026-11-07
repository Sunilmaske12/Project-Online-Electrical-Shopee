# Online Electrical Shopee

An e-commerce web application for electrical products, built using Java Servlets, JSP, JDBC, and MySQL. The application includes customer-facing shopping pages and an administration area for managing products, categories, banners, and orders.

The user interface and server-side application are included in this Maven web application.

## Features

### Customer Section

- Customer registration, login, and logout
- Product browsing and product details
- Product search and category-based browsing
- Shopping cart with quantity updates
- Checkout and address selection
- Order placement with Cash on Delivery and Razorpay payment flow
- Order history and order details
- Invoice page
- Forgot-password and OTP-related flows
- Contact/support and chat pages

### Admin Section

- Admin login and dashboard
- Product management
- Category management
- Banner management
- Customer/user listing
- Order listing and order status management
- Support chat management
- Daily business summary pages

## Technology Stack

- Java
- Java Servlets
- JSP and JSTL
- JDBC
- MySQL
- Maven
- HTML, CSS, and JavaScript
- Gson
- Jakarta Mail
- Razorpay Java SDK

The project is packaged as a WAR application for deployment to a Servlet container.

## Project Structure

```text
Online-Electrical-Shopee/
└── User_panel_new/
    ├── src/
    │   └── main/
    │       ├── java/com/codeo/shop/
    │       │   ├── Dao/
    │       │   ├── controller/
    │       │   ├── dbutil/
    │       │   └── entity/
    │       └── webapp/
    │           ├── WEB-INF/
    │           ├── AdminPanel/
    │           ├── JSP pages
    │           ├── CSS, JavaScript, and image assets
    │           └── ...
    ├── pom.xml
    └── .gitignore
```

### Main Java packages

- `Dao` – database access and data operations
- `controller` – servlet request handling and application workflows
- `dbutil` – database connection utility
- `entity` – Java classes representing application data

## Prerequisites

- JDK compatible with the project's dependencies
- Apache Maven
- MySQL Server
- Apache Tomcat or another compatible Servlet container
- An IDE such as Eclipse, IntelliJ IDEA, or Spring Tool Suite
- Razorpay test account credentials if testing the online payment flow
- Gmail SMTP credentials if testing email functionality

## Database Configuration

The application uses a MySQL database named `oes` in its current connection utility.

Before running the application:

1. Start MySQL.
2. Create the database:

   ```sql
   CREATE DATABASE oes;
   ```

3. Configure the JDBC URL, database username, and password in the database connection utility.
4. Ensure that the required tables exist.

The uploaded project does not include a database export or SQL schema file. The required table definitions must therefore be prepared using the table and column names referenced by the DAO classes.

Do not commit real database credentials to source control. Prefer environment-based or external local configuration.

## Build and Deployment

From the `User_panel_new` directory, build the WAR:

```bash
mvn clean package
```

The Maven configuration sets the generated WAR name to:

```text
UserWebsite.war
```

Deploy the generated WAR from the `target` directory to a compatible Apache Tomcat installation.

For local development, you can also import the `User_panel_new` directory into Eclipse as an existing Maven project and run it on a configured Tomcat server.

## Payment and Email Integration

- The application includes Razorpay order creation for its online payment flow.
- The checkout flow also includes Cash on Delivery.
- Email functionality uses Jakarta Mail with Gmail SMTP.

External integrations require valid credentials and provider-side configuration. Use test credentials for development and keep API secrets and email app passwords outside the repository.

## Important Security and Maintenance Notes

- The uploaded source contains hardcoded third-party credentials, including an email app password and a Razorpay secret. Revoke or rotate exposed credentials before keeping this repository public. Do not copy those values into documentation or commit history.
- User passwords are handled directly in the current implementation. A production application should store securely hashed passwords and use a robust authentication and authorization design.
- Review session and role checks to ensure customer and admin operations are properly protected.
- Validate uploaded files and avoid trusting user-supplied file names or paths.
- Review SQL operations, connection handling, and resource cleanup.
- The archive contains nested duplicate project folders and generated `target` content. Keep only the intended project source in the Git repository and exclude generated build output.

## Project Type

This is a Java Servlet/JSP/JDBC Maven web application. It is not a Spring MVC or Spring Boot application.

## Author

**Sunil Maske**

- GitHub: [Sunilmaske12](https://github.com/Sunilmaske12)
- LinkedIn: [Sunil Maske](https://www.linkedin.com/in/sunil-maske/)
- LeetCode: [maskesunil16](https://leetcode.com/u/maskesunil16/)
