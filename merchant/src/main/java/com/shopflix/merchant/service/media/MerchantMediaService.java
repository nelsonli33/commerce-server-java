package com.shopflix.merchant.service.media;

import com.shopflix.core.data.MediaData;
import com.shopflix.core.data.MediaImageData;
import com.shopflix.core.model.media.MediaImageModel;
import com.shopflix.core.model.media.MediaModel;

public interface MerchantMediaService {

    MediaModel createMedia(MediaData mediaData);

    MediaImageModel createMediaImage(MediaImageData mediaImageData);

    MediaImageModel getMediaImageForId(Long id);
}
