-- GreenFood database schema. No original account or application records are included.
CREATE DATABASE IF NOT EXISTS `greenfood` DEFAULT CHARACTER SET utf8mb4;
USE `greenfood`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `browsing_history` (
  `Account` varchar(50) NOT NULL,
  `ID` varchar(50) NOT NULL,
  `Time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `city_chart` (
  `city` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `comment` (
  `Account` varchar(50) NOT NULL,
  `ID` varchar(50) NOT NULL,
  `StarNum` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `country_chart` (
  `country` varchar(100) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `food` (
  `ID` varchar(100) NOT NULL,
  `food` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `food_chart` (
  `food` varchar(100) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `location_chart` (
  `city` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `district` varchar(100) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `meal_chart` (
  `meal` varchar(100) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `myfavorite` (
  `Account` varchar(50) NOT NULL,
  `ID` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `payment` (
  `ID` varchar(100) NOT NULL,
  `payment` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `payment_chart` (
  `payment` varchar(100) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `person` (
  `account` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneNum` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `restaurant` (
  `ID` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `District` varchar(100) NOT NULL,
  `City` varchar(100) NOT NULL,
  `Phone` varchar(100) NOT NULL,
  `Price` varchar(100) NOT NULL,
  `WiFi` varchar(100) NOT NULL,
  `Accessibility` varchar(100) NOT NULL,
  `Delivery` varchar(100) NOT NULL,
  `URL` varchar(10000) NOT NULL,
  `FanPage` varchar(1000) NOT NULL,
  `BusinessHours` varchar(1000) NOT NULL,
  `Meal` varchar(100) DEFAULT NULL,
  `pictureURL` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `review` (
  `account` varchar(50) NOT NULL,
  `ID` varchar(50) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `style` (
  `ID` varchar(100) NOT NULL,
  `style` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `style_chart` (
  `style` varchar(100) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `browsing_history`
  ADD PRIMARY KEY (`Account`,`ID`,`Time`),
  ADD KEY `ID` (`ID`);

ALTER TABLE `city_chart`
  ADD PRIMARY KEY (`city`);

ALTER TABLE `comment`
  ADD PRIMARY KEY (`Account`,`ID`),
  ADD KEY `ID` (`ID`);

ALTER TABLE `country_chart`
  ADD PRIMARY KEY (`country`);

ALTER TABLE `food`
  ADD PRIMARY KEY (`ID`,`food`,`country`),
  ADD KEY `food` (`food`),
  ADD KEY `country` (`country`);

ALTER TABLE `food_chart`
  ADD PRIMARY KEY (`food`);

ALTER TABLE `location_chart`
  ADD PRIMARY KEY (`city`,`district`);

ALTER TABLE `meal_chart`
  ADD PRIMARY KEY (`meal`);

ALTER TABLE `myfavorite`
  ADD PRIMARY KEY (`Account`,`ID`),
  ADD KEY `ID` (`ID`);

ALTER TABLE `payment`
  ADD PRIMARY KEY (`ID`,`payment`),
  ADD KEY `payment` (`payment`);

ALTER TABLE `payment_chart`
  ADD PRIMARY KEY (`payment`);

ALTER TABLE `person`
  ADD PRIMARY KEY (`account`);

ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Meal` (`Meal`),
  ADD KEY `City` (`City`,`District`);

ALTER TABLE `review`
  ADD PRIMARY KEY (`account`,`time`),
  ADD KEY `ID` (`ID`);

ALTER TABLE `style`
  ADD PRIMARY KEY (`ID`,`style`),
  ADD KEY `style` (`style`);

ALTER TABLE `style_chart`
  ADD PRIMARY KEY (`style`);

ALTER TABLE `browsing_history`
  ADD CONSTRAINT `browsing_history_ibfk_1` FOREIGN KEY (`Account`) REFERENCES `person` (`account`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `browsing_history_ibfk_2` FOREIGN KEY (`ID`) REFERENCES `restaurant` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`Account`) REFERENCES `person` (`account`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`ID`) REFERENCES `restaurant` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `food`
  ADD CONSTRAINT `food_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `restaurant` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `food_ibfk_2` FOREIGN KEY (`food`) REFERENCES `food_chart` (`food`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `food_ibfk_3` FOREIGN KEY (`country`) REFERENCES `country_chart` (`country`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `location_chart`
  ADD CONSTRAINT `location_chart_ibfk_1` FOREIGN KEY (`city`) REFERENCES `city_chart` (`city`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `myfavorite`
  ADD CONSTRAINT `myfavorite_ibfk_1` FOREIGN KEY (`Account`) REFERENCES `person` (`account`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `myfavorite_ibfk_2` FOREIGN KEY (`ID`) REFERENCES `restaurant` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`payment`) REFERENCES `payment_chart` (`payment`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`ID`) REFERENCES `restaurant` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `restaurant`
  ADD CONSTRAINT `restaurant_ibfk_1` FOREIGN KEY (`Meal`) REFERENCES `meal_chart` (`meal`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `restaurant_ibfk_2` FOREIGN KEY (`City`,`District`) REFERENCES `location_chart` (`city`, `district`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`account`) REFERENCES `person` (`account`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`ID`) REFERENCES `restaurant` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `style`
  ADD CONSTRAINT `style_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `restaurant` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `style_ibfk_2` FOREIGN KEY (`style`) REFERENCES `style_chart` (`style`) ON DELETE CASCADE ON UPDATE CASCADE;

SET FOREIGN_KEY_CHECKS = 1;
