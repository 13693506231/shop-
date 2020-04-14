package domain;

public class User {
   private String uid = null;
  private String username = null;
  private String password = null;
  private String name = null;
  private String email = null;
  private String birthday = null;
   private String gender = null;
  private String state = null;
  private String code = null;
  private String remark = null;

    public User() {
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getState() {
        return state;
    }

    public String getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }

    public User(String uid, String username, String password, String name, String email, String birthday, String gender, String state, String code, String remark) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.state = state;
        this.code = code;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", state='" + state + '\'' +
                ", code='" + code + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
