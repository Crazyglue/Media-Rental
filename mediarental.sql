-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2015 at 12:22 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mediarental`
--

-- --------------------------------------------------------

--
-- Table structure for table `borrowers`
--

CREATE TABLE IF NOT EXISTS `borrowers` (
  `idborrower` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`idborrower`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `borrowers`
--

INSERT INTO `borrowers` (`idborrower`, `name`, `address`, `phone`, `password`, `email`) VALUES
(1, 'Erd', '123 Fake St.', '5551234567', 'fff', 'poop@doodoo.com'),
(2, 'Red', NULL, NULL, '111', 'guuui@ttry.com'),
(3, 'butt', NULL, NULL, '4', 'fffeee');

-- --------------------------------------------------------

--
-- Table structure for table `cd`
--

CREATE TABLE IF NOT EXISTS `cd` (
  `idCD` int(5) NOT NULL AUTO_INCREMENT,
  `artist` varchar(45) NOT NULL,
  `album` varchar(45) NOT NULL,
  `total_quantity` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idCD`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `cd`
--

INSERT INTO `cd` (`idCD`, `artist`, `album`, `total_quantity`) VALUES
(1, 'Apparat', 'Walls', 0);

-- --------------------------------------------------------

--
-- Table structure for table `dvds`
--

CREATE TABLE IF NOT EXISTS `dvds` (
  `idDVD` int(5) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `year` varchar(4) DEFAULT NULL,
  `total_quantity` int(11) NOT NULL,
  PRIMARY KEY (`idDVD`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `dvds`
--

INSERT INTO `dvds` (`idDVD`, `title`, `year`, `total_quantity`) VALUES
(1, 'The Rock', '1996', 5);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `idorder` int(5) NOT NULL AUTO_INCREMENT,
  `borrower` int(5) NOT NULL,
  `cd` int(5) DEFAULT NULL,
  `duedate` date DEFAULT NULL,
  `dateborrowed` date DEFAULT NULL,
  `dvd` int(5) DEFAULT NULL,
  PRIMARY KEY (`idorder`),
  KEY `borrower` (`borrower`),
  KEY `item` (`cd`),
  KEY `dvd` (`dvd`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`idorder`, `borrower`, `cd`, `duedate`, `dateborrowed`, `dvd`) VALUES
(2, 1, 1, NULL, NULL, NULL),
(3, 1, NULL, '2015-05-16', '2015-05-12', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`dvd`) REFERENCES `dvds` (`idDVD`),
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`borrower`) REFERENCES `borrowers` (`idborrower`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`cd`) REFERENCES `cd` (`idCD`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
