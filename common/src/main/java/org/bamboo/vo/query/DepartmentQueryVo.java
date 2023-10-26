package org.bamboo.vo.query;

import lombok.Data;
import org.bamboo.pojo.Department;

@Data
public class DepartmentQueryVo extends Department {
    public DepartmentQueryVo(Long id){
        super.setId(id);
    }
}
