/*
Navicat MySQL Data Transfer

Source Server         : 172.16.10.229
Source Server Version : 50614
Source Host           : 172.16.10.229:3306
Source Database       : zhicall_op

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2017-12-13 14:11:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `op_auto_job`
-- ----------------------------
DROP TABLE IF EXISTS `op_auto_job`;
CREATE TABLE `op_auto_job` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) DEFAULT NULL,
  `job_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `uuid` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `remark` text,
  `flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `op_config`
-- ----------------------------
DROP TABLE IF EXISTS `op_config`;
CREATE TABLE `op_config` (
  `uuid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `name` varchar(255) NOT NULL COMMENT '项目名称',
  `ip` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `tname` varchar(255) DEFAULT NULL,
  `sdir` varchar(255) DEFAULT NULL,
  `wname` varchar(64) DEFAULT NULL,
  `lname` varchar(16) DEFAULT NULL,
  `tdir` varchar(255) DEFAULT NULL,
  `bdir` varchar(255) DEFAULT NULL,
  `wdir` varchar(255) DEFAULT NULL,
  `logpath` varchar(255) DEFAULT NULL,
  `javaHomePath` varchar(255) DEFAULT NULL,
  `zhicallConfig` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

