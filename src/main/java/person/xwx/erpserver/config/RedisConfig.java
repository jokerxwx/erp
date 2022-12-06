package person.xwx.erpserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis configuration.
 *
 * @author: xwx
 * @date: 2022-12-06  17:41
 * @Description: TODO
 */
@Configuration
public class RedisConfig {

    /**
     * redis template.
     *
     * @param factory factory
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //key采用String的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        //hash的key也采用String的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        //value 的序列化方式采用 JSON
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //hash value 的序列化方式也采用 JSON
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}