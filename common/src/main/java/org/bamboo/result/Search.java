package org.bamboo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Search<T> implements Serializable {
    private static final long serialVersionUID  = 1L;
    private T data;
    private Page page= new Page();

}
