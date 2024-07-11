package com.gerardogutierrez.conversordemonedas.principal;

import com.gerardogutierrez.conversordemonedas.modelos.Divisas;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {

        List<Divisas> monedas = new ArrayList<>();
        monedas.add(new Divisas("USD", "Dolar Americano"));
        monedas.add(new Divisas("MXN", "Peso Mexicano"));
        monedas.add(new Divisas("EUR", "Euros"));
        monedas.add(new Divisas("GBP", "Libras Esterlinas"));
        monedas.add(new Divisas("COP", "Peso Colombiano"));
        monedas.add(new Divisas("BRL", "Real Brasileño"));


        try {
            Menu.deployMenu(monedas);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}

