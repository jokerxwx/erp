package person.xwx.erpserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: xwx
 * @date: 2022-12-10  19:33
 * @Description: TODO
 */
@Data
@TableName(value = "t_user_role")
public class UserRole implements Serializable {
    /**
     * 用户id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long user_id;
    /**
     * 角色id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long role_id;
}
