package cn.cslg.db;

import cn.cslg.model.Flower;
import cn.cslg.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> userList=new ArrayList<>();
    static {
        userList.add(new User(1,"admin","admin","10086","admin@163.com"));
        userList.add(new User(2,"1","1","10000","1@163.ocm"));
    }

}
