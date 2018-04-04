package com.codurance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SwimInRisingWaterTest {
    /*
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

[
[7, 11, 5,  3],
[2, 14, 12, 8],
[4, 13, 9, 10],
[6, 0,  1, 15]]

     */

    public int swimInWater(int[][] grid) {
        Graph graph = new Graph(grid);
        graph.swim();
        return graph.max;
    }


    static class Graph {
        private final int[][] grid;
        private final int width;
        private final int height;

        Set<Pos> visited = new HashSet<>();
        Pos currentPos = new Pos(0, 0);
        int max = 0;

        Graph(int[][] grid) {
            this.grid = grid;
            this.width = grid[0].length;
            this.height = grid.length;
        }

        void swim() {
            max = Math.max(max, value(currentPos));

            if (isEnd()) {
                return;
            }

            visited.add(currentPos);

            currentPos = findCheapestUnvisited();
            swim();
        }

        private Pos findCheapestUnvisited() {
            return findNeighbors().stream()
                .filter(n -> !visited.contains(n))
                .min(Comparator.comparingInt(this::value)).get();
        }

        private int value(Pos pos) {
            return grid[pos.row][pos.col];
        }

        private List<Pos> findNeighbors() {
            List<Pos> neighbors = new ArrayList<>();
            if (currentPos.col > 0) {
                neighbors.add(new Pos(currentPos.row, currentPos.col - 1));
            }
            if (currentPos.col < width - 1) {
                neighbors.add(new Pos(currentPos.row, currentPos.col + 1));
            }
            if (currentPos.row > 0) {
                neighbors.add(new Pos(currentPos.row - 1, currentPos.col));
            }
            if (currentPos.row < height - 1) {
                neighbors.add(new Pos(currentPos.row + 1, currentPos.col));
            }
            return neighbors;
        }

        private boolean isEnd() {
            return currentPos.row == grid.length - 1 && currentPos.col == grid[0].length - 1;
        }
    }

    static class Pos {
        private final int row;
        private final int col;

        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return row == pos.row &&
                col == pos.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return "Pos{" +
                "row=" + row +
                ", col=" + col +
                '}';
        }
    }

    @Test
    public void test_example_1() {
        int[][] grid = {{0, 2}, {1, 3}};
        assertThat(swimInWater(grid)).isEqualTo(3);
    }

    @Test
    public void test_example_2() {
        int[][] grid = {{0, 1, 2, 3, 4},
            {24, 23, 22, 21, 5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10, 9, 8, 7, 6}};

        assertThat(swimInWater(grid)).isEqualTo(16);
    }
}
