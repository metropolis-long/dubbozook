package org.bamboo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.bamboo.dto.ImageDTO;
import org.bamboo.pojo.Image;
import org.bamboo.result.Result;
import org.bamboo.result.Search;
import org.bamboo.service.ImageService;
import org.bamboo.tools.ImageTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/img")
public class ImageController {

    @Value("${files.picture.path}")
    private String path;
    @Value("${files.picture.source-path}")
    private String sourcePath;
    @Value("${files.picture.small-path}")
    private String smallPath;
    @Value("${files.picture.url-prefix}")
    private String urlPrefix;

    @DubboReference(version = "1.0")
    private ImageService imageService;
    @PostMapping("/upload")
    public Object uploadImage(String imageTitle,String tag,MultipartFile img, HttpServletRequest request) throws IOException {

        System.out.println("文件名"+img.getOriginalFilename());
        System.out.println("文件类型"+img.getContentType());
        System.out.println("文件大小"+img.getSize());

        System.out.println(smallPath);

        File file=new File(path+sourcePath);
        if(!file.exists())  file.mkdirs();

        File file1=new File(path+sourcePath);
        if(!file1.exists())  file1.mkdirs();
        File file2=new File(path+smallPath);
        if(!file2.exists())  file2.mkdirs();

        //获取文件后缀
        String extension = FilenameUtils.getExtension(img.getOriginalFilename());
        String newFileNamePrefix = UUID.randomUUID().toString().replace("-", "")+
                new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String newFileName =newFileNamePrefix+"."+extension;
        //处理上传操作
        File fileContext = new File(file, newFileName);
        img.transferTo(fileContext);
        String filePath = fileContext.getAbsolutePath().toString();
        System.out.println(filePath);
        String smallFile = ImageTools.localImageCompress(path+smallPath,filePath,newFileName);
        return imageService.addImage(imageTitle,newFileName,tag,urlPrefix,filePath,
                sourcePath+"/"+newFileName,smallPath+"/"+newFileName);
    }
    @GetMapping("/images")
    public  Object findImages(){
        Search<Image> search =new Search();
        List<ImageDTO> images = imageService.findImages(search);
        return new Result(images,200,"ok");
    }

}
