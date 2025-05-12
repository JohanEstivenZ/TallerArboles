package org.example.arbolbinario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArbolBinario extends JFrame implements ActionListener {
    private Arbol arbol;
    private JTextField txtValor;

    public ArbolBinario() {
        arbol = new Arbol();
        setTitle("Árbol Binario GUI");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelControl = new JPanel();
        JLabel lblValor = new JLabel("Valor:");
        txtValor = new JTextField(5);

        String[] botones = {
                "Insertar", "Buscar", "Eliminar",
                "Inorden", "Preorden", "Postorden",
                "Esta Vacio", "Peso", "Altura",
                "Nivel", "Hojas", "Nodo Menor", "Nodo Mayor",
                "Amplitud", "Borrar Árbol"
        };

        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.addActionListener(this);
            panelControl.add(boton);
        }

        panelControl.add(lblValor);
        panelControl.add(txtValor);
        add(panelControl, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            int valor = 0;
            if (command.equals("Insertar") || command.equals("Buscar") || command.equals("Eliminar") || command.equals("Nivel"))
                valor = Integer.parseInt(txtValor.getText());

            switch (command) {
                case "Insertar" -> arbol.insertar(valor);
                case "Buscar" -> arbol.buscar(valor);
                case "Eliminar" -> arbol.eliminar(valor);
                case "Inorden" -> JOptionPane.showMessageDialog(this, "Inorden: " + arbol.recorridoInorden());
                case "Preorden" -> JOptionPane.showMessageDialog(this, "Preorden: " + arbol.recorridoPreorden());
                case "Postorden" -> JOptionPane.showMessageDialog(this, "Postorden: " + arbol.recorridoPostorden());
                case "Esta Vacio" -> JOptionPane.showMessageDialog(this, arbol.estaVacio() ? "El árbol está vacío" : "El árbol NO está vacío");
                case "Peso" -> JOptionPane.showMessageDialog(this, "Peso (nodos): " + arbol.obtenerPeso());
                case "Altura" -> JOptionPane.showMessageDialog(this, "Altura: " + arbol.obtenerAltura());
                case "Nivel" -> JOptionPane.showMessageDialog(this, "Nivel del nodo " + valor + ": " + arbol.obtenerNivel(valor));
                case "Hojas" -> JOptionPane.showMessageDialog(this, "Número de hojas: " + arbol.contarHojas());
                case "Nodo Menor" -> JOptionPane.showMessageDialog(this, "Nodo menor: " + arbol.obtenerNodoMenor());
                case "Nodo Mayor" -> JOptionPane.showMessageDialog(this, "Nodo mayor: " + arbol.obtenerNodoMayor());
                case "Amplitud" -> JOptionPane.showMessageDialog(this, "Amplitud: " + arbol.imprimirAmplitud());
                case "Borrar Árbol" -> { arbol.borrarArbol(); JOptionPane.showMessageDialog(this, "Árbol borrado"); }
            }

            repaint();
            txtValor.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido.");
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        dibujarArbol(g, arbol.getRaiz(), getWidth() / 2, 100, 40, getWidth() / 4);
    }

    private void dibujarArbol(Graphics g, Nodo nodo, int x, int y, int yOffset, int xOffset) {
        if (nodo == null) return;

        g.setColor(Color.BLACK);
        if (nodo.izquierdo != null) g.drawLine(x, y, x - xOffset, y + yOffset);
        if (nodo.derecho != null) g.drawLine(x, y, x + xOffset, y + yOffset);

        g.setColor(Color.BLACK);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(nodo.valor), x - 5, y + 5);

        dibujarArbol(g, nodo.izquierdo, x - xOffset, y + yOffset, yOffset, xOffset / 2);
        dibujarArbol(g, nodo.derecho, x + xOffset, y + yOffset, yOffset, xOffset / 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArbolBinario().setVisible(true));
    }
}


