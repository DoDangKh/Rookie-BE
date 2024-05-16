package com.rookie.rookiee.mapper;

import com.rookie.rookiee.dto.ImagesDto;
import com.rookie.rookiee.entity.Images;

public class ImagesMapper {

    public static ImagesDto toImagesDto(Images images) {
        return new ImagesDto(images.getId(), images.getUrl());
    }

    public static Images imagesDtoToImages(ImagesDto imagesDto) {
        Images images = new Images();
        images.setId(imagesDto.getId());
        images.setUrl(images.getUrl());
        return images;
    }

}
