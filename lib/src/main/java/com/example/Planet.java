package com.example;

/**
 * powered by hou. 2017.3
 */
public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet planet) {
        return Math.sqrt(Math.pow(this.xxPos - planet.xxPos, 2) + Math.pow(this.yyPos - planet.yyPos, 2));
    }

    public double calcForceExertedBy(Planet planet) {
        return 6.67e-11 * this.mass * planet.mass / Math.pow(this.calcDistance(planet), 2);
    }

    public double calcForceExertedByX(Planet planet) {
        return calcForceExertedBy(planet) / calcDistance(planet) * (planet.xxPos - this.xxPos);
    }

    public double calcForceExertedByY(Planet planet) {
        return calcForceExertedBy(planet) / calcDistance(planet) * (planet.yyPos - this.yyPos);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double result = 0;
        for (Planet planet : planets) {
            if (!planet.equals(this)) {
                result += this.calcForceExertedByX(planet);
            }
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double result = 0;
        for (Planet planet : planets) {
            if (!planet.equals(this)) {
                result += this.calcForceExertedByY(planet);
            }
        }
        return result;
    }

    public void update(double dt, double fX, double fY) {
        xxVel += dt * (fX / mass);
        yyVel += dt * (fY / mass);
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    void draw() {
        StdDraw.picture(xxPos, yyPos, "./source/" + this.imgFileName);
    }

}