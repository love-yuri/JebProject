package cn.cslg.model;

public class User {

    private Integer uid;
    private String uname;
    private String upwd;
    private String utel;
    private String uemail;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", utel='" + utel + '\'' +
                ", uemail='" + uemail + '\'' +
                '}';
    }

    public User(Integer uid, String uname, String upwd, String utel, String uemail) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.utel = utel;
        this.uemail = uemail;
    }

    public User() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }
}
