package com.denisspec.adcmodule.service;

import java.io.IOException;

public interface ConverterService<T> {
    public <V> T convert(V input) throws IOException;
}
