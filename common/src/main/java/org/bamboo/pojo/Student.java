package org.bamboo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private static final long serialVersionUID  = 1L;
    private String id;
    private String name;
}
