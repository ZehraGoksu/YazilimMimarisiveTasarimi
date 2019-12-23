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

          