/*
 Navicat Premium Data Transfer

 Source Server         : demo20210619
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : java-crud

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 24/07/2021 14:23:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `create_time` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '张三', 23, '男', '北京市', '13867675656', '2021-07-24 14:20:29');
INSERT INTO `user` VALUES (3, '李四', 21, '男', '上海市', '13978786565', '2021-07-24 14:20:23');
INSERT INTO `user` VALUES (5, '王五', 21, '男', '广州市', '13978786565', '2021-07-24 14:20:14');
INSERT INTO `user` VALUES (6, '赵六', 23, '男', '深圳市', '13867675656', '2021-07-24 14:20:09');
INSERT INTO `user` VALUES (7, '张飞', 21, '男', '成都市', '13978786565', '2021-07-24 14:20:03');
INSERT INTO `user` VALUES (8, '关羽', 23, '男', '重庆市', '13867675656', '2021-07-24 14:19:57');
INSERT INTO `user` VALUES (9, '赵云', 21, '男', '长沙市', '13978786565', '2021-07-24 14:19:52');
INSERT INTO `user` VALUES (10, '张辽', 23, '男', '南宁市', '13867675656', '2021-07-24 14:19:46');
INSERT INTO `user` VALUES (12, '许褚', 23, '男', '昆明市', '13867674565', '2021-07-24 14:19:40');
INSERT INTO `user` VALUES (14, '典韦', 24, '男', '贵阳市', '13677189900', '2021-07-24 14:19:34');
INSERT INTO `user` VALUES (16, '张三三', 12, '男', '南宁市', '13566778899', '2021-07-22 04:40:09');
INSERT INTO `user` VALUES (19, '李四四', 35, '男', '南宁市', '13566778899', '2021-07-24 14:20:58');

SET FOREIGN_KEY_CHECKS = 1;
