package MyMath.numerical.difecv;

import MyMath.linear.Intervals.Interval;
import MyMath.linear.vecnew.VectorDouble;
import MyMath.linear.vecnew.VectorFunction;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.util.List;

/**
 * Created by Denis on 09.12.2016.
 */
public class PhasePortraitDrawer {

    private static final double LEFT_BOUNDARY = -6;
    private static final double RIGHT_BOUNDARY = 6;
    private VectorFunction system;
    private Comet comet;

    public PhasePortraitDrawer(VectorFunction system) {
        this.system = system;
        comet = new ODE1Comet();
    }

    public void draw() {
        XYChart chart =
                new XYChartBuilder().width(800).height(600)
                        .xAxisTitle("x").yAxisTitle("y")
                        .title("phase portrait").theme(Styler.ChartTheme.Matlab).build();
        chart.getStyler().setMarkerSize(1);
        VectorDouble start;
        Interval bnd = new Interval(new VectorDouble(-8.0, -8.0)
                , new VectorDouble(8.0, 8.0));
        List<VectorDouble> trace;
        double[][] arrays;
        String name;
        double step = 0.1;
        int chartId = 0;
        for (double i = bnd.getA().get(0); i <= bnd.getB().get(0); i += step) {
            start = new VectorDouble(i, bnd.getB().get(0));
            trace = comet.trace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);

            start = new VectorDouble(i, bnd.getB().get(1));
            trace = comet.trace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);

            start = new VectorDouble(i, bnd.getB().get(0));
            trace = comet.backTrace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);

            start = new VectorDouble(i, bnd.getB().get(1));
            trace = comet.backTrace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);
        }

        for (double i = bnd.getA().get(1); i < bnd.getB().get(1); i += step) {
            start = new VectorDouble(bnd.getA().get(1), i);
            trace = comet.trace(system, start, bnd);
            arrays = toArrays(trace);
            name =Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);

            start = new VectorDouble(bnd.getB().get(1), i);
            trace = comet.trace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);

            start = new VectorDouble(bnd.getA().get(1), i);
            trace = comet.backTrace(system, start, bnd);
            arrays = toArrays(trace);
            name =Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);

            start = new VectorDouble(bnd.getB().get(1), i);
            trace = comet.backTrace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);
        }

        new SwingWrapper(chart).displayChart();
    }

    public void draw(Interval bnd, double curveGap) {
        XYChart chart =
                new XYChartBuilder().width(800).height(600)
                        .xAxisTitle("x").yAxisTitle("y")
                        .title("phase portrait").theme(Styler.ChartTheme.Matlab).build();
        chart.getStyler().setMarkerSize(1);
        VectorDouble start;
        List<VectorDouble> trace;
        double[][] arrays;
        String name;
        int chartId = 0;
        for (double i = bnd.getA().get(0); i <= bnd.getB().get(0); i += curveGap) {
            start = new VectorDouble(i, bnd.getB().get(1));
            trace = comet.trace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);
            //backward
            trace = comet.backTrace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);
            //backward

            start = new VectorDouble(i, bnd.getA().get(1));
            trace = comet.trace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);
            //back
            trace = comet.backTrace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);
        }

        for (double i = bnd.getA().get(1); i < bnd.getB().get(1); i += curveGap) {
            start = new VectorDouble(bnd.getA().get(0), i);
            trace = comet.trace(system, start, bnd);
            arrays = toArrays(trace);
            name =Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);
            //back
            trace = comet.backTrace(system, start, bnd);
            arrays = toArrays(trace);
            name =Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);

            start = new VectorDouble(bnd.getB().get(0), i);
            trace = comet.backTrace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);
            //back
            trace = comet.backTrace(system, start, bnd);
            arrays = toArrays(trace);
            name = Integer.toString(chartId++);
            chart.addSeries(name, arrays[0], arrays[1]);
        }

        new SwingWrapper(chart).displayChart();
    }
        /*for (double i = -6; i <= 6; i++) {
            for (double j = -6; j <= 6 ; j++) {
                start = new VectorDouble(i, j);
                List<VectorDouble> trace = comet.trace(system, start, boundaries);
                *//*for (VectorDouble p: trace) {
                    System.out.println(p.toString());
                }*//*
                double[][] arrays = toArrays(trace);
                String name = Double.toString(i) + ", " + Double.toString(j);
                chart.addSeries(name, arrays[0], arrays[1]);
            }

        }*/


    public void simple() {
        VectorDouble start = new VectorDouble(0.0, 0.0);
        List<VectorDouble> trace = comet.trace(system, start, 100);
        for (VectorDouble p : trace) {
            System.out.println(p.toString());
        }
        double[][] arrays = toArrays(trace);
        XYChart chart = QuickChart.getChart("Simple chart", "X", "Y", "Y(X)", arrays[0], arrays[1]);
        new SwingWrapper(chart).displayChart();
    }

    private static double[][] toArrays(List<VectorDouble> data) {
        double[][] res;
        int length = data.size();
        int arraysCout = data.get(0).getDim();
        res = new double[arraysCout][length];
        int j = 0;
        for (VectorDouble v : data) {
            for (int i = 0; i < arraysCout; i++) {
                res[i][j] = v.get(i);
            }
            j++;
        }
        return res;
    }


    public VectorFunction getSystem() {
        return system;
    }

    public void setSystem(VectorFunction system) {
        this.system = system;
    }
}
