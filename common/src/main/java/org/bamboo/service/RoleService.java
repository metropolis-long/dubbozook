package org.bamboo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.bamboo.pojo.Role;
import org.bamboo.vo.query.RoleQueryVo;


import java.util.List;

public interface RoleService extends IService<Role> {

    /**
     *  查询角色列表
     * @param page
     * @param roleQueryVo
     * @return
     */
    IPage<Role> findRoleListByUserId(IPage<Role> page, RoleQueryVo roleQueryVo);

    /**
     * 检查角色是否被使用
     * @param id
     * @return
     */
    boolean hashRoleCount(Long id);

    /**
     * 删除角色
     * @param id
     * @return
     */
    boolean deleteRoleById(Long id);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean saveRolePermission(Long roleId, List<Long> permissionIds);

    /**
     * 根据登录用户ID查询该用户拥有的角色列表
     * @param userId
     * @return
     */
    List<Long> findRoleIdByUserId(Long userId);
}
