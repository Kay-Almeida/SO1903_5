package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinuxPing extends Thread {
	
    private String nomeServidor;
    private String enderecoServidor;
    private long tempoTotal = 0;
    private int contador = 0;

    public LinuxPing(String nomeServidor, String enderecoServidor) {
        this.nomeServidor = nomeServidor;
        this.enderecoServidor = enderecoServidor;
    }
	
    public long getTempoMedio() {
        if (contador == 0) return 0;
        return tempoTotal / contador;
    }
	
	public void run () {
        try {
            Process processo = Runtime.getRuntime().exec("ping -4 -c 10 " + enderecoServidor);
            BufferedReader leitor = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.contains("time=")) {
                    int index = linha.indexOf("time=");
                    String tempoStr = linha.substring(index + 5);
                    int tempo = Integer.parseInt(tempoStr.substring(0, tempoStr.indexOf(" ")));
                    System.out.println(nomeServidor + ": " + tempo + " ms");
                    tempoTotal += tempo;
                    contador++;
                }
            }
            processo.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
	}
}
