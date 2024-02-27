package pers.nefedov.motiwaretestapp.models;

import java.util.Arrays;

public enum Condition {
    FORMING, APPROVING, REFINEMENT, IMPLEMENTATION, FINISHED;

    public static boolean isInEnum(String value) {
        return Arrays.stream(Condition.values()).anyMatch(e -> e.name().equals(value));
    }
}
