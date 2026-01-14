package nextstep.laddergame.domain.ladder;

import java.util.List;
import java.util.stream.IntStream;

public record Line(List<Point> points) {

    public Line(boolean... values) {
        this(IntStream.range(0, values.length).mapToObj(i -> new Point(values[i])).toList());
    }

    public Line {
        validate(points);
    }

    private void validate(List<Point> points) {
        if (isAllPointsFalse(points)) {
            throw new IllegalArgumentException("모든 라인이 false일 수 없습니다");
        }

        if (IntStream.range(1, points.size()).anyMatch(i -> points.get(i - 1).connect() && points.get(i).connect())) {
            throw new IllegalArgumentException("인접라인이 서로 true일수 없습니다");
        }

    }

    public static boolean isAllPointsFalse(List<Point> points) {
        return points.stream().noneMatch(Point::connect);
    }

    public int nextPosition(int position) {
        if (isRightConnection(position)) {
            return position + 1;
        }
        if (isLeftConnection(position)) {
            return position - 1;
        }
        return position;
    }

    private boolean isRightConnection(int line) {
        if (endOfRight(line)) {
            return false;
        }

        return points.get(line).connect();
    }

    private boolean isLeftConnection(int line) {
        if (endOfLeft(line)) {
            return false;
        }
        return points.get(line - 1).connect();
    }

    private boolean endOfLeft(int line) {
        return line < 1;
    }

    private boolean endOfRight(int line) {
        return line >= points.size() - 1;
    }

    public int size() {
        return points.size();
    }

    @Override
    public String toString() {
        return "Line{" +
            "points=" + points +
            '}';
    }
}
