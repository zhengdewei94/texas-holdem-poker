package com.zdw.poker.exception;

import java.util.function.Supplier;

/**
 * 计算异常
 *
 * @author zheng
 */
public class ComputeException extends RuntimeException implements Supplier<ComputeException> {

    public ComputeException(String message) {
        super(message);
    }

    @Override
    public ComputeException get() {
        return new ComputeException("计算异常");
    }
}
