# Spring Boot 整合 Spring Security

## 表描述
sys_role 角色表
sys_user_role 角色用户关联表
sys_menu 权限表
sys_role_menu 角色权限关联表
## sql查询语句
### 根据userid查询 perms 对应的 role 和 menu 都必须是正常状态（使用DISTINCT去重（权限重复））
SELECT
	DISTINCT m.`perms`
FROM
	sys_user_role ur
	LEFT JOIN `sys_role` r ON ur.`role_id` = r.`id`
	LEFT JOIN `sys_role_menu` rm ON ur.`role_id` = rm.`role_id`
	LEFT JOIN `sys_menu` m ON m.`id` = rm.`menu_id`
WHERE
	user_id = 2
	AND r.`status` = 0
	AND m.`status` = 0