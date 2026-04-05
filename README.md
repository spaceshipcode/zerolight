# zerolight

This is a root application used to reduce Android screen brightness below system limits. The application was written to solve the problem I encountered with custom ROMs, where the brightness couldn't be reduced to the minimum limit, and it can be tried in such software bugs. It has a simple structure; it writes the new value directly to the kernel file that controls the brightness, thus not making permanent changes or increasing the risk of system corruption. The write operation is performed in the `/sys/class/leds/lcd-backlight/brightness` location; essentially, only one command is executed: `echo [VALUE] > /sys/class/leds/lcd-backlight/brightness`.
It has been specifically tested on a Redmi Note 9 (Merlin) with a custom ROM. It hasn't been tested on different devices, but since it doesn't make permanent changes, trying it shouldn't cause any problems.

---
Bu, Android ekran parlaklığını sistem sınırlarının altına düşürmek için kullanılan bir root uygulamasıdır. Uygulama, parlaklığın minimum sınıra düşürülemediği özel ROM'larda karşılaştığım bir sorunu çözmek için yazılmıştır ve bu tür yazılım hatalarında denenebilir. Basit bir yapıya sahiptir; yeni değeri doğrudan parlaklığı kontrol eden çekirdek dosyasına yazar, böylece kalıcı değişiklikler yapmaz veya sistem bozulma riskini artırmaz. Yazma işlemi `/sys/class/leds/lcd-backlight/brightness` konumunda gerçekleştirilir; esasen, yalnızca bir komut yürütülür: `echo [DEĞER] > /sys/class/leds/lcd-backlight/brightness`. 
Özellikle özel bir ROM'a sahip bir Redmi Note 9 (Merlin) üzerinde test edilmiştir. Farklı cihazlarda test edilmemiştir, ancak kalıcı değişiklikler yapmadığı için denemek herhangi bir soruna neden olmayacaktır .


---
## 📱 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/dd6a4db7-ac62-4af7-b61b-36fb2df7f840" width="250" alt="Home Screen 1" />
  <img src="https://github.com/user-attachments/assets/cefb43f4-3b09-4554-8b01-71299dee96b6" width="250" alt="Home Screen 2" />
  <img src="https://github.com/user-attachments/assets/0e1d02d3-fcdc-4a05-8854-7ad095506f4f" width="250" alt="App Interface" />
</p>

---
This application requires **ROOT** access.
