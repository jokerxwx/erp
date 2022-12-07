package person.xwx.erpserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "person.xwx.erpserver.mapper")
public class ErpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpServerApplication.class, args);
    }
}
