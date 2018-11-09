package application.taufiqrahman.com.ovto.api;

import java.util.List;

import application.taufiqrahman.com.ovto.DataResponse;
import application.taufiqrahman.com.ovto.models.CartModel;
import application.taufiqrahman.com.ovto.models.LoginModel;
import application.taufiqrahman.com.ovto.models.MessageModel;
import application.taufiqrahman.com.ovto.models.PromotionalOffersModel;
import application.taufiqrahman.com.ovto.models.RegisterModel;
import application.taufiqrahman.com.ovto.models.RestaurantModel;
import application.taufiqrahman.com.ovto.models.RestaurantsModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Taufiq on 5/8/2018.
 * Interface for API calls
 **/

public interface ApiInterface {

    @GET("users")
    Call<DataResponse> getUsers(@Query("page") int page, @Query("per_page") int per_page);

    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> login(@Field("email") String email,
                           @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterModel> register(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("password_confirmation") String confirm_password);

    @GET("restaurants")
    Call<RestaurantsModel> getRestaurants(@Header("Authorization") String authHeader);

    @GET("restaurants/{id}")
    Call<RestaurantModel> getRestaurant(@Header("Authorization") String authHeader,
                                        @Path(value = "id", encoded = true) String id);

    @FormUrlEncoded
    @POST("restaurants/{id}/cart/checkout")
    Call<MessageModel> checkout(@Header("Authorization") String authHeader,
                                @Path(value = "id", encoded = true) String id,
                                @Field("user_id") String user_id,
                                @Field("table_number") String table_number,
                                @Field("carts") List<CartModel.Cart> carts);

    @FormUrlEncoded
    @POST("restaurants/{id}/review")
    Call<MessageModel> review(@Header("Authorization") String authHeader,
                              @Path(value = "id", encoded = true) String id,
                              @Field("review") String review,
                              @Field("rating") int rating);

    @GET("promotionaloffers")
    Call<PromotionalOffersModel> getPromoOffers(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("{slug}/promotionaloffers/buyrequest")
    Call<MessageModel> buy(@Header("Authorization") String authHeader,
                           @Path(value = "slug", encoded = true) String slug,
                           @Field("promoid") int promoCode);

    @Multipart
    @POST("profiles/{slug}")
    Call<LoginModel> postImage(@Header("Authorization") String authHeader,
                               @Path(value = "slug", encoded = true) String slug,
                               @Part MultipartBody.Part avatar);

    @FormUrlEncoded
    @POST("profiles/{slug}")
    Call<LoginModel> editProfile(@Header("Authorization") String authHeader,
                            @Path(value = "slug", encoded = true) String slug,
                            @Field("phonenumber") String phoneNumber,
                            @Field("address") String address);
}
