package application.taufiqrahman.com.ovto.models;

import java.util.List;

/**
 *  Created by Taufiq on 8/31/2018.
 */

public class PromotionalOffersModel {

    private List<Datum> data = null;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        private Integer id;
        private String name;
        private String description;
        private String offer_pic;
        private Integer required_score;
        private String how_to_use;
        private String terms_and_conditions;
        private String createdAt;
        private String updatedAt;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getOffer_pic() {
            return offer_pic;
        }

        public void setOffer_pic(String offer_pic) {
            this.offer_pic = offer_pic;
        }

        public Integer getRequired_score() {
            return required_score;
        }

        public void setRequired_score(Integer required_score) {
            this.required_score = required_score;
        }

        public String getHow_to_use() {
            return how_to_use;
        }

        public void setHow_to_use(String how_to_use) {
            this.how_to_use = how_to_use;
        }

        public String getTerms_and_conditions() {
            return terms_and_conditions;
        }

        public void setTerms_and_conditions(String terms_and_conditions) {
            this.terms_and_conditions = terms_and_conditions;
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
