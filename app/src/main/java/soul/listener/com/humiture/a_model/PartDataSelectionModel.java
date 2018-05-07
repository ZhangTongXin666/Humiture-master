package soul.listener.com.humiture.a_model;

import java.util.Arrays;

/**
 * @author kuan
 *         Created on 2017/11/17.
 * @description
 */

public class PartDataSelectionModel {
    private int tableNameNo;
    private String[] parts;
    private String[] selections;
    private String[] hazyOrExact;//模糊或者准确
    private String[] conditions;//条件
    private int startLimit;
    private int endLimit;

    public int getTableNameNo() {
        return tableNameNo;
    }

    public void setTableNameNo(int tableNameNo) {
        this.tableNameNo = tableNameNo;
    }

    public String[] getParts() {
        return parts;
    }

    public void setParts(String[] parts) {
        this.parts = parts;
    }

    public String[] getSelections() {
        return selections;
    }

    public void setSelections(String[] sekections) {
        this.selections = sekections;
    }

    public String[] getHazyOrExact() {
        return hazyOrExact;
    }

    public void setHazyOrExact(String[] hazyOrExact) {
        this.hazyOrExact = hazyOrExact;
    }

    public String[] getConditions() {
        return conditions;
    }

    public void setConditions(String[] cinditions) {
        this.conditions = cinditions;
    }

    public int getStartLimit() {
        return startLimit;
    }

    public void setStartLimit(int startLimit) {
        this.startLimit = startLimit;
    }

    public int getEndLimit() {
        return endLimit;
    }

    public void setEndLimit(int endLimit) {
        this.endLimit = endLimit;
    }

    @Override
    public String toString() {
        return "PartDataSelectionModel{" +
                "tableNameNo=" + tableNameNo +
                ", parts=" + Arrays.toString(parts) +
                ", sekections=" + Arrays.toString(selections) +
                ", hazyOrExact=" + Arrays.toString(hazyOrExact) +
                ", cinditions=" + Arrays.toString(conditions) +
                ", startLimit=" + startLimit +
                ", endLimit=" + endLimit +
                '}';
    }
}
