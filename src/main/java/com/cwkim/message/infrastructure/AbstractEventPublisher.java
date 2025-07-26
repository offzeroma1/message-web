package com.cwkim.message.infrastructure;

public abstract class AbstractEventPublisher<T> {

    protected abstract String getChannel();
    protected abstract Object setMessage(T message);

    protected abstract void send(String channel, Object message);

    public void publish(T msg) {
        String channel = getChannel();
        Object message = setMessage(msg);
        send(channel, message);
    }
}
