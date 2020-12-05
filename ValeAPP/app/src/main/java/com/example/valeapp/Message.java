package com.example.valeapp;

public class Message {
    private String text; // message body
    private MemberData memberData; // data of the user that sent this message
    private boolean belongsToCurrentUser; // is this message sent by us?
    private String user; // user who sent the message;

    public Message(String text, MemberData memberData, boolean belongsToCurrentUser, String user) {
        this.text = text;
        this.memberData = memberData;
        this.belongsToCurrentUser = belongsToCurrentUser;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public MemberData getMemberData() {
        return memberData;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }

    public String getUser(){
        return user;
    }
}