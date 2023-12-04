package com.xm.utils;

public enum SliderValues {
    RECENT_AND_NEXT(0),
    TODAY(1),
    TOMORROW(2),
    THIS_WEEK(3),
    NEXT_WEEK(4),
    THIS_MONTH(5),
    NEXT_MONTH(6);

    final int orderOnSlider;

    SliderValues (int orderOnSlider) {
        this.orderOnSlider = orderOnSlider;
    }
}
