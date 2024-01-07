package org.bamboo.tools;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ImageTools {
    /**
     * 压缩本地图片保存到本地
     *
     * @param path          保存文件的路径，精确到最后一个文件夹
     * @param localPhotoUrl 本地图片路径，精确到文件
     * @param fileName      新的文件名
     * @return
     */
    public static String localImageCompress(String path, String localPhotoUrl, String fileName) {
        try {
            File f = new File(localPhotoUrl);
            String fileExt = f.getName().split("\\.")[1];
            long sourceImgValue = f.length() / 1024;
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(f));
            int width = sourceImg.getWidth();//原图宽度
            int height = sourceImg.getHeight();//原图高度
            int hightValue = width;//宽度和高度的最大值
            if (height > width) hightValue = height;

            double scale111 = 0.5;
            if (1000 <= hightValue && hightValue < 2000) scale111 = 0.3;
            if (2000 <= hightValue && hightValue < 3000) scale111 = 0.2;
            if (3000 <= hightValue && hightValue < 4000) scale111 = 0.1;
            if (4000 <= hightValue) {
                scale111 = BigDecimal.valueOf(500L)
                        .divide(BigDecimal.valueOf(hightValue), 2, BigDecimal.ROUND_UP)
                        .doubleValue();
            }
            System.out.println("最终scale为：" + scale111);

            /**
             * 图片小于5M，压缩图片最大尺寸为512K
             * 大于5M，小于10M，压缩图片最大尺寸为1M
             * 大于10M，压缩图片最大尺寸为2M
             */
            int maxSize = 512;//压缩图片的最大尺寸
            if (sourceImgValue < 5 * 1024) maxSize = 512;
            if (5 * 1024 <= sourceImgValue && sourceImgValue < 10 * 1024) maxSize = 1024;
            if (10 * 1024 <= sourceImgValue) maxSize = 2 * 1024;

            String thumbnailPath = path + fileName+"."+fileExt;
            Thumbnails.of(localPhotoUrl)
                    .scale(scale111)
                    .outputQuality(0.9)
                    .toFile(thumbnailPath);
            File f1 = new File(thumbnailPath);
            long returnValue = f1.length() / 1024;
            System.out.println("第一次生成图片缩略图，大小为：" + returnValue + "KB");
            // 如果图片大小超过1M，则继续压缩，直到图片大小小于或等于149KB
            if (returnValue > maxSize) {
                for (int i = 0; i < 4; i++) {
                    double scale = (i + 1) * 0.1;
                    double scaleQuality = 0.9 - scale * 2;
                    if (scale111 > scale) scale111 = scale111 - scale;
                    System.out.println("最终scale为：" + scale111);
                    System.out.println("最终scaleQuality为：" + scaleQuality);
                    Thumbnails.of(localPhotoUrl)
                            .scale(scale111)
                            .outputQuality(scaleQuality)
                            .toFile(thumbnailPath);
                    File f2 = new File(thumbnailPath);
                    returnValue = f2.length() / 1024;
                    System.out.println("继续压缩图片大小：" + returnValue + "KB");
                    if (returnValue > maxSize) {
                        continue;
                    } else {
                        break;
                    }
                }
            }
            return thumbnailPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量压缩
     * @param path
     * @param localPhotoUrls
     * @return
     */
    public static List<String> localImageCompressBatch(String path, List<String> localPhotoUrls){
        List<String> urls =  new ArrayList<>();
        localPhotoUrls.forEach(url->{
            String fileName = UUID.randomUUID().toString();
            urls.add(localImageCompress(path,url,fileName));
        });
        return urls;
    }
    public static void main(String[] args) {
        String p="D:\\temp\\";
        String l="C:\\Users\\Administrator\\Pictures\\Screenshots\\j.png";
        String n="11";
        ImageTools.localImageCompress(p,l,n);
    }
}
