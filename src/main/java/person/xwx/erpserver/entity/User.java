package person.xwx.erpserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
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
public class User implements UserDetails, Serializable {
    /**
     * 主键ID
     */
    @TableId
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
     * 电话
     */
    private String phonenumber;
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

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String getPassword(){
        return this.password;
    }

    /**
     *
     * @return
     */
    @Override
    public String getUsername(){
        return this.username;
    }


    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
