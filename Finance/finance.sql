-- -------------------------------------------------------------
-- TablePlus 3.11.0(352)
--
-- https://tableplus.com/
--
-- Database: finance
-- Generation Time: 2020-11-25 18:51:45.9120
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `banks`;
CREATE TABLE `banks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Bank_Id` (`Name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Currencies`;
CREATE TABLE `Currencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `BankId` int(11) NOT NULL,
  `Code` int(11) NOT NULL DEFAULT 0,
  `Name` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CurrIdx` (`BankId`,`Code`,`Name`) USING BTREE,
  CONSTRAINT `currencies_ibfk_1` FOREIGN KEY (`BankId`) REFERENCES `Banks` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ExchangeRates`;
CREATE TABLE `ExchangeRates` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CurrencyId` int(11) NOT NULL,
  `SaleNB` double(10,7) NOT NULL DEFAULT 0.0000000,
  `PurchaseNB` double(10,7) NOT NULL DEFAULT 0.0000000,
  `Sale` double(10,7) NOT NULL DEFAULT 0.0000000,
  `Purchase` double(10,7) NOT NULL DEFAULT 0.0000000,
  `Currency` varchar(5) NOT NULL,
  `ExchRateDay` int(11) NOT NULL,
  `ExchRateMonth` int(11) NOT NULL,
  `ExchRateYear` int(11) NOT NULL,
  `ExchDate` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ExchRate` (`CurrencyId`,`Currency`,`ExchRateDay`,`ExchRateMonth`,`ExchRateYear`) USING BTREE,
  CONSTRAINT `exchangerates_ibfk_1` FOREIGN KEY (`CurrencyId`) REFERENCES `Currencies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3026 DEFAULT CHARSET=utf8;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;