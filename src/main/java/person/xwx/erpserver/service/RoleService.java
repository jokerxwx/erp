package person.xwx.erpserver.service;

import person.xwx.erpserver.model.req.RoleReq;
import person.xwx.erpserver.model.resp.ResponseResult;

/**
 * @author: xwx
 * @date: 2022-12-09  20:38
 * @Description: 角色的service
 */
public interface RoleService {
    /**
     * 获取角色列表，分页查询
     * @param current 当前页码
     * @param pageSize  一页多少条数据
     * @return 返回ResponseResult，成功返回200，msg,data,失败返回500，msg,null
     */
    ResponseResult fetchRoleList(Integer current, Integer pageSize);

    /**
     *创建角色
     * @param roleReq 角色req
     * @return 返回ResponseResult，成功返回200，msg,data,失败返回500，msg,null
     */
    ResponseResult createRole(RoleReq roleReq);
    /**
     *更新角色
     * @param roleReq 角色req
     * @return 返回ResponseResult，成功返回200，msg,data,失败返回500，msg,null
     */
    ResponseResult updateRole(RoleReq roleReq);
    /**
     *删除角色
     * @param id 角色id
     * @return 返回ResponseResult，成功返回200，msg,data,失败返回500，msg,null
     */
    ResponseResult deleteRole(Long id);

    /**
     *
     * @param id 用户id
     * @return
     */
    ResponseResult fetchCurrentUserRole(Long id);

    /**
     * 将role的名字和id对应起来，存入redis
     */
    void saveRoleNameToId();
}
