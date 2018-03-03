package com.paru.cloudstore.services;

import java.io.*;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import com.paru.cloudstore.model.Food;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class GoogleCloudStoreService implements StoreService {

    @Value("${bucket.name}")
    private String bucketName;
    @Value("${file.key}")
    private String fileKey;
    private Storage storage;
    private Bucket bucket;

    @Override
    public boolean savePicture(InputStream picStream, Food food) {

        Blob result = bucket.create(food.getName()+"_"+Math.random()+".jpg", picStream, "image/jpeg");

        return result != null;
    }

    @PostConstruct
    public void print() throws IOException{
        storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(fileKey)))
                .build()
                .getService();
        bucket = storage.get(bucketName);
    }
}
