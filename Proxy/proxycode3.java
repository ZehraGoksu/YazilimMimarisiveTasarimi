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
