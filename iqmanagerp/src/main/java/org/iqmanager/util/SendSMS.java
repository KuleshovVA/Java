package org.iqmanager.util;


import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.iqmanager.models.PerformerData;

import java.io.IOException;
import java.util.List;


public class SendSMS {

    /** SMS для регистрации заказчика */
    public static void sendAuthorization(String phone, String code) throws IOException {
        Request.Post("https://smsc.ru/sys/send.php")
                .bodyForm(Form.form()
                        .add("login", "iqmanager")
                        .add("psw","IQ#12&manager")
                        .add("phones", phone)
                        .add("mes","Kod podtvergdeniya: " + code)
                        .add("charset", "utf-8")
                        .build())
                .execute();
    }


}
