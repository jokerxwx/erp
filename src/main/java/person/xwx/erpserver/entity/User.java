package person.xwx.erpserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import person.xwx.erpserver.model.req.UserReq;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xwx
 * @date: 2022-11-24  18:23
 * @description: 用户表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
@JsonIgnoreProperties({"enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
public class User implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id")
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
    private boolean status;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 更新时间
     */
    private Date update_time;


//    public void user(UserReq userReq) {
//        this.id = userReq.getId();
//        this.avatar = userReq.getAvatar();
//        this.email = userReq.getEmail();
//        this.mobile = userReq.getMobile();
//        this.name = userReq.getName();
//        this.username = userReq.getUsername();
//
//    }
}
