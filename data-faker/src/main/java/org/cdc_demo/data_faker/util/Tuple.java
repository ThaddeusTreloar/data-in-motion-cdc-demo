package org.cdc_demo.data_faker.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class Tuple<X, Y> { 
    public final X x; 
    public final Y y; 
} 