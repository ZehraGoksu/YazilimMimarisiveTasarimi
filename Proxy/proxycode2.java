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