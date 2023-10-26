package org.bamboo.vo.query;


import lombok.Data;
import org.bamboo.pojo.Role;

@Data
public class RoleQueryVo extends Role {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示数量
    private Long userId;//当前登录用户ID
}
