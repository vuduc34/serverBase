package project.psa.QLDangVien.common;

public class constant {
    //danh sách trạng thái của chi bộ
    public static final class CHIBO {
        public static  final String HOATDONG = "hoatdong";
        public static  final String GIAITHE = "giaithe";
        public static  final String TAMDUNG = "tamdung";
    }

    public static final class LOAICHIBO {
        public static  final String DANGUY = "danguy";
        public static  final String DANGBO = "dangbo";
        public static  final String CHIBO = "chibo";
    }

    //danh sách trạng thái của đảng viên
    public static final class DANGVIEN {
        public static  final String DUBI = "dubi";
        public static  final String CHINHTHUC = "chinhthuc";
        public static  final String KHAITRU = "khaitru";
    }

    //danh sách trạng thái của thông tin: đảng viên, hồ sơ đảng, tin tức
    public static final class THONGTIN {
        public static  final String APPROVED = "approved";
        public static  final String PENDING = "pending";
        public static  final String SAVED = "saved";
        public static  final String REJECT = "reject";
    }

    //danh sách các loại phê duyệt
    public static final class LOAIPHEDUYET {
        public static  final String TINTUC = "tintuc";
        public static  final String THONGTINDANGVIEN = "thongtin";
        public static  final String HOSODANG = "hoso";
    }

    //danh sách trạng thái của xếp loại chi bộ
    public static final class XEPLOAI {
        public static  final String HOANTHANHXUATSACNHIEMVU = "xuatsac";
        public static  final String HOANTHANHTOTNHIEMVU = "tot";
        public static  final String HOANTHANHNHIEMVU = "hoanthanh";
        public static  final String KHONGHOANTHANHNHIEMVU = "khonghoanthanh";
    }

    //danh sách trạng thái của hồ sơ đảng (3 tập: tập 1, tập 2, tập 3)
    public static final class LOAIHOSO {
        public static  final String TAP1 = "tap1";
        public static  final String TAP2 = "tap2";
        public static  final String TAP3 = "tap3";
    }
    //danh sách trạng thái của đảng phí
    public static final class DANGPHI {
        public static  final String CHUAHOANTHANH = "chuahoanthanh";
        public static  final String HOANTHANH = "hoanthanh";
    }


    public static final class STATUS {
        public static final Long ACTIVE = 1L;
        public static final Long DELETED = -1L;
        public static final Long DE_ACTIVE = 0L;
        public static  final String AVAILABLE = "available";
        public static  final String UN_AVAILABLE = "unavailable";
        public static  final String UN_CONFIRMED = "unconfirmed";
        public static  final String PREPARING = "preparing";
        public static  final String DONE = "done";
        public static  final String CANCEL = "cancel";
        public static final String REJECTED = "rejected";
        public static final String DELETEED = "deleted";
        public static final String PAID = "paid";
    }

    public static final class RESULT_CODE {
        public static final Long SUCCESS = 0L;
        public static final Long ERROR = -1L;
        public static final Long NOT_FOUND = 404L;
        public static final Long NOT_ALLOWED = 405L;
        public static final Long UNAUTHORIZED = 403L;


    }

    public static final class API {

        public static final String PREFIX = "/api/v1/project";
        public static final String PREFIX_AUTH = "/api/v1/project/auth";
    }

    public static final class ACTION {

        public static final String CANCEL = "cancel";
        public static final String CONFIRM = "confirm";
        public static final String DONE = "done";
        public static final String REJECTED = "rejected";

    }

    public static final class MESSAGE {
        public static final String SUCCESS = "Successfully";
        public static final String ERROR = "Error";
        public static final String NOT_FOUND_USER = "Not found user in system";
        public static final String PASSWORD_INCORRECT = "Password incorrect";
        public static final String USERNAME_EXIST = "Username already exist";
        public static final String EMAIL_EXIST = "Email already exist";
        public static final String NOT_FOUND_HANDLE = "Not found api";
        public static final String NOT_ALLOWED = "Method not allowed";
        public static final String ACCOUNT_DEACTIVE = "Account is deactive";
        public static final String ROLE_ERROR = "Role name is not exist";
    }

    public static final class ROLE {
        public static final String USER = "ROLE_USER";
        public static final String STAFF = "ROLE_STAFF";
        public static final String ADMIN = "ROLE_ADMIN";
    }

}
