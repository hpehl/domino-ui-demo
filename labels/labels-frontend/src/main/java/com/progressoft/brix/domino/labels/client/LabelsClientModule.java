package com.progressoft.brix.domino.labels.client;

import com.google.gwt.core.client.EntryPoint;
import com.progressoft.brix.domino.api.client.ModuleConfigurator;
import com.progressoft.brix.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Labels")
public class LabelsClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(LabelsClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Labels frontend module ...");
		new ModuleConfigurator().configureModule(new LabelsModuleConfiguration());
	}
}
