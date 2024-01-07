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
    private int pageNo=this.pageNumber*this.limit;
    private int pageNumber=0;
    private int totalNumber;
    private int totalPage = this.totalNumber==0?0:this.totalNumber / this.limit+1;
}
