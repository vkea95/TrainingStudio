package jian.system.rateLimiter;

import com.revinate.guava.util.concurrent.RateLimiter;

import java.time.ZonedDateTime;

public class RateLimiters {
    //    @Test
    public void givenLimitedResource_whenRequestOnce_thenShouldPermitWithoutBlocking() {
// given
        RateLimiter rateLimiter = RateLimiter.create(100);

// when
        long startTime = ZonedDateTime.now().getSecond();
        rateLimiter.acquire(100);
//doSomeLimitedOperation();
        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;

// then
//assertThat(elapsedTimeSeconds <= 1);
    }
}
