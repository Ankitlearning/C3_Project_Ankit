import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {

        Boolean flag=false;

        for(Restaurant res : restaurants){
            if(res.getName().equals(restaurantName)){
                flag=true;
                //return res;
            }
            //flag==false ==!false

                if(!flag)
                  throw new restaurantNotFoundException("Not Found");
                else
            return res;
        }
        return null;

        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if(restaurantToBeRemoved==null){
            throw new restaurantNotFoundException("Restaurant not found");
        }
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
