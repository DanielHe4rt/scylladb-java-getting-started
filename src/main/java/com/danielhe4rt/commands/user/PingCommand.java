package com.danielhe4rt.commands.user;

import com.danielhe4rt.commands.CommandInterface;
import com.datastax.driver.core.Session;

public class PingCommand implements CommandInterface {


    @Override
    public void execute(Session session, String[] args) {
        System.out.println("ScyllaDB: Pong");
    }
}
