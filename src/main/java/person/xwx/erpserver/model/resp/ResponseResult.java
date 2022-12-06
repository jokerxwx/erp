package person.xwx.erpserver.model.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: xwx
 * @date: 2022-12-06  16:27
 * @Description: TODO
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@ApiModel("Response")
public class ResponseResult<T> {
    /**
     * 状态码
     */
    @ApiModelProperty(name = "code", value = "业务状态码")
    private Integer code;
    /**
     * 描述信息
     */
    @ApiModelProperty(name = "msg", value = "描述信息")
    private String msg;
    /**
     * 返回的数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

//    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
//    private int total;


}
