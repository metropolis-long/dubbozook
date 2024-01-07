package org.bamboo.service;

import org.bamboo.pojo.Image;
import org.bamboo.result.Search;

import java.util.List;

public interface ImageService {

    boolean addImage(String imageTitle,String fileName, String tag, String url,String path,String sourcePath, String smallPath);
    List<Image> findImages(Search<Image> search);
}
