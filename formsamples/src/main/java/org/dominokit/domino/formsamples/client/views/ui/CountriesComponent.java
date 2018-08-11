package org.dominokit.domino.formsamples.client.views.ui;

import org.dominokit.domino.formsamples.shared.model.Country;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.SelectOption;
import org.dominokit.domino.ui.icons.Icons;

import java.util.List;

import static org.jboss.gwt.elemento.core.Elements.i;

public class CountriesComponent {

    private Select<Country> countriesSelect;
    private Select<String> citiesSelect;

    public CountriesComponent() {
        countriesSelect = Select.<Country>create("Country")
                .setLeftAddon(i().css("fas", "fa-globe", "fa-lg"));
        citiesSelect = Select.<String>create("City")
                .setLeftAddon(Icons.ALL.location_city())
                .disable();

        countriesSelect.addSelectionHandler(option -> {
            citiesSelect.enable();
            citiesSelect.removeAllOptions();
            Country country = option.getValue();
            for (String city : country.getCities()) {
                citiesSelect.addOption(SelectOption.create(city, city));
            }
        });
    }

    public CountriesComponent(List<Country> countries) {
        this();
        setCountries(countries);
    }

    public static CountriesComponent create(List<Country> countries) {
        return new CountriesComponent(countries);
    }

    public static CountriesComponent create() {
        return new CountriesComponent();
    }

    public CountriesComponent setCountries(List<Country> countries) {
        countriesSelect.removeAllOptions();
        for (Country country : countries) {
            countriesSelect.addOption(SelectOption.create(country, country.getName()));
        }
        return this;
    }

    public Select<Country> getCountriesSelect() {
        return countriesSelect;
    }

    public Select<String> getCitiesSelect() {
        return citiesSelect;
    }
}
