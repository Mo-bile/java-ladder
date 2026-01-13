package nextstep.laddergame.domain.ladder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import nextstep.laddergame.domain.ladder.linegenerator.LineGenerator;

public record Lines(List<Line> lineList) {

    public Lines(int height, LineGenerator generator) {
        this(IntStream.range(0, height).mapToObj(i -> generator.create()).toList());
    }

    public Lines(Line... lists) {
        this(Arrays.stream(lists).toList());
    }

}
