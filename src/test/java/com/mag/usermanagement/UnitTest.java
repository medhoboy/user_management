package com.mag.usermanagement;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import static java.time.LocalTime.now;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.MockitoAnnotations.initMocks;

@Tag("unit-test")
public abstract class UnitTest {

    protected EasyRandom random;

    protected abstract void setup();

    @BeforeEach
    void init() {
        initMocks(this);
        random = new EasyRandom(new EasyRandomParameters()
                .seed(now().toNanoOfDay())
                .randomize(String.class, () -> randomAlphabetic(12))
        );
        setup();
    }

}
