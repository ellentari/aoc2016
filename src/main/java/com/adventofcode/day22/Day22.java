package com.adventofcode.day22;

import com.adventofcode.common.TokenInfo;
import com.adventofcode.common.Tokenizer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

/**
 * --- Day 22: Grid Computing ---
 * <p>
 * You gain access to a massive storage cluster arranged in a grid; each storage node is only connected to the four
 * nodes directly adjacent to it (three if the node is on an edge, two if it's in a corner).
 * <p>
 * You can directly access data only on node /dev/grid/node-x0-y0, but you can perform some limited actions on the
 * other nodes:
 * <p>
 * You can get the disk usage of all nodes (via df). The result of doing this is in your puzzle input.
 * You can instruct a node to move (not copy) all of its data to an adjacent node (if the destination node has enough
 * space to receive the data). The sending node is left empty after this operation.
 * Nodes are named by their position: the node named node-x10-y10 is adjacent to nodes node-x9-y10, node-x11-y10,
 * node-x10-y9, and node-x10-y11.
 * <p>
 * Before you begin, you need to understand the arrangement of data on these nodes. Even though you can only move
 * data between directly connected nodes, you're going to need to rearrange a lot of the data to get access to the
 * data you need. Therefore, you need to work out how you might be able to shift data around.
 * <p>
 * To do this, you'd like to count the number of viable pairs of nodes. A viable pair is any two nodes (A,B),
 * regardless of whether they are directly connected, such that:
 * <p>
 * Node A is not empty (its Used is not zero).
 * Nodes A and B are not the same node.
 * The data on node A (its Used) would fit on node B (its Avail).
 * How many viable pairs of nodes are there?
 * <p>
 * --- Part Two ---
 * <p>
 * Now that you have a better understanding of the grid, it's time to get to work.
 * <p>
 * Your goal is to gain access to the data which begins in the node with y=0 and the highest x
 * (that is, the node in the top-right corner).
 * <p>
 * For example, suppose you have the following grid:
 * <p>
 * Filesystem            Size  Used  Avail  Use%
 * /dev/grid/node-x0-y0   10T    8T     2T   80%
 * /dev/grid/node-x0-y1   11T    6T     5T   54%
 * /dev/grid/node-x0-y2   32T   28T     4T   87%
 * /dev/grid/node-x1-y0    9T    7T     2T   77%
 * /dev/grid/node-x1-y1    8T    0T     8T    0%
 * /dev/grid/node-x1-y2   11T    7T     4T   63%
 * /dev/grid/node-x2-y0   10T    6T     4T   60%
 * /dev/grid/node-x2-y1    9T    8T     1T   88%
 * /dev/grid/node-x2-y2    9T    6T     3T   66%
 * In this example, you have a storage grid 3 nodes wide and 3 nodes tall. The node you can access directly,
 * node-x0-y0, is almost full. The node containing the data you want to access, node-x2-y0 (because it has y=0
 * and the highest x value), contains 6 terabytes of data - enough to fit on your node, if only you could make
 * enough space to move it there.
 * <p>
 * Fortunately, node-x1-y1 looks like it has enough free space to enable you to move some of this data around.
 * In fact, it seems like all of the nodes have enough space to hold any node's data (except node-x0-y2,
 * which is much larger, very full, and not moving any time soon). So, initially, the grid's capacities and
 * connections look like this:
 * <p>
 * ( 8T/10T) --  7T/ 9T -- [ 6T/10T]
 * |           |           |
 * 6T/11T  --  0T/ 8T --   8T/ 9T
 * |           |           |
 * 28T/32T  --  7T/11T --   6T/ 9T
 * The node you can access directly is in parentheses; the data you want starts in the node marked by square brackets.
 * <p>
 * In this example, most of the nodes are interchangable: they're full enough that no other node's data would fit,
 * but small enough that their data could be moved around. Let's draw these nodes as ..
 * The exceptions are the empty node, which we'll draw as _, and the very large, very full node,
 * which we'll draw as #. Let's also draw the goal data as G. Then, it looks like this:
 * <p>
 * (.) .  G
 * .  _  .
 * #  .  .
 * The goal is to move the data in the top right, G, to the node in parentheses. To do this, we can issue some
 * commands to the grid and rearrange the data:
 * <p>
 * Move data from node-y0-x1 to node-y1-x1, leaving node node-y0-x1 empty:
 * <p>
 * (.) _  G
 * .  .  .
 * #  .  .
 * Move the goal data from node-y0-x2 to node-y0-x1:
 * <p>
 * (.) G  _
 * .  .  .
 * #  .  .
 * At this point, we're quite close. However, we have no deletion command, so we have to move some more data around.
 * So, next, we move the data from node-y1-x2 to node-y0-x2:
 * <p>
 * (.) G  .
 * .  .  _
 * #  .  .
 * Move the data from node-y1-x1 to node-y1-x2:
 * <p>
 * (.) G  .
 * .  _  .
 * #  .  .
 * Move the data from node-y1-x0 to node-y1-x1:
 * <p>
 * (.) G  .
 * _  .  .
 * #  .  .
 * Next, we can free up space on our node by moving the data from node-y0-x0 to node-y1-x0:
 * <p>
 * (_) G  .
 * .  .  .
 * #  .  .
 * Finally, we can access the goal data by moving the it from node-y0-x1 to node-y0-x0:
 * <p>
 * (G) _  .
 * .  .  .
 * #  .  .
 * So, after 7 steps, we've accessed the data we want. Unfortunately, each of these moves takes time, and we need to
 * be efficient:
 * <p>
 * What is the fewest number of steps required to move your goal data to node-x0-y0?
 */
