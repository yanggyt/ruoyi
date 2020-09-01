package com.ruoyi.common.func;

public enum IConstant {
        MINUS_ONE(-1),
        ZERO(0),
        ONE(1),
        TWO(2),
        THREE(3),
        FOURTH(4),
        FIFTH(5),
        SIXTH(6),
        SEVENTH(7),
        EIGHTH(8),
        NINTH(9),
        TENTH(10);

        private int value;

        private IConstant(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }