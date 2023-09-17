package bamboo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserCtl {

    @DubboReference
    public UserService userService;
    @GetMapping("/time")

    public String get(){
        String time = userService.getUser();
        return time;
    }
}
