package com.duck.notification.template.domain;


import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public abstract class Template<T> {
    private static final String VARIABLE_SYNTAX = "#{%s}";

    private String id;
    private String name;
    private Message.Type type;
    private String content;
    private String useYn;

    private Sender<T> sender;
    private List<Receiver<T>> receivers;
    private List<String> contentVariables;

    protected Template(String id, String name, Message.Type type, String content, String useYn, Sender<T> sender, List<Receiver<T>> receivers) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.content = content;
        this.useYn = useYn;
        this.sender = sender;
        this.receivers = receivers;
    }

    public Message<T> toMessage(TemplateVariables variables) {
        String messageContent = content;
        for (Map.Entry<String, String> variable : variables.get()) {
            messageContent = messageContent.replaceAll(VARIABLE_SYNTAX.formatted(variable.getKey()), variable.getValue());
        }
        return Message.create(type, content, sender, receivers);
    }

    public List<String> getContentVariables() {
        Pattern pattern = Pattern.compile("#\\{([^}]*)}");
        Matcher matcher = pattern.matcher(content);
        contentVariables = matcher.results()
                .map(matchResult -> matchResult.group(1))
                .toList();
        return contentVariables;
    }

    public void updateUse(String useYn) {
        this.useYn = useYn;
    }

    public void updateSender(Sender<T> sender) {
        this.sender = sender;
    }

    public void addReceiver(Receiver<T> receiver) {
        this.receivers.add(receiver);
    }
}
