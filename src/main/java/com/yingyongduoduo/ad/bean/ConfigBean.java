package com.yingyongduoduo.ad.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuminer on 2017/3/23.
 */
public class ConfigBean implements Serializable {
 //    public String videosourceVersion = "";//当前会加载那些视频渠道的视频
//    public String selfadVersion = "";
//    public String wxgzhversion = "";
//    public String goodPinglunVersion = "";
//    public String onlineVideoParseVersion = "";//在线视频解析地址的jar的version
 public UpdateBean updatemsg = new UpdateBean();
 public Map<String, String> ad_banner_idMap = new HashMap<String, String>();
 public Map<String, String> ad_kp_idMap = new HashMap<String, String>();
 public Map<String, String> ad_cp_idMap = new HashMap<String, String>();
 public Map<String, String> ad_tp_idMap = new HashMap<String, String>();
 public Map<String, String> ad_shiping_idMap = new HashMap<String, String>();
 public String cpuidorurl = "";//baidu的内容联盟的链接或者id，如果是链接，直接解析
 public String nomeinvchannel = "";
 public String nocpuadchannel = "";//没有内容联盟内容的渠道
 public String noalipaychannel = "";//没有支付宝支付的渠道
 public String noweixinpaychannel = "";//没有微信支付的渠道
 public String nofenxiang = "";
 public String nosearch = "";
 public String nohaoping = "";
 public String nomapnochannel = "";//
 public String noshipingadchannel = "";//没SP的版本
 public String noadbannerchannel = "";//没广告条的版本
 public String noadkpchannel = "";//没有开屏的版本
 public String noCanUsePhoneState = "";//
 public String noCanUseLocation = "";//
 public String noadtpchannel = "";//没有开屏的版本
 public String noadcpchannel = "";//没有插屏的版本
 public String nopaychannel = "";//不用付费的版本
 public String showselflogochannel = "";//没有LOGO遮挡的版本
 public String noshowdschannel = "";//打开应用，不显示打赏对话框版本
 public String noadfrontchannel = "";
 public String noupdatechannel = "";
 public String noselfadchannel = "";
 public String norootpaychannel = "";
 public String noaddvideochannel = "";
 public String noadVideowebchannel = "";//使用第三方去广告链接对web地址进行转化播放，这时候是给浏览器播放
 public String playonwebchannel = "";//使用网页播放的版本
 public String nogzhchannel = "";
 public String mapno = "";
 public String bannertype = "";
 public String cptype = "";
 public String kptype = "";
 public String tptype = "";
 public String shipingtype = "";
 public String navigationid = "";
 public String navigationid2 = "";
 public String indexurl = "";
 public String llqsearchurl = "";
 public String starturl = "";
 public String greythemechannel = ""; //灰色主题日期
 public String nolivechannel = ""; //没有景点直播渠道
 public String tbsKey = ""; // 腾讯X5浏览服务 key
 public String baiduCloudId = "";//baidu智能云id
 public String baiduCloudSecret = "";//baidu智能云密钥
 public String csjbannerwidth = "";//csjBannerWidth
 public String csjbannerheight = "";//csjBannerWidth


 public String nozhikouling = "";//没有吱口令的渠道
 public String isfirstfreeusechannel = "";

}
