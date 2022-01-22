package mirea.student.shayko;

public class Universe {
    private String[] stars;  // коллекция звёзд во Вселенной

    // Конструкторы:
    public Universe() {
        this(new String[] {});
    }

    public Universe(String[] values) {
        this.stars = new String[values.length];
        System.arraycopy(values, 0, this.stars, 0, values.length);
    }

    // Получить "сырой" массив stars:
    public String[] getStars() {
        String[] result = new String[this.stars.length];
        System.arraycopy(this.stars, 0, result, 0, this.stars.length);
        return result;
    }

    // Получить температуру всех звёзд в виде массива целых:
    public int[] getTemp() {
        int temp[] = new int[this.stars.length];
        for (int i = 0; i < this.stars.length; ++i) {
            int limit = stars[i].indexOf(':');
            String temp_str = "";
            for (int j = 0; j < limit; ++j)
                temp_str += stars[i].charAt(j);
            try {
                temp[i] = Integer.parseInt(temp_str);
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage() + "\n");
                System.out.println(ex.getStackTrace());
                temp[i] = -1;
            }
        }

        return temp;
    }

    // Получить массы всех звёзд в виде массива дробных (float):
    public float[] getMass() {
        float mass[] = new float[this.stars.length];
        for (int i = 0; i < this.stars.length; ++i) {
            int limit = stars[i].indexOf(':');
            int up_limit = stars[i].indexOf(';');
            String temp_str = "";
            for (int j = limit + 1; j < up_limit; ++j)
                temp_str += stars[i].charAt(j);
            try {
                mass[i] = Float.parseFloat(temp_str);
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage() + "\n");
                System.out.println(ex.getStackTrace());
                mass[i] = -1.0f;
            }
        }

        return mass;
    }

    // Получить массив кластеров каждой звезды:
    public int[] getClusters() {
        int[] clusters = new int[this.stars.length];

        for (int i = 0; i < this.stars.length; ++i) {
            int beg = this.stars[i].indexOf(';') + 1;
            try {
                clusters[i] = Integer.parseInt(this.stars[i].substring(beg));
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage() + "\n");
                System.out.println(ex.getStackTrace());
                clusters[i] = -1;
            }
        }

        return clusters;
    }

    // Вернуть число звёзд:
    public int count() {
        return this.stars.length;
    }

    // Изменить кластер звезды с индексом index:
    public void changeCluster(int index, int cluster_number) {
        if ((index < 0)||(index >= this.stars.length)) {
            System.out.println("Failed to change clusters! Wrong star index!");
            return;
        }

        String edit_star = this.stars[index];

        try {
            if (cluster_number == Integer.parseInt(edit_star.substring(edit_star.indexOf(';') + 1)))
                return;
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + "\n");
            System.out.println(ex.getStackTrace());
            System.out.println("Failed to change clusters! Cluster number error in stars data:\n" + (index + 1) + " star:" + edit_star);
        }

        String result_star = "" + edit_star.substring(0, edit_star.indexOf(';') + 1) + cluster_number;

        this.stars[index] = result_star;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < this.stars.length; ++i)
            result += i + ")\n\t" + "Temperature: " + getTemp()[i] + "\n\tMass: " + getMass()[i] + "\n\tCluster: " + getClusters()[i] + "\n\n";

        return result;
    }
}
