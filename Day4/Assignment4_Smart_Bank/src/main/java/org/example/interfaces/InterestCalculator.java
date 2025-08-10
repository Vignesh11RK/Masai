package org.example.interfaces;

@FunctionalInterface
public interface InterestCalculator {
     double calculate(double principal, double rate, int years);
}
