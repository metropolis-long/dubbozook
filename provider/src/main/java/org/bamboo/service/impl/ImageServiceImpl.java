package org.bamboo.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.bamboo.dto.ImageDTO;
import org.bamboo.mapper.ImageMapper;
import org.bamboo.pojo.Image;
import org.bamboo.result.Search;
import org.bamboo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version ="1.0")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;
    public boolean addImage(String imageTitle,String fileName, String tag, String url,String path,String sourcePath, String smallPath){
        System.out.println("fileName = " + fileName + ", sourcePath = " + sourcePath + ", smallPath = " + smallPath);
        Image image = new Image();
        image.setFileName(fileName);
        image.setImageTitle(imageTitle);
        image.setTag(tag);
        image.setUrlPrefix(url);
        image.setPath(path);
        image.setSourcePath(sourcePath);
        image.setSmallPath(smallPath);
        imageMapper.add(image);
        return  true;
    }

    @Override
    public List<ImageDTO> findImages(Search<Image> search) {
        List<ImageDTO> images = imageMapper.findImages(search);
        return images;
    }
}
