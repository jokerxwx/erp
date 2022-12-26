package person.xwx.erpserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import person.xwx.erpserver.model.req.LoginReq;
import person.xwx.erpserver.model.resp.ResponseResult;
import person.xwx.erpserver.service.LoginService;

/**
 * @author: xwx
 * @date: 2022-12-06  16:15
 * @Description: TODO
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody LoginReq loginReq) {
        return loginService.login(loginReq);
    }

    @GetMapping(value = "/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
