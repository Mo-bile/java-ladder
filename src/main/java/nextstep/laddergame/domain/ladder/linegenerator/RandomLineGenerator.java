package nextstep.laddergame.domain.ladder.linegenerator;

import static nextstep.laddergame.domain.ladder.Line.isAllPointsFalse;
import java.util.ArrayList;
import java.util.List;
import nextstep.laddergame.domain.ladder.Line;
import nextstep.laddergame.domain.ladder.Point;
import nextstep.laddergame.util.RandomUtil;

public class RandomLineGenerator implements LineGenerator {

    private final int countOfPerson;

    public RandomLineGenerator(int countOfPerson) {
        this.countOfPerson = countOfPerson;
    }

    @Override
    public Line create() {
        List<Point> points = new ArrayList<>();
        Point previous = new Point(false);

        for (int i = 0; i < this.countOfPerson; i++) {
            Point current = lineDecider(previous);
            points.add(current);
            previous = current;
        }
        if (isAllPointsFalse(points)) {
            points.set(chooseSomeIndex(points.size()), new Point(true));
        }
        return new Line(points);
    }

    private Point lineDecider(Point previous) {
        if (previous.connect()) {
            return new Point(false);
        }
        return new Point(RandomUtil.halfAndHalfBoolean());
    }

    public int chooseSomeIndex(int bound) {
        return RandomUtil.randomIndex(bound);
    }
}