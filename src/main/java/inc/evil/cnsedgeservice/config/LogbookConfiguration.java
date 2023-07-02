package inc.evil.cnsedgeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;

import static org.zalando.logbook.core.Conditions.*;
import static org.zalando.logbook.core.HeaderFilters.authorization;
import static org.zalando.logbook.core.QueryFilters.replaceQuery;

@Configuration
public class LogbookConfiguration {
    @Bean
    public Logbook logbook() {
        return Logbook.builder()
            .condition(
                exclude(
                    requestTo("/health"),
                    requestTo("/admin/**"),
                    contentType("application/octet-stream")
                )
            )
            .headerFilter(authorization())
            .queryFilter(replaceQuery("password", "<secret>"))
            .build();
    }
}
