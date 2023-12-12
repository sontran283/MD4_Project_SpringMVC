package com.ra.model.service.Image;

import com.ra.model.entity.Image;
import com.ra.model.service.IGenericService;

import java.util.List;

public interface ImageService extends IGenericService<Image,Integer> {
    List<Image> findByProductId(Integer id);

    boolean addImage(Image image ,Integer productId);
}
