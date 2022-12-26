package person.xwx.erpserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan(value = "person.xwx.erpserver.dao")
public class ErpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpServerApplication.class, args);
    }
}
