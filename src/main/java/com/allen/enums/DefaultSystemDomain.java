package com.allen.enums;


public enum  DefaultSystemDomain {

    DEFAULT("default","","http://www.weather.com.cn/");

    String name;
    String ip;
    String url;

    DefaultSystemDomain(String name, String ip, String url) {
        this.name = name;
        this.ip = ip;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return "DefaultSystemDomain{" +
                "name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
