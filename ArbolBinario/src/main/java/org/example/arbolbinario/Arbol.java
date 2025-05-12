package org.example.arbolbinario;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

class Arbol {
    private Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) return new Nodo(valor);
        if (valor < nodo.valor) nodo.izquierdo = insertarRecursivo(nodo.izquierdo, valor);
        else if (valor > nodo.valor) nodo.derecho = insertarRecursivo(nodo.derecho, valor);
        return nodo;
    }

    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor, "Raíz") != null;
    }

    private Nodo buscarRecursivo(Nodo nodo, int valor, String ubicacion) {
        if (nodo == null) return null;
        if (nodo.valor == valor) {
            JOptionPane.showMessageDialog(null, "Valor encontrado en: " + ubicacion);
            return nodo;
        }

        if (valor < nodo.valor) return buscarRecursivo(nodo.izquierdo, valor, ubicacion + " -> Izquierdo");
        else return buscarRecursivo(nodo.derecho, valor, ubicacion + " -> Derecho");
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private Nodo eliminarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) return null;

        if (valor < nodo.valor) nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, valor);
        else if (valor > nodo.valor) nodo.derecho = eliminarRecursivo(nodo.derecho, valor);
        else {
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho == null) return nodo.izquierdo;

            Nodo sucesor = obtenerMinimo(nodo.derecho);
            nodo.valor = sucesor.valor;
            nodo.derecho = eliminarRecursivo(nodo.derecho, sucesor.valor);
        }

        return nodo;
    }

    private Nodo obtenerMinimo(Nodo nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    public Nodo getRaiz() { return raiz; }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int obtenerPeso() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.izquierdo) + contarNodos(nodo.derecho);
    }

    public int obtenerAltura() {
        return altura(raiz);
    }

    private int altura(Nodo nodo) {
        if (nodo == null) return 0;
        int izq = altura(nodo.izquierdo);
        int der = altura(nodo.derecho);
        return 1 + Math.max(izq, der);
    }

    public int obtenerNivel(int valor) {
        return nivel(raiz, valor, 1);
    }

    private int nivel(Nodo nodo, int valor, int nivelActual) {
        if (nodo == null) return 0;
        if (nodo.valor == valor) return nivelActual;

        int nivelIzq = nivel(nodo.izquierdo, valor, nivelActual + 1);
        if (nivelIzq != 0) return nivelIzq;

        return nivel(nodo.derecho, valor, nivelActual + 1);
    }

    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) return 0;
        if (nodo.izquierdo == null && nodo.derecho == null) return 1;
        return contarHojasRecursivo(nodo.izquierdo) + contarHojasRecursivo(nodo.derecho);
    }

    public int obtenerNodoMayor() {
        if (raiz == null) return -1;
        Nodo actual = raiz;
        while (actual.derecho != null) actual = actual.derecho;
        return actual.valor;
    }

    public int obtenerNodoMenor() {
        if (raiz == null) return -1;
        Nodo actual = raiz;
        while (actual.izquierdo != null) actual = actual.izquierdo;
        return actual.valor;
    }

    public String imprimirAmplitud() {
        if (raiz == null) return "Árbol vacío";

        StringBuilder sb = new StringBuilder();
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            sb.append(actual.valor).append(" ");
            if (actual.izquierdo != null) cola.add(actual.izquierdo);
            if (actual.derecho != null) cola.add(actual.derecho);
        }
        return sb.toString();
    }

    public void borrarArbol() {
        raiz = null;
    }

    // Recorridos (Inorden, Preorden, Postorden)
    public String recorridoInorden() {
        StringBuilder sb = new StringBuilder();
        recorridoInorden(raiz, sb);
        return sb.toString();
    }

    private void recorridoInorden(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            recorridoInorden(nodo.izquierdo, sb);
            sb.append(nodo.valor).append(" ");
            recorridoInorden(nodo.derecho, sb);
        }
    }

    public String recorridoPreorden() {
        StringBuilder sb = new StringBuilder();
        recorridoPreorden(raiz, sb);
        return sb.toString();
    }

    private void recorridoPreorden(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            sb.append(nodo.valor).append(" ");
            recorridoPreorden(nodo.izquierdo, sb);
            recorridoPreorden(nodo.derecho, sb);
        }
    }

    public String recorridoPostorden() {
        StringBuilder sb = new StringBuilder();
        recorridoPostorden(raiz, sb);
        return sb.toString();
    }

    private void recorridoPostorden(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            recorridoPostorden(nodo.izquierdo, sb);
            recorridoPostorden(nodo.derecho, sb);
            sb.append(nodo.valor).append(" ");
        }
    }
}

