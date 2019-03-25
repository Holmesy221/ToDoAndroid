package holmes.holmesy.todoandroid.Util;

import android.os.Build;

public class BioMetricUtil {

    public static boolean isBiometricPromptEnabled() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P);
    }




}
