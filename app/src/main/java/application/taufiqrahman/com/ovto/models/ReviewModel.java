package application.taufiqrahman.com.ovto.models;

/**
 * Created by Taufiq on 8/20/2018.
 */

public class ReviewModel {

    private String message;
    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        private String review;
        private Integer rating;
        private String updated_at;
        private String created_at;

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

    }
}
