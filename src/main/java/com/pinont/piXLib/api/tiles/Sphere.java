package com.pinont.piXLib.api.tiles;

import org.bukkit.World;

public record Sphere(World world, int radius, int x, int y, int z) {

    public Float volume() {
        return (float) (4 / 3 * Math.PI * Math.pow(radius, 3));
    }

    public Float surfaceArea() {
        return (float) (4 * Math.PI * Math.pow(radius, 2));
    }

    public Float diameter() {
        return (float) (2 * radius);
    }

    public Float getCircumference() {
        return (float) (2 * Math.PI * radius);
    }

    public Float getArea() {
        return (float) (Math.PI * Math.pow(radius, 2));
    }

    public World getWorld() {
        return world;
    }

    public int getRadius() {
        return radius;
    }

    public int getMinX() {
        return x - radius;
    }

    public int getMaxX() {
        return x + radius;
    }

    public int getMinY() {
        return y - radius;
    }

    public int getMaxY() {
        return y + radius;
    }

    public int getMinZ() {
        return z - radius;
    }

    public int getMaxZ() {
        return z + radius;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int z() {
        return z;
    }

    public Boolean contains(int x, int y, int z) {
        return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) + Math.pow(z - this.z, 2) <= Math.pow(radius, 2);
    }

    public Boolean contains(Sphere sphere) {
        return Math.pow(sphere.x() - this.x, 2) + Math.pow(sphere.y() - this.y, 2) + Math.pow(sphere.z() - this.z, 2) <= Math.pow(radius, 2);
    }

    public Boolean contains(Cuboid cuboid) {
        return cuboid.contains(this);
    }

    public boolean overlaps(Sphere sphere) {
        return Math.pow(sphere.x() - this.x, 2) + Math.pow(sphere.y() - this.y, 2) + Math.pow(sphere.z() - this.z, 2) <= Math.pow(sphere.radius() + this.radius, 2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sphere sphere) {
            return sphere.radius() == radius && sphere.x() == x && sphere.y() == y && sphere.z() == z;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Sphere{World:" + world.getName() + "[" +
                "radius=" + radius +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                "]}";
    }
}