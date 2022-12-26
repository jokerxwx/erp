package person.xwx.erpserver.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import person.xwx.erpserver.entity.Menu;

import java.util.Date;

/**
 * @author: xwx
 * @date: 2022-12-10  14:45
 * @Description: TODO
 */
@Data
public class RoleVO {
    /**
     * 主键ID
     */
    @TableId
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
     * 角色能访问的菜单
     */
    private Menu[] menus;
}
