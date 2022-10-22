package com.example.doormanagement.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to be extended for every lazy data response
 * @param <E>
 */
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class LazyData<E> {
    private Iterable<E> data;
    private Long count;
    private Long offset;
    private Long limit;
}
