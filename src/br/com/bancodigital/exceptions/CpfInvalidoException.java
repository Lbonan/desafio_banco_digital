package br.com.bancodigital.exceptions;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(String msg) {
        super(msg);
    }

}
