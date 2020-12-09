-- -------------------------------------------------------------
-- TablePlus 3.12.0(354)
--
-- https://tableplus.com/
--
-- Database: Bank
-- Generation Time: 2020-12-09 17:52:51.9110
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `Accounts`;
CREATE TABLE `Accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Number` varchar(20) NOT NULL,
  `CustomerId` bigint(20) NOT NULL,
  `CurrencyId` int(11) NOT NULL,
  `Balance` decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`),
  KEY `CustomerId` (`CustomerId`),
  CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`CustomerId`) REFERENCES `Customers` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Crosses`;
CREATE TABLE `Crosses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `FromCurrencyId` int(11) NOT NULL,
  `ToCurrencyId` int(11) NOT NULL,
  `Amount` decimal(7,4) NOT NULL DEFAULT 0.0000,
  PRIMARY KEY (`id`),
  KEY `FromCurrencyId` (`FromCurrencyId`),
  CONSTRAINT `crosses_ibfk_1` FOREIGN KEY (`FromCurrencyId`) REFERENCES `Currencies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Currencies`;
CREATE TABLE `Currencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Customers`;
CREATE TABLE `Customers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Transactions`;
CREATE TABLE `Transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `AccountId` bigint(20) NOT NULL,
  `Number` varchar(20) NOT NULL,
  `CreatedAt` datetime NOT NULL DEFAULT current_timestamp(),
  `Amount` decimal(10,2) NOT NULL DEFAULT 0.00,
  `TypeId` int(11) NOT NULL,
  `Description` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `TypeId` (`TypeId`),
  KEY `AccountId` (`AccountId`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`TypeId`) REFERENCES `TransactionTypes` (`id`) ON DELETE CASCADE,
  CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`AccountId`) REFERENCES `Accounts` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `TransactionTypes`;
CREATE TABLE `TransactionTypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(15) NOT NULL,
  `Active` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;