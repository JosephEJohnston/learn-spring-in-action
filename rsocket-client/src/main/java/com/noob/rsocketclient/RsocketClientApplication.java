package com.noob.rsocketclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RsocketClientApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RsocketClientApplication.class, args);

        // Sleep for 1 second to keep the main thread alive long enough to send message.
        // In a complete example, this isn't necessary because the Tomcat server or some
        // other component will likely keep the app alive. But this example is too simple
        // and the main thread could end too quickly, before the message can be sent.
        Thread.sleep(5000);
    }

}
