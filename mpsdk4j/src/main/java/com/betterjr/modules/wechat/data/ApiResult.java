package com.betterjr.modules.wechat.data;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.betterjr.common.mapper.JsonMapper;

/**
 * 封装微信api返回结果, 输出实体类
 *
 * @author zhoucy
 */
@SuppressWarnings("unchecked")
public class ApiResult {

    private static final Logger log = LoggerFactory.getLogger(ApiResult.class);

    private final Map<String, Object> content;
    private final String json;
    private final Integer errCode;
    private final String errMsg;
    private final String errCNMsg;

    public ApiResult(final String json) {
        this.json = json;
        this.content = JsonMapper.buildNormalMapper().fromJson(json, Map.class);
        this.errCode = (Integer) this.content.get("errcode");
        this.errMsg = (String) this.content.get("errmsg");
        this.errCNMsg = this.errCode == null ? "请求成功." : this.errMsg;

        if (log.isInfoEnabled()) {
            log.debug("Wechat api result: " + json);
            log.debug(" error:"+this.getErrCNMsg());
        }
    }

    public static ApiResult create(final String json) {
        return new ApiResult(json);
    }

    public Object get(final String key) {
        return content.get(key);
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public String getJson() {
        return json;
    }

    public Integer getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg == null ? "Unknow Error!" : this.errMsg;
    }

    public String getErrCNMsg() {
        return this.errCNMsg == null ? "未知错误!" : this.errCNMsg;
    }

    public boolean isSuccess() {
        return (this.errCode == null || this.errCode.intValue() == 0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("errCode=").append(errCode);
        sb.append(", json=").append(json);
        sb.append(", content=").append(content);
        sb.append("]");

        return sb.toString();
    }
}
