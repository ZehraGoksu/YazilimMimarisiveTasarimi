class Program
{
    static void Main(string[] args)
    {
        string KullaniciAdi = "", Sifre = "";
        double Tutar = 0;
        while (true)
        {
            Console.WriteLine("Lütfen kullanıcı adınızı giriniz.");
            KullaniciAdi = Console.ReadLine();
 
            Console.WriteLine("Lütfen şifrenizi giriniz.");
            Sifre = Console.ReadLine();
 
            Console.WriteLine("Lütfen ödenecek miktarı giriniz.");
            Tutar = Convert.ToInt32(Console.ReadLine());
 
            IBanka banka = new ProxyBanka(KullaniciAdi, Sifre);
            banka.OdemeYap(Tutar);
 
            Console.WriteLine("************");
        }
    }
}