package com.example.duanxuong_quanlykhohang.Adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanxuong_quanlykhohang.DAO.DAO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp;
import com.example.duanxuong_quanlykhohang.DAO.DAO_sp_Phieu_Nhap;
import com.example.duanxuong_quanlykhohang.DTO.DTO_KhoHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_LoaiHang;
import com.example.duanxuong_quanlykhohang.DTO.DTO_sp;
import com.example.duanxuong_quanlykhohang.QuanLyKhoHang;
import com.example.duanxuong_quanlykhohang.R;
import com.example.duanxuong_quanlykhohang.fragment.Frag_kingdoanh;
import com.example.duanxuong_quanlykhohang.fragment.Frag_load;
import com.example.duanxuong_quanlykhohang.fragment.Frag_sp_ngungkinhdoanh;

import java.util.Calendar;
import java.util.List;

public class Adapter_sp extends RecyclerView.Adapter<Adapter_sp.ViewHolder> {

    Context context;
    List<DTO_sp> list;

    List<DTO_LoaiHang> listHang;
    DAO_LoaiHang loaiHang;
    DTO_LoaiHang dto_loaiHang;
    Adapter_loaiSP adapterLoaiSP;
    QuanLyKhoHang khoHang;
    DAO_sp dao_sp;
    int trangthai = 0;
    private ViewHolder currentViewHolder;
    Frag_sp_ngungkinhdoanh frag_sp_ngungkinhdoanh;
    Frag_kingdoanh Frag_kingdoanh;
    int i = 0;
    public Adapter_sp(Context context, List<DTO_sp> list, int trangthai) {
        this.context = context;
        this.list = list;
        khoHang = (QuanLyKhoHang) context;
        dao_sp = new DAO_sp(context);
        this.trangthai = trangthai;
        frag_sp_ngungkinhdoanh = new Frag_sp_ngungkinhdoanh();
        Frag_kingdoanh = new Frag_kingdoanh();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_sanpham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_sp.setText(list.get(position).getMaSP() + "");
        holder.ten_sp.setText(list.get(position).getTenSP());
        holder.theLoai.setText(list.get(position).getTenLoai());
        holder.soLuongTon.setText(list.get(position).getSoLuongTon() + "");
        holder.anh.setImageURI(list.get(position).hienthi(context));
        currentViewHolder = holder; // Lưu trữ holder hiện tại
        // Lưu trữ hình ảnh hiện tại của đối tượng đang được hiển thị
        byte[] currentImage = list.get(position).getMota();
        holder.setCurrentImage(currentImage);

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trangthai == 1) {
                    xoa();
                } else {
                    ngungKD();
                }
            }

        private void xoa() {
            DTO_sp DTO_sp = list.get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo");
            builder.setIcon(R.drawable.baseline_warning_amber_24);
            builder.setMessage("Xác nhận muốn xóa vĩnh viễn sản phẩm này");

            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (dao_sp.Deletevinhvien(DTO_sp) > 0) {
                        locSP(trangthai);
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(context, "Đã hủy thao tác", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }

        private void ngungKD() {
            DTO_sp DTO_sp = list.get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo");
            builder.setIcon(R.drawable.baseline_warning_amber_24);
            builder.setMessage("Xác nhận muốn ngừng kinh doanh sản phẩm này");

            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (dao_sp.DeleteRow(DTO_sp) > 0) {
                        locSP(trangthai);
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        FragmentManager manager = khoHang.getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.framelayout, new Frag_load()).commit();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(context, "Đã hủy thao tác", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trangthai == 1) {
                    khoiphuc();
                } else {
                    update(position);
                }
            }

            private void khoiphuc() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn khôi phục sản phẩm này ?");
                builder.setTitle("Thông báo");

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DTO_sp sp = list.get(position);
                        if (dao_sp.KhoiphucRow(sp) > 0) {
                            Toast.makeText(context, "Khôi phục thành công", Toast.LENGTH_SHORT).show();
                            locSP(trangthai);
                        } else {
                            Toast.makeText(context, "Khôi phục thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void update(int position) {
        DTO_sp DTO_sp = list.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = inflater.inflate(R.layout.dialog_them_sp, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        // Ánh xạ
        TextView title = view.findViewById(R.id.tv_tilte_sp);
        title.setText("Update Sản phẩm");
        EditText id_sp = view.findViewById(R.id.txtIdSanPhamThem);
        EditText Ten_sp = view.findViewById(R.id.txtTenSanPhamThem);
        EditText tenLoai = view.findViewById(R.id.txtLoaiThem);
        Button save = view.findViewById(R.id.btnSaveThem);
        Button addImg = view.findViewById(R.id.btnlayanh);
        Button back = view.findViewById(R.id.btnCancelThem);
        final DTO_LoaiHang[] getID = {new DTO_LoaiHang()};
        getID[0].setId(list.get(position).getMaLoai());
        Calendar lich = Calendar.getInstance();
        int ngay = lich.get(Calendar.DAY_OF_MONTH);
        int thang = lich.get(Calendar.MONTH);
        int nam = lich.get(Calendar.YEAR);

        id_sp.setText(DTO_sp.getMaSP());
        Ten_sp.setText(DTO_sp.getTenSP());
        tenLoai.setText(DTO_sp.getTenLoai());
        Log.e(TAG, "update: "+tenLoai.getText().toString() );
        Log.e(TAG, "update: "+ DTO_sp.getMaLoai());

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoHang.importAnh();
            }
        });

        tenLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dialog.getContext());
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view2 = inflater.inflate(R.layout.dialog_them_loaihang, null);
                builder.setView(view2);
                Dialog dialogLoaiSP = builder.create();
                dialogLoaiSP.show();
                ListView listLoaiHang = view2.findViewById(R.id.lis_loaiSP);
                EditText themLoai = view2.findViewById(R.id.txt_themLoai);
                ImageButton add = view2.findViewById(R.id.ibtn_addLoai);
                loaiHang = new DAO_LoaiHang(context);
                listHang = loaiHang.getAll();
                adapterLoaiSP = new Adapter_loaiSP(context, listHang);
                listLoaiHang.setAdapter(adapterLoaiSP);
                themLoai.setVisibility(View.GONE);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themLoai.setVisibility(View.VISIBLE);
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dto_loaiHang = new DTO_LoaiHang();
                                dto_loaiHang.setTenLoai(themLoai.getText().toString());
                                if (loaiHang.AddRow(dto_loaiHang) > 0) {
                                    listHang.clear();
                                    listHang.addAll(loaiHang.getAll());
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    dialogLoaiSP.dismiss();
                                    getID[0] = listHang.get(listHang.size() - 1);
                                    tenLoai.setText(getID[0].getTenLoai());
                                } else {
                                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                listLoaiHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(i==0){
                            getID[0] = listHang.get(position);
                            tenLoai.setText(getID[0].getTenLoai());
                            dialogLoaiSP.dismiss();
                        }
                    }
                });
                listLoaiHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        i=1;
                        dto_loaiHang = listHang.get(position);
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                        builder.setTitle("Thông báo");
                        builder.setIcon(R.drawable.baseline_warning_amber_24);
                        builder.setMessage("Cảnh báo nếu thực hiện xóa " + "'" + dto_loaiHang.getTenLoai() + "'" + " những sản phẩm thuộc " + dto_loaiHang.getTenLoai() + " sẽ bị mất.");
                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DAO_LoaiHang hang = new DAO_LoaiHang(context);
