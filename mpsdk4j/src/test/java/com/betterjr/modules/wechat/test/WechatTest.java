package com.betterjr.modules.wechat.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.betterjr.common.utils.Cryptos;
import com.betterjr.modules.wechat.data.EventType;
import com.betterjr.modules.wechat.data.MPAccount;
import com.betterjr.modules.wechat.data.api.Follower;
import com.betterjr.modules.wechat.data.api.Follower2;
import com.betterjr.modules.wechat.data.api.Menu;
import com.betterjr.modules.wechat.util.WechatAPIImpl;
 
 

public class WechatTest {

    //private static final String redirectUrl = "https://static.qiejf.com/better/wechat/index.html#";
    private static final String redirectUrl = "http://dev5.qiejf.cn/better/Wechat/wxRequest/oauth2";
    private static final String redirectUrlA = "http://dev5.qiejf.cn/better/wechatOauth2";
    //private static final String redirectUrlA = "https://static.qiejf.com/better/wechat/index.html#";
    private static String henryOpenId ="oqJfawF5bPy6u3pmAicGrRx-4aVA";
    private static SecureRandom srd = null; 
    
    protected static MPAccount createMPA() {
        // 修改为实际的公众号信息,可以在开发者栏目中查看
        MPAccount mpAct = new MPAccount();
        mpAct.setAppId("wx35703406a35ffc1a");
        mpAct.setAppSecret("cc2544763c492d8a0ff23d72a4c56e1b");
        mpAct.setToken("ccb8e5ed6c5e4e80ab5cd2d008f09b5a");
        mpAct.setMpId("gh_50c8c1002a7e");
        mpAct.setAESKey("c0X0w3KIonikEyW57M9IbM8HVanOtFTaH5RPTuVR81O");
        return mpAct;

    }

