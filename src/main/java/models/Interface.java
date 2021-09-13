/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JFrame;

/**
 *
 * @author JOscar
 */
public class Interface extends JFrame{
    
    
    public Interface() {
        this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel p1 = new Panel(new GridLayout(1, 1, 10, 10));
        Panel p2 = new Panel(new GridLayout(6, 3, 10, 10));
        
        final TextField t1 = new TextField("");
        
        Button b1 = new Button("1");
        Button b2 = new Button("2");
        Button b3 = new Button("3");
        Button b4 = new Button("4");
        Button b5 = new Button("5");
        Button b6 = new Button("6");
        Button b7 = new Button("7");
        Button b8 = new Button("8");
        Button b9 = new Button("9");
        Button bPunto = new Button(".");
        Button b0 = new Button("0");
        Button bCE = new Button("CE");
        Button bMas = new Button("+");
        Button bMenos = new Button("-");
        Button bIgual = new Button("=");
        Button bMultiplicacion = new Button("*");
        Button bDivision = new Button("/");
        Button bC = new Button("C");
        
        p1.add(t1);
        
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p2.add(b5);
        p2.add(b6);
        p2.add(b7);
        p2.add(b8);
        p2.add(b9);
        p2.add(bPunto);
        p2.add(b0);
        p2.add(bCE);
        p2.add(bMas);
        p2.add(bMenos);
        p2.add(bIgual);
        p2.add(bMultiplicacion);
        p2.add(bDivision);
        p2.add(bC);
        
        setLayout(new BorderLayout());
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "1"); 
            }
        });
        
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "2"); 
            }
        });
        
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "3"); 
            }
        });
        
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "4"); 
            }
        });
        
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "5"); 
            }
        });
        
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "6"); 
            }
        });
        
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "7"); 
            }
        });
        
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "8"); 
            }
        });
        
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "9"); 
            }
        });
        
        bPunto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "."); 
            }
        });
        
        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "0"); 
            }
        });
        
        bCE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(""); 
            }
        });
        
        bMas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "+"); 
            }
        });
        
        bMenos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "-"); 
            }
        });
        
        bIgual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expresion = t1.getText();
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                try {
                    Object resultado = engine.eval(expresion);
                    t1.setText(resultado.toString());
                } catch (ScriptException ex) {
                    t1.setText("error");
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        bMultiplicacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "*"); 
            }
        });
        
        bDivision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(t1.getText() + "/"); 
            }
        });
        
        bC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText(""); 
            }
        });
        }
    
    public static void main(String[] args) {
        Interface calculadora = new Interface();
        calculadora.setVisible(true);
    }
}
