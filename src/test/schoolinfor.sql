/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : javademo

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-12-11 13:38:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for schoolinfor
-- ----------------------------
DROP TABLE IF EXISTS `schoolinfor`;
CREATE TABLE `schoolinfor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacherName` varchar(255) DEFAULT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `perWeekClassNum` varchar(255) DEFAULT NULL,
  `perWeekTimeNum` varchar(255) DEFAULT NULL,
  `IsHead` tinyint(1) DEFAULT NULL,
  `IsNext` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schoolinfor
-- ----------------------------
INSERT INTO `schoolinfor` VALUES ('1', '王素芳', '英语', '2', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('2', '叶娅芬', '英语', '2', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('3', '李建华', '英语', '2', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('4', '吴菲菲', '英语', '2', '6', '1', '0');
INSERT INTO `schoolinfor` VALUES ('5', '何敏英', '英语', '1', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('6', '吴登忠', '地理', '5', '3', '0', '1');
INSERT INTO `schoolinfor` VALUES ('7', '姚子玲', '英语', '2', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('8', '俞运良', '物理', '4', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('9', '廖郁林', '物理', '3', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('10', '叶冬洪', '物理', '4', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('11', '张传香', '历史', '4', '3', '1', '0');
INSERT INTO `schoolinfor` VALUES ('12', '姚春琼', '历史', '3', '3', '0', '0');
INSERT INTO `schoolinfor` VALUES ('13', '吴华芬', '英语', '2', '6', '1', '0');
INSERT INTO `schoolinfor` VALUES ('14', '朱荣海', '数学', '2', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('15', '叶秀芬', '数学', '2', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('16', '谢岩银', '数学', '2', '6', '1', '0');
INSERT INTO `schoolinfor` VALUES ('17', '胡素芬', '数学', '2', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('18', '叶春红', '数学', '2', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('19', '吴步云', '数学', '2', '6', '1', '0');
INSERT INTO `schoolinfor` VALUES ('20', '李云', '数学', '1', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('21', '吴青群', '信技', '8', '1', '0', '0');
INSERT INTO `schoolinfor` VALUES ('22', '练炜波', '信技', '7', '1', '1', '0');
INSERT INTO `schoolinfor` VALUES ('23', '汤进进', '音乐', '8', '1', '0', '0');
INSERT INTO `schoolinfor` VALUES ('24', '姚红妹', '音乐', '7', '1', '1', '0');
INSERT INTO `schoolinfor` VALUES ('25', '朱惠文', '英语', '2', '6', '0', '0');
INSERT INTO `schoolinfor` VALUES ('26', '胡小丽', '物理', '4', '5', '1', '0');
INSERT INTO `schoolinfor` VALUES ('27', '潘宇飞', '化学', '4', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('28', '余谦', '化学', '4', '5', '1', '0');
INSERT INTO `schoolinfor` VALUES ('29', '松梅', '体育', '5', '1', '0', '0');
INSERT INTO `schoolinfor` VALUES ('30', '卯菊', '体育', '5', '1', '0', '0');
INSERT INTO `schoolinfor` VALUES ('31', '吴庆荷', '语文', '2', '5', '1', '0');
INSERT INTO `schoolinfor` VALUES ('32', '吴小聪', '语文', '2', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('33', '吴峰', '语文', '2', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('34', '吴清泉', '语文', '1', '5', '1', '0');
INSERT INTO `schoolinfor` VALUES ('35', '吴岳松', '数学', '2', '6', '1', '0');
INSERT INTO `schoolinfor` VALUES ('36', '马金锋', '化学', '1', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('37', '胡慧芳', '化学', '4', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('38', '叶淑芬', '政治', '7', '3', '0', '1');
INSERT INTO `schoolinfor` VALUES ('39', '孙燕', '政治', '7', '3', '0', '1');
INSERT INTO `schoolinfor` VALUES ('40', '吴继华', '语文', '2', '5', '1', '0');
INSERT INTO `schoolinfor` VALUES ('41', '吴丽丽', '语文', '2', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('42', '廖承勇', '地理', '5', '3', '0', '1');
INSERT INTO `schoolinfor` VALUES ('43', '望良', '体育', '5', '1', '1', '0');
INSERT INTO `schoolinfor` VALUES ('44', '周帮金', '语文', '2', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('45', '王超', '历史', '4', '3', '0', '0');
INSERT INTO `schoolinfor` VALUES ('46', '鲍健薇', '地理', '5', '3', '0', '1');
INSERT INTO `schoolinfor` VALUES ('47', '吴秋玲', '语文', '2', '5', '1', '0');
INSERT INTO `schoolinfor` VALUES ('48', '李志文', '化学', '2', '5', '0', '0');
INSERT INTO `schoolinfor` VALUES ('49', '王昌金', '政治', '1', '3', '0', '1');
INSERT INTO `schoolinfor` VALUES ('50', '杨青青', '历史', '4', '3', '0', '0');