    private static String buildReqUrl(MPAccount anMpa, String anKey, boolean anUserA) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        try {
            sb.append(anMpa.getAppId()).append("&redirect_uri=");
            if (anUserA){
                sb.append(URLEncoder.encode(redirectUrlA, "UTF-8"));
            }
            else{
                sb.append(URLEncoder.encode(redirectUrl, "UTF-8"));
            }
        }
        catch (UnsupportedEncodingException e) { 
            
        }
        sb.append("&response_type=code&scope=snsapi_base&state=").append(anKey).append("#wechat_redirect");
        //sb.setLength(0);
        //sb.append(redirectUrlA);
        return sb.toString();
    }

    public static void createMenu() {
        final MPAccount mpa = createMPA();

        final Menu me = new Menu("我的账户");
        final Menu subMe1 = new Menu("开户", EventType.VIEW.name(), buildReqUrl(mpa, "10,1", true));
        final Menu subMe2 = new Menu("开通保理业务", EventType.VIEW.name(), buildReqUrl(mpa, "10,9", true));
        final Menu subMe3 = new Menu("客户关系管理", EventType.VIEW.name(), buildReqUrl(mpa, "10,10", true));
        me.setSubButtons(Arrays.asList(subMe1, subMe2, subMe3));

        final Menu scf = new Menu("供应链金融");
        final Menu scfMe1 = new Menu("票据池", EventType.VIEW.name(), buildReqUrl(mpa, "10,3", true));
        final Menu scfMe2 = new Menu("保理产品", EventType.VIEW.name(), buildReqUrl(mpa, "10,4", true));
        final Menu scfMe3 = new Menu("融资管理", EventType.VIEW.name(), buildReqUrl(mpa, "10,5", true));
        final Menu scfMe4 = new Menu("询价/报价", EventType.VIEW.name(), buildReqUrl(mpa, "10,6", true));
        scf.setSubButtons(Arrays.asList(scfMe1, scfMe2, scfMe3));

        final Menu qiejf = new Menu("企e金服");
        qiejf.setType(EventType.VIEW.name());
        qiejf.setUrl("http://www.qiejf.com");

        final Menu[] customerMenus = new Menu[] { me, scf, qiejf };
        final WechatAPIImpl wechatApi = WechatAPIImpl.create(mpa);
        wechatApi.createMenu(customerMenus);
    }

    public static void createMenuXXX() {
        MPAccount mpa = createMPA();

        Menu me = new Menu("我的账户");
        Menu subMe1 = new Menu("注册", EventType.VIEW.name(), buildReqUrl(mpa, "/register", false));
        me.setSubButtons(Arrays.asList(subMe1));

        Menu scf = new Menu("供应链金融");
        Menu scfMe1 = new Menu("票据池", EventType.VIEW.name(), buildReqUrl(mpa, "/bill", true));
        Menu scfMe2 = new Menu("保理产品", EventType.VIEW.name(), buildReqUrl(mpa, "/factoring/product", true));
        Menu scfMe3 = new Menu("融资管理", EventType.VIEW.name(), buildReqUrl(mpa, "/finance", true));
        Menu scfMe4 = new Menu("询价/报价", EventType.VIEW.name(), buildReqUrl(mpa, "/inquiry", true));
        scf.setSubButtons(Arrays.asList(scfMe1, scfMe2, scfMe3, scfMe4));

        Menu qiejf = new Menu("企e金服");
        qiejf.setType(EventType.VIEW.name());
        qiejf.setUrl("http://www.qiejf.com");

        Menu[] customerMenus = new Menu[] { me, scf, qiejf };
        WechatAPIImpl wechatApi = WechatAPIImpl.create(mpa);
        wechatApi.createMenu(customerMenus);
    }
    
    public static void createMenuZZ() {
        final MPAccount mpa = createMPA();

        final Menu financing = new Menu("融资", EventType.VIEW.name(), buildReqUrl(mpa, "10,1", true));//

        final Menu investment = new Menu("投资", EventType.VIEW.name(), buildReqUrl(mpa, "10,2", true));//

        final Menu service = new Menu("客户服务");

        final Menu subMe1 = new Menu("个人中心", EventType.VIEW.name(), buildReqUrl(mpa, "10,3", true));//
        final Menu subMe2 = new Menu("联系我们", EventType.CLICK.name(), "联系我们");//
        service.setSubButtons(Arrays.asList(subMe1, subMe2));

        final Menu[] customerMenus = new Menu[] { financing, investment, service };
        final WechatAPIImpl wechatApi = WechatAPIImpl.create(mpa);
        wechatApi.createMenu(customerMenus);
    }

    public static void delMenu(){
        MPAccount mpa = createMPA();
        WechatAPIImpl wechatApi = WechatAPIImpl.create(mpa);
        wechatApi.delMenu();
    }
    
    public static void findUsers(){
        MPAccount mpa = createMPA();
        WechatAPIImpl wechatApi = WechatAPIImpl.create(mpa);
        Follower2 user = new Follower2(henryOpenId, "zh_CN");
       for(Follower ff : wechatApi.getFollowers( Arrays.asList(new Follower2[]{user}))){
           System.out.println(ff);
       };
       
    }
    
    private static int ramdInt(){
       return Math.abs(srd.nextInt()); 
    }
    
    
    public static void qrcodeCreateTicket(){
        MPAccount mpa = createMPA();
        WechatAPIImpl wechatApi = WechatAPIImpl.create(mpa);
        wechatApi.createQR(ramdInt(), 1800);
    }
      

    public static void findMenu(){
        MPAccount mpa = createMPA();
        WechatAPIImpl wechatApi = WechatAPIImpl.create(mpa);
        for(Menu ff : wechatApi.getMenu()){
           System.out.println(ff);
       };
       
    }
    public static void main(String[] args) throws Exception {
//        srd = SecureRandom.getInstanceStrong();
         //sendMessage();
        //qrcodeCreateTicket();
//        String tmpStr ="gQFf8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3Jqc2JzUjdsS0YxUi1PbFFweFZzAAIECObDVwMECAcAAA==";
//        System.out.println( URLEncoder.encode(tmpStr, "UTF-8") );
        //queryMsgTemp();
//       delMenu();
//        createMenu();
//        findMenu();
        //findUsers(); 
//        System.out.println(Integer.MAX_VALUE);gh_76558e8b69eewxa79cca7dac8c27a9
        createMenuZZ();
        System.out.println("================================================");
        System.out.println(Cryptos.aesEncrypt("{\"AppId\":\"wx35703406a35ffc1a\",\"AppSecret\":\"cc2544763c492d8a0ff23d72a4c56e1b\",\"Token\":\"ccb8e5ed6c5e4e80ab5cd2d008f09b5a\",\"AESKey\":\"c0X0w3KIonikEyW57M9IbM8HVanOtFTaH5RPTuVR81O\",\"MpId\":\"gh_50c8c1002a7e\",\"wechatUrl\":\"http://dev5.qiejf.cn/better/\"}"));
    }
}
