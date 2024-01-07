package org.bamboo.result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result <T> implements Serializable {
    private static final long serialVersionUID  = 1L;
    private T data;
    private int code;
    private String msg;
    private Page page = new Page();
    private Map map=new HashMap();
    public Result(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
