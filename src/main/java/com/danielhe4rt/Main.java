package com.danielhe4rt;

import com.danielhe4rt.commands.CommandHandler;
import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;

import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        // bó começar

        Scanner inputReader = new Scanner(System.in);
        CommandHandler commandHandler = new CommandHandler();

        Cluster cluster = Cluster.builder()
                .addContactPoints("node-0.aws_us_east_1.7207ca5a8fdc45f2b03f.clusters.scylla.cloud", "node-1.aws_us_east_1.7207ca5a8fdc45f2b03f.clusters.scylla.cloud", "node-2.aws_us_east_1.7207ca5a8fdc45f2b03f.clusters.scylla.cloud")
                .withLoadBalancingPolicy(DCAwareRoundRobinPolicy.builder().withLocalDc("AWS_US_EAST_1").build()) // your local data center
                .withAuthProvider(new PlainTextAuthProvider("scylla", "lalala"))
                .build();

        Session session = cluster.connect();

        System.out.println("conectamo familia " + cluster.getMetadata().getClusterName());
        System.out.println("Welcome to the ChatBoladaoGPFODASE");

        while (true) {
            System.out.print("danielhe4rt: ");
            String content = inputReader.nextLine();
            String[] commandArgs = content.split(" ");

            if (commandArgs.length == 0) {
                continue;
            }

            if (!commandHandler.verifyCommandExists(commandArgs[0])) {
                continue;
            }

            commandHandler.getCommand(commandArgs[0]).execute(session, commandArgs);
        }

    }

}