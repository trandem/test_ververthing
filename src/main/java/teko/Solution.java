package teko;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
    private Set<Seat> availableSeatSet;
    private int[][] allSeat;
    private Map<Integer, Seat> reserveMap;// for cancel seat
    private int row, col, minDistance;

    public int initSize(int row, int col, int minDistance) {
        if (row > 0 && col > 0 && minDistance >= 0) {
            this.row = row;
            this.col = col;
            this.minDistance = minDistance;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Seat point = new Seat(i, j);
                availableSeatSet.add(point);
            }
        }
        this.allSeat = new int[row][col];
        return 0;
    }

    private static int manhattanDistance(Seat point1, Seat point2) {
        return Math.abs(point1.getX() - point2.getX()) + Math.abs(point1.getY() - point2.getY());
    }

    private List<Seat> processBlackSeat(Seat reserveSeat) {
        List<Seat> output = new ArrayList<>();
        for (Seat p : availableSeatSet) {
            if (manhattanDistance(reserveSeat, p) < minDistance) {
                output.add(p);
                allSeat[p.getX() - 1][p.getY() - 1] = -1;
            }
        }
        availableSeatSet.removeAll(output);
        return output;
    }

    public int reserve(int userId, List<Seat> reserveSeat) {
        for (Seat point : reserveSeat) {
            if (!availableSeatSet.contains(point)) {
                return -1;
            }
        }

        for (Seat seat : reserveSeat) {
            availableSeatSet.remove(seat);
            allSeat[seat.getX() - 1][seat.getY() - 1] = -1;

            reserveMap.put(userId, seat);

            processBlackSeat(seat);
        }

        return 0;
    }

    public List<Seat> availableSeatForGroup(int numberSeat) {
        List<Seat> output = new ArrayList<>();
        for (int i = 0; i < this.row; i++) {
            List<Seat> temp = new ArrayList<>();
            for (int j = 0; j < this.col; j++) {
                if (allSeat[i][j] == 0) {
                    temp.add(new Seat(i, j));
                }
            }
            if (temp.size() >= numberSeat) {
                output.addAll(temp);
            }
        }
        return output;
    }


}
