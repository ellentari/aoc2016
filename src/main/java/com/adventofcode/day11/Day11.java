package com.adventofcode.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class Day11 {

    public static void main(String[] args) {
//        Floor[] floorsArr = new Floor[4];
//        floorsArr[0] = new Floor(asList(new Microchip("H"), new Microchip("L")));
//        floorsArr[1] = new Floor(singletonList(new Generator("H")));
//        floorsArr[2] = new Floor(singletonList(new Generator("L")));
//        floorsArr[3] = new Floor(emptyList());


//        Floor[] floorsArr = new Floor[4];
//        floorsArr[0] = new Floor(asList(new Generator("promethium"), new Microchip("promethium")));
//        floorsArr[1] = new Floor(asList(new Generator("cobalt"), new Generator("curium"),
//                new Generator("ruthenium"), new Generator("plutonium")));
//        floorsArr[2] = new Floor(asList(new Microchip("cobalt"),
//                new Microchip("curium"),
//                new Microchip("ruthenium"),
//                new Microchip("plutonium")));
//        floorsArr[3] = new Floor(emptyList());

        Floor[] floorsArr = new Floor[4];
        floorsArr[0] = new Floor(asList(
                new Generator("promethium"),
                new Microchip("promethium"),
                new Generator("elerium"),
                new Microchip("elerium"),
                new Generator("dilithium"),
                new Microchip("dilithium"))
        );
        floorsArr[1] = new Floor(asList(new Generator("cobalt"), new Generator("curium"),
                new Generator("ruthenium"), new Generator("plutonium")));
        floorsArr[2] = new Floor(asList(new Microchip("cobalt"),
                new Microchip("curium"),
                new Microchip("ruthenium"),
                new Microchip("plutonium")));
        floorsArr[3] = new Floor(emptyList());

        Floors floors = new Floors(floorsArr);
        Set<State> visited = new HashSet<>();

        visited.add(new State(0, floors));

        PriorityQueue<Move> moves = new PriorityQueue<>();
        moves.addAll(getPossibleMoves(0, 0, floors));

        while (!moves.isEmpty()) {
            Move move = moves.poll();
            move.make();
            State state = new State(move.to, move.floors);

            try {
                state.checkState();
            } catch (AssertionError e) {
                e.printStackTrace();
            }


            if (state.isFinal(move.floors.size() - 1)) {
                System.out.println(move.steps);
                break;
            } else if (!visited.contains(state)) {
                visited.add(state);
                moves.addAll(getPossibleMoves(move.to, move.steps, move.floors));
            }
        }
    }

    private static List<Move> getPossibleMoves(int elevator, int steps, Floors floors) {
        List<Move> moves = new ArrayList<>();
        List<Object[]> possibleToTake = floors.get(elevator).possibleToTake();

        for (Object[] objects : possibleToTake) {
            Move move1 = new Move(elevator, elevator + 1, steps + 1, floors, objects);
            if (move1.isValid()) {
                moves.add(move1);
            }

            Move move2 = new Move(elevator, elevator - 1, steps + 1, floors, objects);
            if (move2.isValid()) {
                moves.add(move2);
            }
        }

        return moves;
    }

    private static class Move implements Comparable<Move> {
        final int from;
        final int to;
        final int steps;
        final Floors floors;
        final List<Object> objects;

        Move(int from, int to, int steps, Floors floors, Object[] objects) {
            this.from = from;
            this.to = to;
            this.steps = steps;
            this.floors = new Floors(floors);
            this.objects = new ArrayList<>(objects.length);
            Collections.addAll(this.objects, objects);
        }

        boolean isValid() {
            return to >= 0 && to < floors.size() && floors.get(to).canMove(objects) && floors.get(from).isValidWithout(objects);
        }

        void make() {
            floors.get(from).objects.removeAll(objects);
            floors.get(to).objects.addAll(objects);
        }

        @Override
        public int compareTo(Move o) {
            return Integer.compare(steps, o.steps);
        }
    }

    private static class Floors {
        final Floor[] floors;

        Floors(Floor[] floors) {
            this.floors = floors;
        }

        Floors(Floors floors) {
            this.floors = new Floor[floors.floors.length];
            for (int i = 0; i < floors.floors.length; i++) {
                this.floors[i] = new Floor(floors.floors[i].objects);
            }
        }

        int size() {
            return floors.length;
        }

        Floor get(int i) {
            return floors[i];
        }
    }

    private static class Floor {

        final List<Object> objects;

        Floor(List<Object> objects) {
            this.objects = new ArrayList<>(objects);
        }

        List<Object[]> possibleToTake() {
            List<Object[]> toTake = new ArrayList<>();

            for (int i = 0; i < objects.size(); i++) {
                toTake.add(new Object[]{objects.get(i)});
            }

            for (int i = 0; i < objects.size() - 1; i++) {
                for (int j = i + 1; j < objects.size(); j++) {
                    toTake.add(new Object[]{objects.get(i), objects.get(j)});
                }
            }

            return toTake;
        }

        boolean canMove(List<Object> objects) {
            Set<String> microchipTypes = new HashSet<>();
            Set<String> generatorTypes = new HashSet<>();

            for (Object object : this.objects) {
                if (object instanceof Microchip) {
                    microchipTypes.add(((Microchip) object).type);
                } else {
                    generatorTypes.add(((Generator) object).type);
                }
            }

            for (Object object : objects) {
                if (object instanceof Microchip) {
                    microchipTypes.add(((Microchip) object).type);
                } else {
                    generatorTypes.add(((Generator) object).type);
                }
            }

            if (generatorTypes.isEmpty()) {
                return true;
            }


            for (String microchipType : microchipTypes) {
                if (!generatorTypes.contains(microchipType)) {
                    return false;
                }
            }

            return true;
        }

        boolean isValidWithout(List<Object> objects) {
            Set<String> microchipTypes = new HashSet<>();
            Set<String> generatorTypes = new HashSet<>();

            for (Object object : this.objects) {
                if (!objects.contains(object)) {
                    if (object instanceof Microchip) {
                        microchipTypes.add(((Microchip) object).type);
                    } else {
                        generatorTypes.add(((Generator) object).type);
                    }
                }
            }

            if (generatorTypes.isEmpty()) {
                return true;
            }

            for (String microchipType : microchipTypes) {
                if (!generatorTypes.contains(microchipType)) {
                    return false;
                }
            }

            return true;
        }
    }

    private static class State {
        final int elevator;
        final List<Pair> pairs;

        public State(int elevator, Floors floors) {
            this.elevator = elevator;
            this.pairs = new ArrayList<>();

            Map<String, Integer> microchips = new HashMap<>();
            Map<String, Integer> generators = new HashMap<>();

            for (int i = 0; i < floors.size(); i++) {
                Floor floor = floors.get(i);
                for (Object object : floor.objects) {
                    if (object instanceof Microchip) {
                        microchips.put(((Microchip) object).type, i);
                    } else {
                        generators.put(((Generator) object).type, i);
                    }
                }
            }

            if (microchips.size() != generators.size()) {
                throw new AssertionError();
            }


            for (Map.Entry<String, Integer> entry : microchips.entrySet()) {
                int mFloor = entry.getValue();
                int gFloor = generators.get(entry.getKey());

                pairs.add(new Pair(mFloor, gFloor));
            }

            Collections.sort(pairs);
        }

        void checkState() {
            Map<String, Integer> microchips = new HashMap<>();
            Map<String, Integer> generators = new HashMap<>();

            for (int i = 0; i < pairs.size(); i++) {
                microchips.put("x" + i, pairs.get(i).microchipFloor);
                generators.put("x" + i, pairs.get(i).generatorFloor);
            }

            for (Map.Entry<String, Integer> entry : microchips.entrySet()) {
                Set<String> gensOnFlor = generatorsOnFloor(generators, entry.getValue());
                if (gensOnFlor.size() > 0 && !gensOnFlor.contains(entry.getKey())) {
                    throw new AssertionError();
                }
            }
        }

        Set<String> generatorsOnFloor(Map<String, Integer> generators, Integer floor) {
            Set<String> strings = new HashSet<>();
            for (Map.Entry<String, Integer> e : generators.entrySet()) {
                if (e.getValue().equals(floor)) {
                    strings.add(e.getKey());
                }
            }
            return strings;
        }

        boolean isFinal(int elFloor) {
            if (elevator != elFloor) {
                return false;
            }

            for (Pair pair : pairs) {
                if (pair.microchipFloor != elevator || pair.generatorFloor != elevator) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            return elevator == state.elevator && pairs.equals(state.pairs);
        }

        @Override
        public int hashCode() {
            int result = elevator;
            result = 31 * result + pairs.hashCode();
            return result;
        }
    }

    private static class Pair implements Comparable<Pair> {
        final int microchipFloor;
        final int generatorFloor;

        Pair(int microchipFloor, int generatorFloor) {
            this.microchipFloor = microchipFloor;
            this.generatorFloor = generatorFloor;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            return microchipFloor == pair.microchipFloor && generatorFloor == pair.generatorFloor;
        }

        @Override
        public int hashCode() {
            int result = microchipFloor;
            result = 31 * result + generatorFloor;
            return result;
        }

        @Override
        public int compareTo(Pair o) {
            int compare = Integer.compare(microchipFloor, o.microchipFloor);
            if (compare != 0) {
                return compare;
            }
            return Integer.compare(generatorFloor, o.generatorFloor);
        }
    }

    private static class Microchip {
        final String type;

        public Microchip(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Microchip{" + type + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Microchip microchip = (Microchip) o;

            return type != null ? type.equals(microchip.type) : microchip.type == null;
        }

        @Override
        public int hashCode() {
            return type != null ? type.hashCode() : 0;
        }
    }

    private static class Generator {
        final String type;

        public Generator(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Generator{" + type + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Generator generator = (Generator) o;

            return type != null ? type.equals(generator.type) : generator.type == null;
        }

        @Override
        public int hashCode() {
            return type != null ? type.hashCode() : 0;
        }
    }
}
