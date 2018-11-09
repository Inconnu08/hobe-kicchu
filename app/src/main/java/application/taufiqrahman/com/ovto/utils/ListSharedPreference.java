package application.taufiqrahman.com.ovto.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Taufiq Rahman on 20/04/18.
 * Manage and save preferences
 */

public class ListSharedPreference {

    public static class Set {
        Context context;

        public Set(Context context) {
            this.context = context;
        }

        public void setLoginStatus(boolean isLoggedIn) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putBoolean("islogin", isLoggedIn).apply();
        }

        public void setUId(String id) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("uid", id).apply();
        }

        public void setSlug(String slug) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("slug", slug).apply();
        }

        public void setUName(String name) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("uname", name).apply();
        }

        public void setEmail(String email) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("email", email).apply();
        }

        public void setToken(String token) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("token", token).apply();
        }

        public void setMobile(String mobile) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("mobile", mobile).apply();
        }

        public void setAddress(String address) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("address", address).apply();
        }

        public void setImage(String image) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("image", image).apply();
        }

        public void setScore(float score) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putFloat("score", score).apply();
        }

        public void setStatus(String status) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("status", status).apply();
        }

        public void setItemPrice(String price, String id) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("price", price).apply();
            prefEditor.putString("price id", id).apply();
        }

        public void setIsNotifications(boolean isNotifications) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putBoolean("isNotifications", isNotifications).apply();
        }

        public void setNotificationFrom(String id){
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("notificationFrom", id).apply();
        }

        public void setCart(String cart){
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("cart", cart).apply();
        }

        public void setMenuHistory(String key, String cart){
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString(key, cart).apply();
        }

        public void setDp(String dp) {
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefEditor.putString("dp", dp).apply();
        }
    }//end Set


    public static class Get
    {
        Context context;

        public Get(Context context) {
            this.context = context;
        }


        public boolean getLoginStatus() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getBoolean("islogin", false);
        }

        public String getUId() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("uid", "id");
        }

        public String getSlug() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("slug", "");
        }

        public String getUName() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("uname", "agent");
        }

        public String getEmail() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("email", "example@domain.com");
        }

        public String getToken() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("token", "123");
        }

        public String getMobile() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("mobile", "add mobile number");
        }

        public String getAddress() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("address", "add address");
        }

        public float getScore() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getFloat("score",  0.0f);
        }

        public String getImage() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("image", "http://myspare.net/public/upload/default.jpg");
        }

        public String getStatus() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("status", "Bronze");
        }

        public String getItemPrice() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("price", "0.00");
        }

        public boolean getIsNotifications() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getBoolean("isNotifications", false);
        }

        public String getNotificationFrom() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("notificationFrom", null);
        }

        public String getCart(){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("cart", null);
        }

        public String getMenuHistory(String key){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString(key, null);
        }

        public String getDp(){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            return prefs.getString("dp", null);
        }
    }//end Get

}//end ListSharedPreference
