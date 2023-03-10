package com.noob.resourcewebfluxserver.mbean;

import com.noob.resourcewebfluxserver.dao.TacoRepository;
import com.noob.resourcewebfluxserver.model.Taco;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.concurrent.atomic.AtomicLong;

// 自定义 mbean
@Service
@ManagedResource
public class TacoCounter extends AbstractRepositoryEventListener<Taco>
        // 实现 NotificationPublisherAware 可以发送通知给订阅此 MBean 的 JMX 客户端
        implements NotificationPublisherAware {


    private AtomicLong counter;

    private NotificationPublisher np;

    public TacoCounter(TacoRepository tacoRepo) {
        tacoRepo.count()
                .subscribe(initialCount ->
                        this.counter = new AtomicLong(initialCount));
    }

    @Override
    protected void onAfterCreate(Taco entity) {
        counter.incrementAndGet();
    }

    @ManagedAttribute
    public long getTacoCount() {
        return counter.get();
    }

    @ManagedOperation
    public long increment(long delta) {
        long before = counter.get();
        long after = counter.addAndGet(delta);
        if ((after / 100) > (before / 100)) {
            Notification notification = new Notification(
                    "taco.count", this,
                    before, after + "th taco created!");
            np.sendNotification(notification);
        }
        return after;
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.np = notificationPublisher;
    }

}
