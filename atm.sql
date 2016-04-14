-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Дек 23 2015 г., 14:45
-- Версия сервера: 5.1.41
-- Версия PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `atm`
--

-- --------------------------------------------------------

--
-- Структура таблицы `atm`
--

CREATE TABLE IF NOT EXISTS `atm` (
  `atm_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(16) NOT NULL,
  `address` varchar(32) NOT NULL,
  PRIMARY KEY (`atm_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=778 ;

--
-- Дамп данных таблицы `atm`
--

INSERT INTO `atm` (`atm_id`, `account_id`, `address`) VALUES
(777, 0, '12 Kotsubynskogo str.,Kyiv'),
(555, 0, '11 Skovorody str.,Kyiv');

-- --------------------------------------------------------

--
-- Структура таблицы `creditaccount`
--

CREATE TABLE IF NOT EXISTS `creditaccount` (
  `account_num` int(16) NOT NULL AUTO_INCREMENT,
  `card_number` int(16) NOT NULL,
  `pin` int(4) NOT NULL,
  `open_date` date NOT NULL,
  `close_date` date NOT NULL,
  `max_available_sum` double NOT NULL,
  `own_money` double DEFAULT NULL,
  `borrowed_money` double DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `surname` varchar(32) NOT NULL,
  PRIMARY KEY (`account_num`),
  UNIQUE KEY `account_num` (`account_num`),
  UNIQUE KEY `card_number` (`card_number`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Дамп данных таблицы `creditaccount`
--

INSERT INTO `creditaccount` (`account_num`, `card_number`, `pin`, `open_date`, `close_date`, `max_available_sum`, `own_money`, `borrowed_money`, `name`, `surname`) VALUES
(12, 757, 5789, '2012-05-12', '2015-05-15', 500, 0, 100, 'Felix', 'Kjellberg'),
(14, 575, 9955, '2014-11-24', '2015-11-30', 12000, 0, 205, 'Michael', 'Faraday');

-- --------------------------------------------------------

--
-- Структура таблицы `debitaccount`
--

CREATE TABLE IF NOT EXISTS `debitaccount` (
  `account_num` int(16) NOT NULL AUTO_INCREMENT,
  `card_number` int(16) NOT NULL,
  `pin` int(4) NOT NULL,
  `open_date` date NOT NULL,
  `close_date` date NOT NULL,
  `sum_available` double NOT NULL,
  `name` varchar(32) NOT NULL,
  `surname` varchar(32) NOT NULL,
  PRIMARY KEY (`account_num`),
  UNIQUE KEY `account_num` (`account_num`),
  UNIQUE KEY `card_number` (`card_number`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=134 ;

--
-- Дамп данных таблицы `debitaccount`
--

INSERT INTO `debitaccount` (`account_num`, `card_number`, `pin`, `open_date`, `close_date`, `sum_available`, `name`, `surname`) VALUES
(2, 177, 4321, '2015-11-29', '2015-12-31', 350, 'Grace', 'Hopper'),
(1, 144, 1234, '2013-12-26', '2016-12-22', 1427, 'Anna', 'Green');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
