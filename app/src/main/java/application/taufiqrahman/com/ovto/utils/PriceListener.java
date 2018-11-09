package application.taufiqrahman.com.ovto.utils;

import application.taufiqrahman.com.ovto.models.RestaurantModel;

/**
 *  Created by Taufiq on 8/9/2018.
 */

public interface PriceListener {
    void onButtonClick(int id, RestaurantModel.Menu menu);
}
