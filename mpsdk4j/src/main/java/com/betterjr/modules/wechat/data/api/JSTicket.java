package com.betterjr.modules.wechat.data.api;

import java.io.Serializable;

import com.betterjr.common.utils.BetterStringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信JSSDK凭证
 *
 * @author zhoucy
 */
public class JSTicket implements Serializable {

    /**
     * 调用微信JS接口的临时票据
     */
    private String ticket;

    /**
     * 凭证有效时间,单位:秒
     */
    @JsonProperty(value = "expires_in")
    private long expiresIn;

    @JsonIgnore
    private long orginExpireSec;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(final String ticket) {
        this.ticket = ticket;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public long getOrginExpireSec() {
        return orginExpireSec;
    }

    public void setExpiresIn(final long expiresIn) {
        this.orginExpireSec = expiresIn / 2;
        this.expiresIn = System.currentTimeMillis() + (expiresIn / 2 - 60) * 1000;
    }

    public boolean isAvailable() {
        if (!BetterStringUtils.isEmpty(ticket) && this.expiresIn >= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "JSTicket [ticket=" + ticket + ", expiresIn=" + expiresIn + "]";
    }

}
