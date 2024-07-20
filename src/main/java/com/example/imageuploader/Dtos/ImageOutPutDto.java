package com.example.imageuploader.Dtos;

public class ImageOutPutDto {
    private Long id;
    private String name;
    private String Url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public ImageOutPutDto(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        Url = url;
    }
}
