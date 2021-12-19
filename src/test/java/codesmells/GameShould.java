package codesmells;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class GameShould {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    public void not_allow_player_oto_play_first() {
        Throwable thrown = catchThrowable(() -> game.Play('O', 0, 0));

        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void not_allow_player_x_to_play_twice_in_a_row() throws Exception {
        game.Play('X', 0, 0);

        Throwable thrown = catchThrowable(() -> game.Play('X', 1, 0));

        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void not_allow_player_to_play_in_last_played_position() throws Exception {
        game.Play('X', 0, 0);

        Throwable thrown = catchThrowable(() -> game.Play('O', 0, 0));

        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void not_allow_player_to_play_in_any_played_position() throws Exception {
        game.Play('X', 0, 0);
        game.Play('O', 1, 0);

        Throwable thrown = catchThrowable(() -> game.Play('X', 0, 0));

        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void declare_player_x_as_a_winner_if_three_in_top_row() throws Exception {
        game.Play('X', 0, 0);
        game.Play('O', 1, 0);
        game.Play('X', 0, 1);
        game.Play('O', 1, 1);
        game.Play('X', 0, 2);

        char winner = game.Winner();

        assertThat(winner).isEqualTo('X');
    }

    @Test
    public void declare_player_o_as_a_winner_if_three_in_top_row() throws Exception {
        game.Play('X', 2, 2);
        game.Play('O', 0, 0);
        game.Play('X', 1, 0);
        game.Play('O', 0, 1);
        game.Play('X', 1, 1);
        game.Play('O', 0, 2);

        char winner = game.Winner();

        assertThat(winner).isEqualTo('O');
    }

    @Test
    public void declare_player_x_as_a_winner_if_three_in_middle_row() throws Exception {
        game.Play('X', 1, 0);
        game.Play('O', 0, 0);
        game.Play('X', 1, 1);
        game.Play('O', 0, 1);
        game.Play('X', 1, 2);

        char winner = game.Winner();

        assertThat(winner).isEqualTo('X');
    }

    @Test
    public void declare_player_o_as_a_winner_if_three_in_middle_row() throws Exception {
        game.Play('X', 0, 0);
        game.Play('O', 1, 0);
        game.Play('X', 2, 0);
        game.Play('O', 1, 1);
        game.Play('X', 2, 1);
        game.Play('O', 1, 2);

        char winner = game.Winner();

        assertThat(winner).isEqualTo('O');
    }

    @Test
    public void declare_player_x_as_a_winner_if_three_in_bottom_row() throws Exception {
        game.Play('X', 2, 0);
        game.Play('O', 0, 0);
        game.Play('X', 2, 1);
        game.Play('O', 0, 1);
        game.Play('X', 2, 2);

        char winner = game.Winner();

        assertThat(winner).isEqualTo('X');
    }

    @Test
    public void declare_player_o_as_a_winner_if_three_in_bottom_row() throws Exception {
        game.Play('X', 0, 0);
        game.Play('O', 2, 0);
        game.Play('X', 1, 0);
        game.Play('O', 2, 1);
        game.Play('X', 1, 1);
        game.Play('O', 2, 2);

        char winner = game.Winner();

        assertThat(winner).isEqualTo('O');
    }
}
