package com.danielhe4rt.commands.songs;

import com.danielhe4rt.commands.CommandInterface;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

import java.util.Date;
import java.util.Scanner;

public class InsertSongCommand implements CommandInterface {

    public void execute(Session session, String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.println("ScyllaDB: Insert a song identifier | Ex: 123");
        int id = Integer.parseInt(inputReader.nextLine());

        System.out.println("ScyllaDB: Insert the song name | Ex: Stairway to Heaven");
        String songName = inputReader.nextLine();

        System.out.println("ScyllaDB: Insert the artist name | Ex: Led Zeppelin");
        String artist = inputReader.nextLine();

        System.out.println("ScyllaDB: Insert the album name | Ex: Led Zeppelin IV");
        String album = inputReader.nextLine();

        System.out.println("ScyllaDB: Insert the album name | Ex: Led Zeppelin IV");


        PreparedStatement statement = session.prepare(
                "INSERT INTO media_player.songs (id, title, artist, album, created_at, " +
                "updated_at) VALUES (?,?,?,?,?,?)"
        );

        BoundStatement bound = statement.bind().setInt(0, id)
                .setString(1, songName)
                .setString(2, artist)
                .setString(3, album)
                .setTimestamp(4, new Date())
                .setTimestamp(5, new Date());

        session.execute(bound);
        System.out.println("foi caralho");

    }
}
