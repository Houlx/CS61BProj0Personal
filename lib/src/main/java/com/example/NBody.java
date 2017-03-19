package com.example;

public class NBody {
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        double result = 0;
        if (!in.isEmpty()) {
            in.readInt();
            result = in.readDouble();
            in.close();
            return result;
        }
        in.close();
        return result;
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
        Planet[] planets = new Planet[5];
        in.readLine();
        in.readLine();
        for (int i = 0; i < planets.length; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        Double T = Double.parseDouble(args[0]);
        Double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double universeRadius = readRadius(fileName);
        Planet[] planets = readPlanets(fileName);

        StdDraw.clear();
        StdDraw.setScale(-universeRadius, universeRadius);
        double time = 0;
        double xForce[] = new double[5];
        double yForce[] = new double[5];

        while (time < T) {
            for (int i = 0; i < 5; i++) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < 5; i++) {
                planets[i].update(dt, xForce[i], yForce[i]);
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, "./source/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show(10);
            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel, planet.yyVel, planet.mass, planet.imgFileName);
        }

    }
}
