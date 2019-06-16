/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 47.102.124.211:3306
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 16/06/2019 22:35:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `classID` int(11) NOT NULL AUTO_INCREMENT,
  `className` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`classID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, '20-1计算机科学与技术');
INSERT INTO `class` VALUES (2, '20-2计算机科学与技术');
INSERT INTO `class` VALUES (11, '21-1软件工程');
INSERT INTO `class` VALUES (12, '22-6奥特曼集训营');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `examID` int(11) NOT NULL AUTO_INCREMENT,
  `examName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `startTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `endTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `teaNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `examPath` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试文档路径',
  `isAuto` bit(1) NOT NULL COMMENT '默认false',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classID` int(11) NULL DEFAULT NULL,
  `isManager` bit(1) NOT NULL DEFAULT b'0',
  `pack` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`examID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (12, '考试测试', '2019-06-12 09:00:00', '2019-06-12 10:00:00', '1010121001', '/1010121001', b'1', 'underway', 1, b'1', b'0');

-- ----------------------------
-- Table structure for exam_student
-- ----------------------------
DROP TABLE IF EXISTS `exam_student`;
CREATE TABLE `exam_student`  (
  `esID` int(11) NOT NULL AUTO_INCREMENT,
  `stuNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `examID` int(11) NOT NULL,
  `answerPath` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生答案路径',
  `answerTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '学生提交时间',
  PRIMARY KEY (`esID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_student
-- ----------------------------
INSERT INTO `exam_student` VALUES (15, '1610121001', 12, NULL, '2019-06-11 21:34:43');
INSERT INTO `exam_student` VALUES (16, '1610121002', 12, NULL, '2019-06-11 21:34:43');
INSERT INTO `exam_student` VALUES (17, '1610121003', 12, NULL, '2019-06-11 21:34:43');
INSERT INTO `exam_student` VALUES (18, '1610121004', 12, NULL, '2019-06-11 21:34:43');
INSERT INTO `exam_student` VALUES (19, '1610121005', 12, NULL, '2019-06-11 21:34:43');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `noticeID` int(11) NOT NULL AUTO_INCREMENT,
  `noticeTitle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `noticeTime` datetime(0) NOT NULL,
  `noticeContent` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `examID` int(11) NOT NULL COMMENT '考试ID',
  `teaNumber` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师工号发布人',
  PRIMARY KEY (`noticeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stuID` int(11) NOT NULL AUTO_INCREMENT,
  `stuNumber` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stuName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classID` int(11) NOT NULL,
  PRIMARY KEY (`stuID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (13, '1610121001', '李飞', 1);
INSERT INTO `student` VALUES (14, '1610121002', '李朗', 1);
INSERT INTO `student` VALUES (15, '1610121003', '李东升', 1);
INSERT INTO `student` VALUES (16, '1610121004', '王可儿', 1);
INSERT INTO `student` VALUES (17, '1610121005', '林楠', 1);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teaID` int(11) NOT NULL AUTO_INCREMENT,
  `teaNumber` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teaName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isadmin` bit(1) NULL DEFAULT b'0' COMMENT '0为不是管理员，1是管理员',
  PRIMARY KEY (`teaID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (14, '1010121001', '测试1', b'1');
INSERT INTO `teacher` VALUES (15, '1010121002', '张三', b'1');

-- ----------------------------
-- Table structure for teacher_class
-- ----------------------------
DROP TABLE IF EXISTS `teacher_class`;
CREATE TABLE `teacher_class`  (
  `tcID` int(11) NOT NULL AUTO_INCREMENT,
  `teaNumber` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classID` int(11) NOT NULL,
  PRIMARY KEY (`tcID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher_permission
-- ----------------------------
DROP TABLE IF EXISTS `teacher_permission`;
CREATE TABLE `teacher_permission`  (
  `tpID` int(11) NOT NULL AUTO_INCREMENT,
  `teaNumber` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`tpID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '32加密',
  `type` int(11) NOT NULL COMMENT '学生或者教师或者管理员',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (15, '1010121001', '61E53FE926315CF1FE674B7008241EFF', 2, '115.61.106.43');
INSERT INTO `user` VALUES (16, '1010121002', '61E53FE926315CF1FE674B7008241EFF', 2, NULL);
INSERT INTO `user` VALUES (22, '1610121001', 'D57E597A49DB90162DC2611F32A409C9', 3, NULL);
INSERT INTO `user` VALUES (23, '1610121002', 'D57E597A49DB90162DC2611F32A409C9', 3, '115.61.106.43');
INSERT INTO `user` VALUES (24, '1610121003', 'C833AA3E1957C144638D91BC473C47E6', 3, NULL);
INSERT INTO `user` VALUES (25, '1610121004', 'D9D555DC32A1D8928F0AB02C321AB20B', 3, NULL);
INSERT INTO `user` VALUES (26, '1610121005', '2F707B93298A14D61AA39D7E9EADB28D', 3, NULL);

SET FOREIGN_KEY_CHECKS = 1;
