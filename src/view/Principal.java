package view;

import controller.LinuxPing;

public class Principal {
	
	public static void main(String[] args) {
        LinuxPing uolThread = new LinuxPing("UOL", "www.uol.com.br");
        LinuxPing terraThread = new LinuxPing("Terra", "www.terra.com.br");
        LinuxPing googleThread = new LinuxPing("Google", "www.google.com.br");

        uolThread.start();
        terraThread.start();
        googleThread.start();

        try {
            uolThread.join();
            terraThread.join();
            googleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Resultados:");
        System.out.println("UOL: Tempo médio = " + uolThread.getTempoMedio() + " ms");
        System.out.println("Terra: Tempo médio = " + terraThread.getTempoMedio() + " ms");
        System.out.println("Google: Tempo médio = " + googleThread.getTempoMedio() + " ms");
    }
	}
	
	
