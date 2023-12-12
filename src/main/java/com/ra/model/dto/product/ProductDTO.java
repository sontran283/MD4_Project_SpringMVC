package com.ra.model.dto.product;

import com.ra.model.entity.Category;
import com.ra.model.validate.FileNotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ProductDTO {
    private int productId;
    private Category category;
    @FileNotNull(message = "NotEmpty")
    private MultipartFile file;
    @NotEmpty(message = "NotEmpty")
    private String productName;
    @NotEmpty(message = "NotEmpty")
    private String productDescription;
    private double productPrice;
    private int quantity;

    private boolean productStatus;

    public ProductDTO() {
    }

    public ProductDTO(int productId, Category category, MultipartFile file, String productName, String productDescription, double productPrice, int quantity, boolean productStatus) {
        this.productId = productId;
        this.category = category;
        this.file = file;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.productStatus = productStatus;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }
}
