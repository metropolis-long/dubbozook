package org.bamboo.mapper;

import org.bamboo.apo.MasterDataSource;
import org.bamboo.pojo.Image;
import org.bamboo.result.Search;

import java.util.List;

public interface ImageMapper {
    List<Image> findImages(Search<Image> search);
    @MasterDataSource
    Image getStudentById(String param);

    boolean add(Image image);
}
