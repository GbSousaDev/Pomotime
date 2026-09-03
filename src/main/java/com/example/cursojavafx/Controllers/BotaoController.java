package com.example.cursojavafx.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class BotaoController {

    @FXML
    Button btnIniciar;

    @FXML
    RadioButton rb20;

    @FXML
    RadioButton rb30;

    @FXML
    RadioButton rb40;

    @FXML
    Label lblTempo; // <- confere se esse campo já existe no seu arquivo original; se não existir, é ele que está causando os erros de compilação

    @FXML
    public void timer() {

        int escolhaTemp;
        if (rb20.isSelected()) {
            escolhaTemp = 20;
        } else if (rb30.isSelected()) {
            escolhaTemp = 30;
        } else if (rb40.isSelected()) {
            escolhaTemp = 40;
        } else {
            System.out.println("ERRO! Marque algumas das opções antes de iniciar");
            return;
        }

        Temporizador temporizador = new Temporizador(escolhaTemp, lblTempo);
        Thread thread = new Thread(temporizador);
        thread.setDaemon(true);
        thread.start();
    }


    class Temporizador implements Runnable {
        private int minutos;
        private int segundos;
        private Label lblTempo;

        public Temporizador(int escolhaTemp, Label lblTempo) {
            this.minutos = escolhaTemp;
            this.segundos = 0;
            this.lblTempo = lblTempo;
        }

        @Override
        public void run() {
            System.out.println("Iniciando Temporizador. Bons Estudos!");
            temporizado();
        }

        void temporizado() {
            while (minutos > 0 || segundos > 0) {
                String formmint = String.format("%02d", minutos);
                String formseg = String.format("%02d", segundos);
                String tempoFinal = formmint + ":" + formseg;

                // Atualiza o Label na thread correta do JavaFX
                Platform.runLater(() -> lblTempo.setText(tempoFinal));

                if (segundos > 0) {
                    segundos--;
                } else {
                    minutos = minutos - 1;
                    segundos = 59;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }

            Platform.runLater(() -> lblTempo.setText("Tempo esgotado!"));
        }
    }
}