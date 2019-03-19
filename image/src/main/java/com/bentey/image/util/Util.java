package com.bentey.image.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;

/**
 * @author : bentey
 * @date : 2019/3/19
 */
public class Util {

    public static boolean checkContextNull(Context context) {
        if (context != null && context instanceof Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (((Activity) context).isDestroyed()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkFragmentNull(Fragment fragment) {
        if (fragment != null && (fragment.getActivity() == null || fragment.isDetached())) {
            return false;
        }
        return true;
    }
}
