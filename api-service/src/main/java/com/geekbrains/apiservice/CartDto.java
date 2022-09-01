package com.geekbrains.apiservice;

public class CartDto {

    private String username;
    private Long productId;


    public CartDto(String username, Long productId) {
        this.username = username;
        this.productId = productId;
    }

    public CartDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "username='" + username + '\'' +
                ", productId=" + productId +
                '}';
    }
}