//                getID[0] = listHang.get(position);
                                if (hang.DeleteRow(listHang.get(position)) > 0) {
                                    Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                                    listHang.clear();
                                    listHang.addAll(loaiHang.getAll());
                                    adapterLoaiSP.notifyDataSetChanged();
                                    list.clear();
                                    list.addAll(dao_sp.getAll(0));
                                    adapterLoaiSP.notifyDataSetChanged();
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DTO_sp.setMaSP(id_sp.getText().toString());
                DTO_sp.setTenSP(Ten_sp.getText().toString());
                DTO_sp.setTenLoai(getID[0].getTenLoai());
                DTO_sp.setMaLoai(getID[0].getId());
                // Kiểm tra xem ảnh có thay đổi không
                if (khoHang.getAnh() != null) {
                    DTO_sp.setMota(khoHang.getAnh());
                } else {
                    // Nếu không có sự thay đổi, sử dụng ảnh hiện tại
                    DTO_sp.setMota(currentViewHolder.getCurrentImage());
                }

                if (dao_sp.Update(DTO_sp) > 0) {
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    locSP(trangthai);
                    FragmentManager manager = khoHang.getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.framelayout, new Frag_load()).commit();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Sửa  thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_sp, ten_sp, soLuongTon, theLoai;
        ImageButton xoa, update;
        private byte[] currentImage;
        ImageView anh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_sp = itemView.findViewById(R.id.lbl_id_sp);
            ten_sp = itemView.findViewById(R.id.lbl_name_sp);
            theLoai = itemView.findViewById(R.id.lbl_ten_loai_sp);
            soLuongTon = itemView.findViewById(R.id.lbl_soluongton);
            xoa = itemView.findViewById(R.id.ibtn_delete_sp);
            update = itemView.findViewById(R.id.ibtn_update_sp);
            anh = itemView.findViewById(R.id.imv_imgsp_show);
        }

        public void setCurrentImage(byte[] currentImage) {
            this.currentImage = currentImage;
        }

        public byte[] getCurrentImage() {
            return currentImage;
        }
    }

    public void locSP(int check) {
        if (check == 0) {
            list.clear();
            list.addAll(dao_sp.getAll(trangthai));
            notifyDataSetChanged();
        } else if (check == 1) {
            list.clear();
            list.addAll(dao_sp.getAll(trangthai));
            notifyDataSetChanged();
        }
    }
}
