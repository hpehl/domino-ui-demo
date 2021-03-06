package org.dominokit.domino.icons.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Icons")
public class IconsClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(IconsClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Icons frontend module ...");
		new ModuleConfigurator().configureModule(new IconsModuleConfiguration());
	}
}
