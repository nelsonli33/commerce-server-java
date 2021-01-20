package com.shopflix.core.service.media.impl;

import com.shopflix.core.data.MediaData;
import com.shopflix.core.data.MediaImageData;
import com.shopflix.core.model.MediaImageModel;
import com.shopflix.core.model.MediaModel;
import com.shopflix.core.repository.MediaImageRepository;
import com.shopflix.core.repository.MediaRepository;
import com.shopflix.core.service.media.MerchantMediaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;


@Service(value = "merchantMediaService")
public class DefaultMerchantMediaService implements MerchantMediaService {

    private MediaRepository mediaRepository;
    private MediaImageRepository mediaImageRepository;

    @Override
    public MediaModel createMedia(MediaData mediaData) {
        validateParameterNotNull(mediaData, "Media cannot be null");
        MediaModel mediaModel = new MediaModel();
        mediaModel.setCode(mediaData.getCode());
        mediaModel.setFilename(mediaData.getFilename());
        mediaModel.setMime(mediaData.getMime());
        return mediaRepository.save(mediaModel);
    }

    public MediaImageModel createMediaImage(MediaImageData mediaImageData) {
        validateParameterNotNull(mediaImageData, "Media Image cannot be null");
        MediaImageModel mediaImageModel = new MediaImageModel();
        mediaImageModel.setCode(mediaImageData.getCode());
        mediaImageModel.setFilename(mediaImageData.getFilename());
        mediaImageModel.setMime(mediaImageData.getMime());
        mediaImageModel.setAlt(mediaImageData.getAlt());
        mediaImageModel.setWidth(mediaImageData.getWidth());
        mediaImageModel.setHeight(mediaImageData.getHeight());
        return mediaImageRepository.save(mediaImageModel);
    }

    public MediaRepository getMediaRepository() {
        return mediaRepository;
    }

    @Resource(name = "mediaRepository")
    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public MediaImageRepository getMediaImageRepository() {
        return mediaImageRepository;
    }

    @Resource(name = "mediaImageRepository")
    public void setMediaImageRepository(MediaImageRepository mediaImageRepository) {
        this.mediaImageRepository = mediaImageRepository;
    }

}
