package person.xwx.erpserver.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import person.xwx.erpserver.model.req.RoleReq;
import person.xwx.erpserver.model.req.UserReq;
import person.xwx.erpserver.model.resp.ResponseResult;
import person.xwx.erpserver.model.vo.UserVO;
import person.xwx.erpserver.service.RoleService;
import person.xwx.erpserver.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xwx
 * @date: 2022-12-09  20:36
 * @Description: TODO
 */
@RestController
@RequestMapping("/api/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseResult fetchRoleList(
            @ApiParam("页码") @RequestParam(value = "current", defaultValue = "1") int current,
            @ApiParam("页面容量") @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return roleService.fetchRoleList(current, pageSize);
    }

    /**
     *创建用户角色
     * @param roleReq
     * @return
     */
    @PostMapping("/create")
    @ApiOperation(value = "创建角色", notes = "创建角色")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseResult createUser(@RequestBody RoleReq roleReq) {
        return roleService.createRole(roleReq);
    }

    /**
     *更新角色
     * @param roleReq
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新角色", notes = "更新角色")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseResult updateUser(@RequestBody RoleReq roleReq) {
        return roleService.updateRole(roleReq);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseResult deleteUser(@ApiParam("主键") @PathVariable("id") Long id) {
        return roleService.deleteRole(id);
    }

    @GetMapping("/fetch/{id}")
    @ApiOperation(value = "获取主键为id的用户的角色", notes = "获取主键为id的用户的角色")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseResult fetchCurrentUserRole(@ApiParam("用户主键") @PathVariable("id") Long id) {
        return roleService.fetchCurrentUserRole(id);
    }
}
