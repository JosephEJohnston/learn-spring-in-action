package com.noob.resourceserver.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.integration.router.MessageRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Configuration
public class FileWriterIntegrationConfig {

    @Bean
    public MessageChannel orderChannel() {
        return new PublishSubscribeChannel();

        // 若使用 QueueChannel，则消费者必须配置一个 poller
        // return new QueueChannel();
    }

    /*
    // 过滤器
    @Filter(inputChannel = "numberChannel", outputChannel = "evenNumberChannel")
    public boolean evenNumberFilter(Integer number) {
        return number % 2 == 0;
    }*/

    /*// 路由器
    @Bean
    @Router(inputChannel = "numberChannel")
    public AbstractMessageRouter evenOddRouter() {
        return new AbstractMessageRouter() {
            @Override
            protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
                Integer number = (Integer) message.getPayload();
                if (number % 2 == 0) {
                    return Collections.singleton(evenChannel());
                }

                return Collections.singleton(oddChannel());
            }
        };
    }*/

    // 切分器
    @Bean
    @Splitter(inputChannel = "poChannel", outputChannel = "splitOrderChannel")
    public OrderSplitter orderSplitter() {
        return new OrderSplitter();
    }

    @Bean
    @Router(inputChannel = "splitOrderChannel")
    public MessageRouter splitOrderRouter() {
        PayloadTypeRouter router = new PayloadTypeRouter();

        router.setChannelMapping(
                BillingInfo.class.getName(), "billingInfoChannel");
        router.setChannelMapping(
                List.class.getName(), "lineItemsChannel");

        return router;
    }

    // 再将 lineItemsChannel 中 List 的每个元素拆分为一个消息
    // 并发送到 lineItemChannel 中
    @Bean
    @Splitter(inputChannel = "lineItemsChannel", outputChannel = "lineItemChannel")
    public List<LineItem> lineItemSplitter(List<LineItem> lineItems) {
        return lineItems;
    }

    @Bean
    public MessageChannel evenChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel oddChannel() {
        return new DirectChannel();
    }

    private String route(Object p) {
        return p.getClass().isAssignableFrom(BillingInfo.class)
                ? "BILLING_INFO"
                : "LINE_ITEMS";
    }

    private BillingInfo handleBillingInfo(
            BillingInfo billingInfo,
            MessageHeaders h
    ) {
        // to do
        return null;
    }

    private LineItem handleLineItems(
            LineItem lineItem,
            MessageHeaders h
    ) {
        // to do
        return null;
    }


    @Bean
    public IntegrationFlow fileWriterFlow() {
        return IntegrationFlow
                .from(MessageChannels.direct("textInChannel"))

                // 转换器，也可传入 bean
                .<String, String>transform(String::toUpperCase)

                // 过滤器
                //.<Integer>filter(p -> p % 2 == 0)

                // 默认为 DirectChannel
                //.channel(MessageChannels.direct("FileWriterChannel"))
                .channel("orderChannel")
                .handle(Files
                        .outboundAdapter(new File("/tmp/sia6/files"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true))

                /*
                //
                .split()
                .<Object, String> route(this::route,
                        mapping -> mapping
                                .subFlowMapping("BILLING_INFO", sf -> sf
                                        .handle(this::handleBillingInfo))
                                .subFlowMapping("LINE_ITEMS", sf -> sf
                                        .split()
                                        .handle(this::handleLineItems)))*/

                /*
                // 切割路由 dsl 等价
                .split(orderSplitter())
                .<Object, String> route(
                        p -> {

                        }, mapping -> mapping
                                .subFlowMapping("BILLING_INFO", sf -> sf
                                        .<BillingInfo>handle((billingInfo, h) -> {

                                }))
                                .subFlowMapping("LINE_ITEMS", sf -> sf
                                        .split()
                                        .<LineItem> handle((lineItem, h) -> {

                                }))
                )*/

                /*
                // dsl 路由
                .<Integer, String>route(n -> n % 2 == 0 ? "EVEN" : "ODD", mapping -> mapping
                        .subFlowMapping("EVEN", sf -> sf
                                .<Integer, Integer>transform(n -> n * 10)
                                .handle((i, h) -> {
                        }))
                        .subFlowMapping("ODD", sf -> sf.transform()))*/

                .get();
    }
}
