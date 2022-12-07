package person.xwx.erpserver.service;

import person.xwx.erpserver.entity.User;
import person.xwx.erpserver.model.resp.ResponseResult;

public interface LoginService {
    /**
     * 登录接口
     * @param user
     * @return
     */
    ResponseResult login(User user);

    /**
     * 用户登出
     * @return
     */
    ResponseResult logout();
}
