package in.MomenTarek.musifyapi.config;

import com.mongodb.ConnectionString;
import org.springframework.boot.mongodb.autoconfigure.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClientSettingsBuilderCustomizer mongoClientSettingsBuilderCustomizer() {
        String uri = System.getenv("MONGODB_URI");
        return builder -> {
            if (uri != null && !uri.isBlank()) {
                builder.applyConnectionString(new ConnectionString(uri));
            }
        };
    }
}