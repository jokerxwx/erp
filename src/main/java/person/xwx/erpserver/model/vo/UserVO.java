package person.xwx.erpserver.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import person.xwx.erpserver.entity.Role;

import java.util.List;


/**
 * @author: xwx
 * @date: 2022-12-08  15:32
 * @Description: TODO
 */
@Data
public class UserVO {
    /**
     * 主键ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 名字
     */
    private String name;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 账号状态（1已启用 0未启用）
     */
    private Boolean status;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户角色
     */
    private List<String> roles;

}
