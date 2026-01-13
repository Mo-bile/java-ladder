package nextstep.laddergame.domain.ladder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class LineTest {

    @Test
    void 모든_라인이_false_로_생성되면_예외전파() {
        assertThatThrownBy(() -> {
            new Line(false, false, false, false, false);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("모든 라인이 false일 수 없습니다");
    }

    @Test
    void 인접이_서로_true_로_생성되면_예외전파() {
        assertThatThrownBy(() -> {
            new Line(false, true, true, false, false);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("인접라인이 서로 true일수 없습니다");
    }

    @Test
    void 해당_line이_왼쪽으로_이동가능한지_검증하고_가능하면_왼쪽_이동하는_값을_전달한다() {
        Line line = new Line(false, false, true, false, false);

        assertThat(line.nextPosition(3)).isEqualTo(2);
    }

    @Test
    void 해당_line이_오른쪽으로_이동가능한지_검증하고_가능하면_오른쪽_이동하는_값을_전달한다() {
        Line line = new Line(false, false, true, false, false);

        assertThat(line.nextPosition(2)).isEqualTo(3);
    }

}