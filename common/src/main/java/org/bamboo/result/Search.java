package org.bamboo.result;

import lombok.Data;

import java.io.Serializable;

public class Search<T> implements Serializable {
    private static final long serialVersionUID  = 1L;
    private T data;
    private Page page= new Page();
}
