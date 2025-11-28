# 📱 TechZone - Ứng dụng Thương mại điện tử Mobile

> Đồ án môn học: Lập trình thiết bị di động (Mobile Application Development)

**TechZone** là ứng dụng Android native giúp người dùng tìm kiếm, xem thông tin và đặt mua các thiết bị công nghệ (Điện thoại, Laptop, Phụ kiện...) trực tuyến với giao diện hiện đại và thân thiện.

---

## 📸 Demo Giao diện (Screenshots)

Dưới đây là một số hình ảnh thực tế của ứng dụng:

| Màn hình Intro | Màn hình Đăng nhập |
|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/fae13260-4937-41a6-97cc-2e27267c8b6f" width="200" /> | <img src="https://github.com/user-attachments/assets/f5bc2b22-92ed-42c4-990d-e214d3854667" width="200" /> |
| *Intro / Onboarding* | *Login Screen* |

| Màn hình Đăng ký | Xác thực OTP |
|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/c1ccb81f-e3ae-4fd0-bccf-b44d95f0213b" width="200" /> | <img src="https://github.com/user-attachments/assets/dca88aaa-e9c1-42b1-a6cd-416b45221ef3" width="200" /> |
| *Register Screen* | *OTP Verification* |

| Màn hình Trang chủ | Danh mục sản phẩm |
|:---:|:---:|
| <img src="screenshots/home.png" width="200" alt="Home Screen (Cập nhật sau)" /> | <img src="https://github.com/user-attachments/assets/187d3b43-ba23-4d6d-804a-8da0ecfd301d" width="200" /> |
| *Home Screen* | *Categories* |

---

## ✨ Tính năng chính (Features)

* **Xác thực người dùng:**
    * Màn hình Intro giới thiệu ứng dụng.
    * Đăng nhập / Đăng ký tài khoản.
    * Xác thực bảo mật qua mã OTP.
* **Trang chủ (Home):**
    * Banner quảng cáo khuyến mãi.
    * Thanh tìm kiếm sản phẩm (Search Bar).
    * Danh sách danh mục sản phẩm (Categories) cuộn ngang.
    * Danh sách sản phẩm nổi bật / bán chạy.
* **Điều hướng:**
    * Bottom Navigation Bar tùy chỉnh với hiệu ứng cắt góc (Cradle).
    * Nút Giỏ hàng (Floating Action Button) nổi bật.
* **Chức năng khác:**
    * Xem chi tiết sản phẩm.

---

## 🛠 Công nghệ sử dụng (Tech Stack)

* **Ngôn ngữ:** Java
* **IDE:** Android Studio
* **Kiến trúc:** MVC / MVVM
* **Giao diện (UI):**
    * XML Layouts (ConstraintLayout, CoordinatorLayout, ScrollView).
    * Material Design Components (BottomAppBar, FloatingActionButton, CardView).
    * RecyclerView (Hiển thị danh sách).
* **Thư viện (Libraries):**
    * [Glide](https://github.com/bumptech/glide): Load ảnh từ URL.
    * [Retrofit](https://square.github.io/retrofit/): Xử lý API Networking.
    * [Gson](https://github.com/google/gson): Chuyển đổi JSON.
    * [CircleImageView](https://github.com/hdodenhof/CircleImageView): Avatar tròn.

---

## 👥 Thành viên nhóm (Team Members)

| STT | Họ và Tên | MSSV |
|:---:|:---|:---:|
| 1 | **Đỗ Đoàn Duy Hoàng** | 23162025 |
| 2 | **Huỳnh Thiên Hạo** | 23162021 |
| 3 | **Trương Nguyễn Minh Hậu** | 23162022 |
| 4 | **Bùi Việt Hoàng** | 23162024 |
| 5 | **Võ Gia Huân** | 23162027 |
| 6 | **Cao Đăng Huy** | 23162028 |
| 7 | **Trần Nguyễn Nhật Huy** | 23162031 |
| 8 | **Dương Đình Ngọc Khang** | 23162036 |


## 📝 Trạng thái phát triển
* [x] Thiết kế UI/UX (Intro, Login, Register, OTP, Home)
* [x] Chức năng Xác thực (Đăng nhập/Đăng ký/OTP)
* [x] Hiển thị danh sách sản phẩm & Danh mục
* [ ] Chức năng Giỏ hàng & Thanh toán (Đang phát triển)

---
**TechZone © 2025 - Developed with ❤️ by Group f10_Peter**
