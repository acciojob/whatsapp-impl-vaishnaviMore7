package com.driver;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Date;
import java.util.List;

public class WhatsappService {
    WhatsappRepository whatsappRepository=new WhatsappRepository();
    public String createUser(String name, String mobile) throws Exception {
        return whatsappRepository.createUser(name,mobile);
    }


    public Group createGroup(List<User> users){
        return whatsappRepository.createGroup(users);

    }


    public int createMessage(String content){
       return whatsappRepository.createMessage(content);
    }


    public int sendMessage(Message message, User sender, Group group) throws Exception{
         return whatsappRepository.sendMessage(message, sender, group);
    }

    public String changeAdmin(User approver, User user, Group group) {
        return whatsappRepository.changeAdmin(approver, user, group);
    }


    public int removeUser(User user){
       return whatsappRepository.removeUser(user);
    }


    public String findMessage(Date start, Date end, int K) {
        return whatsappRepository.findMessage(start,end,K);

    }
}


