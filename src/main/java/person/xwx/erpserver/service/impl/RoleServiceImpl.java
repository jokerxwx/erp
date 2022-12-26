package person.xwx.erpserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.xwx.erpserver.dao.RoleMapper;
import person.xwx.erpserver.entity.Role;
import person.xwx.erpserver.entity.User;
import person.xwx.erpserver.model.req.RoleReq;
import person.xwx.erpserver.model.resp.ResponseResult;
import person.xwx.erpserver.service.RoleService;
import person.xwx.erpserver.utils.RedisUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: xwx
 * @date: 2022-12-09  20:40
 * @Description: TODO
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取角色列表，分页查询
     *
     * @param current  当前页码
     * @param pageSize 一页多少条数据
     * @return
     */
    @Override
    public ResponseResult fetchRoleList(Integer current, Integer pageSize) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("id");

        // 创建分页对象（current表示页码；pageSize表示每页大小）
        Page<Role> page = new Page<>(current, pageSize);
        Page<Role> result = roleMapper.selectPage(page, wrapper);
        return new ResponseResult(200, "查询成功", result);
    }

    /**
     * 创建角色
     *
     * @param roleReq 角色req
     * @return 返回ResponseResult，成功返回200，msg,data,失败返回500，msg,null
     */
    @Override
    public ResponseResult createRole(RoleReq roleReq) {
        //不明文存储密码，使用spring security自带的编码，转换
        Role role = new Role();
        role.setName(roleReq.getName());
        int result = roleMapper.insert(role);
        if ( result == 0 ) {
            return new ResponseResult<>(500, "创建角色失败", null);
        }
        return new ResponseResult<>(200, "创建角色成功", null);
    }

    /**
     * 更新角色
     *
     * @param roleReq 角色req
     * @return 返回ResponseResult，成功返回200，msg,data,失败返回500，msg,null
     */
    @Override
    public ResponseResult updateRole(RoleReq roleReq) {
        Role role = new Role();
        role.setName(roleReq.getName());
        role.setStatus(roleReq.isStatus());
        role.setId(roleReq.getId());
        int result = roleMapper.updateById(role);
        if ( result == 0 ) {
            return new ResponseResult<>(500, "更新角色失败", null);
        }
        return new ResponseResult<>(200, "更新角色成功", null);
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 返回ResponseResult，成功返回200，msg,data,失败返回500，msg,null
     */
    @Override
    public ResponseResult deleteRole(Long id) {
        int result = roleMapper.deleteById(id);

        if ( result == 0 ) {
            return new ResponseResult<>(500, "删除角色失败", null);
        }
        return new ResponseResult<>(200, "删除角色成功", null);
    }

    /**
     * @param id 用户id
     * @return
     */
    @Override
    public ResponseResult fetchCurrentUserRole(Long id) {
        List<String> arrayList = roleMapper.selectPermissionByUserId(id);
        return new ResponseResult<>(200, "查询用户角色成功", arrayList);
    }

    /**
     * 将role的名字和id对应起来，存入redis
     */
    @Override
    public void saveRoleNameToId() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        List<Role> list = roleMapper.selectList(queryWrapper);
        for ( Role role : list ) {
            redisUtils.set(role.getName(),role.getId());
        }
    }
}
