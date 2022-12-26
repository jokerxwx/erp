package person.xwx.erpserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import person.xwx.erpserver.dao.MenuMapper;
import person.xwx.erpserver.dao.RoleMapper;
import person.xwx.erpserver.entity.User;
import person.xwx.erpserver.dao.UserMapper;
import person.xwx.erpserver.model.dto.UserDTO;

import java.util.List;
import java.util.Objects;

/**
 * @author: xwx
 * @date: 2022-12-05  18:19
 * @Description: TODO
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        if ( Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }

        List<String> list = roleMapper.selectPermissionByUserId(user.getId());

        return new UserDTO(user,list);
    }
}
