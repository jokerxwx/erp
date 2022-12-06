package person.xwx.erpserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xwx
 * @date: 2022-12-06  14:41
 * @Description: TODO
 */

@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }
}
