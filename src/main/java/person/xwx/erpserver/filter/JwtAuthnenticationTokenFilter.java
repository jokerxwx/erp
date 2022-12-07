package person.xwx.erpserver.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import person.xwx.erpserver.entity.User;
import person.xwx.erpserver.utils.JwtUtils;
import person.xwx.erpserver.utils.RedisUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xwx
 * @date: 2022-12-06  18:30
 * @Description: TODO
 */
@Component
public class JwtAuthnenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if ( !StringUtils.hasText(token) ) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        Long id;
        try {
            Claims claims = JwtUtils.parseToken(token);
            id = claims.get("Id", Long.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token有问题");
        }
        User user = (User) redisUtils.get("login:" + id);
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
