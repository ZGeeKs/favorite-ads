package com.github.zgeeks.ads;

import org.immutables.value.Value;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Value.Immutable
public interface Ad {

    @Id
    String id();
    String title();
    String content();
    Instant createdDate();

    static Ad create(String id, String title, String content, Instant createDate) {
        return ImmutableAd.builder()
            .id(id)
            .title(title)
            .content(content)
            .createdDate(createDate)
            .build();
    }
}
