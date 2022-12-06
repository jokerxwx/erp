package person.xwx.erpserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import person.xwx.erpserver.entity.User;
import person.xwx.erpserver.mapper.UserMapper;

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

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        if ( Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        return user;
    }
}
