interface IBanka
{
    bool OdemeYap(double Tutar);
}
class Banka : IBanka
{
    public bool OdemeYap(double Tutar)
    {
        if (Tutar < 100)
            Console.WriteLine($"Ödeyeceğiniz tutar 100 TL'den az olamaz. Fark -> { 100 - Tutar }");
        else if (Tutar > 100)
            Console.WriteLine($"Ödeyeceğiniz tutar 100 TL'den fazla olamaz. Fark -> { Tutar - 100 }");
        else
        {
            Console.WriteLine($"Ödeme başarıyla gerçekleştirildi. -> { Tutar }");
            return true;
        }
 
        return false;
    }
}
class ProxyBanka : IBanka
{
    Banka banka;
    bool Login;
    string KullaniciAdi, Sifre;
    public ProxyBanka(string KullaniciAdi, string Sifre)
    {
        this.KullaniciAdi = KullaniciAdi;
        this.Sifre = Sifre;
    }
 
    bool GirisYap()
    {
        if (KullaniciAdi.Equals("gncy") && Sifre.Equals("1234"))
        {
            banka = new Banka();
            Login = true;
            Console.WriteLine("Hesaba giriş yapıldı.");
            return true;
        }
        else
            Console.WriteLine("Lütfen kullanıcı adı ve şifreinizi doğru girdiğinize emin olunuz.");
 
        Login = false;
        return false;
    }
 
    public bool OdemeYap(double Tutar)
    {
        GirisYap();
 
        if (!Login)
        {
            Console.WriteLine("Hesaba giriş yapılmadığından dolayı ödeme alamıyoruz.");
            return false;
        }
 
        banka.OdemeYap(Tutar);
        return true;
    }
}
interface IResim
{
    void Goster(PictureBox pb, string Dizin);
}
 
class Resim : IResim
{
    public void Goster(PictureBox pb, string Dizin)
    {
        pb.ImageLocation = Dizin;
    }
}
 
class ProxyResim : IResim
{
    Resim resim;
    bool ResimYuklendi;
    PictureBox pb;
    string Dizin;
    void ResimYukle(object o)
    {
        resim = new Resim();
        resim.Goster(pb, Dizin);
        ResimYuklendi = true;
    }
    public void Goster(PictureBox pb, string Dizin)
    {
        this.pb = pb;
        this.Dizin = Dizin;
 
        if (resim == null)
        {
            new System.Threading.Timer(new TimerCallback(ResimYukle), this, 2000, 0);
        }
 
        if (!ResimYuklendi)
        {
            pb.ImageLocation = "D:\\Yukleniyor.gif";
        }
    }
}
