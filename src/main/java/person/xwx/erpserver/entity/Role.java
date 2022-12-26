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
 * @date: 2022-12-09  18:48
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_role")
@JsonIgnoreProperties({"enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
public class Role implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 是否启用（1已启用 0未启用）
     */
    private boolean status;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 更新时间
     */
    private Date update_time;
}
