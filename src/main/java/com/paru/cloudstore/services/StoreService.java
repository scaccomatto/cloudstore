package com.paru.cloudstore.services;

import com.paru.cloudstore.model.Food;

import java.io.InputStream;

public interface StoreService {

    boolean savePicture(InputStream pic, Food foodData);

}
