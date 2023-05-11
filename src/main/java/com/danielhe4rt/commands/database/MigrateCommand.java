package com.danielhe4rt.commands.database;

import com.danielhe4rt.commands.CommandInterface;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.schemabuilder.*;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;

import static com.datastax.driver.core.schemabuilder.SchemaBuilder.createKeyspace;
import static com.datastax.driver.core.schemabuilder.SchemaBuilder.createTable;

public class MigrateCommand implements CommandInterface {
    @Override
    public void execute(Session session, String[] args) {

        this.migrationList().forEach((cqlName, cqlStatement) -> {
            session.execute(cqlStatement);
            System.out.println("ScyllaDB: Migrated -> " + cqlName);
        });

    }

    private HashMap<String, String> migrationList() {
        HashMap<String, String> migrationCqls = new HashMap<>();

        migrationCqls.put(
                "keyspace",
                createKeyspace("vaicaralhows")
                        .ifNotExists()
                        .with()
                        .replication(ImmutableMap.of("class", "NetworkTopologyStrategy", "replication_factor", 3))
                        .durableWrites(true)
                        .getQueryString()
        );

        migrationCqls.put(
                "table songs",
                createTable("vaicaralhows", "songs1")
                        .ifNotExists()
                        .addPartitionKey("id", DataType.bigint())
                        .addClusteringColumn("updated_at", DataType.timestamp())
                        .addColumn("title", DataType.text())
                        .addColumn("album", DataType.text())
                        .addColumn("artist", DataType.text())
                        .addColumn("created_at", DataType.timestamp())
                        .getQueryString()

        );

        return migrationCqls;
    }
}
