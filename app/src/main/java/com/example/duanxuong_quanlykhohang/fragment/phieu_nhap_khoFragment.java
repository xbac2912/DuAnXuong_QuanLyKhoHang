package com.example.duanxuong_quanlykhohang.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.duanxuong_quanlykhohang.Adapter.Adapter_loaiSP;
import com.example.duanxuong_quanlykhohang.Adapter.Adapter_sp;
import com.example.duanxuong_quanlykhohang.Adapter.Adapter_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.DAO.DAO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DAO.DAO_khohang;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.DTO.DTO_KhoHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class phieu_nhap_khoFragment extends Fragment {

    Dialog dialog;
    DAO_sp_Phieu_Nhap dao_sp_phieu_nhap;
    DTO_sp_Phieu_Nhap dto_sp_phieu_nhap;
    RecyclerView rcv_phieunhapkho;
    List<DTO_sp_Phieu_Nhap> list;
    Adapter_sp_Phieu_Nhap adapter_sp_phieu_nhap;

    List<DTO_sp> listSP;
    DAO_sp dao_sp;


    public phieu_nhap_khoFragment() {
        // Required empty public constructor
    }
    public void taoDoiTuong() {
        dialog = new Dialog(getContext());
        dao_sp_phieu_nhap = new DAO_sp_Phieu_Nhap(dialog.getContext());
        list = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taoDoiTuong();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phieu_nhap_kho, container, false);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_phieu_nhap_kho,null,false);
        rcv_phieunhapkho = view.findViewById(R.id.rcv_phieunhapkho);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton floatThem = view.findViewById(R.id.flb_themPhieuNhap);
        rcv_phieunhapkho = view.findViewById(R.id.rcv_phieunhapkho);
        dao_sp_phieu_nhap = new DAO_sp_Phieu_Nhap(getContext());
        list = dao_sp_phieu_nhap.getAll();
        dao_sp = new DAO_sp(getContext());
        listSP = dao_sp.getAllA();


        adapter_sp_phieu_nhap = new Adapter_sp_Phieu_Nhap(view.getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        rcv_phieunhapkho.setLayoutManager(linearLayoutManager);
        rcv_phieunhapkho.setAdapter(adapter_sp_phieu_nhap);
        adapter_sp_phieu_nhap.notifyDataSetChanged();
        floatThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogAdd();
            }
        });

    }

    public void openDialog_tb() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Save");
        builder.setMessage("Bạn có chắc chắn muốn Save không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (themSP() > 0) {
                dialog.dismiss();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    String id_sp_nhap;
    int gia;
    int soluong;
    String ngayluu;
    DAO_khohang dao_khohang;
    ArrayList<DTO_KhoHang> listKH = new ArrayList<>();
    DAO_LoaiHang dao_loaiHang;
    int slcu;

    private void showDialogAdd() {
        dialog.setContentView(R.layout.dialog_them_sp_phieu_nhap);
        //ánh xạ
        EditText ed_idSPnhap = dialog.findViewById(R.id.txtIdSanPhamThem);
        EditText ed_ngayNhap = dialog.findViewById(R.id.txtNgayNhap);
        EditText ed_giaNhap = dialog.findViewById(R.id.txtgiaNhap);
        EditText ed_soLuongNhap = dialog.findViewById(R.id.txtsoLuongNhap);
        Button btn_themspnhap = dialog.findViewById(R.id.btnSavephieuThem);
        Button btn_cancelSpnhap = dialog.findViewById(R.id.btnCancelphieuThem);
        final DTO_LoaiHang[] getID = {new DTO_LoaiHang()};

        Calendar lich = Calendar.getInstance();

        int ngay = lich.get(Calendar.DAY_OF_MONTH);
        int thang = lich.get(Calendar.MONTH);
        int nam = lich.get(Calendar.YEAR);
        //
        ed_ngayNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog bangLich = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Chuyển đổi thành định dạng dd/mm/yyyy
                        String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                        ed_ngayNhap.setText(selectedDate);
                    }
                }, nam, thang, ngay);
                bangLich.show();
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_themspnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dto_sp_phieu_nhap = new DTO_sp_Phieu_Nhap();
                dao_loaiHang = new DAO_LoaiHang(getContext());
                dao_khohang = new DAO_khohang(getContext());
                if (!ed_idSPnhap.getText().toString().isEmpty() && !ed_giaNhap.getText().toString().isEmpty() && !ed_soLuongNhap.getText().toString().isEmpty() && !ed_ngayNhap.getText().toString().isEmpty()) {
                    id_sp_nhap = ed_idSPnhap.getText().toString();
                    gia = Integer.parseInt(ed_giaNhap.getText().toString());
                    soluong = Integer.parseInt(ed_soLuongNhap.getText().toString());
                    ngayluu = ed_ngayNhap.getText().toString();
                    ngayluu = chuyenDoiNgayPhuHop(ngayluu);
                    String loai = "";
                    listKH = dao_khohang.selectAll();
                    for (DTO_KhoHang x: listKH) {
                        if (id_sp_nhap.equals(x.getMaSP())) {
                            slcu = soluong + x.getSoluong();
                        }
                    }
                    int idLoai=0;
                    String tenSP="";
                    for (DTO_sp x: listSP) {
                        if (id_sp_nhap.equals(x.getMaSP())) {
                            idLoai = x.getMaLoai();
                            tenSP = x.getTenSP();
                            break;
                        }
                    }
                    if(dao_khohang.checkkh(id_sp_nhap)) {
                        dao_khohang.UpdateSL(String.valueOf(slcu),String.valueOf(gia), id_sp_nhap);
                    } else {
                        if(dao_khohang.insert( new DTO_KhoHang(id_sp_nhap,tenSP,gia,soluong, idLoai))){

                        }else {
                            Toast.makeText(getContext(), "sdfsdf", Toast.LENGTH_SHORT).show();
                        }
                    }
                    openDialog_tb();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.findViewById(R.id.btnCancelphieuThem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    public long themSP() {
        long a = 0;
        dto_sp_phieu_nhap = new DTO_sp_Phieu_Nhap();
        dto_sp_phieu_nhap.setMaSanPham(id_sp_nhap);

        // Chuyển đổi ngày nhập về định dạng yyyy-mm-dd
        String ngayNhapFormatted = chuyenDoiNgayPhuHop(ngayluu);
        dto_sp_phieu_nhap.setNgayNhap(ngayNhapFormatted);

        dto_sp_phieu_nhap.setGia(gia);
        dto_sp_phieu_nhap.setSoLuong(soluong);

        a = dao_sp_phieu_nhap.ADDSanPhamPhieunhpa(dto_sp_phieu_nhap);
        list.clear();
        list.addAll(dao_sp_phieu_nhap.getAll());
        adapter_sp_phieu_nhap.notifyDataSetChanged();
        return a;
    }
    private String chuyenDoiNgayPhuHop(String ngayNhapStr) {
        String[] ngayThangNam = ngayNhapStr.split("/");
        String ngay = ngayThangNam[0];
        String thang = ngayThangNam[1];
        String nam = ngayThangNam[2];
        return nam + "-" + thang + "-" + ngay;
    }

}