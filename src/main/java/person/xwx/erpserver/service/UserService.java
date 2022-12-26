package person.xwx.erpserver.service;

import person.xwx.erpserver.model.req.UserReq;
import person.xwx.erpserver.model.resp.ResponseResult;
import person.xwx.erpserver.model.vo.UserVO;

/**
 * @author: xwx
 * @date: 2022-12-08  15:45
 * @Description: TODO
 */
public interface UserService {
    /**
     *获取登录的当前用户的信息
     * @param string token
     * @return
     */
    ResponseResult<UserVO> fetchCurrentUser(String string);

    /**
     * 获取用户列表，分页查询
     * @param current 当前页码
     * @param pageSize  一页多少条数据
     * @return
     */
    ResponseResult fetchUserList(Integer current,Integer pageSize);

    /**
     *创建用户
     * @param userReq 前端请求的user模板
     * @return
     */
    ResponseResult createUser(UserReq userReq);

    /**
     *更新用户信息
     * @param userReq 前端请求的user模板
     * @return
     */
    ResponseResult updateUser(UserReq userReq);

    /**
     *删除用户
     * @param id 用户id
     * @return
     */
    ResponseResult deleteUser(Long id);
}
