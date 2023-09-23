package com.pratheek.foodcatalogue.service;

import com.pratheek.foodcatalogue.dto.FoodCataloguePage;
import com.pratheek.foodcatalogue.dto.FoodItemDto;
import com.pratheek.foodcatalogue.dto.Restaurant;
import com.pratheek.foodcatalogue.entity.FoodItem;
import com.pratheek.foodcatalogue.mapper.FoodItemMapper;
import com.pratheek.foodcatalogue.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    private FoodItemRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public FoodItemDto addFoodItem(FoodItemDto foodItemDto) {
        FoodItem savedFoodItem = repository.save(FoodItemMapper.INSTANCE.mapFoodItemDtoToFoodItem(foodItemDto));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(savedFoodItem);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurentId) {
        List<FoodItem> foodItemsList = fetchFoodItemList(restaurentId);
        Restaurant restaurent = fetchRestaurentDetailsFromRestaurentMS(restaurentId);
        return createFoodCataloguePage(foodItemsList, restaurent);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemsList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemsList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurentDetailsFromRestaurentMS(Integer restaurentId) {
       return restTemplate.getForObject("http://RESTAURENT-SERVICE/restaurant/fetchById/" + restaurentId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer retsaurantId) {
        return repository.findByRestaurantId(retsaurantId);
    }
}
