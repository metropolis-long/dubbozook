package org.bamboo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bamboo.pojo.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色权限菜单数据回显Vo类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionVo{
    /**
     * 菜单数据
     */
    private List<Permission> permissionList = new ArrayList<Permission>();

    /**
     * 该角色拥有的权限菜单数据
     */
    private Object[] checkList;
}
