
package org.bamboo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bamboo.mapper.UserMapper;
import org.bamboo.pojo.User;
import org.bamboo.utils.SystemConstants;
import org.bamboo.service.FileService;
import org.bamboo.service.UserService;
import org.bamboo.vo.query.UserQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //    @Resource
    private FileService fileService;


    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public User findUserByUserName(String username) {
        //创建条件构造器对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username",username);
        //执行查询
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 分页查询用户列表
     *
     * @param page
     * @param userQueryVo
     * @return
     */
    @Override
    public IPage<User> findUserListByPage(IPage<User> page, UserQueryVo userQueryVo) {
        //创建条件构造器对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //部门编号
        queryWrapper.eq(!ObjectUtils.isEmpty(userQueryVo.getDepartmentId()),"department_id",userQueryVo.getDepartmentId());
        //用户名
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getUsername()),"username",userQueryVo.getUsername());
        //真实姓名
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getRealName()),"real_name",userQueryVo.getRealName());
        //电话
        queryWrapper.like(!ObjectUtils.isEmpty(userQueryVo.getPhone()),"phone",userQueryVo.getPhone());
        //执行查询
        return baseMapper.selectPage(page,queryWrapper);
    }

    /**
     * 根据Id删除用户
     *
     * @param userId
     * @return
     */
    @Override
    public boolean deleteById(Long userId) {
        //根据用户ID查询用户信息
        User user = baseMapper.selectById(userId);
        //删除用户角色关系
        baseMapper.deleteUserRole(userId);
        //删除用户信息
        if(baseMapper.deleteById(userId)>0){
            //判断用户对象是的为空
            if(user!=null && !ObjectUtils.isEmpty(user.getAvatar()) && !user.getAvatar().equals(SystemConstants.DEFAULT_AVATAR)){
                //删除阿里云OSS中的文件
                fileService.deleteFile(user.getAvatar());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean saveUserRole(Long userId, List<Long> roleIds) {
        //删除用户角色关系
        baseMapper.deleteUserRole(userId);
        //保存用户角色关系
        return baseMapper.saveUserRole(userId,roleIds)>0;
    }
}
