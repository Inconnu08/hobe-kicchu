package application.taufiqrahman.com.ovto.models;

/**
 *   Created by Taufiq on 7/13/2018.
 */

public class RegisterModel {

    private DataBean data;
    private MetaBean meta;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public MetaBean getToken() {
        return meta;
    }

    public void setToken(MetaBean meta) {
        this.meta = meta;
    }


    public static class DataBean {

        private String id;
        private String name;
        private String email;
        private String slug;
        private String code;
        private String created_at;
        private String updated_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMail() {
            return email;
        }

        public void setMail(String mail) {
            this.email = mail;
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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }


    public static class MetaBean {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}
