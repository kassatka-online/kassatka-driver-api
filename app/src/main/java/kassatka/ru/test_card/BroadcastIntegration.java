package kassatka.ru.test_card;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import kassatka.ru.test_card.helper.SettingHelper;
import ru.checkonline.kassatka_driver.DriverIntegrationBroadcastReceiver;

public class BroadcastIntegration extends DriverIntegrationBroadcastReceiver {

    /**
     * При сопряжении модуля будет вызываться данный метод и в нём необходимо сохранить токен
     *
     * Способ хранения на ваш выбор
     *
     * @param context передаваемый из метода {@link #onReceive(Context, Intent)}
     * @param token   токен полученный в ходе сопряжения
     */
    @Override
    public void saveToken(Context context, @NonNull String token) {
        SettingHelper.setIntegrationToken(context, token);
    }
}
