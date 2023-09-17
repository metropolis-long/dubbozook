package bamboo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.service.UserService;

@DubboService
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public String getUser() {
        System.out.println("UserServiceImpl.getTime");
        log.info("ddddddddddd");
        return "2222222222222";
    }
}
