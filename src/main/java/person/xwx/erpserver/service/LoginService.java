package person.xwx.erpserver.service;

import person.xwx.erpserver.model.req.LoginReq;
import person.xwx.erpserver.model.resp.ResponseResult;

public interface LoginService {
    /**
     * 登录接口
     * @param loginReq
     * @return
     */
    ResponseResult login(LoginReq loginReq);

    /**
     * 用户登出
     * @return
     */
    ResponseResult logout();
}
