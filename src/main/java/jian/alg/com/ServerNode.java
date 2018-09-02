package jian.alg.com;

import java.util.Objects;

public class ServerNode {
    public String servName;
    public String password;
    public String port;
    public String ip;

    public ServerNode(String servName, String password, String ip, String port) {
        this.servName = servName;
        this.password = password;
        this.ip = ip;
        this.port = port;
    }


    public ServerNode(ServerNode s) {
        this.servName = s.servName;
        this.password = s.password;
        this.ip = s.ip;
        this.port = s.port;
    }

    @Override
    public String toString() {
        return "ServerNode{" +
                "servName='" + servName + '\'' +
                ", password='" + password + '\'' +
                ", port='" + port + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerNode)) return false;
        ServerNode that = (ServerNode) o;
        return Objects.equals(servName, that.servName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(port, that.port) &&
                Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {

        return Objects.hash(servName, password, port, ip);
    }
}