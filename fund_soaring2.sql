/*
Navicat MySQL Data Transfer

Source Server         : 118.31.187.222_3306
Source Server Version : 50568
Source Host           : 118.31.187.222:3306
Source Database       : fund_soaring

Target Server Type    : MYSQL
Target Server Version : 50568
File Encoding         : 65001

Date: 2023-09-23 12:12:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for db_user
-- ----------------------------
DROP TABLE IF EXISTS `db_user`;
CREATE TABLE `db_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '所属角色（外键）',
  `user_name` varchar(20) NOT NULL COMMENT '管理员名称',
  `email` varchar(255) DEFAULT NULL,
  `user_fullname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `user_phone` varchar(20) NOT NULL COMMENT '管理员手机号',
  `user_password` varchar(200) NOT NULL COMMENT '管理员密码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `delete_status` int(2) DEFAULT '0' COMMENT '是否删除0: 未删除, 1表示删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `db_admin_user_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='管理用户信息表';

-- ----------------------------
-- Table structure for f_fund_info
-- ----------------------------
DROP TABLE IF EXISTS `f_fund_info`;
CREATE TABLE `f_fund_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fund_name` varchar(255) DEFAULT NULL COMMENT '基金名称',
  `fund_code` varchar(255) DEFAULT NULL COMMENT '基金代码',
  `max_net_worth_date` timestamp NULL DEFAULT NULL COMMENT '最大净值日期',
  `max_net_worth` double(10,4) DEFAULT '0.0000' COMMENT '最大净值',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人ID',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `delete_status` int(11) DEFAULT '0' COMMENT '删除状态: 0 正常, 1 删除',
  `fund_type_id` int(11) DEFAULT NULL COMMENT '基金类型ID',
  `volatility_value` double(10,4) DEFAULT '0.0500' COMMENT '等级利率',
  `bonus_net_worth` double(10,4) DEFAULT '0.0000' COMMENT '分红净值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `f_fund_info_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=316 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for f_fund_level
-- ----------------------------
DROP TABLE IF EXISTS `f_fund_level`;
CREATE TABLE `f_fund_level` (
  `level20` double(10,4) DEFAULT NULL,
  `level19` double(10,4) DEFAULT NULL,
  `level18` double(10,4) DEFAULT NULL,
  `level17` double(10,4) DEFAULT NULL,
  `level16` double(10,4) DEFAULT NULL,
  `level15` double(10,4) DEFAULT NULL,
  `level14` double(10,4) DEFAULT NULL,
  `level13` double(10,4) DEFAULT NULL,
  `level12` double(10,4) DEFAULT NULL,
  `level11` double(10,4) DEFAULT NULL,
  `level10` double(10,4) DEFAULT NULL,
  `level9` double(10,4) DEFAULT NULL,
  `level8` double(10,4) DEFAULT NULL,
  `level7` double(10,4) DEFAULT NULL,
  `level6` double(10,4) DEFAULT NULL,
  `level5` double(10,4) DEFAULT NULL,
  `level4` double(10,4) DEFAULT NULL,
  `level3` double(10,4) DEFAULT NULL,
  `level2` double(10,4) DEFAULT NULL,
  `level1` double(10,4) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fund_info_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `f_fund_level_id_uindex` (`id`) USING BTREE,
  UNIQUE KEY `f_fund_level_fund_info_code_uindex` (`fund_info_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=121733 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for f_fund_net_worth
-- ----------------------------
DROP TABLE IF EXISTS `f_fund_net_worth`;
CREATE TABLE `f_fund_net_worth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fund_info_code` varchar(255) DEFAULT NULL,
  `fund_day` timestamp NULL DEFAULT NULL COMMENT '当天',
  `fund_net_worth` double(10,4) DEFAULT NULL COMMENT '当天净值',
  `level_number` int(11) DEFAULT NULL COMMENT '次数',
  `level_front` double(10,4) DEFAULT NULL COMMENT '前净值',
  `level_behind` double(10,4) DEFAULT NULL COMMENT '后净值',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '最新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `f_fund_net_worth_id_uindex` (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `f_fund_net_worth_pk_123` (`fund_info_code`,`fund_day`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=172023 DEFAULT CHARSET=utf8 COMMENT='基金净值';

-- ----------------------------
-- Table structure for f_fund_net_worth_temp
-- ----------------------------
DROP TABLE IF EXISTS `f_fund_net_worth_temp`;
CREATE TABLE `f_fund_net_worth_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fund_info_code` varchar(255) DEFAULT NULL,
  `fund_day` timestamp NULL DEFAULT NULL COMMENT '当天',
  `fund_net_worth` double(10,4) DEFAULT NULL COMMENT '当天净值',
  `level_number` int(11) DEFAULT NULL COMMENT '次数',
  `level_front` double(10,4) DEFAULT NULL COMMENT '前净值',
  `level_behind` double(10,4) DEFAULT NULL COMMENT '后净值',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '最新时间',
  `rise_fall` double(10,4) DEFAULT '0.0000' COMMENT '今日涨跌',
  `settlement_new_worth` double(10,4) DEFAULT '0.0000' COMMENT '结算净值',
  `settlement_day` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '结算日期',
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=630 DEFAULT CHARSET=utf8 COMMENT='基金最新净值';

-- ----------------------------
-- Table structure for f_fund_type
-- ----------------------------
DROP TABLE IF EXISTS `f_fund_type`;
CREATE TABLE `f_fund_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fund_type_number` int(11) NOT NULL COMMENT '编号',
  `fund_type_name` varchar(50) NOT NULL COMMENT '类型名称',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人ID',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `delete_status` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除: 0 未删除 ; 1 已删除',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fund_type_id_uindex` (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='基金类型';

-- ----------------------------
-- Table structure for u_fund_level_money
-- ----------------------------
DROP TABLE IF EXISTS `u_fund_level_money`;
CREATE TABLE `u_fund_level_money` (
  `fund_info_code` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money1` double(10,4) DEFAULT '20.0000',
  `money2` double(10,4) DEFAULT '40.0000',
  `money3` double(10,4) DEFAULT '60.0000',
  `money4` double(10,4) DEFAULT '80.0000',
  `money5` double(10,4) DEFAULT '100.0000',
  `money6` double(10,4) DEFAULT '120.0000',
  `money7` double(10,4) DEFAULT '140.0000',
  `money8` double(10,4) DEFAULT '160.0000',
  `money9` double(10,4) DEFAULT '180.0000',
  `money10` double(10,4) DEFAULT '200.0000',
  `money11` double(10,4) DEFAULT '220.0000',
  `money12` double(10,4) DEFAULT '240.0000',
  `money13` double(10,4) DEFAULT '260.0000',
  `money14` double(10,4) DEFAULT '280.0000',
  `money15` double(10,4) DEFAULT '300.0000',
  `money16` double(10,4) DEFAULT '320.0000',
  `money17` double(10,4) DEFAULT '340.0000',
  `money18` double(10,4) DEFAULT '360.0000',
  `money19` double(10,4) DEFAULT '380.0000',
  `money20` double(10,4) DEFAULT '400.0000',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `f_fund_level_money_id_uindex` (`id`) USING BTREE,
  UNIQUE KEY `u_fund_level_money_pk` (`fund_info_code`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=675 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for u_fund_transaction_purchase
-- ----------------------------
DROP TABLE IF EXISTS `u_fund_transaction_purchase`;
CREATE TABLE `u_fund_transaction_purchase` (
  `sell_id` int(11) DEFAULT NULL COMMENT '卖出ID',
  `purchase_net_worth` double(10,4) DEFAULT NULL COMMENT '买入净值',
  `state` int(11) DEFAULT '0' COMMENT '卖出状态: 0 未买入, 1 已卖入,2 已卖出,3 最低净值',
  `income_amount` double(10,4) DEFAULT NULL COMMENT '收益金额',
  `today_net_worth` double(10,4) DEFAULT NULL COMMENT '今日净值',
  `today_time` timestamp NULL DEFAULT NULL COMMENT '今日时间',
  `purchase_amount_all` double(10,4) DEFAULT '0.0000' COMMENT '买入总金额',
  `purchase_rate` double(10,6) DEFAULT '0.000000' COMMENT '买入费率',
  `purchase_rate_amount` double(10,4) DEFAULT '0.0000' COMMENT '买入费率金额',
  `purchase_amount` double(10,2) DEFAULT NULL COMMENT '买入金额',
  `purchase_share` double(10,2) DEFAULT NULL COMMENT '买入份额',
  `purchase_time` timestamp NULL DEFAULT NULL COMMENT '买入时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `fund_info_code` varchar(255) DEFAULT NULL COMMENT '基金编号',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `yield` double(10,6) DEFAULT NULL COMMENT '收益率',
  `minimum_new_worth` int(11) DEFAULT '0' COMMENT '是否是最小净值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_fund_transaction_purchase_id_uindex` (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=5158 DEFAULT CHARSET=utf8 COMMENT='基金交易卖出';

-- ----------------------------
-- Table structure for u_fund_transaction_sell
-- ----------------------------
DROP TABLE IF EXISTS `u_fund_transaction_sell`;
CREATE TABLE `u_fund_transaction_sell` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fund_info_code` varchar(255) DEFAULT NULL COMMENT '基金编号',
  `sell_net_worth` double(10,4) DEFAULT NULL COMMENT '卖出净值',
  `sell_share` double(10,4) DEFAULT NULL COMMENT '卖出份额',
  `sell_amount_all` double(10,4) DEFAULT '0.0000' COMMENT '卖出总金额',
  `sell_rate` double(10,6) DEFAULT '0.000000' COMMENT '卖出费率',
  `sell_rate_amount` double(10,4) DEFAULT '0.0000' COMMENT '卖出费率金额',
  `sell_amount` double(10,2) DEFAULT NULL COMMENT '卖出金额',
  `sell_time` timestamp NULL DEFAULT NULL COMMENT '卖出时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `purchase_id` int(11) DEFAULT NULL COMMENT '卖出ID',
  `income_amount` double(10,2) DEFAULT NULL COMMENT '收益金额',
  `yield` double(10,4) DEFAULT NULL COMMENT '收益率',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_fund_transaction_sell_id_uindex` (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=3077 DEFAULT CHARSET=utf8 COMMENT='基金交易买入';

-- ----------------------------
-- Table structure for u_user_fund
-- ----------------------------
DROP TABLE IF EXISTS `u_user_fund`;
CREATE TABLE `u_user_fund` (
  `user_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `delete_status` int(11) DEFAULT '0' COMMENT '是否删除: 0 未删除, 1 已删除',
  `fund_info_code` varchar(255) DEFAULT NULL,
  `fund_info_name_number` int(11) DEFAULT NULL COMMENT '基金的名称编号',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `total_amount` double(10,2) DEFAULT '0.00' COMMENT '总金额',
  `total_share` double(10,4) DEFAULT '0.0000' COMMENT '总份额',
  `new_amount` double(10,2) DEFAULT '0.00' COMMENT '持有金额',
  `profit_amount` double(10,2) DEFAULT '0.00' COMMENT '盈利金额',
  `today_profit_amount` double(10,2) DEFAULT '0.00' COMMENT '今日盈收金额',
  `purchase_rate` double(10,4) DEFAULT '0.0000' COMMENT '买入费率',
  `sell_rate` double(10,4) DEFAULT '0.0000' COMMENT '卖出费率',
  `cumulative_gain` double(10,4) DEFAULT '0.0000' COMMENT '累计收益',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_user_fund_pk` (`user_id`,`fund_info_code`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=625 DEFAULT CHARSET=utf8;
