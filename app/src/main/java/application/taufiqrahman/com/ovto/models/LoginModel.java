package application.taufiqrahman.com.ovto.models;

/*****************************************
 **  Created by Taufiq on 7/10/2018.    **
 *****************************************/
public class LoginModel {

    private DataBean data;
    private UnreadNotifications unreadNotifications;
    private MetaBean meta;
    private ErrorBean error;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public UnreadNotifications getUnreadNotifications() {
        return unreadNotifications;
    }

    public void setUnreadNotifications(UnreadNotifications unreadNotifications) {
        this.unreadNotifications = unreadNotifications;
    }

    public MetaBean getToken() {
        return meta;
    }

    public void setToken(MetaBean meta) {
        this.meta = meta;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public static class DataBean {

        private ProfileBean profile;

        public ProfileBean getProfile() {
            return profile;
        }

        public void setProfile(ProfileBean profile) {
            this.profile = profile;
        }

        private String id;
        private String name;
        private String email;
        private String mobile;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public static class ProfileBean {

            private String id;
            private String user_id;
            private String avatar;
            private String phonenumber;
            private String address;
            private float score;
            private String created_at;
            private String updated_at;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getPhonenumber() {
                return phonenumber;
            }

            public void setPhonenumber(String phonenumber) {
                this.phonenumber = phonenumber;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public float getScore() {
                return score;
            }

            public void setScore(float score) {
                this.score = score;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

        }
    }

    public static class UnreadNotifications {

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

    public static class ErrorBean {
        private Errors errors;

        public Errors getErrors() {
            return errors;
        }

        public void setErrors(Errors errors) {
            this.errors = errors;
        }

        public static class Errors {
            private String message;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }
}

