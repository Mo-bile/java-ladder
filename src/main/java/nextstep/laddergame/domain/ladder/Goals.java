package nextstep.laddergame.domain.ladder;

import java.util.Arrays;
import java.util.List;

public record Goals(List<Goal> goalList) {

    public Goals(String[] goals) {
        this(Arrays.stream(goals).map(Goal::new).toList());
    }

}
