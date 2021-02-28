package com.github.hcsp.app;

import com.github.hcsp.starter.MyResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class MyController {
    @GetMapping("/users")
    @MyResponseBody
    public List<User> users() {
        return Arrays.asList(new User(1, "A"), new User(2, "B"));
    }

    public static class User {
        Integer id;
        String name;

        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

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
    }
}
