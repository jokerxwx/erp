package person.xwx.erpserver.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: xwx
 * @date: 2022-12-07  22:12
 * @Description: TODO
 */
@Component
public class WebUtils {
    /**
     *
     * @param response 渲染对象
     * @param string 带渲染的字符串
     * @return
     */
    public static String renderString(HttpServletResponse response,String string)
             {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
