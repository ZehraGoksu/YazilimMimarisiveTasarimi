# YazilimMimarisiveTasarimi

## Proxy Tasarım Deseni

-Proxy tasarım deseni structural tasarım desenlerinden biridir. Bir nesneye erişimi kontrol etmek için, proxy nesne kullanır. Bu nesne erişilecek nesneyi kontrol eder.Bu tasarım deseni çalışma maliyeti yüksek işlemlerin olduğu yapılarda, web servisi kullanılan yapılarda, remoting uygulamalarında, operasyonun gerçekleştirilmesinden önce hazırlık yapılması veya ön işlem yapılması durumlarında kullanılır.

![Image of Class](https://github.com/ZehraGoksu/YazilimMimarisiveTasarimi/blob/master/proxy_uml.png)

Öncelikle türü interface veya abstract olan bir Subject sınıfı oluşturulur.

```java
interface IBanka
{
    bool OdemeYap(double Tutar);
}```

Bu sınıftan türeyecek veya sınıfı implement edecek Proxy ve RealSubject sınıfları yaratılır. Bu sınıflar aynı sınıftan türediği için, ortak metodlara sahip olurlar.
```java
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
```
Proxy sınıfına, Subject türüne sahip değişken eklenir. Proxy ve RealSubject aynı sınıftan türediği için türleri aynı olur. Bu sayede Proxy sınıfı içerisindeki bir metod içerisinden RealSubject sınıfındaki metod çağrılır.
Proxy sınıfında bulunan metoda ise Client sınıfı tarafından erişilir.Şimdi Proxy’i inşa ederek, Client tarafından erişim sağlanacak vekil sınıfımızı oluşturalım.
```java
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
 ```
Client sınıfı RealSubject sınıfına direkt erişmez, bunun yerine Proxy sınıfı aracılığı ile erişir. Proxy sınıfı bu özelliği ile erişimi kontrol eden sınıf olarak adlandırılır.Ayrıca RealSubject sınıfından nesne yaratılma işlemi Proxy sınıfında yapılır.
```java
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
    }  }
    
    
    
```

Client --> İstemcidir.  
Subject --> İstemcinin tek bir tip ile çalışmasını sağlayacak olan Interface yahut abstract class’ımızdır. Real Subject ve Proxy nesnelerimizin türediği yapıdır.  
Real Subject --> O anki işin asıl çalışmasını gerçekleştirecek olan gerçek nesnemizdir.  
Proxy --> Vekil sınıfımızdır. İçerisinde Real Subject referansını taşıyarak istemcinin isteklerine cevap verecektir. Doğal olarak istemci gerçek nesneye dolaylı yoldan Proxy üzerinden erişebilecektir.  






## Iterator Tasarım Deseni
-Iterator (tekrarlayıcı) tasarım deseni, behavior grubununa ait, nesne koleksyonlarının (list,array,queue) elemanlarını belirlenen kurallara göre elde edilmesini düzenleyen tasarım desenidir. Nesne tabanlı dillerde uygulama geliştirilirken en sık kullanılan yapılardan biri de koleksiyonlardır.  Iterator tasarım deseni ile koleksiyon yapısı bilinmesine ihtiyaç olmadan koleksiyon elemanları üzerinde işlem yapılabilmesini sağlar. Örneğin foreach döngüsü, iterasyon mantığıyla çalışan bir mekanizmadır. Haliyle bu mekanizmanın kullandığı kaynak niteliğindeki yapıları genel olarak koleksiyonlar ve diziler olarak düşünebiliriz. Bu yapıların kalıtımsal durumlarına dikkat edersek eğer IEnumerable interface’inden türemekteler ve bu tip sayesinde ortak bir noktada erişilebilir hale gelmektedirler. Ayrıca IEnumerable tipi kalıtım verdiği sınıfa iterasyon özelliği kazandırmaktadır.Koleksiyon içindeki nesnelerin nasıl elde edileceği tercihe göre belirlenebilir. Iterator tasarım deseninde 5 temel yapı bulunur.  

Iterator:  Koleksiyon elemanları elde edilebilmesi için gerekli işlemleri tanımlar.  
Aggregate:  Koleksiyon barındıran nesnelerin Iterator tipinden nesne oluşturacağını belirten arayüzdür.  
Concrete Aggregate:  Koleksiyon barındıran nesnedir. Aggregate arayüzünü uygular ve ilgili ConcreteIterator nesnesini oluşturur.  
ConcreteIterator:   Aggregate yapısında ki koleksiyon elemanlarının elde edilmesini sağlayan metotları barındıran yani Iterator arayüzünü uygulayan gerçek iterator nesnesidir.  
Client:  Bu yapıyı kullanarak koleksiyon içindeki elemanlara erişen yapıdır.  


![Image of Class](https://github.com/ZehraGoksu/YazilimMimarisiveTasarimi/blob/master/iterator_uml.jpg)

Öncelikle Aggregate arayüz oluşturulur. 
```java
interface IAggregate
{
    IIterator CreateIterator();
}
```
Ardından Iterator arayüzü oluşturulur.
```java
interface IIterator
{
    bool HasDate();
    DateTime CurrentDate();
}
```
Concrete nesnelerimizde öncelikle ConcreteAggregate nesnesini oluştururuz.
```java
class DateTimeAggregate : IAggregate
{
    public DateTime startDate;
    public DateTime endDate;
    public IIterator CreateIterator() => new DateTimeIterator(this);
}
```
Ve ardından ConcreteIterator nesnesi oluşturulur.
```java
class DateTimeIterator : IIterator
{
    DateTimeAggregate aggregate;
    DateTime currentDate;
    public DateTimeIterator(DateTimeAggregate aggregate)
    {
        this.aggregate = aggregate;
        currentDate = aggregate.startDate;
    }
    public DateTime CurrentDate() => currentDate;
    public bool HasDate()
    {
        if (currentDate.DayOfWeek == DayOfWeek.Saturday || currentDate.DayOfWeek == DayOfWeek.Sunday)
        {
            int dayCount = currentDate.DayOfWeek == DayOfWeek.Saturday ? 1 : 6;
            currentDate = currentDate.AddDays(dayCount);
        }
        else
        {
            int dayCount = (int)currentDate.DayOfWeek;
            currentDate = currentDate.AddDays(6 - dayCount);
            
        }
        return currentDate < aggregate.endDate;
    }
}
```
Kodda iki tarih arasındaki hafta sonlarını Iterator tasarım deseniyle hesaplamış olduk.  
Iterator Design Pattern sayesinde periyodik yahut aşamalı işlemleri çok rahat bir şekilde ve veri kümesini uygulamadan soyutlarak kullanabilme olanağı bulmuş oluyoruz.  
