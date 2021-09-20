-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 20 Wrz 2021, 22:31
-- Wersja serwera: 10.4.18-MariaDB
-- Wersja PHP: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `pizzamapdb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pizzerias`
--

CREATE TABLE `pizzerias` (
  `Id` int(20) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Longitude` float NOT NULL,
  `Latitude` float NOT NULL,
  `Description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `pizzerias`
--

INSERT INTO `pizzerias` (`Id`, `Name`, `Longitude`, `Latitude`, `Description`) VALUES
(1, 'Tivoli', 16.92, 52.397, 'Nice place'),
(2, 'Pizza Naturalna', 16.89, 52.391, 'Good natural pizza'),
(3, 'Przyjemnosc', 16.9245, 52.3967, 'The restaurant has a nice garden '),
(4, 'Pizza Taxi', 16.949, 52.397, 'Fast delivery'),
(5, 'Pizza4You', 16.9705, 52.3839, 'Pizza for you '),
(6, 'Sky Pizza', 16.9586, 52.3909, 'Pizza from heaven');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `pizzerias`
--
ALTER TABLE `pizzerias`
  ADD PRIMARY KEY (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
