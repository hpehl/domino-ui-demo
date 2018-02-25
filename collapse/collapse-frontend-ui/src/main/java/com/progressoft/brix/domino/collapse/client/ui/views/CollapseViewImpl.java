package com.progressoft.brix.domino.collapse.client.ui.views;

import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.api.shared.extension.Content;
import com.progressoft.brix.domino.collapse.client.presenters.CollapsePresenter;
import com.progressoft.brix.domino.collapse.client.views.CollapseView;
import com.progressoft.brix.domino.ui.button.Button;
import com.progressoft.brix.domino.ui.cards.Card;
import com.progressoft.brix.domino.ui.collapsible.Accordion;
import com.progressoft.brix.domino.ui.collapsible.AccordionPanel;
import com.progressoft.brix.domino.ui.collapsible.Collapsible;
import com.progressoft.brix.domino.ui.column.Column;
import com.progressoft.brix.domino.ui.header.BlockHeader;
import com.progressoft.brix.domino.ui.icons.Icons;
import com.progressoft.brix.domino.ui.row.Row;
import com.progressoft.brix.domino.ui.style.Background;
import com.progressoft.brix.domino.ui.style.Color;
import com.progressoft.brix.domino.ui.style.CssStyles;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.Text;
import jsinterop.base.Js;

import javax.annotation.processing.RoundEnvironment;

import static org.jboss.gwt.elemento.core.Elements.b;
import static org.jboss.gwt.elemento.core.Elements.col;
import static org.jboss.gwt.elemento.core.Elements.div;

@UiView(presentable = CollapsePresenter.class)
public class CollapseViewImpl implements CollapseView {

    private static final String SAMPLE_CONTENT = "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.";

    private HTMLDivElement element = div().asElement();

    private final Column column = Column.create()
            .onLarge(Column.OnLarge.twelve)
            .onMedium(Column.OnMedium.twelve)
            .onSmall(Column.OnSmall.twelve)
            .onXSmall(Column.OnXSmall.twelve);

    public CollapseViewImpl() {
        element.appendChild(BlockHeader.create("COLLAPSE").asElement());
        example();
        element.appendChild(BlockHeader.create("ACCORDION").asElement());
        accordionSample();
        colorFullWithIcons();
        multiOpenItems();
    }

    private void example() {
        Collapsible collapsible = Collapsible.create(div()
                .add(div()
                        .css("well")
                        .textContent(SAMPLE_CONTENT)
                        .asElement())
                .asElement());
        EventListener collapsibleListener = evt -> {
            if (collapsible.isCollapsed())
                collapsible.expand();
            else
                collapsible.collapse();
        };

        Button anchorButton = Button.create("LINK WITH HREF");
        anchorButton.justify();
        anchorButton.getClickableElement().addEventListener("click", collapsibleListener);

        Button button = Button.create("BUTTON");
        button.getClickableElement().addEventListener("click", collapsibleListener);

        element.appendChild(Row.create()
                .addColumn(column.copy()
                        .addElement(Card.create("EXAMPLE", "click the buttons below to show and hide another element via class changes.")
                                .appendContent(anchorButton.htmlBuilder()
                                        .css(CssStyles.M_B_15).component().setBackground(Background.PINK)
                                        .asElement())
                                .appendContent(new Text("\n"))
                                .appendContent(button.htmlBuilder()
                                        .css(CssStyles.M_B_15).component()
                                        .setBackground(Background.CYAN)
                                        .asElement())
                                .appendContent(collapsible.asElement())
                                .asElement())).asElement());

        element.appendChild(Card.createCodeCard("Collapsible collapsible = Collapsible.create(div()\n" +
                "        .add(div()\n" +
                "                .css(\"well\")\n" +
                "                .textContent(SAMPLE_CONTENT)\n" +
                "                .asElement())\n" +
                "        .asElement());\n" +
                "EventListener collapsibleListener = evt -> {\n" +
                "    if (collapsible.isCollapsed())\n" +
                "        collapsible.expand();\n" +
                "    else\n" +
                "        collapsible.collapse();\n" +
                "};\n" +
                "\n" +
                "Button anchorButton = Button.create(\"LINK WITH HREF\");\n" +
                "anchorButton.justify();\n" +
                "anchorButton.getClickableElement().addEventListener(\"click\", collapsibleListener);\n" +
                "\n" +
                "Button button = Button.create(\"BUTTON\");\n" +
                "button.getClickableElement().addEventListener(\"click\", collapsibleListener);\n" +
                "\n" +
                "element.appendChild(Row.create()\n" +
                "        .addColumn(column.copy()\n" +
                "                .addElement(Card.create(\"EXAMPLE\", \"click the buttons below to show and hide another element via class changes.\")\n" +
                "                        .appendContent(anchorButton.htmlBuilder()\n" +
                "                                .css(CssStyles.M_B_15).component().setBackground(Background.PINK)\n" +
                "                                .asElement())\n" +
                "                        .appendContent(new Text(\"\\n\"))\n" +
                "                        .appendContent(button.htmlBuilder()\n" +
                "                                .css(CssStyles.M_B_15).component()\n" +
                "                                .setBackground(Background.CYAN)\n" +
                "                                .asElement())\n" +
                "                        .appendContent(collapsible.asElement())\n" +
                "                        .asElement())).asElement());")
                .asElement());
    }

