package com.exam.ptitexam.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class ExamResultDTO {
    private long userId;
    private String ten;
    private int soLanThamGia;
    private double tiLeHoanThanh;
    private List<Double> diem;
    private double diemTrungBinh;

    public ExamResultDTO() {
    }

    public ExamResultDTO(long userId, String ten, int soLanThamGia, double tiLeHoanThanh, List<Double> diem) {
        this.userId = userId;
        this.ten = ten;
        this.soLanThamGia = soLanThamGia;
        this.tiLeHoanThanh = tiLeHoanThanh;
        this.diem = diem;
        double trungBinh = 0;
        for (double x : diem) {
            trungBinh += x;
        }
        this.diemTrungBinh = trungBinh / diem.size();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoLanThamGia() {
        return soLanThamGia;
    }

    public void setSoLanThamGia(int soLanThamGia) {
        this.soLanThamGia = soLanThamGia;
    }

    public double getTiLeHoanThanh() {
        return tiLeHoanThanh;
    }

    public void setTiLeHoanThanh(double tiLeHoanThanh) {
        this.tiLeHoanThanh = tiLeHoanThanh;
    }

    public List<Double> getDiem() {
        return diem;
    }

    public void setDiem(List<Double> diem) {
        this.diem = diem;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }
}
