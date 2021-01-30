package com.shopflix.merchant.service.media;

import com.google.cloud.storage.Blob;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    Blob sendFileToStorage(MultipartFile uploadFile) throws IOException;

    Blob sendFileToStorage(MultipartFile uploadFile, String filename) throws IOException;

    Blob sendFileToStorage(byte[] bytes, String filename, String mimeType) throws IOException;
}
