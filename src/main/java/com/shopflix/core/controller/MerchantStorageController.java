package com.shopflix.core.controller;

import com.google.cloud.storage.*;
import com.shopflix.core.data.MediaData;
import com.shopflix.core.data.MediaImageData;
import com.shopflix.core.model.MediaImageModel;
import com.shopflix.core.model.MediaModel;
import com.shopflix.core.service.media.MerchantMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/merchant/api/v1/file")
public class MerchantStorageController {

    protected final static String BUCKET_NAME = "shopflix-test";

    @Autowired
    private Storage storage;

    @Resource(name = "merchantMediaService")
    private MerchantMediaService merchantMediaService;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile uploadFile) {

        String filename = String.join("", UUID.randomUUID().toString().split("-"));
        String fileMimeType = uploadFile.getContentType();

        BlobId blobId = BlobId.of(BUCKET_NAME, filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(fileMimeType)
                .build();

        try {
            byte[] bytes = uploadFile.getBytes();
            Blob blob = storage.create(blobInfo, bytes);
            blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

            String type = fileMimeType.split("/")[0];
            if (type.equals("image")) {
                MediaImageData imageData = new MediaImageData();
                imageData.setCode(filename);
                imageData.setFilename(uploadFile.getOriginalFilename());
                imageData.setMime(fileMimeType);
                imageData.setAlt(uploadFile.getOriginalFilename());
                MediaImageModel mediaImageModel = merchantMediaService.createMediaImage(imageData);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(mediaImageModel);
            } else {
                MediaData mediaData = new MediaData();
                mediaData.setCode(filename);
                mediaData.setFilename(uploadFile.getOriginalFilename());
                mediaData.setMime(fileMimeType);
                MediaModel mediaModel = merchantMediaService.createMedia(mediaData);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(mediaModel);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

}
