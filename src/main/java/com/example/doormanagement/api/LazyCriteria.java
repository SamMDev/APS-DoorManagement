package com.example.doormanagement.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * Model used for filtering and loading lazy data
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LazyCriteria {
    private Long offset;
    private Long limit;
    private Map<String, Object> filter;
}
