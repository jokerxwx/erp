package person.xwx.erpserver.service.impl;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import person.xwx.erpserver.dao.RoleMapper;
import person.xwx.erpserver.dao.UserMapper;
import person.xwx.erpserver.dao.UserRoleMapper;
import person.xwx.erpserver.entity.Role;
import person.xwx.erpserver.entity.User;
import person.xwx.erpserver.entity.UserRole;
import person.xwx.erpserver.model.req.UserReq;
import person.xwx.erpserver.model.resp.ResponseResult;
import person.xwx.erpserver.model.vo.UserVO;
import person.xwx.erpserver.service.UserService;
import person.xwx.erpserver.utils.JwtUtils;
import person.xwx.erpserver.utils.RedisUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xwx
 * @date: 2022-12-08  15:45
 * @Description: TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @param string token
     * @return
     */
    @Override
    public ResponseResult<UserVO> fetchCurrentUser(String string) {
        Claims claims = JwtUtils.parseToken(string);
        Long id = claims.get("Id", Long.class);
        User user = userMapper.selectById(id);
        List<String> list = roleMapper.selectPermissionByUserId(id);
        UserVO userVO = new UserVO();
        userVO.setAvatar(user.getAvatar());
        userVO.setId(user.getId());
        userVO.setMobile(user.getMobile());
        userVO.setEmail(userVO.getEmail());
        userVO.setRoles(list);
        return new ResponseResult<>(200, "当前用户查询成功", userVO);
    }

    /**
     * 获取用户列表，分页查询
     *
     * @param current  当前页码
     * @param pageSize 一页多少条数据
     * @return
     */
    @Override
    public ResponseResult fetchUserList(Integer current, Integer pageSize) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        // 创建分页对象（current表示页码；pageSize表示每页大小）
        Page<User> page = new Page<>(current, pageSize);
        Page<User> result = userMapper.selectPage(page, wrapper);

        return new ResponseResult(200, "查询成功", result);
    }

    /**
     * @param userReq 用户请求格式
     * @return
     */
    @Override
    public ResponseResult createUser(UserReq userReq) {
        //不明文存储密码，使用spring security自带的编码，转换
        userReq.setPassword(passwordEncoder.encode(userReq.getPassword()));
        User user = new User();
        user.setName(userReq.getName());
        user.setAvatar(userReq.getAvatar());
        user.setEmail(userReq.getEmail());
        user.setPassword(userReq.getPassword());
        user.setMobile(userReq.getMobile());
        user.setUsername(userReq.getUsername());
        Integer result = userMapper.insert(user);

        //存储用户角色
        List<UserRole> list = new ArrayList<>();
        for ( String string : userReq.getRoles() ) {
            UserRole userRole = new UserRole();
            userRole.setUser_id(user.getId());
            userRole.setRole_id(Long.valueOf(String.valueOf(redisUtils.get(string))));
            list.add(userRole);
        }
        for ( UserRole userRole : list ) {
            userRoleMapper.insert(userRole);
        }

        if ( result == 0 ) {
            return new ResponseResult<>(500, "创建用户失败", null);
        }
        return new ResponseResult<>(200, "创建用户成功", null);
    }

    /**
     * @param userReq 前端请求的user模板
     * @return
     */
    @Override
    public ResponseResult updateUser(UserReq userReq) {
        User user = userMapper.selectById(userReq.getId());
        //修改用户的角色，每次修改都删除原来的，插入新的
        List<UserRole> list = new ArrayList<>();
        for ( String string : userReq.getRoles() ) {
            UserRole userRole = new UserRole();
            userRole.setUser_id(userReq.getId());
            userRole.setRole_id(Long.valueOf(String.valueOf(redisUtils.get(string))));
            list.add(userRole);
        }
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userReq.getId());
        userRoleMapper.delete(queryWrapper);
        for ( UserRole userRole : list ) {
            userRoleMapper.insert(userRole);
        }
        //如果密码相同，就不修改
        if ( user.getPassword().equals(userReq.getPassword()) ) {
        } else {
            userReq.setPassword(passwordEncoder.encode(userReq.getPassword()));
            user.setPassword(userReq.getPassword());
        }
        //不明文存储密码，使用spring security自带的编码，转换
        user.setId(userReq.getId());
        user.setName(userReq.getName());
        user.setAvatar(userReq.getAvatar());
        user.setEmail(userReq.getEmail());
        user.setMobile(userReq.getMobile());
        user.setUsername(userReq.getUsername());
        Integer result = userMapper.updateById(user);
        if ( result == 0 ) {
            return new ResponseResult<>(500, "更新用户失败", null);
        }
        return new ResponseResult<>(200, "更新用户成功", null);

    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    @Override
    public ResponseResult deleteUser(Long id) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        userRoleMapper.delete(queryWrapper);
        int result = userMapper.deleteById(id);
        if ( result == 0 ) {
            return new ResponseResult<>(500, "删除用户失败", null);
        }
        return new ResponseResult<>(200, "删除用户成功", null);
    }

}
