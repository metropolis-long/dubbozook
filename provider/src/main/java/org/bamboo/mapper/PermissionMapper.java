package org.bamboo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.bamboo.pojo.Permission;


import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户ID查询权限菜单列表
     * @param userId
     * @return
     */
    List<Permission> findPermissionListByUserId(Long userId);

    /**
     * 根据角色ID查询权限列表
     * @param roleId
     * @return
     */
    List<Permission> findPermissionListByRoleId(Long roleId);
}
