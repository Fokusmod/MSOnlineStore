package com.geekbrains.apiservice;


public class CategoryDto {

    private Long id;

    private String title;

    public CategoryDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public CategoryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
