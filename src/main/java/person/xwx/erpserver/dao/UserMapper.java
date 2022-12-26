package person.xwx.erpserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import person.xwx.erpserver.entity.User;
import person.xwx.erpserver.model.req.UserReq;
import person.xwx.erpserver.model.vo.UserVO;

import java.util.List;

/**
 * @author: xwx
 * @date: 2022-12-05  18:33
 * @Description: TODO
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     *
     * @param userReq
     * @return
     */
    Integer updateUser(UserReq userReq);
}
