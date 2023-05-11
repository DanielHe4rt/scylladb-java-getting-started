package com.danielhe4rt.commands;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public interface CommandInterface {
    public void execute(Session session, String[] args);

}
