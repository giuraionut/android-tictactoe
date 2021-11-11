package com.tictactoe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView playerScore, computerScore;
    private final Set<Button> buttons = new HashSet<>();
    private List<String> buttonsId = new ArrayList<>();
    Player player = new Player("player");
    Player computer = new Player("Computer");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView playerNameLabel = findViewById(R.id.player_label);
        this.playerScore = findViewById(R.id.player_score_value);
        this.computerScore = findViewById(R.id.computer_score_value);
        findViewById(R.id.button_restart).setOnClickListener(this);
        String username_input = getIntent().getStringExtra("username");
        if (!username_input.isEmpty())
            player.setName(username_input);

        playerNameLabel.setText(player.getName());

        player.setSymbol("X");
        computer.setSymbol("O");

        IntStream.range(0, 9).forEach(val -> buttonsId.add("button" + val));

        IntStream.range(0, 9)
                .forEach(val -> buttons
                        .add(findViewById(getResources().getIdentifier("button" + val, "id", getPackageName()))));
        buttons.forEach(button -> button.setOnClickListener(this));
    }

    public void restartGame() {
        new Handler(Looper.getMainLooper()).postDelayed(
                () -> buttons.forEach(button -> button.setText("")), 3000);

        this.buttonsId = new ArrayList<>();
        IntStream.range(0, 9).forEach(val -> buttonsId.add("button" + val));
        player.resetMoves();
        computer.resetMoves();
    }

    private boolean isWinner(String p, int pos) {

        if (p.equals("player")) {
            return checkPlayer(pos);
        }
        if (p.equals("computer")) {
            return checkComputer(pos);
        }

        return false;
    }

    public void showToast(String message) {
        Toast winnerToast = new Toast(this);
        winnerToast.setText(message);
        winnerToast.setDuration(Toast.LENGTH_LONG);
        winnerToast.show();
    }

    public boolean checkPlayer(int pos) {
        if (player.move(pos)) {
            this.playerScore.setText(String.format(Locale.getDefault(), "%d", player.getScore()));
            showToast("Player Won");
            restartGame();
            return true;
        } else
            return false;
    }

    public boolean checkComputer(int pos) {
        if (computer.move(pos)) {
            this.computerScore.setText(String.format(Locale.getDefault(), "%d", computer.getScore()));
            showToast("Computer Won");
            restartGame();
            return true;
        } else
            return false;
    }

    @Override
    public void onClick(View v) {

        if (((Button) v).getText().toString().equals("Restart")) {
            restartGame();
            return;
        }
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        Button clickedButton = (Button) v;
        String clickedButtonId = clickedButton.getResources().getResourceEntryName(clickedButton.getId());
        clickedButton.setText(player.getSymbol());
        buttonsId.remove(clickedButtonId);
        int position = Integer.parseInt(String.valueOf(clickedButtonId.charAt(clickedButtonId.length() - 1)));
        if (isWinner("player", position)) {
            restartGame();
            return;
        }
        if (buttonsId.isEmpty()) {
            showToast("No Winner");
            restartGame();
            return;
        }
        computerMove();

    }

    private void computerMove() {
        Collections.shuffle(buttonsId);
        int clickedButtonByComputerId = getResources().getIdentifier(buttonsId.get(0), "id", getPackageName());
        Button computerButton = findViewById(clickedButtonByComputerId);
        computerButton.setText(computer.getSymbol());
        String clickedButtonId = computerButton.getResources().getResourceEntryName(computerButton.getId());
        int position = Integer.parseInt(String.valueOf(buttonsId.get(0).charAt(clickedButtonId.length() - 1)));
        if (isWinner("computer", position)) {
            restartGame();
            return;
        }
        buttonsId.remove(0);
    }
}