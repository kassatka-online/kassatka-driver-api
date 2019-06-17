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
implementation 'com.github.kassatka-online:kassatka-driver-api:1.0.0'
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
setResult(DriverConstants.RESULT_DRIVER_SUCCESS);
finish();
```
Для отклонённой оплаты необходимо вызвать 

```
setResult(DriverConstants.RESULT_DRIVER_REJECTED);
finish();
```


Пример Activity

```
public class ExampleActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Получение данных

        if (getIntent() != null) {
            if (getIntent().getExtras() != null) {
                Bundle bundle = getIntent().getExtras();
                bundle.getString(DriverConstants.EXTRA_NAME);
                bundle.getString(DriverConstants.EXTRA_COUNT);
                bundle.getString(DriverConstants.EXTRA_PRICE);
            }
        }


        //Например
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // необходимая логика для успешного результата
                  
                // формирование ответа
                Intent intent = new Intent();
                intent.putExtra(DriverConstants.ALIAS, "токен который сохранили в BroadcastReceiver");
                
                setResult(DriverConstants.RESULT_DRIVER_SUCCESS);
                finish();
            }
        });
        
        //Например
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // необходимая логика для отклонённого результата
                
                // формирование ответа
                Intent intent = new Intent();
                intent.putExtra(DriverConstants.ALIAS, "токен который сохранили в BroadcastReceiver");
                
                setResult(DriverConstants.RESULT_DRIVER_REJECTED);
                finish();
            }
        });
    }
}

```




