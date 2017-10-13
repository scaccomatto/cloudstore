package com.paru.cloudstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paru.cloudstore.model.Food;
import com.paru.cloudstore.services.FoodService;
import com.paru.cloudstore.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


@Controller
public class FoodieController {

    private final StoreService ss;
    private final FoodService fs;

    @Autowired
    public FoodieController(StoreService ss, FoodService fsIn) {
        this.ss = ss;
        this.fs = fsIn;
    }

    @GetMapping("/food")
    public ResponseEntity testFoodItem(){

        System.out.println("colpito!!!!");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/food")
    public ResponseEntity getFood(@RequestParam("file") MultipartFile pic, @RequestParam("food") String foodObj,
                                  HttpServletRequest request){
    //public ResponseEntity getFood(@RequestParam("file") MultipartFile pic, Food food){

        ObjectMapper mapper = new ObjectMapper();
        Food food = null;
        try {
            food = mapper.readValue(foodObj, Food.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //for(Map.Entry k:  request.getParameterMap())
        System.out.println(pic.getOriginalFilename()+" ");
        fs.save(food);
        try {
            if(ss.savePicture(pic.getInputStream(), food)){
                return ResponseEntity.ok(food);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


}
