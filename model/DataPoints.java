package com.hf.model;

import java.util.List;

public enum DataPoints {
    Total {
        @Override
        public Object getValue(List<Events> list) {
            return (long) list.size();
        }
    },
    SumX {
        @Override
        public Object getValue(List<Events> list) {
            return list.stream().mapToDouble(Events::x).sum();
        }
    },
    AvgX {
        @Override
        public Object getValue(List<Events> list) {
            long count = (long) DataPoints.Total.getValue(list);
            if (count > 0) {
                return (double) DataPoints.SumX.getValue(list) / count;
            }
            return 0;
        }
    },
    SumY {
        @Override
        public Object getValue(List<Events> list) {
            return list.stream().mapToLong(Events::y).sum();
        }
    },
    AvgY {
        @Override
        public Object getValue(List<Events> list) {
            long count = (long) DataPoints.Total.getValue(list);
            if (count > 0) {
                return (long) DataPoints.SumY.getValue(list) / count;
            }
            return 0;
        }
    };

    public abstract Object getValue(List<Events> list);
}