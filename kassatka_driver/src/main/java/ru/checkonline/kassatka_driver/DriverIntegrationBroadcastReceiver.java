package ru.checkonline.kassatka_driver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static ru.checkonline.kassatka_driver.DriverConstants.ALIAS;
import static ru.checkonline.kassatka_driver.DriverConstants.INTENT_RECEIVER;

public abstract class DriverIntegrationBroadcastReceiver extends BroadcastReceiver {


    public static final String RESPONSE = "response";
    public static final String REJECTED = "rejected";
    public static final String SUCCESS = "success";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            String token = intent.getExtras().getString(ALIAS);
            if (token == null) {
                sendAnswer(context, REJECTED, null);
                return;
            }

            saveToken(token);
            sendAnswer(context, SUCCESS, token);
        }
    }

    public abstract void saveToken(@NonNull String token);

    private void sendAnswer(@Nullable Context context, @NonNull String answer, @Nullable String token) {
        if (context == null) {
            return;
        }

        Intent successIntent = createIntent(answer, token);

        context.sendBroadcast(successIntent);
    }

    @NonNull
    private Intent createIntent(@NonNull String answer, @Nullable String token) {
        Intent intent = new Intent(INTENT_RECEIVER);
        intent.putExtra(RESPONSE, answer);
        intent.putExtra(ALIAS, token);
        return intent;
    }
}
