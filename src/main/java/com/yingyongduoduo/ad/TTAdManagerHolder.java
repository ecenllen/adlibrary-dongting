package com.yingyongduoduo.ad;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTCustomController;
import com.bytedance.sdk.openadsdk.mediation.init.IMediationPrivacyConfig;
import com.bytedance.sdk.openadsdk.mediation.init.MediationPrivacyConfig;
import com.yingyongduoduo.ad.config.AppConfig;
import com.yingyongduoduo.ad.utils.PublicUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 可以用一个单例来保存TTAdManager实例，在需要初始化sdk的时候调用
 */
public class TTAdManagerHolder {

    private static final String TAG = "TTAdManagerHolder";

    private static boolean sInit;
    private static String appId;


    public static TTAdManager get() {
        return TTAdSdk.getAdManager();
    }

    public static void init(final Context context, String appId) {
        TTAdManagerHolder.appId = appId;
        doInit(context);
    }

    //step1:接入网盟广告sdk的初始化操作，详情见接入文档和穿山甲平台说明
    private static void doInit(Context context) {
        if (!sInit) {
            //TTAdSdk.init(context, buildConfig(context));
            sInit = TTAdSdk.init(context, buildConfig(context));
            TTAdSdk.start(new TTAdSdk.Callback() {
                @Override
                public void success() {
                    Log.i(TAG, "success: "+ TTAdSdk.isInitSuccess());
                }

                @Override
                public void fail(int i, String s) {
                    Log.i(TAG, "fail: "+  s);
                }
            });

//            TTAdSdk.isInitSuccess();
//            TTAdSdk.init(context, buildConfig(context), new TTAdSdk.InitCallback() {
//                @Override
//                public void success() {
//
//                    Log.i(TAG, "success: "+ TTAdSdk.isInitSuccess());
//                }
//
//                @Override
//                public void fail(int code, String msg) {
//                    Log.i(TAG, "fail:  code = " + code + " msg = " + msg);
//                }
//            });
        }
    }

    private static TTAdConfig buildConfig(Context context) {
        return new TTAdConfig.Builder()
                .customController(getTTCustomController(context)) // 隐私合规设置
                .appId(appId)
//                .useTextureView(false) //使用TextureView控件播放视频,默认为SurfaceView,当有SurfaceView冲突的场景，可以使用TextureView
                .allowShowNotify(true) //是否允许sdk展示通知栏提示
//                .debug(false) //测试阶段打开，可以通过日志排查问题，上线时去除该调用
                .directDownloadNetworkType() //允许直接下载的网络状态集合, 不设置代表二次确认
                .supportMultiProcess(false)//是否支持多进程
                .useMediation(true)//④兜底需要设置useMediation(true)，否则无效。
//                .needClearTaskReset()
                .build();
    }

    //函数返回值表示隐私开关开启状态，未重写函数使用默认值
    private static TTCustomController getTTCustomController(final Context context){
        return new TTCustomController() {

            //【新增】新支持开发者控制弹出app下载完成安装/app安装完成打开通知;
            @Override
            public boolean isCanUseMessage() {
                return super.isCanUseMessage();
            }

            @Override
            public boolean isCanUseWifiState() {
                return false;
            }

            @Override
            public boolean isCanUsePhoneState() {
                return AppConfig.isCanUsePhoneState();
            }

            @Override
            public boolean isCanUseLocation() {
                return AppConfig.isCanUseLocation();
            }

            @Override
            public Map<String, Object> userPrivacyConfig() {
                Map<String, Object> map = new HashMap<>();
                // 控制oaid获取频率，"0"表示关闭，"1"或者其他值表示打开。
                map.put("mcod", "0");
                return map;
            }

            @Override
            public String getMacAddress() {
                return "";
            }

            @Override
            public boolean isCanUseWriteExternal() {
                return super.isCanUseWriteExternal();
            }

            @Override
            public String getDevOaid() {
                return PublicUtil.metadata(context, "UMENG_APPKEY");
            }

            @Override
            public boolean isCanUseAndroidId() {
                return false;
            }

            @Override
            public String getAndroidId() {
                return "";
            }

            @Override
            public boolean isCanUsePermissionRecordAudio() {
                return false;
            }

            @Nullable
            @Override
            public IMediationPrivacyConfig getMediationPrivacyConfig() {
                return new MediationPrivacyConfig() {
                    @Nullable
                    @Override
                    public List<String> getCustomAppList() {
                        return super.getCustomAppList();
                    }

                    @Nullable
                    @Override
                    public List<String> getCustomDevImeis() {
                        return super.getCustomDevImeis();
                    }

                    @Override
                    public boolean isCanUseOaid() {
                        return false;
                    }

                    @Override
                    public boolean isLimitPersonalAds() {
                        return super.isLimitPersonalAds();
                    }

                    @Override
                    public boolean isProgrammaticRecommend() {
                        return super.isProgrammaticRecommend();
                    }
                };
            }
        };
    }
}
