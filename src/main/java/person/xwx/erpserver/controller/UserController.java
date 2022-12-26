package person.xwx.erpserver.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import person.xwx.erpserver.model.req.UserReq;
import person.xwx.erpserver.model.resp.ResponseResult;
import person.xwx.erpserver.model.vo.UserVO;
import person.xwx.erpserver.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xwx
 * @date: 2022-12-08  13:59
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/current")
    @ApiOperation(value = "获取当前用户", notes = "获取当前已经登录的用户")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseResult<UserVO> fetchCurrentUser(HttpServletRequest request) {
        return userService.fetchCurrentUser(request.getHeader("token"));
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseResult<UserVO> fetchUserList(
            @ApiParam("页码") @RequestParam(value = "current", defaultValue = "1") int current,
            @ApiParam("页面容量") @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return userService.fetchUserList(current, pageSize);
    }

    @PostMapping("/create")
    @ApiOperation(value = "创建用户", notes = "创建用户")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseResult createUser(@RequestBody UserReq userReq) {
        return userService.createUser(userReq);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户", notes = "更新用户")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseResult updateUser(@RequestBody UserReq userReq) {
        return userService.updateUser(userReq);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除用户", notes = "删除用户,需要增加删除用户的权限")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseResult deleteUser(@ApiParam("主键") @PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

}
