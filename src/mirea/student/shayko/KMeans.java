package mirea.student.shayko;

import java.util.*;

public class KMeans {
    public Universe sample;
    public final int CLUSTERS_NUM = 6;

    private int[] centers;

    public KMeans() {
        this(new Universe());
    }

    public KMeans(Universe uni) {
        sample = uni;
        this.centers = this.init();

        int[] temperature = sample.getTemp();
        float[] mass = sample.getMass();
        for (int i = 0; i < this.centers.length; ++i)
            if (temperature[centers[i]] < 10000) {  // красные звёзды...
                if (mass[centers[i]] < 4000.0)
                    sample.changeCluster(centers[i], 0);  // красная звезда-долгожитель
                else if (mass[centers[i]] < 8000.0)
                    sample.changeCluster(centers[i], 1);  // красная звезда-среднежитель
                else if (mass[centers[i]] <= 12000.0)
                    sample.changeCluster(centers[i], 2);  // красная звезда с относительно непродолжительным жизненным циклом
            } else {  // голубые звёзды...
                if (mass[centers[i]] < 4000.0)
                    sample.changeCluster(centers[i], 3);  // голубая звезда-долгожитель
                else if (mass[centers[i]] < 8000.0)
                    sample.changeCluster(centers[i], 4);  // голубая звезда-среднежитель
                else if (mass[centers[i]] <= 12000.0)
                    sample.changeCluster(centers[i], 5);  // голубая звезда с относительно непродолжительным жизненным циклом
            }
    }

    public int[] getCenters() {
        int[] result = new int[CLUSTERS_NUM];
        System.arraycopy(this.centers, 0, result, 0, this.centers.length);

        return result;
    }

    // k-means++ (see https://ru.wikipedia.org/wiki/K-means%2B%2B)
    // Определить все начальные центроиды алгоритма (улучшенным способом, а не просто случайной выборкой):
    private int[] init() {
        Random random = new Random();  // генератор псевдослучайных
        int[] centers = new int[CLUSTERS_NUM];  // массив с индексами изначальных центроидов алгоритма k-means
        Arrays.fill(centers, -2);
        int counter = 0;  // счётчик заполнения массива (см. выше) новыми центроидами
        centers[counter++] = random.nextInt(sample.count()-1);  // первый центроид выбирается случайным образом


        int[] temperatures = sample.getTemp();  // массив температур звёзд
        float[] masses = sample.getMass();  // массив масс звёзд Вселенной
        int[] clusters = sample.getClusters();  // массив кластеров каждой звезды

        // Поэтапно определить все необходимые центроиды:
        while (counter < this.CLUSTERS_NUM) {
            double[] min_distances = new double[sample.count()];  // расстояние каждой точки до её ближайшего центроида
            double[][] distances = new double[sample.count()][sample.count()];  // расстояние каждой точки до всех центроидов (определённых на данный момент)
            double sum = 0;  // сумма расстояний до ближайших центроидов (понадобится в конце цикла)
            for (int i = 0; i < temperatures.length; ++i) {
                double min = -1;  // минимальное расстояние среди всех расстояний у текущей точки (точка = звезда)
                for (int j = 0; j < counter; ++j) {
                    distances[i][j] = (temperatures[centers[j]] - temperatures[i]) * (temperatures[centers[j]] - temperatures[i]) + (masses[centers[j]] - masses[i]) * (masses[centers[j]] - masses[i]);
                    if (min == -1)
                        min = distances[i][j];
                    else if (min > distances[i][j])
                        min = distances[i][j];

                }
                min_distances[i] = min;
                sum += min;
            }

            // Выбрать следующий случайный центроид, опираясь уже на проставленные (см. выше) вероятности выбора:
            double rnd = random.nextDouble() * sum;
            sum = 0;

            boolean flag = false;
            for (int i = 0; i < temperatures.length; ++i) {
                sum += min_distances[i];
                if (sum > rnd) {
                    centers[counter++] = i;
                    break;
                }
            }
        }

        return centers;
    }

    private double V(double[][] centers) {
        int[] temperatures = sample.getTemp();  // массив температур звёзд
        float[] masses = sample.getMass();  // массив масс звёзд Вселенной
        int[] clusters = sample.getClusters();  // массив кластеров каждой звезды
        double sum = 0;

        for (int i = 0; i < centers.length; ++i)
            for (int j = 0; j < sample.count(); ++j) {
                if (clusters[j] == i)
                    sum += (centers[i][0] - masses[j]) * (centers[i][0] - masses[j]) + (centers[i][1] - temperatures[j]) * (centers[i][1] - temperatures[j]);
            }

        return sum;
    }

    public void run() {
        int[] temperatures = sample.getTemp();  // массив температур звёзд
        float[] masses = sample.getMass();  // массив масс звёзд Вселенной
        int[] clusters = sample.getClusters();  // массив кластеров каждой звезды

        for (int i = 0; i < sample.count(); ++i)
            if (clusters[i] == -1) {
                double distance;  // расстояние текущей точки до следующего центроида
                double min = -1;  // минимальное расстояние среди всех расстояний у текущей точки (точка = звезда)
                for (int j = 0; j < this.centers.length; ++j) {
                    distance = (temperatures[this.centers[j]] - temperatures[i]) * (temperatures[this.centers[j]] - temperatures[i]) + (masses[this.centers[j]] - masses[i]) * (masses[this.centers[j]] - masses[i]);
                    if (min == -1) {
                        min = distance;
                        sample.changeCluster(i, clusters[this.centers[j]]);
                    }
                    else if (min > distance) {
                        min = distance;
                        sample.changeCluster(i, clusters[this.centers[j]]);
                    }
                }
            }

        double v = -1;
        double v_past = -1;
        while (true) {
            v_past = v;

            double[][] centers = new double[this.CLUSTERS_NUM][3];
            clusters = sample.getClusters();
            for (int i = 0; i < this.CLUSTERS_NUM; ++i) {
                double temp_sum = 0;
                double mass_sum = 0;
                int counter = 0;

                for (int j = 0; j < this.sample.count(); ++j) {
                    if (clusters[j] == i) {
                        temp_sum += temperatures[j];
                        mass_sum += masses[j];
                        ++counter;
                    }
                }

                if (counter == 0) {
                    centers[i][0] = -10000;
                    centers[i][1] = -10000;
                    continue;
                }
                centers[i][0] = mass_sum/counter;
                centers[i][1] = temp_sum/counter;
                centers[i][2] = i;

                System.out.println("i = " + i + "\t( " + centers[i][0] + " ; " + centers[i][1] + " )\t" + "cluster: " + centers[i][2]);
            }

            for (int i = 0; i < sample.count(); ++i) {
                double distance;  // расстояние текущей точки до следующего центроида
                double min = -1;  // минимальное расстояние среди всех расстояний у текущей точки (точка = звезда)
                for (int j = 0; j < centers.length; ++j) {
                    distance = (centers[j][1] - temperatures[i]) * (centers[j][1] - temperatures[i]) + (centers[j][0] - masses[i]) * (centers[j][0] - masses[i]);
                    if (min == -1) {
                        min = distance;
                        sample.changeCluster(i, (int)centers[j][2]);
                    }
                    else if (min > distance) {
                        min = distance;
                        sample.changeCluster(i, (int)centers[j][2]);
                    }
                }
            }

            v = V(centers);
            System.out.println("v = " + v);
            if (v_past == v)
                break;
        }
    }

}
