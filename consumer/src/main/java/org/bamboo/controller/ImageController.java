package org.bamboo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/img")
public class ImageController {

    @PostMapping("/upload")
    public Object uploadImage(MultipartFile img, HttpServletRequest request) throws IOException {

        System.out.println("文件名"+img.getOriginalFilename());
        System.out.println("文件类型"+img.getContentType());
        System.out.println("文件大小"+img.getSize());

        //根据相对获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/files");

        //创建时间文件夹
        String format = new SimpleDateFormat("yyyy-MM--dd").format((new Date()));
        File file=new File(realPath,format);
        if(!file.exists())  file.mkdirs();

        //获取文件后缀
        String extension = FilenameUtils.getExtension(img.getOriginalFilename());
        String newFileNamePrefix = UUID.randomUUID().toString().replace("-", "")+
                new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String newFileName =newFileNamePrefix+"."+extension;
        //处理上传操作
        img.transferTo(new File(file,newFileName));
        return "ok";
    }

}
