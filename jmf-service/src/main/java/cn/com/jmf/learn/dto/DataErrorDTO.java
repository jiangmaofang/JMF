package cn.com.jmf.learn.dto;

import cn.com.jmf.learn.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据异常
 * @author zhouliangfei
 * @version v1.0.0
 * @date 2019-05-30 09:54
 */
public abstract class DataErrorDTO {

    @Getter
    @Setter
    private int index; //在excel里面的数据行

    @Getter
    @Setter
    private boolean errored;
    public List<String> message;

    public String getMessage() {
        return CollectionUtils.isEmpty(message) ? "" : StringUtils.join(message, ";");
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public void setMessage(String message) {
        if (this.message == null)  {
            this.message = new ArrayList<>();
        }
        this.message.add(message);
    }
}
