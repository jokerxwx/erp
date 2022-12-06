package person.xwx.erpserver.service;

import person.xwx.erpserver.entity.User;
import person.xwx.erpserver.model.resp.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);
}
