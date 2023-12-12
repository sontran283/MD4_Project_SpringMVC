package com.ra.model.service.Image;

import com.ra.model.dao.Image.ImageDAO;
import com.ra.model.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageDAO imageDAO;
    @Override
    public List<Image> findAll() {
        return imageDAO.findAll();
    }

    @Override
    public List<Image> findByName(String name) {
        return imageDAO.findByName(name);
    }

    @Override
    public List<Image> sortByName() {
        return imageDAO.sortByName();
    }

    @Override
    public Image findById(Integer integer) {
        return imageDAO.findById(integer);
    }

    @Override
    public boolean saveOrUpDate(Image image) {
        return imageDAO.saveOrUpDate(image);
    }

    @Override
    public void delete(Integer integer) {
        imageDAO.delete(integer);
    }

    @Override
    public List<Image> findByProductId(Integer id) {
        return imageDAO.findByProductId(id);
    }

    @Override
    public boolean addImage(Image image, Integer productId) {
        return imageDAO.addImage(image, productId);
    }

}
