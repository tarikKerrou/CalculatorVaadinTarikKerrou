package com.example.application.views;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.awt.event.*;

import java.util.List;

@PageTitle("Calculatrice")
@Route(value = "calcul", layout = MainLayout.class)
@Tag("calculator-view")
@JsModule("./views/calculator-view.ts")
public class CalculatorView extends LitTemplate {
	
    String action;
	double result ;
	double tmp ;
	
    @Id
    private Button chf0;
    @Id
    private Button chf1;
    @Id
    private Button chf2;
    @Id
    private Button chf3;
    @Id
    private Button chf4;
    @Id
    private Button chf5;
    @Id
    private Button chf6;
    @Id
    private Button chf7;
    @Id
    private Button chf8;
    @Id
    private Button chf9;
    @Id
    private Label ecran ;
    @Id
    private HorizontalLayout extendedLayout;
    @Id
    private Checkbox extendedChk;
    @Id
    private Button inverse;
    @Id
    private Button ce ;
    @Id
    private Button egal ;
    @Id
    private Button plus ;
    @Id
    private Button moins ;
    @Id
    private Button fois ;
    @Id
    private Button div ;
    @Id
    private Button cos ;
    @Id
    private Button sin ;
    @Id
    private Button tan ;

    public CalculatorView() {
        List<Button> chiffres = List.of(chf0,chf1,chf2,chf3,chf4,chf5,chf6,chf7,chf8,chf9);
        for (int i = 0; i < chiffres.size(); i++)
            prepare(chiffres,i);
        
        
        plus.addClickListener(e->doAction(e));
        moins.addClickListener(e->doAction(e));
		fois.addClickListener(e->doAction(e));
		div.addClickListener(e->doAction(e));
		egal.addClickListener(e->doSet());
		ce.addClickListener(e->doClear());
		cos.addClickListener(e->trigonometrie(e)) ;
		sin.addClickListener(e->trigonometrie(e)) ;
		tan.addClickListener(e->trigonometrie(e)) ;
		inverse.addClickListener(e->inverse(e)) ;
         
    }
    
    public void inverse(ClickEvent<Button> e) {
		// TODO Auto-generated method stub
    	double r = Double.parseDouble(ecran.getText()) ;
    	try {
			if(r==0) {
				ecran.setText("Error");
			}
			else {
				result = 1/r ;
			}
		} catch (ArithmeticException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	ecran.setText(String.valueOf(result));
	}
    
    public void trigonometrie(ClickEvent e) {
		// TODO Auto-generated method stub
		double r = Double.parseDouble(ecran.getText()) ;
		if(e.getSource() == cos) {
			result = Math.cos(r) ;
		}
		else if(e.getSource() == sin) {
			result = Math.sin(r) ;
		}
		else {
			result = Math.tan(r) ;
		}
		ecran.setText(String.valueOf(result));
	}

	public void doAction(ClickEvent e) {
		
    	if(e.getSource() == plus) {
    		action = "+" ;
    		tmp = Double.parseDouble(ecran.getText());
    		ecran.setText("0");
		   
    	}
    	else if(e.getSource() == moins) {
    		action = "-" ;
    		tmp = Double.parseDouble(ecran.getText());
    		ecran.setText(String.valueOf(0));
		    
    	}
    	else if(e.getSource() == fois) {
    		action = "*" ;
    		tmp = Double.parseDouble(ecran.getText());
    		ecran.setText("0");
		    
    	}
    	else {
    		action = "/" ;
    		tmp = Double.parseDouble(ecran.getText());
    		ecran.setText(String.valueOf(0));
    	}
    	
	}
	
	public void doClear() {
		ecran.setText("0");
		result = 0;
	}

	public void doSet() {
		if(action == "+") {
		   result = Double.parseDouble(ecran.getText()) + tmp ;
		}
		else if(action == "-") {
			result = tmp - Double.parseDouble(ecran.getText()) ;
		}
		else if(action == "*") {
			result = tmp * Double.parseDouble(ecran.getText()) ;
		}
		else {
			result = tmp / Double.parseDouble(ecran.getText()) ;
		}
		ecran.setText(String.valueOf(result));
	}
    
	public void prepare(List<Button> chiffres, Integer i) {
    	
        Button btn = chiffres.get(i);
        btn.setText(i.toString());
        btn.addClickListener(this::addChiffre);
    }
	public void addChiffre(ClickEvent<Button> e) {
        String str = ((Button)e.getSource()).getText() ;
   	    if(!ecran.getText().equals("0.0")) {
            str = ecran.getText() + str;
   	    }
   	 ecran.setText(str);
     }
}
