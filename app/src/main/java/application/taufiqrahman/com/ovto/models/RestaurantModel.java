package application.taufiqrahman.com.ovto.models;

import java.util.List;

/***********************************
 * Created by Taufiq on 7/26/2018. *
 ***********************************/

public class RestaurantModel {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

        private String identifier;
        private String name;
        private String phonenumber;
        private String startTime;
        private String endTime;
        private String createdAt;
        private String updatedAt;
        private Integer reviews_count;
        private Integer avgRatingCount;
        private Integer categoryCount;
        private Integer subCategoryCount;
        private Integer totalItemCount;
        private Integer availableItemCount;
        private Integer isOpen;
        private String logo;
        private String cover;
        private List<Galleryimage> galleryimages = null;
        private Location location;
        private List<Promotionalimage> promotionalimages;
        private List<Reviews> reviews = null;
        private List<Menu> menus = null;
        private List<Feature> features = null;
        private List<Category> categories = null;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getReviews_count() {
            return reviews_count;
        }

        public void setReviews_count(Integer reviews_count) {
            this.reviews_count = reviews_count;
        }

        public Integer getAvgRatingCount() {
            return avgRatingCount;
        }

        public void setAvgRatingCount(Integer avgRatingCount) {
            this.avgRatingCount = avgRatingCount;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public Integer getCategoryCount() {
            return categoryCount;
        }

        public void setCategoryCount(Integer categoryCount) {
            this.categoryCount = categoryCount;
        }

        public Integer getSubCategoryCount() {
            return subCategoryCount;
        }

        public void setSubCategoryCount(Integer subCategoryCount) {
            this.subCategoryCount = subCategoryCount;
        }

        public Integer getTotalItemCount() {
            return totalItemCount;
        }

        public void setTotalItemCount(Integer totalItemCount) {
            this.totalItemCount = totalItemCount;
        }

        public Integer getAvailableItemCount() {
            return availableItemCount;
        }

        public void setAvailableItemCount(Integer availableItemCount) {
            this.availableItemCount = availableItemCount;
        }

        public Integer getIsOpen() {
            return isOpen;
        }

        public void setIsOpen(Integer isOpen) {
            this.isOpen = isOpen;
        }

        public List<Galleryimage> getGalleryimages() {
            return galleryimages;
        }

        public void setGalleryimages(List<Galleryimage> galleryimages) {
            this.galleryimages = galleryimages;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public List<Promotionalimage> getPromotionalimages() {
            return promotionalimages;
        }

        public void setPromotionalimages(List<Promotionalimage> promotionalimages) {
            this.promotionalimages = promotionalimages;
        }

        public List<Reviews> getReviews() {
            return reviews;
        }

        public void setReviews(List<Reviews> reviews) {
            this.reviews = reviews;
        }

        public List<Menu> getMenus() {
            return menus;
        }

        public void setMenus(List<Menu> menus) {
            this.menus = menus;
        }

        public List<Feature> getFeatures() {
            return features;
        }

        public void setFeatures(List<Feature> features) {
            this.features = features;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public class Reviews {

            private String review;
            private Integer rating;
            private String updated_at;
            private String created_at;
            private User user;

            public User getUser() {
                return user;
            }

            public void setUser(User user) {
                this.user = user;
            }

            public String getReview() {
                return review;
            }

            public void setReview(String review) {
                this.review = review;
            }

            public Integer getRating() {
                return rating;
            }

            public void setRating(Integer rating) {
                this.rating = rating;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public class User {

                private Integer id;
                private String name;
                private String email;
                private String slug;
                private String code;
                private Profile profile;
                private List<Object> unreadNotifications = null;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getSlug() {
                    return slug;
                }

                public void setSlug(String slug) {
                    this.slug = slug;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }


                public List<Object> getUnreadNotifications() {
                    return unreadNotifications;
                }

                public void setUnreadNotifications(List<Object> unreadNotifications) {
                    this.unreadNotifications = unreadNotifications;
                }

                public Profile getProfile() {
                    return profile;
                }

                public void setProfile(Profile profile) {
                    this.profile = profile;
                }

                public class Profile {

                    private Integer id;
                    private Integer userId;
                    private String avatar;
                    private Object phonenumber;
                    private Object address;
                    private String score;
                    private String status;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public Integer getUserId() {
                        return userId;
                    }

                    public void setUserId(Integer userId) {
                        this.userId = userId;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public Object getPhonenumber() {
                        return phonenumber;
                    }

                    public void setPhonenumber(Object phonenumber) {
                        this.phonenumber = phonenumber;
                    }

                    public Object getAddress() {
                        return address;
                    }

                    public void setAddress(Object address) {
                        this.address = address;
                    }

                    public String getScore() {
                        return score;
                    }

                    public void setScore(String score) {
                        this.score = score;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                }

            }

        }

        public static class Promotionalimage {

            private Long id;
            private String identifier;
            private Long restaurantId;
            private String promotional_picture;
            private String createdAt;
            private String updatedAt;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getIdentifier() {
                return identifier;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }

            public Long getRestaurantId() {
                return restaurantId;
            }

            public void setRestaurantId(Long restaurantId) {
                this.restaurantId = restaurantId;
            }

            public String getPromotionalPicture() {
                return promotional_picture;
            }

            public void setPromotionalPicture(String promotional_picture) {
                this.promotional_picture = promotional_picture;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

        }
    }

    public class Feature {

        private Integer id;
        private Integer restaurantId;
        private String name;
        private String createdAt;
        private String updatedAt;
        private Integer favoritesCount;
        private Boolean isFavorited;
        private List<Object> favorites = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getRestaurantId() {
            return restaurantId;
        }

        public void setRestaurantId(Integer restaurantId) {
            this.restaurantId = restaurantId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getFavoritesCount() {
            return favoritesCount;
        }

        public void setFavoritesCount(Integer favoritesCount) {
            this.favoritesCount = favoritesCount;
        }

        public Boolean getIsFavorited() {
            return isFavorited;
        }

        public void setIsFavorited(Boolean isFavorited) {
            this.isFavorited = isFavorited;
        }

        public List<Object> getFavorites() {
            return favorites;
        }

        public void setFavorites(List<Object> favorites) {
            this.favorites = favorites;
        }

    }

    public class Category  {

        // implements Parcelable
        private int id;
        private int restaurantId;
        private int parentId;
        private String name;
        private String createdAt;
        private String updatedAt;
        private List<Object> children = null;

//        public Category(Integer id, Integer restaurantId, Object parentId, String name, String createdAt, String updatedAt, List<Object> children) {
//            this.id = id;
//            this.restaurantId = restaurantId;
//            this.parentId = parentId;
//            this.name = name;
//            this.createdAt = createdAt;
//            this.updatedAt = updatedAt;
//            this.children = children;
//        }

//        public Category(Parcel in) {
//            id = in.readInt();
//            restaurantId = in.readInt();
//            parentId = in.readInt();
//            name = in.readString();
//            createdAt = in.readString();
//            updatedAt = in.readString();
//        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRestaurantId() {
            return restaurantId;
        }

        public void setRestaurantId(int restaurantId) {
            this.restaurantId = restaurantId;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public List<Object> getChildren() {
            return children;
        }

        public void setChildren(List<Object> children) {
            this.children = children;
        }

//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeInt(id);
//            dest.writeInt(restaurantId);
//            dest.writeInt(parentId);
//            dest.writeString(name);
//            dest.writeString(createdAt);
//            dest.writeString(updatedAt);
//        }
//
//        public static final Parcelable.Creator<Category> CREATOR = new Creator<Category>()
//        {
//            public Category createFromParcel(Parcel in)
//            {
//                return new Category(in);
//            }
//            public Category[] newArray(int size)
//            {
//                return new Category[size];
//            }
//        };
    }

    public class Location {

        private String house;
        private String road;
        private String details;
        private String country;
        private String createdAt;
        private String updatedAt;
        private String cityName;
        private String areaName;

        public String getHouse() {
            return house;
        }

        public void setHouse(String house) {
            this.house = house;
        }

        public String getRoad() {
            return road;
        }

        public void setRoad(String road) {
            this.road = road;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

    }

    public class Menu {

        private Integer id;
        private String identifier;
        private Integer restaurantId;
        private int category_id;
        private Object subCategoryId;
        private String genericname;
        private String foodtitle;
        private String description;
        private String defaultMenuPicture;
        private Integer previousPrice;
        private Integer actualPrice;
        private Integer availability;
        private String startTime;
        private String endTime;
        private String vat;
        private String discount;
        private Integer isFinished;
        private String createdAt;
        private String updatedAt;
        private Integer price;
        private Integer isAvailable;
        private Integer todaySales;
        private Integer yesterdaySales;
        private Integer currentMonthSales;
        private Integer lastMonthSales;
        private Integer currentYearSales;
        private Integer lastYearSales;
        private List<Object> menuimages = null;
        private Menuextra menuextra;
        private int quantity = 1;
        private int costPerItem;

        public int getCostPerItem() {
            return costPerItem;
        }

        public void setCostPerItem(int costPerItem) {
            this.costPerItem = costPerItem;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public Integer getRestaurantId() {
            return restaurantId;
        }

        public void setRestaurantId(Integer restaurantId) {
            this.restaurantId = restaurantId;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public Object getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(Object subCategoryId) {
            this.subCategoryId = subCategoryId;
        }

        public String getGenericname() {
            return genericname;
        }

        public void setGenericname(String genericname) {
            this.genericname = genericname;
        }

        public String getFoodtitle() {
            return foodtitle;
        }

        public void setFoodtitle(String foodtitle) {
            this.foodtitle = foodtitle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDefaultMenuPicture() {
            return defaultMenuPicture;
        }

        public void setDefaultMenuPicture(String defaultMenuPicture) {
            this.defaultMenuPicture = defaultMenuPicture;
        }

        public Integer getPreviousPrice() {
            return previousPrice;
        }

        public void setPreviousPrice(Integer previousPrice) {
            this.previousPrice = previousPrice;
        }

        public Integer getActualPrice() {
            return actualPrice;
        }

        public void setActualPrice(Integer actualPrice) {
            this.actualPrice = actualPrice;
        }

        public Integer getAvailability() {
            return availability;
        }

        public void setAvailability(Integer availability) {
            this.availability = availability;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getVat() {
            return vat;
        }

        public void setVat(String vat) {
            this.vat = vat;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public Integer getIsFinished() {
            return isFinished;
        }

        public void setIsFinished(Integer isFinished) {
            this.isFinished = isFinished;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Integer getIsAvailable() {
            return isAvailable;
        }

        public void setIsAvailable(Integer isAvailable) {
            this.isAvailable = isAvailable;
        }

        public Integer getTodaySales() {
            return todaySales;
        }

        public void setTodaySales(Integer todaySales) {
            this.todaySales = todaySales;
        }

        public Integer getYesterdaySales() {
            return yesterdaySales;
        }

        public void setYesterdaySales(Integer yesterdaySales) {
            this.yesterdaySales = yesterdaySales;
        }

        public Integer getCurrentMonthSales() {
            return currentMonthSales;
        }

        public void setCurrentMonthSales(Integer currentMonthSales) {
            this.currentMonthSales = currentMonthSales;
        }

        public Integer getLastMonthSales() {
            return lastMonthSales;
        }

        public void setLastMonthSales(Integer lastMonthSales) {
            this.lastMonthSales = lastMonthSales;
        }

        public Integer getCurrentYearSales() {
            return currentYearSales;
        }

        public void setCurrentYearSales(Integer currentYearSales) {
            this.currentYearSales = currentYearSales;
        }

        public Integer getLastYearSales() {
            return lastYearSales;
        }

        public void setLastYearSales(Integer lastYearSales) {
            this.lastYearSales = lastYearSales;
        }

        public List<Object> getMenuimages() {
            return menuimages;
        }

        public void setMenuimages(List<Object> menuimages) {
            this.menuimages = menuimages;
        }

        public Menuextra getMenuextra() {
            return menuextra;
        }

        public void setMenuextra(Menuextra menuextra) {
            this.menuextra = menuextra;
        }

        public class Menuextra {

            private Integer id;
            private Integer menuId;
            private Object name;
            private String chargePercentage;
            private Integer chargeAmount;
            private String createdAt;
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getMenuId() {
                return menuId;
            }

            public void setMenuId(Integer menuId) {
                this.menuId = menuId;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public String getChargePercentage() {
                return chargePercentage;
            }

            public void setChargePercentage(String chargePercentage) {
                this.chargePercentage = chargePercentage;
            }

            public Integer getChargeAmount() {
                return chargeAmount;
            }

            public void setChargeAmount(Integer chargeAmount) {
                this.chargeAmount = chargeAmount;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

        }
    }

    public class Galleryimage {

        private Long id;
        private String identifier;
        private Long restaurantId;
        private String gallery_picture;
        private String createdAt;
        private String updatedAt;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public Long getRestaurantId() {
            return restaurantId;
        }

        public void setRestaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
        }

        public String getGallery_picture() {
            return gallery_picture;
        }

        public void setGallery_picture(String gallery_picture) {
            this.gallery_picture = gallery_picture;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}
