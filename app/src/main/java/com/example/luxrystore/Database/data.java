package com.example.luxrystore.Database;

public  class data {
    public static final String insertNhanVien=" insert into nhanvien(name,sdt,user,password)" +
            "values  ('admin','0000000000','admin','admin')," +
            "('Tran Xuan Bac','0359788344','bac1','bac1')," +
            "('Tran Khanh Linh','0359788344','linh2','linh2');";
//    public  static  final  String insertKhachHang=" insert into khachhang(name,sdt) " +
//            "values('Nguyen Tuan Dat','0234674456')," +
//            "('Nguyen Van Tung','03594444555');";
    public  static final String insertLoaiSP="insert into loaisanpham(tenLoai) " +
            "values('ao nam'),('ao nu'),('quan nam'),('quan nu'),('phu kien nam'),('phu kien nu');";
    public static  final String insertSanPham="insert into sanpham(tenSP,maLoai,soLuong,giaBan,moTa) " +
            "values('áo cổ lọ','1','10','100000',''),('áo 2 dây','1','12','50000','Áo Khoác Nam Dù Trượt Nước Vải Gân Mờ MOP 1029. Áo khoác dù trượt nước phù hợp cho việc sử dụng trong những thời điểm mưa nhỏ bất thường, sương mù. Bề mặt vải có tính năng trượt nước và lớp cán xử lý TPU bên trong vừa ngăn thấm vào trong nhưng vẫn đảm bảo độ thoáng khí khi mặc. Form áo dáng rộng thời trang, được tăng cường thêm 2 túi dây kéo ở thân trước, túi ngang ở bên trong áo, thân sau tăng cường lỗ thoát hơi. Logo tay áo in phản quang trong bóng tối.')," +
            "('áo Hồng','2','12','50000',''),('áo 2 dây nữ','2','12','50000','Áo Khoác Nữ Dù Trượt Nước Vải Gân Mờ MOP 1029. Áo khoác dù trượt nước phù hợp cho việc sử dụng trong những thời điểm mưa nhỏ bất thường, sương mù. Bề mặt vải có tính năng trượt nước và lớp cán xử lý TPU bên trong vừa ngăn thấm vào trong nhưng vẫn đảm bảo độ thoáng khí khi mặc. Form áo dáng rộng thời trang, được tăng cường thêm 2 túi dây kéo ở thân trước, túi ngang ở bên trong áo, thân sau tăng cường lỗ thoát hơi. Logo tay áo in phản quang trong bóng tối.')," +
            "('quần Gucci','3','12','50000',''),('quần AB','3','12','50000','Quần Dù Trượt Nước Vải Gân Mờ MOP 1029. Áo khoác dù trượt nước phù hợp cho việc sử dụng trong những thời điểm mưa nhỏ bất thường, sương mù. Bề mặt vải có tính năng trượt nước và lớp cán xử lý TPU bên trong vừa ngăn thấm vào trong nhưng vẫn đảm bảo độ thoáng khí khi mặc. Form áo dáng rộng thời trang, được tăng cường thêm 2 túi dây kéo ở thân trước, túi ngang ở bên trong áo, thân sau tăng cường lỗ thoát hơi. Logo tay áo in phản quang trong bóng tối.')," +
            "('quần Gucci Nữ','4','12','50000',''),('quần AB Nữ','4','12','50000','Áo Khoác Nam Dù Trượt Nước Vải Gân Mờ MOP 1029. Áo khoác dù trượt nước phù hợp cho việc sử dụng trong những thời điểm mưa nhỏ bất thường, sương mù. Bề mặt vải có tính năng trượt nước và lớp cán xử lý TPU bên trong vừa ngăn thấm vào trong nhưng vẫn đảm bảo độ thoáng khí khi mặc. Form áo dáng rộng thời trang, được tăng cường thêm 2 túi dây kéo ở thân trước, túi ngang ở bên trong áo, thân sau tăng cường lỗ thoát hơi. Logo tay áo in phản quang trong bóng tối.')," +
            "('Túi LUONVUITUOI','5','12','50000',''),('Ví cá Sấu','5','12','50000','')," +
            "('ví Da','6','12','50000',''),('Ví ???','6','12','50000','')";
    public  static  final String insertHoaDon="insert into hoadon(maNV,tenKH,ngayLap,tongTien)" +
            "values('1','2','10/10/2022','250000')";
    public  static final  String insertcthd="insert into chitiethoadon(maHD,maSP,soLuong,thanhTien) " +
            "values('1','1','2','200000'),('1','2','1','50000');";
}
