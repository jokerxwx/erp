package person.xwx.erpserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xwx
 * @date: 2022-12-07  15:41
 * @Description: 菜单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_menu")
@JsonIgnoreProperties({"enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
public class Menu implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 图标
     */
    private String icon;
    /**
     *
     */
    private Long parentid;
    /**
     * 密码
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     *
     */
    private String name;
    /**
     * 隐藏状态（1隐藏 0不隐藏）
     */
    private boolean hide;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 更新时间
     */
    private Date update_time;
}

