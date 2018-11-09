package application.taufiqrahman.com.ovto.utils;

import application.taufiqrahman.com.ovto.models.RestaurantModel;

/**
 *   Created by Taufiq on 8/17/2018.
 */

public interface CartActionListener {
    void onAddButtonClick(int id, RestaurantModel.Menu menu);
    void onReduceButtonClick(int id, RestaurantModel.Menu menu);
}
