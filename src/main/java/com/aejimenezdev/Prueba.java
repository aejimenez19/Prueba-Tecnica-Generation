package com.aejimenezdev;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/*
 ==============================================================
  PRUEBA TÉCNICA DE LÓGICA · Desarrollador/a Junior · IPSUM Technology
 ==============================================================
  Tiempo sugerido: 45 minutos
  Cómo ejecutar:   java Prueba.java     (Java 11 o superior)

  Instrucciones:
  - Implementa los 5 métodos de la sección "TU CÓDIGO".
  - No uses librerías externas. Puedes crear métodos auxiliares.
  - Al ejecutar verás qué pruebas pasan (✅) y cuáles fallan (❌).
  - Se evalúa que funcione, pero también claridad, nombres y manejo
    de casos borde. Si no terminas un ejercicio, deja comentado
    cómo lo resolverías.

  EJERCICIO 1 · esPalindromo(texto)
    Retorna true si el texto se lee igual al derecho y al revés,
    ignorando mayúsculas, espacios y signos de puntuación.
    Ej: "Anita lava la tina" -> true

  EJERCICIO 2 · segundoMayor(numeros)
    Retorna el segundo número más grande DISTINTO del arreglo.
    Si no existe, retorna null. No ordenes el arreglo (reto: una pasada).
    Ej: {3, 7, 2, 7, 5} -> 5

  EJERCICIO 3 · contarEstados(estados)
    Recibe una lista de estados de tickets de soporte y retorna un
    Map con cuántos hay de cada uno. Normaliza: ignora mayúsculas
    y espacios al inicio/final.
    Ej: ["Abierto", "cerrado ", "abierto"] -> {abierto=2, cerrado=1}

  EJERCICIO 4 · estaBalanceado(texto)
    Retorna true si los paréntesis (), corchetes [] y llaves {} están
    correctamente abiertos y cerrados. Ignora cualquier otro carácter.
    Ej: "([]{})" -> true   |   "([)]" -> false

  EJERCICIO 5 · fusionarIntervalos(intervalos)
    Recibe franjas de mantenimiento {inicio, fin} (pueden venir
    desordenadas) y retorna las franjas fusionadas cuando se solapan
    o se tocan, ordenadas por inicio.
    Ej: {{1,3},{2,6},{8,10}} -> {{1,6},{8,10}}
 ==============================================================
*/

public class Prueba {

    // ===================== TU CÓDIGO =====================

    public static boolean esPalindromo(String texto) {
        texto = texto.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String invertido = new StringBuilder(texto).reverse().toString();
        System.out.println("invertido: " + invertido);
        return texto.equals(invertido);
    }

    public static Integer segundoMayor(int[] numeros) {
        if (numeros.length < 2) return null;
        int mayor = numeros[0];
        Integer segundoMayor = null;
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > mayor) {
                segundoMayor = mayor;
                mayor = numeros[i];
            } else if (numeros[i] < mayor && (segundoMayor == null || numeros[i] > segundoMayor)) {
                segundoMayor = numeros[i];
            }
        }
        return segundoMayor;
    }

    public static Map<String, Integer> contarEstados(List<String> estados) {
        List<String> estadosNormalizados = estados.stream()
                .filter(Objects::nonNull)
                .map(e -> e.trim().replaceAll("[^a-zA-Z0-9]", "").toLowerCase())
                .toList();


        Map<String, Integer> reporte = new HashMap<>();

        reporte.put("abierto", (int) estadosNormalizados.stream().filter(e -> e.equals("abierto")).count());
        reporte.put("cerrado", (int) estadosNormalizados.stream().filter(e -> e.equals("cerrado")).count());
        reporte.put("en progreso", (int) estadosNormalizados.stream().filter(e -> e.equals("enprogreso")).count());
        return reporte;
    }

    public static boolean estaBalanceado(String texto) {
        throw new UnsupportedOperationException("TODO");
    }

    public static int[][] fusionarIntervalos(int[][] intervalos) {
        throw new UnsupportedOperationException("TODO");
    }

    // ===================== NO MODIFICAR DESDE AQUÍ =====================

    static int ok = 0, total = 0;

    static String str(Object o) {
        if (o instanceof int[][]) return Arrays.deepToString((int[][]) o);
        if (o instanceof Map) return new TreeMap<>((Map<?, ?>) o).toString();
        return String.valueOf(o);
    }

    static void check(String nombre, Supplier<Object> fn, Object esperado) {
        total++;
        Object obtenido;
        try {
            obtenido = fn.get();
        } catch (Exception e) {
            obtenido = "Error: " + e.getMessage();
        }
        boolean pasa = (esperado instanceof int[][] && obtenido instanceof int[][])
                ? Arrays.deepEquals((int[][]) esperado, (int[][]) obtenido)
                : Objects.equals(esperado, obtenido);
        if (pasa) ok++;
        System.out.println((pasa ? "✅ " : "❌ ") + nombre +
                (pasa ? "" : "\n     esperado: " + str(esperado) + "\n     obtenido: " + str(obtenido)));
    }

    public static void main(String[] args) {
        /*System.out.println("\n— Ejercicio 1 —");
        check("palíndromo simple", () -> esPalindromo("Anita lava la tina"), true);
        check("no palíndromo", () -> esPalindromo("Hola"), false);
        check("con puntuación", () -> esPalindromo("A man, a plan, a canal: Panama"), true);
        check("cadena vacía", () -> esPalindromo(""), true);

               System.out.println("\n— Ejercicio 2 —");
                check("con repetidos", () -> segundoMayor(new int[]{3, 7, 2, 7, 5}), 5);
                check("todos iguales", () -> segundoMayor(new int[]{4, 4, 4}), null);
                check("un solo elemento", () -> segundoMayor(new int[]{10}), null);
                check("negativos", () -> segundoMayor(new int[]{-1, -5, -3}), -3);*/

        System.out.println("\n— Ejercicio 3 —");
        check("conteo básico", () -> contarEstados(List.of("abierto", "cerrado", "abierto", "en progreso")),
                Map.of("abierto", 2, "cerrado", 1, "en progreso", 1));
        check("normaliza", () -> contarEstados(List.of("Abierto ", "  abierto", "CERRADO")),
                Map.of("abierto", 2, "cerrado", 1));
        check("lista vacía", () -> contarEstados(List.of()), Map.of());

                /*System.out.println("\n— Ejercicio 4 —");
                check("balanceado", () -> estaBalanceado("([]{})"), true);
                check("cruzado", () -> estaBalanceado("([)]"), false);
                check("sin cerrar", () -> estaBalanceado("((("), false);
                check("cierra de más", () -> estaBalanceado("())"), false);
                check("con texto", () -> estaBalanceado("a(b)c{d}"), true);
                check("vacío", () -> estaBalanceado(""), true);

                System.out.println("\n— Ejercicio 5 —");
                check("solapados", () -> fusionarIntervalos(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}),
                        new int[][]{{1, 6}, {8, 10}, {15, 18}});
                check("se tocan", () -> fusionarIntervalos(new int[][]{{1, 4}, {4, 5}}), new int[][]{{1, 5}});
                check("desordenados", () -> fusionarIntervalos(new int[][]{{5, 7}, {1, 2}}), new int[][]{{1, 2}, {5, 7}});
                check("contenido", () -> fusionarIntervalos(new int[][]{{1, 10}, {2, 3}}), new int[][]{{1, 10}});
                check("vacío", () -> fusionarIntervalos(new int[0][]), new int[0][]);

                System.out.println("\nResultado: " + ok + "/" + total + " pruebas\n");*/
    }
}
