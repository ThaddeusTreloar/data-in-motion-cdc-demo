package org.cdc_demo.data_faker;

import org.cdc_demo.data_faker.biz.KafkaEnv;
import org.cdc_demo.data_faker.biz.Runner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppApplication {
	public static void main(String[] args) {
		var config = new KafkaEnv();
		var runner = new Runner(config);

		log.info("Starting Runner...");
		runner.run();
	}
}
