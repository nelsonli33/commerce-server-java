package com.shopflix.core.service.media.impl;

import com.shopflix.core.data.MediaData;
import com.shopflix.core.data.MediaImageData;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.media.MediaImageModel;
import com.shopflix.core.model.media.MediaModel;
import com.shopflix.core.repository.MediaImageRepository;
import com.shopflix.core.repository.MediaRepository;
import com.shopflix.core.service.media.MerchantMediaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Optional;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;


@Service(value = "merchantMediaService")
public class DefaultMerchantMediaService implements MerchantMediaService {

    private MediaRepository mediaRepository;
    private MediaImageRepository mediaImageRepository;

    @Override
    public MediaModel createMedia(MediaData mediaData) {
        validateParameterNotNull(mediaData, "Media cannot be null");
        MediaModel mediaModel = new MediaModel();
        mediaModel.setFilename(mediaData.getFilename());
        mediaModel.setOriginfilename(mediaData.getOriginFilename());
        mediaModel.setMime(mediaData.getMime());
        return mediaRepository.save(mediaModel);
    }

    public MediaImageModel createMediaImage(MediaImageData mediaImageData) {
        validateParameterNotNull(mediaImageData, "Media Image cannot be null");
        MediaImageModel mediaImageModel = new MediaImageModel();
        mediaImageModel.setFilename(mediaImageData.getFilename());
        mediaImageModel.setOriginfilename(mediaImageData.getOriginFilename());
        mediaImageModel.setMime(mediaImageData.getMime());
        mediaImageModel.setAlt(mediaImageData.getAlt());
        mediaImageModel.setWidth(mediaImageData.getWidth());
        mediaImageModel.setHeight(mediaImageData.getHeight());
        return mediaImageRepository.save(mediaImageModel);
    }

    @Override
    public MediaImageModel getMediaImageForId(Long id) {
        validateParameterNotNull(id, "Media image id cannot be null");
        Optional<MediaImageModel> mediaImageModel = mediaImageRepository.findById(id);
        if (mediaImageModel.isPresent()) {
            return mediaImageModel.get();
        } else {
            throw new ModelNotFoundException("Media image with id "+id+" not found.");
        }
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
