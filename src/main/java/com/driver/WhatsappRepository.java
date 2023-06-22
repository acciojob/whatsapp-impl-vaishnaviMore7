package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private HashMap<String,String> mobileUser=new HashMap<>();
    private int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.mobileUser=new HashMap<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }
    public String createUser(String name, String mobile) throws Exception {

          if(mobileUser.containsKey(name) && userMobile.contains(mobile)){
              return "The user is already exist";
          }
          else{
              mobileUser.put(name, mobile);
              userMobile.add(mobile);
          }
          return "new user created successfully";
    }


    public Group createGroup(List<User> users){
        Group group=new Group();
        if(!(groupUserMap.containsKey(group))){
            groupUserMap.put(group,users);
        }
        return group;

    }


    public int createMessage(String content){
        senderMap.put(new Message(content),new User());
        return 1;
    }


    public int sendMessage(Message message, User sender, Group group) throws Exception{
        if(senderMap.containsKey(message)){
            return 0;
        }else{
            senderMap.put(message,sender);
        }
         return 1;
    }

    public String changeAdmin(User approver, User user, Group group) {
        adminMap.replace(group,user,approver);
        return "Admin is changed";

    }


    public int removeUser(User user){
        for(Map.Entry<Group, User> e:adminMap.entrySet()) {
            if(adminMap.containsValue(user)){
                Group group1=e.getKey();
                adminMap.replace(group1,user,null);
            }
        }
       return 0;
    }


    public String findMessage(Date start, Date end, int K) {
        String mess1 = null;
        for (List<Message> value : groupMessageMap.values()) {
            for (int i = 0; i < value.size(); i++) {
                Message mess = value.get(i);
                if (mess.getId() == K) {
                    if (mess.getTimestamp() == start || mess.getTimestamp() == end) {
                        mess1 = mess.getContent();
                    }
                }
            }
        }
        return mess1;

    }
}


