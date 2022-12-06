package person.xwx.erpserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import person.xwx.erpserver.mapper.UserMapper;

/**
 * @author: xwx
 * @date: 2022-12-06  15:42
 * @Description: TODO
 */
@SpringBootTest
public class MapperTest {

    @Autowired
     PasswordEncoder passwordEncoder;
    @Test
    public void MapperTest() {
        System.out.printf(passwordEncoder.encode("123456"));
    }
}
