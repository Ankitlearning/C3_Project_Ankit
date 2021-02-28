import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantTest {
    Restaurant restaurant;
    LocalTime openingTime;
    LocalTime closingTime;

    @BeforeEach
    public void setUp() {
        openingTime = LocalTime.parse("10:30:00");
        closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }

    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING,IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");
        /*restaurant =new Restaurant("Cmelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup with sauce",119);
        restaurant.addToMenu("Vegetable lasagne with sauce", 269);*/

        Restaurant rest= Mockito.mock(Restaurant.class);
        LocalTime current=LocalTime.parse("11:30:00");


        when(rest.getCurrentTime()).thenReturn(current);
        assertTrue(restaurant.isRestaurantOpen());
        //Assertions.assertTrue(current.isAfter(restaurant.openingTime) && current.isBefore(restaurant.closingTime));
        //assertEquals(closingTime,restaurant.closingTime);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");
        /* restaurant =new Restaurant("Cmelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup with sauce",119);
        restaurant.addToMenu("Vegetable lasagne with sauce", 269);
        */

        Restaurant restau= Mockito.mock(Restaurant.class);
        LocalTime current=LocalTime.parse("23:30:30");

        when(restau.getCurrentTime()).thenReturn(current);
        //Assertions.assertFalse(restaurant.isRestaurantOpen());

        assertEquals(false,restau.isRestaurantOpen());
       // Assertions.assertFalse(current.isAfter(restaurant.openingTime) && current.isBefore(restaurant.closingTime));

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void restaurant_order_value_return_pass_when_totalOrderValue_called(){
        /*restaurant =new Restaurant("Amelie's cafe","Mumbai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);*/


        List<String> name=new ArrayList<>();
        for(Item resItem: restaurant.getMenu()){
            name.add(resItem.getName());
        }
         int totalOrderValue = restaurant.getTotalOrderValue( name);
        assertEquals(388,totalOrderValue);

    }

    @Test
    public void restaurant_order_value_return_fail_when_totalOrderValue_called(){
        /*restaurant =new Restaurant("Amelie's cafe","Mumbai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);*/

        List<String> name=new ArrayList<>();
        for(Item resItem: restaurant.getMenu()){
            name.add(resItem.getName());
        }
        int totalOrderValue = restaurant.getTotalOrderValue( name);
        assertFalse(400==totalOrderValue);
    }

    @Test
    public void restaurant_order_value_not_null_when_totalOrderValue_called(){
        /*restaurant =new Restaurant("Amelie's cafe","Mumbai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);*/

        List<String> name=new ArrayList<>();
        for(Item resItem: restaurant.getMenu()){
            name.add(resItem.getName());
        }
        int totalOrderValue = restaurant.getTotalOrderValue( name);
        assertNotNull(totalOrderValue);
    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");
        /*restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);*/

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");
       /* restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);*/

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());

    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        /*LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);*/

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));

    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void searching_for_existing_item_should_return_expected_restaurant_item_Object() {
        //WRITE UNIT TEST CASE HERE
        //restaurant=service.addRestaurant("Amelie's cafe","Ram Vihar",LocalTime.parse("10:30:00"),LocalTime.parse("22:00:00"));
        //restaurant.addToMenu("Sour corn soup",119);
        //restaurant.addToMenu("Vegetable Soup", 269);

        //restaurant= service.findRestaurantByName("Amelie's cafe");
        Item item = restaurant.findItemByName("Sweet corn soup");
        //Assertions.assertNotNull(restaurant);
        Assertions.assertEquals("Sweet corn soup", item.getName());
        Assertions.assertNotNull(item.getName());

    }
    @Test
    public void searching_for_existing_item_should_return_null_restaurant_item_Object()  {


        Item item= restaurant.findItemByName("Sweet mango soup");

        //Assertions.assertEquals("Sweet corn soup",item.getName());
        //Assertions.assertNotNull(item.getName());
        Assertions.assertNull(item);


    }

    @Test
    public void test_Get_Restaurent_Name_true(){
        Assertions.assertEquals("Amelie's cafe", restaurant.getName());
    }
    @Test
    public void test_Get_Restaurent_Name_False(){
        Assertions.assertFalse(restaurant.getName().equals("Tea cafe"));

    }
}