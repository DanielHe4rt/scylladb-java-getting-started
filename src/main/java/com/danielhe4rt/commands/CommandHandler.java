package com.danielhe4rt.commands;

import com.danielhe4rt.commands.database.MigrateCommand;
import com.danielhe4rt.commands.songs.InsertSongCommand;
import com.danielhe4rt.commands.songs.ListSongsCommand;
import com.danielhe4rt.commands.user.PingCommand;

import java.util.Arrays;

public class CommandHandler
{
    private String[] availableCommands = {"!ping", "!migrate", "!songlist", "!newsong"};

    public boolean verifyCommandExists(String commandPrefix) {
        return Arrays.stream(availableCommands).anyMatch(item -> item.equals(commandPrefix));
    }

    public CommandInterface getCommand(String commandPrefix) {
        return switch (commandPrefix) {
            case "!ping" -> new PingCommand();
            case "!migrate" -> new MigrateCommand();
            case "!songlist" -> new ListSongsCommand();
            case "!newsong" -> new InsertSongCommand();
            default -> throw new IllegalStateException("Unexpected value: " + commandPrefix);
        };
    }
}
