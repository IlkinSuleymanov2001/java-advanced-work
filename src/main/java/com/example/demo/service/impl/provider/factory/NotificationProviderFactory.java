package com.example.demo.service.impl.provider.factory;

import com.example.demo.model.enums.Provider;
import com.example.demo.service.impl.provider.strategy.EmailProvider;
import com.example.demo.service.impl.provider.strategy.SmsProvider;
import com.example.demo.service.impl.provider.strategy.NotificationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class NotificationProviderFactory {

    private final SmsProvider smsProvider;
    private final EmailProvider emailProvider;

    public NotificationProvider getNotificationProvider(Provider provider) {
        return switch (provider) {
            case SMS -> smsProvider;
            case EMAIL -> emailProvider;
        };
    }
}
