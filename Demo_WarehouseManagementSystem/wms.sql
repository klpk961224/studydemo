/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : wms

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-04-03 01:08:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  `ordernum` int(11) DEFAULT NULL COMMENT '排序码【为了调试显示顺序】',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '部门列表', '1', '总经办', '无锡', '1', '1', '2021-03-27 14:06:32');
INSERT INTO `sys_dept` VALUES ('2', '1', '系统管理部', '0', '系统管理部', '无锡', '1', '2', '2021-03-27 17:43:16');
INSERT INTO `sys_dept` VALUES ('3', '1', '销售部', '0', '销售部', '无锡', '1', '3', '2021-03-27 17:44:09');
INSERT INTO `sys_dept` VALUES ('4', '1', '财务部', '0', '财务部', '无锡', '1', '4', '2021-03-27 17:44:33');

-- ----------------------------
-- Table structure for sys_loginfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_loginfo`;
CREATE TABLE `sys_loginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) DEFAULT NULL,
  `loginip` varchar(255) DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_loginfo
-- ----------------------------
INSERT INTO `sys_loginfo` VALUES ('1', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-25 12:06:19');
INSERT INTO `sys_loginfo` VALUES ('2', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-25 12:30:29');
INSERT INTO `sys_loginfo` VALUES ('3', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-25 12:31:13');
INSERT INTO `sys_loginfo` VALUES ('4', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-25 17:10:54');
INSERT INTO `sys_loginfo` VALUES ('5', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-25 18:45:13');
INSERT INTO `sys_loginfo` VALUES ('6', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-25 22:35:24');
INSERT INTO `sys_loginfo` VALUES ('7', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-25 23:12:11');
INSERT INTO `sys_loginfo` VALUES ('8', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-25 23:49:58');
INSERT INTO `sys_loginfo` VALUES ('9', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-25 23:55:12');
INSERT INTO `sys_loginfo` VALUES ('10', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-26 16:40:32');
INSERT INTO `sys_loginfo` VALUES ('11', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-26 16:41:46');
INSERT INTO `sys_loginfo` VALUES ('12', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-26 17:25:36');
INSERT INTO `sys_loginfo` VALUES ('13', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-27 00:12:36');
INSERT INTO `sys_loginfo` VALUES ('14', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-27 01:02:58');
INSERT INTO `sys_loginfo` VALUES ('15', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-27 17:29:04');
INSERT INTO `sys_loginfo` VALUES ('16', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-28 15:37:25');
INSERT INTO `sys_loginfo` VALUES ('17', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-28 16:39:13');
INSERT INTO `sys_loginfo` VALUES ('18', '超级管理员-system', '0:0:0:0:0:0:0:1', '2021-03-28 16:51:44');
INSERT INTO `sys_loginfo` VALUES ('19', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-28 16:52:10');
INSERT INTO `sys_loginfo` VALUES ('20', '超级管理员-system', '0:0:0:0:0:0:0:1', '2021-03-28 16:53:31');
INSERT INTO `sys_loginfo` VALUES ('21', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-28 22:00:09');
INSERT INTO `sys_loginfo` VALUES ('22', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-28 22:17:37');
INSERT INTO `sys_loginfo` VALUES ('23', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-28 22:24:36');
INSERT INTO `sys_loginfo` VALUES ('24', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-28 22:38:37');
INSERT INTO `sys_loginfo` VALUES ('25', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-28 22:51:52');
INSERT INTO `sys_loginfo` VALUES ('26', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-28 23:00:31');
INSERT INTO `sys_loginfo` VALUES ('27', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 16:12:18');
INSERT INTO `sys_loginfo` VALUES ('28', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 17:05:03');
INSERT INTO `sys_loginfo` VALUES ('29', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 17:17:53');
INSERT INTO `sys_loginfo` VALUES ('30', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 17:43:56');
INSERT INTO `sys_loginfo` VALUES ('31', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 17:49:21');
INSERT INTO `sys_loginfo` VALUES ('32', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 20:50:26');
INSERT INTO `sys_loginfo` VALUES ('33', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 21:42:42');
INSERT INTO `sys_loginfo` VALUES ('34', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 22:57:23');
INSERT INTO `sys_loginfo` VALUES ('35', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 23:02:41');
INSERT INTO `sys_loginfo` VALUES ('36', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 23:06:01');
INSERT INTO `sys_loginfo` VALUES ('37', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 23:17:38');
INSERT INTO `sys_loginfo` VALUES ('38', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 23:28:22');
INSERT INTO `sys_loginfo` VALUES ('39', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-29 23:28:50');
INSERT INTO `sys_loginfo` VALUES ('40', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-30 20:23:53');
INSERT INTO `sys_loginfo` VALUES ('41', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-30 20:29:50');
INSERT INTO `sys_loginfo` VALUES ('42', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-30 20:32:53');
INSERT INTO `sys_loginfo` VALUES ('43', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-30 20:46:24');
INSERT INTO `sys_loginfo` VALUES ('44', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-30 20:58:00');
INSERT INTO `sys_loginfo` VALUES ('45', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-30 21:04:27');
INSERT INTO `sys_loginfo` VALUES ('46', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-30 21:22:44');
INSERT INTO `sys_loginfo` VALUES ('47', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-30 21:28:23');
INSERT INTO `sys_loginfo` VALUES ('48', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-31 15:53:43');
INSERT INTO `sys_loginfo` VALUES ('49', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-31 16:38:49');
INSERT INTO `sys_loginfo` VALUES ('50', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-31 16:47:16');
INSERT INTO `sys_loginfo` VALUES ('51', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-31 17:02:06');
INSERT INTO `sys_loginfo` VALUES ('52', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-31 17:22:18');
INSERT INTO `sys_loginfo` VALUES ('53', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-31 17:28:04');
INSERT INTO `sys_loginfo` VALUES ('54', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-03-31 18:29:32');
INSERT INTO `sys_loginfo` VALUES ('55', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-04-02 19:28:48');
INSERT INTO `sys_loginfo` VALUES ('56', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-04-02 19:32:02');
INSERT INTO `sys_loginfo` VALUES ('57', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-04-02 23:38:14');
INSERT INTO `sys_loginfo` VALUES ('58', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-04-02 23:42:13');
INSERT INTO `sys_loginfo` VALUES ('59', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-04-02 23:54:42');
INSERT INTO `sys_loginfo` VALUES ('60', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-04-02 23:57:15');
INSERT INTO `sys_loginfo` VALUES ('61', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-04-03 00:22:02');
INSERT INTO `sys_loginfo` VALUES ('62', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-04-03 00:50:00');
INSERT INTO `sys_loginfo` VALUES ('63', '超级管理员-admin', '0:0:0:0:0:0:0:1', '2021-04-03 01:02:01');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `createtime` datetime DEFAULT NULL,
  `opername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '公告测试标题', '公告测试内容', '2021-03-27 00:15:24', '超级管理员-admin');
INSERT INTO `sys_notice` VALUES ('2', '11', '11', '2021-03-27 01:03:50', '超级管理员');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '权限类型[menu/permission]',
  `title` varchar(255) DEFAULT NULL,
  `percode` varchar(255) DEFAULT NULL COMMENT '权限编码[只有type= permission才有  user:view]',
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', 'menu', '业务管理', null, '&#xe63c;', '', '', '1', '1', '1');
INSERT INTO `sys_permission` VALUES ('2', '1', 'menu', '基础管理', null, '&#xe857;', '', '', '0', '2', '1');
INSERT INTO `sys_permission` VALUES ('3', '1', 'menu', '进货管理', null, '&#xe645;', '', null, '0', '3', '1');
INSERT INTO `sys_permission` VALUES ('4', '1', 'menu', '销售管理', null, '&#xe611;', '', '', '0', '4', '1');
INSERT INTO `sys_permission` VALUES ('5', '1', 'menu', '系统管理', null, '&#xe614;', '', '', '0', '5', '1');
INSERT INTO `sys_permission` VALUES ('6', '1', 'menu', '其它管理', null, '&#xe628;', '', '', '0', '6', '1');
INSERT INTO `sys_permission` VALUES ('7', '2', 'menu', '客户管理', null, '&#xe651;', 'yw/toCustomerManager', '', '0', '7', '1');
INSERT INTO `sys_permission` VALUES ('8', '2', 'menu', '供应商管理', null, '&#xe658;', 'yw/toProviderManager', '', '0', '8', '1');
INSERT INTO `sys_permission` VALUES ('9', '2', 'menu', '商品管理', null, '&#xe657;', 'yw/toGoodsManager', '', '0', '9', '1');
INSERT INTO `sys_permission` VALUES ('10', '3', 'menu', '商品进货', null, '&#xe756;', 'yw/toImportManager', '', '0', '10', '1');
INSERT INTO `sys_permission` VALUES ('11', '3', 'menu', '商品退货查询', null, '&#xe65a;', 'yw/toOutportManager', '', '0', '11', '1');
INSERT INTO `sys_permission` VALUES ('12', '4', 'menu', '商品销售', null, '&#xe65b;', 'yw/toSalesManager', '', '0', '12', '1');
INSERT INTO `sys_permission` VALUES ('13', '4', 'menu', '销售退货查询', null, '&#xe770;', 'yw/toSalesbackManager', '', '0', '13', '1');
INSERT INTO `sys_permission` VALUES ('14', '5', 'menu', '部门管理', null, '&#xe770;', 'toDeptManager', '', '0', '14', '1');
INSERT INTO `sys_permission` VALUES ('15', '5', 'menu', '菜单管理', null, '&#xe663;', 'toMenuManager', '', '0', '15', '1');
INSERT INTO `sys_permission` VALUES ('16', '5', 'menu', '权限管理', '', '&#xe857;', 'toPermissionManager', '', '0', '16', '1');
INSERT INTO `sys_permission` VALUES ('17', '5', 'menu', '角色管理', '', '&#xe650;', 'toRoleManager', '', '0', '17', '1');
INSERT INTO `sys_permission` VALUES ('18', '5', 'menu', '用户管理', '', '&#xe612;', 'toUserManager', '', '0', '18', '1');
INSERT INTO `sys_permission` VALUES ('21', '6', 'menu', '登陆日志', null, '&#xe675;', 'toLoginfoManager', '', '0', '21', '1');
INSERT INTO `sys_permission` VALUES ('22', '6', 'menu', '系统公告', null, '&#xe756;', 'toNoticeManager', null, '0', '22', '1');
INSERT INTO `sys_permission` VALUES ('23', '6', 'menu', '图标管理', null, '&#xe670;', 'resources/page/icon.html', null, '0', '23', '1');
INSERT INTO `sys_permission` VALUES ('30', '14', 'permission', '添加部门', 'dept:create', '', null, null, '0', '24', '1');
INSERT INTO `sys_permission` VALUES ('31', '14', 'permission', '修改部门', 'dept:update', '', null, null, '0', '26', '1');
INSERT INTO `sys_permission` VALUES ('32', '14', 'permission', '删除部门', 'dept:delete', '', null, null, '0', '27', '1');
INSERT INTO `sys_permission` VALUES ('34', '15', 'permission', '添加菜单', 'menu:create', '', '', '', '0', '29', '1');
INSERT INTO `sys_permission` VALUES ('35', '15', 'permission', '修改菜单', 'menu:update', '', null, null, '0', '30', '1');
INSERT INTO `sys_permission` VALUES ('36', '15', 'permission', '删除菜单', 'menu:delete', '', null, null, '0', '31', '1');
INSERT INTO `sys_permission` VALUES ('38', '16', 'permission', '添加权限', 'permission:create', '', null, null, '0', '33', '1');
INSERT INTO `sys_permission` VALUES ('39', '16', 'permission', '修改权限', 'permission:update', '', null, null, '0', '34', '1');
INSERT INTO `sys_permission` VALUES ('40', '16', 'permission', '删除权限', 'permission:delete', '', null, null, '0', '35', '1');
INSERT INTO `sys_permission` VALUES ('42', '17', 'permission', '添加角色', 'role:create', '', null, null, '0', '37', '1');
INSERT INTO `sys_permission` VALUES ('43', '17', 'permission', '修改角色', 'role:update', '', null, null, '0', '38', '1');
INSERT INTO `sys_permission` VALUES ('44', '17', 'permission', '删除角色', 'role:delete', '', null, null, '0', '39', '1');
INSERT INTO `sys_permission` VALUES ('46', '17', 'permission', '分配权限', 'role:selectPermission', '', null, null, '0', '41', '1');
INSERT INTO `sys_permission` VALUES ('47', '18', 'permission', '添加用户', 'user:create', '', null, null, '0', '42', '1');
INSERT INTO `sys_permission` VALUES ('48', '18', 'permission', '修改用户', 'user:update', '', null, null, '0', '43', '1');
INSERT INTO `sys_permission` VALUES ('49', '18', 'permission', '删除用户', 'user:delete', '', null, null, '0', '44', '1');
INSERT INTO `sys_permission` VALUES ('51', '18', 'permission', '用户分配角色', 'user:selectRole', '', null, null, '0', '46', '1');
INSERT INTO `sys_permission` VALUES ('52', '18', 'permission', '重置密码', 'user:resetPwd', null, null, null, '0', '47', '1');
INSERT INTO `sys_permission` VALUES ('53', '14', 'permission', '部门查询', 'dept:view', null, null, null, '0', '48', '1');
INSERT INTO `sys_permission` VALUES ('54', '15', 'permission', '菜单查询', 'menu:view', null, null, null, '0', '49', '1');
INSERT INTO `sys_permission` VALUES ('55', '16', 'permission', '权限查询', 'permission:view', null, null, null, '0', '50', '1');
INSERT INTO `sys_permission` VALUES ('56', '17', 'permission', '角色查询', 'role:view', null, null, null, '0', '51', '1');
INSERT INTO `sys_permission` VALUES ('57', '18', 'permission', '用户查询', 'user:view', null, null, null, '0', '52', '1');
INSERT INTO `sys_permission` VALUES ('68', '7', 'permission', '客户查询', 'customer:view', null, null, null, null, '60', '1');
INSERT INTO `sys_permission` VALUES ('69', '7', 'permission', '客户添加', 'customer:create', null, null, null, null, '61', '1');
INSERT INTO `sys_permission` VALUES ('70', '7', 'permission', '客户修改', 'customer:update', null, null, null, null, '62', '1');
INSERT INTO `sys_permission` VALUES ('71', '7', 'permission', '客户删除', 'customer:delete', null, null, null, null, '63', '1');
INSERT INTO `sys_permission` VALUES ('73', '21', 'permission', '日志查询', 'info:view', null, null, null, null, '65', '1');
INSERT INTO `sys_permission` VALUES ('74', '21', 'permission', '日志删除', 'info:delete', null, null, null, null, '66', '1');
INSERT INTO `sys_permission` VALUES ('75', '21', 'permission', '日志批量删除', 'info:batchdelete', null, null, null, null, '67', '1');
INSERT INTO `sys_permission` VALUES ('76', '22', 'permission', '公告查询', 'notice:view', null, null, null, null, '68', '1');
INSERT INTO `sys_permission` VALUES ('77', '22', 'permission', '公告添加', 'notice:create', null, null, null, null, '69', '1');
INSERT INTO `sys_permission` VALUES ('78', '22', 'permission', '公告修改', 'notice:update', null, null, null, null, '70', '1');
INSERT INTO `sys_permission` VALUES ('79', '22', 'permission', '公告删除', 'notice:delete', null, null, null, null, '71', '1');
INSERT INTO `sys_permission` VALUES ('81', '8', 'permission', '供应商查询', 'provider:view', null, null, null, null, '73', '1');
INSERT INTO `sys_permission` VALUES ('82', '8', 'permission', '供应商添加', 'provider:create', null, null, null, null, '74', '1');
INSERT INTO `sys_permission` VALUES ('83', '8', 'permission', '供应商修改', 'provider:update', null, null, null, null, '75', '1');
INSERT INTO `sys_permission` VALUES ('84', '8', 'permission', '供应商删除', 'provider:delete', null, null, null, null, '76', '1');
INSERT INTO `sys_permission` VALUES ('86', '22', 'permission', '公告查看', 'notice:viewnotice', null, null, null, null, '78', '1');
INSERT INTO `sys_permission` VALUES ('91', '9', 'permission', '商品查询', 'goods:view', null, null, null, '0', '79', '1');
INSERT INTO `sys_permission` VALUES ('92', '9', 'permission', '商品添加', 'goods:create', null, null, null, '0', '80', '1');
INSERT INTO `sys_permission` VALUES ('116', '9', 'permission', '商品删除', 'goods:delete', null, null, null, '0', '84', '1');
INSERT INTO `sys_permission` VALUES ('117', '9', 'permission', '商品修改', 'goods:update', null, null, null, '0', '85', '1');
INSERT INTO `sys_permission` VALUES ('118', '9', 'permission', '商品查询', 'goods:view', null, null, null, '0', '86', '1');
INSERT INTO `sys_permission` VALUES ('119', '22', 'permission', '公告批量删除', 'notice:batchdelete', null, null, null, '0', '87', '1');
INSERT INTO `sys_permission` VALUES ('122', '6', 'menu', '缓存管理', null, '&#xe630', 'toCacheManager', '_black', '1', '88', '1');
INSERT INTO `sys_permission` VALUES ('123', '122', 'permission', '同步缓存', 'cache:syncCache', null, null, null, '0', '89', '1');
INSERT INTO `sys_permission` VALUES ('124', '122', 'permission', '清空缓存', 'cache:removeAllCache', null, null, null, '0', '90', '1');
INSERT INTO `sys_permission` VALUES ('125', '0', 'menu', '用户中心', null, '&#xe613;', '', '', '1', '1', '1');
INSERT INTO `sys_permission` VALUES ('126', '0', 'menu', '系统设置', null, '&#xe620;', '', '', '1', '1', '1');
INSERT INTO `sys_permission` VALUES ('127', '10', 'permission', '进货添加', 'import:create', null, null, null, '0', '91', '1');
INSERT INTO `sys_permission` VALUES ('128', '10', 'permission', '进货编辑', 'import:update', null, null, null, '0', '92', '1');
INSERT INTO `sys_permission` VALUES ('129', '10', 'permission', '进货删除', 'import:delete', null, null, null, '0', '93', '1');
INSERT INTO `sys_permission` VALUES ('130', '10', 'permission', '退货', 'import:back', null, null, null, '0', '94', '1');
INSERT INTO `sys_permission` VALUES ('131', '11', 'permission', '退货删除', 'outport:delete', null, null, null, '0', '95', '1');
INSERT INTO `sys_permission` VALUES ('132', '12', 'permission', '销售添加', 'sales:create', null, null, null, '0', '96', '1');
INSERT INTO `sys_permission` VALUES ('133', '12', 'permission', '销售编辑', 'sales:update', null, null, null, '0', '97', '1');
INSERT INTO `sys_permission` VALUES ('134', '12', 'permission', '销售删除', 'sales:delete', null, null, null, '0', '98', '1');
INSERT INTO `sys_permission` VALUES ('135', '12', 'permission', '销售退回', 'sales:back', null, null, null, '0', '99', '1');
INSERT INTO `sys_permission` VALUES ('136', '13', 'permission', '销售退回删除', 'salesback:delete', null, null, null, '0', '100', '1');
INSERT INTO `sys_permission` VALUES ('137', '1', 'menu', '数据统计', null, '&#xe63c;', 'report/toReport', '', '0', '101', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '拥有所有菜单权限', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('4', '基础数据管理员', '基础数据管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('6', '销售管理员', '销售管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('8', '系统管理员', '管理所有的系统设置', '1', '2020-02-24 07:46:27');
INSERT INTO `sys_role` VALUES ('10', '仓库管理员', '仓库管理员', '1', '2020-03-06 03:31:36');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`pid`,`rid`) USING BTREE,
  KEY `sys_role_permission_ibfk_1` (`pid`) USING BTREE,
  KEY `sys_role_permission_ibfk_2` (`rid`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE,
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('4', '1');
INSERT INTO `sys_role_permission` VALUES ('6', '1');
INSERT INTO `sys_role_permission` VALUES ('8', '1');
INSERT INTO `sys_role_permission` VALUES ('10', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('4', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('10', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('6', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('8', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('8', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('4', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('4', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('4', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('10', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('10', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('6', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('6', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('8', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('8', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('8', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '17');
INSERT INTO `sys_role_permission` VALUES ('8', '17');
INSERT INTO `sys_role_permission` VALUES ('1', '18');
INSERT INTO `sys_role_permission` VALUES ('8', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '21');
INSERT INTO `sys_role_permission` VALUES ('8', '21');
INSERT INTO `sys_role_permission` VALUES ('1', '22');
INSERT INTO `sys_role_permission` VALUES ('8', '22');
INSERT INTO `sys_role_permission` VALUES ('1', '23');
INSERT INTO `sys_role_permission` VALUES ('8', '23');
INSERT INTO `sys_role_permission` VALUES ('1', '30');
INSERT INTO `sys_role_permission` VALUES ('8', '30');
INSERT INTO `sys_role_permission` VALUES ('1', '31');
INSERT INTO `sys_role_permission` VALUES ('8', '31');
INSERT INTO `sys_role_permission` VALUES ('1', '32');
INSERT INTO `sys_role_permission` VALUES ('8', '32');
INSERT INTO `sys_role_permission` VALUES ('1', '34');
INSERT INTO `sys_role_permission` VALUES ('8', '34');
INSERT INTO `sys_role_permission` VALUES ('1', '35');
INSERT INTO `sys_role_permission` VALUES ('8', '35');
INSERT INTO `sys_role_permission` VALUES ('1', '36');
INSERT INTO `sys_role_permission` VALUES ('8', '36');
INSERT INTO `sys_role_permission` VALUES ('1', '38');
INSERT INTO `sys_role_permission` VALUES ('8', '38');
INSERT INTO `sys_role_permission` VALUES ('1', '39');
INSERT INTO `sys_role_permission` VALUES ('8', '39');
INSERT INTO `sys_role_permission` VALUES ('1', '40');
INSERT INTO `sys_role_permission` VALUES ('8', '40');
INSERT INTO `sys_role_permission` VALUES ('1', '42');
INSERT INTO `sys_role_permission` VALUES ('8', '42');
INSERT INTO `sys_role_permission` VALUES ('1', '43');
INSERT INTO `sys_role_permission` VALUES ('8', '43');
INSERT INTO `sys_role_permission` VALUES ('1', '44');
INSERT INTO `sys_role_permission` VALUES ('8', '44');
INSERT INTO `sys_role_permission` VALUES ('1', '46');
INSERT INTO `sys_role_permission` VALUES ('8', '46');
INSERT INTO `sys_role_permission` VALUES ('1', '47');
INSERT INTO `sys_role_permission` VALUES ('8', '47');
INSERT INTO `sys_role_permission` VALUES ('1', '48');
INSERT INTO `sys_role_permission` VALUES ('8', '48');
INSERT INTO `sys_role_permission` VALUES ('1', '49');
INSERT INTO `sys_role_permission` VALUES ('8', '49');
INSERT INTO `sys_role_permission` VALUES ('1', '51');
INSERT INTO `sys_role_permission` VALUES ('8', '51');
INSERT INTO `sys_role_permission` VALUES ('1', '52');
INSERT INTO `sys_role_permission` VALUES ('8', '52');
INSERT INTO `sys_role_permission` VALUES ('1', '53');
INSERT INTO `sys_role_permission` VALUES ('8', '53');
INSERT INTO `sys_role_permission` VALUES ('1', '54');
INSERT INTO `sys_role_permission` VALUES ('8', '54');
INSERT INTO `sys_role_permission` VALUES ('1', '55');
INSERT INTO `sys_role_permission` VALUES ('8', '55');
INSERT INTO `sys_role_permission` VALUES ('1', '56');
INSERT INTO `sys_role_permission` VALUES ('8', '56');
INSERT INTO `sys_role_permission` VALUES ('1', '57');
INSERT INTO `sys_role_permission` VALUES ('8', '57');
INSERT INTO `sys_role_permission` VALUES ('1', '68');
INSERT INTO `sys_role_permission` VALUES ('4', '68');
INSERT INTO `sys_role_permission` VALUES ('1', '69');
INSERT INTO `sys_role_permission` VALUES ('1', '70');
INSERT INTO `sys_role_permission` VALUES ('1', '71');
INSERT INTO `sys_role_permission` VALUES ('1', '73');
INSERT INTO `sys_role_permission` VALUES ('8', '73');
INSERT INTO `sys_role_permission` VALUES ('1', '74');
INSERT INTO `sys_role_permission` VALUES ('8', '74');
INSERT INTO `sys_role_permission` VALUES ('1', '75');
INSERT INTO `sys_role_permission` VALUES ('8', '75');
INSERT INTO `sys_role_permission` VALUES ('1', '76');
INSERT INTO `sys_role_permission` VALUES ('8', '76');
INSERT INTO `sys_role_permission` VALUES ('1', '77');
INSERT INTO `sys_role_permission` VALUES ('8', '77');
INSERT INTO `sys_role_permission` VALUES ('1', '78');
INSERT INTO `sys_role_permission` VALUES ('8', '78');
INSERT INTO `sys_role_permission` VALUES ('1', '79');
INSERT INTO `sys_role_permission` VALUES ('8', '79');
INSERT INTO `sys_role_permission` VALUES ('1', '81');
INSERT INTO `sys_role_permission` VALUES ('4', '81');
INSERT INTO `sys_role_permission` VALUES ('1', '82');
INSERT INTO `sys_role_permission` VALUES ('1', '83');
INSERT INTO `sys_role_permission` VALUES ('1', '84');
INSERT INTO `sys_role_permission` VALUES ('1', '86');
INSERT INTO `sys_role_permission` VALUES ('8', '86');
INSERT INTO `sys_role_permission` VALUES ('1', '91');
INSERT INTO `sys_role_permission` VALUES ('4', '91');
INSERT INTO `sys_role_permission` VALUES ('1', '92');
INSERT INTO `sys_role_permission` VALUES ('4', '92');
INSERT INTO `sys_role_permission` VALUES ('1', '116');
INSERT INTO `sys_role_permission` VALUES ('4', '116');
INSERT INTO `sys_role_permission` VALUES ('1', '117');
INSERT INTO `sys_role_permission` VALUES ('4', '117');
INSERT INTO `sys_role_permission` VALUES ('1', '118');
INSERT INTO `sys_role_permission` VALUES ('4', '118');
INSERT INTO `sys_role_permission` VALUES ('1', '119');
INSERT INTO `sys_role_permission` VALUES ('8', '119');
INSERT INTO `sys_role_permission` VALUES ('1', '122');
INSERT INTO `sys_role_permission` VALUES ('8', '122');
INSERT INTO `sys_role_permission` VALUES ('1', '123');
INSERT INTO `sys_role_permission` VALUES ('8', '123');
INSERT INTO `sys_role_permission` VALUES ('1', '124');
INSERT INTO `sys_role_permission` VALUES ('8', '124');
INSERT INTO `sys_role_permission` VALUES ('1', '125');
INSERT INTO `sys_role_permission` VALUES ('8', '125');
INSERT INTO `sys_role_permission` VALUES ('1', '126');
INSERT INTO `sys_role_permission` VALUES ('8', '126');
INSERT INTO `sys_role_permission` VALUES ('1', '127');
INSERT INTO `sys_role_permission` VALUES ('10', '127');
INSERT INTO `sys_role_permission` VALUES ('1', '128');
INSERT INTO `sys_role_permission` VALUES ('10', '128');
INSERT INTO `sys_role_permission` VALUES ('1', '129');
INSERT INTO `sys_role_permission` VALUES ('10', '129');
INSERT INTO `sys_role_permission` VALUES ('1', '130');
INSERT INTO `sys_role_permission` VALUES ('10', '130');
INSERT INTO `sys_role_permission` VALUES ('1', '131');
INSERT INTO `sys_role_permission` VALUES ('10', '131');
INSERT INTO `sys_role_permission` VALUES ('1', '132');
INSERT INTO `sys_role_permission` VALUES ('6', '132');
INSERT INTO `sys_role_permission` VALUES ('1', '133');
INSERT INTO `sys_role_permission` VALUES ('6', '133');
INSERT INTO `sys_role_permission` VALUES ('1', '134');
INSERT INTO `sys_role_permission` VALUES ('6', '134');
INSERT INTO `sys_role_permission` VALUES ('1', '135');
INSERT INTO `sys_role_permission` VALUES ('6', '135');
INSERT INTO `sys_role_permission` VALUES ('1', '136');
INSERT INTO `sys_role_permission` VALUES ('6', '136');
INSERT INTO `sys_role_permission` VALUES ('1', '137');
INSERT INTO `sys_role_permission` VALUES ('4', '137');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `hiredate` datetime DEFAULT NULL,
  `mgr` int(11) DEFAULT NULL COMMENT '上级领导id',
  `available` int(11) DEFAULT '1' COMMENT '是否可用，0不可用，1可用',
  `ordernum` int(11) DEFAULT NULL COMMENT '排序码',
  `type` int(255) DEFAULT NULL COMMENT '用户类型[0超级管理员，1管理员，2普通用户]',
  `imgpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户头像地址',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '盐',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_user_loginname` (`loginname`) USING BTREE COMMENT '登陆名称唯一',
  KEY `FK_sys_dept_sys_user` (`deptid`) USING BTREE,
  CONSTRAINT `FK_sys_dept_sys_user` FOREIGN KEY (`deptid`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '超级管理员', 'system', '3eb4c652f5699becca235f2ad4e2ce7e', '无锡', '1', '超级管理员', '1', '2021-03-28 16:57:31', null, '1', '1', '1', '/images/face.jpg', 'F07527F4053F42758B58A195751F1D21');
INSERT INTO `sys_user` VALUES ('2', '超级管理员', 'admin', 'c196f39f765e0dc5025d4c4e17856218', '地址', '1', '超级管理员', '1', '2021-03-25 11:27:09', null, '1', '2', '1', '2021-03-28/AD1105C797944C5194BADC401887BAAD.png', '06238BDADE514A8CBF22B62C3B49234F');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`rid`) USING BTREE,
  KEY `FK_sys_user_role_1` (`rid`) USING BTREE,
  CONSTRAINT `FK_sys_user_role_1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_sys_user_role_2` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1');

-- ----------------------------
-- Table structure for yw_customer
-- ----------------------------
DROP TABLE IF EXISTS `yw_customer`;
CREATE TABLE `yw_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customername` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `connectionpersion` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of yw_customer
-- ----------------------------
INSERT INTO `yw_customer` VALUES ('1', '客户单位', '214181', '客户地址', '13912345678', '客户', '13912345678', '中国银行', '1111', '客户@qq.com', '12', '1');

-- ----------------------------
-- Table structure for yw_goods
-- ----------------------------
DROP TABLE IF EXISTS `yw_goods`;
CREATE TABLE `yw_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(255) DEFAULT NULL,
  `providerid` int(11) DEFAULT NULL,
  `produceplace` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `goodspackage` varchar(255) DEFAULT NULL,
  `productcode` varchar(255) DEFAULT NULL,
  `promitcode` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `dangernum` int(11) DEFAULT NULL,
  `goodsimg` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_sq0btr2v2lq8gt8b4gb8tlk0i` (`providerid`) USING BTREE,
  CONSTRAINT `yw_goods_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `yw_provider` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of yw_goods
-- ----------------------------
INSERT INTO `yw_goods` VALUES ('1', '塑料袋', '3', '无锡', '64*64*135', '包装', '20210105-1', '1111', '塑料袋', '20', '710', '500', '2021-03-31/BFC67BB72B6E43D09B94DCABB9C0BFA5.png', '1');
INSERT INTO `yw_goods` VALUES ('2', '保鲜盒', '3', '无锡', '规格', '包装', '20210301-1', '1111', '保鲜盒', '20', '1700', '200', '2021-03-31/087CFC4B3FAA492FA7C02B9FEE0E13AA.jpeg', '1');

-- ----------------------------
-- Table structure for yw_import
-- ----------------------------
DROP TABLE IF EXISTS `yw_import`;
CREATE TABLE `yw_import` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paytype` varchar(255) DEFAULT NULL,
  `importtime` datetime DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `importprice` double DEFAULT NULL,
  `providerid` int(11) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `bus_inport_ibfk_1` (`providerid`) USING BTREE,
  KEY `bus_inport_ibfk_2` (`goodsid`) USING BTREE,
  CONSTRAINT `yw_import_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `yw_provider` (`id`),
  CONSTRAINT `yw_import_ibfk_2` FOREIGN KEY (`goodsid`) REFERENCES `yw_goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of yw_import
-- ----------------------------
INSERT INTO `yw_import` VALUES ('2', '支付宝', '2021-03-31 17:31:09', '超级管理员', '2000', 'wq', '5', '3', '2');
INSERT INTO `yw_import` VALUES ('4', '支付宝', '2021-03-31 17:33:06', '超级管理员', '0', '200', '200', '3', '1');
INSERT INTO `yw_import` VALUES ('5', '支付宝', '2021-03-31 18:35:26', '超级管理员', '1400', '', '10', '3', '1');

-- ----------------------------
-- Table structure for yw_outport
-- ----------------------------
DROP TABLE IF EXISTS `yw_outport`;
CREATE TABLE `yw_outport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) DEFAULT NULL,
  `outputtime` datetime DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `outportprice` double(10,2) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of yw_outport
-- ----------------------------
INSERT INTO `yw_outport` VALUES ('1', '3', '支付宝', '2021-03-31 17:33:21', '超级管理员', '200.00', '200', 'youwenti ', '1');

-- ----------------------------
-- Table structure for yw_provider
-- ----------------------------
DROP TABLE IF EXISTS `yw_provider`;
CREATE TABLE `yw_provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providername` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `connectionperson` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of yw_provider
-- ----------------------------
INSERT INTO `yw_provider` VALUES ('1', '供应商名称', '214181', '供应商地址', '13912345678', '供应商', '13912345678', '中国银行', '1111', '11', '1111', '1');
INSERT INTO `yw_provider` VALUES ('3', '自己', '2124181', '自己地址', '13912345678', '自己', '13912345678', '中国银行', '1111', '1111', '1111', '1');

-- ----------------------------
-- Table structure for yw_sales
-- ----------------------------
DROP TABLE IF EXISTS `yw_sales`;
CREATE TABLE `yw_sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) DEFAULT NULL,
  `salestime` datetime DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `saleprice` decimal(10,2) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of yw_sales
-- ----------------------------
INSERT INTO `yw_sales` VALUES ('2', '1', '支付宝', '2021-03-31 17:38:33', '超级管理员', '500', 'we ', '50.00', '1');
INSERT INTO `yw_sales` VALUES ('3', '1', '支付宝', '2021-03-31 18:33:02', '超级管理员', '300', 'r', '40.00', '2');
INSERT INTO `yw_sales` VALUES ('4', '1', '支付宝', '2021-03-31 18:33:26', '超级管理员', '100', 'ghj ', '40.00', '1');
INSERT INTO `yw_sales` VALUES ('5', '1', '支付宝', '2021-03-31 18:36:18', '超级管理员', '90', 'hn', '40.00', '1');

-- ----------------------------
-- Table structure for yw_salesback
-- ----------------------------
DROP TABLE IF EXISTS `yw_salesback`;
CREATE TABLE `yw_salesback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) DEFAULT NULL,
  `salesbacktime` datetime DEFAULT NULL,
  `salebackprice` double(10,2) DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of yw_salesback
-- ----------------------------
INSERT INTO `yw_salesback` VALUES ('1', '1', '支付宝', '2021-03-31 18:36:42', '40.00', '超级管理员', '10', 'h', '1');
