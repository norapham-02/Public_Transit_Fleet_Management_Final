-- Drop and Create Database
DROP DATABASE IF EXISTS PublicTransitFleetManagement;
CREATE DATABASE IF NOT EXISTS PublicTransitFleetManagement;
USE PublicTransitFleetManagement;

-- Users Table (with is_approved)
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('Manager', 'Operator') NOT NULL,
    session_token VARCHAR(255),
    last_login DATETIME,
    is_approved BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Routes Table
CREATE TABLE IF NOT EXISTS routes (
    route_id INT AUTO_INCREMENT PRIMARY KEY,
    route_name VARCHAR(255) NOT NULL,
    start_station VARCHAR(255) NOT NULL,
    end_station VARCHAR(255) NOT NULL,
    total_distance FLOAT NOT NULL
);

-- Vehicles Table (with assigned_operator_email)
CREATE TABLE IF NOT EXISTS vehicles (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_type ENUM('Bus', 'Electric Light Rail', 'Diesel-Electric Train') NOT NULL,
    vehicle_number VARCHAR(100) NOT NULL UNIQUE,
    fuel_type ENUM('Diesel', 'CNG', 'Electric') NOT NULL,
    consumption_rate FLOAT NOT NULL,
    max_passengers INT NOT NULL,
    assigned_route INT,
    assigned_operator_email VARCHAR(255),
    last_service_date DATE,
    FOREIGN KEY (assigned_route) REFERENCES routes(route_id)
);

-- Fuel Usage Table
CREATE TABLE IF NOT EXISTS fuel_usage (
    fuel_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT NOT NULL,
    date DATE NOT NULL,
    fuel_consumed FLOAT NOT NULL,
    mileage FLOAT NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

-- Maintenance Logs Table
CREATE TABLE IF NOT EXISTS maintenance_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT NOT NULL,
    service_date DATE NOT NULL,
    service_type VARCHAR(255) NOT NULL,
    mileage_at_service FLOAT NOT NULL,
    next_service_due DATE NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

-- GPS Tracking Table
CREATE TABLE gps_tracking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_number VARCHAR(20),
    station_name VARCHAR(100),
    arrival_time DATETIME,
    departure_time DATETIME,
    status VARCHAR(20), -- ACTIVE, OUT_OF_SERVICE
    logged_by VARCHAR(100),
    log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Operator Break Log Table
CREATE TABLE IF NOT EXISTS operator_break_log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    operator_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    break_start DATETIME NOT NULL,
    break_end DATETIME NOT NULL,
    FOREIGN KEY (operator_id) REFERENCES users(user_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

-- Indexes
CREATE INDEX idx_vehicle_assigned_route ON vehicles(assigned_route);
CREATE INDEX idx_fuel_vehicle_id ON fuel_usage(vehicle_id);
CREATE INDEX idx_maintenance_vehicle_id ON maintenance_logs(vehicle_id);
CREATE INDEX idx_gps_vehicle_number ON gps_tracking(vehicle_number);

-- Populate Users
INSERT INTO users (name, email, password, role, session_token, last_login, is_approved) VALUES
('admin', 'admin', '$2a$12$J1E6qkpvnblcIUZfuZ9z6Od7I2jLni0p3ZfhCCLCZ7SGld4s7FV2a', 'Manager', NULL, NOW(), TRUE);

-- Populate Routes
INSERT INTO routes (route_name, start_station, end_station, total_distance) VALUES
('Downtown Express', 'Central Station', 'North Terminal', 12.5),
('Suburban Line', 'East Station', 'West Station', 22.3),
('City Loop', 'South Terminal', 'Central Station', 15.0),
('University Express', 'West Campus', 'East Campus', 18.7),
('Airport Shuttle', 'Downtown Hub', 'Airport Terminal', 25.4),
('Tech Park Route', 'Tech Park', 'Business District', 10.2),
('Green Belt Route', 'North Green Park', 'South Green Park', 17.9),
('Civic Center Line', 'City Hall', 'Civic Center', 8.6),
('Residential Connector', 'Suburb A', 'Suburb B', 20.1),
('Harbor Line', 'Seaport', 'Warehouse District', 14.3);

-- Populate Vehicles
INSERT INTO vehicles (vehicle_type, vehicle_number, fuel_type, consumption_rate, max_passengers, assigned_route, assigned_operator_email, last_service_date) VALUES
('Bus', 'BUS-101', 'Diesel', 8.5, 50, 1, '', '2025-02-15'),
('Electric Light Rail', 'LR-203', 'Electric', 0.0, 120, 2, '', '2025-01-10'),
('Diesel-Electric Train', 'TRAIN-305', 'Diesel', 15.2, 200, 3, '', '2025-03-05'),
('Bus', 'BUS-104', 'CNG', 9.0, 45, 4, '', '2025-02-20'),
('Electric Light Rail', 'LR-205', 'Electric', 0.0, 130, 5, '', '2025-02-25'),
('Bus', 'BUS-106', 'Diesel', 7.8, 55, 6, '', '2025-03-10'),
('Diesel-Electric Train', 'TRAIN-307', 'Diesel', 14.8, 220, 7, '', '2025-03-15'),
('Bus', 'BUS-108', 'CNG', 8.3, 50, 8, '', '2025-03-18'),
('Electric Light Rail', 'LR-209', 'Electric', 0.0, 140, 9, '', '2025-03-22'),
('Bus', 'BUS-110', 'Diesel', 9.2, 60, 10, '', '2025-03-25');

-- Populate Fuel Usage
INSERT INTO fuel_usage (vehicle_id, date, fuel_consumed, mileage) VALUES
(1, '2025-03-20', 50.0, 600.0),
(2, '2025-03-19', 0.0, 800.0),
(3, '2025-03-19', 120.5, 1400.0),
(4, '2025-03-21', 45.0, 550.0),
(5, '2025-03-21', 0.0, 900.0),
(6, '2025-03-22', 38.7, 480.0),
(7, '2025-03-23', 110.3, 1300.0),
(8, '2025-03-24', 42.5, 500.0),
(9, '2025-03-25', 0.0, 750.0),
(10, '2025-03-26', 55.1, 620.0);

-- Populate Maintenance Logs
INSERT INTO maintenance_logs (vehicle_id, service_date, service_type, mileage_at_service, next_service_due) VALUES
(1, '2025-02-15', 'Engine Check', 15000.0, '2025-05-15'),
(2, '2025-01-10', 'Battery Inspection', 25000.0, '2025-06-10'),
(3, '2025-03-05', 'Brake Replacement', 40000.0, '2025-09-05'),
(4, '2025-02-20', 'Oil Change', 12000.0, '2025-05-20'),
(5, '2025-02-25', 'Transmission Check', 18000.0, '2025-07-25'),
(6, '2025-03-10', 'Coolant Refill', 22000.0, '2025-06-10'),
(7, '2025-03-15', 'Air Filter Replacement', 35000.0, '2025-09-15'),
(8, '2025-03-18', 'Alignment Check', 26000.0, '2025-06-18'),
(9, '2025-03-22', 'Suspension Inspection', 29000.0, '2025-07-22'),
(10, '2025-03-25', 'Fuel System Cleaning', 31000.0, '2025-08-25');
