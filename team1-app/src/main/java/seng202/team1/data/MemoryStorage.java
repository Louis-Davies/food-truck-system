package seng202.team1.data;

import seng202.team1.model.FoodItem;
import seng202.team1.util.InvalidDataCodeException;

import java.util.*;

/**
 * 
 */
public class MemoryStorage implements FoodItemDAO {

    private static MemoryStorage instance;

    private Set<FoodItem> suppliedFoodItems;
    private Map<String, Integer> suppliedFoodItemCounts; // maps code to a count

    /**
     * private constructor to enforce singleton pattern
     */
    private MemoryStorage() {
        suppliedFoodItems = new HashSet<FoodItem>();
        suppliedFoodItemCounts = new HashMap<String, Integer>();
    }

    /**
     * function used to get an instance of a InMemoryDAO object.
     *Implements the singleton pattern
     * @return an instance of InMemoryDAO
     */
    public static MemoryStorage getInstance() {
        if (instance == null) {
            instance = new MemoryStorage();
        }
        return instance;
        // TODO move this into a parent class??
    }

    /**
     * I don't think we should ever ues this outside of testing? not sure?
     */
    public void resetInstance() {
        // TODO get rid of this? we need it for testing but it seems bad?
        suppliedFoodItems = new HashSet<FoodItem>();
        suppliedFoodItemCounts = new HashMap<String, Integer>();
    }

    @Override
    public Set<FoodItem> getAllFoodItems() {
        return suppliedFoodItems;
    }

    @Override
    public FoodItem getFoodItemByCode(String code) {
        for (FoodItem item : suppliedFoodItems) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void addFoodItem(FoodItem item, int count) {
        // code is assumed to be valid, is that good practice? seems fine imo but should ask
        suppliedFoodItems.add(item);
        suppliedFoodItemCounts.put(item.getCode(), count);
    }

    @Override
    public void editFoodItem(String code, FoodItem alteredItem) {

    }

    @Override
    public void removeFoodItem(String code) {
        boolean itemRemoved = suppliedFoodItems.removeIf(item -> item.getCode().equals(code));
        if (!itemRemoved) {
            throw new InvalidDataCodeException("no FoodItem found with corresponding code");
        }
        suppliedFoodItemCounts.remove(code);
    }

    @Override
    public void setFoodItemStock(String code, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count must be non-negative.");
        }
        if (getFoodItemByCode(code) == null) {
            throw new InvalidDataCodeException("no FoodItem found with corresponding code");
        }
        suppliedFoodItemCounts.put(code, count);
    }

    @Override
    public int getFoodItemStock(String code) {
        return suppliedFoodItemCounts.get(code);
    }
}