public class Day22 {

    public static int solvePart1(String input) {
        List<NodeInGrid> nodeInGrids = parseNodesInGrid(input);

        int viablePairsCount = 0;

        for (NodeInGrid nodeInGridA : nodeInGrids) {
            Node nodeA = nodeInGridA.node;

            for (NodeInGrid nodeInGridB : nodeInGrids) {
                Node nodeB = nodeInGridB.node;

                if (isViablePair(nodeA, nodeB)) {
                    viablePairsCount++;
                }
            }
        }

        return viablePairsCount;
    }

    private static boolean isViablePair(Node a, Node b) {
        return a != b && !a.isEmpty() && b.fits(a.used);
    }

    public static int solvePart2(String input) {
        Node[][] grid = parseNodesGrid(input);

        IJ me = new IJ(0, 0);
        IJ goal = new IJ(0, grid[0].length - 1);

        char[][] scheme = buildScheme(grid, me, goal);

        int emptyToTargetMovesCount = getEmptyToTargetMovesCount(scheme, new IJ(0, goal.j - 1));
        // it takes 5 moves to shift from ._G to _G. ; total of (grid width - 2) times
        int shiftGoalToTheLeftCount = 5 * (grid[0].length - 2);

        return emptyToTargetMovesCount + shiftGoalToTheLeftCount + 1;
    }

