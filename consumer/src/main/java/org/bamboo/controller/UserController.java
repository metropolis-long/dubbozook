package org.bamboo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.bamboo.dto.UserRoleDTO;
import org.bamboo.pojo.Role;
import org.bamboo.pojo.User;
import org.bamboo.service.RoleService;
import org.bamboo.service.UserService;
import org.bamboo.utils.Result;
import org.bamboo.vo.query.RoleQueryVo;
import org.bamboo.vo.query.UserQueryVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 查询所有用户信息（测试使用）
     *
     * @return
     */
    @GetMapping("/listAll")
    public Result listAll() {
        return Result.ok(userService.list());
    }

    /**
     * 查询用户列表
     *
     * @param userQueryVo
     * @return
     */
    @GetMapping("/list")
    public Result list(UserQueryVo userQueryVo) {
        //创建分页对象
        IPage<User> page = new Page<User>(userQueryVo.getPageNo(), userQueryVo.getPageSize());
        //调用分页查询方法
        userService.findUserListByPage(page,userQueryVo);
        //返回数据
        return Result.ok(page);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:add')")
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        //调用根据用户名查询用户信息的方法
        User item = userService.findUserByUserName(user.getUsername());
        //判断对象是否为空，不为空则表示用户存在
        if(item!=null){
            return Result.error().message("该用户名已被使用，请重新输入！");
        }
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //调用保存用户信息的方法
        if(userService.save(user)){
            return Result.ok().message("用户添加成功");
        }
        return Result.error().message("用户添加失败");
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        //调用根据用户名查询用户信息的方法
        User item = userService.findUserByUserName(user.getUsername());
        //判断对象是否为空，不为空则表示用户存在
        if(item!=null && item.getId() != user.getId()){
            return Result.error().message("该用户名已被使用，请重新输入！");
        }
        //调用修改用户信息的方法
        if(userService.updateById(user)){
            return Result.ok().message("用户修改成功");
        }
        return Result.error().message("用户修改失败");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        //调用删除用户信息的方法
        if(userService.deleteById(id)){
            return Result.ok().message("用户删除成功");
        }
        return Result.error().message("用户删除失败");
    }

    /**
     * 获取分配角色列表
     * @param roleQueryVo
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:assign')")
    @GetMapping("/getRoleListForAssign")
    public Result getRoleListForAssign(RoleQueryVo roleQueryVo){
        //创建分页对象
        IPage<Role> page = new Page<Role>(roleQueryVo.getPageNo(),roleQueryVo.getPageSize());
        //调用查询角色列表的方法
        roleService.findRoleListByUserId(page,roleQueryVo);
        //返回数据
        return Result.ok(page);
    }

    /**
     * 根据用户ID查询该用户拥有的角色列表
     * @param userId
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:assign')")
    @GetMapping("/getRoleByUserId/{userId}")
    public Result getRoleByUserId(@PathVariable Long userId){
        //调用根据用户ID查询该用户拥有的角色ID的方法
        List<Long> roleIds = roleService.findRoleIdByUserId(userId);
        //返回数据
        return Result.ok(roleIds);
    }

    /**
     * 分配角色
     * @param userRoleDTO
     * @return
     */
    @PreAuthorize("hasAuthority('sys:user:assign')")
    @PostMapping("/saveUserRole")
    public Result saveUserRole(@RequestBody UserRoleDTO userRoleDTO){
        //保存用户角色关系
        if(userService.saveUserRole(userRoleDTO.getUserId(),userRoleDTO.getRoleIds())){
            return Result.ok().message("角色分配成功");
        }
        return Result.error().message("角色分配失败");
    }
}

