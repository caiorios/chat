package com.br.iff.si.chatserver.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
    
    private String nick;
    private String ip;

    public User() {
    }
    
    public User(String nick, String url) {
        this.nick = nick;
        this.ip = url;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getIp() {
        return ip;
    }
}
