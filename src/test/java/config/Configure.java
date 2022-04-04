package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

@Sources("classpath:config/Configure.properties")
public interface Configure extends Config {
    @Key("https.url")
    String url();

    @Key("https.curl")
    String curl();

    @Key("device.browser")
    String browser();

    @Key("device.name")
    String device();

    @Key("device.user")
    String user();

    @Key("device.key")
    String key();
}
