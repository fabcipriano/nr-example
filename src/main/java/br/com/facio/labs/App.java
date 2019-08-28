package br.com.facio.labs;

import com.newrelic.api.agent.Agent;
import com.newrelic.api.agent.NewRelic;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {

    private static Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        LOG.info("Send events...");
        Map<String, Object> mapMetricNewRelic = null;

        for (int i = 100; i < 10000; i++) {
            LOG.info("Generate Event id.:{}", i);
            mapMetricNewRelic = generateEvent(i);
            
            LOG.info(" Enviando dados para o New Relic ... ");
            Agent agent = NewRelic.getAgent();
            agent.getInsights().recordCustomEvent( "SpsM4Transactions", mapMetricNewRelic );
            LOG.info("sent.");
            
            Thread.sleep(100);
        }

        LOG.info(" FIM! ");
    }

    private static Map<String, Object> generateEvent( int seed ) {
        // Add a new custom event named "MyCustomEvent" to NRDB in Java
        Map<String, Object> mapMetricNewRelic = new HashMap<String, Object>();
        mapMetricNewRelic.put("Id", 3 + seed);
        mapMetricNewRelic.put("Valor", "Teste5697");
        mapMetricNewRelic.put("type", "M4");
        mapMetricNewRelic.put("transactionType", "SPS");
        mapMetricNewRelic.put("duration", 6+seed);
        mapMetricNewRelic.put("appName", "teste");
        mapMetricNewRelic.put("host", "mgdell01");
        return mapMetricNewRelic;
    }
}