    private static  char[][] buildScheme(Node[][] grid, IJ me, IJ goal) {
        char[][] scheme = new char[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].used == 0) {
                    scheme[i][j] = '_';
                } else if (me.equals(new IJ(i, j))) {
                    scheme[i][j] = '!';
                }  else if (goal.equals(new IJ(i, j))) {
                    scheme[i][j] = 'G';
                } else if (!canMoveDataToNeighbours(grid, i, j)) {
                    scheme[i][j] = '#';
                } else {
                    scheme[i][j] = '.';
                }
            }
        }

        return scheme;
    }

    private static boolean canMoveDataToNeighbours(Node[][] grid, int fromI, int fromJ) {
        Node from = grid[fromI][fromJ];

        if (fromI > 0) {
            Node to = grid[fromI - 1][fromJ];
            if (to.size < from.used) {
                return false;
            }
        }

        if (fromJ > 0) {
            Node to = grid[fromI][fromJ - 1];
            if (to.size < from.used) {
                return false;
            }
        }

        if (fromI < grid.length - 1) {
            Node to = grid[fromI + 1][fromJ];
            if (to.size < from.used) {
                return false;
            }
        }

        if (fromJ < grid[fromI].length - 1) {
            Node to = grid[fromI][fromJ + 1];
            if (to.size < from.used) {
                return false;
            }
        }

        return true;
    }

    private static int getEmptyToTargetMovesCount(char[][] scheme, IJ target) {
        int[][] movesCount = new int[scheme.length][scheme[0].length];
        boolean[][] visited = new boolean[scheme.length][scheme[0].length];

        Queue<IJ> toVisit = new ArrayDeque<>();
        toVisit.add(findEmptyPosition(scheme));

        IJ current;
        do {
            current = toVisit.poll();

            if (!visited[current.i][current.j]) {
                List<IJ> neighbours = getPossibleIJsToMove(current, scheme, visited);
                for (IJ neighbour : neighbours) {
                    movesCount[neighbour.i][neighbour.j] = movesCount[current.i][current.j] + 1;
                }
                toVisit.addAll(neighbours);
                visited[current.i][current.j] = true;
            }
        } while (!current.equals(target));

        return movesCount[target.i][target.j];
    }

    private static List<IJ> getPossibleIJsToMove(IJ current, char[][] scheme, boolean[][] visited) {
        List<IJ> neighbours = new ArrayList<>(4);
        neighbours.add(new IJ(current.i + 1, current.j));
        neighbours.add(new IJ(current.i - 1, current.j));
        neighbours.add(new IJ(current.i, current.j + 1));
        neighbours.add(new IJ(current.i, current.j - 1));

        return neighbours.stream()
                .filter(ij -> ij.i >= 0 && ij.j >= 0 && ij.i < scheme.length && ij.j < scheme[ij.i].length)
                .filter(ij -> !visited[ij.i][ij.j])
                .filter(ij -> scheme[ij.i][ij.j] != '#')
                .collect(toList());
    }

    private static IJ findEmptyPosition(char[][] scheme) {
        for (int i = 0; i < scheme.length; i++) {
            for (int j = 0; j < scheme[i].length; j++) {
                if (scheme[i][j] == '_') {
                    return new IJ(i, j);
                }
            }
        }

        return null;
    }

    private static Node[][] parseNodesGrid(String input) {
        List<NodeInGrid> nodesInGrid = parseNodesInGrid(input);

        NodeInGrid lastNodeInGrid = nodesInGrid.get(nodesInGrid.size() - 1);

        Node[][] grid = new Node[lastNodeInGrid.y + 1][lastNodeInGrid.x + 1];

        for (NodeInGrid nodeInGrid : nodesInGrid) {
            grid[nodeInGrid.y][nodeInGrid.x] = nodeInGrid.node;
        }

        return grid;
    }

    private static List<NodeInGrid> parseNodesInGrid(String input) {
        Tokenizer<NodeInGrid> tokenizer = new Tokenizer<>(singletonList(
                new TokenInfo<>(
                        "/dev/grid/node-x(\\d+)-y(\\d+)\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)%",
                        Day22::parseNodeInGrid
                )
        ));

        return tokenizer.parse(input);
    }

    private static NodeInGrid parseNodeInGrid(Matcher matcher) {
        return new NodeInGrid(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                new Node(
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4))
                )
        );
    }

    private static class NodeInGrid {
        final int x;
        final int y;
        final Node node;

        NodeInGrid(int x, int y, Node node) {
            this.x = x;
            this.y = y;
            this.node = node;
        }
    }

    private static class Node {
        final int size;
        final int used;

        Node(int size, int used) {
            this.size = size;
            this.used = used;
        }

        boolean isEmpty() {
            return used == 0;
        }

        int getAvailable() {
            return size - used;
        }

        boolean fits(int size) {
            return getAvailable() >= size;
        }
    }

    private static class IJ {
        final int i, j;

        IJ(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IJ ij = (IJ) o;

            return i == ij.i && j == ij.j;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }


}