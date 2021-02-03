package com.shopflix.merchant.facades.media.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.MediaImageData;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.media.MediaImageModel;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component(value = "mediaImagePopulator")
public class MediaImagePopulator implements Populator<MediaImageModel, MediaImageData>
{
    @Override
    public void populate(MediaImageModel source, MediaImageData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter mediaImageModel cannot be null.");
        Assert.notNull(target, "Parameter mediaImageData cannot be null.");

        target.setId(source.getId());
        target.setFilename(source.getFilename());
        target.setOriginFilename(source.getOriginfilename());
        target.setMime(source.getMime());
        target.setAlt(source.getAlt());
        target.setHeight(source.getHeight());
        target.setWidth(source.getWidth());
    }
}
