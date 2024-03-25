package com.duck.notification.template.domain;

import com.duck.notification.core.model.Email;
import com.duck.notification.core.util.IdGenerator;
import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import com.duck.notification.message.domain.Sender;

import java.util.List;

public class EmailTemplate extends Template<Email> {
    protected EmailTemplate(String id, String name, String content, String useYn, Sender<Email> sender, List<Receiver<Email>> receivers) {
        super(id, name, Message.Type.EMAIL, content, useYn, sender, receivers);
    }

    public static EmailTemplate create(String id, String name, String content, Sender<Email> sender, List<Receiver<Email>> receivers) {
        return new EmailTemplate(id, name, content, "Y", sender, receivers);
    }

    public static EmailTemplate create(String name, String content, Sender<Email> sender, List<Receiver<Email>> receivers) {
        return new EmailTemplate(IdGenerator.generate(String.class), name, content, "N", sender, receivers);
    }
}
