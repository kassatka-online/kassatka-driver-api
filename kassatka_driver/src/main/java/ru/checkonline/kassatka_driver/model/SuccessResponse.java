package ru.checkonline.kassatka_driver.model;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import ru.checkonline.kassatka_driver.DriverConstants;


/**
 * Класс-обёртка для отправки данных при успешной оплате
 */
public class SuccessResponse implements Response {

    private Activity activity;

    private Intent intent;

    private SuccessResponse(@NonNull Activity activity) {
        this.activity = activity;
        intent = new Intent();
    }

    private void setToken(String token) {
        intent.putExtra(
                DriverConstants.ALIAS,
                token
        );
    }

    private void setName(String name) {
        intent.putExtra(DriverConstants.EXTRA_NAME, name);
    }

    private void setPrice(String price) {
        intent.putExtra(DriverConstants.EXTRA_PRICE, price);
    }

    private void setCount(int count) {
        intent.putExtra(DriverConstants.EXTRA_COUNT, count);
    }

    @Override
    public void finish() {
        if (intent == null) {
            throw new NullPointerException("Intent should't be null");
        }

        if (intent.getExtras() == null) {
            throw new NullPointerException("Extra should't be null");
        }

        activity.setResult(DriverConstants.RESULT_DRIVER_SUCCESS, intent);
        activity.finish();
    }

    public static class Builder {

        private SuccessResponse response;

        public Builder(@NonNull Activity activity, @NonNull String token) {
            response = new SuccessResponse(activity);
            setToken(token);
        }

        private void setToken(String token) {
            response.setToken(token);
        }

        void setPrice(String price) {
            response.setPrice(price);
        }

        void setName(String name) {
            response.setName(name);
        }

        /**
         * Установка количества оплаченных билетов
         *
         * @param count Количество оплаченых билетов
         */
        public Builder setCount(int count) {
            response.setCount(count);
            return this;
        }

        public Builder setRoute(Route route) {
            setName(route.getName());
            setPrice(route.getPrice());
            setCount(route.getCount());
            return this;
        }

        public SuccessResponse create() {
            return response;
        }
    }
}
