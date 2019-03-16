package com.example.android.pathshalaapp.chatroom;

public class ChatRoom_studs_pojo
{
        String message;
        String username;
        String photo_url;

        public ChatRoom_studs_pojo() {
        }

        public ChatRoom_studs_pojo(String message, String username, String photo_url)
        {
            this.message = message;
            this.username = username;
            this.photo_url = photo_url;
        }

        public String getMessage() {     return message;    }

        public String getUsername() {    return username;    }

        public String getPhoto_url() {   return photo_url;    }

}