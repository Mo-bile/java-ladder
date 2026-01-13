package nextstep.laddergame.domain.ladder.linegenerator;

import static org.assertj.core.api.Assertions.assertThat;
import nextstep.laddergame.domain.ladder.Line;
import nextstep.laddergame.domain.ladder.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RandomLineGeneratorTest {

    @Test
    void 참가한_사람_수_만큼_가로_라인을_생성한다() {
        assertThat(new RandomLineGenerator(5).create().points()).hasSize(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    void true_개수는_인접금지_규칙상_최대치를_넘지않는다(int countOfPerson) {
        Line line = new RandomLineGenerator(5).create();
        int maxTrue = (line.points().size() + 1) / 2;

        assertThat(
            line.points().stream()
                .filter(Point::connect)
                .count()
        ).isLessThanOrEqualTo(maxTrue);
    }

}