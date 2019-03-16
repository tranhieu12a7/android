-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 06, 2019 lúc 09:35 AM
-- Phiên bản máy phục vụ: 10.1.34-MariaDB
-- Phiên bản PHP: 7.1.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `sqlandroid_appbanhang`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id_donhang` int(11) NOT NULL,
  `id_sanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `thanhtien` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `menucha`
--

CREATE TABLE `menucha` (
  `id_menucha` int(11) NOT NULL,
  `ten_menucha` text NOT NULL,
  `hinh_menucha` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `menucha`
--

INSERT INTO `menucha` (`id_menucha`, `ten_menucha`, `hinh_menucha`) VALUES
(1, 'Trang Chủ', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQw2A772CTUFN7zBvXvJqug4QEPvK2X8G0vXrqKd-5KgBUj0raE\r\n'),
(2, 'Laptop', 'https://cdn4.iconfinder.com/data/icons/proglyphs-communication-and-devices/512/Laptop-512.png'),
(3, 'Điện Thoại\r\n', 'https://banner2.kisspng.com/20180329/bsq/kisspng-iphone-computer-icons-smartphone-telephone-handphone-5abcd76beacec8.8413586615223253559618.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `menucon`
--

CREATE TABLE `menucon` (
  `id_menucon` int(11) NOT NULL,
  `ten_menucon` text NOT NULL,
  `hinh_menucon` text NOT NULL,
  `id_menucha` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `menucon`
--

INSERT INTO `menucon` (`id_menucon`, `ten_menucon`, `hinh_menucon`, `id_menucha`) VALUES
(1, 'Samsung', 'https://cdn.iconscout.com/icon/free/png-256/samsung-226432.png', 3),
(2, 'Apple', 'https://image.flaticon.com/icons/png/512/23/23656.png', 3),
(3, 'Dell', 'http://cdn.onlinewebfonts.com/svg/img_263083.png', 2),
(4, 'MacBook', 'https://imageog.flaticon.com/icons/png/512/23/23656.png?size=1200x630f&pad=10,10,10,10&ext=png&bg=FFFFFFFF', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoidung`
--

CREATE TABLE `nguoidung` (
  `id_donhang` int(11) NOT NULL,
  `ten_nguoidung` text NOT NULL,
  `diachi_nguoidung` text NOT NULL,
  `sdt` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id_sp` int(11) NOT NULL,
  `ten_sp` text NOT NULL,
  `hinh_sp` text NOT NULL,
  `sp_hot` bit(1) NOT NULL,
  `sp_khuyenmai` bit(1) NOT NULL,
  `giabandau_sp` int(20) NOT NULL DEFAULT '0',
  `giahientai_sp` int(20) NOT NULL,
  `gioithieu_sp` text,
  `id_menucon` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id_sp`, `ten_sp`, `hinh_sp`, `sp_hot`, `sp_khuyenmai`, `giabandau_sp`, `giahientai_sp`, `gioithieu_sp`, `id_menucon`) VALUES
(1, 'Điện thoại iPhone X 256GB', 'https://cdn.tgdd.vn/Products/Images/42/114111/iphone-x-256gb-silver-400x460.png', b'1', b'0', 0, 27990000, 'Đến hẹn lại lên, năm nay Apple giới thiệu tới người dùng thế hệ tiếp theo với 3 phiên bản, trong đó có cái tên iPhone Xs với những nâng cấp mạnh mẽ về phần cứng đến hiệu năng, màn hình cùng hàng loạt các trang bị cao cấp khác. \r\nHiệu năng đỉnh cao đến từ con chip Apple A12\r\nNgoài điện thoại thì năm nay iPhone cũng đã chính thức ra mắt chip A12 bionic thế hệ mới với những nâng cấp vượt trội về mặt hiệu năng.\r\n\r\nChip A12 trên điện thoại iPhone Xs chính hãng\r\n\r\nApple A12 Bionic được xây dựng trên tiến trình 7nm đầu tiên của hãng với 6 nhân giúp iPhone Xs có được một hiệu năng “vô đối” cùng khả năng tiết kiệm năng lượng tối ưu', 2),
(2, 'Điện thoại Samsung Galaxy M20', 'https://cdn.tgdd.vn/Products/Images/42/195314/samsung-galaxy-m20-400x460.png', b'1', b'0', 0, 4990000, 'Nổi bật hơn cả trong lần ra mắt bộ đôi dòng M của Samsung, chiếc điện thoại Galaxy M20 có màn hình mới Infinity-V, dung lượng pin cực khủng lên tới 5000 mAh, camera siêu rộng và nhiều tính năng hấp dẫn khác.\r\nMàn hình Infinity-V mới đẹp mắt\r\nChiếc điện thoại Samsung mới có thiết kế nguyên khối, mặt kính cong 2.5D bo góc tạo cảm giác chắc tay khi cầm điện thoại.\r\n\r\nThiết kế điện thoại Samsung Galaxy M20 chính hãng\r\n\r\nMàn hình tràn viền Infinity-V của Galaxy M20 có kích thước 6.3 inch với độ phân giải cao lên tới Full HD+ đem lại hình ảnh sống động và đẹp mắt.\r\n\r\nMàn hình điện thoại Samsung Galaxy M20 chính hãng\r\n\r\nVới thiết kế màn hình trên, Galaxy M20 cho phép tỉ lệ hiển thị màn hình của máy được tối đa, giúp bạn có thể tương tác và trải nghiệm tốt hơn. \r\n\r\nCụm camera kép góc rộng\r\nBộ đôi camera kép ở mặt sau của máy có độ phân giải 13 MP + 5 MP trong đó ống kính góc rộng là 5 MP cho phép chụp ảnh được bao quát và lạ mắt hơn.\r\n\r\nCamera kép điện thoại Samsung Galaxy M20 chính hãng\r\n\r\nNgoài ra, những tính năng chụp ảnh xóa phông, tự động lấy nét, HDR, Panorama,... sẽ đáp ứng được nhu cầu nắm bắt khoảnh khắc hàng ngày của bạn và chia sẻ với bạn bè.\r\n\r\nẢnh chụp từ điện thoại Galaxy M20 chính hãng\r\n\r\nỞ mặt trước, camera sefie có độ phân giải 8 MP có chế độ làm đẹp cộng thêm tính năng nhận diện khuôn mặt sẽ giúp bạn có được những tấm ảnh tự sướng chất lượng và xinh đẹp', 1),
(3, 'Điện thoại Samsung Galaxy J4 Core', 'https://cdn.tgdd.vn/Products/Images/42/193388/samsung-galaxy-j4-core-1-400x460.png', b'0', b'1', 3090000, 2390000, 'Android Go nhẹ nhàng, tối ưu tốt\r\nAndroid Go Edition là phiên bản hệ điều hành được tinh chỉnh cho những smartphone cấu hình thấp như Samsung Galaxy J4 Core nên mọi trải nghiệm trên máy khá mượt mà.\r\n\r\nAndroid Go trên điện thoại J4 Core chính hãng\r\n\r\nMột số ứng dụng mặc định cũng bị lược bỏ để máy trở nên nhẹ nhàng hơn và không bị giật lag trong quá trình sử dụng.\r\n\r\nTrải nghiệm điện thoại Galaxy J4 Core chính hãng\r\n\r\nSở hữu thiết kế đặc trưng của Samsung\r\nThiết kế mặt trước của chiếc smartphone Samsung giá rẻ có nhiều nét giống với những chiếc máy Samsung trước đây với các góc cạnh bo cong mềm mại, cho cảm giác cầm nhẹ tay.\r\n\r\nThiết kế điện thoại J4 Core chính hãng\r\n\r\n \r\n\r\nGalaxy J4 Core sở hữu màn hình có kích thước lên đến 6 inch tỷ lệ màn hình mới 18:9 cho trải nghiệm mới mẻ trên không gian thoải mái.\r\n\r\nMàn hình điện thoại J4 Core chính hãng\r\n\r\nCamera được đầu tư nhiều\r\nMáy sở hữu camera chính có độ phân giải 8 MP với chất lượng hình ảnh cải thiện nhiều, nhất là trong điều kiện thiếu sáng.\r\n\r\nCamera selfie điện thoại Galaxy J4 Core chính hãng\r\n\r\nCamera trước có độ phân giải khá khiêm tốn, chỉ 5 MP. Tuy nhiên, khi trải nghiệm thực tế mình đánh giá ảnh chụp khá ổn, màu sắc tươi tắn đặc biệt là chế độ làm mịn da nhìn khá thích mắt.\r\n\r\nDung lượng pin khá\r\nSamsung Galaxy J4 Core được trang bị viên pin có dung lượng ổn 3.300 mAh và với màn hình HD+ của máy không tiêu tốn quá nhiều năng lượng đã giúp J4 Core có thời lượng pin tốt.\r\n\r\nDung lượng pin điện thoại Galaxy J4 Core chính hãng\r\n\r\nVới viên pin này bạn có thể thoải mái dùng Samsung Galaxy J4 Core trong 1 ngày với các tác vụ thông thường, kết hợp giữa 4G và Wifi mà không lo về việc phải sạc máy.', 1),
(4, 'Laptop Dell Vostro 3578 i7 8550U/8GB/1TB/2GB 520/Win10/(NGMPF11)', 'https://cdn.tgdd.vn/Products/Images/44/166602/dell-vostro-3578-ngmpf11-450x300-600x600-600x600.jpg', b'0', b'0', 0, 20990000, 'Laptop Dell Vostro 3578 là dòng máy tính xách tay mới của Dell trong năm 2018 với cấu hình cực cao bao gồm vi xử lý i7 8550U thế hệ thứ 8 có hiệu năng vượt trội, card màn hình rời Radeon 520 và 8 GB RAM. Với cấu hình mạnh mẽ máy có thể chạy tốt các ứng dụng phục vụ cho công việc, học tập, xử lý đồ hoạ cũng như chơi game ở mức cấu hình tầm trung khá mượt mà.\r\nThiết kế truyền thống cứng cáp\r\nVẫn giữ nguyên thiết kế quen thuộc trên dòng máy Vostro chiếc laptop có phần thân máy khá chắc chắn với vỏ nhựa và màu đen mạnh mẽ. Phần tản nhiệt lớn cũng giúp cho máy hoạt động mát mẻ trong thời gian dài, không bị nóng quá nhiều khi chạy các ứng dụng nặng.\r\n\r\nThiết kế của máy tính xách tay Dell Vostro 3578\r\n\r\nHiệu năng mạnh mẽ\r\nĐiểm nâng cấp đáng kể nhất của Dell Vostro năm nay nằm ở phần cấu hình với Core i7 thế hệ thứ 8 cho hiệu năng khá vượt trội so với thế hệ cũ, nó còn tiết kiệm pin hơn mang lại thời gian sử dụng pin dài hơn.\r\n\r\nMáy tính xách tay Dell Vostro 3578 được trang bị 8 GB RAM cùng card đồ hoạ rời đảm bảo cho máy chạy tốt các ứng dụng phục vụ cho công việc cũng như giải trí một cách mượt mà, không có độ trễ.\r\n\r\nHiệu năng trên máy tính xách tay Dell Vostro 3578\r\n\r\nMàn hình Full HD 15.6 inch\r\nTrang bị màn hình 15.6 inch độ phân giải Full HD giúp máy hiển thị rõ nét hơn. Màn hình còn được phủ một lớp chống chói giúp hiển thị tốt trong môi trường có nhiều ánh sáng mạnh như ngoài trời, phòng có nhiều đèn. Màn hình Full HD còn giúp bạn xem phim, hình ảnh sắc nét hơn.\r\n\r\nMàn hình của máy tính xách tay Dell Vostro 3578\r\n\r\nBàn phím tích hợp phím số\r\nBàn phím trên máy sử dụng bàn phím full size với các phím số bên phải tiện lợi hơn cho người dùng cần làm việc liên quan đến tính toán, hay những con số. Việc gõ phím cũng hoàn toàn thoải mái do các phím được bố trí khoảng cách vừa phải, không gây mỏi tay.\r\n\r\nBàn phím của máy tính xách tay Dell Vostro 3578\r\n\r\nCác cổng kết nối và thời lượng pin\r\nThời lương pin trên máy vẫn đảm bảo được khoảng 3 đến 4 giờ hoạt động cho 1 lần sạc, tuỳ vào tác vụ nặng hay nhẹ khác nhau mà thời lượng pin có thể thay đổi ngắn hơn hoặc dài hơn. Các cổng kết nối trên máy vẫn được bố trí đầy đủ với cổng USB, VGA để xuất hình, cổng LAN kết nối dây mạng đảm bảo sự tiện lợi nhất cho người dùng.', 3),
(5, 'Laptop Apple Macbook Air MREE2SA/A i5/8GB/128GB (2018)\r\n\r\n', 'https://cdn.tgdd.vn/Products/Images/44/197305/apple-macbook-air-mree2sa-a-i5-8gb-128gb-133-gold-600x600.jpg', b'0', b'0', 0, 31990000, 'Đặc điểm nổi bật của Apple Macbook Air MREE2SA/A i5/8GB/128GB (2018)\r\n\r\nMacbook air 2018 vừa được Apple cho ra mắt vào cuối năm 2018, mang trong trong mình vẻ ngoài siêu mỏng, siêu nhẹ và một cấu hình đủ khoẻ để chạy tốt tất cả các ứng dụng hiện nay. Macbook air 2018 hứa hẹn sẽ là sự lựa chọn đáng giá cho nhân viên văn phòng, doanh nhân.\r\nThiết kế sang trọng, đẳng cấp\r\nLà sự lột xác hoàn toàn so với thế các thế hệ trước, chiếc laptop Apple mang trong mình vẻ ngoài đầy sự tinh tế với kim loại nguyên khối màu vàng đồng, logo quả táo không còn phát sáng tiệm cận với các đàn anh macbook pro. Cùng kích thước màn hình so với thế hệ trước, song Macbook Air 2018 nhỏ và nhẹ hơn hẳn khi đặt cạnh người tiền nhiệm (1,25 kg và 15,6 mm so với 1,35 kg và 17 mm), sự cải tiến mạnh mẽ này sẽ rất thuận tiện cho doanh nhân, nhân viên văn phòng trong việc di chuyển hằng ngày gặp đối tác, khách hàng.\r\n\r\nThiết kế hiện đại, siêu nhẹ trên laptop nhỏ gọn macbook air 2018\r\nMàn hình Retina đầu tiên trên dòng Macbook air\r\nĐiều làm nhiều khách hàng phân vân nhất khi quyết định mua các thế hệ trước của Macbook air là màn hình chưa thật sự rõ nét. Tuy nhiên với thế hệ 2018, Apple đã trang bị màn hình retina sắc nét đến từng chi tiết nhỏ.\r\n\r\nMàn hình retina sắc nét trên laptop nhỏ gọn Macbook Air MREE2SA/A (2018)\r\n\r\nCấu hình xử lý đồ hoạ tốt\r\nBên trong mẫu laptop nhỏ gọn là chip xử lý Intel core i5 , tiết kiệm năng lượng cùng 8GB RAM cho hiệu năng tốt. Các phản hồi trong ứng dụng đồ hoạ photoshop, ai,... nhanh chóng.\r\n\r\nHiệu năng mạnh mẽ trên laptop nhỏ gọn Macbook Air MREE2SA/A (2018)\r\n\r\nHỗ trợ eGPU qua cổng thunderbolt 3\r\nCác dòng Macbook Air thường chỉ sử dụng card đồ hoạ tích hợp, việc hỗ trợ eGPU qua cổng thunderbolt 3 sẽ giúp bạn kết nối với card đồ hoạ rời nâng cao khả năng xử lý đồ hoạ, làm phim ảnh.\r\n\r\neGPU qua thunderbolt 3 kết nối card đồ hoạ rời trên laptop nhỏ gọn Macbook Air MREE2SA/A (2018)\r\n\r\nForceTouch rộng hơn thế hệ trước\r\nCảm ứng lực Force Touch trên phiên bản mới cũng có diện tích lớn hơn, từ đó mang lại trải nghiệm rộng rãi và thoải mái hơn khi sử dụng.\r\n\r\nForcetouch rộng hơn thế hệ trước trên laptop nhỏ gọn Macbook Air MREE2SA/A (2018)\r\n\r\nCảm biến vân tay trên nút nguồn\r\nTrên MacBook Air 2018 phím nguồn mới được tích hợp cảm biến vân tay Touch ID chỉ cần chạm nhẹ để mở khoá màn hình, không cần nhập mật khẩu rườm rà.\r\n\r\nCảm biến vân tay trên nút nguồn trên laptop nhỏ gọn Macbook Air MREE2SA/A (2018)\r\n\r\nDãy loa phía trước âm thanh sống động\r\nLoa của chiếc macbook air 2018 được bố trí bên ngoài ở hai bên bàn phím thay vì phía trên như thế hệ trước cho âm thanh to rõ và trong trẻo, sự chuyển âm thanh từ 2 loa rõ ràng, trải nhiệm âm thanh không thua kém trong rạp phim.\r\n\r\n2 dãy loa phía trước hứa hẹn âm thanh to và rõ trên laptop nhỏ gọn Macbook Air MREE2SA/A (2018)\r\n\r\nBàn phím Butterfly thế hệ 3\r\nBàn phím máy có thiết kế mới hoàn toàn với cơ chế Butterfly thế hệ thứ 3, cho phép gõ với lực nhẹ hơn, độ bền cao hơn cũng như kháng bụi tốt hơn.\r\n\r\nMàn phím cánh bướm thế hệ 3 trên laptop nhỏ gọn Macbook Air MREE2SA/A (2018)\r\n\r\nCổng kết nối Type C chuẩn Thunderbolt 3 đa chứng năng\r\nCác cổng kết nối trên Macbook air 2018 đã được rút gọn đáng kể. Ở cạnh trái, cổng USB và sạc MagSafe không còn, thay vào đó là hai cổng USB Type C hỗ trợ kết nối dữ liệu và sạc. Jack tai nghe 3.5 mm đã được dời sang bên phải. \r\n\r\nCổng type C chuẩn Thunderbolt 3 đầy đủ chức năng trên laptop nhỏ gọn Macbook Air MREE2SA/A (2018)\r\n\r\n\r\n', 4);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id_donhang`);

--
-- Chỉ mục cho bảng `menucha`
--
ALTER TABLE `menucha`
  ADD PRIMARY KEY (`id_menucha`);

--
-- Chỉ mục cho bảng `menucon`
--
ALTER TABLE `menucon`
  ADD PRIMARY KEY (`id_menucon`),
  ADD KEY `KhoaNgoai_menucon_menucha` (`id_menucha`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id_sp`),
  ADD KEY `Khoangoai_sanpham_menucon` (`id_menucon`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id_donhang` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `menucha`
--
ALTER TABLE `menucha`
  MODIFY `id_menucha` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `menucon`
--
ALTER TABLE `menucon`
  MODIFY `id_menucon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id_sp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `menucon`
--
ALTER TABLE `menucon`
  ADD CONSTRAINT `KhoaNgoai_menucon_menucha` FOREIGN KEY (`id_menucha`) REFERENCES `menucha` (`id_menucha`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `Khoangoai_sanpham_menucon` FOREIGN KEY (`id_menucon`) REFERENCES `menucon` (`id_menucon`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
