package com.ra.model.dto.product;

import com.ra.model.entity.Category;
import com.ra.model.validate.FileNotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class ProductDTO {
    private int productId;
    @NotNull(message = "Cannot be left blank")
    private Category category;
    @FileNotNull(message = "Cannot be left blank")
    private MultipartFile file;
    @NotEmpty(message = "Cannot be left blank")
    private String productName;
    @NotEmpty(message = "Cannot be left blank")
    private String productDescription;
    @Min(value = 1, message = "Price should not be less than 1")
    private double productPrice;
    @Min(value = 1, message = "Quantity should not be less than 1")
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
