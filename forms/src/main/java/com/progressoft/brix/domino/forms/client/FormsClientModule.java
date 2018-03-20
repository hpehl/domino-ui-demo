package com.progressoft.brix.domino.forms.client;

import com.google.gwt.core.client.EntryPoint;
import com.progressoft.brix.domino.api.client.ModuleConfigurator;
import com.progressoft.brix.domino.api.client.annotations.ClientModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Forms")
public class FormsClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(FormsClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Forms frontend module ...");
		new ModuleConfigurator().configureModule(new FormsModuleConfiguration());
	}
}
