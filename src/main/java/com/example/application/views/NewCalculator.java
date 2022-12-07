package com.example.application.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("Calculatrice, Full Java")
@Route(value = "new-calcul", layout = MainLayout.class)
public class NewCalculator  extends VerticalLayout {
    H2 titre;
    Label ecran;
    HorizontalLayout hl1, hl2, hl3, hl4;
    Button chf0, chf1, chf2, chf3, chf4, chf5, chf6, chf7, chf8, chf9;
    Button eq, plus, fois, moins, div, ce;

    public NewCalculator() {
        titre = new H2("New Calculator");
        ecran = new Label("0.0");
        init();
        List<Button> chfs = List.of(chf0, chf1, chf2, chf3, chf4, chf5, chf6, chf7, chf8, chf9);
        
        for (int i = 0; i < chfs.size(); i++)
            prepare(chfs, i);

        hl1.add(chf7, chf8, chf9, ce);
        hl2.add(chf4, chf5, chf6, fois);
        hl3.add(chf1, chf2, chf3, div);
        hl4.add(chf0, eq, plus, moins);

        add(titre, ecran, hl1, hl2, hl3, hl4);
    }

    private void init() {
        setWidth("370px");
        getStyle().set("align-items", "center");
        //Chiffres
        chf0 = new Button();
        chf1 = new Button();
        chf2 = new Button();
        chf3 = new Button();
        chf4 = new Button();
        chf5 = new Button();
        chf6 = new Button();
        chf7 = new Button();
        chf8 = new Button();
        chf9 = new Button();
        //Opérations
        eq = new Button("=", this::eval);

        plus = new Button("+", this::operation);
        fois = new Button("x", this::operation);
        moins = new Button("-", this::operation);
        div = new Button("/", this::operation);

        ce = new Button("CE", this::clear);

        //Layouts : hl1, hl2,hl3,hl4;
        hl1 = new HorizontalLayout();
        hl2 = new HorizontalLayout();
        hl3 = new HorizontalLayout();
        hl4 = new HorizontalLayout();

        //Styles
        ecran.getStyle().set("align-self", "stretch");
        ecran.getStyle().set("color", "white");
        ecran.getStyle().set("background-color", "DodgerBlue");
        ecran.getStyle().set("text-align", "right");
        ecran.getStyle().set("padding-right", "10px");

    }

    private void operation(ClickEvent<Button> buttonClickEvent) {
    }

    private void eval(ClickEvent<Button> buttonClickEvent) {
    }

    private void clear(ClickEvent<Button> buttonClickEvent) {
        ecran.setText("0");
    }

    private void prepare(List<Button> chiffres, Integer i) {
        Button btn = chiffres.get(i);
        btn.setText(i.toString());
        btn.addClickListener(this::addChiffre);
    }

    private void addChiffre(ClickEvent<Button> e) {
        String number = ecran.getText();
        ecran.setText(number + e.getSource().getText());
    }
}
