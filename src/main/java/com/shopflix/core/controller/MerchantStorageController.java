package com.shopflix.core.controller;

import com.google.cloud.storage.Blob;
import com.shopflix.core.data.MediaData;
import com.shopflix.core.data.MediaImageData;
import com.shopflix.core.model.MediaImageModel;
import com.shopflix.core.model.MediaModel;
import com.shopflix.core.response.ApiResult;
import com.shopflix.core.service.media.MerchantMediaService;
import com.shopflix.core.service.media.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/merchant/api/v1/file")
public class MerchantStorageController {

    @Resource(name = "storageService")
    private StorageService storageService;

    @Resource(name = "merchantMediaService")
    private MerchantMediaService merchantMediaService;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile uploadFile) throws IOException {


        Blob blob = storageService.sendFileToStorage(uploadFile);

        String fileMimeType = uploadFile.getContentType();
        String type = fileMimeType.split("/")[0];
        if (type.equals("image")) {
            System.out.println(blob.getName());
            MediaImageData imageData = new MediaImageData();
            imageData.setFilename(blob.getName());
            imageData.setOriginFilename(uploadFile.getOriginalFilename());
            imageData.setMime(fileMimeType);
            imageData.setAlt(uploadFile.getOriginalFilename());
            MediaImageModel mediaImageModel = merchantMediaService.createMediaImage(imageData);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResult.success(mediaImageModel));
        } else {
            MediaData mediaData = new MediaData();
            mediaData.setFilename(blob.getName());
            mediaData.setOriginFilename(uploadFile.getOriginalFilename());
            mediaData.setMime(fileMimeType);
            MediaModel mediaModel = merchantMediaService.createMedia(mediaData);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResult.success(mediaModel));
        }
    }

}
