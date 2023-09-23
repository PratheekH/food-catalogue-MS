package com.pratheek.foodcatalogue.controller;

import com.pratheek.foodcatalogue.dto.FoodCataloguePage;
import com.pratheek.foodcatalogue.dto.FoodItemDto;
import com.pratheek.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {

    @Autowired
    private FoodCatalogueService service;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDto> addFoodItem(@RequestBody FoodItemDto foodItemDto){
        FoodItemDto foodItemSaved = service.addFoodItem(foodItemDto);
        return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurentAndFoodItemsById/{restaurentId}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurentDetailsWithFoodMenu(@PathVariable Integer restaurentId){
        FoodCataloguePage foodCataloguePage = service.fetchFoodCataloguePageDetails(restaurentId);
        System.out.println(foodCataloguePage);
        return new ResponseEntity<>(foodCataloguePage,HttpStatus.OK);
    }

}
