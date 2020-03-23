/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : busstation

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-03-27 14:14:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `Name` varchar(255) default NULL,
  `NationalId` varchar(255) NOT NULL,
  `Password` varchar(255) default NULL,
  `YearOfBirth` int(11) default NULL,
  PRIMARY KEY  (`NationalId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES ('Muhammad ibrahim', '133131', 'duke', '1999');
INSERT INTO `customers` VALUES ('Ahmed Sayeed', '123451', 'darkmido', '1999');
INSERT INTO `customers` VALUES ('Hosam Tarek Ouda', '221121313213', 'midoooo', '1999');

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `Name` varchar(255) default NULL,
  `NationalId` varchar(255) NOT NULL,
  `YearOfBirth` int(11) default NULL,
  `Salary` double(10,2) default NULL,
  `EmpType` tinyint(4) default NULL,
  PRIMARY KEY  (`NationalId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES ('Muhammad Ibrahim', '1323232', '1999', '2000.50', '0');
INSERT INTO `employees` VALUES ('Sherif Ahmed', '18383228383', '1997', '2000.50', '0');

-- ----------------------------
-- Table structure for trips
-- ----------------------------
DROP TABLE IF EXISTS `trips`;
CREATE TABLE `trips` (
  `ID` int(11) NOT NULL auto_increment,
  `TripFrom` varchar(255) default NULL,
  `TripTo` varchar(255) default NULL,
  `VehicleInfo` varchar(255) default NULL,
  `Driver` varchar(255) default NULL,
  `Day` varchar(255) default NULL,
  `Month` varchar(255) default NULL,
  `Year` varchar(255) default NULL,
  `Hour` varchar(255) default NULL,
  `Minute` varchar(255) default NULL,
  `External` tinyint(4) default NULL,
  `AvailableSpots` int(11) default NULL,
  `Stops` varchar(255) default NULL,
  `Price` double(10,2) default '0.00',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trips
-- ----------------------------
INSERT INTO `trips` VALUES ('7', 'Alexandria', 'KSA', 'Limo:123', 'Muhammad Ibrahim', '1', '1', '2018', '0', '0', '1', '3', '', '200.00');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) default NULL,
  PRIMARY KEY  (`Username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('duke', '123');

-- ----------------------------
-- Table structure for vehicles
-- ----------------------------
DROP TABLE IF EXISTS `vehicles`;
CREATE TABLE `vehicles` (
  `Number` int(11) NOT NULL,
  `VehicleType` int(11) default NULL,
  PRIMARY KEY  (`Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicles
-- ----------------------------
INSERT INTO `vehicles` VALUES ('123', '2');
INSERT INTO `vehicles` VALUES ('22', '0');
INSERT INTO `vehicles` VALUES ('144', '1');
