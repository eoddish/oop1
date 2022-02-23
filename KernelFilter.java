/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.awt.Color;

public class KernelFilter {

    // Returns a new picture that applies a Gaussian blur filter to the given picture.
    public static Picture gaussian(Picture picture) {
        double[][] weights = new double[3][3];
        int m = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) m += 3;
                double lm = 1.0 * m / 16;
                weights[i][j] = lm;
                if (i == 1 && j == 1) m -= 3;
                m = (-m + 3);
            }
        }
        Picture res = kernel(picture, weights);
        return res;
    }


    // Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture) {
        Picture res = new Picture(picture.width(), picture.height());
        for (int i = 0; i < picture.height(); i++) {
            for (int j = 0; j < picture.width(); j++) {
                int r = 0;
                int g = 0;
                int b = 0;
                int m = 0;
                for (int k = i - 1; k < i + 2; k++) {
                    for (int l = j - 1; l < j + 2; l++) {
                        if (k == i && l == j) m += 5;
                        Color color1 = picture
                                .get(Math.abs(l) % (picture.width()),
                                     Math.abs(k) % (picture.height()));

                        r += (color1.getRed() * m);
                        g += (color1.getGreen() * m);
                        b += (color1.getBlue() * m);
                        if (k == i && l == j) m -= 5;
                        m = Math.abs(m) - 1;

                    }
                }

                if (r < 0) r = 0;
                if (r > 255) r = 255;
                if (g < 0) g = 0;
                if (g > 255) g = 255;
                if (b < 0) b = 0;
                if (b > 255) b = 255;

                Color color = new Color(r, g, b);


                res.set(j, i, color);

            }
        }
        return res;
    }

    // Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture) {
        double[][] weights = new double[3][3];
        int m = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) m += 9;

                weights[i][j] = m;
                if (i == 1 && j == 1) m -= 9;

            }
        }
        Picture res = kernel(picture, weights);
        return res;
    }


    // Returns a new picture that applies an emboss filter to the given picture.
    public static Picture emboss(Picture picture) {
        double[][] weights = { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } };
        Picture res = kernel(picture, weights);
        return res;
    }

    // Returns a new picture that applies a motion blur filter to the given picture.
    public static Picture motionBlur(Picture picture) {
        double[][] weights = new double[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j) weights[i][j] = 1.0 / 9;


            }
        }
        Picture res = kernel(picture, weights);
        return res;
    }

    // Returns a new picture that applies an arbitrary kernel filter to the given picture.
    private static Picture kernel(Picture picture, double[][] weights) {
        Picture res = new Picture(picture.width(), picture.height());
        for (int i = 0; i < picture.height(); i++) {
            for (int j = 0; j < picture.width(); j++) {
                double r = 0;
                double g = 0;
                double b = 0;
                int x = 0;
                int y = 0;
                for (int k = i - weights.length / 2; k <= i + weights.length / 2; k++) {
                    for (int l = j - weights.length / 2; l <= j + weights.length / 2; l++) {
                        Color color1 = picture
                                .get(Math.abs(l) % (picture.width()),
                                     Math.abs(k) % (picture.height()));

                        r += (color1.getRed() * weights[x][y]);
                        g += (color1.getGreen() * weights[x][y]);
                        b += (color1.getBlue() * weights[x][y]);

                        y = (y + 1) % weights.length;
                    }
                    x++;
                }

                int R = (int) Math.round(r);
                int G = (int) Math.round(g);
                int B = (int) Math.round(b);

                if (R < 0) R = 0;
                if (R > 255) R = 255;
                if (G < 0) G = 0;
                if (G > 255) G = 255;
                if (B < 0) B = 0;
                if (B > 255) B = 255;

                Color color = new Color(R, G, B);


                res.set(j, i, color);

            }
        }
        return res;
    }

    // Test client (ungraded).
    public static void main(String[] args) {
        String name = args[0];
        Picture picture = new Picture(name);
        Picture res = motionBlur(picture);
        res.show();

    }

}
