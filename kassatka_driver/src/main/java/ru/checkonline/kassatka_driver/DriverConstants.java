package ru.checkonline.kassatka_driver;

public interface DriverConstants {
    int RESULT_DRIVER_SUCCESS = 117;
    int RESULT_DRIVER_REJECTED = 121;

    String ALIAS = "ru.kassatka.integration.driver_token";
    String INTENT_RECEIVER = "ru.kassatka.driver.integration.receiver";

    String EXTRA_NAME = "integration_name";
    String EXTRA_COUNT = "integration_count";
    String EXTRA_PRICE = "integration_price";
    String EXTRA_TAX_MODE = "integration_tax_mode";

    String EXTRA_ERROR = "integration_error_message";


}
