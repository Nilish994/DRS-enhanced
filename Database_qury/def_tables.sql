CREATE DATABASE IF NOT EXISTS disaster_response;
USE disaster_response;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);
CREATE TABLE IF NOT EXISTS disasters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    disaster_type VARCHAR(50) NOT NULL,
    location VARCHAR(100) NOT NULL,
    severity VARCHAR(20) NOT NULL,
    description TEXT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS notificationModel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    notification VARCHAR(255) NOT NULL
);
-- INSERT INTO users (username, password, role) VALUES 
-- ('admin', 'adminpass', 'admin'),
-- ('responder1', 'responderpass', 'responder'),
-- ('user1', 'userpass', 'user');
INSERT INTO disasters (disaster_type, location, severity, description) VALUES 
('Flood', 'New York', 'High', 'Heavy rainfall causing severe flooding in the city center.'),
('Earthquake', 'San Francisco', 'Moderate', 'A moderate earthquake causing minor damages in the southern part of the city.');
