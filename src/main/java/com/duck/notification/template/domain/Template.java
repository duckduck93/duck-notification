package com.duck.notification.template.domain;


import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Template<T> {
    private static final String VARIABLE_SYNTAX = "#{%s}";

    private String id;
    private String name;
    private Message.Type type;
    private String title;
    private String content;
    private Boolean useYn;

    private Sender<T> sender;
    private List<Receiver<T>> receivers;
    private List<String> contentVariables;

    public Message<T> toMessage(TemplateVariables variables) {
        String messageContent = content;
        for (Map.Entry<String, String> variable : variables.get()) {
            messageContent = messageContent.replaceAll(VARIABLE_SYNTAX.formatted(variable.getKey()), variable.getValue());
        }
        return Message.create(type, title, content, sender, receivers);
    }

    public void updateUse(Boolean useYn) {
        this.useYn = useYn;
    }

    public void updateSender(Sender<T> sender) {
        this.sender = sender;
    }

    public void addReceiver(Receiver<T> receiver) {
        this.receivers.add(receiver);
    }

    public static List<String> parseContentVariables(String content) {
        Pattern pattern = Pattern.compile("#\\{([^}]*)}");
        Matcher matcher = pattern.matcher(content);
        return matcher.results()
                .map(matchResult -> matchResult.group(1))
                .toList();
    }
}
