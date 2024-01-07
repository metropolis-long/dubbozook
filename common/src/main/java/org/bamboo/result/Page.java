package org.bamboo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page implements Serializable {
    private static final long serialVersionUID  = 1L;
    private int limit =10;
    private int pageNo=1;
    private int total;
    private int page = total==0?0:total/limit+1;
}
