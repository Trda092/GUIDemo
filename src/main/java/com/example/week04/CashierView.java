package com.example.week04;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route("/Cashier")
public class CashierView extends VerticalLayout {
    private TextField change, b1k, b5h, b1h, b20, b10, b5, b1;
    private Button genChange;
    public CashierView(){
        change = new TextField("เงินทอน"); change.setPrefixComponent(new Div(new Text("$")));
        b1k = new TextField(); b1k.setPrefixComponent(new Div(new Text("$1000")));
        b5h = new TextField(); b5h.setPrefixComponent(new Div(new Text("$500")));
        b1h = new TextField(); b1h.setPrefixComponent(new Div(new Text("$100")));
        b20 = new TextField(); b20.setPrefixComponent(new Div(new Text("$20")));
        b10 = new TextField(); b10.setPrefixComponent(new Div(new Text("$10")));
        b5 = new TextField(); b5.setPrefixComponent(new Div(new Text("$5")));
        b1 = new TextField(); b1.setPrefixComponent(new Div(new Text("$1")));
        genChange = new Button("คำนวนเงินทอน");
        this.add(change, genChange, b1k, b5h, b1h, b20, b10, b5, b1);

        genChange.addClickListener(event->{
           int changed = Integer.parseInt(change.getValue());
            Change out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getChange/"+changed)
                    .retrieve()
                    .bodyToMono(Change.class)
                    .block();
            b1k.setValue(Integer.toString(out.getB1000()));
            b5h.setValue(Integer.toString(out.getB500()));
            b1h.setValue(Integer.toString(out.getB100()));
            b20.setValue(Integer.toString(out.getB20()));
            b10.setValue(Integer.toString(out.getB10()));
            b5.setValue(Integer.toString(out.getB5()));
            b1.setValue(Integer.toString(out.getB1()));
        });
    }
}
