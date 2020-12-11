package com.wims.whereismystore.Class;

import java.util.HashMap;
import java.util.Map;

public class ChatModel {
    public Map<String, Boolean> getUsers() {
        return users;
    }

    public void setUsers(Map<String, Boolean> users) {
        this.users = users;
    }

    public Map<String, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<String, Comment> comments) {
        this.comments = comments;
    }

    public Map<String,Boolean> users=new HashMap<>(); //채팅방 유저들
    public Map<String, Comment> comments=new HashMap<>(); //채팅방의 내용

    public static class Comment{
        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String uid;
        public String message;
        public String name;

    }


//
//    public String My_id;
//    public String UID;
}
