package com.iam844.finddragonapps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class ApkInfoExtractor {

    Context context1;

    public ApkInfoExtractor(Context context2){
        context1 = context2;
    }

    public List<String> getAllInstalledApkInfo(){

        List<String> apkPackageName = new ArrayList<>();
        ListOfDragonApps listOfDragonApps = new ListOfDragonApps();

        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED );

        List<ResolveInfo> resolveInfoList = context1.getPackageManager().queryIntentActivities(intent,0);
        for(ResolveInfo resolveInfo : resolveInfoList){
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if(!isSystemPackage(resolveInfo)){
                apkPackageName.add(activityInfo.applicationInfo.packageName);
            }
        }
        apkPackageName.retainAll(listOfDragonApps.getDragonApps());
        return apkPackageName;
    }

    public boolean isSystemPackage(ResolveInfo resolveInfo){
        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    public Drawable getAppIconByPackageName(String ApkTempPackageName){

        Drawable drawable;

        try{
            drawable = context1.getPackageManager().getApplicationIcon(ApkTempPackageName);
        }
        catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            drawable = ContextCompat.getDrawable(context1, R.mipmap.ic_launcher);
        }
        return drawable;
    }

    public String getAppName(String ApkPackageName){

        String name = "";
        ApplicationInfo applicationInfo;

        PackageManager packageManager = context1.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(ApkPackageName, 0);
            if(applicationInfo!=null){
                name = (String)packageManager.getApplicationLabel(applicationInfo);
            }
        }catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

}
