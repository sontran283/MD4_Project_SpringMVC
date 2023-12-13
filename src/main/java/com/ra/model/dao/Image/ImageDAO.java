package com.ra.model.dao.Image;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Image;

import java.util.List;

public interface ImageDAO extends IGenericDAO<Image, Integer> {
  List<Image> findByProductId(Integer id);
  List<Image> paginater(Integer noPage);
  Integer getTotalPage();
  boolean addImage(Image image ,Integer productId);

}
