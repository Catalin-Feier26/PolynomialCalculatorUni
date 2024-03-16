package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GraphicInterface extends JFrame{
    private final JFrame calculatorFrame;
    private JPanel calculatorPanel;
    private JTextField polinom1;
    private JTextField polinom2;
    private JTextField result;
    private JTextField remainder;

    public GraphicInterface()
    {
        Color emerald = new Color(0, 77, 36);

        calculatorFrame=new JFrame("Calculator");
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setSize(720,720);
        calculatorFrame.setLayout(new BorderLayout());

        JButton plusButton=new JButton("add +");
        JButton subButton=new JButton("sub -");
        JButton multButton= new JButton("mult *");
        JButton divButton= new JButton("div /");
        JButton derivButton=new JButton("deriv d/dx");
        JButton intButton=new JButton("integrate");

        Polynomial polynomial1=new Polynomial();
        Polynomial polynomial2=new Polynomial();

        plusButton.addActionListener(e -> {
            String input1 = polinom1.getText();
            String input2 = polinom2.getText();
            if (!Polynomial.checkValidInput(input1)) {
                new CustomPopUp(calculatorFrame, "First polynomial is invalid", emerald);
                return;
            }
            if (!Polynomial.checkValidInput(input2)) {
                new CustomPopUp(calculatorFrame, "Second polynomial is invalid.", emerald);
                return;
            }
            polynomial1.readPolynomial(input1);
            polynomial2.readPolynomial(input2);
            Polynomial opResult = Operation.addition(polynomial1, polynomial2);
            result.setText(opResult.toString());
            remainder.setText("No remainder");
        });

        subButton.addActionListener(e -> {
            String input1 = polinom1.getText();
            String input2 = polinom2.getText();
            if (!Polynomial.checkValidInput(input1)) {
                new CustomPopUp(calculatorFrame, "First polynomial is invalid", emerald);
                return;
            }
            if (!Polynomial.checkValidInput(input2)) {
                new CustomPopUp(calculatorFrame, "Second polynomial is invalid.", emerald);
                return;
            }
            polynomial1.readPolynomial(input1);
            polynomial2.readPolynomial(input2);
            Polynomial opResult = Operation.subtraction(polynomial1, polynomial2);
            result.setText(opResult.toString());
            remainder.setText("No remainder");
        });

        multButton.addActionListener(e -> {
            String input1=polinom1.getText();
            String input2=polinom2.getText();
            if(!Polynomial.checkValidInput(input1))
            {
                new CustomPopUp(calculatorFrame, "First polynomial is invalid", emerald);
                return;
            }
            if(!Polynomial.checkValidInput(input2))
            {
                new CustomPopUp(calculatorFrame, "Second polynomial is invalid", emerald);
                return;
            }
            polynomial1.readPolynomial(input1);
            polynomial2.readPolynomial(input2);
            Polynomial multResult=Operation.multiplication(polynomial1,polynomial2);
            result.setText(multResult.toString());
            remainder.setText("No remainder");
        });
        divButton.addActionListener(e -> {
            String input1=polinom1.getText();
            String input2=polinom2.getText();
            if(!Polynomial.checkValidInput(input1))
            {
                new CustomPopUp(calculatorFrame, "First polynomial is invalid", emerald);
                return;
            }
            if(!Polynomial.checkValidInput(input2))
            {
                new CustomPopUp(calculatorFrame, "Second polynomial is invalid", emerald);
                return;
            }
            polynomial1.readPolynomial(input1);
            polynomial2.readPolynomial(input2);
            if(polynomial1.getDegree()<polynomial2.getDegree())
            {
                new CustomPopUp(calculatorFrame, "The degree of the first polynomial MUST be higher or equal than the second", emerald);
                return;
            }
            if(polynomial1.getDegree()>=polynomial2.getDegree()) {
                Polynomial[] res = Operation.division(polynomial1, polynomial2);
                result.setText(res[0].toString());
                remainder.setText(res[1].toString());
            }
        });

        derivButton.addActionListener(e -> {
            new CustomPopUp(calculatorFrame, "Enter the polynomial only in the first textBox", emerald);
            String input1=polinom1.getText();
            if(!Polynomial.checkValidInput(input1))
            {
                new CustomPopUp(calculatorFrame, "Polynomial is invalid", emerald);
                return;
            }
            polynomial1.readPolynomial(input1);
            Polynomial res=Operation.derivation(polynomial1);
            result.setText(res.toString());
            remainder.setText("No remainder");
        });
        intButton.addActionListener(e -> {
            new CustomPopUp(calculatorFrame, "Enter a polynomial only in the first textBox", emerald);
            String input1=polinom1.getText();
            if(!Polynomial.checkValidInput(input1))
            {
                new CustomPopUp(calculatorFrame, "Polynomial is invalid", emerald);
                return;
            }
            polynomial1.readPolynomial(input1);
            Polynomial res=Operation.integration(polynomial1);
            result.setText(res.toString());
            remainder.setText("No remainder");
        });

        calculatorPanel= createCalculatorPanel(plusButton,subButton,multButton,divButton,derivButton,intButton);
        calculatorFrame.add(calculatorPanel, BorderLayout.CENTER);
        calculatorFrame.setVisible(true);
    }

    private JPanel createCalculatorPanel(JButton plus, JButton sub, JButton mult, JButton div, JButton deriv, JButton inte) {

        Font tnr = new Font("Times New Roman", Font.PLAIN, 20);
        Dimension buttonSize = new Dimension(125, 25);
        Color emerald = new Color(0, 77, 36);

        String placeholder = "Enter a polynomial...";
        polinom1 = new JTextField(placeholder, 25);
        polinom2 = new JTextField(placeholder, 25);
        polinom1.setBackground(Color.WHITE);
        polinom1.setForeground(emerald);
        polinom1.setFont(tnr);

        polinom2.setBackground(Color.WHITE);
        polinom2.setFont(tnr);
        polinom2.setForeground(emerald);

        setTextfields(placeholder, polinom1);
        setTextfields(placeholder, polinom2);

        result=new JTextField("Result/Quotient",25);
        result.setBackground(Color.white);
        result.setFont(tnr);
        result.setForeground(emerald);
        result.setEditable(false);

        remainder=new JTextField("Remainder",25);
        remainder.setBackground(Color.white);
        remainder.setFont(tnr);
        remainder.setForeground(emerald);
        remainder.setEditable(false);


        JLabel poly1 = new JLabel("Polynomial1");
        poly1.setForeground(new Color(255, 255, 255));
        poly1.setFont(tnr);

        setProperties(plus, sub, mult, tnr, buttonSize, emerald);
        setProperties(div, deriv, inte, tnr, buttonSize, emerald);

        JLabel poly2 = new JLabel("Polynomial2");
        poly2.setForeground(new Color(255, 255, 255));
        poly2.setFont(tnr);

        JLabel poly3=new JLabel("Result/Quotient");
        poly3.setFont(tnr);
        poly3.setForeground(Color.white);

        JLabel poly4=new JLabel("Remainder");
        poly4.setFont(tnr);
        poly4.setForeground(Color.white);


        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(emerald);

        GridBagConstraints gbcCalculator = new GridBagConstraints();
        gbcCalculator.insets = new Insets(10, 10, 10, 10);
        gbcCalculator.anchor = GridBagConstraints.CENTER;

        gbcCalculator.gridx = 0;
        gbcCalculator.gridy = 0;
        panel.add(poly1, gbcCalculator);

        gbcCalculator.gridx = 1;
        gbcCalculator.gridy = 0;
        panel.add(polinom1, gbcCalculator);

        gbcCalculator.gridx = 0;
        gbcCalculator.gridy = 1;
        panel.add(poly2, gbcCalculator);

        gbcCalculator.gridx = 1;
        gbcCalculator.gridy = 1;
        panel.add(polinom2, gbcCalculator);

        gbcCalculator.gridx=0;
        gbcCalculator.gridy=2;
        panel.add(poly3,gbcCalculator);

        gbcCalculator.gridx=1;
        gbcCalculator.gridy=2;
        panel.add(result,gbcCalculator);

        gbcCalculator.gridx=0;
        gbcCalculator.gridy=3;
        panel.add(poly4,gbcCalculator);

        gbcCalculator.gridx=1;
        gbcCalculator.gridy=3;
        panel.add(remainder,gbcCalculator);

        gbcCalculator.gridx = 0;
        gbcCalculator.gridy = 4;
        panel.add(plus, gbcCalculator);

        gbcCalculator.gridx = 1;
        gbcCalculator.gridy = 4;
        panel.add(sub, gbcCalculator);

        gbcCalculator.gridx = 2;
        gbcCalculator.gridy = 4;
        panel.add(mult, gbcCalculator);

        gbcCalculator.gridx = 0;
        gbcCalculator.gridy = 5;
        panel.add(div, gbcCalculator);

        gbcCalculator.gridx = 1;
        gbcCalculator.gridy = 5;
        panel.add(deriv, gbcCalculator);

        gbcCalculator.gridx = 2;
        gbcCalculator.gridy = 5;
        panel.add(inte, gbcCalculator);

        return panel;
    }

    private void setTextfields(String placeholder, JTextField polinom1) {
        polinom1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (polinom1.getText().equals(placeholder)) {
                    polinom1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (polinom1.getText().isEmpty()) {
                    polinom1.setText(placeholder);
                }
            }
        });
    }

    private void setProperties(JButton plus, JButton sub, JButton mult, Font tnr, Dimension buttonSize, Color emerald) {
        plus.setFont(tnr);
        plus.setBackground(Color.WHITE);
        plus.setPreferredSize(buttonSize);
        plus.setForeground(emerald);

        sub.setFont(tnr);
        sub.setBackground(Color.WHITE);
        sub.setPreferredSize(buttonSize);
        sub.setForeground(emerald);

        mult.setFont(tnr);
        mult.setBackground(Color.WHITE);
        mult.setPreferredSize(buttonSize);
        mult.setForeground(emerald);
    }
}
