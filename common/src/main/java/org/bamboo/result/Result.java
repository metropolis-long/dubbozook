package org.bamboo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bamboo.pojo.Image;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result <T> implements Serializable {
    private static final long serialVersionUID  = 1L;
    private T data;
    private int code;
    private String msg;
    private Page page;

    public Result(List<Image> images, int i, String ok) {
    }
}
