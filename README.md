# E-Exchange 🔄

A second-hand electronics marketplace web app where users can buy and sell used phones and laptops. Built as a college project to practice full-stack development with Spring Boot and vanilla JS.

## Features

- **User Authentication** – Register and login with secure password hashing (BCrypt)
- **Sell an Item** – List a phone or laptop with photo, price, condition, and description
- **Buy** – Browse all listings with search and category (Phones/Laptops) filters
- **Seller Contact** – View seller's name, phone, and email to connect and buy
- **My Listings** – Edit or delete your own listed items, with a custom confirmation popup
- **Profile Dropdown** – Quick logout from any page
- **Responsive UI** – Clean, minimal design with subtle hover effects

## Tech Stack

**Backend:** Java, Spring Boot 4, Spring Data JPA, Spring Security (BCrypt), MySQL
**Frontend:** HTML, CSS, Vanilla JavaScript (fetch API)
**Build Tool:** Maven

## Project Structure
com.eexchange.eexchange
├── config/          # Security configuration
├── controller/       # REST APIs (Auth, Product)
├── entity/           # User, Product
├── repository/        # Spring Data JPA repositories
└── EExchangeApplication.java

src/main/resources/static/
├── index.html, login.html, register.html
├── dashboard.html, buy.html, sell.html, my-listings.html
├── css/style.css
└── js/login.js, register.js

## How to Run

1. Clone the repository
2. Create a MySQL database named `e_exchange`
3. Update `src/main/resources/application.properties` with your MySQL username/password
4. Run `EExchangeApplication.java`
5. Open `http://localhost:8080/register.html` in your browser

## Screenshots

*(Add screenshots of login, dashboard, buy and sell pages here)*

## Future Improvements

- JWT-based authentication for stronger security
- Chat between buyer and seller
- Image upload to cloud storage instead of base64
- Pagination for large numbers of listings