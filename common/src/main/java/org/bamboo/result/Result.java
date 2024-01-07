package org.bamboo.result;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bamboo.pojo.Image;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result <T> implements Serializable {
    private static final long serialVersionUID  = 1L;
    @JsonManagedReference
    private T data;
    private int code;
    private String msg;
    private Map map=new HashMap();

    public Result(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
