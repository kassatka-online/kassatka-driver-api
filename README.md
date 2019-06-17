# kassatka-driver-api
API водительского режима

[![](https://jitpack.io/v/kassatka-online/kassatka-driver-api.svg)](https://jitpack.io/#kassatka-online/kassatka-driver-api)

## Добавление библиотеки к проект

```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

в модуле `build.gradle` добавьте зависимость:

```
implementation 'com.github.kassatka-online:kassatka-driver-api:1.1.1'
```

## Quick start

Создайте BroadcastReceiver

```
public class MyBroadcastReceiver extends DriverIntegrationBroadcastReceiver {

    @Override
    public void saveToken(@NonNull String token) {
        // Необходимо сохранить полученый токен
        // Далее его необходимо передавать вместе с данными
    }
}

```

Зарегистрируйте его в AndroidManifest

```
 <receiver android:name="ru.kassatka.test_card.MyBroadcastReceiver">
    <intent-filter>
        <action android:name="ru.kassatka.driver.integration" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</receiver>
```

Для получения и обмена данными создайте Activity и зарегистрируйте его в AndroidManifest

```
<activity android:name="ru.kassatka.test_card.Example
    <intent-filter>
        <action android:name="ru.kassatka.driver.payment" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
```

Это Acitivity будет вызываться для оплаты транспортной картой

Для успешной оплаты небоходимо вызвать 
``` 
   Response response = new SuccessResponse
                        .Builder(PaymentActivity.this, token)
                        .setCount(route.getCount()) // количество оплаченных билетов
                        .create();

                response.finish(); // отправка данных
```
Для отклонённой оплаты необходимо вызвать 

```
 Response response = new RejectedResponse
                        .Builder(PaymentActivity.this, token)
                        .create();

                response.finish(); // отправка данных
```


Пример Activity

```
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

                Response response = new SuccessResponse
                        .Builder(PaymentActivity.this, "ваш токен")
                        .setCount(route.getCount()) // количество оплаченных билетов
                        .create();

                response.finish(); // отправка данных

            }
        });

        findViewById(R.id.rejected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // формирование ответа

                Response response = new RejectedResponse
                        .Builder(PaymentActivity.this, "ваш токен")
                        .setError("На счете недостаточно средств")
                        .create();

                response.finish(); // отправка данных
            }
        });
    }
}


```




