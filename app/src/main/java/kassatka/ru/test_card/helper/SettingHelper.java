package kassatka.ru.test_card.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import kassatka.ru.test_card.R;

public class SettingHelper {

    @Nullable
    public static String getIntegrationToken(@NonNull Context context) {
        return SettingUtil.getString(context, context.getString(R.string.preferences_token), null);
    }

    public static void setIntegrationToken(@NonNull Context context, String serial) {
        SettingUtil.putString(context, context.getString(R.string.preferences_token), serial);
    }
}
