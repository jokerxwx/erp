package person.xwx.erpserver.handler;

import com.alibaba.fastjson2.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import person.xwx.erpserver.model.resp.ResponseResult;
import person.xwx.erpserver.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xwx
 * @date: 2022-12-07  22:06
 * @Description: TODO
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    /**
     * @param request       that resulted in an <code>AuthenticationException</code>
     * @param response      so that the user agent can begin authentication
     * @param authException that caused the invocation
     * @throws IOException
     * @throws ServletException
     * @description 认证失败
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "用户认证失败，请重新登录",null);
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(response,json);

    }
}
