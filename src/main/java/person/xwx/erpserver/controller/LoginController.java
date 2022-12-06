package person.xwx.erpserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import person.xwx.erpserver.entity.User;
import person.xwx.erpserver.model.resp.ResponseResult;
import person.xwx.erpserver.service.LoginService;

/**
 * @author: xwx
 * @date: 2022-12-06  16:15
 * @Description: TODO
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }
}
