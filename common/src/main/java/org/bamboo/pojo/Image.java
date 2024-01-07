package org.bamboo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {
    private static final long serialVersionUID  = 1L;
    private Integer id;
    private String fileName;
    private String imageTitle;
    private String tag;
    private String urlPrefix;
    private String path;
    private String sourcePath;
    private String smallPath;
    private Date created;
    private Date updated;
}
