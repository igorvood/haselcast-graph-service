package ru.vood.graph.haselcastgraphservice.configuration.delete;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.cdc.CdcSinks;
import com.hazelcast.jet.cdc.ChangeRecord;
import com.hazelcast.jet.cdc.postgres.PostgresCdcSources;
import com.hazelcast.jet.config.JobConfig;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.StreamSource;

public class Delete {
    public static void main(String[] args) {
        StreamSource<ChangeRecord> source = PostgresCdcSources.postgres("source")
                .setDatabaseAddress("127.0.0.1")
                .setDatabasePort(5432)
                .setDatabaseUser("postgres")
                .setDatabasePassword("postgres")
                .setDatabaseName("postgres")
                .setTableWhitelist("inventory.customers")
                .build();

        Pipeline pipeline = Pipeline.create();
        pipeline.readFrom(source)
                .withoutTimestamps()
                .peek()
                .writeTo(CdcSinks.map("customers",
                        r -> r.key().toMap().get("id"),
                        r -> r.value().toObject(Customer.class).toString()));

        JobConfig cfg = new JobConfig().setName("postgres-monitor");
        Jet.bootstrappedInstance().newJob(pipeline, cfg);
    }
}
