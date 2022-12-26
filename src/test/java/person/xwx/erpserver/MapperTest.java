package person.xwx.erpserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import person.xwx.erpserver.dao.MenuMapper;
import person.xwx.erpserver.utils.RedisUtils;

import java.util.List;

/**
 * @author: xwx
 * @date: 2022-12-06  15:42
 * @Description: TODO
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void MapperTest() {
        System.out.println(redisUtils.get("admin"));
    }
}
