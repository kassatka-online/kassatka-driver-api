package kassatka.ru.test_card.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

final class SettingUtil {

    @Nullable
    static String getString(@NonNull Context context,
                            String keyId,
                            @Nullable String defaultValue) {
        return getPreferences(context).getString(keyId, defaultValue);
    }

    static void putString(@NonNull Context context,
                          String keyId,
                          @Nullable String value) {
        getEditor(context).putString(keyId, value).apply();
    }


    @NonNull
    private static SharedPreferences getPreferences(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    private static SharedPreferences.Editor getEditor(@NonNull Context context) {
        return getPreferences(context).edit();
    }
}
