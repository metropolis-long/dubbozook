package org.bamboo.mapper;

import org.bamboo.apo.MasterDataSource;
import org.bamboo.dto.ImageDTO;
import org.bamboo.pojo.Image;
import org.bamboo.result.Search;

import java.util.List;

public interface ImageMapper {
    List<ImageDTO> findImages(Search<Image> search);
    @MasterDataSource
    Image getStudentById(String param);

    boolean add(Image image);
}
