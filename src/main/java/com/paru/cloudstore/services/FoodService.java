package com.paru.cloudstore.services;

import com.paru.cloudstore.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FoodService extends MongoRepository<Food, String> {

    Food findById(String id);
    List<Food> findByRestaurant(String restaurant);
    List<Food> findByName(String foodName);
}
