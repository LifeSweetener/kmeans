package mirea.student.shayko;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {

    public static void main(String[] args) {
        String[] stars = new String[18];
        stars[0] = "1000:1000.0;-1"; stars[1] = "2050:1400.0;-1"; stars[2] = "3000:2500.0;-1";
        stars[3] = "2100:5600.0;-1"; stars[4] = "5050:6000.0;-1"; stars[5] = "3000:6900.0;-1";
        stars[6] = "4500:11500.0;-1"; stars[7] = "5050:12000.0;-1"; stars[8] = "7000:10800.0;-1";
        stars[9] = "18100:10350.0;-1"; stars[10] = "18800:11110.0;-1"; stars[11] = "16300:12000.0;-1";
        stars[12] = "10000:7000.0;-1"; stars[13] = "13000:7300.0;-1"; stars[14] = "15500:7900.0;-1";
        stars[15] = "13700:1500.0;-1"; stars[16] = "15000:2100.0;-1"; stars[17] = "16000:3700.0;-1";

        KMeans kmeans = new KMeans(new Universe(stars));

        XYSeries series = new XYSeries("Universe");

        int[] temperatures = kmeans.sample.getTemp();
        float[] masses = kmeans.sample.getMass();

        System.out.println(kmeans.sample);

        kmeans.run();

        System.out.println(kmeans.sample);

        for(int i = 0; i < kmeans.sample.count(); ++i) {
            series.add(masses[i], temperatures[i]);
        }

        XYDataset xyDataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory
                .createScatterPlot("Stars", "MASS", "TEMPERATURE",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);

        JFrame frame =
                new JFrame("Clusterization");
        frame.getContentPane()
                .add(new ChartPanel(chart));

        frame.setSize(400,300);
        frame.show();
    }
}
