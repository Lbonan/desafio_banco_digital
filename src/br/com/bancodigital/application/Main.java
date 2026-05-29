package br.com.bancodigital.application;

import br.com.bancodigital.services.MenuService;

public class Main {
    public static void main(String[] args) {
        MenuService menu = new MenuService();
        menu.iniciar();
    }
}
