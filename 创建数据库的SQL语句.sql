create database lsms_db;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(15) NOT NULL,
  `admin_name` varchar(255) NOT NULL,
  `admin_phone` varchar(255) NOT NULL,
  `admin_password` varchar(255) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `admin` VALUES ('1', 'Admin233', '19965583122', '123456');
INSERT INTO `admin` VALUES ('2', '1', '10000', '1');

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` int(15) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `book_writer` varchar(255) NOT NULL,
  `book_publish` varchar(255) NOT NULL,
  `book_amount` int(15) NOT NULL,
   `book_price` double NOT NULL,
   `book_discount` double NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `book` VALUES ('1', '计算机网络', '谢希仁', '电子工业出版社', '100','59.80','1.0');
INSERT INTO `book` VALUES ('2', '计算机组成与系统结构', '袁春风', '清华大学出版社', '100','59.50','1.0');
INSERT INTO `book` VALUES ('3', '设计模式(第2版)', '刘伟', '清华大学出版社', '100','79.80','1.0');
INSERT INTO `book` VALUES ('4', '设计模式实训教程（第2版）', '刘伟', '清华大学出版社', '100','59.00','1.0');
INSERT INTO `book` VALUES ('5', '鸟哥的Linux私房菜', '鸟哥', '机械工业出版社', '150','19.90','1.0');
INSERT INTO `book` VALUES ('6', 'MySQL必知必会', '刘晓霞', '人民邮电出版社', '100','9.90','1.0');

DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader` (
  `reader_id` int(50) NOT NULL,
  `reader_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reader_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reader_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `reader` VALUES ('1', '123', '10086', '123');
INSERT INTO `reader` VALUES ('2', 'wkq', '18866661234', 'password123');
INSERT INTO `reader` VALUES ('3', 'xzd', '18155667788', 'qwertyuiop');
INSERT INTO `reader` VALUES ('4', 'opg', '13345679876', 'qazwsxedc');

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(50) NOT NULL AUTO_INCREMENT,
  `order_price` double NOT NULL,
  `order_total` double NOT NULL,
  `order_amount` int(15) NOT NULL,
  `order_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci,
   `order_book_id` int(15) NOT NULL,
   `order_reader_id` int(50) NOT NULL,
  foreign key(`order_book_id`) references book(`book_id`),
  foreign key(`order_reader_id`) references reader(`reader_id`),
  PRIMARY KEY (`order_id`)
)  AUTO_INCREMENT = 1 ENGINE=InnoDB DEFAULT CHARSET=utf8;



