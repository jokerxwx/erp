package person.xwx.erpserver.handler;

import com.alibaba.fastjson2.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import person.xwx.erpserver.model.resp.ResponseResult;
import person.xwx.erpserver.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xwx
 * @date: 2022-12-07  22:16
 * @Description: 授权失败
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    /**
     * @param request               that resulted in an <code>AccessDeniedException</code>
     * @param response              so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "您的权限不足",null);
        String json = JSON.toJSONString(responseResult);
        WebUtils.renderString(response,json);
    }
}
