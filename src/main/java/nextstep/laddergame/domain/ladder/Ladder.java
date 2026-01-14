package nextstep.laddergame.domain.ladder;

import java.util.stream.Stream;
import nextstep.laddergame.domain.ladder.linegenerator.RandomLineGenerator;

public record Ladder(Lines lines, Goals goals) {

    public Ladder(String ladderHeight, int countOfPerson) {
        this(Integer.parseInt(ladderHeight), countOfPerson);
    }

    public Ladder(String ladderHeight, int countOfPerson, String goals) {
        this(Integer.parseInt(ladderHeight), countOfPerson, goals.split(","));
    }

    public Ladder(int ladderHeight, int countOfPerson) {
        this(getLines(ladderHeight, countOfPerson), Stream.generate(() -> "").limit(countOfPerson).toArray(String[]::new));
    }

    public Ladder(int ladderHeight, int countOfPerson, String... goals) {
        this(getLines(ladderHeight, countOfPerson), getGoals(goals));
    }

    private static Goals getGoals(String[] goals) {
        return new Goals(goals);
    }

    private static Lines getLines(int ladderHeight, int countOfPerson) {
        return new Lines(ladderHeight, new RandomLineGenerator(countOfPerson));
    }

    public Ladder(Lines lines, String... goals) {
        this(lines, getGoals(goals));
    }

    public Ladder {
        validate(lines, goals);
    }

    private void validate(Lines lines, Goals goals) {
        if (lines.lineList().isEmpty()) {
            throw new IllegalArgumentException("생성된 사다리가 없습니다");
        }

        if (goals.goalList().stream().anyMatch(goal -> goal.value().isEmpty())) {
            throw new IllegalArgumentException("목표 지점에 대한 정보가 없습니다");
        }

        if (lines.lineList().getFirst().size() != goals.goalList().size()) {
            throw new IllegalArgumentException("사다리와 결과값이 다르다");
        }
    }

    public String traverse(int startPosition) {
        int currentPosition = startPosition;

        for (Line line : lines.lineList()) {
            currentPosition = line.nextPosition(currentPosition);
        }

        return goals.goalList().get(currentPosition).value();
    }

}
