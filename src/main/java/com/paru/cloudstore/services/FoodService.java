package com.paru.cloudstore.services;

import com.paru.cloudstore.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FoodService extends MongoRepository<Food, String> {

    public Food findById(String id);
    public List<Food> findByRestaurant(String restaurant);
    public List<Food> findByName(String foodName);
}
