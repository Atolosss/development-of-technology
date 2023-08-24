package http.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum CityNumber {
    FIFTY(50),
    HUNDRED(100),
    FIFTY_HUNDRED(150);

    private final int value;

    public static CityNumber getByValue(final int value) {
        return Arrays.stream(values())
                .filter(cityNumber -> cityNumber.getValue() == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unexpected value: " + value));
    }
}
