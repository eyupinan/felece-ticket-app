# Ticket Uygulaması
## Arayüz 
### Login/Register
 * ``` /register ``` sayfasından user hesabı oluşturulabilir ve ``` /login ``` sayfasında hesaba giriş yapılabilir.
 * register sayfasından sadece user hesabı oluşturulabilir, admin hesabı ise başka bir admin tarafından oluşturulması gerekir. Default admin bilgileri:
 ```
 admin.username=admin
 admin.password=administrator
 ```
 şeklindedir.(Chrome şifre ile alakalı uyarı verebilir sebebi şifrenin kısa olmasıdır.)
### User
 * Arayüzde kayıt olmamış kişiler tarafından görüntülenebilen tek sayfa ``` /routes ``` sayfasıdır. Bu sayfada kullanıcılar filtreleme işlemi ile tarih ve durakları girerek rotalarını seçip rezervasyon tuşuna bastıklarında login sayfasına yönlendirilirler. Kullanıcı kayıt olup login olduktan sonra rezervasyon butonuna basması ile bastığı route ve kullanıcıların bilgileri ile birlikte ticket oluşturulmaktadır. 
 * Ticket'lar ``` /tickets ``` sayfasında görüntülenebilmektedir. Bu ssayfada kullanıcı ticket'larını filtreleyebilir, daha sonraki tarihte bir rotaya erteleyebilir veya iptal edebilir.
 * ``` /profile ``` sayfası ile kullanıcı kişisel bilgilerini görüntüleyebilir, buradan bilgilerini güncelleyebilir ve hesabı inaktif hale getirebilir.
### Admin
 * Admin ``` /admin/routes ``` sayfasından var olan tüm rotaları filtreleyerek görüntüleyebilir. Burada ``` Details ``` butonuna basarak route ayrıntılarını görüntüleyip buradan route bilgilerini değiştirebilir veya rota'yı inaktif hale getirebilir. Yeni bir rota oluşturmak için ``` Create New ``` butonu kullanılır. Bu sayfadan rota başlangıç ve bitiş noktası, tarihi ve araç plakası belirtilerek rota oluşturulur.
 * Admin ``` /admin/tickets ``` sayfasından bütün kullanıcıların bütün ticket'larını görüntüleyebilir. Bu ticket'ları erteleyip iptal edebilir. ``` Create New ``` butonu ile bir kullanıcı için bir ticket oluşturulabilir. Burada rota bilgileri girilerek ve kullanıcı adı girilerek ticket oluşturulur.
 * Admin ``` /admin/users ``` sayfasından tüm kullanıcıları görüntüleyebilir. Kullanıcı bilgileri ``` Details ``` butonuna basarak görüntülenebilir, güncellenebilir. Bu sayfada ``` Create New ``` Button'una basılarak yeni bir kullanıcı oluşturulabilir. Bu yeni kullanıcı oluşturulması esnasında yeni kullanıcının rolü de belirlenebilir.
 * Admin ``` /admin/settings ``` sayfasından yeni bir durak veya yeni bir araç ekleyebilir,güncelleyebilir veya silebilir.
 * Admin ``` /profile ``` adresinden kendi bilgilerini görüntüleyip güncelleyebilir.
 ### LeftBar
 * her sayfada sol tarafta bir yönlendirme çubuğu bulunur. Bu kısmın özellikleri kullanıcıya göre değişiklik gösterir.
## Api
### Login 
 * ``` /perform_login ``` adresi ile login olunabilir.
 * ``` /perform_logout ``` adresi ile log out olunabilir.
 * ``` /api/user/create ``` adresi ile kayıt olunabilir.
### User
 * user ``` /api ``` ön ekine sahip ``` /route ``` , ``` /ticket ``` ,  ``` /user ``` adresleri üzerinden kendisine has işlemler yapabilir.
 * user kısmında ``` /api/user/create ``` adresi herkese açık bırakılmıştır. Bu adres ile kayıt işlemi yapılabilir.
### Admin 
* user ``` /admin/api ``` ön ekine sahip ``` /route ``` , ``` /ticket ``` ,  ``` /user ``` adresleri üzerinden kendisine has işlemler yapabilir.