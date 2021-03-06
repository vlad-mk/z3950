package org.xbib.io.iso23950;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ZClientTest {

    private static final Logger logger = Logger.getLogger(ZClientTest.class.getName());

    @Test
    public void testCQL() {
        for (String serviceName : Arrays.asList("LIBRIS", "SWB")) {
            String query = "bib.identifierISSN = 00280836";
            int from = 1;
            int size = 10;
            try (ZClient client = ZClient.newZClient(serviceName)) {
                logger.log(Level.INFO, "executing CQL " + serviceName);
                int count = client.executeCQL(query, from, size,
                        (status, recordCount, elapsedMillis) ->
                                logger.log(Level.INFO, serviceName + " results = " + recordCount),
                        record ->
                                logger.log(Level.INFO, "record = " + record));
                logger.log(Level.INFO, "returned records = " + count);
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    @Test
    public void testPQF() {
        for (String serviceName : Arrays.asList("LIBRIS", "SWB")) {
            String query = "@attr 1=8 \"00280836\"";
            int from = 1;
            int size = 10;
            try (ZClient client = ZClient.newZClient(serviceName)) {
                logger.log(Level.INFO, "executing PQF " + serviceName);
                int count = client.executePQF(query, from, size,
                        (status, recordCount, elapsedMillis) ->
                                logger.log(Level.INFO, serviceName + " status = " + status + " results = " + recordCount),
                        record ->
                                logger.log(Level.INFO, "record = " + record.toString(Charset.forName(client.getEncoding()))));
                logger.log(Level.INFO, "returned records = " + count);
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }
}
