package ru.geekbrains;

public class Triangle {
    public static float areaTriangle(int a, int b, int c) throws TriangleIsNotAvailable {
        if (a <= 0 | b <=0 | c <= 0)
            throw new TriangleIsNotAvailable();
        if ((a + b < c) | (a + c < b) | (b + c <a))
            throw new TriangleIsNotAvailable();
        float p = (a + b + c) / 2.0f;
        float s = (float) Math.sqrt(p*(p-a)*(p-b)*(p-c));
        return s;
    }

    public static void main(String[] args) throws TriangleIsNotAvailable {
        System.out.println(areaTriangle(1,1,3));
        System.out.println(areaTriangle(4, 3, 5));
    }
}
