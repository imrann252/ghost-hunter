# 👻 Ghost Hunter Inc.

> JavaFX kullanılarak geliştirilmiş, nesne yönelimli programlama (OOP) mimarisine dayanan, dinamik çarpışma tespiti ve animasyonlu seviye mekaniklerine sahip 2D hayalet avlama oyunu.

---

### 🎮 Proje Hakkında
**Ghost Hunter Inc.**, retro arcade hissiyatını modern masaüstü arayüz bileşenleriyle harmanlayan bir 2D hayatta kalma ve avlanma oyunudur. Oyuncu, harita üzerinde beliren doğaüstü varlıkları tespit edip etkisiz hale getirirken hayatta kalmaya ve en yüksek skoru toplamaya çalışır.

---

### ✨ Öne Çıkan Özellikler
* **Entity & Collision Management:** Oyuncu, hayaletler ve mermiler/tuzaklar arasındaki anlık geometrik çarpışma kontrolleri (Bounding Box / Hitbox).
* **Game Loop & Animasyon Döngüsü:** Akıcı kare hızları (FPS) ve arka plan/karakter durum animasyonları için optimize edilmiş `AnimationTimer` mekaniği.
* **Katmanlı UI/HUD:** Can barı, skor tablosu, aktif ekipmanlar ve dalga sayaçlarını barındıran özel JavaFX arayüz bileşenleri.
* **OOP & Genişletilebilir Mimari:** Kalıtım, çok biçimlilik (polymorphism) ve modüler varlık hiyerarşisiyle tasarlanmış temiz kod yapısı.

---

### 🛠️ Teknoloji Yığını
* **Dil:** Java (JDK 17+)
* **Grafik & UI Framework:** JavaFX
* **Derleme & Bağımlılık Yönetimi:** Maven / Gradle
* **Tasarım Deseni:** Object-Oriented Architecture (OOP) & Component/State Pattern

---

### 🕹️ Kontroller & Oynanış

| Tuş | Eylem |
| :--- | :--- |
| **W, A, S, D** / **Yön Tuşları** | Karakter Hareketi |
| **Mouse / Sol Tık** | Nişan Alma & Saldırı |
| **Space** | Özel Yetenek / Aksiyon |
| **ESC / P** | Oyunu Duraklat (Pause) |

---

### 💻 Kurulum ve Çalıştırma

Projeyi yerel ortamınızda çalıştırmak için aşağıdaki adımları izleyin:

#### 1. Depoyu Klonlayın
```bash
git clone [https://github.com/imrann252/ghost-hunter.git](https://github.com/imrann252/ghost-hunter.git)
cd ghost-hunter
