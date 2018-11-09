package application.taufiqrahman.com.ovto.models;

import java.util.List;

/*************************************
 * Created by Taufiq on 7/16/2018.  **
 *************************************/

public class RestaurantsModel {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        private int currentPage;
        private List<Datum> data = null;
        private String firstPageUrl;
        private int from;
        private int lastPage;
        private String lastPageUrl;
        private String nextPageUrl;
        private String path;
        private int perPage;
        private String prevPageUrl;
        private int to;
        private int total;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public String getFirstPageUrl() {
            return firstPageUrl;
        }

        public void setFirstPageUrl(String firstPageUrl) {
            this.firstPageUrl = firstPageUrl;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public String getLastPageUrl() {
            return lastPageUrl;
        }

        public void setLastPageUrl(String lastPageUrl) {
            this.lastPageUrl = lastPageUrl;
        }

        public String getNextPageUrl() {
            return nextPageUrl;
        }

        public void setNextPageUrl(String nextPageUrl) {
            this.nextPageUrl = nextPageUrl;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public String getPrevPageUrl() {
            return prevPageUrl;
        }

        public void setPrevPageUrl(String prevPageUrl) {
            this.prevPageUrl = prevPageUrl;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public class Datum {

            private String identifier;
            private String name;
            private String phonenumber;
            private String startTime;
            private String endTime;
            private String createdAt;
            private String updatedAt;
            private Long avgRatingCount;
            private Long categoryCount;
            private Long subCategoryCount;
            private Long totalItemCount;
            private Long availableItemCount;
            private Long isOpen;
            private String logo;
            private String cover;
            private List<Galleryimage> galleryimages = null;
            private Location location;
            private List<Promotionalimage> promotionalimages = null;
            private List<Category> categories = null;
            private List<Menu> menus = null;
            private Profile profile;

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

            public Long getAvgRatingCount() {
                return avgRatingCount;
            }

            public void setAvgRatingCount(Long avgRatingCount) {
                this.avgRatingCount = avgRatingCount;
            }

            public Long getCategoryCount() {
                return categoryCount;
            }

            public void setCategoryCount(Long categoryCount) {
                this.categoryCount = categoryCount;
            }

            public Long getSubCategoryCount() {
                return subCategoryCount;
            }

            public void setSubCategoryCount(Long subCategoryCount) {
                this.subCategoryCount = subCategoryCount;
            }

            public Long getTotalItemCount() {
                return totalItemCount;
            }

            public void setTotalItemCount(Long totalItemCount) {
                this.totalItemCount = totalItemCount;
            }

            public Long getAvailableItemCount() {
                return availableItemCount;
            }

            public void setAvailableItemCount(Long availableItemCount) {
                this.availableItemCount = availableItemCount;
            }

            public Long getIsOpen() {
                return isOpen;
            }

            public void setIsOpen(Long isOpen) {
                this.isOpen = isOpen;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
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

            public List<Category> getCategories() {
                return categories;
            }

            public void setCategories(List<Category> categories) {
                this.categories = categories;
            }

            public List<Menu> getMenus() {
                return menus;
            }

            public void setMenus(List<Menu> menus) {
                this.menus = menus;
            }

            public Profile getProfile() {
                return profile;
            }

            public void setProfile(Profile profile) {
                this.profile = profile;
            }

            public class Galleryimage {

                private Long id;
                private String identifier;
                private Long restaurantId;
                private String galleryPicture;
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

                public String getGalleryPicture() {
                    return galleryPicture;
                }

                public void setGalleryPicture(String galleryPicture) {
                    this.galleryPicture = galleryPicture;
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

            public class Category {

                private Long id;
                private Long restaurantId;
                private Object parentId;
                private String name;
                private String createdAt;
                private String updatedAt;
                private List<Object> children = null;

                public Long getId() {
                    return id;
                }

                public void setId(Long id) {
                    this.id = id;
                }

                public Long getRestaurantId() {
                    return restaurantId;
                }

                public void setRestaurantId(Long restaurantId) {
                    this.restaurantId = restaurantId;
                }

                public Object getParentId() {
                    return parentId;
                }

                public void setParentId(Object parentId) {
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

            }

            public class Profile {

                private int id;
                private int restaurantId;
                private String logo;
                private String cover;
                private String payableAmount;
                private String paymentReceived;
                private String lastPaid;
                private String score;
                private Object vatRegNumber;
                private String createdAt;
                private String updatedAt;
                private String status;

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

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getPayableAmount() {
                    return payableAmount;
                }

                public void setPayableAmount(String payableAmount) {
                    this.payableAmount = payableAmount;
                }

                public String getPaymentReceived() {
                    return paymentReceived;
                }

                public void setPaymentReceived(String paymentReceived) {
                    this.paymentReceived = paymentReceived;
                }

                public String getLastPaid() {
                    return lastPaid;
                }

                public void setLastPaid(String lastPaid) {
                    this.lastPaid = lastPaid;
                }

                public String getScore() {
                    return score;
                }

                public void setScore(String score) {
                    this.score = score;
                }

                public Object getVatRegNumber() {
                    return vatRegNumber;
                }

                public void setVatRegNumber(Object vatRegNumber) {
                    this.vatRegNumber = vatRegNumber;
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

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }


            public class Promotionalimage {

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

            private Long id;
            private String identifier;
            private Long restaurantId;
            private Long categoryId;
            private Object subCategoryId;
            private String genericname;
            private String foodtitle;
            private String description;
            private String defaultMenuPicture;
            private Long previousPrice;
            private Long actualPrice;
            private Long availability;
            private String startTime;
            private String endTime;
            private String vat;
            private String discount;
            private Long isFinished;
            private String createdAt;
            private String updatedAt;
            private Long price;
            private Long isAvailable;
            private Long todaySales;
            private Long yesterdaySales;
            private Long currentMonthSales;
            private Long lastMonthSales;
            private Long currentYearSales;
            private Long lastYearSales;
            private List<Object> menuimages = null;
            private Menuextra menuextra;

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

            public Long getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Long categoryId) {
                this.categoryId = categoryId;
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

            public Long getPreviousPrice() {
                return previousPrice;
            }

            public void setPreviousPrice(Long previousPrice) {
                this.previousPrice = previousPrice;
            }

            public Long getActualPrice() {
                return actualPrice;
            }

            public void setActualPrice(Long actualPrice) {
                this.actualPrice = actualPrice;
            }

            public Long getAvailability() {
                return availability;
            }

            public void setAvailability(Long availability) {
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

            public Long getIsFinished() {
                return isFinished;
            }

            public void setIsFinished(Long isFinished) {
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

            public Long getPrice() {
                return price;
            }

            public void setPrice(Long price) {
                this.price = price;
            }

            public Long getIsAvailable() {
                return isAvailable;
            }

            public void setIsAvailable(Long isAvailable) {
                this.isAvailable = isAvailable;
            }

            public Long getTodaySales() {
                return todaySales;
            }

            public void setTodaySales(Long todaySales) {
                this.todaySales = todaySales;
            }

            public Long getYesterdaySales() {
                return yesterdaySales;
            }

            public void setYesterdaySales(Long yesterdaySales) {
                this.yesterdaySales = yesterdaySales;
            }

            public Long getCurrentMonthSales() {
                return currentMonthSales;
            }

            public void setCurrentMonthSales(Long currentMonthSales) {
                this.currentMonthSales = currentMonthSales;
            }

            public Long getLastMonthSales() {
                return lastMonthSales;
            }

            public void setLastMonthSales(Long lastMonthSales) {
                this.lastMonthSales = lastMonthSales;
            }

            public Long getCurrentYearSales() {
                return currentYearSales;
            }

            public void setCurrentYearSales(Long currentYearSales) {
                this.currentYearSales = currentYearSales;
            }

            public Long getLastYearSales() {
                return lastYearSales;
            }

            public void setLastYearSales(Long lastYearSales) {
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

        }

        public class Menuextra {

            private Long id;
            private Long menuId;
            private Object name;
            private String chargePercentage;
            private Long chargeAmount;
            private String createdAt;
            private String updatedAt;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public Long getMenuId() {
                return menuId;
            }

            public void setMenuId(Long menuId) {
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

            public Long getChargeAmount() {
                return chargeAmount;
            }

            public void setChargeAmount(Long chargeAmount) {
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
}
