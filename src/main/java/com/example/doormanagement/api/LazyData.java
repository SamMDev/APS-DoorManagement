package com.example.doormanagement.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * Class to be extended for every lazy data response
 * @param <E>
 */
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class LazyData<E> {
    private Collection<E> data;
    private Long count;
    private Long offset;
    private Long limit;
}
