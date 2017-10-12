package com.paru.cloudstore.services;

import java.io.*;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import com.paru.cloudstore.model.Food;
import org.springframework.stereotype.Service;


@Service
public class GoogleCloudStoreService implements StoreService {

    Storage storage = null;
    Bucket bucket = null;

    public GoogleCloudStoreService() throws IOException {
        setupCredentials();
    }

    @Override
    public boolean savePicture(InputStream picStream, Food food) {

        Blob result = bucket.create(food.getName()+"_"+Math.random()+".jpg", picStream, "image/jpeg");
//        Page<Blob> blobs = bucket.list();
//        for (Blob blob : blobs.iterateAll()) {
//            System.out.println(blob.getName());
//        }
        if(result !=null ){
            return  true;
        } else {
            return false;
        }
    }

    private void setupCredentials() throws IOException {

        storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("/home/matteo/code/keys/my-kubernetes-codelab-2ef04f84f803.json")))
                .build()
                .getService();

        bucket = storage.get("testimg2");
    }
}
