package com.test.springsecurity.dao;

import com.test.springsecurity.entity.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserDao {

    private Map<Integer, User> map = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        map.put(1, new User(1, "god", "123"));
        map.put(2, new User(2, "xxx", "123"));
        map.put(3, new User(3, "zzz", "123"));
    }

    public User getByName(String username) {
        for (Map.Entry<Integer, User> entry: map.entrySet()) {
            if (entry.getValue().getName().equals(username)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
