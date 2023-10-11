package org.bamboo.mapper;

import org.bamboo.apo.MasterDataSource;
import org.bamboo.pojo.Student;

import java.util.List;

public interface StudentMapper  {
    List<Student> queryAll();
    @MasterDataSource
    Student getStudentById(String param);
}
