package ru.checkonline.kassatka_driver.model;

import android.app.Activity;
import android.content.Intent;

import ru.checkonline.kassatka_driver.DriverConstants;


/**
 * Класс-обёртка для отправки данных при отклонённой оплате
 */
public class RejectedResponse implements Response {

    private Activity activity;

    private Intent intent;

    private RejectedResponse(Activity activity) {
        this.activity = activity;
        intent = new Intent();
    }

    private void setToken(String token) {
        intent.putExtra(
                DriverConstants.ALIAS,
                token
        );
    }

    private void setError(String text) {
        intent.putExtra(DriverConstants.EXTRA_ERROR, text);
    }

    @Override
    public void finish() {
        if (intent == null) {
            throw new NullPointerException("Intent should't be null");
        }

        activity.setResult(DriverConstants.RESULT_DRIVER_REJECTED, intent);
        activity.finish();
    }

    public static class Builder {

        private RejectedResponse response;

        public Builder(Activity activity, String token) {
            response = new RejectedResponse(activity);
            setToken(token);
        }

        /**
         * Метод в котором передаётся сообщение пользователю
         *
         * @param text Текст, который вы хотите показать пользователю
         */
        public Builder setError(String text) {
            response.setError(text);
            return this;
        }

        private void setToken(String token) {
            response.setToken(token);
        }

        public RejectedResponse create() {
            return response;
        }

    }
}