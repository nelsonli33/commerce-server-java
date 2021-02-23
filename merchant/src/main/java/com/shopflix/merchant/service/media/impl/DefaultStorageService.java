package com.shopflix.merchant.service.media.impl;

import com.google.cloud.storage.*;
import com.shopflix.merchant.service.media.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class DefaultStorageService implements StorageService {

    private String BucketName;
    private Storage storage;

    public Blob sendFileToStorage(MultipartFile uploadFile) throws IOException {
        String filename = String.join("", UUID.randomUUID().toString().split("-"));
        return sendFileToStorage(uploadFile, filename);
    }

    public Blob sendFileToStorage(MultipartFile uploadFile, String filename) throws IOException {
        String fileMimeType = uploadFile.getContentType();

        BlobId blobId = BlobId.of(BucketName, filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(fileMimeType)
                .build();


        byte[] bytes = uploadFile.getBytes();
        Blob blob = storage.create(blobInfo, bytes);
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        return blob;
    }

    public Blob sendFileToStorage(byte[] bytes, String filename, String mimeType) throws IOException {
        BlobId blobId = BlobId.of(BucketName, filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(mimeType)
                .build();

        Blob blob = storage.create(blobInfo, bytes);
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        return blob;
    }

    public String getBucketName()
    {
        return BucketName;
    }

    public void setBucketName(String bucketName)
    {
        BucketName = bucketName;
    }

    public Storage getStorage()
    {
        return storage;
    }

    public void setStorage(Storage storage)
    {
        this.storage = storage;
    }
}
