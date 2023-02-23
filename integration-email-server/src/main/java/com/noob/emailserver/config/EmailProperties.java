package com.noob.emailserver.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "taco-cloud.email")
@Component
public class EmailProperties {

    private String username;

    private String password;

    private String host;

    private String mailbox;

    private long pollRate = 30000;

    public String getImapUrl() {
        return String.format("imaps://%s:%s@%s/%s",
                this.username, this.password, this.host, this.mailbox);
    }
}
