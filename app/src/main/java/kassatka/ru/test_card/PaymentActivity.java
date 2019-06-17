package kassatka.ru.test_card;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import kassatka.ru.test_card.helper.SettingHelper;
import ru.checkonline.kassatka_driver.model.RejectedResponse;
import ru.checkonline.kassatka_driver.model.Response;
import ru.checkonline.kassatka_driver.model.Route;
import ru.checkonline.kassatka_driver.model.SuccessResponse;

/**
 * Активити в котором реализуется логика оплаты и возврата ответа
 * Это активити будет вызываться с помощью стандартных механизмов startActivityForResult
 */
public class PaymentActivity extends Activity {

    Route route;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent() != null) {
            if (getIntent().getExtras() != null) {
                // получение данных
                route = Route.newInstance(getIntent().getExtras());
            } else {
                finish();
            }
        }

        /*
         *
         * Ваша логика
         *
         */

        // Пример реализации ответов

        findViewById(R.id.success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // формирование ответа

                // пример получения токена
                String token = SettingHelper.getIntegrationToken(PaymentActivity.this);

                if (token == null) {
                    finish();
                }

                Response response = new SuccessResponse
                        .Builder(PaymentActivity.this, token)
                        .setCount(route.getCount()) // количество оплаченных билетов
                        .create();

                response.finish(); // отправка данных

            }
        });

        findViewById(R.id.rejected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // формирование ответа

                // пример получения токена
                String token = SettingHelper.getIntegrationToken(PaymentActivity.this);

                if (token == null) {
                    finish();
                }

                Response response = new RejectedResponse
                        .Builder(PaymentActivity.this, token)
                        // пример передачи ошибки(опционально)
                        .setError("На счете недостаточно средств")
                        .create();

                response.finish(); // отправка данных
            }
        });
    }
}
