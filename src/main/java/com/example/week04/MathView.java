package com.example.week04;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Route(value = "/MathCalculator")
public class MathView extends FormLayout {
    private TextField inp1, inp2, otp;
    private Button plusBtn, minusBtn, divideBtn, multiBtn, modBtn, maxBtn;
    public MathView(){
        inp1 = new TextField("Number1");
        inp2 = new TextField("Number2");
        otp = new TextField("Answer");
        otp.setReadOnly(true);
        plusBtn = new Button("+");
        minusBtn = new Button("-");
        divideBtn = new Button("/");
        multiBtn = new Button("x");
        modBtn = new Button("mod");
        maxBtn = new Button("max");
        VerticalLayout v1 = new VerticalLayout();
        HorizontalLayout h1 = new HorizontalLayout();
        h1.add(plusBtn, minusBtn, multiBtn, divideBtn, modBtn, maxBtn);
        v1.add(inp1, inp2, h1, otp);
        this.add(v1);

        plusBtn.addClickListener(event->{
           double num1 = Double.parseDouble(inp1.getValue());
           double num2 = Double.parseDouble(inp2.getValue());
           String out = WebClient.create()
                   .get()
                   .uri("http://localhost:8080/plus/"+num1+"/"+num2)
                   .retrieve()
                   .bodyToMono(String.class)
                   .block();
           otp.setValue(out);
        });

        minusBtn.addClickListener(event->{
            double num1 = Double.parseDouble(inp1.getValue());
            double num2 = Double.parseDouble(inp2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/minus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            otp.setValue(out);
        });

        divideBtn.addClickListener(event->{
            double num1 = Double.parseDouble(inp1.getValue());
            double num2 = Double.parseDouble(inp2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/divide/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            otp.setValue(out);
        });

        multiBtn.addClickListener(event->{
            double num1 = Double.parseDouble(inp1.getValue());
            double num2 = Double.parseDouble(inp2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/multi/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            otp.setValue(out);
        });

        modBtn.addClickListener(event->{
            double num1 = Double.parseDouble(inp1.getValue());
            double num2 = Double.parseDouble(inp2.getValue());
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/mod/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            otp.setValue(out);
        });

        maxBtn.addClickListener(event->{
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("num1", inp1.getValue()); // key คือ n1, value คือ n1.getValue())
            formData.add("num2", inp2.getValue());
            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/max/")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            otp.setValue(out);
        });
    }

}
