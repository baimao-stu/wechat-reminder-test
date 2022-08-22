package com.baimao.wechatReminder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author admin
 * @create 2022/8/7 13:41
 */

@Getter
@Setter
@ToString()
public class WechatSendBody {

    @JsonIgnore
    private static final String DEFAULT_TOP_COLOR = "#FF0000";
    private String touser;

    private String url;

    private String template_id;

    private String topcolor;

    private Map data;


    public WechatSendBody(String touser, String template_id, Map data) {
        this.touser = touser;
        this.topcolor = DEFAULT_TOP_COLOR;
        this.template_id = template_id;
        this.data = data;
    }

    public WechatSendBody(String touser, String url, String template_id, String topcolor, Map data) {
        this.touser = touser;
        this.url = url;
        this.template_id = template_id;
        this.topcolor = topcolor;
        this.data = data;
    }
}
