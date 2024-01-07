package org.bamboo.dto;

import org.bamboo.pojo.Image;

public class ImageDTO extends Image {
    private String smallUrl;
    private String sourceUrl;
    public String getSmallUrl(){
        return  super.getUrlPrefix()+super.getSmallPath();
    }
    public String getSourceUrl(){
        return  super.getUrlPrefix()+super.getSourcePath();
    }
}
