package com.danielhe4rt.commands.songs;

import com.danielhe4rt.commands.CommandInterface;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.List;

public class ListSongsCommand implements CommandInterface {
    @Override
    public void execute(Session session, String[] args) {
        ResultSet results = session.execute("SELECT * FROM vaicaralho.songs PER PARTITION LIMIT 1");
        List<Row> resultsmemo = results.all();

        if (resultsmemo.size() == 0) {
            System.out.println("ScyllaDB: Zero rows found :/");
            return;
        }

        for (Row row : resultsmemo) {
            System.out.println(
                    String.format(
                            "ID: %d -> Song: %s -> Artist: %s -> Album: %s -> Created At: %s",
                            row.getInt("id"),
                            row.getString("title"),
                            row.getString("artist"),
                            row.getString("album"),
                            row.getTimestamp("created_at").toString()
                    )
            );
        }
    }
}
