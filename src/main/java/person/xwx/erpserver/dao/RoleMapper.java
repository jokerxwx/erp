package person.xwx.erpserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import person.xwx.erpserver.entity.Role;

import java.util.List;

/**
 * @author: xwx
 * @date: 2022-12-09  20:57
 * @Description: TODO
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     *
     * @param userid 用户id
     * @return
     */
    List<String> selectPermissionByUserId(Long userid);
}
