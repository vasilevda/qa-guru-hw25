package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

@Sources("classpath:config/configure.properties")
public interface Configure extends Config {
    @Key("hub")
    String hub();

    @Key("remoteDriver")
    String remoteDriver();

    @Key("browser")
    String browser();

    @Key("tool")
    String tool();

    @Key("user")
    String user();

    @Key("key")
    String key();
}