    private void accordionSample() {
        Row row = Row.create();
        Column column = Column.create()
                .onLarge(Column.OnLarge.six)
                .onMedium(Column.OnMedium.six)
                .onSmall(Column.OnSmall.twelve)
                .onXSmall(Column.OnXSmall.twelve);

        element.appendChild(row.addColumn(column.copy()
                .addElement(Card.create("BASIC EXAMPLES", "Extend the default collapse behavior to create an accordion with the panel component.")
                        .setCollapsible()
                        .appendContent(b().textContent("Panel Primary").asElement())
                        .appendContent(Accordion.create()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT)).expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT)))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT)))
                                .asElement())
                        .appendContent(b().textContent("Panel Success").asElement())
                        .appendContent(Accordion.create()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT)).expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT)))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT)))
                                .success()
                                .asElement())
                        .appendContent(b().textContent("Panel Warning").asElement())
                        .appendContent(Accordion.create()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT)).expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT)))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT)))
                                .warning()
                                .asElement())
                        .appendContent(b().textContent("Panel Danger").asElement())
                        .appendContent(Accordion.create()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT)).expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT)))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT)))
                                .danger()
                                .asElement())
                        .asElement()))
                .addColumn(column.copy().addElement(Card.create("FULL BODY EXAMPLES", "If you want to also colorful body, you need to use fullBody method.")
                        .setCollapsible()
                        .appendContent(b().textContent("Panel Primary").asElement())
                        .appendContent(Accordion.create()
                                .fullBody()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT)).expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT)))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT)))
                                .asElement())
                        .appendContent(b().textContent("Panel Success").asElement())
                        .appendContent(Accordion.create()
                                .fullBody()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT)).expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT)))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT)))
                                .success()
                                .asElement())
                        .appendContent(b().textContent("Panel Warning").asElement())
                        .appendContent(Accordion.create()
                                .fullBody()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT)).expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT)))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT)))
                                .warning()
                                .asElement())
                        .appendContent(b().textContent("Panel Danger").asElement())
                        .appendContent(Accordion.create()
                                .fullBody()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT)).expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT)))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT)))
                                .danger()
                                .asElement())
                        .asElement()))
                .asElement());

        element.appendChild(Card.createCodeCard("Row row = Row.create();\n" +
                "Column column = Column.create()\n" +
                "        .onLarge(Column.OnLarge.six)\n" +
                "        .onMedium(Column.OnMedium.six)\n" +
                "        .onSmall(Column.OnSmall.twelve)\n" +
                "        .onXSmall(Column.OnXSmall.twelve);\n" +
                "\n" +
                "element.appendChild(row.addColumn(column.copy()\n" +
                "        .addElement(Card.create(\"BASIC EXAMPLES\", \"Extend the default collapse behavior to create an accordion with the panel component.\")\n" +
                "                .appendContent(b().textContent(\"Panel Primary\").asElement())\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT)).expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .asElement())\n" +
                "                .appendContent(b().textContent(\"Panel Success\").asElement())\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT)).expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .success()\n" +
                "                        .asElement())\n" +
                "                .appendContent(b().textContent(\"Panel Warning\").asElement())\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT)).expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .warning()\n" +
                "                        .asElement())\n" +
                "                .appendContent(b().textContent(\"Panel Danger\").asElement())\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT)).expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .danger()\n" +
                "                        .asElement())\n" +
                "                .asElement()))\n" +
                "        .addColumn(column.copy().addElement(Card.create(\"FULL BODY EXAMPLES\", \"If you want to also colorful body, you need to use fullBody method.\")\n" +
                "                .appendContent(b().textContent(\"Panel Primary\").asElement())\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .fullBody()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT)).expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .asElement())\n" +
                "                .appendContent(b().textContent(\"Panel Success\").asElement())\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .fullBody()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT)).expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .success()\n" +
                "                        .asElement())\n" +
                "                .appendContent(b().textContent(\"Panel Warning\").asElement())\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .fullBody()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT)).expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .warning()\n" +
                "                        .asElement())\n" +
                "                .appendContent(b().textContent(\"Panel Danger\").asElement())\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .fullBody()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT)).expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT)))\n" +
                "                        .danger()\n" +
                "                        .asElement())\n" +
                "                .asElement()))\n" +
                "        .asElement());")
                .asElement());

    }

    private void colorFullWithIcons() {
        Row row = Row.create();
        Column column = Column.create()
                .onLarge(Column.OnLarge.six)
                .onMedium(Column.OnMedium.six)
                .onSmall(Column.OnSmall.twelve)
                .onXSmall(Column.OnXSmall.twelve);

        element.appendChild(row.addColumn(column.copy()
                .addElement(Card.create("COLORFUL PANEL ITEMS WITH ICON")
                        .setCollapsible()
                        .appendContent(b().textContent("Panel Primary").asElement())
                        .appendContent(Accordion.create()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.perm_contact_calendar())
                                        .setColor(Color.PINK)
                                        .expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.cloud_download())
                                        .setColor(Color.CYAN))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.contact_phone())
                                        .setColor(Color.TEAL))
                                .addPanel(AccordionPanel.create("Collapsible item 4", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.folder_shared())
                                        .setColor(Color.ORANGE))
                                .asElement())
                        .asElement()))
                .addColumn(column.copy().addElement(Card.create("FULL BODY COLORFUL PANEL ITEMS WITH ICON")
                        .setCollapsible()
                        .appendContent(b().textContent("Panel Primary").asElement())
                        .appendContent(Accordion.create()
                                .fullBody()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.perm_contact_calendar())
                                        .setColor(Color.PINK)
                                        .expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.cloud_download())
                                        .setColor(Color.CYAN))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.contact_phone())
                                        .setColor(Color.TEAL))
                                .addPanel(AccordionPanel.create("Collapsible item 4", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.folder_shared())
                                        .setColor(Color.ORANGE))
                                .asElement())
                        .asElement()))
                .asElement());

        element.appendChild(Card.createCodeCard("Row row = Row.create();\n" +
                "Column column = Column.create()\n" +
                "        .onLarge(Column.OnLarge.six)\n" +
                "        .onMedium(Column.OnMedium.six)\n" +
                "        .onSmall(Column.OnSmall.twelve)\n" +
                "        .onXSmall(Column.OnXSmall.twelve);\n" +
                "\n" +
                "element.appendChild(row.addColumn(column.copy()\n" +
                "        .addElement(Card.create(\"COLORFUL PANEL ITEMS WITH ICON\")\n" +
                "                .setCollapsible()\n" +
                "                .appendContent(b().textContent(\"Panel Primary\").asElement())\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.perm_contact_calendar())\n" +
                "                                .setColor(Color.PINK)\n" +
                "                                .expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.cloud_download())\n" +
                "                                .setColor(Color.CYAN))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.contact_phone())\n" +
                "                                .setColor(Color.TEAL))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 4\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.folder_shared())\n" +
                "                                .setColor(Color.ORANGE))\n" +
                "                        .asElement())\n" +
                "                .asElement()))\n" +
                "        .addColumn(column.copy().addElement(Card.create(\"FULL BODY COLORFUL PANEL ITEMS WITH ICON\")\n" +
                "                .setCollapsible()\n" +
                "                .appendContent(b().textContent(\"Panel Primary\").asElement())\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .fullBody()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.perm_contact_calendar())\n" +
                "                                .setColor(Color.PINK)\n" +
                "                                .expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.cloud_download())\n" +
                "                                .setColor(Color.CYAN))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.contact_phone())\n" +
                "                                .setColor(Color.TEAL))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 4\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.folder_shared())\n" +
                "                                .setColor(Color.ORANGE))\n" +
                "                        .asElement())\n" +
                "                .asElement()))\n" +
                "        .asElement());")
                .asElement());

    }

    private void multiOpenItems() {
        Row row = Row.create();

        element.appendChild(row.addColumn(column.copy()
                .addElement(Card.create("MULTIPLE ITEMS TO BE OPEN")
                        .setCollapsible()
                        .appendContent(Accordion.create()
                                .multiOpen()
                                .fullBody()
                                .addPanel(AccordionPanel.create("Collapsible item 1", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.perm_contact_calendar())
                                        .setColor(Color.PINK)
                                        .expand())
                                .addPanel(AccordionPanel.create("Collapsible item 2", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.cloud_download())
                                        .setColor(Color.CYAN))
                                .addPanel(AccordionPanel.create("Collapsible item 3", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.contact_phone())
                                        .setColor(Color.TEAL)
                                        .expand())
                                .addPanel(AccordionPanel.create("Collapsible item 4", new Text(SAMPLE_CONTENT))
                                        .setIcon(Icons.ALL.folder_shared())
                                        .setColor(Color.ORANGE))
                                .asElement())
                        .asElement()))
                .asElement());

        element.appendChild(Card.createCodeCard("Row row = Row.create();\n" +
                "\n" +
                "element.appendChild(row.addColumn(column.copy()\n" +
                "        .addElement(Card.create(\"MULTIPLE ITEMS TO BE OPEN\")\n" +
                "                .setCollapsible()\n" +
                "                .appendContent(Accordion.create()\n" +
                "                        .multiOpen()\n" +
                "                        .fullBody()\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 1\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.perm_contact_calendar())\n" +
                "                                .setColor(Color.PINK)\n" +
                "                                .expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 2\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.cloud_download())\n" +
                "                                .setColor(Color.CYAN))\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 3\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.contact_phone())\n" +
                "                                .setColor(Color.TEAL)\n" +
                "                                .expand())\n" +
                "                        .addPanel(AccordionPanel.create(\"Collapsible item 4\", new Text(SAMPLE_CONTENT))\n" +
                "                                .setIcon(Icons.ALL.folder_shared())\n" +
                "                                .setColor(Color.ORANGE))\n" +
                "                        .asElement())\n" +
                "                .asElement()))\n" +
                "        .asElement());")
                .asElement());

    }

    @Override
    public void showIn(Content content) {
        HTMLElement contentElement = Js.cast(content.get());
        contentElement.appendChild(element);
    }
}