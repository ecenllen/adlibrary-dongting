package com.yingyongduoduo.ad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yingyongduoduo.ad.config.AppConfig;
import com.yingyongduoduo.ad.interfaceimpl.KPAdListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

@SuppressLint("NewApi")
public class AdLibraryJarUtil {

    private DexClassLoader loader;
    private static AdLibraryJarUtil util;

    // 全局缓存变量，只初始化一次
    private Class<?> adControlClass;
    private Object adControlInstance;
    private Method myKpMethod;
    private Method myBannerMethod;
    private Method myTPMethod;

    private AdLibraryJarUtil(Context context) {
        try {
            File dexfile = new File(AppConfig.adPath);
            if (!dexfile.exists()) {
                copyLocal(context, AppConfig.adPath);
            }
            String libDir = context.getCacheDir() + File.separator;
            ClassLoader parent = context.getClassLoader();
            loader = new DexClassLoader(AppConfig.adPath, libDir, libDir, parent);
            System.out.println("errinit:" + AppConfig.adPath);
        } catch (Exception e) {
            System.out.println("errinit:" + e.toString());
            e.printStackTrace();
        }
    }

    public static AdLibraryJarUtil getInstance(Context context) {
        if (util == null) {
            util = new AdLibraryJarUtil(context);
        }
        return util;
    }

    public static void ReInit() {
        if (util != null) {
            util = null;
        }
    }

    public void myKp(Activity context, RelativeLayout adsParent, View skipView, final KPAdListener kpAdListener) throws Exception {
        if (adControlClass == null) {
            adControlClass = loader.loadClass("com.yingyongduoduo.ad.ADControl");
            adControlInstance = adControlClass.newInstance();
        }
        if (myKpMethod == null) {
            myKpMethod = adControlClass.getMethod("myKp", Activity.class, RelativeLayout.class, View.class, KPAdListener.class);// type,id
            // 关闭权限校验，提速
            myKpMethod.setAccessible(true);
        }
        myKpMethod.invoke(adControlInstance, context, adsParent, skipView, kpAdListener);
    }

    public void myBannerAd(LinearLayout lyt, Activity context) throws Exception {
        if (adControlClass == null) {
            adControlClass = loader.loadClass("com.yingyongduoduo.ad.ADControl");
            adControlInstance = adControlClass.newInstance();
        }

        if (myBannerMethod == null) {
            myBannerMethod = adControlClass.getMethod("myBannerAd", LinearLayout.class, Activity.class);// type,id
            // 关闭权限校验，提速
            myBannerMethod.setAccessible(true);
        }

        myBannerMethod.invoke(adControlInstance, lyt, context);
    }

    public boolean myTPAD(Activity context) throws Exception {
        if (adControlClass == null) {
            adControlClass = loader.loadClass("com.yingyongduoduo.ad.ADControl");
            adControlInstance = adControlClass.newInstance();
        }
        if (myTPMethod == null) {
            myTPMethod = adControlClass.getMethod("myTPAD", Activity.class);// type,id
            // 关闭权限校验，提速
            myTPMethod.setAccessible(true);
        }

        return (Boolean) myTPMethod.invoke(adControlInstance, context);
    }

    private static void copyLocal(Context context, String dexpath) {
        InputStream is = null;
        OutputStream outputStream = null;
        try {
            is = context.getAssets().open("libad.so");
            outputStream = new FileOutputStream(dexpath);
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                // buffer字节
                outputStream.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
