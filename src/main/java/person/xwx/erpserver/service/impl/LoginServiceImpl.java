package person.xwx.erpserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import person.xwx.erpserver.entity.User;
import person.xwx.erpserver.model.dto.UserDTO;
import person.xwx.erpserver.model.req.LoginReq;
import person.xwx.erpserver.model.resp.ResponseResult;
import person.xwx.erpserver.service.LoginService;
import person.xwx.erpserver.service.RoleService;
import person.xwx.erpserver.utils.JwtUtils;
import person.xwx.erpserver.utils.RedisUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: xwx
 * @date: 2022-12-06  16:49
 * @Description: TODO
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    RoleService roleService;

    /**
     * 用户认证，如果认证通过，使用userid生成token
     *
     * @return
     */
    @Override
    public ResponseResult login(LoginReq loginReq) {
        //Authentication authenticate进行用户认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if ( Objects.isNull(authentication) ) {
            throw new RuntimeException("登陆失败");
        }
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        String token = JwtUtils.generateToken(userDTO.getUser());
        redisUtils.set("login:" + userDTO.getUser().getId(), userDTO);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        //将role的名字和id对应起来，存入redis
        roleService.saveRoleNameToId();

        return new ResponseResult<>(200, "登陆成功", map);
    }

    /**
     * @return
     */
    @Override
    public ResponseResult logout() {
        //获取securityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        redisUtils.del("login:" + user.getId());
        return new ResponseResult<>(200,"注销成功",null);
    }
}
