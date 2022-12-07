package person.xwx.erpserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import person.xwx.erpserver.model.resp.ResponseResult;

/**
 * @author: xwx
 * @date: 2022-12-06  14:41
 * @Description: TODO
 */

@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    @PreAuthorize("hasAuthority('test')")
    public ResponseResult hello() {
        return new ResponseResult<>(200,"hello",null);
    }
}
