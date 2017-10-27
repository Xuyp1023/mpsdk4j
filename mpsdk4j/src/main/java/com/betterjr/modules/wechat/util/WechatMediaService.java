package com.betterjr.modules.wechat.util;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.betterjr.common.service.BaseService; 
import com.betterjr.modules.wechat.data.message.ImageMsg;
import com.betterjr.modules.wechat.data.message.VideoMsg;
import com.betterjr.modules.wechat.data.message.VoiceMsg; 
/***
 * 推送
 *
 * @author hubl
 *
 */
@Service
public class WechatMediaService  {
 
    private static final Logger logger = LoggerFactory.getLogger(WechatMediaService.class);

    /**
     * @param anMsg
     */
    public boolean saveWechatMedia(final ImageMsg anMsg) {
        return saveWechatMedia(anMsg.getFromUserName(), anMsg.getMediaId(), "0");
    }

    /**
     * @param anMsg
     * @return
     */
    public boolean saveWechatMedia(final VoiceMsg anMsg) {
        return saveWechatMedia(anMsg.getFromUserName(), anMsg.getMediaId(), "2");
    }

    /**
     * @param anMsg
     * @return
     */
    public boolean saveWechatMedia(final VideoMsg anMsg) {
        return saveWechatMedia(anMsg.getFromUserName(), anMsg.getMediaId(), "1");
    }

    /**
     * @param anMsg
     */
    public boolean saveWechatMedia(final String anOpenId, final String anMediaId, final String anMediaType) { 

        return true;
    }

}
