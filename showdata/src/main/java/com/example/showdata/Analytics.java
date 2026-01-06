package com.example.showdata;
import org.springframework.stereotype.Component;

@Component
public class Analytics {
    private double max;
    private double min;
    private double average;
    private double count;

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Analytics{" +
                "max=" + max +
                ", min=" + min +
                ", average=" + average +
                ", count=" + count +
                '}';
    }
}
