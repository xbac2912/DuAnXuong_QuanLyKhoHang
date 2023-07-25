package com.example.duanxuong_quanlykhohang.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.Adapter.Adapter_loaiSP;
import com.example.duanxuong_quanlykhohang.Adapter.Adapter_sp;
import com.example.duanxuong_quanlykhohang.DAO.DAO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DTO.DTO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link qlSanPhamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class qlSanPhamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Dialog dialog;
    DAO_sp dao_sp;
    DTO_sp dto_sp;
    RecyclerView recyclerView;
    List<DTO_sp> list;
    Adapter_sp adapter_sp;
    Adapter_loaiSP adapterLoaiSP;
    List<DTO_LoaiHang> listHang;
    DAO_LoaiHang loaiHang;
    DTO_LoaiHang dto_loaiHang;
    RecyclerView.LayoutManager layoutManager;

    Calendar lich = Calendar.getInstance();

    public qlSanPhamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment qlSanPhamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static qlSanPhamFragment newInstance(String param1, String param2) {
        qlSanPhamFragment fragment = new qlSanPhamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void taoDoiTuong() {
        dialog = new Dialog(getContext());
        dao_sp = new DAO_sp(dialog.getContext());
        list = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taoDoiTuong();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ql_san_pham, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FloatingActionButton floatThem = view.findViewById(R.id.flb_themSanPham);
        recyclerView = view.findViewById(R.id.rcv_sanpham);

        list = dao_sp.getAll();


        adapter_sp = new Adapter_sp(view.getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter_sp);
        floatThem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialogAdd();
            }
        });
        super.onViewCreated(view, savedInstanceState);
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

    String id_sp;
    int maloai;
    String tensp;
    int gia;
    int soluong;
    String ngayluu;
    String tenLoai;

    private void showDialogAdd() {
        dialog.setContentView(R.layout.dialog_them_sp);
        //ánh xạ
        EditText ed_idSp = dialog.findViewById(R.id.txtIdSanPhamThem);
        EditText ed_loaiSp = dialog.findViewById(R.id.txtLoaiThem);
        EditText ed_tenSp = dialog.findViewById(R.id.txtTenSanPhamThem);
        EditText ed_soluongSp = dialog.findViewById(R.id.txtSoLuongThem);
        EditText ed_ngayluu = dialog.findViewById(R.id.txtNgayLuuKhoThem);
        EditText ed_gia = dialog.findViewById(R.id.txtGiaThem);
        Button btn_themsp = dialog.findViewById(R.id.btnSaveThem);
        final DTO_LoaiHang[] getID = {new DTO_LoaiHang()};

        int ngay = lich.get(Calendar.DAY_OF_MONTH);
        int thang = lich.get(Calendar.MONTH);
        int nam = lich.get(Calendar.YEAR);
        ed_ngayluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog bangLich = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ed_ngayluu.setText(String.format("%d-%d-%d", year, month, dayOfMonth));
                    }
                }, nam, thang, ngay);
                bangLich.show();
            }
        });
        ed_loaiSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] i = {0};
                AlertDialog.Builder builder = new AlertDialog.Builder(dialog.getContext());
                LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_them_loaihang, null);
                builder.setView(view);
                Dialog dialogLoaiSP = builder.create();
                dialogLoaiSP.show();
                ListView listLoaiHang = view.findViewById(R.id.lis_loaiSP);
                EditText themLoai = view.findViewById(R.id.txt_themLoai);
                ImageButton add = view.findViewById(R.id.ibtn_addLoai);
                loaiHang = new DAO_LoaiHang(getContext());
                listHang = loaiHang.getAll();
                adapterLoaiSP = new Adapter_loaiSP(getContext(), listHang);
//                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//                listLoaiHang.setLayoutManager(layoutManager);
                listLoaiHang.setAdapter(adapterLoaiSP);
                themLoai.setVisibility(View.GONE);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themLoai.setVisibility(View.VISIBLE);
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!themLoai.getText().toString().isEmpty()) {
                                    dto_loaiHang = new DTO_LoaiHang();
                                    dto_loaiHang.setTenLoai(themLoai.getText().toString());
                                    if (loaiHang.AddRow(dto_loaiHang) > 0) {
                                        listHang.clear();
                                        listHang.addAll(loaiHang.getAll());
                                        adapterLoaiSP.notifyDataSetChanged();
                                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                        dialogLoaiSP.dismiss();
                                        getID[0] = listHang.get(listHang.size() - 1);
                                        ed_loaiSp.setText(getID[0].getTenLoai());

                                    } else {
                                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                    }
                });
                // xử lý sự kiện click của người dùng
                listLoaiHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (i[0] == 0) {
                            getID[0] = listHang.get(position);
                            ed_loaiSp.setText(getID[0].getTenLoai());
                            dialogLoaiSP.dismiss();
                        }
                    }
                });
                listLoaiHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        i[0] = 1;
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                        builder.setTitle("Thông báo");
                        builder.setIcon(R.drawable.baseline_warning_amber_24);
                        builder.setMessage("Cảnh báo nếu thực hiện xóa LOẠI những sản pẩm thuộc loại sẽ bị mất.");

                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DAO_LoaiHang hang = new DAO_LoaiHang(getContext());

//                getID[0] = listHang.get(position);
                                if (hang.DeleteRow(listHang.get(position)) > 0) {
                                    Toast.makeText(getContext(), "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                                    listHang.clear();
                                    listHang.addAll(loaiHang.getAll());
                                    adapterLoaiSP.notifyDataSetChanged();
                                    list.clear();
                                    list.addAll(dao_sp.getAll());
                                    adapter_sp.notifyDataSetChanged();
                                    dialog.dismiss();
                                }
                            }
                        });

                        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        Dialog dialog = builder.create();
                        dialog.show();
                        return false;
                    }
                });
            }


        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_themsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ed_idSp.getText().toString().isEmpty() && !ed_idSp.getText().toString().isEmpty() && !ed_tenSp.getText().toString().isEmpty() && !ed_soluongSp.getText().toString().isEmpty() && !ed_ngayluu.getText().toString().isEmpty()) {
                    id_sp = ed_idSp.getText().toString();
                    maloai = getID[0].getId();
                    tenLoai = getID[0].getTenLoai();
                    tensp = ed_tenSp.getText().toString();
                    gia = Integer.parseInt(ed_gia.getText().toString());
                    soluong = Integer.parseInt(ed_soluongSp.getText().toString());
                    ngayluu = ed_ngayluu.getText().toString();
                    openDialog_tb();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.findViewById(R.id.btnCancelThem).setOnClickListener(new View.OnClickListener() {
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
        dto_sp = new DTO_sp();
        dto_sp.setMaSP(id_sp);
        dto_sp.setMaLoai(maloai);
        dto_sp.setTenLoai(tenLoai);
        int maND = 1;
        dto_sp.setTenSP(tensp);
        dto_sp.setGia(gia);
        dto_sp.setSoLuong(soluong);
        dto_sp.setNgayluu(ngayluu);

        a = dao_sp.ADDSanPham(dto_sp);
        list.clear();
        list.addAll(dao_sp.getAll());
        adapter_sp.notifyDataSetChanged();
        return a;
    }
}