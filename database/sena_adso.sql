-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2024 at 02:02 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sena_adso`
--

-- --------------------------------------------------------

--
-- Table structure for table `aprendices`
--

CREATE TABLE `aprendices` (
  `id` int(11) NOT NULL,
  `tipo_identificacion` enum('CC','TI','PS') COLLATE utf8mb4_bin NOT NULL,
  `identificacion` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `nombres` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `fecha_nacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `aprendices`
--

INSERT INTO `aprendices` (`id`, `tipo_identificacion`, `identificacion`, `nombres`, `fecha_nacimiento`) VALUES
(1, 'CC', '1061', 'Gustavo Rodriguez', '1991-08-03'),
(4, 'CC', '1062', 'Test test', '2023-08-03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aprendices`
--
ALTER TABLE `aprendices`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `identificacion` (`identificacion`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aprendices`
--
ALTER TABLE `aprendices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
