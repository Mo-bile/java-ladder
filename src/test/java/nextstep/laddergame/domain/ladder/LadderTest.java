package nextstep.laddergame.domain.ladder;

import static nextstep.laddergame.builder.LadderBuilder.aLadderBuilder;
import static nextstep.laddergame.builder.LadderBuilder.aLadderResult;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class LadderTest {

    @Test
    void 사다리_높이만큼_Line_을_생성한다() {
        Ladder ladder = new Ladder(3, 2, "꽝", "당첨");
        assertThat(ladder.lines().lineList().size()).isEqualTo(3);
    }

    @Test
    void lines와_golas의_수가_불일치하면_예외전파() {
        assertThatThrownBy(() -> {
            new Ladder(5, 3, "꽝");
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("사다리와 결과값이 다르다");
    }

    @Test
    void 생성된_사다리가_입력이_안되면_예외전파() {
        assertThatThrownBy(() -> {
            new Ladder(0, 0, "꽝");
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("생성된 사다리가 없습니다");
    }

    @Test
    void 목표에_대한_정보가_없으면_예외전파() {
        assertThatThrownBy(() -> {
            new Ladder(3, 5, "");
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("목표 지점에 대한 정보가 없습니다");
    }

    @Test
    void 특정참가자가_lines를_따라가서_0번_line이_마지막_golas_가_어딘지_나타낸다() {
        Ladder ladder = aLadderBuilder();

        assertThat(ladder.traverse(0)).isEqualTo(aLadderResult(2));
        assertThat(ladder.traverse(1)).isEqualTo(aLadderResult(1));
        assertThat(ladder.traverse(2)).isEqualTo(aLadderResult(0));
    }

}