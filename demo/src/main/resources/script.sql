/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`twitter` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `twitter`;

-- tabela user details
DROP TABLE IF EXISTS `user_details`;
create table `user_details` (
`ID` int(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
`name` varchar(50) NOT NULL,
`surname` varchar(50) NOT NULL,
`email` varchar(50) NOT NULL,
`born_date` datetime NOT NULL,
`join_date` datetime);


-- tabela USER
DROP TABLE IF EXISTS `user`;
create table `user` (
`ID` int(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
`login` varchar(50) NOT NULL,
`password` varchar(50) NOT NULL,
`role` varchar(50) NOT NULL,
`user_details_id` int(11) UNSIGNED,
`lock_date` datetime,
`unlock_date` datetime,
CONSTRAINT `user_userDetails_fk` FOREIGN KEY (`user_details_id`) REFERENCES `user_details` (`ID`) );

-- tabela POST
DROP TABLE IF EXISTS `post`;
create table `post` (
`ID` int(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
`create_date` datetime,
`text` varchar(200) NOT NULL,
`modify_date` datetime,
`user_id` int(11) UNSIGNED,
`delete_date` datetime,
CONSTRAINT `post_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) );

-- tabela COMMENT
DROP TABLE IF EXISTS `comment`;
create table `comment` (
`ID` int(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
`create_date` datetime,
`text` varchar(200) NOT NULL,
`modify_date` datetime,
`delete_date` datetime,
`user_id` int(11) UNSIGNED,
`post_id` int(11) UNSIGNED,
CONSTRAINT `comment_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`),
CONSTRAINT `comment_post_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`ID`) );
